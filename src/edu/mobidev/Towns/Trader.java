package edu.mobidev.Towns;

import edu.mobidev.*;

import java.util.*;
import java.util.List;

public class Trader implements ITrader {
    private int money;
    private ITradePoint currentLocation=null;
    private IGoodie caryingGoodie;
    private Set<ITradePoint> visited = new HashSet<ITradePoint>();
    public Trader(int money){
        this.money=money;
    }

    @Override
    public int getMoney() {
        return money;
    }

    @Override
    public void move(ITradePoint tradepoint) {
        if(visited.contains(tradepoint)){
            System.out.println("Trader cant move to " + currentLocation.getName());
            System.out.println("Anything works not right");
            return;
        }
        currentLocation=tradepoint;
        System.out.println("Trader move to " + currentLocation.getName());
    }

    @Override
    public boolean buy(IGoodie g) {
        if(caryingGoodie != null){return false;}
        int temp = money - currentLocation.getPrice(g);
        if(temp>=0){
            money=temp;
            caryingGoodie=g;
            System.out.println
                    ("Trader buy " + caryingGoodie.getName() +
                            " for "+ currentLocation.getPrice(g) +
                            " in " +currentLocation.getName()+" money left "+money);

            return true;
        }
        return false;
    }



    @Override
    public boolean sell() {
        if(caryingGoodie == null){
            System.out.println("Nothing for sell");
            return false;
        }
        try {
            int price =currentLocation.getPrice(caryingGoodie);
            money+=price;
            System.out.println("Trader sell "+caryingGoodie.getName()+" for "+price+" in "+currentLocation.getName()+" money left "+money);
            caryingGoodie=null;

            return true;
        }catch (Exception e){

        }

        return false;
    }

    @Override
    public IGoodie getGoodie() {
        return caryingGoodie;
    }

    @Override
    public void followOptimalPath(List<ITradePoint> points) {
        Navigator nav = new Navigator(points);
        nav.compute().direct(this);
    }
    private class Navigator implements INavigator {
        List<ITradePoint> points;
        Map<way,Map<IGoodie,Integer>> relations=new HashMap<way,Map<IGoodie,Integer>>();
        IPath result;
        public Navigator(List<ITradePoint> points){
            this.points=points;
            for (ITradePoint from:
                    points) {
                for (ITradePoint to:
                        points) {
                    if(from.equals(to)){
                        continue;
                    }
                    Map<IGoodie,Integer> dif = new HashMap<IGoodie,Integer>();
                    Map<IGoodie,Integer> toArr=to.getPrices();
                    Map<IGoodie,Integer> fromArr= from.getPrices();
                    for (Map.Entry<IGoodie,Integer> value:
                         toArr.entrySet()) {
                        IGoodie k =value.getKey();
                        if(fromArr.containsKey(k)){
                            dif.put(k,value.getValue() - fromArr.get(k));
                        }
                    }
                    relations.put(new way(from,to),dif);
                }
            }

        }

        @Override
        public IPath compute(){
            for (ITradePoint town:
                 points) {
                search(new Path(town,money));
            }
            return result;
        }
        private void search(IPath p){
            boolean end = true;
            List<ITradePoint> visited = p.getList();
            for1:
            for (Map.Entry<way,Map<IGoodie,Integer>> entry:
                 relations.entrySet()) {
                if(visited.get(visited.size()-1).equals(entry.getKey().from)){
                    for (ITradePoint v:
                         visited) {
                        if(entry.getKey().to.equals(v)){
                            continue for1;
                        }
                    }
                    makeBranch(p,entry);
                    end=false;

                }
            }
            if(end){
                if(result==null ||result.getProfit() < p.getProfit()){
                    result=p;
                }

            }
        }

        private void makeBranch(IPath p,Map.Entry<way,Map<IGoodie,Integer>> relation){

            int maxProfit=0xffffffff;
            IGoodie selectedGoodie=null;
            for (Map.Entry<IGoodie,Integer> en:
            relation.getValue().entrySet()) {
                if(en.getValue().intValue() > maxProfit){
                    if(p.getList().get(p.getList().size()-1).getPrice(en.getKey())>p.getProfit()){
                        //if no enough money
                        continue;
                    }
                    maxProfit = en.getValue();
                    selectedGoodie = en.getKey();
                }
            }
            if(selectedGoodie == null){
                return;
            }

            IPath nv = p.clone();
            nv.getChoosedGoodie().add(selectedGoodie);
            nv.getList().add(relation.getKey().to);

            nv.addProfit(maxProfit);
            search(nv);
        }

        @Override
        public List<ITradePoint> getPoints() {
            return null;
        }

    }


    private class way{
        ITradePoint from,to;
        public way(ITradePoint from,ITradePoint to){
            this.from=from;
            this.to=to;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            way way = (way) o;
            return from.equals(way.from) && to.equals(way.to);
        }

        @Override
        public int hashCode() {
            return Objects.hash(from, to);
        }
    }
}
