package ru.otus.timofeev.task17.service;

import com.otus.grpc.*;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ClientShopService {

    @GrpcClient("grpc-server")
    private ProductInfoGrpc.ProductInfoBlockingStub productInfoBlockingStub;

    public void createUser(String id, String name, String email) {
        User product = User.newBuilder()
                .setId(id)
                .setUsername(name)
                .setEmail(email)
                .build();
        log.info("Send request createUser - id: {}, name: {}, email: {}", id, name, email);

        log.info("Response: {} ", productInfoBlockingStub.createUser(product));
    }

    public void changeUserEmail(String id, String email) {
        User user = User.newBuilder()
                .setId(id)
                .setEmail(email)
                .build();
        log.info(String.format("Send request changeUserEmail - id: %s, email: %s", id, email));

        log.info("Response: {}", productInfoBlockingStub.changeUserEmail(user));
    }

    public void changeUserName(String id, String username) {
        User user = User.newBuilder()
                .setId(id)
                .setUsername(username)
                .build();
        log.info(String.format("Send request changeUserName - id: %s, username: %s", id, username));

        User createProductResponse = productInfoBlockingStub.changeUserName(user);
        log.info("Response: {}", createProductResponse);
    }

    public void createProduct(String productId, String description) {
        Product product = Product.newBuilder()
                .setId(productId)
                .setName(description)
                .build();
        log.info(String.format("Send request createProduct - id: %s, name: %s", productId, description));

        log.info("Server response: {}", productInfoBlockingStub.createProduct(product));
    }

    public void addProductToCard(String userId, String productId) {
        Card card = Card.newBuilder()
                .setUserId(userId)
                .setProductId(productId)
                .build();
        log.info(String.format("Send request addProductToCard - userId: %s, productId: %s", userId, productId));

        Card response = productInfoBlockingStub.addProductToCart(card);
        log.info("Response: userId: {}, productId {}:", response.getUserId(), response.getProductId());
    }
}
