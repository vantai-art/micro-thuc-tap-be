package com.rainbowforest.orderservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "products")
public class Product {

    /**
     * FIX: @GeneratedValue bỏ strategy IDENTITY → dùng GenerationType.AUTO
     * với @Column(name = "id")
     * ID được gửi từ frontend (lấy từ product-catalog-service), không tự sinh.
     * Dùng TABLE hoặc bỏ hẳn @GeneratedValue để Hibernate không override id đã set.
     */
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "product_name")
    @NotNull
    private String productName;

    @Column(name = "price")
    @NotNull
    private BigDecimal price;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Item> items;

    public Product() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}