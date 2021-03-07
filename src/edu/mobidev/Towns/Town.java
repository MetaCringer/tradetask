package edu.mobidev.Towns;



import edu.mobidev.IGoodie;
import edu.mobidev.ITradePoint;

import java.util.*;

public class Town implements ITradePoint {
    private String name;
    private Map<IGoodie,Integer> prices = new HashMap<IGoodie,Integer>();

    public Town(String name,Map<IGoodie,Integer> prices) {
        this.name = name;
        this.prices=prices;
        ITradePoint.indexes.put(this,ITradePoint.indexes.size());
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getPrice(IGoodie goodie) {
        return prices.get(goodie);
    }

    @Override
    public Map<IGoodie, Integer> getPrices() {
        return prices;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Town town = (Town) o;
        return name.equals(town.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
