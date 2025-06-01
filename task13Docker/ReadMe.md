**Задание:**

Описать Dockerfile или docker-compose конфиг, для запуска вашего приложения на порту 8080 из docker-конетйнера

**Как воспроизвести работу приложения:**

После клонирования проекта, перейти в директорию ./task13Docker

В терминале запусть команду для билдинга докер контейнера

```bash
docker build ./ -t app:1.1
```
![](screenshots/1.jpg)
...
![](screenshots/2.jpg)

Запустить докер котейнер

```bash
docker run -p 8080:8080 app:1.1
```

![](screenshots/3.jpg)
...
![](screenshots/4.jpg)

Сделать тестовый http request для создания пользователя

![](screenshots/5.jpg)

Получить http responce

![](screenshots/6.jpg)
