package com.cbs.elctronic.store.controllers;

import com.cbs.elctronic.store.dtos.ApiResponse;
import com.cbs.elctronic.store.dtos.CreateOrderRequest;
import com.cbs.elctronic.store.dtos.OrderDto;
import com.cbs.elctronic.store.dtos.PageableResponse;
import com.cbs.elctronic.store.services.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {


    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@Valid @RequestBody CreateOrderRequest orderDto)
    {
        OrderDto order = orderService.createOrder(orderDto);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<ApiResponse> removeOrder(@PathVariable String orderId)
    {
        orderService.removeOrder(orderId);
        ApiResponse responseMessage = ApiResponse.builder()
                .status(HttpStatus.OK)
                .message("order is removed !!")
                .success(true)
                .build();
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<OrderDto>> getOrdersOfUser(@PathVariable String userId) {
        List<OrderDto> ordersOfUser = orderService.getOrdersOfUser(userId);
        return new ResponseEntity<>(ordersOfUser, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<PageableResponse<OrderDto>> getOrders(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "orderedDate", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "desc", required = false) String sortDir
    ) {
        PageableResponse<OrderDto> orders = orderService.getOrders(pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PutMapping("/update/{orderId}")
    public ResponseEntity<OrderDto> updateOrder(@RequestBody CreateOrderRequest request,@PathVariable String orderId)
    {
        OrderDto orderDto = orderService.updateOrder(request, orderId);
        return new ResponseEntity<>(orderDto,HttpStatus.OK);
    }

}
