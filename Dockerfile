FROM gradle:jdk8

RUN mkdir -p /app
COPY . /app
WORKDIR /app

EXPOSE 8080

CMD ["gradle", "bootRun"]
