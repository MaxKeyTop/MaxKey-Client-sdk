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
 

package org.maxkey.client.oauth.builder.api;

import org.maxkey.client.crypto.DigestUtils;
import org.maxkey.client.http.HttpVerb;
import org.maxkey.client.oauth.extractors.AccessTokenExtractor;
import org.maxkey.client.oauth.extractors.GsonJsonTokenExtractor;
import org.maxkey.client.oauth.model.OAuthConfig;
import org.maxkey.client.utils.HttpEncoder;
import org.maxkey.client.utils.Preconditions;
import org.maxkey.client.utils.StringUtils;

public class MaxkeyApi20 extends DefaultApi20 {
	//approval_prompt:force or auto
	private static final String DEFAULT_WEB_URL = "https://sso.maxkey.top/maxkey";
	
	private static final String AUTHORIZATION_URL = "%s/authz/oauth/v20/authorize?client_id=%s&response_type=code&redirect_uri=%s&approval_prompt=auto";
    
    public MaxkeyApi20() {
   
	}

	@Override
    public String getAccessTokenEndpoint() {
    	return getWebUrl() + "/authz/oauth/v20/token?grant_type=authorization_code";
    }

    
    @Override
    public String getAuthorizationUrl(OAuthConfig config) {
    	Preconditions.checkValidUrl(config.getCallback(), "Must provide a valid url as callback. Secure does not support OOB");
    	
    	StringBuffer authorizationUrl = new StringBuffer("");
    	authorizationUrl.append(
    	        String.format(AUTHORIZATION_URL, getWebUrl(),config.getApiKey(), HttpEncoder.encode(config.getCallback()))
    	        );
    	
    	if(config.hasScope()) {
    	    authorizationUrl.append(String.format("&scope=%s", config.getScope()));
    	} 
    	if(StringUtils.isNotBlank(config.getState())) {
            authorizationUrl.append(String.format("&state=%s", config.getState()));
        }
    	if(StringUtils.isNotBlank(config.getCodeChallengeMethod())) {
            authorizationUrl.append(String.format("&code_challenge_method=%s", config.getCodeChallengeMethod()));
        }
    	
    	if(StringUtils.isNotBlank(config.getCodeVerifier())) {
    	    String codeChallenge = config.getCodeVerifier();
    	    if(config.getCodeChallengeMethod().toUpperCase().equals("S256")) {
    	        codeChallenge =DigestUtils.digestBase64Url(config.getCodeVerifier(),DigestUtils.Algorithm.SHA256);
    	    }
            authorizationUrl.append(String.format("&code_challenge=%s", codeChallenge));
        }
    	
    	return authorizationUrl.toString();
    }
    
    

    @Override
	public HttpVerb getAccessTokenVerb() {
		return HttpVerb.POST;
	}


	@Override
    public AccessTokenExtractor getAccessTokenExtractor() {
    	return new GsonJsonTokenExtractor();
    }
    
    private String getWebUrl() {
    	String webUrl = null;
    	if(webUrl == null || "".equals(webUrl)) {
    		webUrl = DEFAULT_WEB_URL;
    	}
    	return webUrl;
    }


	@Override
	public String getGrantType() {
		return "authorization_code";
	}
}
