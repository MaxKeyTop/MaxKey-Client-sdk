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
 

package org.maxkey.client.oauth.model;

import java.io.*;

import org.maxkey.client.http.SignatureType;

/**
 * Parameter object that groups OAuth config values
 * 
 * @author Pablo Fernandez
 */
public class OAuthConfig {
    private String baseWebUrl = null;
	private final String apiKey;
	private final String apiSecret;
	private final String callback;
	private final SignatureType signatureType;
	private final String scope;
	private String approvalPrompt; // 授权方式:force or auto
	private String responseType; // 返回类型:code、passwrod、token
	private final OutputStream debugStream;
	private  String state;
	//PKCE
	private  String codeVerifier;
	private  String codeChallengeMethod = "S256";
	
	private PkceKey pkceKey;
	
	public OAuthConfig(String key, String secret) {
		this(key, secret, null, null, null, null,null);
	}

	public OAuthConfig(String key, String secret, String callback) {
		this(key, secret, callback, null, null, null,null);
	}

	public OAuthConfig(String key, String secret, String callback,
			SignatureType type, String scope,String state, OutputStream stream) {
		this.apiKey = key;
		this.apiSecret = secret;
		this.callback = callback;
		this.signatureType = type;
		this.scope = scope;
		this.state = state;
		this.debugStream = stream;
	}

	public String getApiKey() {
		return apiKey;
	}

	public String getApiSecret() {
		return apiSecret;
	}

	public String getCallback() {
		return callback;
	}

	public SignatureType getSignatureType() {
		return signatureType;
	}

	public String getScope() {
		return scope;
	}

	public boolean hasScope() {
		return scope != null;
	}

	public String getApprovalPrompt() {
		return approvalPrompt;
	}

	public void setApprovalPrompt(String approvalPrompt) {
		this.approvalPrompt = approvalPrompt;
	}

	public String getResponseType() {
		return responseType;
	}

	public void setResponseType(String responseType) {
		this.responseType = responseType;
	}

	public String getCodeVerifier() {
        return codeVerifier;
    }

    public void setCodeVerifier(String codeVerifier) {
        this.codeVerifier = codeVerifier;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCodeChallengeMethod() {
        return codeChallengeMethod;
    }

    public void setCodeChallengeMethod(String codeChallengeMethod) {
        this.codeChallengeMethod = codeChallengeMethod;
    }

    public OutputStream getDebugStream() {
        return debugStream;
    }

    public String getBaseWebUrl() {
        return baseWebUrl;
    }

    public void setBaseWebUrl(String baseWebUrl) {
        this.baseWebUrl = baseWebUrl;
    }

    public PkceKey getPkceKey() {
        return pkceKey;
    }

    public void setPkceKey(PkceKey pkceKey) {
        this.pkceKey = pkceKey;
    }

    public void log(String message) {
		if (debugStream != null) {
			message = message + "\n";
			try {
				debugStream.write(message.getBytes("UTF8"));
			} catch (Exception e) {
				throw new RuntimeException(
						"there were problems while writting to the debug stream",
						e);
			}
		}
	}
}
