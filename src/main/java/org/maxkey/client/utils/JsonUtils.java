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
 

package org.maxkey.client.utils;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

public class JsonUtils {

    /**
     * Transform json string to java bean object.
     * 
     * @param json String
     * @param bean Object 
     * @return Object 
     */
    public static Object json2Object(String json, Object bean) {
        try {
            bean = (new ObjectMapper()).readValue(json, bean.getClass());
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } 
        return bean;
    }

    /**
     * Transform json string to java bean object.
     * 
     * @param json String
     * @param cls Class
     * @return Object
     */
    public static <T> T json2Object(String json, Class<T> cls) {
        T bean = null;
        try {
            bean = (new ObjectMapper()).readValue(json, cls);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } 
        return bean;
    }

    /**
     * Transform java bean object to json string.
     * 
     * @param bean Object
     * @return string
     */
    public static String object2Json(Object bean) {
        String json = "";
        try {
            json = (new ObjectMapper()).writeValueAsString(bean);
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } 
        return json;
    }
}
