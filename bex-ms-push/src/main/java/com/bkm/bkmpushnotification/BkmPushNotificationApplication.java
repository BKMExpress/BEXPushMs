package com.bkm.bkmpushnotification;

import ch.qos.logback.classic.util.ContextInitializer;
import com.bkm.bkmpushnotification.security.XSSFilter;
import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.QueryParams;
import com.ecwid.consul.v1.Response;
import com.ecwid.consul.v1.catalog.model.CatalogService;
import com.ecwid.consul.v1.kv.model.GetValue;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.annotation.PostConstruct;
import java.util.*;

@SpringBootApplication
@EnableDiscoveryClient
public class BkmPushNotificationApplication {

	private static final Logger logger = LoggerFactory.getLogger(BkmPushNotificationApplication.class);

	public static void main(String[] args) {
		logger.info("Bkm Push Notification Application up and running");
		logger.info("Proxy host: " + System.getProperties().getProperty("http.proxyHost"));
		logger.info("Proxy port:" + System.getProperties().getProperty("http.proxyPort"));
		SpringApplication.run(BkmPushNotificationApplication.class, args);
	}

	@PostConstruct
	public void init(){
		TimeZone.setDefault(TimeZone.getTimeZone("Europe/Istanbul"));   // It will set UTC timezone
		logger.info("Spring boot application running in UTC timezone :" + new Date() + "  - " + TimeZone.getTimeZone("Europe/Istanbul"));   // It will print UTC timezone
	}

	@Bean
	FilterRegistrationBean myFilterRegistration() {
		FilterRegistrationBean frb = new FilterRegistrationBean();
		frb.setFilter(new XSSFilter());
		frb.setUrlPatterns(Arrays.asList("/*"));
		logger.info("XSS Filter Bean added");
		return frb;
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("*"));
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
		configuration.setAllowedHeaders(Arrays.asList("authorization", "content-type", "x-auth-token"));
		configuration.setExposedHeaders(Arrays.asList("x-auth-token"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		logger.info("CORS Configuration Source Bean added");
		return source;
	}

	@Bean
	public ConsulClient consulClient() {
		return new ConsulClient();
	}

	@Bean
	public DataSource dataSource(ConsulClient consulClient) {
		DataSource source = new DataSource();
		PoolProperties poolProperties = new PoolProperties();

		String dbConnectionUrl = getConsulValue(consulClient, "BKMPushNotification/dbConnectionUrl", false);
		String dbUsername = getConsulValue(consulClient, "BKMPushNotification/dbUsername", false);
		String dbPassword = getConsulValue(consulClient, "BKMPushNotification/dbPassword", false);
		String dbDriverClassName = getConsulValue(consulClient, "BKMPushNotification/dbDriverClassName", false);
		String dbValidationQuery= getConsulValue(consulClient, "BKMPushNotification/dbValidationQuery", false);
		String dbTestOnBorrow= getConsulValue(consulClient, "BKMPushNotification/dbTestOnBorrow", false);
		String dbMaxActive = getConsulValue(consulClient, "BKMPushNotification/dbMaxActive", false);
		String dbMaxIdle = getConsulValue(consulClient, "BKMPushNotification/dbMaxIdle", false);
		String dbMinIdle = getConsulValue(consulClient, "BKMPushNotification/dbMinIdle", false);
		String dbMaxWait = getConsulValue(consulClient, "BKMPushNotification/dbMaxWait", false);
		String dbInitialSize = getConsulValue(consulClient, "BKMPushNotification/dbInitialSize", false);
		String dbLogAbandoned = getConsulValue(consulClient, "BKMPushNotification/dbLogAbandoned", false);
		String dbRemoveAbandoned = getConsulValue(consulClient, "BKMPushNotification/dbRemoveAbandoned", false);
		String dbRemoveAbandonedTimeout = getConsulValue(consulClient, "BKMPushNotification/dbRemoveAbandoned", false);
		String dbValidationInterval = getConsulValue(consulClient, "BKMPushNotification/dbValidationInterval", false);

		poolProperties.setUrl(dbConnectionUrl);
		poolProperties.setUsername(dbUsername);
		poolProperties.setPassword(dbPassword);
		poolProperties.setDriverClassName(dbDriverClassName);
		poolProperties.setValidationQuery(dbValidationQuery);
		poolProperties.setTestOnBorrow(Boolean.parseBoolean(dbTestOnBorrow));
		poolProperties.setMaxActive(Integer.parseInt(dbMaxActive));
		poolProperties.setMaxIdle(Integer.parseInt(dbMaxIdle));
		poolProperties.setMinIdle(Integer.parseInt(dbMinIdle));
		poolProperties.setMaxWait(Integer.parseInt(dbMaxWait));
		poolProperties.setInitialSize(Integer.parseInt(dbInitialSize));
		poolProperties.setLogAbandoned(Boolean.parseBoolean(dbLogAbandoned));
		poolProperties.setRemoveAbandoned(Boolean.parseBoolean(dbRemoveAbandoned));
		poolProperties.setRemoveAbandonedTimeout(Integer.parseInt(dbRemoveAbandonedTimeout));
		poolProperties.setValidationInterval(Integer.parseInt(dbValidationInterval));


		source.setPoolProperties(poolProperties);
		return source;
	}

	private String getConsulValue(ConsulClient consulClient, String key, boolean ignore) {
		try {


			final GetValue getValue = consulClient.getKVValue(key).getValue();
			final String encoded = getValue.getValue();

			return new String(Base64.getDecoder().decode(encoded.getBytes()));
		} catch (Exception e) {
			if (ignore) return "";
			logger.info("get Consul value exception: " + ExceptionUtils.getStackTrace(e));
			throw new RuntimeException(e);
		}
	}

}
