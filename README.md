# Spike_SpringDockerPostgres

## Getting Started

Create a Spring project with Spring Initializr (https://start.spring.io/)
* Project: Maven Project
* Language: Java
* Dependencies: Spring Web, Spring Data JPA, PostgreSQL Driver


## Write Java classes ...

## Docker

* Modify application.properties

spring.datasource.url=jdbc:postgresql://dbpostgresql:5432/testdb
spring.datasource.driver-class-name=org.postgresql.Driver
spring.datasource.username=admin
spring.datasource.password=admin
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.temp.use_jdbc_metadata_defaults = false
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQL9Dialect
spring.jpa.hibernate.ddl-auto = create

* Add the docker plugin in pom.xml

    <plugin>
				<groupId>io.fabric8</groupId>
				<artifactId>docker-maven-plugin</artifactId>
				<version>0.20.1</version>
				<configuration>
					<images>
						<image>
							<name>app</name>
							<build>
								<dockerFileDir>${project.basedir}/src/main/docker</dockerFileDir>
								<assembly>
									<mode>dir</mode>
									<targetDir>/app</targetDir>
									<descriptor>${project.basedir}/src/main/docker/assembly.xml</descriptor>
								</assembly>
							</build>
						</image>
					</images>
				</configuration>
				<executions>
					<execution>
						<id>build</id>
						<phase>install</phase>
						<goals>
							<goal>build</goal>
						</goals>
					</execution>
				</executions>
			</plugin>
      
 * In the root directory add the "Dockerfile"
  FROM openjdk:13-alpine
  RUN addgroup -S spring && adduser -S spring -G spring
  #VOLUME /tmp
  EXPOSE 8080
  ARG DEPENDENCY=target
  ADD ${DEPENDENCY}/*.jar appbootrest.jar
  ENTRYPOINT ["java","-jar","/appbootrest.jar"]

* In the root directory add the "docker-compose.yml" (match the db name with the application.properties)
  version: '3'

  services:
    dbpostgresql:
      image: "postgres"
      ports:
        - "5432"
      environment:
        POSTGRES_DB: testdb
        POSTGRES_USER: admin
        POSTGRES_PASSWORD: admin
        restart: unless-stopped
    app:
      build: .
      image: appbootrest
      container_name: appbootrest
      ports:
        - "8080:8080"
      depends_on:
        - dbpostgresql


* In the root directory open a Windows Powershell and run 

```sh
 ./mvnw - DskipTests=true clean package
```

* Build the project with Intellij

* In the intellij shell
```sh
 docker-compose build
 docker-compose up
```

* Test the rest api with Postman

GET http://localhost:8080/api/v1/customers

POST http://localhost:8080/api/v1/customers/create
RAW - JSON (NB)
{ 
    "id" : "002",
    "name" : "marco"
}



