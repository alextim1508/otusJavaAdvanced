package ru.otus.timofeev.task17.service;

import com.otus.grpc.*;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.HashMap;
import java.util.Map;


@Slf4j
@GrpcService
public class ShopService extends ProductInfoGrpc.ProductInfoImplBase {

    private final Map<String, User> usersById = new HashMap<>();
    private final Map<String, Product> productById = new HashMap<>();
    private final Map<User, Product> orderMap = new HashMap<>();


    @Override
    public void createUser(User request, StreamObserver<UserID> responseObserver) {
        usersById.put(request.getId(), request);
        log.info("User created: id: {}, userName: {}, email: {}",
                request.getId(), request.getUsername(), request.getEmail());

        responseObserver.onNext(UserID.newBuilder().setValue(request.getId()).build());
        responseObserver.onCompleted();
    }

    @Override
    public void changeUserEmail(User request, StreamObserver<User> responseObserver) {
        User user = usersById.get(request.getId());
        User updatedUser = User.newBuilder()
                .setId(user.getId())
                .setUsername(user.getUsername())
                .setEmail(request.getEmail())
                .build();

        usersById.put(user.getId(), updatedUser);
        log.info("User updated: id: {}, userName: {}, email: {}",
                updatedUser.getId(), updatedUser.getUsername(), updatedUser.getEmail());

        responseObserver.onNext(updatedUser);
        responseObserver.onCompleted();
    }

    @Override
    public void changeUserName(User request, StreamObserver<User> responseObserver) {
        User user = usersById.get(request.getId());
        User updatedUser = User.newBuilder()
                .setId(user.getId())
                .setUsername(request.getUsername())
                .setEmail(user.getEmail())
                .build();

        usersById.put(user.getId(), updatedUser);
        log.info("User updated: id: {}, userName: {}, email: {}",
                updatedUser.getId(), updatedUser.getUsername(), updatedUser.getEmail());

        responseObserver.onNext(updatedUser);
        responseObserver.onCompleted();
    }

    public void createProduct(Product request, StreamObserver<ProductID> responseObserver) {
        productById.put(request.getId(), request);

        log.info("Product created: id: {}, name: {}",
                request.getId(), request.getName());

        responseObserver.onNext(ProductID.newBuilder().setValue(request.getId()).build());
        responseObserver.onCompleted();
    }

    public void addProductToCart(Card card, StreamObserver<Card> productIDStreamObserver) {
        User currentUser = usersById.get(card.getUserId());
        Product product = productById.get(card.getProductId());

        Card currentCard = Card.newBuilder().setProductId(card.getProductId()).setUserId(card.getUserId()).build();
        orderMap.put(currentUser, product);

        log.info("Product id {} added to card for user with id: {}", card.getUserId(), card.getProductId());

        productIDStreamObserver.onNext(currentCard);
        productIDStreamObserver.onCompleted();
    }

}
