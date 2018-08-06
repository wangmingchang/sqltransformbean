package ${daoPackageName};

import java.util.List;
import java.util.Map;
import ${beanPackageName!''}.${tableDto.className};

<#if remarkDto??>
/**
 * ${remarkDto.daoRemark}
 * @author ${remarkDto.author}
 * @date ${remarkDto.createDate}
 */
 </#if>
public interface ${tableDto.daoName} {
	
	${tableDto.className} selectByPrimaryKey(<#list tableDto.primaryKeyColumnDtos as primaryKeyColumnDto>${primaryKeyColumnDto.fieldType} ${primaryKeyColumnDto.fieldName} <#if primaryKeyColumnDto_has_next>,</#if></#list>);
	
	List<${tableDto.className}> selectBySelective(Map paramMap);
	
	int deleteByPrimaryKey(<#list tableDto.primaryKeyColumnDtos as primaryKeyColumnDto>${primaryKeyColumnDto.fieldType} ${primaryKeyColumnDto.fieldName} <#if primaryKeyColumnDto_has_next>,</#if></#list>);
	
	int insert(${tableDto.className} ${tableDto.className?uncap_first});
	
	int insertSelective(${tableDto.className} ${tableDto.className?uncap_first});
	
	int updateByPrimaryKeySelective(${tableDto.className} ${tableDto.className?uncap_first});
	
	int updateByPrimaryKey(${tableDto.className} ${tableDto.className?uncap_first});
}
