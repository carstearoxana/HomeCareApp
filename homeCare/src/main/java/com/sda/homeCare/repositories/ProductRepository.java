package com.sda.homeCare.repositories;

import com.sda.homeCare.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    public List<Product> findAllByCategory_CategoryName(String categoryName);
}
