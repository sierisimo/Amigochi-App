package net.amigochi.amigochi.features.home;

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
public class HomeFragment extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        init(view);

        return view;
    }

    private void init(View view) {
        int suiteId = getActivity().getSharedPreferences(BuildConfig.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
                .getInt("suiteId", -1);

        if (suiteId != -1) {
            int drawableId = 0;
            switch (suiteId){
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
            ((ImageView) view.findViewById(R.id.iv_fr_home_suit)).setImageResource(drawableId);
        }
    }
}
