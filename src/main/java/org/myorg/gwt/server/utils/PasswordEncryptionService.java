package org.myorg.gwt.server.utils;


import org.mindrot.jbcrypt.BCrypt;


public class PasswordEncryptionService  {

    public static boolean authenticate(String attemptedPassword, String hasedPassword ) {
        return BCrypt.checkpw(attemptedPassword, hasedPassword);
    }

    public static String getEncryptedPassword(String password) {

        return BCrypt.hashpw(password, BCrypt.gensalt());
    }


}