package com.murtaza.fingertap;

import android.app.Application;

import com.murtaza.fingertap.di.components.AppComponent;
import com.murtaza.fingertap.di.components.DaggerAppComponent;
import com.murtaza.fingertap.di.modules.AppModule;

/**
 * Created by murtaza on 30/1/17.
 */

public class FingerTap extends Application {

    private AppComponent applicationComponent;


    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerAppComponent.builder()
            .appModule(new AppModule(this))
            .build();
        applicationComponent.inject(this);
    }

    public AppComponent getAppComponent(){
        return applicationComponent;
    }
}
