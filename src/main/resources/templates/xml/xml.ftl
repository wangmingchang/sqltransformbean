<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${daoPackageName}.${tableDto.daoName}">
  <resultMap id="BaseResultMap" type="${beanPackageName}.${tableDto.className}">
  <#list tableDto.primaryKeyColumnDtos as primaryKeyColumnDto>
    <id column="${primaryKeyColumnDto.columnName}" jdbcType="${primaryKeyColumnDto.columnType}" property="${primaryKeyColumnDto.fieldName}" />
  </#list>
  <#list tableDto.columnDtos as columnDto>
  	<#if !columnDto.ifKey>
    <result column="${columnDto.columnName}" jdbcType="${columnDto.columnType}" property="${columnDto.fieldName}" />
  	</#if>
  </#list>
  </resultMap>
  <sql id="Base_Column_List">
  <#list tableDto.columnDtos as columnDto>
  <#if (columnDto_index + 1) % 3 == 0 && columnDto_index != 0 >
  	${columnDto.columnName}<#if columnDto_has_next>,</#if>
  <#else>
  	${columnDto.columnName}<#if columnDto_has_next>,</#if> <#rt>
  </#if>
  </#list>
  
  </sql>
  <select id="selectByPrimaryKey" parameterType="<#if tableDto.primaryKeyColumnDtos?size gt 1>java.util.Map<#else>java.lang.String</#if>" resultMap="BaseResultMap">
    select 
    <include refid="Base_Column_List" />
    from ${tableDto.tableName}
    where 
    <#list tableDto.primaryKeyColumnDtos as primaryKeyColumnDto>
    ${primaryKeyColumnDto.columnName} = #${"{" + primaryKeyColumnDto.fieldName},jdbcType="${primaryKeyColumnDto.columnType}"}<#if primaryKeyColumnDto_has_next> AND </#if>
    </#list>
  </select>
  
  <select id="selectBySelective" parameterType="java.util.Map" resultMap="BaseResultMap">
    select 
    <include refid="Base_Column_List" />
    from city
    where 
    <trim suffixOverrides="AND">
	    <#list tableDto.columnDtos as columnDto>
	    <if test="${columnDto.fieldName} != null and ${columnDto.fieldName} != ''">
	    	${columnDto.columnName} = #${"{" + columnDto.fieldName},jdbcType="${columnDto.columnType}"} AND 
	    </if>
	    </#list>
    </trim>
  </select>  
  
  <delete id="deleteByPrimaryKey" parameterType="<#if tableDto.primaryKeyColumnDtos?size gt 1>java.util.Map<#else>java.lang.String</#if>">
    delete from ${tableDto.tableName}
    where 
    <#list tableDto.primaryKeyColumnDtos as primaryKeyColumnDto>
    ${primaryKeyColumnDto.columnName} = #${"{" + primaryKeyColumnDto.fieldName},jdbcType="${primaryKeyColumnDto.columnType}"}<#if primaryKeyColumnDto_has_next> AND </#if>
    </#list>
  </delete>
  
  <insert id="insert" parameterType="${beanPackageName}.${tableDto.className}">
    insert into ${tableDto.tableName} (
    <#list tableDto.columnDtos as columnDto>
    <#if (columnDto_index + 1) % 3 == 0 && columnDto_index != 0 >
    ${columnDto.columnName}<#if columnDto_has_next>, </#if>
    <#else>
    ${columnDto.columnName}<#if columnDto_has_next>, </#if><#rt>
    </#if>
    </#list>
    )
    values (
    <#list tableDto.columnDtos as columnDto>
    <#if (columnDto_index + 1) % 3 == 0 && columnDto_index != 0 >
    #${"{" + columnDto.fieldName},jdbcType="${columnDto.columnType}"}<#if columnDto_has_next>, </#if>
    <#else>
    #${"{" + columnDto.fieldName},jdbcType="${columnDto.columnType}"}<#if columnDto_has_next>, </#if><#rt>
    </#if>
    </#list>
    )
  </insert>
  
  <insert id="insertSelective" parameterType="${beanPackageName}.${tableDto.className}">
    insert into ${tableDto.tableName}
    <trim prefix="(" suffix=")" suffixOverrides=",">
    <#list tableDto.columnDtos as columnDto>
      <if test="${columnDto.fieldName} != null and ${columnDto.fieldName} != ''">
        ${columnDto.columnName}, 
      </if>
    </#list>
    </trim>
    <trim prefix="values (" suffix=")" suffixOverrides=",">
    <#list tableDto.columnDtos as columnDto>
      <if test="${columnDto.fieldName} != null and ${columnDto.fieldName} != ''">
        #${"{" + columnDto.fieldName},jdbcType="${columnDto.columnType}"}, 
      </if>
    </#list>
    </trim>
  </insert>
  
  <update id="updateByPrimaryKeySelective" parameterType="${beanPackageName}.${tableDto.className}">
    update ${tableDto.tableName}
    <set>
	    <trim suffixOverrides=",">
		    <#list tableDto.columnDtos as columnDto>
		      <if test="${columnDto.fieldName} != null and ${columnDto.fieldName} != ''">
		        ${columnDto.columnName} = #${ "{" + columnDto.fieldName},jdbcType="${columnDto.columnType}"}, 
		      </if>
		    </#list>
	    </trim>
    </set>
    where 
    <#list tableDto.primaryKeyColumnDtos as primaryKeyColumnDto>
    ${primaryKeyColumnDto.columnName} = #${"{" + primaryKeyColumnDto.fieldName},jdbcType="${primaryKeyColumnDto.columnType}"} <#if primaryKeyColumnDto_has_next> AND </#if>
    </#list>
  </update>
  
  <update id="updateByPrimaryKey" parameterType="${beanPackageName}.${tableDto.className}">
    update ${tableDto.tableName}
    set 
    <trim suffixOverrides=",">
    <#list tableDto.columnDtos as columnDto>
      ${columnDto.columnName} = #${"{" + columnDto.fieldName},jdbcType="${columnDto.columnType}"}, 
    </#list>
    </trim>
    where 
    <#list tableDto.primaryKeyColumnDtos as primaryKeyColumnDto>
    ${primaryKeyColumnDto.columnName} = #${"{" + primaryKeyColumnDto.fieldName},jdbcType="${primaryKeyColumnDto.columnType}"} <#if primaryKeyColumnDto_has_next> AND </#if>
    </#list>
  </update>

</mapper>