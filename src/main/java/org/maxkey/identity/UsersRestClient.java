package org.maxkey.identity;

import java.io.IOException;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.maxkey.client.http.HttpVerb;
import org.maxkey.client.http.rest.HttpHttpClient;
import org.maxkey.client.utils.JsonUtils;

public class UsersRestClient  extends RestBase{

   final Logger _logger = LogManager.getLogger(UsersRestClient.class);
    
    public UsersRestClient(){}
    
    public UserInfo getById(String id) throws IOException {
        UserInfo userInfo = null;
        HttpHttpClient client = new HttpHttpClient();
        client.addBasicAuthorization(clientId, password);
        CloseableHttpResponse response= client.execute(baseUrl+"/"+id, null);
        if(response.getEntity()!=null) {
            _logger.debug(response.getEntity().toString());
            _logger.debug(response.getEntity().getContent().toString());
            String content = EntityUtils.toString(response.getEntity());
            _logger.debug(content);
            userInfo= JsonUtils.json2Object(content, UserInfo.class);
        }
        
        response.close();
        client.close();
        
        return userInfo;
    }
    
    public UserInfo create(UserInfo userInfo) throws IOException {
        HttpHttpClient client = new HttpHttpClient(HttpVerb.POST);
        client.addBasicAuthorization(clientId, password);
        CloseableHttpResponse response= client.execute(baseUrl+"/", userInfo);
        if(response.getEntity()!=null) {
            _logger.debug(response.getEntity().toString());
            _logger.debug(response.getEntity().getContent().toString());
            String content = EntityUtils.toString(response.getEntity());
            _logger.debug(content);
            userInfo= JsonUtils.json2Object(content, UserInfo.class);
        }
        
        response.close();
        client.close();
        
        return userInfo;
    }
    
    public UserInfo update(UserInfo userInfo) throws IOException {
        HttpHttpClient client = new HttpHttpClient(HttpVerb.PUT);
        client.addBasicAuthorization(clientId, password);
        CloseableHttpResponse response= client.execute(baseUrl+"/", userInfo);
        if(response.getEntity()!=null) {
            _logger.debug(response.getEntity().toString());
            _logger.debug(response.getEntity().getContent().toString());
            String content = EntityUtils.toString(response.getEntity());
            _logger.debug(content);
            userInfo= JsonUtils.json2Object(content, UserInfo.class);
        }
        
        response.close();
        client.close();
        
        return userInfo;
    }
    
    public boolean delete(UserInfo userInfo) throws IOException {
        HttpHttpClient client = new HttpHttpClient(HttpVerb.DELETE);
        boolean deleteResult=false;
        client.addBasicAuthorization(clientId, password);
        CloseableHttpResponse response= client.execute(baseUrl+"/"+userInfo.getId(),null);
        if(response.getStatusLine().getStatusCode()==200) {
            deleteResult = true;
        }
        
        response.close();
        client.close();
        
        return deleteResult;
    }
}
