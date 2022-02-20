package org.maxkey.client.oauth.services;

import java.security.SecureRandom;

import org.maxkey.client.oauth.model.PkceKey;
import org.maxkey.crypto.Base64Utils;
import org.maxkey.crypto.DigestUtils;
import org.maxkey.util.StringUtils;


public class PkceService {
    private static final SecureRandom RANDOM = new SecureRandom();
    private static final PkceService INSTANCE = new PkceService();
    /**
     * number of octets to randomly generate.
     */
    private final int numberOFOctets;

    public PkceService(int numberOFOctets) {
        this.numberOFOctets = numberOFOctets;
    }

    /**
     * will create random generator with recommended params (32 octets) https://tools.ietf.org/html/rfc7636#section-4.1
     */
    public PkceService() {
        this(32);
    }
    
    public static PkceService getInstance() {
        return INSTANCE;
    }
    
    public PkceKey generatePKCE() {
        final byte[] bytes = new byte[numberOFOctets];
        RANDOM.nextBytes(bytes);
        return generatePKCE(bytes);
    }
    
    public PkceKey generatePKCE(byte[] randomBytes) {
        final String codeVerifier = Base64Utils.base64UrlEncode(randomBytes);

        final PkceKey pkceKey = new PkceKey();
        pkceKey.setCodeVerifier(codeVerifier);
        pkceKey.setCodeChallenge(DigestUtils.digestBase64Url(codeVerifier, DigestUtils.Algorithm.SHA256));
        if(StringUtils.isBlank(pkceKey.getCodeChallenge())) {
            pkceKey.setCodeChallengeMethod(PkceKey.PKCECodeChallengeMethod.PLAIN.name());
            pkceKey.setCodeChallenge(codeVerifier);
        }
        return pkceKey;
    }
    
}
