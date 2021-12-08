package SupportVariables;

import junitx.util.PropertyManager;

public class Credentials {
    /**
     * This class is for the credentials.
     * We store the credentials as a static final Strings
     * The string admin_pass is for the password that changes depending on the environment and the password is changed
     * in the local.properties or server.properties
     */
    String admin_pass = PropertyManager.getProperty("ADMIN_PASS");

    public static final String ACCOUNT_MAIL = "themail@mail.com";
    public static final String ACCOUNT_PASS = "password";
    public static final String ADMIN_MAIL = "admin@mail.com";
    public final String ADMIN_PASS = admin_pass;
}
