package edu.mobidev;

import java.util.List;

public interface IPath {
    public int getProfit();
    public void addProfit(int profit);
    public void direct(ITrader trader);
    public List<ITradePoint> getList();
    public List<IGoodie> getChoosedGoodie();
    public IPath clone();
}
