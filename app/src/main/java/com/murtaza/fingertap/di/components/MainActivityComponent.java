package com.murtaza.fingertap.di.components;

import com.murtaza.fingertap.views.MainActivity;
import com.murtaza.fingertap.di.modules.MainActivityModule;
import com.murtaza.fingertap.di.scopes.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by murtaza on 30/1/17.
 */

@ActivityScope
@Subcomponent(
    modules = {
        MainActivityModule.class
    }
)

public interface MainActivityComponent {
    void inject(MainActivity mainActivity);
}
