package com.sda.homeCare.repositories;


import com.sda.homeCare.entities.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart,Integer> {
    public ShoppingCart findByUser_UserId (Integer id);
    public ShoppingCart findByUser_UserEmail (String email);
}
