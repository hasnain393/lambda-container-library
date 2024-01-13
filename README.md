# spring-boot-crud-example

```
<dependency>
      <groupId>com.amazonaws</groupId>
      <artifactId>aws-lambda-java-runtime-interface-client</artifactId>
      <version>2.3.2</version>
    </dependency>
```

```

			<!-- lambda conatiner add-->
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-dependency-plugin</artifactId>
				<version>3.1.2</version>
				<executions>
					<execution>
						<id>copy-dependencies</id>
						<phase>package</phase>
						<goals>
							<goal>copy-dependencies</goal>
						</goals>
					</execution>
				</executions>
			</plugin>
```

```
FROM public.ecr.aws/lambda/java:11
  
# Copy function code and runtime dependencies from Maven layout
COPY target/classes ${LAMBDA_TASK_ROOT}
COPY target/dependency/* ${LAMBDA_TASK_ROOT}/lib/
    
# Set the CMD to your handler (could also be done as a parameter override outside of the Dockerfile)
CMD [ "com.ood.library.LambdaHandler::handleRequest" ]
```

mvn compile dependency:copy-dependencies -DincludeScope=runtime

docker build --platform linux/amd64 -t docker-image:test .

https://docs.aws.amazon.com/lambda/latest/dg/java-image.html#java-image-instructions

![Screenshot (761)](https://github.com/hasnain393/lambda-container-library/assets/56108097/ce96e78b-0708-415a-aba0-d21a0ccb7c56)

![Screenshot (762)](https://github.com/hasnain393/lambda-container-library/assets/56108097/196b4fa1-ca64-4fcb-9a14-78f508d3211c)

![Screenshot (763)](https://github.com/hasnain393/lambda-container-library/assets/56108097/fbb5ba7b-9d7a-4cf5-ab9d-831afcb713d6)

![Screenshot (764)](https://github.com/hasnain393/lambda-container-library/assets/56108097/cb631b50-9b28-4062-97f5-59673a1e75d1)

![Screenshot (765)](https://github.com/hasnain393/lambda-container-library/assets/56108097/8419fac3-c022-46c4-8990-4f70591489cc)

![Screenshot (766)](https://github.com/hasnain393/lambda-container-library/assets/56108097/54b0f1e3-dcaa-40d7-8a01-2dc10a70cd1a)






