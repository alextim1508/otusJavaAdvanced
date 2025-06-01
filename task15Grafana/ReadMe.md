**Задание:**

Покрыть Rest сервис метриками, построить дашборд к Grafana и продемонстрировать результаты дашборда, подавая нагрузку 
через JMeter

**Как воспроизвести работу приложения:**

После клонирования проекта, перейти в директорию ./task15Grafana, поднять docker контейнеры командой

```bash
docker compouse up
```

Внутри приложения работают эндпоинты по адресу localhost:8080/actuator/prometheus
![](screenshots/4.jpg)

Сервер метрик prometheus доступен по порту localhost:9090
![](screenshots/5.jpg)

После запуска всех контейнеров подать нагрузка на приложение через JMeter
![](screenshots/1.jpg)

![](screenshots/2.jpg)

Графана доступна на адресу localhost:3000, Дашбоард загружен из файла otus.json

![](screenshots/3.jpg)

![](screenshots/6.jpg)

![](screenshots/7.jpg)

