package edu.mobidev.Towns;

import edu.mobidev.IGoodie;
import edu.mobidev.ITradePoint;
import edu.mobidev.ITradePointFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class TownFromFileFactory implements ITradePointFactory {
    private File source;
    public TownFromFileFactory(File source) throws FileNotFoundException {
        this.source=source;
        if(!this.source.exists()){
            throw new FileNotFoundException(this.source.getPath());
        }

    }
    @Override
    public List<ITradePoint> getTradePoints() {
        List<ITradePoint> result = new ArrayList<ITradePoint>();
        try {
            Scanner scan = new Scanner(source);
            String temp;
            String[] args;
            ITradePoint t=null;
            Map<IGoodie,Integer> prices=null;
            while(scan.hasNext()){
                temp = scan.nextLine();
                if(temp.startsWith(" ")){
                    temp = temp.trim();
                    args=temp.split(":");
                    prices.put(new Product(args[0]),Integer.parseInt(args[1]));
                }else{
                    prices = new HashMap<IGoodie,Integer>();
                    t = new Town(temp.trim(),prices);
                    result.add(t);
                }
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        return result;
    }
}
