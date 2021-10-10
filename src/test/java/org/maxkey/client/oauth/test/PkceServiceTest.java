package org.maxkey.client.oauth.test;

import org.maxkey.client.oauth.model.PkceKey;
import org.maxkey.client.oauth.services.PkceService;

public class PkceServiceTest {

    public static void main(String[] args) {
        PkceKey pkceKey = PkceService.getInstance().generatePKCE();
        System.out.println(pkceKey);
    }

}
