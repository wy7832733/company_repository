<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="${basePackage}.${moduleName}.${daoPackage}.${entityCamelName}Mapper" >
  <resultMap id="BaseResultMap" type="${basePackage}.${moduleName}.${entityPackage}.${entityCamelName}" >
    <#list primaryKeyList as col>
    <id column="${col.columnName}" property="${col.propertyName}" jdbcType="${col.columnType}" />
    </#list>
    <#list columns as col>
    <#if !col.primaryKey>
    <result column="${col.columnName}" property="${col.propertyName}" jdbcType="${col.columnType}" <#if col.columnType == 'DATE' || col.columnType=='TIMESTAMP'></#if> />
    </#if>
    </#list>
  </resultMap>
  
  <sql id="Base_Column_List" >
    <#list columns as col>t.${col.columnName}<#if col_index lt columns?size-1>,</#if></#list>
  </sql>
  
  <insert id="insert${entityCamelName}" parameterType="${basePackage}.${moduleName}.${entityPackage}.${entityCamelName}">
  	insert into ${tableFullName} (<#list columns as col><#if col_index &gt;= 0 && !col.identity>${col.columnName}</#if><#if !col.identity && col_index &gt;= 0 && col_index lt columns?size-1>,</#if></#list>) 
  	values (
  	<#list columns as col>
	  	<#if col_index &gt;= 0 && !col.identity>
	  	${'#'}{${col.propertyName},jdbcType=${col.columnType}}
	  	</#if>
  		<#if !col.identity! && col_index &gt;= 0 && col_index &lt; columns?size-1>,</#if>
  	</#list>)
  	<#if primaryKeyList?size==1>
  		<#list primaryKeyList as col>
  		<#if col.identity>
	    <selectKey resultType="${col.propertyType}" keyProperty="${col.propertyName}" >
	      select last_insert_id()
	    </selectKey>
	    </#if>
	    </#list>
    </#if>
  </insert>
  
  <update id="update${entityCamelName}" parameterType="${basePackage}.${moduleName}.${entityPackage}.${entityCamelName}">
  	update ${tableFullName} set 
  	<#list columns as col>
	  	<#if col_index gt 0>,</#if>
	  	<#assign jdbcType=col.columnType?replace(" UNSIGNED","")>
	    <#if jdbcType=="INT">
	    <#assign jdbcType="INTEGER">
	    <#elseif jdbcType=="DATETIME">
	    <#assign jdbcType="DATE">
	    </#if>
	  	${col.columnName}=${'#'}{${col.propertyName},jdbcType=${jdbcType}}
  	</#list>
  	where <#list primaryKeyList as col><#if col_index gt 0> and </#if>${col.columnName!}=${'#'}{${col.propertyName!},jdbcType=${col.columnType!}}</#list>
  </update>
  
  <update id="delete${entityCamelName}">
  	update ${tableFullName} set delete_flag= 1 where <#list primaryKeyList as col> <#if col_index gt 0> and </#if>${col.columnName}=${'#'}{${col.propertyName}}</#list>
  </update>
  
  <select id="get${entityCamelName}ManageMap" resultMap="BaseResultMap">
  	select <include refid="Base_Column_List"/> from ${tableFullName} t where 
  	<#list primaryKeyList as col> <#if col_index gt 0> and </#if>t.${col.columnName}=${'#'}{${col.propertyName}}</#list>
  </select>
  
  <sql id="BaseCondition">
  	<#list columns as col>
  	<if test="${col.propertyName}!=null and ${col.propertyName}!=''">
  	and t.${col.columnName}=${'#'}{${col.propertyName},jdbcType=${col.columnType}}
  	</if>
    </#list>
  </sql>
  
  <select id="get${entityCamelName}ListData" resultMap="BaseResultMap">
  	select <include refid="Base_Column_List"/> from ${tableFullName} t where t.delete_flag=0
  	<include refid="BaseCondition"/>
  	order by <#list primaryKeyList as col><#if col_index gt 0> , </#if>t.${col.columnName!} desc</#list>
  	LIMIT ${'#'}{offset},${'#'}{limit}
  </select>
  
  <select id="get${entityCamelName}ListDataCount" resultType="int">
  	select count(*) from ${tableFullName} t where t.delete_flag=0
  	<include refid="BaseCondition"/>
  </select>
  
   <select id="get${entityCamelName}ManageList" resultMap="BaseResultMap">
	  	select <include refid="Base_Column_List"/> from ${tableFullName} t where t.delete_flag=0
	  	<include refid="BaseCondition"/>
	  	order by <#list primaryKeyList as col><#if col_index gt 0> , </#if>t.${col.columnName!} desc</#list>	
  </select> 
  
</mapper>