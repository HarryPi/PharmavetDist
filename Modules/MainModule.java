package com.pharmavet.imperial.pharmavetdist.Modules;


import com.pharmavet.imperial.pharmavetdist.Util.ImageHandlers.ImageRetriever;

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
}
