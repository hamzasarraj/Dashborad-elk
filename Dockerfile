FROM openjdk:17-oracle
EXPOSE 8089
ADD target/dashboard-0.0.1-release.jar dashboard-0.0.1-release.jar
ENTRYPOINT ["java","-jar","/dashboard-0.0.1-release.jar"]