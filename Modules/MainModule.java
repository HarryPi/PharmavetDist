package com.pharmavet.imperial.pharmavetdist.Modules;

import com.pharmavet.imperial.pharmavetdist.Database.DatabasePopulator;
import com.pharmavet.imperial.pharmavetdist.Services.ImageRetriever;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {
    @Singleton
    @Provides
    public ImageRetriever providesImageEncoderDecoder(){
        return new ImageRetriever();
    }
    @Singleton
    @Provides
    public DatabasePopulator providesDatabasePopulator(){
        return new DatabasePopulator();
    }
}
