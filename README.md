#Simple POM template

Read me:

This project is using Java, Maven, Selenium Webdriver, TestNG for automated web testing

Environment setup:

1. Java version 11.0.10

2. Apache Maven version 3.8.1

3. Download ChromeDriver for Linux and copy the file to resources folder of the project.

In the project resources you have the drivers that are used for the browsers.

The dependencies from the project should be also downloaded before running the script.

There are .xml and .pom files that should be configured sometimes depends on what and where we would like to run the script.

Under the project package there are resources folder with "environment .properties" files that declares all the variables we use for running the script.    
Keep in mind that locally you should set your own variables depending on the operating system you use.  
There is local.properties file as an example for windows operating system.  
We can update and create more XML files like this if we have more environments for server run.



Use command line "mvn clean install -DPropertyManager.file=LOCATION_OF_THE_.properties_FILE" to run the script on Windows.

For Linux for use the same but change the slash for example: "mvn clean install -DPropertyManager.file=resources/server.properties"