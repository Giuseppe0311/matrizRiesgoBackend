#!/bin/sh

# Esperar a que el contenedor 'ollama' esté corriendo
until docker exec ollama curl -f http://localhost:11434; do
  echo "Esperando a que el servicio Ollama esté listo..."
  sleep 5
done

# Ejecutar el comando para correr el modelo
docker exec ollama ollama run llama3.2
echo "Modelo llama3.2 ejecutado correctamente"
