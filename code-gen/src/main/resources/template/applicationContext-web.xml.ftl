<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE beans PUBLIC "-//SPRING//DTD BEAN//EN" "http://www.springframework.org/dtd/spring-beans.dtd">
<beans>
    <#list modelDefinitions?if_exists as modelDefinition>
    <bean id="${modelDefinition.className?uncap_first}Action" class="${modelDefinition.packageName}.action.${modelDefinition.className}Action" singleton="false">
    	<property name="${modelDefinition.className?uncap_first}Manager">
			<ref bean="${modelDefinition.className?uncap_first}Manager" />
		</property>
    </bean>
    </#list>
</beans>