package org.maxkey.client.entity.idm;

public class RestBase {


    String baseUrl = "http://sso.maxkey.top:9521/maxkey-mgt/identity/api/org";
    String clientId = "maxkey_mgt";
    String clientSecret = "kpJpIe7hREN6sjt6";
    public String getBaseUrl() {
        return baseUrl;
    }
    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }
    public String getClientId() {
        return clientId;
    }
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
    public String getClientSecret() {
        return clientSecret;
    }
    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }
 
    
}
