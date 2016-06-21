package net.amigochi.amigochi.features.store;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import net.amigochi.amigochi.R;

/**
 * Created by sierisimo on 21/06/16.
 */
public class StoreFragment extends Fragment implements View.OnClickListener {
    private int pageActiveId;
    private int pageActive;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        pageActive = 1;
        pageActiveId = R.id.iv_fr_store_dot_1;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_store, container, false);

        init(view);

        return view;
    }

    private void init(View view) {
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
        }
    }
}
