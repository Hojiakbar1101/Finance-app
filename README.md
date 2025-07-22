project:
  title: "💰 Finance App – Финансовое приложение"
  description: >
    Приложение **Finance App** — это простая, но мощная система управления пользовательскими финансами.
    Реализовано с использованием **Spring Boot**, **PostgreSQL**, с возможностью масштабирования и контейнеризации с помощью **Docker**.

overview:
  goals:
    - Ведение учета пользователей и их текущего баланса
    - Отслеживание транзакций пользователей (пополнение / списание)
    - Использование PostgreSQL в качестве хранилища данных
    - Поддержка контейнеризации с Docker
  audience: Финансовые стартапы, банковские платформы, учетные системы

stack:
  language: Java 17+
  framework: Spring Boot 3+
  database: PostgreSQL
  orm: Hibernate (JPA)
  build_tool: Maven
  containerization: Docker, Docker Compose

structure:
  tree: |
    finance_app/
    ├── src/
    │   └── main/
    │       ├── java/com/example/finance_app/
    │       │   ├── controller/
    │       │   ├── model/
    │       │   ├── repository/
    │       │   ├── service/
    │       │   └── FinanceAppApplication.java
    │       └── resources/
    │           ├── application.properties
    │           └── data.sql
    ├── Dockerfile
    ├── docker-compose.yml
    └── README.md

configuration:
  application.properties:
    server.port: 9090
    spring.datasource.url: jdbc:postgresql://localhost:5432/finance
    spring.datasource.username: postgres
    spring.datasource.password: 1101samon
    spring.jpa.hibernate.ddl-auto: update
    spring.jpa.show-sql: true
    spring.jpa.properties.hibernate.format_sql: true

sql:
  schema: |
    CREATE TABLE IF NOT EXISTS users (
        id SERIAL PRIMARY KEY,
        name VARCHAR(255),
        balance DOUBLE PRECISION
    );

    CREATE TABLE IF NOT EXISTS transaction (
        id SERIAL PRIMARY KEY,
        user_id INTEGER REFERENCES users(id),
        amount DOUBLE PRECISION,
        timestamp TIMESTAMP
    );
  mock_data: |
    INSERT INTO users(name, balance) VALUES ('Ali', 1000), ('Vali', 500);

    INSERT INTO transaction(user_id, amount, timestamp)
    VALUES 
      (1, 200.0, NOW()),
      (2, -100.0, NOW());

docker:
  dockerfile: |
    FROM openjdk:17-jdk-alpine
    VOLUME /tmp
    COPY target/finance_app.jar app.jar
    ENTRYPOINT ["java","-jar","/app.jar"]
  compose: |
    version: '3.8'

    services:
      postgres:
        image: postgres:15
        environment:
          POSTGRES_DB: finance
          POSTGRES_USER: postgres
          POSTGRES_PASSWORD: 1101samon
        ports:
          - "5432:5432"
        volumes:
          - postgres_data:/var/lib/postgresql/data

      finance_app:
        build: .
        ports:
          - "9090:9090"
        depends_on:
          - postgres
        environment:
          SPRING_DATASOURCE_URL: jdbc:postgresql://postgres:5432/finance
          SPRING_DATASOURCE_USERNAME: postgres
          SPRING_DATASOURCE_PASSWORD: 1101samon

    volumes:
      postgres_data:

build_and_run:
  prerequisites:
    - Java 17+
    - Maven
    - Docker & Docker Compose
  manual:
    - mvn clean install
    - java -jar target/finance_app.jar
  docker:
    - docker-compose up --build
  result:
    api_url: http://localhost:9090

api_routes:
  public:
    - GET /users — список всех пользователей
    - POST /users — создать нового пользователя
    - GET /transactions — список всех транзакций
    - POST /transactions — добавить новую транзакцию
  note: >
    Реализация маршрутов зависит от контроллеров и может быть расширена согласно требованиям.

author:
  name: Saidrasulov Hojiakbar
  role: Java Backend-разработчик
  email: saidrasulovhojiakbar7@gmail.com
  phone: +998-88-521-30-08
  github: https://github.com/Hojiakbar1101
  location: Tashkent, Uzbekistan

license:
  type: MIT
  notice: >
    Вы можете свободно использовать, копировать, изменять и распространять данный проект.
    Пожалуйста, указывайте авторство при использовании в публичных или коммерческих целях.

credits:
  thanks:
    - Spring Boot сообществу
    - PostgreSQL команде
    - JetBrains (IntelliJ IDEA)
    - Docker Community
