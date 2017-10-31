package ${beanPackageName};

/**
 * 测试返回类
 * 
 * @author 王明昌
 * @since 2017年9月9日
 */
public class ${tableDto.className} {

	<#list tableDto.columnDtos as columnDto>
	private ${columnDto.fieldType} ${columnDto.fieldName+';'} <#if columnDto.remark != ''>// ${columnDto.remark}</#if>
	</#list>

	<#list tableDto.columnDtos as columnDto>
	public ${columnDto.fieldType} get${columnDto.fieldName ? cap_first}() {
		return ${columnDto.fieldName};
	}

	public void set${columnDto.fieldName ? cap_first}(${columnDto.fieldType} ${columnDto.fieldName}) {
		this.${columnDto.fieldName} = ${columnDto.fieldName};
	}
	
	</#list>
}
