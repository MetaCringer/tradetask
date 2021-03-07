package edu.mobidev.Towns;

import edu.mobidev.IGoodie;

import java.util.Objects;

public class Product implements IGoodie {

    String name;
    public Product(String name){
        this.name=name;
        if(!IGoodie.indexes.containsKey(this)){
            IGoodie.indexes.put(this,IGoodie.indexes.size());
        }
    }

    @Override
    public String getName() {
        return name;
    }



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
