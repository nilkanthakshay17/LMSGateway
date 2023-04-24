package com.cognizant.app.lms.gateway.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
public class AuthFilter extends AbstractGatewayFilterFactory<AuthFilter.Config>{

	@Autowired
	private RouteValidator routeValidator;
	
	@Autowired
	private JwtService jwtService;
	
	public AuthFilter() {
		super(Config.class);
	}
	
	@Override
	public GatewayFilter apply(Config config) {
		// TODO Auto-generated method stub
		return ((exchange,chain)->{
			if(routeValidator.isSecured.test(exchange.getRequest())) {
				if(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)){
			
					throw new RuntimeException("Missing Authorization Header");
				}
				
				String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
				
				if(null != authHeader && authHeader.startsWith("Bearer ")){
					authHeader = authHeader.substring(7);
				}
				try {
					jwtService.validateToken(authHeader);
				}
				catch(Exception e){
					throw new RuntimeException("Unauthorized Access to application");
				}
			}
			return chain.filter(exchange);
		});
	}
	
	public static class Config{
		
	}
	
}
