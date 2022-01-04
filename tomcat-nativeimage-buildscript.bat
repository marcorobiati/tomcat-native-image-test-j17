@echo off
echo Only maven webapp projects from Netbeans saved in %USERPROFILE%\Documents\NetBeansProjects supported.
echo Project folder name HAS to match webbappname.
echo It will be assumed that exploded war inside target folder is 'named webappname-1.0-SNAPSHOT'
echo Change pom.xml in %TOMCAT_STUFFED% to appropriate java source/target version (11 or 17), then press enter to continue.
echo Make SURE you have built your webapp (clean and build).
echo "---------------------------------------------------------------------"
echo "---------------------------------------------------------------------"
echo "---------------------------------------------------------------------"
echo Insert webapp name
set /p webappname=
echo "---------------------------------------------------------------------"
cd %TOMCAT_STUFFED%
rmdir /s /q "%TOMCAT_STUFFED%/"
xcopy "..\stuffed-backup" "%TOMCAT_STUFFED%" /s /e
copy /y "..\native-image-commands-resources\conf\*.*" conf
echo ------------------------------
call mvn package
echo ------------------------------
xcopy "%USERPROFILE%\Documents\NetBeansProjects\%webappname%\target\%webappname%-1.0-SNAPSHOT" "%TOMCAT_STUFFED%\webapps\%webappname%\" /s /e
xcopy "%USERPROFILE%\Documents\NetBeansProjects\%webappname%\src\main\java" "%TOMCAT_STUFFED%\webapps\%webappname%\WEB-INF\classes\" /s /e
echo ------------------------------
call ant -Dwebapp.name=%webappname% -f webapp-jspc.ant.xml
echo ------------------------------
echo ------------------------------
echo ------------------------------
set /p= Dependencies for the webapp should now be added to the main pom.xml, following with building the complete fat JAR,then press enter to continue.
call mvn package
echo ------------------------------
start firefox "localhost:8080/%webappname%"
%JAVA_HOME%\bin\java -Dcatalina.base="%TOMCAT_STUFFED%" -Djava.util.logging.config.file="%TOMCAT_STUFFED%\conf\logging.properties" -jar target\tomcat-stuffed-1.0.jar --catalina -generateCode src\main\java
echo ------------------------------
call mvn package
echo ------------------------------
start firefox "localhost:8080/%webappname%"
%JAVA_HOME%\bin\java -agentlib:native-image-agent=config-output-dir=%TOMCAT_STUFFED%\target -Dorg.graalvm.nativeimage.imagecode=agent -Dcatalina.base="%TOMCAT_STUFFED%" -Djava.util.logging.config.file="%TOMCAT_STUFFED%\conf\logging.properties" -jar target\tomcat-stuffed-1.0.jar --catalina -useGeneratedCode
echo ------------------------------