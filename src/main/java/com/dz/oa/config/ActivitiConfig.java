package com.dz.oa.config;import org.activiti.engine.*;import org.activiti.spring.ProcessEngineFactoryBean;import org.activiti.spring.SpringProcessEngineConfiguration;import org.apache.commons.dbcp.BasicDataSource;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.context.annotation.*;import org.springframework.core.env.Environment;import org.springframework.core.io.ClassPathResource;import org.springframework.core.io.Resource;import org.springframework.jdbc.datasource.DataSourceTransactionManager;import org.springframework.jdbc.datasource.DriverManagerDataSource;import org.springframework.transaction.annotation.EnableTransactionManagement;import javax.sql.DataSource;@Configuration@EnableTransactionManagement@ImportResource("classpath:activiti-context.xml")public class ActivitiConfig {}