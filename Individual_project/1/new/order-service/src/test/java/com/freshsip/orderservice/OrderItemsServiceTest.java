//package com.freshsip.orderservice;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.modelmapper.ModelMapper;
//import org.modelmapper.TypeToken;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.web.client.RestTemplate;
//
//import java.time.LocalDate;
//import java.time.LocalTime;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//@ActiveProfiles("test")
//public class OrderItemsServiceTest {
//
//    @Mock
//    private OrderItemsRepository orderItemsRepository;
//
//    @Mock
//    private OrderRepository orderRepository;
//
//    @Mock

//    private RestTemplate restTemplate;
//
//
//    @Mock
//    private ModelMapper modelMapper;
//
//    @InjectMocks
//    private OrderItemsService orderItemsService;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//
//    @Test
//    public void testSaveOrder() {
//        // Arrange
//        ProductDTO product1 = new ProductDTO(1L, 1500.00, 15, "Product1", 100.0);
//        ProductDTO product2 = new ProductDTO(2L, 1500.00, 10, "Product2", 150.0);
//        List<ProductDTO> selectedProducts = Arrays.asList(product1, product2);
//
//        OrderItemsDTO orderItemsDTO = new OrderItemsDTO();
//        orderItemsDTO.setCart_id(1L);
//        orderItemsDTO.setSelectedProducts(selectedProducts);
//
//        Order order = new Order();
//        order.setCart_id(1L);
//
//        ItemDTO itemDTO1 = new ItemDTO();
//        itemDTO1.setItem_id(1L);
//        itemDTO1.setItem_name("Product1");
//        itemDTO1.setItem_prize(100.0);
//
//        ItemDTO itemDTO2 = new ItemDTO();
//        itemDTO2.setItem_id(2L);
//        itemDTO2.setItem_name("Product2");
//        itemDTO2.setItem_prize(150.0);
//
//        // Act: mock the repositories and REST calls
//        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
//        when(restTemplate.getForObject("http://localhost:8083/items/1", ItemDTO.class)).thenReturn(itemDTO1);
//        when(restTemplate.getForObject("http://localhost:8083/items/2", ItemDTO.class)).thenReturn(itemDTO2);
//
//        orderItemsService.saveOrder(orderItemsDTO);
//
//        // Assert
//        verify(orderRepository, times(1)).findById(1L);
//        verify(restTemplate, times(1)).getForObject("http://localhost:8083/items/1", ItemDTO.class);
//        verify(restTemplate, times(1)).getForObject("http://localhost:8083/items/2", ItemDTO.class);
//        verify(orderItemsRepository, times(2)).save(any(OrderItems.class));
//    }
//
//    @Test
//    public void testGetAllOrderItems() {
//
//        OrderItems orderItem = new OrderItems();
//        List<OrderItems> orderItemsList = Arrays.asList(orderItem);
//
//        when(orderItemsRepository.findAll()).thenReturn(orderItemsList);
//        when(modelMapper.map(orderItemsList, new TypeToken<List<OrderItemsDTO>>() {}.getType())).thenReturn(Arrays.asList(new OrderItemsDTO()));
//
//
//        List<OrderItemsDTO> result = orderItemsService.getAllOrderItems();
//
//
//        assertNotNull(result);
//        assertEquals(1, result.size());
//        verify(orderItemsRepository, times(1)).findAll();
//    }
//
//    @Test
//    public void testDeleteOrderByOrderID() {
//        Long cartId = 1L;
//
//
//        orderItemsService.deleteOrderByOrderID(cartId);
//
//
//        verify(orderItemsRepository, times(1)).deleteByCartId(cartId);
//    }
//
//    @Test
//    public void testGetChannelingByChannelingDate() {
//        Long cartId = 1L;
//        List<OrderItems> orderItemsList = Arrays.asList(new OrderItems());
//
//        when(orderItemsRepository.getChannelingByChannelingDate(cartId)).thenReturn(orderItemsList);
//        when(modelMapper.map(orderItemsList, new TypeToken<List<OrderItemsDTO>>() {}.getType())).thenReturn(Arrays.asList(new OrderItemsDTO()));
//
//        List<OrderItemsDTO> result = orderItemsService.getChannelingByChannelingDate(cartId);
//
//        assertNotNull(result);
//        verify(orderItemsRepository, times(1)).getChannelingByChannelingDate(cartId);
//    }
//
//    @Test
//    public void testGetOrderByOrderDate() {
//        LocalTime createTime = LocalTime.now();
//        List<OrderItems> orderItemsList = Arrays.asList(new OrderItems());
//
//        when(orderItemsRepository.getOrderByOrderTime(createTime)).thenReturn(orderItemsList);
//
//        List<OrderItems> result = orderItemsService.getOrderByOrderDate(createTime);
//
//        assertNotNull(result);
//        verify(orderItemsRepository, times(1)).getOrderByOrderTime(createTime);
//    }
//
//    @Test
//    public void testGetOrderByOrders() {
//
//        LocalDate createDate = LocalDate.now();
//
//        Object[] orderData1 = {1L, "Item1", 10, 100.0};
//        Object[] orderData2 = {2L, "Item2", 5, 50.0};
//
//        List<Object[]> orderDataList = Arrays.asList(orderData1, orderData2);
//
//
//        when(orderItemsRepository.getOrderByOrders(createDate)).thenReturn(orderDataList);
//
//
//        List<Object[]> result = orderItemsService.getOrderByOrders(createDate);
//
//
//        assertNotNull(result);
//        assertEquals(2, result.size(), "The result should contain two items");
//        assertEquals("Item1", result.get(0)[1], "The first item's name should be 'Item1'");
//        assertEquals(100.0, result.get(0)[3], "The first item's price should be 100.0");
//
//
//        verify(orderItemsRepository, times(1)).getOrderByOrders(createDate);
//    }
//
//    @Test
//    public void testGetOrderByOrder() {
//        Long cartId = 1L;
//        List<OrderItems> orderItemsList = Arrays.asList(new OrderItems());
//
//        when(orderItemsRepository.getOrderByOrder(cartId)).thenReturn(orderItemsList);
//
//        List<OrderItems> result = orderItemsService.getOrderByOrder(cartId);
//
//        assertNotNull(result);
//        verify(orderItemsRepository, times(1)).getOrderByOrder(cartId);
//    }
//
//
//    @Test
//    public void testUpdateOrder() {
//        // Arrange
//        ProductDTO product1 = new ProductDTO(1L, 1000, 10, "Product1", 100.0);
//        List<ProductDTO> selectedProducts = Arrays.asList(product1);
//
//        OrderItemsDTO orderItemsDTO = new OrderItemsDTO();
//        orderItemsDTO.setCart_id(1L);
//        orderItemsDTO.setSelectedProducts(selectedProducts);
//
//        Order order = new Order();
//        order.setCart_id(1L);
//
//        ItemDTO itemDTO = new ItemDTO();
//        itemDTO.setItem_id(1L);
//        itemDTO.setItem_name("Product1");
//        itemDTO.setItem_prize(100.0);
//
//        // Mock order and REST call to product-service
//        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
//        when(restTemplate.getForObject("http://localhost:8083/items/1", ItemDTO.class)).thenReturn(itemDTO);
//
//        // Act
//        orderItemsService.updateOrder(1L, orderItemsDTO);
//
//        // Assert
//        verify(orderRepository, times(1)).findById(1L);
//        verify(restTemplate, times(1)).getForObject("http://localhost:8083/items/1", ItemDTO.class);
//        verify(orderItemsRepository, times(1)).save(any(OrderItems.class));
//    }
//
//}
