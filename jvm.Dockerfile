### Build stage
# Builder gradle
FROM gradle:jdk22-alpine AS builder

# Set the working directory inside the container
WORKDIR /tmp

# Copy the source code into the container
COPY . .

# Build
RUN gradle clean :book-catalog-app:bootJar -x test --no-daemon

# Extract the layers
RUN java -Djarmode=layertools -jar book-catalog-app/build/libs/book-catalog-app.jar extract

### Run stage
# Create a minimal production image
FROM azul/zulu-openjdk-alpine:22-jre-headless

# Set the working directory inside the container
WORKDIR /app

# Set the working directory inside the container
COPY --from=builder /tmp/dependencies/ ./
COPY --from=builder /tmp/snapshot-dependencies/ ./
COPY --from=builder /tmp/spring-boot-loader/ ./
COPY --from=builder /tmp/application/ ./

# Run the binary when the container starts
ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]
