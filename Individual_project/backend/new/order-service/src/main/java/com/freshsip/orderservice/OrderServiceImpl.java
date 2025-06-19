package com.freshsip.orderservice;



import java.util.List;

public interface OrderServiceImpl {
    OrderDTO saveOrder(OrderDTO orderDTO);
    List<OrderDTO> getAllOrders();
    OrderDTO getLatestOrderByIndexNo(String u_email);
    OrderDTO updateOrderByOrderID( Long cart_id , OrderDTO orderDTO);
    public OrderDTO getCartById(Long cart_id);
}
