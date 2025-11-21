# Product 서비스 서버 : `product-service`

## Spring Initializr: 
![nn](images/01.png)
- 평소에는 RDBMS를 사용하기 때문에 JPA를 포함하지만, 
    - 이번에는 MongoDB를 사용하기 때문에  JPA 라이브러리 제외 
    - 포함하면 오히려 서버 구동 시 에러

- Spring Web
- Lombok
- Spring Data MongoDB
- Testcontainers

## MongoDB : Docker-Compose 구축
- 노트북 로컬 환경에 MongoDB 설치되어 27017 포트에서 실행 중 
    - 따라서 `27018:27017` 포트 포워딩 

- `docker-compose.yaml` : 
```yaml
version: '4'
services:
  mongodb:
    image: mongo:7.0.5
    container_name: mongodb
    ports:
      # 로컬에 mongodb 프로그램 깔아서 실행하고 있어서 부득이하게 다른 포트로 실행
      - "27018:27017"
    environment:
      MONGO_INITDB_ROOT_USERNAME: root
      MONGO_INITDB_ROOT_PASSWORD: password
      MONGO_INITDB_DATABASE: product-service
      # root 계정을 사용하려면, 접근 시 authSource=admin 설정해야 한다.
    volumes:
      - ./data:/data/db
```

실행 명령어: 
```bash
# docker 컨테이너 구축 
docker-compose up -d

# 컨테이너 종료
docker-compose down -v

# 볼륨 삭제: 리눅스 명령어
rm -rf ./data
```

## MongoDB와 연결 위한 `application.yml` 설정: 
```yaml
server:
  port: 8001

spring:
  application:
    name: product-service

  data:
    mongodb:
      # root 게정을 사용하려면, 접근 시 authSource=admin 설정해야 한다.
      uri: "mongodb://root:password@localhost:27018/product-service?authSource=admin"
```

## 코드: Github에서 확인

## 실행 결과 : Postman 확인 

### 1. 서버-DB 연동 로그 확인: 
![nn](images/02.png)

### 2. PostMan POST / GET 요청 결과: 

- POST 요청으로 Product 컬렉션에 새 데이터 추가
![nn](images/03.png)

- GET 요청으로 Product 컬렉션의 모든 데이터 Query
![nn](images/04.png)

### 3. MongoDB 컨테이너 exec하여 직접 데이터 조회 
```bash
>> docker exec -it mongodb mongosh -u root -p password --authenticationDatabase admin
```
![nn](images/05.png)

```bash
test> use product-service
switched to db product-service
product-service> show collections
product
product-service> db.product.find().pretty()
[
  {
    _id: ObjectId('6920e02876bce73deaafab0a'),
    name: 'iPhone 15',
    description: 'iPhone 15 is a smartphone from Apple',
    price: '1000',
    _class: 'com.leew.microservice.product_service.model.Product'
  }
]
```
![nn](images/06.png)