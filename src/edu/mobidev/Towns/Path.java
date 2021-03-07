package edu.mobidev.Towns;

import edu.mobidev.IGoodie;
import edu.mobidev.IPath;
import edu.mobidev.ITradePoint;
import edu.mobidev.ITrader;

import java.util.ArrayList;
import java.util.List;

public class Path implements IPath {
    private List<ITradePoint> sequence = new ArrayList<ITradePoint>();
    private List<IGoodie> choosedGoodies = new ArrayList<IGoodie>();
    public int profit=0;

    private Path(){

    }

    public Path(ITradePoint start,int capital){
        this.sequence.add(start);
        profit = capital;
    }


    @Override
    public int getProfit() {
        return profit;
    }

    @Override
    public void addProfit(int profit) {
        this.profit+=profit;
    }

    @Override
    public void direct(ITrader trader) {
        ITradePoint town=null;
        IGoodie g;

        for (int i = 0; i < sequence.size()-1; i++) {
            town = sequence.get(i);
            g=choosedGoodies.get(i);
            trader.move(town);
            if(!trader.sell()){
                System.out.println("Trader sell nothing");
            }

            trader.buy(g);
        }
        town = sequence.get(sequence.size()-1);
        trader.move(town);
        if(!trader.sell()){
            System.out.println("Trader sell nothing");
        }

    }
    @Override
    public List<ITradePoint> getList() {
        return sequence;
    }
    @Override
    public List<IGoodie> getChoosedGoodie() {
        return choosedGoodies;
    }


    @Override
    public IPath clone() {
        Path result = new Path();
        result.profit=profit;
        result.sequence = new ArrayList<ITradePoint>();
        result.sequence.addAll(sequence);
        result.choosedGoodies = new ArrayList<IGoodie>();
        result.choosedGoodies.addAll(choosedGoodies);
        return result;
    }
}
