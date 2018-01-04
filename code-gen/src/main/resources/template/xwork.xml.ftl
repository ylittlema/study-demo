<!DOCTYPE xwork PUBLIC "-//OpenSymphony Group//XWork 1.0//EN" "http://www.opensymphony.com/xwork/xwork-1.1.dtd">

<xwork>
    <include file="webwork-default.xml"/>

	<package name="default" extends="webwork-default">
			<interceptors>
				<interceptor-stack name="myDefaultStack">
					<interceptor-ref name="exception"/>
					<interceptor-ref name="alias"/>
					<interceptor-ref name="servlet-config"/>
					<interceptor-ref name="prepare"/>
					<interceptor-ref name="i18n"/>
					<interceptor-ref name="chain"/>
					<interceptor-ref name="model-driven"/>
					<interceptor-ref name="fileUpload"/>
					<interceptor-ref name="static-params"/>
					<interceptor-ref name="params"/>
					<interceptor-ref name="conversionError"/>
					<interceptor-ref name="validation">
						<param name="excludeMethods">input,back,cancel,list,view,execute,delete,query</param>
					</interceptor-ref>
					<interceptor-ref name="workflow">
						<param name="excludeMethods">input,back,cancel,list,view,execute,delete,query</param>
					</interceptor-ref>
				</interceptor-stack>
			</interceptors>
		<#list modelDefinitions?if_exists as modelDefinition>
		<action name="${modelDefinition.className?uncap_first}" class="${modelDefinition.className?uncap_first}Action">
			<result name="success" type="redirect">/${modelDefinition.className?uncap_first}.action</result>
			<!--
			<result name="list" type="freemarker">/WEB-INF/view/${modelDefinition.className?uncap_first}List.ftl</result>
			<result name="input" type="freemarker">/WEB-INF/view/${modelDefinition.className?uncap_first}Form.ftl</result>
			<result name="view" type="freemarker">/WEB-INF/view/${modelDefinition.className?uncap_first}View.ftl</result>
			-->
			<result name="list">/WEB-INF/view/${modelDefinition.className?uncap_first}List.jsp</result>
			<result name="input">/WEB-INF/view/${modelDefinition.className?uncap_first}Form.jsp</result>
			<result name="view">/WEB-INF/view/${modelDefinition.className?uncap_first}View.jsp</result>
			<interceptor-ref name="myDefaultStack">
			</interceptor-ref>
		</action>
		</#list>
	</package>
</xwork>
