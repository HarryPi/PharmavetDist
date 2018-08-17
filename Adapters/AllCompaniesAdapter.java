package com.pharmavet.imperial.pharmavetdist.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pharmavet.imperial.pharmavetdist.Database.Models.Company;
import com.pharmavet.imperial.pharmavetdist.R;
import com.pharmavet.imperial.pharmavetdist.Util.Picasso.RoundedCornersTransform;
import com.pharmavet.imperial.pharmavetdist.ViewModels.CompanyViewModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AllCompaniesAdapter extends RecyclerView.Adapter<AllCompaniesAdapter.ViewHolder> {

    @Inject
    Picasso picasso;
    @Inject
    CompanyViewModel companyViewModel;

    private List<Company> companies = new ArrayList<>();

    @Inject
    public AllCompaniesAdapter() {
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.company_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Company company = companies.get(position);
        holder.companyName.setText(company.getName());
        picasso.load(company.getImageUrl())
                .transform(new RoundedCornersTransform())
                .placeholder(R.drawable.loading_animation)
                .fit()
                .into(holder.companyImage);
    }

    @Override
    public int getItemCount() {
        return companies.size();
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.company_image)
        ImageView companyImage;
        @BindView(R.id.company_name)
        TextView companyName;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }



}
