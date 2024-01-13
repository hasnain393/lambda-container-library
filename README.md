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
