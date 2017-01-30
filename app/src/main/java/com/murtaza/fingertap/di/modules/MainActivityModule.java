package com.murtaza.fingertap.di.modules;

import android.content.Context;
import android.widget.GridLayout;

import com.murtaza.fingertap.di.scopes.ActivityScope;
import com.murtaza.fingertap.presenters.MainPresenter;
import com.murtaza.fingertap.presenters.MainPresenterImpl;
import com.murtaza.fingertap.views.MainView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by murtaza on 30/1/17.
 */

@Module
public class MainActivityModule {


    private MainView mainView;

    public MainActivityModule(MainView mainView){

        this.mainView = mainView;
    }

    @Provides
    @ActivityScope
    public MainPresenter provideMainPresenter(){
        return new MainPresenterImpl(mainView);
    }

}
