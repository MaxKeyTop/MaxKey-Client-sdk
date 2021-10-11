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

import org.maxkey.client.utils.Preconditions;

/**
 * Represents an OAuth verifier code.
 * 
 * @author Pablo Fernandez
 */
public class Verifier {

    private  String  code;
    private  String  codeVerifier;

    private  PkceKey pkceKey;
    /**
     * Default constructor.
     * 
     * @param value verifier value
     */
    public Verifier(String code) {
        Preconditions.checkNotNull(code, "Must provide a valid string as verifier");
        this.code = code;
        this.codeVerifier = "";
    }

    public Verifier(String code, String codeVerifier) {
        Preconditions.checkNotNull(code, "Must provide a valid string as verifier");
        Preconditions.checkNotNull(codeVerifier, "Must provide a valid string as codeVerifier");
        this.code = code;
        this.codeVerifier = codeVerifier;
    }
    
    public Verifier(String code, PkceKey pkceKey) {
        Preconditions.checkNotNull(code, "Must provide a valid string as verifier");
        Preconditions.checkNotNull(pkceKey, "Must provide a valid string as pkceKey");
        this.code = code;
        this.pkceKey = pkceKey;
    }

    public String getCode() {
        return code;
    }

    public String getCodeVerifier() {
        return codeVerifier;
    }

    public PkceKey getPkceKey() {
        return pkceKey;
    }

}
