FROM amazoncorretto:17-alpine-jdk
COPY target/admin.jar admin.jar
ENTRYPOINT ["java","-jar","/admin.jar"]