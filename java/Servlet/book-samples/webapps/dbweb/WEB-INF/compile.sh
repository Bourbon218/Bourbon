source=$1
javac -sourcepath src -cp $TOMCAT_CLASSPATH/servlet-api.jar src/$source -d classes/