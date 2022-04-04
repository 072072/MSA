package sang.ik.orderservice.service;

import sang.ik.orderservice.dto.OrderDto;
import sang.ik.orderservice.jpa.OrderEntity;

public interface OrderService {
    OrderDto createOrder(OrderDto orderDetails);

    OrderDto getOrderByOrderId(String orderId);

    Iterable<OrderEntity> getOrdersByUserId(String userId);
}
