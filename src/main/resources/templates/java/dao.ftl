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
	
	${tableDto.className} selectByPrimaryKey(<#if tableDto.primaryKeyColumnDtos?size gt 1>Map paramMap<#else><#list tableDto.primaryKeyColumnDtos as primaryKeyColumnDto>${primaryKeyColumnDto.fieldType} ${primaryKeyColumnDto.fieldName}</#list></#if>);
	
	List<${tableDto.className}> selectBySelective(Map paramMap);
	
	int deleteByPrimaryKey(<#if tableDto.primaryKeyColumnDtos?size gt 1>Map paramMap<#else><#list tableDto.primaryKeyColumnDtos as primaryKeyColumnDto>${primaryKeyColumnDto.fieldType} ${primaryKeyColumnDto.fieldName}</#list></#if>);
	
	int insert(${tableDto.className} ${tableDto.className?uncap_first});
	
	int insertSelective(${tableDto.className} ${tableDto.className?uncap_first});
	
	int updateByPrimaryKeySelective(${tableDto.className} ${tableDto.className?uncap_first});
	
	int updateByPrimaryKey(${tableDto.className} ${tableDto.className?uncap_first});
}
