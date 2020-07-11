package org.maxkey.client.http.rest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.maxkey.client.http.AuthorizationHeader;
import org.maxkey.client.http.HttpVerb;
import org.maxkey.client.http.ParameterList;
import org.maxkey.client.utils.HttpsTrusts.TrustAllX509Certificates;
import org.maxkey.client.utils.JsonUtils;


public class HttpHttpClient {
    final Logger _logger = LogManager.getLogger(HttpHttpClient.class);
    //public static final MediaType APPLICATION_JSON_UTF8 = MediaType.parse("application/json; charset=utf-8");
    
    CloseableHttpClient client = HttpClientBuilder.create().build();
    
    private HttpVerb method = HttpVerb.GET;
    
    HashMap<String,String> headers = new HashMap<String,String>();
    
    ParameterList parameterList = new ParameterList();
    
    public HttpHttpClient() {
        builderHttpClient();
    }
    
    public HttpHttpClient(HttpVerb method) {
        builderHttpClient();
        this.method = method;
    }
    
    public void builderHttpClient() {
        try {
            TrustManager[] trustManager = new javax.net.ssl.TrustManager[1];
            TrustManager  trustAllCertificates= new TrustAllX509Certificates();
            trustManager[0] = trustAllCertificates;
            
            SSLContext sslContext = javax.net.ssl.SSLContext.getInstance("SSL");
            sslContext.init(null, trustManager, null);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public HttpHttpClient(CloseableHttpClient client) {
        this.client = client;
    }
    
    public void setMethod(HttpVerb method) {
        this.method = method;
    }

    public void close() throws IOException {
        client.close();
    }

    public void addBasicAuthorization(String username,String password) {
        headers.put(AuthorizationHeader.AUTHORIZATION_HEADERNAME, AuthorizationHeader.createBasic(username, password));
    }
    
    public void addBearerAuthorization(String bearer) {
        headers.put(AuthorizationHeader.AUTHORIZATION_HEADERNAME, AuthorizationHeader.createBearer(bearer));
    }
    
    public void addParameter(String name,String value) {
        parameterList.add(name, value);
    }
    
    public void addHeader(String name,String value) {
        headers.put(name, value);
    }
    
    public CloseableHttpResponse execute(String url,Object bodyContents) throws IOException {
        url = parameterList.appendTo(url);
        
        _logger.debug("url " +url);
        RequestBuilder builder = null;
        
        if(this.method==HttpVerb.POST) {
            builder = RequestBuilder.post(); 
        }else if(this.method==HttpVerb.GET) {
            builder = RequestBuilder.get();
        }else if(this.method==HttpVerb.PUT) {
            builder = RequestBuilder.put();
        }else if(this.method==HttpVerb.DELETE) {
            builder = RequestBuilder.delete();
        }
        
        builder.setUri(url);
        
        if(bodyContents!=null) {
            StringEntity entity = new StringEntity(JsonUtils.object2Json(bodyContents), "UTF-8");
            builder.setEntity(entity);
        }
        
        for (Map.Entry<String, String> header : headers.entrySet()) {
            builder.addHeader(header.getKey(), header.getValue());
        }
        builder.addHeader("Content-Type", "application/json;charset=utf8");
        
        CloseableHttpResponse httpResponse = client.execute(builder.build());
        _logger.debug("响应状态为:" + httpResponse.getStatusLine());

        return httpResponse;
      }
}
