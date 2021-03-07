package edu.mobidev;

import java.util.HashMap;
import java.util.Map;

public interface IGoodie {
    public static int c = 0;
    public static Map<IGoodie,Integer> indexes = new HashMap<IGoodie,Integer>();
    public String getName();
    //public int getPrice();

}
