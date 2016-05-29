GWT Maven User Auth
===================
[![Build Status](https://travis-ci.org/Zhigalov-E/gwt-user-auth.svg?branch=master)](https://travis-ci.org/Zhigalov-E/gwt-user-auth)
Overview
--------

Finally, a working example combining a maven build together with GWT 2.

This example is based on the GWT webapp created by the 
[Maven GWT Plugin archetype](http://mojo.codehaus.org/gwt-maven-plugin/user-guide/archetype.html).

Running via Maven in GWT 
------------------------

In order to run the example via Maven in GWT, you need to do:

1. Start the web application in Tomcat 7 via Maven
2. Start GWT Dev Mode via Maven
3. Run the application in your browser

To accomplish the first point, issue the following Maven command on a shell:

    mvn clean install
    mvn tomcat7:run-war-only

Your application is now deployed at http://127.0.0.1:8080/GWTUser/.

-- Option D: Using Maven --

If you have generated your project with the option '-maven', you have a 'pom.xml'
file ready to use. Assuming you have Maven installed in your system, 'mvn' is 
in your path, and you have access to maven repositories, you should be able to run:

mvn clean         # delete temporary stuff
mvn test          # run all the tests (gwt and junit)
mvn gwt:run       # run development mode
mvn package       # generate a .war package ready to deploy

For more information about other available goals, read Maven and gwt-maven-plugin 
documentation (http://maven.apache.org, http://mojo.codehaus.org/gwt-maven-plugin)  
