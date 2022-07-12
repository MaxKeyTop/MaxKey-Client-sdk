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

import org.maxkey.client.http.HttpVerb;

public class MaxkeyApi20 extends OAuthApi20 {
    // approval_prompt:force or auto
    private static final String DEFAULT_WEB_URL = "http://sso.maxkey.top/sign";

    private static final String AUTHORIZATION_ENDPOINT = "/authz/oauth/v20/authorize?client_id=%s&response_type=code&redirect_uri=%s&approval_prompt=auto";

    private static final String TOKEN_ENDPOINT    = "/authz/oauth/v20/token?grant_type=authorization_code";
    
    public MaxkeyApi20() {
        this.authorizeUrl = DEFAULT_WEB_URL + AUTHORIZATION_ENDPOINT;
    }

    @Override
    public String getAccessTokenEndpoint() {
        return DEFAULT_WEB_URL + TOKEN_ENDPOINT;
    }

    @Override
    public HttpVerb getAccessTokenVerb() {
        return HttpVerb.POST;
    }
    

    @Override
    public String getGrantType() {
        return "authorization_code";
    }
}
