package com.cbs.elctronic.store.services;

import com.cbs.elctronic.store.dtos.CreateOrderRequest;
import com.cbs.elctronic.store.dtos.OrderDto;
import com.cbs.elctronic.store.dtos.PageableResponse;

import java.util.List;

public interface OrderService {

    OrderDto createOrder(CreateOrderRequest request);

    OrderDto updateOrder(CreateOrderRequest request,String orderId);

    //remove order
    void removeOrder(String orderId);

    //get orders of user
    List<OrderDto> getOrdersOfUser(String userId);

    //get orders
    PageableResponse<OrderDto> getOrders(int pageNumber, int pageSize, String sortBy, String sortDir);
}
