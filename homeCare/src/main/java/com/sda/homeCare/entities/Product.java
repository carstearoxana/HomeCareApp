package com.sda.homeCare.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;
    @Column
    private String productName;
    @Column
    private String productDescription;
    @Column
    private Integer productPrice;
    @Column
    private String productPicture;
    @Column
    private Boolean available;

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    @OneToMany(mappedBy = "product")
    List<ShoppingCartItem> shoppingCartItemList;

    public List<ShoppingCartItem> getShoppingCartItemList() {
        return shoppingCartItemList;
    }

    public void setShoppingCartItemList(List<ShoppingCartItem> shoppingCartItemList) {
        this.shoppingCartItemList = shoppingCartItemList;
    }

    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;

    //sintaxa folosita pentru rel manyToMany fara a crea o entitate vizibila in java
//    @ManyToMany(mappedBy = "shoppingCartProducts")
//    Set<ShoppingCart> productShoppingCarts;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public Integer getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Integer productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductPicture() {
        return productPicture;
    }

    public void setProductPicture(String productPicture) {
        this.productPicture = productPicture;
    }
}
