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

package org.maxkey.client.oauth.oauth;

import org.maxkey.client.http.HttpVerb;
import org.maxkey.client.http.Response;
import org.maxkey.client.oauth.builder.api.*;
import org.maxkey.client.oauth.model.*;
import org.maxkey.util.StringUtils;

public class OAuth20ServiceImpl implements OAuthService {
    private static final String VERSION = "2.0";

    private final DefaultApi20 api;
    private final OAuthConfig config;

    /**
     * Default constructor
     * 
     * @param api    OAuth2.0 api information
     * @param config OAuth 2.0 configuration param object
     */
    public OAuth20ServiceImpl(DefaultApi20 api, OAuthConfig config) {
        this.api = api;
        this.config = config;
    }

    /**
     * Default constructor
     * 
     * @param clientId
     * @param clientSecret
     * @param redirectUri
     */
    public OAuth20ServiceImpl(String clientId, String clientSecret, String redirectUri) {
        this.api = new MaxkeyApi20();
        this.config = new OAuthConfig(clientId, clientSecret, redirectUri);

    }

    /**
     * {@inheritDoc}
     */
    public Token getAccessToken(Token requestToken, Verifier verifier) {
        OAuthRequest request = new OAuthRequest(api.getAccessTokenVerb(), api.getAccessTokenEndpoint());
        addParams(request, OAuthConstants.CLIENT_ID, config.getApiKey());
        addParams(request, OAuthConstants.CLIENT_SECRET, config.getApiSecret());
        addParams(request, OAuthConstants.CODE, verifier.getCode());
        addParams(request, OAuthConstants.CODE_VERIFIER, verifier.getCodeVerifier());
        if(config.getPkceKey() != null) {
            addParams(request, OAuthConstants.CODE_VERIFIER, config.getPkceKey().getCodeVerifier());
        }
        if(verifier.getPkceKey() != null) {
            addParams(request, OAuthConstants.CODE_VERIFIER, verifier.getPkceKey().getCodeVerifier());
        }
        addParams(request, OAuthConstants.REDIRECT_URI, config.getCallback());
        addParams(request, OAuthConstants.GRANT_TYPE, api.getGrantType());
        if (config.hasScope()) {
            addParams(request, OAuthConstants.SCOPE, config.getScope());
        }

        Response response = request.send();
        return api.getAccessTokenExtractor().extract(response.getBody());
    }

    public void addParams(OAuthRequest request, String key, String value) {
        if (StringUtils.isNotBlank(value)) {
            if (api.getAccessTokenVerb().equals(HttpVerb.GET)) {
                request.addQuerystringParameter(key, value);
            } else {
                request.getBodyParams().add(key, value);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public Token getRequestToken() {
        throw new UnsupportedOperationException(
                "Unsupported operation, please use 'getAuthorizationUrl' and redirect your users there");
    }

    /**
     * {@inheritDoc}
     */
    public String getVersion() {
        return VERSION;
    }

    /**
     * {@inheritDoc}
     */
    public void signRequest(Token accessToken, OAuthRequest request) {
        request.addQuerystringParameter(OAuthConstants.ACCESS_TOKEN, accessToken.getToken());
    }

    /**
     * {@inheritDoc}
     */
    public String getAuthorizationUrl(Token requestToken) {
        return api.getAuthorizationUrl(config);
    }

    @Override
    public void signAccessTokenRequest(Token accessToken, OAuthRequest request) {
        // TODO Auto-generated method stub
        request.addQuerystringParameter(OAuthConstants.ACCESS_TOKEN, accessToken.getToken());
    }

}
