package com.cbs.elctronic.store.repositories;

import com.cbs.elctronic.store.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,String> {


}
