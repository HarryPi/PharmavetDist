package com.pharmavet.imperial.pharmavetdist.Database;

import android.support.annotation.NonNull;

import com.pharmavet.imperial.pharmavetdist.Database.Models.DisplayItems;
import com.pharmavet.imperial.pharmavetdist.Services.ImageRetriever;

import java.util.List;

import javax.inject.Inject;

public class DatabasePopulator {

    @NonNull
    public static DisplayItems[] populateData() {
        return new DisplayItems[]{
                new DisplayItems("acetylcarnitinealphalipoicacidcapules", null,
                        null, 0, 10, null, null,
                        ImageRetriever.getUrl("acetylcarnitinealphalipoicacidcapules").toString(), "Acetyl Carnitine Alpha Lipoic Acid Capules"),
                new DisplayItems("htpcapsules", null,
                        null, 0, 10, null, null,
                        ImageRetriever.getUrl("htpcapsules").toString(), "HTP Capsules"),
        };
    }

}
