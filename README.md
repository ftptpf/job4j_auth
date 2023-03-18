# job4j_auth

## Проект "RestFul API Person"

Проект аутентификации, авторизации, создания, поиска и удаления пользователей.
Реализованы различные виды валидации передаваемых данных и обработка исключений.

### Стек технологии

В проекте используется:
- Java 17
- Spring 2.7 (boot, data, security, web, validation)
- PostgreSQL 14
- Liquibase 4.20
- Maven 3.8
- PostMan

### Требования к окружению

Для запуска проекта вам необходимо установить:
- Java 17
- PostgreSQL 14
- Maven 3.8
- PostMan

### Запуск проекта

Запустите SQL shell(psql) из пакета PostgreSQL.
Создайте базу данных проекта "fullstack_auth_db" выполнив команду
```create database fullstack_auth_db;```

Запустите ваш терминал с командной строкой (например для Windows это программа cmd)
перейдите в папку с вашим проектом и запустите проект командой
```mvn spring-boot:run```

После запуска, для начала работы с "RestFul API Person" воспользуйтесь любым HTTP-клиентом, например PostMan.

### Взаимодействие с приложением

#### Создание
```POST http://localhost:8080/person/```
Для начала работы необходимо зарегистрировать пользователя.
![Alt-текст](https://github.com/ftptpf/job4j_auth/blob/master/img/1.JPG "Создание пользователя")
Пароль пользователя хранится в базе в зашифрованном виде.

#### Аутентификация
```POST http://localhost:8080/login```
Чтобы выполнять запросы и изменение данных пользователей, необходимо пройти аутентификацию.
![Alt-текст](https://github.com/ftptpf/job4j_auth/blob/master/img/2.JPG "Логин пользователя")
При положительном прохождении аутентификации формируется jwt-токен, который мы будем использовать в других запросах. 

#### Обновление данных
```PUT http://localhost:8080/person/```
Мы можем обновить данные пользователя
![Alt-текст](https://github.com/ftptpf/job4j_auth/blob/master/img/3.JPG "Обновление имени и пароля пользователя")

#### Обновление пароля
```PATCH http://localhost:8080/person/```
Или только обновить его пароль
![Alt-текст](https://github.com/ftptpf/job4j_auth/blob/master/img/4.JPG "Обновление пароля пользователя")

#### Поиск пользователя по id
```GET http://localhost:8080/person/id```
Выполняем поиск конкретного пользователя
![Alt-текст](https://github.com/ftptpf/job4j_auth/blob/master/img/5.JPG "Поиск пользователя")

#### Поиск всех пользователей
```GET http://localhost:8080/person/```
Находим всех пользователей системы
![Alt-текст](https://github.com/ftptpf/job4j_auth/blob/master/img/6.JPG "Все пользователи")

#### Удаление пользователя по id
```DELETE http://localhost:8080/person/id```
Удаляем конкретного пользователя
![Alt-текст](https://github.com/ftptpf/job4j_auth/blob/master/img/7.JPG "Удаляем пользователя")
