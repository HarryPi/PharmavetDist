package com.pharmavet.imperial.pharmavetdist.Modules;

import android.app.Application;
import android.support.annotation.Nullable;

import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

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

    @Singleton
    @Provides
    public Picasso providesPicasso(Application application) {
        return new Picasso.Builder(application.getApplicationContext())
                .build();
    }
}
