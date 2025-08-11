package com.cbs.elctronic.store.repositories;

import com.cbs.elctronic.store.entities.Cart;
import com.cbs.elctronic.store.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart,String> {

    Optional<Cart> findByUser(User user);
}
