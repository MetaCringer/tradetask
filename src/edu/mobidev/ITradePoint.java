package edu.mobidev;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ITradePoint {
    public static Map<ITradePoint,Integer> indexes=new HashMap<ITradePoint,Integer>();
    public String getName();
    //public List<IGoodie> getGoodies();
    public int getPrice(IGoodie goodie);
    public Map<IGoodie,Integer> getPrices();
}
