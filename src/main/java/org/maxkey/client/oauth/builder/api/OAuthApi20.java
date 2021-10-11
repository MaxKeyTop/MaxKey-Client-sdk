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
import org.maxkey.client.oauth.extractors.*;
import org.maxkey.client.oauth.model.*;
import org.maxkey.client.utils.HttpEncoder;
import org.maxkey.client.utils.StringUtils;

/**
 * OAuth 2.0 api.
 */
public class OAuthApi20 extends DefaultApi20 {
    protected String authorizeUrl;
    protected String accessTokenUrl;
    protected String grantType            = "authorization_code";
    protected String accessTokenMethod    = "POST";

    public OAuthApi20() {
        super();
    }

    public OAuthApi20(String authorizeUrl, String accessTokenUrl) {
        super();
        this.authorizeUrl = authorizeUrl;
        this.accessTokenUrl = accessTokenUrl;
    }

    public OAuthApi20(String authorizeUrl, String accessTokenUrl, String accessTokenMethod) {
        super();
        this.authorizeUrl = authorizeUrl;
        this.accessTokenUrl = accessTokenUrl;
        this.accessTokenMethod = accessTokenMethod;
    }

    public OAuthApi20(String authorizeUrl, String accessTokenUrl, String grantType, String accessTokenMethod) {
        super();
        this.authorizeUrl = authorizeUrl;
        this.accessTokenUrl = accessTokenUrl;
        this.grantType = grantType;
        this.accessTokenMethod = accessTokenMethod;
    }


    @Override
    public HttpVerb getAccessTokenVerb() {
        if (accessTokenMethod != null && accessTokenMethod.toUpperCase().equals("POST")) {
            return HttpVerb.POST;
        } else {
            return HttpVerb.GET;
        }
    }

    @Override
    public AccessTokenExtractor getAccessTokenExtractor() {
        return new GsonJsonTokenExtractor();
    }

    @Override
    public String getAccessTokenEndpoint() {
        if (accessTokenUrl.indexOf("?") > 0) {
            return accessTokenUrl + "&grant_type=" + grantType;
        } else {
            return accessTokenUrl + "?grant_type=" + grantType;
        }
    }

    @Override
    public String getAuthorizationUrl(OAuthConfig config) {
        // Append scope if present
        StringBuffer authorizationUrl = new StringBuffer("");
        authorizationUrl.append(
                String.format(authorizeUrl, config.getApiKey(),HttpEncoder.encode(config.getCallback())));

        if (config.hasScope()) {
            authorizationUrl.append(String.format("&scope=%s", config.getScope()));
        }
        if (StringUtils.isNotBlank(config.getState())) {
            authorizationUrl.append(String.format("&state=%s", config.getState()));
        }
        if (StringUtils.isNotBlank(config.getCodeChallengeMethod())) {
            authorizationUrl.append(String.format("&code_challenge_method=%s", config.getCodeChallengeMethod()));
        }

        if (StringUtils.isNotBlank(config.getCodeVerifier())) {
            String codeChallenge = config.getCodeVerifier();
            if (config.getCodeChallengeMethod().toUpperCase().equals("S256")) {
                codeChallenge = DigestUtils.digestBase64Url(config.getCodeVerifier(), DigestUtils.Algorithm.SHA256);
            }
            authorizationUrl.append(
                    String.format("&code_challenge_method=%s",config.getCodeChallengeMethod().toUpperCase()));
            authorizationUrl.append(String.format("&code_challenge=%s", codeChallenge));
        }
        
        if (config.getPkceKey() != null) {
            authorizationUrl.append(String.format("&code_challenge=%s", config.getPkceKey().getCodeChallenge()));
            authorizationUrl.append(
                    String.format("&code_challenge_method=%s",config.getPkceKey().getCodeChallengeMethod()));
        }

        return authorizationUrl.toString();
    }

    public String getAuthorizeUrl() {
        return authorizeUrl;
    }

    public void setAuthorizeUrl(String authorizeUrl) {
        this.authorizeUrl = authorizeUrl;
    }

    public String getAccessTokenUrl() {
        return accessTokenUrl;
    }

    public void setAccessTokenUrl(String accessTokenUrl) {
        this.accessTokenUrl = accessTokenUrl;
    }

    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("OAuthApi20 [authorizeUrl=");
        builder.append(authorizeUrl);
        builder.append(", accessTokenUrl=");
        builder.append(accessTokenUrl);
        builder.append(", grantType=");
        builder.append(grantType);
        builder.append(", accessTokenMethod=");
        builder.append(accessTokenMethod);
        builder.append("]");
        return builder.toString();
    }

}
