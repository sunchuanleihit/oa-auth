package com.loukou.auth.core.config;

import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;

public class SessionConfigInitializer extends AbstractHttpSessionApplicationInitializer 
{

	
	public SessionConfigInitializer() {
		super(SessionRedisConfig.class);
	}
	
}
