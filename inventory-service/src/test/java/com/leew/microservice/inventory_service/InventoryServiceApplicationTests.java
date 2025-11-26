package com.leew.microservice.inventory_service;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.utility.DockerImageName; // DockerImageName 추가

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

// @Import(TestcontainersConfiguration.class) // 불필요하며 충돌 유발 가능성 있음 (제거)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class InventoryServiceApplicationTests {

	// 1. @ServiceConnection은 그대로 유지
	// 2. DockerImageName.parse()를 사용해 이미지 이름을 명확히 지정
	// 3. .withEnv("MYSQL_ALLOW_EMPTY_PASSWORD", "true")를 추가하여
	//    MySQL 컨테이너가 "code 1"로 종료되는 문제를 방지 (필수)
//	@ServiceConnection
//	static MySQLContainer mySQLContainer = new MySQLContainer("mysql:8.3.0");
	@ServiceConnection
	static MySQLContainer<?> mySQLContainer = new MySQLContainer<>(DockerImageName.parse("mysql:8.3.0")) // DockerImageName 사용 권장
			.withEnv("MYSQL_ALLOW_EMPTY_PASSWORD", "true"); // 필수: 비밀번호 없이 시작 허용

	@LocalServerPort
	private Integer port;

	@BeforeEach
	void setup() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
	}

	// // Spring Boot 3.1+는 @ServiceConnection이 붙은 static 필드를 찾아 자동으로 시작/종료 처리합니다.
//	static {
//		mySQLContainer.start();
//	}

	@Test
	void shouldReadInventory() {
		var response = RestAssured.given()
				.when()
				.get("/api/inventory/exists?skuCode=iphone_15&quantity=1")
				.then()
				.log().all()
				.statusCode(200)
				.extract().response().as(Boolean.class);
		assertTrue(response);

		var negativeResponse = RestAssured.given()
				.when()
				.get("/api/inventory/exists?skuCode=iphone_15&quantity=1000")
				.then()
				.log().all()
				.statusCode(200)
				.extract().response().as(Boolean.class);
		assertFalse(negativeResponse);

	}

}
