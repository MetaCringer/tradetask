package edu.mobidev;

import java.util.HashMap;
import java.util.Map;

public interface ITradePoint {
    public static Map<ITradePoint,Integer> indexes=new HashMap<ITradePoint,Integer>();
    public String getName();
    public int getPrice(IGoodie goodie);
    public Map<IGoodie,Integer> getPrices();
}
