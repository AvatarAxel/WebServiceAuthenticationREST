/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package security;

import java.util.ResourceBundle;
import java.util.StringTokenizer;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author michi
 */
public class BasicAuthorization {

    public static boolean authenticate(String credentialsBase64) {
        if (credentialsBase64 == null) {
            return false;
        }
        try {
            String credentials = null;
            byte[] bytes = DatatypeConverter.parseBase64Binary(credentialsBase64);
            credentials = new String(bytes, "UTF-8");
            final StringTokenizer tokenizer = new StringTokenizer(credentials, ":");
            final String username = tokenizer.nextToken();
            final String password = tokenizer.nextToken();
            ResourceBundle bundle = ResourceBundle.getBundle("security.frontend_credentials");
            String user = bundle.getString("user");
            String pass = bundle.getString("pass");
            return user.equals(username) && pass.equals(password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
