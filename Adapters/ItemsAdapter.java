package com.pharmavet.imperial.pharmavetdist.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pharmavet.imperial.pharmavetdist.Database.Models.DisplayItems;
import com.pharmavet.imperial.pharmavetdist.R;
import com.pharmavet.imperial.pharmavetdist.Services.RoundedCornersTransform;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {

    Picasso picasso;

    private List<DisplayItems> displayItems;

    public ItemsAdapter(List<DisplayItems> displayItems, Picasso picasso) {
        this.displayItems = displayItems;
        this.picasso = picasso;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.items_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DisplayItems item = displayItems.get(position);

        picasso.load(item.getImageUrl())
                .transform(new RoundedCornersTransform())
                .placeholder(R.drawable.loading_animation)
                .fit()
                .into(holder.item);
    }

    @Override
    public int getItemCount() {
        return displayItems.size();
    }

    public void setDisplayItems(List<DisplayItems> items) {
        this.displayItems = items;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.item_image)
        ImageView item;


        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }



}
