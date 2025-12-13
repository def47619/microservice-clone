package com.leew.microservice.order_service.stubs;

import lombok.experimental.UtilityClass;
import static com.github.tomakehurst.wiremock.client.WireMock.*;

@UtilityClass
public class InventoryClientStub {

    /**
     * 재고 충분 -> true 반환
     */
    public static void stubInventoryCall(String skuCode, Integer quantity) {
        stubFor(get(urlEqualTo("/api/inventory/exists?skuCode=" + skuCode + "&quantity=" + quantity))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("true")
        ));
    }

    /**
     *  재고 부족 -> false 반환
     */
    public void stubInventoryOutOfStock(String skuCode, Integer quantity) {

        // 재고 부족 → false 반환
        stubFor(get(urlEqualTo("/api/inventory/exists?skuCode=" + skuCode + "&quantity=" + quantity))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("false")));
    }
}
