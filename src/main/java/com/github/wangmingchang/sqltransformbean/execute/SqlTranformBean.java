package com.github.wangmingchang.sqltransformbean.execute;

import java.net.URL;
import java.util.Date;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.wangmingchang.sqltransformbean.pojo.dto.RemarkDto;
import com.github.wangmingchang.sqltransformbean.pojo.dto.TableDto;
import com.github.wangmingchang.sqltransformbean.util.DateUtil;
import com.github.wangmingchang.sqltransformbean.util.JavaTemlateUtil;
import com.github.wangmingchang.sqltransformbean.util.MySQLTableUtil;
import com.github.wangmingchang.sqltransformbean.util.PropertiesUtil;
import com.github.wangmingchang.sqltransformbean.util.StringUtil;

/**
 * 生成类
 *
 * @author 王明昌
 * @since 2017年10月31日
 */
public class SqlTranformBean {

    private static final Logger logger = LoggerFactory.getLogger(SqlTranformBean.class);

    public static void main(String[] args) {

        SqlTranformBean.init();
    }

    private static final String DAOANDXML_SUFFIX = "Dao"; // dao和xml的后辍

    public static void init() {
        logger.info("***********************开始执行***********************");
        URL classpath = Thread.currentThread().getContextClassLoader().getResource("");
        String rootPate = classpath.getPath();
        Properties properties = PropertiesUtil.loadProps(rootPate + "sqlTransformBean.properties");
        String beanTargetProject = properties.getProperty("path.beanTargetProject", "src/main/java");
        String beanPackageName = properties.getProperty("path.beanPackageName");
        String daoTargetProject = properties.getProperty("path.daoTargetProject", "src/main/java");
        String daoPackageName = properties.getProperty("path.daoPackageName");
        String xmlTargetProject = properties.getProperty("path.xmlTargetProject", "src/main/resource");
        String xmlPackageName = properties.getProperty("path.xmlPackageName");
        String beanAbsoluteUrl = properties.getProperty("path.beanAbsoluteUrl");
        String daoAbsoluteUrl = properties.getProperty("path.daoAbsoluteUrl");
        String xmlAbsoluteUrl = properties.getProperty("path.xmlAbsoluteUrl");

        String isBeanName = properties.getProperty("java.isBeanName");
        isBeanName = StringUtil.isBlank(isBeanName) ? "true" : isBeanName;
        String isDaoName = properties.getProperty("java.isDaoName");
        isDaoName = StringUtil.isBlank(isDaoName) ? "true" : isDaoName;
        String isXmlNameableName = properties.getProperty("java.isXmlNameableName");
        isXmlNameableName = StringUtil.isBlank(isXmlNameableName) ? "true" : isXmlNameableName;

        String tableName = properties.getProperty("table.tableName");

        String beanName = properties.getProperty("java.beanName");
        String daoName = properties.getProperty("java.daoName");
        String xmlName = properties.getProperty("java.xmlName");
        String isToString = properties.getProperty("java.isToString", "false");
        String daoAndXmlSuffix = properties.getProperty("java.daoAndXmlSuffix");
        daoAndXmlSuffix = StringUtil.isNotBlank(daoAndXmlSuffix) ? daoAndXmlSuffix : DAOANDXML_SUFFIX;

        String isRemark = properties.getProperty("java.remark.isRemark", "false");
        String beanRemark = properties.getProperty("java.remark.beanRemark", "the is javabean");
        String daoRemark = properties.getProperty("java.remark.daoRemark", "the is dao");
        String author = properties.getProperty("java.remark.author");
        String defaultDate = DateUtil.DateToString(new Date());
        String createDate = properties.getProperty("java.remark.createDate", defaultDate);
        RemarkDto remarkDto = null;
        // 是否开启备注信息
        if ("true".equals(isRemark)) {
            remarkDto = new RemarkDto();
            remarkDto.setBeanRemark(beanRemark);
            remarkDto.setDaoRemark(daoRemark);
            if (!StringUtil.isNotBlank(author)) {
                Properties props = System.getProperties();
                author = props.getProperty("user.name");
            }
            remarkDto.setAuthor(author);
            remarkDto.setCreateDate(createDate);
        }

        String beanSavePath = rootPate + beanPackageName.replace(".", "/") + "/";
        beanSavePath = StringUtil.transform(beanSavePath, "target/classes", beanTargetProject);
        if (StringUtil.isNotBlank(beanAbsoluteUrl)) {
            //如果有绝对路径，优先级最高
            beanSavePath = beanAbsoluteUrl.replace("\\\\", "/") + "/";
        }
        logger.info("beanSavePath:" + beanSavePath);
        String daoSavePath = rootPate + daoPackageName.replace(".", "/") + "/";
        daoSavePath = StringUtil.transform(daoSavePath, "target/classes", daoTargetProject);
        if (StringUtil.isNotBlank(daoAbsoluteUrl)) {
            //如果有绝对路径，优先级最高
            daoSavePath = daoAbsoluteUrl.replace("\\\\", "/") + "/";
        }
        logger.info("daoSavePath:" + daoSavePath);
        String xmlSavePath = rootPate + xmlPackageName.replace(".", "/") + "/";
        xmlSavePath = StringUtil.transform(xmlSavePath, "target/classes", xmlTargetProject);
        if (StringUtil.isNotBlank(xmlAbsoluteUrl)) {
            //如果有绝对路径，优先级最高
            xmlSavePath = xmlAbsoluteUrl.replace("\\\\", "/") + "/";
        }
        logger.info("xmlSavePath:" + xmlSavePath);

        TableDto tableDto = MySQLTableUtil.getColumnByTableName(tableName);
        if (tableDto.getPrimaryKeyColumnDtos().size() == 0) {
            throw new RuntimeException("表名为：" + tableName + "检测到没有主键");
        }
        if (StringUtil.isNotBlank(beanName)) {
            tableDto.setClassName(beanName);
        } else {
            beanName = tableDto.getClassName();
        }
        if (StringUtil.isBlank(daoName)) {
            daoName = beanName + daoAndXmlSuffix;
        }
        if (StringUtil.isBlank(xmlName)) {
            xmlName = beanName + daoAndXmlSuffix;
        }

        tableDto.setDaoName(daoName);
        tableDto.setXmlName(xmlName);

        if ("true".equals(isToString)) {
            tableDto.setIfToString(Boolean.valueOf(isToString));
        }
        logger.info("tableDto:" + tableDto);
        logger.info("remarkDto:" + remarkDto);
        if ("true".equals(isBeanName)) {
            JavaTemlateUtil.setJavaPoTemplate(beanSavePath, tableDto, beanPackageName, remarkDto);
        }
        if ("true".equals(isDaoName)) {
            JavaTemlateUtil.setJavaDaoTemplate(daoSavePath, tableDto, daoPackageName, remarkDto, beanPackageName);
        }
        if ("true".equals(isXmlNameableName)) {
            JavaTemlateUtil.setJavaXmlTemplate(xmlSavePath, tableDto, xmlPackageName, beanPackageName, daoPackageName);
        }
        logger.info("***********************执行结束*********************");
    }
}
