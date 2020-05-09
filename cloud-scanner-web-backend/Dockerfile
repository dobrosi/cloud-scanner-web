FROM adoptopenjdk/openjdk8

ADD cloud-scanner-web-application/target/*.jar /app.jar

EXPOSE 8080

CMD ["java",\ 
	"-jar",\
	"/app.jar"]