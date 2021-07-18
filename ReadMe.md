### Note: I know about the existence of "Lombok" and that it helps to reduce the amount of boilerplate code

## Requirements
* JDK 1.11
* docker-compose

## How to run
- Build project with gradle:
```
gradle bootJar
```
- Copy the following files:
```
build/**
Dockerfile
compose.yml
```
- Go to the directory with files and execute the command:
```
export UID && export GID && docker-compose -f compose.yml up -d
```
