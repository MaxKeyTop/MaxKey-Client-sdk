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
 

package org.maxkey.identity.scim.resources;

import java.io.Serializable;

public class OrganizationEmail extends MultiValuedAttribute implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -41327146592552688L;
    
    public static class UserEmailType {
        public static final String WORK = "work";
        public static final String HOME = "home";
        public static final String OTHER = "other";

    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isPrimary() {
        return primary;
    }

    public void setPrimary(boolean primary) {
        this.primary = primary;
    }

    public OrganizationEmail() {
    }

    public OrganizationEmail(String value, String type, boolean primary) {
        super();
        this.value = value;
        this.type = type;
        this.primary = primary;
    }
    
    
}
