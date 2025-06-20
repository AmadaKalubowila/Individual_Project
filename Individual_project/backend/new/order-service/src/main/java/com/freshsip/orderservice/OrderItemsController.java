package com.freshsip.orderservice;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping(value = "FreshSip/Order")
@CrossOrigin(origins = "http://localhost:3000")

public class OrderItemsController {

    @Autowired
    private OrderItemsService orderItemsService;
    @GetMapping(value="/OrdersItems", produces = "application/json")
    public List<OrderItemsDTO> getOrderItems(){

        return orderItemsService.getAllOrderItems();
    }
    @PostMapping(value="/adminUser/OrderItems", produces = "application/json")
    public ResponseEntity<String> submitOrder(@RequestBody OrderItemsDTO orderItemsDTO) {
        try {
            orderItemsService.saveOrder(orderItemsDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Order successfully submitted");
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to submit order");
        }
    }


    @DeleteMapping(value="/adminUser/OrderItemsByOrderItemsID/{cart_id}", produces = "application/json")
    public void deleteOrderByOrderID(@Valid @PathVariable Long cart_id){
        orderItemsService.deleteOrderByOrderID(cart_id);
    }

    @GetMapping(value="/OrderItemsLatest/{cart_id}", produces = "application/json")
    public List<OrderItemsDTO> getChannelingByChannelingDate(@PathVariable Long cart_id) {
        return orderItemsService.getChannelingByChannelingDate(cart_id);
    }


    @GetMapping(value="/OrderItemsByOrderItemsTime/{create_time}", produces = "application/json")
    public ResponseEntity<List<OrderItems>> getOrderItemsByOrderDate(
            @PathVariable("create_time")  LocalTime create_time) {

        List<OrderItems> orderItems = orderItemsService.getOrderByOrderDate(create_time);
        return ResponseEntity.ok(orderItems);
    }

    @GetMapping(value="/admin/OrderItemsByOrdersItemsDates/{create_date}", produces = "application/json")
    public ResponseEntity<List<Object[]>> getOrderItemsByOrderDate(
            @PathVariable("create_date") LocalDate create_date) {

        List<Object[]> orderItems = orderItemsService.getOrderByOrders(create_date);
        return ResponseEntity.ok(orderItems);
    }

    @GetMapping(value="/adminUser/OrderItemsByOrderItems/{cart_id}", produces = "application/json")
    public ResponseEntity<List<OrderItems>> getOrderItemsByOrder(
            @PathVariable("cart_id")  Long cart_id) {

        List<OrderItems> orderItems = orderItemsService.getOrderByOrder(cart_id);
        return ResponseEntity.ok(orderItems);
    }

    @PutMapping(value="/adminUser/OrderItemsOrder/{cart_id}", produces = "application/json")
    public ResponseEntity<String> updateOrder(@PathVariable Long cart_id,@RequestBody OrderItemsDTO orderItemsDTO) {
        try {
            orderItemsService.updateOrder(cart_id, orderItemsDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Order successfully submitted");
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to submit order");
        }
    }
}
