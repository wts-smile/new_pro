package com.seven.pojo;

import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class Product implements Serializable {
    private int id;
    private String name;
    private String ptype;
    private String pic;
    private String detail;
    private double price;
    private int status;
    private int salecnt;

    public Product() {
    }

    public Product(int id, String name, String ptype, String pic, String detail, double price, int status, int salecnt) {
        this.id = id;
        this.name = name;
        this.ptype = ptype;
        this.pic = pic;
        this.detail = detail;
        this.price = price;
        this.status = status;
        this.salecnt = salecnt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPtype() {
        return ptype;
    }

    public void setPtype(String ptype) {
        this.ptype = ptype;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getSalecnt() {
        return salecnt;
    }

    public void setSalecnt(int salecnt) {
        this.salecnt = salecnt;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ptype='" + ptype + '\'' +
                ", pic='" + pic + '\'' +
                ", detail='" + detail + '\'' +
                ", status=" + status +
                ", salecnt=" + salecnt +
                '}';
    }
}
