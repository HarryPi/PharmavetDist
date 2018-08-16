package com.pharmavet.imperial.pharmavetdist.Components;

import com.pharmavet.imperial.pharmavetdist.Activities.BaseActivity;
import com.pharmavet.imperial.pharmavetdist.Modules.AppModule;
import com.pharmavet.imperial.pharmavetdist.Modules.MainModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, MainModule.class})
public interface MainComponent {
    void inject(BaseActivity baseActivity);
}
