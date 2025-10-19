# Сборка и запуск

Для сборки модулей можно воспользоваться командой `mvn package`:
```shell
mvn clean package -DskipTests
```

Для развертывания всех модулей в Docker-контейнерах, а также Prometheus, Grafana и Zipkin,
можно использовать готовый [docker-compose.yml](docker-compose.yml)-файл:
```shell
docker compose up -d --build
```

После запуска доступны следующие ресурсы:
* Prometheus — по адресу http://localhost:9090/
* Grafana — по адресу http://localhost:3000/ (admin/admin)
* Zipkin — по адресу http://localhost:9411/

# Перезапуск одного модуля
В процессе работы нужно будет вносить изменения в сервисы и перезапускать их.
Чтобы не перезапускать все контейнеры, обновление можно выполнить точечно.
Например, для перезапуска сервиса _accounts_ можно использовать следующие команды:
```shell
> mvn clean package -DskipTests
> docker compose up -d --build accounts
```