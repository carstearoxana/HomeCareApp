package com.sda.homeCare.repositories;

import com.sda.homeCare.entities.Product;
import com.sda.homeCare.entities.ShoppingCartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoppingCartItemRepository extends JpaRepository<ShoppingCartItem,Integer> {
    public List<Product> findAllByProduct_ProductId(Integer id);
}
