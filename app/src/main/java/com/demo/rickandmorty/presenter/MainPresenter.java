package com.demo.rickandmorty.presenter;

import android.os.Message;
import android.util.Log;

import com.demo.rickandmorty.entity.ActionDefinition;
import com.demo.rickandmorty.model.IMainModel;
import com.demo.rickandmorty.view.IMainView;

import common.utils.observer.IObserver;
import common.utils.observer.ISubject;

public class MainPresenter implements IObserver {
    IMainModel mModel = null;
    IMainView mView = null;
    private int mLoadState[] = {ActionDefinition.STATE_FINISHED, ActionDefinition.STATE_FINISHED, ActionDefinition.STATE_FINISHED};

    public MainPresenter(IMainModel model, IMainView view) {
        super();
        this.mModel = model;
        this.mView = view;
    }

    /* (non-Javadoc)
     * @see common.utils.observer.IObserver#listen(android.os.Message)
     */
    @Override
    public void listen(Message inMessage) {
        // TODO Auto-generated method stub
        Log.e("MainPresenter1", "inMessage.what:" + inMessage.what);

        switch (inMessage.what) {
            case ActionDefinition.ACTION_APP_LAUNCHED: {
                init();
                break;
            }
            case ActionDefinition.ACTION_APP_EXIT: {
                exit();
                break;
            }
            case ActionDefinition.ACTION_SHOW_CHARACTER: {
                if (mLoadState[ActionDefinition.SOURCE_CHARACTER] != ActionDefinition.STATE_LOADING) {
                    int page = (inMessage.arg1 < 0) ? 1 : inMessage.arg1;
                    mModel.requestCharacterData(page);
                }
                break;
            }
            case ActionDefinition.ACTION_SHOW_LOCATION: {
                if (mLoadState[ActionDefinition.SOURCE_LOCATION] != ActionDefinition.STATE_LOADING) {
                    int page = (inMessage.arg1 < 0) ? 1 : inMessage.arg1;
                    mModel.requestLocationData(page);
                }
                break;
            }
            case ActionDefinition.ACTION_SHOW_EPISODE: {
                if (mLoadState[ActionDefinition.SOURCE_EPISODE] != ActionDefinition.STATE_LOADING) {
                    int page = (inMessage.arg1 < 0) ? 1 : inMessage.arg1;
                    mModel.requestEpisodeData(page);
                }
                break;
            }
            case ActionDefinition.ACTION_DATA_CHANGED: {
                mLoadState[inMessage.arg1] = inMessage.arg2;
                if (mLoadState[inMessage.arg1] == ActionDefinition.STATE_FINISHED) {
                    if (inMessage.arg1 == ActionDefinition.SOURCE_CHARACTER) {
                        mView.showCharacters(mModel.getCharacterData());
                    } else if (inMessage.arg1 == ActionDefinition.SOURCE_LOCATION) {
                        mView.showLocations(mModel.getLocationData());
                    } else if (inMessage.arg1 == ActionDefinition.SOURCE_EPISODE) {
                        mView.showEpisodes(mModel.getEpisodeData());
                    }
                    mView.updateInfo(mModel.getPagePos(inMessage.arg1), mModel.getPageNum(inMessage.arg1));
                }
                break;
            }
        }
    }

    private void exit() {
        // TODO Auto-generated method stub
        ((ISubject) mModel).detach(this);
        ((ISubject) mView).detach(this);
        mModel.releaseModel();
        mView.releaseView();
    }

    private void init() {
        // TODO Auto-generated method stub
        ((ISubject) mModel).attach(this);
        ((ISubject) mView).attach(this);
    }
}
