# Usar una imagen base de Java para construir la aplicación
FROM openjdk:17-jdk-alpine

# Establecer el directorio de trabajo
WORKDIR /app

# Copiar el archivo JAR a la imagen de Docker
COPY build/libs/matriz-0.0.1-SNAPSHOT.jar /app/matriz-0.0.1-SNAPSHOT.jar

# Exponer el puerto que utiliza tu aplicación Spring Boot
EXPOSE 9098

# Ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "matriz-0.0.1-SNAPSHOT.jar"]
