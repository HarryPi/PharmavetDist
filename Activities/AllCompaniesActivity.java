package com.pharmavet.imperial.pharmavetdist.Activities;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.pharmavet.imperial.pharmavetdist.Adapters.AllCompaniesAdapter;
import com.pharmavet.imperial.pharmavetdist.Adapters.RecyclerItemClickListener;
import com.pharmavet.imperial.pharmavetdist.App;
import com.pharmavet.imperial.pharmavetdist.Database.Models.Company;
import com.pharmavet.imperial.pharmavetdist.Database.Models.DisplayItems;
import com.pharmavet.imperial.pharmavetdist.R;
import com.pharmavet.imperial.pharmavetdist.ViewModels.CompanyViewModel;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.schedulers.Schedulers;

public class AllCompaniesActivity extends BaseActivity {
    @BindView(R.id.all_companies_progressBar)
    ProgressBar progressBar;
    @BindView(R.id.all_companies_recyclerView)
    RecyclerView recyclerView;
    @Inject
    CompanyViewModel companyViewModel;
    @Inject
    AllCompaniesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_companies);
        super.setupSideNavAndToolbar();
        ButterKnife.bind(this);
        ((App) getApplication()).getMainComponent().inject(this);
        showProgress(true);
        setupRecyclerView();


    }



    private void setupRecyclerView() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        companyViewModel.getCompanies()
                .subscribeOn(Schedulers.computation())
                .doOnSuccess((companies -> {
                    adapter.setCompanies(companies);
                    runOnUiThread( () -> {
                        recyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                        this.addRecyclerOnTouchEvent(companies);
                        showProgress(false);
                    });
                }))
        .subscribe();

    }
    private void addRecyclerOnTouchEvent(List<Company> companies) {
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        this,
                        recyclerView,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Intent intent = new Intent(AllCompaniesActivity.this, CompanyItemsActivity.class);
                                intent.putExtra("companyName", companies.get(position).getName());
                                startActivity(intent);
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {
                            }
                        }));
    }
    private void showProgress(boolean show) {
        if (show) {
            progressBar.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.INVISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }

}
