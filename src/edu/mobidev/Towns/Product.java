package edu.mobidev.Towns;

import edu.mobidev.IGoodie;

import java.util.Objects;

public class Product implements IGoodie {

    String name;
    //int price;
    public Product(String name/*, int price*/){
        this.name=name;
        //this.price=price;
        if(!IGoodie.indexes.containsKey(this)){
            IGoodie.indexes.put(this,IGoodie.indexes.size());
        }
    }

    @Override
    public String getName() {
        return name;
    }

//    @Override
//    public int getPrice() {
//        return price;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return name.equals(product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
