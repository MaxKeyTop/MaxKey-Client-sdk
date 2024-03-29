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
 

package org.maxkey.client.entity.idm.scim;

import java.io.Serializable;

public class OrganizationPhoneNumber extends MultiValuedAttribute implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 3201987266085144715L;

    public static class UserPhoneNumberType {
        public static final String WORK = "work";
        public static final String HOME = "home";
        public static final String MOBILE = "mobile";
        public static final String FAX = "fax";
        public static final String PAGER = "pager";
        public static final String OTHER = "other";

    }
}
