package net.amigochi.amigochi.features.statusbar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
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
    private int points;

    private double money;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
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

    @Override
    public void onPointsAdded(int pointsToAdd) {
        points += pointsToAdd;

        setPointsText(String.format("%d", points));
    }

    @Override
    public void onPointsReduced(int pointsToReduce) {
        points -= pointsToReduce;

        setPointsText(String.format(String.format("%d",points)));
    }

    @Override
    public void onMoneyAdded(double moneyToAdd) {

    }

    @Override
    public void onMoneyRemoved(double moneToRemove) {

    }
}
