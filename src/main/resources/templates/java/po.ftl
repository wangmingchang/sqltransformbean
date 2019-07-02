package ${beanPackageName};

<#if tableDto.packageNames?? && tableDto.packageNames?size gt 0>
<#list tableDto.packageNames as packageName>import ${packageName};</#list>
</#if>
<#if remarkDto??>

/**
* ${remarkDto.beanRemark}
*
* @author ${remarkDto.author}
* @since ${remarkDto.createDate}
*/
</#if>
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
<#if tableDto.ifToString>
    @Override
    public String toString() {
    return "${tableDto.className} [<#rt>
    <#list tableDto.columnDtos as columnDto>
        <#if columnDto_index != 0>"</#if><#t>
        ${columnDto.fieldName}=" + ${columnDto.fieldName} + <#t>
        <#if !columnDto_has_next>"</#if><#t>
    </#list>
    ]";<#lt>
    }
</#if>
}
