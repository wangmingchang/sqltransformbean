# 简介
这是一个将mysql库中的表生成java对应的po,dao,xml的小工具。
# maven地址
```
<dependency>
    <groupId>com.github.wangmingchang</groupId>
    <artifactId>sqltransformbean</artifactId>
     <version>1.0.0</version>
</dependency>

```
# 快速使用
1.新增一个名字为sqlTransformBean.properties的文件，或者拷贝git上相同名字文件</br>
2.调用jar中的SqlTranformBean.init()方法</br>
###properties相关配置如下
``` 
#数据库链接配置
jdbc.driverClassName=com.mysql.jdbc.Driver
jdbc.url=jdbc:mysql://127.0.0.1:3306/test?useSSL=false&useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&serverTimezone=Asia/Shanghai
jdbc.userName=root
jdbc.password=admin

#使用绝对路径保存bean,优先级最高
path.beanAbsoluteUrl=F:/eclipse-jee-oxyen-workspace/sqltransformbean/src/main/java/com/github/wangmingchang/sqltransformbean/pojo/po
#使用绝对路径保存dao,优先级最高
path.daoAbsoluteUrl=F:/eclipse-jee-oxyen-workspace/sqltransformbean/src/main/java/com/github/wangmingchang/sqltransformbean/pojo/dao
#使用绝对路径保存xml,优先级最高
path.xmlAbsoluteUrl=F:/eclipse-jee-oxyen-workspace/sqltransformbean/src/main/resources/mybatis/mapper

#java对象保存的项目路径（默认为“src/main/java”） 
path.beanTargetProject=src/main/java
#java对象保存的包名
path.beanPackageName=com.github.wangmingchang.sqltransformbean.pojo.po
#dao保存的保存的项目路径（默认为“src/main/java”） 
path.daoTargetProject=src/main/java
#dao保存的包名
path.daoPackageName=com.github.wangmingchang.sqltransformbean.pojo.dao
#xml保存的项目路径（默认为“src/main/resources”） 
path.xmlTargetProject=src/main/resources
#mybatis映射的xml保存的包名
path.xmlPackageName=mybatis/mapper
#是否开启生成java对象的名称,默认true
java.isBeanName=true
#是否开启生成dao的名称，默认为true
java.isDaoName=true
#是否开启生成xml的名称,默认为true
java.isXmlName=true

#表名
table.tableName=app_integeral

#生成java对象的名称
java.beanName=IntegeralEntity
#生成dao的名称，默认为dao后辍
java.daoName=IntegeralDao
#生成xml的名称,默认为dao后辍
java.xmlName=IntegeralDao
# dao和xml的后辍，默认为dao后辍
java.daoAndXmlSuffix=

#是否开启toString,默认false
java.isToString=true

#是否开启备注，默认false
java.remark.isRemark=true
#bean的备注
java.remark.beanRemark=积分类
#dao的备注
java.remark.daoRemark=积分Dao类
#创建人
java.remark.author=王明昌
#创建日期（默认当前日期）
#java.remark.createDate=


```

###执行调用
```
/**
 * @author:王明昌
 * @Date:2018/7/31
 */
public class SqlTranformExecute {

    public static void main(String[] arg){
        SqlTranformBean.init();
    }
}


```
3.1最终输出结果PO</br>

```
package com.github.wangmingchang.sqltransformbean.pojo.po;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 积分类
 * 
 * @author 王明昌
 * @since 2018-08-01
 */
public class IntegeralEntity {

	private Integer id; // 主键
	private Integer companyId; // 公司id(app_comapny表的id)
	private Integer userId; // 用户id(app_user表的id)
	private String companyName; // 公司名称
	private String memberName; // 会员名称
	private String mobilephone; // 手机号
	private String relevanceNo; // 关联单据号
	private BigDecimal amount; // 关联单据金额
	private BigDecimal weight; // 重量
	private Integer integeralNumber; // 积分数量
	private Integer surplusIntegeral; // 剩余可用积分
	private Date updateTime; // 修改时间
	private Date createTime; // 创建时间
	...
}

```
3.2最终输出结果DAO</br>
```
package com.github.wangmingchang.sqltransformbean.pojo.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.github.wangmingchang.sqltransformbean.pojo.po.IntegeralEntity;

/**
 * 积分Dao类
 * @author 王明昌
 * @date 2018-08-01
 */
public interface IntegeralDao {
	
	IntegeralEntity selectByPrimaryKey( @Param("id")  Integer id );
	
	List<IntegeralEntity> selectBySelective(Map paramMap);
	
	int deleteByPrimaryKey( @Param("id")  Integer id );
	
	int insert(IntegeralEntity integeralEntity);
	
	int insertSelective(IntegeralEntity integeralEntity);
	
	int updateByPrimaryKeySelective(IntegeralEntity integeralEntity);
	
	int updateByPrimaryKey(IntegeralEntity integeralEntity);
}
```
3.3最终输出结果XML</br>
```
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.github.wangmingchang.sqltransformbean.pojo.dao.IntegeralDao">
  <resultMap id="BaseResultMap" type="com.github.wangmingchang.sqltransformbean.pojo.po.IntegeralEntity">
    <id column="id" jdbcType="INTEGER" property="id" />
    <result column="company_id" jdbcType="INTEGER" property="companyId" />
    <result column="user_id" jdbcType="INTEGER" property="userId" />
    <result column="company_name" jdbcType="VARCHAR" property="companyName" />
    <result column="member_name" jdbcType="VARCHAR" property="memberName" />
    <result column="mobilephone" jdbcType="VARCHAR" property="mobilephone" />
    <result column="relevance_no" jdbcType="VARCHAR" property="relevanceNo" />
    <result column="amount" jdbcType="DECIMAL" property="amount" />
    <result column="weight" jdbcType="DECIMAL" property="weight" />
    <result column="integeral_number" jdbcType="INTEGER" property="integeralNumber" />
    <result column="surplus_integeral" jdbcType="INTEGER" property="surplusIntegeral" />
    <result column="update_time" jdbcType="TIMESTAMP" property="updateTime" />
    <result column="create_time" jdbcType="TIMESTAMP" property="createTime" />
  </resultMap>
  <sql id="Base_Column_List">
  	`id`, `company_id`, `user_id`, 
  	`company_name`, `member_name`, `mobilephone`, 
  	`relevance_no`, `amount`, `weight`, 
  	`integeral_number`, `surplus_integeral`, `update_time`, 
  	`create_time`
  </sql>
  <select id="selectByPrimaryKey" parameterType="java.lang.Integer" resultMap="BaseResultMap">
    select 
    <include refid="Base_Column_List" />
    from app_integeral
    where 
    `id` = #{id,jdbcType=INTEGER}
  </select>
  ...
  
 ```