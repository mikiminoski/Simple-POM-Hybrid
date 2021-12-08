package SupportVariables;

import junitx.util.PropertyManager;

public class URLs {
    /**
     * Over here we provide the variable for environment that is declared in the local.properties or server.properties file
     * Also we declare the static Strings for the urls that are constant.
     */
    private static final String environment = PropertyManager.getProperty("ENVIRONMENT");

    private static final String https = "https://";
    private static final String theapp = ".theapp.com/";


    public static final String BASE_URL = https + environment + theapp;
    public static final String BASE_LOGIN_URL = https + environment + theapp + "login";

}
