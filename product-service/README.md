# Product-Service

- Refs: [YouTube Vids: Programming Techie](https://www.youtube.com/watch?v=yn_stY3HCr8&t=23978s)
- Github Source Code: [URL](https://github.com/SaiUpadhyayula/spring-boot-3-microservices-course)
## Docker Compose 

```bash
# 볼륨까지 같이 생성 + load 명령어 
docker-compose up -d

# 볼륨까지 같이 삭제 + down시키는 명령어
docker-compose down -v
```

## Product Service [Java JDK17 + SpringBoot]
`application.yml`: 
```yaml
server:
  port: 8001

spring:
  application:
    name: product-service

  data:
    mongodb:
      uri: "mongodb://localhost:27017/product-service"
```