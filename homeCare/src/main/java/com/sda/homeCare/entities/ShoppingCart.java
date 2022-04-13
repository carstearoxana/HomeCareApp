package com.sda.homeCare.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "shoppingCart")
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer shoppingCartId;

    @ManyToOne
    @JoinColumn(name ="user_id")
    private User user;

    @OneToMany(mappedBy = "shoppingCart")
    List<ShoppingCartItem> shoppingCartItemList;

    public List<ShoppingCartItem> getShoppingCartItemList() {
        return shoppingCartItemList;
    }

    public void setShoppingCartItemList(List<ShoppingCartItem> shoppingCartItemList) {
        this.shoppingCartItemList = shoppingCartItemList;
    }

    //sintaxa folosita pentru rel manyToMany fara a crea o entitate vizibila in java
//    @ManyToMany
//    @JoinTable(
//            name = "shoppind_cart_item",
//            joinColumns = @JoinColumn(name = "shopping_cart_id"),
//            inverseJoinColumns = @JoinColumn(name = "product_id"))
//    Set<Product> shoppingCartProducts;
    public Integer getShoppingCartId() {
        return shoppingCartId;
    }

    public void setShoppingCartId(Integer shoppingCartId) {
        this.shoppingCartId = shoppingCartId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
