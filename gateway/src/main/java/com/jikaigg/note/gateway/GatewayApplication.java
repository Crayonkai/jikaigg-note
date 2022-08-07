package com.jikaigg.note.gateway;

@SpringBootApplication
public class GatewayApplication {
    public static void main(String[] args) {
        GatewayApplication.run(OrderApplication.class, args);
    }

}
