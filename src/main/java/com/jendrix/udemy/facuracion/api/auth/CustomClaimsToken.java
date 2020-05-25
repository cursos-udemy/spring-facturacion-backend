package com.jendrix.udemy.facuracion.api.auth;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.jendrix.udemy.facuracion.api.model.entity.User;
import com.jendrix.udemy.facuracion.api.service.UserService;

@Component
public class CustomClaimsToken implements TokenEnhancer {

	@Autowired
	private UserService userService;

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {

		User user = this.userService.findByUsername(authentication.getName());
		if (user != null) {
			Map<String, Object> claims = new HashMap<>();
			claims.put("first_name", user.getName());
			claims.put("last_name", user.getLastname());
			claims.put("email", user.getEmail());

			((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(claims);
		}

		return accessToken;
	}

}
