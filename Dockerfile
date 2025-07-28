FROM openjdk:17-jdk-slim
WORKDIR /app
# plain이 아닌 JAR 파일만 복사
COPY build/libs/*.jar ./
RUN mv *.jar app.jar 2>/dev/null || find . -name "*.jar" ! -name "*plain*" -exec mv {} app.jar \;
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]