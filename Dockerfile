FROM eclipse-temurin:21-jdk-alpine

ENV JAVA_OPTS="-Xmx256m -Xms128m"

# Set the working directory in the container
WORKDIR /app

ADD docker/glowroot glowroot

# Copy the compiled Kotlin jar file into the container
COPY build/libs/*.jar app.jar

# Specify the entrypoint to run the application
#ENTRYPOINT exec java -Djava.library.path=/usr/lib/jni -javaagent:/app/glowroot/glowroot.jar $JAVA_OPTS -jar app.jar
ENTRYPOINT exec java $JAVA_OPTS -jar app.jar
