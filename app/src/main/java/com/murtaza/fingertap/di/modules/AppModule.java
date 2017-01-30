package com.murtaza.fingertap.di.modules;

import android.content.pm.PackageManager;

import com.murtaza.fingertap.FingerTap;

import java.util.Random;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by murtaza on 30/1/17.
 */

@Module
public class AppModule {

    private FingerTap app;

    public AppModule(FingerTap app){

        this.app = app;
    }

    @Provides
    @Singleton
    public Random provideRandom(){
        return new Random();
    }

    @Provides
    @Singleton
    public PackageManager providePackageManager(){
        return app.getPackageManager();
    }
}
