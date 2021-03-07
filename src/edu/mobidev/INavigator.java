package edu.mobidev;

import java.util.List;

public interface INavigator {
    public List<ITradePoint> getPoints();

    public IPath compute();
}
