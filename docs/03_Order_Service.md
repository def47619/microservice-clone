# Order_Service

### 서버 설정사항 
- Java JDK17
- Spring 3.X
- MySQL + JPA 사용 예정 

### Spring Initializr
![nn](images/08.png)
- 이번 서버에서는 MySQL 사용할 예정이므로 JPA dependency 도입 
- `TestContainers`: 인수 테스트 위한 도구 
- `Flyway Migration`: 데이터베이스 마이그레이션 툴 
    - https://ywoosang.tistory.com/18
    - https://coding-jun.tistory.com/14


### MySQL: docker-compose.yml로 설정 

- Port: `3307:3306`
    - 로컬 pc에 설치된 MySQL WorkBench와의 포트 겹침을 방지하기 위함
```yaml: 
version: '4'

services:
  mysql:
    image: mysql:8.3.0
    container_name: mysql
    environment:
      MYSQL_ROOT_PASSWORD: mysql
    ports:
      - "3307:3306"
    volumes:
      - ./mysql:/var/lib/mysql
      - ./docker/mysql/init.sql:/docker-entrypoint-initdb.d/init.sql
```
- `docker/mysql/init.sql`: 
```sql
CREATE DATABASE IF NOT EXISTS order_service;
```

- 실행 bash 명령어: 
```bash
# docker-compose 실행
docker-compose up -d

# 중단 
docker-compose down -v

# 중단 후 연결된 볼륨 삭제 
rm -rf ./mysql
```

- 실행 결과 :  
![nn](images/09.png)

### application.yaml: 
```yaml
server:
  port: 8002

spring:
  application:
    name: order-service

  datasource:
    url: jdbc:mysql://localhost:3307/order_service
    username: root
    password: mysql
    driver-class-name: com.mysql.cj.jdbc.Driver

  jpa:
    hibernate:
      ddl-auto: none
    properties:
      hibernate:
        format_sql: true
    database-platform: org.hibernate.dialect.MySQL8Dialect
    show-sql: true
```

실행 결과 : 정상적으로 실행됨 
![nn](images/10.png)