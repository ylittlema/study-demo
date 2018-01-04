<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE beans PUBLIC "-//SPRING//DTD BEAN//EN" "http://www.springframework.org/dtd/spring-beans.dtd">


<beans>
	<bean id="dataSource" class="org.apache.commons.dbcp.BasicDataSource" 
		destroy-method="close">
		<property name="driverClassName">
			<value>com.mysql.jdbc.Driver</value>
		</property>
		<property name="url">
			<value>jdbc:mysql:///test</value>
		</property>
		<property name="username">
			<value>root</value>
		</property>
		<property name="password">
			<value></value>
		</property>
	</bean>
	<bean id="sessionFactory" 
		class="org.springframework.orm.hibernate3.LocalSessionFactoryBean">
		<property name="dataSource">
			<ref bean="dataSource"/>
		</property>
		<property name="mappingResources">
			<list>
				<#list modelDefinitions?if_exists as modelDefinition>
				<value>${modelDefinition.packageName?replace('.','/')}/model/${modelDefinition.className}.hbm.xml</value>
				</#list>
			</list>
		</property>
		<property name="hibernateProperties">
			<props>
				<prop key="hibernate.dialect"> 
					org.hibernate.dialect.MySQLDialect</prop>
				<prop key="hibernate.show_sql">true</prop>
				<prop key="hibernate.hbm2ddl.auto">create-drop</prop>
			</props>
		</property>
	</bean>
	<bean id="transactionManager" 
		class="org.springframework.orm.hibernate3.HibernateTransactionManager">
		<property name="sessionFactory">
			<ref bean="sessionFactory"/>
		</property>
		<property name="dataSource">
			<ref bean="dataSource"/>
		</property>
	</bean>
	<bean id="managerProxy" abstract="true" 
		class="org.springframework.transaction.interceptor.TransactionProxyFactoryBean">
		<property name="transactionManager">
			<ref bean="transactionManager" />
		</property>
		<property name="transactionAttributes">
			<props>
				<prop key="save*">PROPAGATION_REQUIRED</prop>
				<prop key="delete*">PROPAGATION_REQUIRED</prop>
				<prop key="get*">PROPAGATION_REQUIRED,readOnly</prop>
				<prop key="count*">PROPAGATION_REQUIRED,readOnly</prop>
			</props>
		</property>
	</bean>
	<#list modelDefinitions?if_exists as modelDefinition>
	<bean id="${modelDefinition.className?uncap_first}Manager" parent="managerProxy">
		<property name="target">
			<bean class="${modelDefinition.packageName}.service.${modelDefinition.className}ManagerImpl">
				<property name="dao">
					<bean class="${modelDefinition.packageName}.dao.${modelDefinition.className}DAOImpl">
						<property name="sessionFactory">
							<ref bean="sessionFactory"/>
						</property>
					</bean>
				</property>
			</bean>
		</property>
	</bean>
	</#list>
</beans>
