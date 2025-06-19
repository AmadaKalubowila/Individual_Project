package com.freshsip.orderservice;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService implements OrderServiceImpl {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private RestTemplate restTemplate;
    public UserDTO getUserByEmail(String email) {
        return restTemplate.getForObject("http://user-service:8081/FreshSip/adminUser/" + email, UserDTO.class);


    }

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public OrderDTO saveOrder(OrderDTO orderDTO) {

        // Fetch user details from user-service via REST
        UserDTO user = getUserByEmail(orderDTO.getU_email());
        System.out.println("user"+orderDTO.getU_email());
        if (user == null) {
            throw new RuntimeException("User not found with email: " + orderDTO.getU_email());
        }

        // Map OrderDTO to Order entity
        Order order = modelMapper.map(orderDTO, Order.class);

        // Set only the userId and email (not full User entity)
        order.setUserId(user.getUserId());
        order.setU_email(orderDTO.getU_email());
        System.out.println("Received Order: " + orderDTO);
        System.out.println("Email: " + orderDTO.getU_email());
        // Save to repository
        orderRepository.save(order);

        return modelMapper.map(order, OrderDTO.class); // optional: return updated order
    }


    @Override
    public List<OrderDTO> getAllOrders(){
        List<Order>orderList=orderRepository.findAll();
        return modelMapper.map(orderList,new TypeToken<List<OrderDTO>>(){}.getType());

    }
    @Override
    public OrderDTO getLatestOrderByIndexNo(String u_email) {
        Long latestOrderId = orderRepository.findLatestOrderIdByIndexNo(u_email);
        if (latestOrderId != null) {
            Optional<Order> orderOptional = orderRepository.findById(latestOrderId);
            if (orderOptional.isPresent()) {
                return modelMapper.map(orderOptional.get(), OrderDTO.class);
            }
        }
        return null;
    }
    @Override
    public OrderDTO updateOrderByOrderID( Long cart_id , OrderDTO orderDTO){
        Order existingOrder = orderRepository.findById(cart_id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + cart_id));


        modelMapper.map(orderDTO, existingOrder);


        orderRepository.save(existingOrder);


        return modelMapper.map(existingOrder, OrderDTO.class);
    }

    @Override
    public OrderDTO getCartById(Long cart_id){
        Order order=orderRepository.getCartById(cart_id);
        return modelMapper.map(order,OrderDTO.class);
    }

}
