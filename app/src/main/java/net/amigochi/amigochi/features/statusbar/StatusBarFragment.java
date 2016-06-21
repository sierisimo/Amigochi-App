package net.amigochi.amigochi.features.statusbar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.amigochi.amigochi.R;

/**
 * Created by sierisimo on 21/06/16.
 */
public class StatusBarFragment extends Fragment implements OnStatusChange {
    private static final String TAG = "StatusBarFragment";

    private int points;

    private double money;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        points = 0;
        money = 0.0;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_statusbar, container, false);


        return view;
    }

    private void setPointsText(String value) {
        if (getView() != null) {
            ((TextView) getView().findViewById(R.id.tv_fr_statusbar_points)).setText(value);
        }
    }

    private void setMoneyText(String moneyText) {
        if (getView() != null) {
            ((TextView) getView().findViewById(R.id.tv_fr_statusbar_money)).setText(moneyText);
        }
    }

    @Override
    public void onPointsAdded(int pointsToAdd) {
        points += pointsToAdd;

        setPointsText(String.format("%d", points));
    }

    @Override
    public void onPointsReduced(int pointsToReduce) {
        points -= pointsToReduce;

        setPointsText(String.format("%d", points));
    }

    @Override
    public void onMoneyAdded(double moneyToAdd) {
        money += moneyToAdd;

        setMoneyText(String.format("%.2f", money));
    }

    @Override
    public void onMoneyRemoved(double moneToRemove) {
        money -= moneToRemove;

        setMoneyText(String.format("%.2f", money));
    }
}
