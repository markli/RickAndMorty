package com.demo.rickandmorty;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.demo.rickandmorty.android.CharacterFragment;
import com.demo.rickandmorty.android.EpisodeFragment;
import com.demo.rickandmorty.android.LocationFragment;
import com.demo.rickandmorty.entity.ActionDefinition;
import com.demo.rickandmorty.entity.character.Character;
import com.demo.rickandmorty.entity.episode.Episode;
import com.demo.rickandmorty.entity.location.Location;
import com.demo.rickandmorty.model.impl.MainModel;
import com.demo.rickandmorty.presenter.MainPresenter;
import com.demo.rickandmorty.view.IMainView;

import java.util.List;

import common.utils.observer.IObserver;
import common.utils.observer.ISubject;
import common.utils.observer.ObserverAdapter;

public class MainActivity extends AppCompatActivity implements ISubject, IMainView {
    private Fragment[] mFragments;
    private int mLastFragment;
    private int mQueryPagePos[] = {1, 1, 1};
    MainPresenter mPresenter = null;

    private TextView mTextMessage;
    private ImageButton mButtonNext;
    private ImageButton mButtonPre;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
//                    mTextMessage.setText(R.string.title_home);
                    switchFragment(ActionDefinition.SOURCE_CHARACTER);
                    return true;
                case R.id.navigation_dashboard:
//                    mTextMessage.setText(R.string.title_dashboard);
                    switchFragment(ActionDefinition.SOURCE_LOCATION);
                    return true;
                case R.id.navigation_notifications:
//                    mTextMessage.setText(R.string.title_notifications);
                    switchFragment(ActionDefinition.SOURCE_EPISODE);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        initMVP();
        mTextMessage = (TextView) findViewById(R.id.title_tv);
        mButtonNext = (ImageButton) findViewById(R.id.bt_next);
        mButtonPre = (ImageButton) findViewById(R.id.bt_pre);
        mButtonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getNextPageData();

            }
        });
        mButtonPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPrePageData();
            }
        });
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        initFragment();
    }

    private void getNextPageData() {
        mQueryPagePos[mLastFragment]++;
        dataRequest();
    }

    private void getPrePageData() {
        mQueryPagePos[mLastFragment]--;
        dataRequest();
    }

    private void dataRequest() {
        Message msg = Message.obtain();
        switch (mLastFragment) {
            case ActionDefinition.SOURCE_LOCATION: {
                msg.what = ActionDefinition.ACTION_SHOW_LOCATION;
                break;
            }
            case ActionDefinition.SOURCE_EPISODE: {
                msg.what = ActionDefinition.ACTION_SHOW_EPISODE;
                break;
            }
            case ActionDefinition.SOURCE_CHARACTER:
            default: {
                msg.what = ActionDefinition.ACTION_SHOW_CHARACTER;
                break;
            }
        }
        msg.arg1 = mQueryPagePos[mLastFragment];
        this.notify(msg, FLAG_RUN_MAIN_THREAD);
    }

    private void initFragment() {
        mLastFragment = -1;
        mFragments = new Fragment[]{CharacterFragment.newInstance(3), LocationFragment.newInstance(0), EpisodeFragment.newInstance(0)};
        switchFragment(ActionDefinition.SOURCE_CHARACTER);
    }

    private void switchFragment(int index) {
        if (mLastFragment != index) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            if (mLastFragment >= 0)
                transaction.hide(mFragments[mLastFragment]);
            if (mFragments[index].isAdded() == false) {
                transaction.add(R.id.fragmentContainer, mFragments[index]);
            }
            transaction.show(mFragments[index]).commitAllowingStateLoss();
            mLastFragment = index;
            dataRequest();
        }
    }

    private void initMVP() {
        // TODO Auto-generated method stub
        MainModel model = new MainModel();
        mPresenter = new MainPresenter(model, this);
        this.attach(mPresenter);
        Message msg = Message.obtain();
        msg.what = ActionDefinition.ACTION_APP_LAUNCHED;
        this.notify(msg, FLAG_RUN_SYNC);
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        Message msg = Message.obtain();
        msg.what = ActionDefinition.ACTION_APP_EXIT;
        this.notify(msg, FLAG_RUN_SYNC);
    }

    /* (non-Javadoc)
     * @see common.utils.observer.ISubject#attach(common.utils.observer.IObserver)
     */
    @Override
    public boolean attach(IObserver inObserver) {
        return ObserverAdapter.getInstance().register(this, inObserver);
    }

    /* (non-Javadoc)
     * @see common.utils.observer.ISubject#detach(common.utils.observer.IObserver)
     */
    @Override
    public boolean detach(IObserver inObserver) {
        return ObserverAdapter.getInstance().unregister(this, inObserver);
    }

    /* (non-Javadoc)
     * @see common.utils.observer.ISubject#notify(android.os.Message, int)
     */
    @Override
    public void notify(Message inMessage, int... flag) {
        ObserverAdapter.getInstance().notify(this, inMessage, flag);
    }

    @Override
    public void showCharacters(List<Character> characters) {
        ((CharacterFragment) mFragments[0]).setData(characters);
    }

    @Override
    public void showLocations(List<Location> locations) {
        ((LocationFragment) mFragments[1]).setData(locations);

    }

    @Override
    public void showEpisodes(List<Episode> episodes) {
        ((EpisodeFragment) mFragments[2]).setData(episodes);

    }

    @Override
    public void updateInfo(int pagePos, int pageNum) {
        mTextMessage.setText("Page:" + pagePos + "/" + pageNum);
        mQueryPagePos[mLastFragment] = pagePos;

    }

    @Override
    public void releaseView() {

    }

}
