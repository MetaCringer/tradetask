package edu.mobidev;

import java.util.List;

public interface ITrader {
    public int getMoney();
    public void move(ITradePoint tradepoint);
    public boolean buy(IGoodie goodie);
    public IGoodie getGoodie();
    public boolean sell();
    public void followOptimalPath(List<ITradePoint> points);
}
