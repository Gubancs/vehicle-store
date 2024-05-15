#!/bin/zsh

gradlew clean

gradlew bootJar

docker build --platform linux/amd64 . -t vehiclestore:latest

if [ -d "build/release" ]; then
  rm -rf build/release/*
else
  mkdir -p build/release/docker
fi

docker save vehiclestore:latest > build/release/vehiclestore.tar

cp docker/.env build/release/docker/
cp docker/haproxy.conf build/release/docker/
cp docker/init.sql build/release/docker/
cp docker-compose.yaml build/release/

zip -jr build/release/vehiclestore.zip build/release/

echo "Release is ready at build/release/vehiclestore.zip"