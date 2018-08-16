package com.pharmavet.imperial.pharmavetdist;

import android.app.Application;

import com.pharmavet.imperial.pharmavetdist.Components.DaggerMainComponent;
import com.pharmavet.imperial.pharmavetdist.Components.MainComponent;
import com.pharmavet.imperial.pharmavetdist.Modules.AppModule;
import com.pharmavet.imperial.pharmavetdist.Modules.MainModule;

public class App extends Application {
    private MainComponent mainComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mainComponent = DaggerMainComponent.builder()
                .appModule(new AppModule(this))
                .mainModule(new MainModule())
                .build();
    }

    public MainComponent getMainComponent() {
        return mainComponent;
    }
}
