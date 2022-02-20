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

package org.maxkey.client.oauth.services;

import org.maxkey.crypto.Base64Utils;

public class DatatypeConverterEncoder extends Base64Encoder {
    @Override
    public String encode(byte[] bytes) {
        return Base64Utils.encodeBase64(bytes);
    }

    @Override
    public String getType() {
        return "DatatypeConverter";
    }
}
