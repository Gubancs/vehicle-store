
gradlew clean
gradlew bootJar
docker build . -t vehiclestore:latest
docker-compose down && docker-compose up --build --force-recreate	 -d