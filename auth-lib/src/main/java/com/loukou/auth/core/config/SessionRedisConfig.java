package com.loukou.auth.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import com.github.diamond.client.PropertiesConfigurationFactoryBean;

@EnableRedisHttpSession
public class SessionRedisConfig {

	private static final String KEY_REDIS_PORT = "redis.port";
	private static final String KEY_REDIS_HOST = "redis.host";

	@Bean
	public JedisConnectionFactory connectionFactory() {
		JedisConnectionFactory connection = new JedisConnectionFactory();
		int port = PropertiesConfigurationFactoryBean
				.getPropertiesConfiguration().getInt(KEY_REDIS_PORT);
		String host = PropertiesConfigurationFactoryBean
				.getPropertiesConfiguration().getString(KEY_REDIS_HOST);
		connection.setPort(port);
		connection.setHostName(host);
		return connection;
	}
}
