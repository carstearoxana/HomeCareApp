package com.sda.homeCare.entities;

import javax.persistence.*;

@Entity
@Table(name="shopping_cart_item")
public class ShoppingCartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer shoppingCartItemId;

    @ManyToOne
    @JoinColumn(name="shopping_cart_id")
    ShoppingCart shoppingCart;

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    @ManyToOne
    @JoinColumn(name="product_id")
    Product product;

    public Integer getShoppingCartItemId() {
        return shoppingCartItemId;
    }

    public void setShoppingCartItemId(Integer shoppingCartItemId) {
        this.shoppingCartItemId = shoppingCartItemId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
