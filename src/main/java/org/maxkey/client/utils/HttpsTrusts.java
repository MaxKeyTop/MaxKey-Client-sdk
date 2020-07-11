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

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.cert.X509Certificate;
import java.security.cert.CertificateException ;

public class HttpsTrusts {

    public  static SSLSocketFactory  trustAllHttpsCertificates() throws Exception {
		TrustManager[] trustManager = new javax.net.ssl.TrustManager[1];
		TrustManager  trustAllCertificates= new TrustAllX509Certificates();
		trustManager[0] = trustAllCertificates;
		
		SSLContext sslContext = javax.net.ssl.SSLContext.getInstance("SSL");
		sslContext.init(null, trustManager, null);
		HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
		
		return sslContext.getSocketFactory();
	}
    
    /*
     * https ssl auto trust
     */
	public static void beforeConnection() {
		try {
			trustAllHttpsCertificates();
			
            HostnameVerifier hostnameVerifier = new HostnameVerifier() {
                public boolean verify(String urlHostName, SSLSession session) {
                    System.out.println("Warning: URL Host: " + urlHostName + " vs. " + session.getPeerHost());
                    return true;
                }
            };
            
			HttpsURLConnection.setDefaultHostnameVerifier(hostnameVerifier);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static class TrustAllX509Certificates  implements TrustManager,X509TrustManager {
		public X509Certificate[] getAcceptedIssuers() {
			return null;
		}

		public boolean isServerTrusted(X509Certificate[] certs) {
			return true;
		}

		public boolean isClientTrusted(X509Certificate[] certs) {
			return true;
		}

		public void checkServerTrusted(X509Certificate[] certs, String authType)
				throws CertificateException {
			return;
		}

		public void checkClientTrusted(X509Certificate[] certs, String authType)
				throws CertificateException {
			return;
		}
	}

}
