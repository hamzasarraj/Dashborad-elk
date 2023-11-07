FROM openjdk:17-oracle
EXPOSE 8089
RUN curl -u admin:hiba  http://localhost:8081/repository/front-dashboard/com/vermeg/dashboard/0.0.1-release/dashboard-0.0.1-release.jar -L -o dashboard-0.0.1-release.jar
ENTRYPOINT ["java","-jar","/dashboard-0.0.1-release.jar"]