/*
 * Copyright [2020] [MaxKey of copyright http://www.maxkey.top]
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
 

package org.maxkey.client.oauth.test;

import org.maxkey.client.http.Response;
import org.maxkey.client.oauth.OAuthClient;
import org.maxkey.client.oauth.builder.api.MaxkeyPasswordApi20;
import org.maxkey.client.oauth.domain.UserInfo;
import org.maxkey.client.oauth.model.OAuthConfig;
import org.maxkey.client.oauth.model.Token;
import org.maxkey.client.oauth.oauth.OAuthPasswordService;

public class MaxkeyPasswordDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 
		String accessTokenUrl="http://sso.maxkey.top/maxkey/oauth/v20/token";
		String clientId = "b32834accb544ea7a9a09dcae4a36403";
		String clientSerect = "E9UO53P3JH52aQAcnLP2FlLv8olKIB7u";
		
		String callback = "http://oauth.demo.maxkey.top:8080/demo-oauth/oauth20callback.jsp";
		String responseType ="token";
		String approvalprompt = "auto";
		OAuthConfig oauthServiceConfig=new OAuthConfig(clientId,clientSerect,callback);
	
		MaxkeyPasswordApi20	passwordApi20=new MaxkeyPasswordApi20(accessTokenUrl);
		
		OAuthPasswordService oAuthPasswordService=new OAuthPasswordService(oauthServiceConfig,passwordApi20);
		Token accessToken = null;
		Response response = null;
		accessToken = oAuthPasswordService.getAccessToken("admin", "maxkey"); 
		
		OAuthClient restClient=new OAuthClient("http://sso.maxkey.top/maxkey/api/oauth/v20/me");
		 
		 UserInfo userInfo=restClient.getUserInfo(accessToken.getAccess_token());

	}
	

}
