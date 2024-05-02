package rikkei.academy.business.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Catalog implements Serializable {
    private int catalogId;
    private String catalogName;
    private String descriptions;
    private List<Product> products;

    public Catalog() {
        this.products = new ArrayList();
    }

    public Catalog(int catalogId, String catalogName, String descriptions) {
        this.catalogId = catalogId;
        this.catalogName = catalogName;
        this.descriptions = descriptions;
    }

    public int getCatalogId() {
        return this.catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public String getCatalogName() {
        return this.catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public String getDescriptions() {
        return this.descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public List<Product> getProducts() {
        return this.products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            Catalog catalog = (Catalog)o;
            return this.catalogId == catalog.catalogId;
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.catalogId});
    }

    public String toString() {
        return "Catalog ID: " + this.catalogId + ", Catalog Name: " + this.catalogName;
    }
}