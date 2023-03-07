FROM ubuntu:latest
LABEL maintainer="Guilherme Querentino <squerentino@gmail.com>"
 
# Instala o Java 17
RUN apt-get update && \
    apt-get upgrade -y && \
    apt-get install -y openjdk-17-jdk
 
# Instala o MySQL
RUN apt-get install -y mysql-server
 
# Cria o diretório para o projeto
RUN mkdir /journaling
 
# Define o diretório de trabalho
WORKDIR /journaling
 
# Copia o arquivo .jar do projeto para o contêiner
COPY journaling.jar /journaling
 
# Define a porta do MySQL
EXPOSE 3306
 
# Define a porta do projeto
EXPOSE 8080
 
# Inicia o MySQL e o projeto
CMD service mysql start && \
    java -jar journaling.jar