package org.maxkey.client.oauth.model;

public class PkceKey {
    String codeVerifier;
    String codeChallenge;
    String CodeChallengeMethod = PKCECodeChallengeMethod.S256.name();
    
    public enum  PKCECodeChallengeMethod {
        PLAIN,S256  
    }

    public PkceKey() {
        super();
    }

    public String getCodeVerifier() {
        return codeVerifier;
    }

    public void setCodeVerifier(String codeVerifier) {
        this.codeVerifier = codeVerifier;
    }

    public String getCodeChallenge() {
        return codeChallenge;
    }

    public void setCodeChallenge(String codeChallenge) {
        this.codeChallenge = codeChallenge;
    }

    public String getCodeChallengeMethod() {
        return CodeChallengeMethod;
    }

    public void setCodeChallengeMethod(String codeChallengeMethod) {
        CodeChallengeMethod = codeChallengeMethod;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("PkceKey [codeVerifier=");
        builder.append(codeVerifier);
        builder.append(", codeChallenge=");
        builder.append(codeChallenge);
        builder.append(", CodeChallengeMethod=");
        builder.append(CodeChallengeMethod);
        builder.append("]");
        return builder.toString();
    }

}
