package com.loukou.auth.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;


public abstract class BaseController{

	protected final Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	protected HttpServletRequest request;
	
}
