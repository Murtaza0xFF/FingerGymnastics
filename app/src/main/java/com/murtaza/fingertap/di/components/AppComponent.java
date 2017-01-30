package com.murtaza.fingertap.di.components;

import com.murtaza.fingertap.FingerTap;
import com.murtaza.fingertap.di.modules.MainActivityModule;
import com.murtaza.fingertap.di.modules.AppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by murtaza on 30/1/17.
 */
@Component(modules = {
    AppModule.class,
})
@Singleton
public interface AppComponent {

    void inject(FingerTap app);
    MainActivityComponent plus(MainActivityModule mainActivityModule);
}
