FROM maven

RUN mkdir -p /app

COPY ./ /app/

ENTRYPOINT cd /app && mvn spring-boot:run