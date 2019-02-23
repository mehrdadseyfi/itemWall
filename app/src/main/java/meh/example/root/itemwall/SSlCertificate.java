package meh.example.root.itemwall;

import com.loopj.android.http.MySSLSocketFactory;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

/**
 * Created by root on 9/25/2018.
 */

public class SSlCertificate {
    public static MySSLSocketFactory Ssl(){

        KeyStore trustStore = null;
        try {
            trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }
// We load the KeyStore
        try {
            trustStore.load(null, null);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        }
// We initialize a new SSLSocketFacrory
        MySSLSocketFactory socketFactory = null;
        try {
            socketFactory = new MySSLSocketFactory(trustStore);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (UnrecoverableKeyException e) {
            e.printStackTrace();
        }
// We set that all host names are allowed in the socket factory
        socketFactory.setHostnameVerifier(MySSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        return socketFactory;
        //</editor-fold>
    }
}
