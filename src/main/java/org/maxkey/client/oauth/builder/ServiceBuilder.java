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

package org.maxkey.client.oauth.builder;

import java.io.*;

import org.maxkey.client.http.SignatureType;
import org.maxkey.client.oauth.builder.api.*;
import org.maxkey.client.oauth.exceptions.*;
import org.maxkey.client.oauth.model.*;
import org.maxkey.client.oauth.oauth.*;
import org.maxkey.client.utils.Preconditions;
import org.maxkey.client.utils.StringUtils;

/**
 * Implementation of the Builder pattern, with a fluent interface that creates a
 * {@link OAuthService}
 * 
 * @author Pablo Fernandez
 *
 */
public class ServiceBuilder {
    private String baseWebUrl = null;
    private String apiKey;
    private String apiSecret;
    private String callback;
    private Api api;
    private String scope;
    private SignatureType signatureType;
    private OutputStream debugStream;

    private String state;
    // PKCE
    private String codeVerifier;
    private String codeChallengeMethod = "S256";

    /**
     * Default constructor
     */
    public ServiceBuilder() {
        this.callback = OAuthConstants.OUT_OF_BAND;
        this.signatureType = SignatureType.Header;
        this.debugStream = null;
    }

    /**
     * Configures the {@link Api}
     * 
     * @param apiClass the class of one of the existent {@link Api}s on
     *                 org.scribe.api package
     * @return the {@link ServiceBuilder} instance for method chaining
     */
    public ServiceBuilder provider(Class<? extends Api> apiClass) {
        this.api = createApi(apiClass);
        return this;
    }

    private Api createApi(Class<? extends Api> apiClass) {
        Preconditions.checkNotNull(apiClass, "Api class cannot be null");
        Api api;
        try {
            api = apiClass.newInstance();
        } catch (Exception e) {
            throw new OAuthException("Error while creating the Api object", e);
        }
        return api;
    }

    /**
     * Configures the {@link Api}
     *
     * Overloaded version. Let's you use an instance instead of a class.
     *
     * @param api instance of {@link Api}s
     * @return the {@link ServiceBuilder} instance for method chaining
     */
    public ServiceBuilder provider(Api api) {
        Preconditions.checkNotNull(api, "Api cannot be null");
        this.api = api;
        return this;
    }

    /**
     * Adds an OAuth callback url
     * 
     * @param callback callback url. Must be a valid url or 'oob' for out of band
     *                 OAuth
     * @return the {@link ServiceBuilder} instance for method chaining
     */

    public ServiceBuilder callback(String callback) {
        Preconditions.checkNotNull(callback, "Callback can't be null");
        this.callback = callback;
        return this;
    }

    public ServiceBuilder state(String state) {
        Preconditions.checkNotNull(callback, "state can't be null");
        this.state = state;
        return this;
    }

    public ServiceBuilder baseWebUrl(String baseWebUrl) {
        Preconditions.checkNotNull(baseWebUrl, "baseWebUrl can't be null");
        this.baseWebUrl = baseWebUrl;
        return this;
    }

    public ServiceBuilder codeVerifier(String codeVerifier) {
        Preconditions.checkNotNull(codeVerifier, "codeVerifier can't be null");
        this.codeVerifier = codeVerifier;
        return this;
    }

    public ServiceBuilder codeChallengeMethod(String codeChallengeMethod) {
        Preconditions.checkNotNull(codeChallengeMethod, "codeChallengeMethod can't be null");
        this.codeChallengeMethod = codeChallengeMethod;
        return this;
    }

    /**
     * Configures the api key
     * 
     * @param apiKey The api key for your application
     * @return the {@link ServiceBuilder} instance for method chaining
     */
    public ServiceBuilder apiKey(String apiKey) {
        Preconditions.checkEmptyString(apiKey, "Invalid Api key");
        this.apiKey = apiKey;
        return this;
    }

    /**
     * Configures the api secret
     * 
     * @param apiSecret The api secret for your application
     * @return the {@link ServiceBuilder} instance for method chaining
     */
    public ServiceBuilder apiSecret(String apiSecret) {
        Preconditions.checkEmptyString(apiSecret, "Invalid Api secret");
        this.apiSecret = apiSecret;
        return this;
    }

    /**
     * Configures the OAuth scope. This is only necessary in some APIs (like
     * Google's).
     * 
     * @param scope The OAuth scope
     * @return the {@link ServiceBuilder} instance for method chaining
     */
    public ServiceBuilder scope(String scope) {
        Preconditions.checkEmptyString(scope, "Invalid OAuth scope");
        this.scope = scope;
        return this;
    }

    /**
     * Configures the signature type, choose between header, querystring, etc.
     * Defaults to Header
     *
     * @param scope The OAuth scope
     * @return the {@link ServiceBuilder} instance for method chaining
     */
    public ServiceBuilder signatureType(SignatureType type) {
        Preconditions.checkNotNull(type, "Signature type can't be null");
        this.signatureType = type;
        return this;
    }

    public ServiceBuilder debugStream(OutputStream stream) {
        Preconditions.checkNotNull(stream, "debug stream can't be null");
        this.debugStream = stream;
        return this;
    }

    public ServiceBuilder debug() {
        this.debugStream(System.out);
        return this;
    }

    /**
     * Returns the fully configured {@link OAuthService}
     * 
     * @return fully configured {@link OAuthService}
     */
    public OAuthService build() {
        Preconditions.checkNotNull(api, "You must specify a valid api through the provider() method");
        Preconditions.checkEmptyString(apiKey, "You must provide an api key");
        Preconditions.checkEmptyString(apiSecret, "You must provide an api secret");
        OAuthConfig oauthConfig = new OAuthConfig(this.apiKey, this.apiSecret, this.callback, this.signatureType,
                this.scope, this.state, this.codeVerifier, this.codeChallengeMethod, this.debugStream);
        if (StringUtils.isNotBlank(this.baseWebUrl)) {
            oauthConfig.setBaseWebUrl(baseWebUrl);
        }
        return api.createService(oauthConfig);
    }
}
