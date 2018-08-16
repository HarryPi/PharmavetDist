package com.pharmavet.imperial.pharmavetdist.Modules;

import android.app.Application;
import android.support.annotation.Nullable;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    public Application application;

    public AppModule(@Nullable Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public Application providesApplication() {
        return this.application;
    }
}
