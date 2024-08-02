FROM postgres:latest

ENV POSTGRES_DB=operations
ENV POSTGRES_USER=postgres
ENV POSTGRES_PASSWORD=admin

COPY BaseDatos.sql /docker-entrypoint-initdb.d/
