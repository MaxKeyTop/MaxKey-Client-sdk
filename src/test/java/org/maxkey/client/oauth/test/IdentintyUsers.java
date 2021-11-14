package org.maxkey.client.oauth.test;

import java.io.IOException;

import org.maxkey.client.entity.idm.Organizations;
import org.maxkey.client.entity.idm.OrgsRestClient;

public class IdentintyUsers {

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        OrgsRestClient orgClient = new OrgsRestClient();
        //System.out.println(orgClient.getOrgById("1"));
        
        Organizations org=new Organizations();
        org.setId("1000");
        org.setName("REST ORG22");
        org.setParentId("1");
        
        orgClient.create(org);
        
        orgClient.update(org);
        
        orgClient.delete(org);
    }

}
