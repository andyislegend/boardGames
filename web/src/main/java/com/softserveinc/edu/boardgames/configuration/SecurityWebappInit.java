package com.softserveinc.edu.boardgames.configuration;

import org.springframework.core.annotation.Order;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

@Order(2)
public class SecurityWebappInit extends AbstractSecurityWebApplicationInitializer {}
