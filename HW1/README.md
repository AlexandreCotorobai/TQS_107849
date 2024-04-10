## How to run

### Use app and run tests
To **USE THE APP** or **RUN TESTS**(Selenium inclusive) you can run docker compose
(from HW1 folder)
```bash
docker compose up --build
```
and to populate the db
```bash
python3 init_db/populateDB.py 
```

and inside bustickets folder
```bash
mvn test
```
(if you don't have redis you might need to change `localhost` to `cache` in `bustickets/src/main/java/tqs/hw1/bustickets/config/RedisConfig.java` to use)

#### Run integration tests
If you want to run **Integration Tests** you will need to exec a separated docker for mysql (with the previous docker compose down), since docker compose executes everything and we dont want that
```bash
docker run --name mysql5tqs -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=bustickets -e MYSQL_USER=user -e MYSQL_PASSWORD=mysql -p 33060:3306 -d mysql/mysql-server:5.7
```

Then to run tests (testing the connect with db)
```bash
mvn test -Pno-selenium
```

and now to populate db (from HW1 folder)
```bash
python3 init_db/populateDB.py 
```
and finally
```bash
mvn install failsafe:integration-test -Pno-selenium
```

``-Pno-seleniu`` is important to avoid running selenium tests since the frontend is not running (at the moment)

