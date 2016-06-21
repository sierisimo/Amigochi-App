package net.amigochi.amigochi.features.store;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import net.amigochi.amigochi.BuildConfig;
import net.amigochi.amigochi.R;

/**
 * Created by sierisimo on 21/06/16.
 */
public class StoreFragment extends Fragment implements View.OnClickListener {
    private int pageActiveId;
    private int pageActive;

    private int suiteActive;

    private int buttonsArr[];

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        pageActive = 1;
        pageActiveId = R.id.iv_fr_store_dot_1;

        suiteActive = -1;

        buttonsArr = new int[]{
                R.id.iv_fr_store_status_11_a,
                R.id.iv_fr_store_status_12_a,
                R.id.iv_fr_store_status_21_a,
                R.id.iv_fr_store_status_22_a,
                R.id.iv_fr_store_status_11_b,
                R.id.iv_fr_store_status_12_b,
                R.id.iv_fr_store_status_21_b,
                R.id.iv_fr_store_status_22_b
        };
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_store, container, false);

        init(view);

        return view;
    }

    private void init(View view) {
//        setDots(view);
        setSuitesClicks(view);

        int suiteId = getActivity().getSharedPreferences(BuildConfig.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
                .getInt("suiteId", -1);

        if (suiteId != -1) {
            int drawableId = 0;
            switch (suiteId) {
                case 1:
                    drawableId = R.drawable.suite_1;
                    break;
                case 2:
                    drawableId = R.drawable.suite_2;
                    break;
                case 3:
                    drawableId = R.drawable.suite_3;
                    break;
            }
            ((ImageView) view.findViewById(R.id.iv_fr_store_amigochin_suite)).setImageResource(drawableId);
        }
    }

    private void setSuitesClicks(View view) {
        view.findViewById(R.id.iv_fr_store_status_11_a).setOnClickListener(this);
        view.findViewById(R.id.iv_fr_store_status_11_b).setOnClickListener(this);
        view.findViewById(R.id.iv_fr_store_status_12_a).setOnClickListener(this);
        view.findViewById(R.id.iv_fr_store_status_12_b).setOnClickListener(this);
        view.findViewById(R.id.iv_fr_store_status_21_a).setOnClickListener(this);
        view.findViewById(R.id.iv_fr_store_status_21_b).setOnClickListener(this);
        view.findViewById(R.id.iv_fr_store_status_22_a).setOnClickListener(this);
        view.findViewById(R.id.iv_fr_store_status_22_b).setOnClickListener(this);
    }

    private void setDots(View view) {
        view.findViewById(R.id.iv_fr_store_dot_1).setOnClickListener(this);
        view.findViewById(R.id.iv_fr_store_dot_2).setOnClickListener(this);
        view.findViewById(R.id.iv_fr_store_dot_3).setOnClickListener(this);
        view.findViewById(R.id.iv_fr_store_dot_4).setOnClickListener(this);

        view.findViewById(R.id.iv_fr_store_arrow_left).setOnClickListener(this);
        view.findViewById(R.id.iv_fr_store_arrow_right).setOnClickListener(this);

        if (pageActive == 1) {
            view.findViewById(R.id.iv_fr_store_arrow_left).setEnabled(false);
        } else if (pageActive == 4) {
            view.findViewById(R.id.iv_fr_store_arrow_right).setEnabled(false);
        }
    }

    private void changeDots(int newId) {
        if (getView() != null && pageActiveId != newId) {
            ((ImageView) getView().findViewById(pageActiveId)).setImageResource(R.drawable.vector_gps_inactive);
            ((ImageView) getView().findViewById(newId)).setImageResource(R.drawable.vector_gps_active);

            pageActiveId = newId;

            setArrows();
        }
    }

    private void setArrows() {
        if (getView() != null) {
            View view = getView();

            if (pageActive == 1) {
                view.findViewById(R.id.iv_fr_store_arrow_left).setEnabled(false);
            } else if (pageActive == 4) {
                view.findViewById(R.id.iv_fr_store_arrow_right).setEnabled(false);
            } else {
                view.findViewById(R.id.iv_fr_store_arrow_left).setEnabled(true);
                view.findViewById(R.id.iv_fr_store_arrow_right).setEnabled(true);
            }
        }
    }

    private void moveByArrows(int moveElements) {
        pageActive += moveElements;
        if (getView() != null) {
            setArrows();

            switch (pageActive) {
                case 1:
                    changeDots(R.id.iv_fr_store_dot_1);
                    break;
                case 2:
                    changeDots(R.id.iv_fr_store_dot_2);
                    break;
                case 3:
                    changeDots(R.id.iv_fr_store_dot_3);
                    break;
                case 4:
                    changeDots(R.id.iv_fr_store_dot_4);
                    break;
            }
        }
    }

    private void setSuite() {
        for (int i = 0; i < buttonsArr.length; i++) {
            getView().findViewById(buttonsArr[i]).setEnabled(buttonsArr[i] != suiteActive);
        }

        int suiteId = -1;

        switch (suiteActive) {
            case R.id.iv_fr_store_status_11_a:
            case R.id.iv_fr_store_status_22_a:
                suiteId = 1;
                ((ImageView) getView().findViewById(R.id.iv_fr_store_amigochin_suite)).setImageResource(R.drawable.suite_1);
                getView().findViewById(R.id.iv_fr_store_amigochin_suite).setVisibility(View.VISIBLE);
                break;
            case R.id.iv_fr_store_status_12_a:
                suiteId = 2;
                ((ImageView) getView().findViewById(R.id.iv_fr_store_amigochin_suite)).setImageResource(R.drawable.suite_2);
                getView().findViewById(R.id.iv_fr_store_amigochin_suite).setVisibility(View.VISIBLE);
                break;
            case R.id.iv_fr_store_status_21_a:
                suiteId = 3;
                ((ImageView) getView().findViewById(R.id.iv_fr_store_amigochin_suite)).setImageResource(R.drawable.suite_3);
                getView().findViewById(R.id.iv_fr_store_amigochin_suite).setVisibility(View.VISIBLE);
                break;
            case R.id.iv_fr_store_status_11_b:
            case R.id.iv_fr_store_status_12_b:
            case R.id.iv_fr_store_status_21_b:
            case R.id.iv_fr_store_status_22_b:
                getView().findViewById(R.id.iv_fr_store_amigochin_suite).setVisibility(View.INVISIBLE);
                suiteId = -1;
                break;
        }

        getActivity().getSharedPreferences(BuildConfig.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
                .edit()
                .putInt("suiteId", suiteId)
                .apply();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_fr_store_dot_1:
                pageActive = 1;
                changeDots(v.getId());
                break;
            case R.id.iv_fr_store_dot_2:
                pageActive = 2;
                changeDots(v.getId());
                break;
            case R.id.iv_fr_store_dot_3:
                pageActive = 3;
                changeDots(v.getId());
                break;
            case R.id.iv_fr_store_dot_4:
                pageActive = 4;
                changeDots(v.getId());
                break;
            case R.id.iv_fr_store_arrow_left:
                moveByArrows(-1);
                break;
            case R.id.iv_fr_store_arrow_right:
                moveByArrows(1);
                break;
            case R.id.iv_fr_store_status_11_a:
            case R.id.iv_fr_store_status_12_a:
            case R.id.iv_fr_store_status_21_a:
            case R.id.iv_fr_store_status_22_a:
            case R.id.iv_fr_store_status_11_b:
            case R.id.iv_fr_store_status_12_b:
            case R.id.iv_fr_store_status_21_b:
            case R.id.iv_fr_store_status_22_b:
                suiteActive = v.getId();
                setSuite();
                break;
        }
    }
}
