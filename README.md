# 💰 Finance App – Финансовое приложение

![Java](https://img.shields.io/badge/Java-17+-orange?logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3%2B-brightgreen?logo=spring)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-blue?logo=postgresql)
![Docker](https://img.shields.io/badge/Docker-ready-2496ED?logo=docker)
![License](https://img.shields.io/badge/license-MIT-lightgrey)

---

## 📌 Описание проекта

**Finance App** — это легкое, но надежное REST-приложение для управления финансами пользователей. Подходит для стартапов, банковских платформ или любых систем, где нужно вести учёт баланса и транзакций пользователей.

**Ключевые функции:**
- Управление пользователями и их балансом.
- Проведение транзакций (пополнение / списание).
- Сохранение данных в PostgreSQL.
- Лёгкое развертывание с Docker.

---

## 🚀 Используемые технологии

| Категория         | Технология             |
|------------------|------------------------|
| Язык             | Java 17+               |
| Фреймворк        | Spring Boot 3+         |
| ORM              | Hibernate (JPA)        |
| База данных      | PostgreSQL             |
| Сборщик          | Maven                  |
| Контейнеризация  | Docker, Docker Compose |

---

## 🗂️ Структура проекта

finance_app/
├── src/
│ └── main/
│ ├── java/com/example/finance_app/
│ │ ├── controller/
│ │ ├── model/
│ │ ├── repository/
│ │ ├── service/
│ │ └── FinanceAppApplication.java
│ └── resources/
│ ├── application.properties
│ └── data.sql
├── Dockerfile
├── docker-compose.yml
└── README.md

yaml
Copy
Edit

---

## ⚙️ Конфигурация приложения

`application.properties`

```properties
server.port=9090

spring.datasource.url=jdbc:postgresql://localhost:5432/finance
spring.datasource.username=postgres
spring.datasource.password=1101samon

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
🧾 SQL Скрипты
Структура таблиц:

sql
Copy
Edit
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
Тестовые данные:

sql
Copy
Edit
INSERT INTO users(name, balance) VALUES ('Ali', 1000), ('Vali', 500);

INSERT INTO transaction(user_id, amount, timestamp)
VALUES 
  (1, 200.0, NOW()),
  (2, -100.0, NOW());
🐳 Docker-интеграция
Dockerfile

dockerfile
Copy
Edit
FROM openjdk:17-jdk-alpine
VOLUME /tmp
COPY target/finance_app.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
docker-compose.yml

yaml
Copy
Edit
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
🛠️ Сборка и запуск
🔧 Требования
Java 17+

Maven

Docker & Docker Compose

🚀 Сборка вручную
bash
Copy
Edit
mvn clean install
java -jar target/finance_app.jar
🐳 Запуск через Docker
bash
Copy
Edit
docker-compose up --build
После запуска, API будет доступен по адресу:
📍 http://localhost:9090

📬 REST API Роуты
Метод	Endpoint	Описание
GET	/users	Получить всех пользователей
POST	/users	Создать нового пользователя
GET	/transactions	Получить все транзакции
POST	/transactions	Добавить новую транзакцию
GET	/transactions/byUser/{userId}	Поиск по пользователю
GET	/transactions/byDate?start=...&end=...	Поиск по дате

🔐 Безопасность
❗ В текущей версии аутентификация через JWT не реализована, но структура проекта легко расширяется для добавления Spring Security.

👨‍💻 Автор проекта
Saidrasulov Hojiakbar
Java Backend-разработчик
📧 Email: saidrasulovhojiakbar7@gmail.com
📱 Телефон: +998-88-521-30-08
🌍 Город: Ташкент, Узбекистан
🔗 GitHub: github.com/Hojiakbar1101
