package com.hbfintech.logger.configuration.parse;

import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.hbfintech.logger.configuration.CustomConfigBean;
import com.hbfintech.logger.configuration.LoggerConfigBean;
import com.hbfintech.logger.configuration.PatterConfigBean;
import com.hbfintech.logger.configuration.PatternType;
import com.hbfintech.logger.configuration.WhiteListConfigBean;
import com.hbfintech.logger.constants.LoggerType;

/**
 * Xml文件CustomLogger配置解析器
 *
 * @author kaylves
 * @since 1.0
 */
public class XmlConfigParseImpl implements ConfigParse
{

    private static final String LOGGER_SCHAME = "logger";

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override public CustomConfigBean parse(InputStream inputStream)
    {
        logger.debug("start transfer logger file>>>>>>>>>>>>>>>>>>>>");

        Element rootElement = getDocument(inputStream).getDocumentElement();
        NodeList nodelList = rootElement.getElementsByTagName(LOGGER_SCHAME);

        Element loggerNode = null;

        CustomConfigBean customConfig = new CustomConfigBean();

        LoggerConfigBean loggerConfigBean = null;

        for (int i = 0; i < nodelList.getLength(); i++)
        {
            loggerNode = (Element) nodelList.item(i);
            loggerConfigBean = new LoggerConfigBean();

            if (loggerNode.hasAttribute("turn"))
            {
                loggerConfigBean.setTurn(Boolean.valueOf(
                        loggerNode.getAttribute("turn").trim()));
            }

            loggerConfigBean.setType(LoggerType
                    .transfer(loggerNode.getAttribute("type").trim()));
            loggerConfigBean.setWhitelists(transferWhitePattern(loggerNode));
            customConfig.getLoggerConfigCache()
                    .put(loggerConfigBean.getType(), loggerConfigBean);
        }
        logger.debug("transfer logger file successfully>>>>>>>>>>>>>>>>>>>>");

        customConfig.prepare();

        return customConfig;
    }

    private List<WhiteListConfigBean> transferWhitePattern(Element loggerNode)
    {
        List<WhiteListConfigBean> whitelists = new LinkedList<>();
        logger.debug("logger type:{},context:{}",
                loggerNode.getAttribute("type").trim(),
                loggerNode.getTextContent().trim());

        NodeList nodelList = loggerNode.getElementsByTagName("whitelists");
        Element node = (Element) nodelList.item(0);
        Element patternNode = null;
        if (node != null)
        {

            logger.debug("node name :{},whitelist context:{}",
                    node.getNodeValue(), node.getTextContent().trim());

            NodeList patternList = node.getElementsByTagName("pattern");
            WhiteListConfigBean whiltePattern;
            PatterConfigBean pattern = null;
            for (int k = 0; k < patternList.getLength(); k++)
            {
                patternNode = (Element) patternList.item(k);
                whiltePattern = new WhiteListConfigBean();
                pattern = new PatterConfigBean();
                pattern.setPattern(patternNode.getTextContent().trim());
                pattern.setType(
                        PatternType.transfer(patternNode.getAttribute("type")));

                whiltePattern.setPattern(pattern);

                whitelists.add(whiltePattern);
            }
        }
        return whitelists;
    }

    public Document getDocument(InputStream inputStream)
    {
        try
        {
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory
                    .newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            return builder.parse(inputStream);
        }
        catch (Exception e)
        {
            logger.error(e.getMessage(), e);
        }
        return null;
    }
}
