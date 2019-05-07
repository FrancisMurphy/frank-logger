package com.hbfintech.logger.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hbfintech.logger.LoggerContext;
import com.hbfintech.logger.configuration.CustomConfigBean;
import com.hbfintech.logger.configuration.parse.ConfigParse;
import com.hbfintech.logger.configuration.parse.XmlConfigParseImpl;
import com.hbfintech.logger.exception.LoggerInitException;

/**
 * <上下文初始化操作>
 * <功能详细描述>
 *
 * @author kaylves
 * @since 1.0
 */
public class ContextInitializer
{
    /**
     * the logger
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    protected String defaultLoggerConfig = "hbfintech-logger.xml";

    protected LoggerContext loggerContext;

    private ConfigParse configParse = new XmlConfigParseImpl();

    private WatchService watchService;

    public ContextInitializer(LoggerContext loggerContext)
    {
        super();
        this.loggerContext = loggerContext;
    }

    /**
     * <根据文件类型自动选择解析器并解析和初始化操作>
     * <功能详细描述>
     *
     * @throws FileNotFoundException
     */
    public void autoConfig()
    {
        initConfig();
        loggerContext.afterInitialize();
        startListener();
    }

    private void initConfig()
    {
        InputStream inputStream = ContextInitializer.class.getClassLoader()
                .getResourceAsStream(defaultLoggerConfig);

        if (inputStream == null)
        {
            throw new LoggerInitException(
                    "hbfintech-logger.xml not found on classpath");
        }

        CustomConfigBean customConfig = configParse.parse(inputStream);
        loggerContext.setCustomConfig(customConfig);
    }

    public void reloadConfig()
    {
        initConfig();
    }

    private void startListener()
    {
        try
        {
            watchService = FileSystems.getDefault().newWatchService();

            URL resourceUrl = Thread.currentThread().getContextClassLoader()
                    .getResource(defaultLoggerConfig);

            File file = new File(resourceUrl.toURI());

            logger.debug("file absolutePath:{}", file.getAbsolutePath());

            logger.debug("file parent {}:", file.getParent());

            Paths.get(file.getParent()).register(watchService,
                    StandardWatchEventKinds.ENTRY_MODIFY);

            registerWatch();

            registerShutdownListener();
        }
        catch (Exception e)
        {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * <当JVM关闭时执行操作>
     * <功能详细描述>
     */
    private void registerShutdownListener()
    {
        /** 注册关闭钩子 */
        Thread hook = new Thread(new Runnable()
        {
            @Override public void run()
            {
                try
                {
                    watchService.close();
                }
                catch (IOException e)
                {
                    //do none
                }
            }
        });

        Runtime.getRuntime().addShutdownHook(hook);
    }

    private void registerWatch()
    {
        /** 独立线程 */
        Thread watchThread = new Thread(new Runnable()
        {
            @Override public void run()
            {
                boolean flag = true;
                while (flag)
                {

                    try
                    {
                        watchProcess();
                    }
                    catch (Exception e)
                    {
                        flag = false;
                        logger.debug("watchService closed");

                    }

                }

            }

            private void watchProcess() throws InterruptedException
            {
                WatchKey watchKey = watchService.take();

                for (WatchEvent<?> event : watchKey.pollEvents())
                {
                    if (defaultLoggerConfig
                            .equalsIgnoreCase(event.context().toString()))
                    {
                        logger.debug("file name {}", event.context());
                        reloadConfig();
                        break;
                    }
                }
                watchKey.reset();
                // 完成一次监控就需要重置监控器一次
            }
        });

        watchThread.setDaemon(true);
        watchThread.start();
    }
}
