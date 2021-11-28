FROM tomcat:9.0
LABEL maintainer="miniprojet-devrep"

#Configuration
RUN ln -s /usr/local/tomcat/webapps.dist/manager webapps/manager
RUN ln -s /usr/local/tomcat/webapps.dist/host-manager webapps/host-manager
RUN ln -s /usr/local/tomcat/webapps.dist/ROOT webapps/ROOT
COPY context.xml /usr/local/tomcat/webapps.dist/manager/META-INF/context.xml
COPY tomcat-users.xml /usr/local/tomcat/conf/tomcat-users.xml

#Deploy sample applications
COPY devrepBanque.war $CATALINA_HOME/webapps/devrepBanque.war


EXPOSE 8080
CMD ["catalina.sh", "run"]

#docker build -t devrep/tomcat-sample .
# docker run -it --name mysampleapp -p 8080:8080 devrep/tomcat-sample