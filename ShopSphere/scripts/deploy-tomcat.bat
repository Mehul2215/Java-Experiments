@echo off
echo ===================================================
echo   ShopSphere - Deploy to Apache Tomcat 11
echo ===================================================

set TOMCAT_HOME=D:\CODE\apache-tomcat-11.0.26
set WAR_FILE=%~dp0..\web\target\shopsphere.war

if not exist "%WAR_FILE%" (
    echo [ERROR] %WAR_FILE% not found.
    echo Running Maven build first...
    call mvn clean package -f "%~dp0..\pom.xml"
)

echo.
echo Deploying shopsphere.war to %TOMCAT_HOME%\webapps...
copy /Y "%WAR_FILE%" "%TOMCAT_HOME%\webapps\shopsphere.war"

echo.
echo Starting Tomcat Server...
start "Apache Tomcat 11 - ShopSphere" "%TOMCAT_HOME%\bin\startup.bat"

echo.
echo ===================================================
echo Application Deployed Successfully!
echo Open your browser at:
echo   Storefront:      http://localhost:8080/shopsphere/
echo   Admin Dashboard: http://localhost:8080/shopsphere/admin/dashboard
echo   Syllabus Demos:  http://localhost:8080/shopsphere/syllabus/jsp-elements.jsp
echo ===================================================
pause
