FROM mysql:8.0
LABEL maintainer="miniprojet-devrep"

#docker build -t devrep/tomcat-sample .
# docker run -it --name mysampleapp -p 8080:8080 devrep/tomcat-sample

EXPOSE 3306

# Environment variables
ENV MYSQL_ROOT_PASSWORD=root
ENV MYSQL_DATABASE=devdb
ENV MYSQL_USER=dbuser
ENV MYSQL_PASSWORD=dbpassword


# Create Database
RUN	mkdir /usr/sql
RUN	chmod 644 /usr/sql

ADD ["create.sql", "/docker-entrypoint-initdb.d/create.sql"]