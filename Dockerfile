FROM openjdk:17
EXPOSE 8282
ADD build/libs/Employeemanagment-client-0.0.1-SNAPSHOT.jar Employeemanagment-client-0.0.1-SNAPSHOT
ENTRYPOINT [ "java","-jar","/Employeemanagment-client-0.0.1-SNAPSHOT" ]