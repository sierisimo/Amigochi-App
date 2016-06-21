package net.amigochi.amigochi.features.statusbar;

/**
 * Created by sierisimo on 21/06/16.
 */
public interface OnStatusChange {
    void onPointsAdded(int pointsToAdd);

    void onPointsReduced(int pointsToReduce);

    void onMoneyAdded(double moneyToAdd);

    void onMoneyRemoved(double moneToRemove);
}
