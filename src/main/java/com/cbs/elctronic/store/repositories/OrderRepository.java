package com.cbs.elctronic.store.repositories;

import com.cbs.elctronic.store.entities.Order;
import com.cbs.elctronic.store.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,String> {

    List<Order> findByUser(User user);
}
