package com.loukou.auth.core.config;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.loukou.auth.exception.AuthRuntimeException;

@Component
public class AuthConfigLoader {

	private static final String PATH = "/META-INF/app.properties";
	private static final String KEY_APPID = "appid";
	private static final String APP_NAME = "app.name";
	private static final String SESSION_KEY = "AUTH_INFO_";
	private static final String LOGIN_URL_FORMAT = "http://cas.%s/login?appid=%s";

	private int appId;

	private String appName;

	private String sessionKey;

	private String loginUrl;

	@Resource(name = "env.domain")
	private String domain;

	@PostConstruct
	public void load() {
		InputStream in = null;
		try {
			in = Thread.currentThread().getContextClassLoader()
					.getResourceAsStream(PATH);
			if (in == null) {
				in = AuthConfigLoader.class.getResourceAsStream(PATH);
			}
			Properties props = new Properties();
			props.load(in);
			String appIdValue = props.getProperty(KEY_APPID);
			String appName = props.getProperty(APP_NAME);
			if (StringUtils.isNotBlank(appIdValue)
					&& StringUtils.isNumeric(appIdValue)
					&& StringUtils.isNotBlank(appName)) {
				this.appId = Integer.valueOf(appIdValue);
				this.appName = appName;
				this.sessionKey = SESSION_KEY + appName;
				this.loginUrl = String.format(LOGIN_URL_FORMAT, domain, appId);
			} else {
				throw new AuthRuntimeException(
						"param appid or app.name in /META-INF/app.properties is not set properly");
			}
		} catch (IOException e) {
			throw new AuthRuntimeException("Error when loading properties", e);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
				}
			}
		}
	}

	private static URL loadFromClassPath(String file) throws IOException {
		URL url = Thread.currentThread().getContextClassLoader()
				.getResource(file);
		if (null == url) {
			url = AuthConfigLoader.class.getResource(file);
		}
		if (null == url) {
			throw new FileNotFoundException("file " + file
					+ " doesn't exist in classpath");
		}

		return url;
	}

	public int getAppId() {
		return appId;
	}

	public String getAppName() {
		return appName;
	}

	public String getSessionKey() {
		return sessionKey;
	}

	public String getLoginUrl() {
		return loginUrl;
	}
}
