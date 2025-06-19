package com.freshsip.orderservice;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class OrderItemsService implements OrderItemsServiceImpl{
    @Autowired
    private OrderItemsRepository orderItemsRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RestTemplate restTemplate;

    public ItemDTO getItemById(Long id) {
        return restTemplate.getForObject("http://product-service:8083/FreshSip/ItemStock/admin/ITEMItemById/" + id, ItemDTO.class);
    }


    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    @Override
    public void saveOrder(OrderItemsDTO orderItemsDTO) {

    List<ProductDTO> selectedProducts = orderItemsDTO.getSelectedProducts();
    Long cartId = orderItemsDTO.getCart_id();


    Order order = orderRepository.findById(cartId)
            .orElseThrow(() -> new RuntimeException("Order not found"));

    for (ProductDTO product : selectedProducts) {

        ItemDTO item = getItemById(product.getId());
        if (item == null) throw new RuntimeException("Item not found");


        OrderItems orderItem = new OrderItems();


        orderItem.setOrder(order);
        orderItem.setItems(item);
orderItem.setCreate_date(orderItemsDTO.getCreate_date());
orderItem.setItemId(product.getId());
        orderItem.setPrice(product.getPrice());
        orderItem.setCart_quantity(product.getQuantity());
        orderItem.setItem_name(product.getItem_name());
        orderItem.setItem_price(product.getItem_price());


        orderItemsRepository.save(orderItem);
    }
}


    @Override
    public List<OrderItemsDTO> getAllOrderItems(){
        List<OrderItems>orderList=orderItemsRepository.findAll();
        return modelMapper.map(orderList,new TypeToken<List<OrderItemsDTO>>(){}.getType());

    }

    @Override
    @Transactional
    public void deleteOrderByOrderID( Long cart_id){
       orderItemsRepository.deleteByCartId(cart_id);

    }

    @Override
    public List<OrderItemsDTO> getChannelingByChannelingDate(Long cart_id){
        List<OrderItems> channelingList=orderItemsRepository.getChannelingByChannelingDate(cart_id);
        return modelMapper.map(channelingList, new TypeToken<List<OrderItemsDTO>>() {}.getType());
    }
    @Override
    public List<OrderItems> getOrderByOrderDate(LocalTime create_time) {
        return orderItemsRepository.getOrderByOrderTime(create_time);
    }
    @Override
    public List<Object[]> getOrderByOrders(LocalDate create_date) {
        return orderItemsRepository.getOrderByOrders(create_date);
    }
    @Override
    public List<OrderItems> getOrderByOrder(Long cart_id) {
        return orderItemsRepository.getOrderByOrder(cart_id);
    }
    @Override
    public void updateOrder(Long cart_id, OrderItemsDTO orderItemsDTO) {

        List<ProductDTO> selectedProducts = orderItemsDTO.getSelectedProducts();
        Long cartId = orderItemsDTO.getCart_id();


        Order order = orderRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        for (ProductDTO product : selectedProducts) {
            ItemDTO item = getItemById(product.getId());
            if (item == null) throw new RuntimeException("Item not found");


            OrderItems orderItem = new OrderItems();


            orderItem.setOrder(order);
            orderItem.setItems(item);

orderItem.setItemId(product.getId());
orderItem.setCreate_date(orderItemsDTO.getCreate_date());
            orderItem.setPrice(product.getPrice());
            orderItem.setCart_quantity(product.getQuantity());
            orderItem.setItem_name(product.getItem_name());
            orderItem.setItem_price(product.getItem_price());


            orderItemsRepository.save(orderItem);
        }
    }

}
