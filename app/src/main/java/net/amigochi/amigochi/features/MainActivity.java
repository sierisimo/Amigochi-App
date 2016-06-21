package net.amigochi.amigochi.features;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import net.amigochi.amigochi.R;
import net.amigochi.amigochi.features.home.HomeFragment;
import net.amigochi.amigochi.features.statusbar.OnStatusChange;
import net.amigochi.amigochi.features.statusbar.StatusBarFragment;
import net.amigochi.amigochi.features.store.StoreFragment;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by sierisimo on 20/06/16.
 */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private DrawerLayout mDrawer;

    private OnStatusChange onStatusChangeListener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupToolbar();

        mDrawer = (DrawerLayout) findViewById(R.id.dl_ac_home);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nv_ac_home);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                return onOptionsItemSelected(item);
            }
        });

        StatusBarFragment statusBarFragment = new StatusBarFragment();
        onStatusChangeListener = statusBarFragment;

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fl_ac_main_status, statusBarFragment)
                .replace(R.id.fl_ac_home_content, new HomeFragment())
                .commit();

        Observable.from(new Integer[]{20, 30, 40, 50, 10, -20, -30, 13})
                .delay(2, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        if (integer < 0) {
                            onStatusChangeListener.onPointsReduced(integer);
                        } else {
                            onStatusChangeListener.onPointsAdded(integer);
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                    }
                });

        Observable.from(new Double[]{20.50, 10.30, 15.20, 90.80, -10.10, -14.13})
                .delay(3, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Action1<Double>() {
                    @Override
                    public void call(Double aDouble) {
                        if (aDouble < 0) {
                            onStatusChangeListener.onMoneyRemoved(aDouble);
                        } else {
                            onStatusChangeListener.onMoneyAdded(aDouble);
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                    }
                });
    }

    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.tb_ac_general);

        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.vector_menu);
            actionBar.setTitle(R.string.title_activity_home);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawer.openDrawer(GravityCompat.START);
                return true;
            case R.id.action_amigochi:
                if (!(getSupportFragmentManager().findFragmentById(R.id.fl_ac_home_content) instanceof HomeFragment)) {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fl_ac_home_content, new HomeFragment())
                            .commit();
                }
                break;
            case R.id.action_pelota:

                break;
            case R.id.action_store:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fl_ac_home_content, new StoreFragment())
                        .commit();
                break;
        }

        item.setChecked(true);
        mDrawer.closeDrawers();

        return true;
    }
}
