package net.amigochi.amigochi.features.home;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import net.amigochi.amigochi.BuildConfig;
import net.amigochi.amigochi.R;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

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
            ((ImageView) view.findViewById(R.id.iv_fr_home_suit)).setImageResource(drawableId);
        }

//        Observable.range(0, 74)
//                .delay(2, TimeUnit.SECONDS)
//                .observeOn(Schedulers.newThread())
//                .subscribeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Action1<Integer>() {
//                    @Override
//                    public void call(Integer integer) {
//                        if (getView() != null) {
//                            ((ProgressBar) getView().findViewById(R.id.pb_fr_home)).setProgress(integer);
//                        }
//                    }
//                }, new Action1<Throwable>() {
//                    @Override
//                    public void call(Throwable throwable) {
//                        throwable.printStackTrace();
//                    }
//                });
    }
}
