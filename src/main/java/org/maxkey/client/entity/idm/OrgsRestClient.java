package org.maxkey.client.entity.idm;

import java.io.IOException;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.maxkey.client.http.HttpVerb;
import org.maxkey.client.http.rest.HttpHttpClient;
import org.maxkey.util.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OrgsRestClient extends RestBase{
	final static Logger _logger = LoggerFactory.getLogger(OrgsRestClient.class);
    
    public OrgsRestClient(){}
    
    public Organizations getById(String id) throws IOException {
        Organizations org = null;
        HttpHttpClient client = new HttpHttpClient();
        client.addBasicAuthorization(clientId, clientSecret);
        CloseableHttpResponse response= client.execute(baseUrl+"/"+id, null);
        if(response.getEntity()!=null) {
            _logger.debug(response.getEntity().toString());
            _logger.debug(response.getEntity().getContent().toString());
            String content = EntityUtils.toString(response.getEntity());
            _logger.debug(content);
            org= JsonUtils.json2Object(content, Organizations.class);
        }
        
        response.close();
        client.close();
        
        return org;
    }
    
    public Organizations create(Organizations org) throws IOException {
        HttpHttpClient client = new HttpHttpClient(HttpVerb.POST);
        client.addBasicAuthorization(clientId, clientSecret);
        CloseableHttpResponse response= client.execute(baseUrl+"/", org);
        if(response.getEntity()!=null) {
            _logger.debug(response.getEntity().toString());
            _logger.debug(response.getEntity().getContent().toString());
            String content = EntityUtils.toString(response.getEntity());
            _logger.debug(content);
            org= JsonUtils.json2Object(content, Organizations.class);
        }
        
        response.close();
        client.close();
        
        return org;
    }
    
    public Organizations update(Organizations org) throws IOException {
        HttpHttpClient client = new HttpHttpClient(HttpVerb.PUT);
        client.addBasicAuthorization(clientId, clientSecret);
        CloseableHttpResponse response= client.execute(baseUrl+"/", org);
        if(response.getEntity()!=null) {
            _logger.debug(response.getEntity().toString());
            _logger.debug(response.getEntity().getContent().toString());
            String content = EntityUtils.toString(response.getEntity());
            _logger.debug(content);
            org= JsonUtils.json2Object(content, Organizations.class);
        }
        
        response.close();
        client.close();
        
        return org;
    }
    
    public boolean delete(Organizations org) throws IOException {
        HttpHttpClient client = new HttpHttpClient(HttpVerb.DELETE);
        boolean deleteResult=false;
        client.addBasicAuthorization(clientId, clientSecret);
        CloseableHttpResponse response= client.execute(baseUrl+"/"+org.getId(),null);
        if(response.getStatusLine().getStatusCode()==200) {
            deleteResult = true;
        }
        
        response.close();
        client.close();
        
        return deleteResult;
    }
    
}
