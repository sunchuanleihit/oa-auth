package com.loukou.auth.core.config;

import java.io.IOException;
import java.io.InputStream;
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
	private static final String LOGIN_URL_FORMAT = "http://%s/login?appid=%s";
	private static final String NO_PRIV_URL_FORMAT = "http://%s/no_priv?appid=%s";

	private int appId;

	private String appName;

	private String sessionKey;

	private String loginUrl;
	
	private String noPrivUrl;

	@Resource(name = "cas.url")
	private String casUrl;
	

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
				this.loginUrl = String.format(LOGIN_URL_FORMAT, casUrl, appId);
				this.noPrivUrl = String.format(NO_PRIV_URL_FORMAT, casUrl, appId);
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
	
	public String getNoPrivUrl() {
		return noPrivUrl;
	}
	
}
