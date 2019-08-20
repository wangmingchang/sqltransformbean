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
    /** ${columnDto.remark}*/
    private ${columnDto.fieldType} ${columnDto.fieldName+';'} <#if columnDto.remark != ''></#if>
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
        return "${tableDto.className} {" +
        <#list tableDto.columnDtos as columnDto>
                "${columnDto.fieldName}='" + ${columnDto.fieldName} + "\'" +
        </#list>
                "}";
    }
</#if>
}
