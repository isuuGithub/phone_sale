package com.suge.bean;

import java.math.BigDecimal;

/**
 * @author suu
 * @create 2020-12-07 21:31
 */
public class Phone {
    private Integer id;
    private String name;
    private String brand;
    private BigDecimal price;
    private Integer sales;
    private Integer stock;

    private String imgPath = "static/img/";


    public Phone() {

    }

    public Phone(Integer id, String name, String brand, BigDecimal price, Integer sales, Integer stock, String imgPath) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.sales = sales;
        this.stock = stock;
        this.imgPath = imgPath;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        if (imgPath!=null){
            this.imgPath = imgPath;

        }

    }

    @Override
    public String toString() {
        return "Phone{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", price=" + price +
                ", sales=" + sales +
                ", stock=" + stock +
                ", imgPath='" + imgPath + '\'' +
                '}';
    }
}
