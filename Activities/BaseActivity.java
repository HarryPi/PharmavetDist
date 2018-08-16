package com.pharmavet.imperial.pharmavetdist.Activities;

import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;

import com.pharmavet.imperial.pharmavetdist.App;
import com.pharmavet.imperial.pharmavetdist.Database.DatabasePopulator;
import com.pharmavet.imperial.pharmavetdist.Database.ItemsDatabase;
import com.pharmavet.imperial.pharmavetdist.Database.Models.DisplayItems;
import com.pharmavet.imperial.pharmavetdist.Database.Queries.DisplayItemsRepository;
import com.pharmavet.imperial.pharmavetdist.R;
import com.pharmavet.imperial.pharmavetdist.Services.ImageRetriever;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class BaseActivity extends AppCompatActivity {

    @Inject
    ImageRetriever imageRetriever;
    @Inject
    DisplayItemsRepository displayItemsRepository;
    @BindView(R.id.imageView)
    ImageView imageView;
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        ButterKnife.bind(this);
        ((App) getApplication()).getMainComponent().inject(this);
        final List<DisplayItems> items = new ArrayList<>();
        displayItemsRepository.getAll()
                .subscribeOn(Schedulers.computation())
                .doOnSuccess((s) -> {
                    Log.d("DB TEST", s.get(0).getImageUrl());
                    imageView.setImageURI(Uri.parse(s.get(0).getImageUrl()));
                })
                .subscribe((i) -> items.addAll(i));
        ;
    }


    public void setupSideNavAndToolbar() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.mipmap.ic_menu);
    }
}
