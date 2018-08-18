package com.pharmavet.imperial.pharmavetdist.Activities;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.pharmavet.imperial.pharmavetdist.Adapters.ItemsAdapter;
import com.pharmavet.imperial.pharmavetdist.App;
import com.pharmavet.imperial.pharmavetdist.Database.Models.DisplayItems;
import com.pharmavet.imperial.pharmavetdist.R;
import com.pharmavet.imperial.pharmavetdist.ViewModels.DisplayItemsViewModel;
import com.squareup.picasso.Picasso;
import com.yarolegovich.discretescrollview.DSVOrientation;
import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.InfiniteScrollAdapter;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.schedulers.Schedulers;

public class CompanyItemsActivity extends BaseActivity implements DiscreteScrollView.OnItemChangedListener {
    @BindView(R.id.product_name)
    TextView productName;
    @BindView(R.id.product_type)
    TextView productType;
    @BindView(R.id.product_ingredient_description)
    TextView ingredientDescription;
    @BindView(R.id.product_additional_info)
    TextView additionalInfo;
    @BindView(R.id.product_description)
    TextView description;
    @BindView(R.id.product_recommended_intake)
    TextView recommendedIntake;
    @BindView(R.id.product_warning)
    TextView warnings;
    @BindView(R.id.product_size)
    TextView size;
    @Inject
    Picasso picasso;
    @Inject
    DisplayItemsViewModel
            displayItemsViewModel;

    @BindView(R.id.all_items_scroll_view)
    DiscreteScrollView itemPicker;
    private InfiniteScrollAdapter infiniteAdapter;
    private List<DisplayItems> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_items);
        super.setupSideNavAndToolbar();

        ((App) getApplication()).getMainComponent().inject(this);
        ButterKnife.bind(this);
        setupScrollView();

    }

    private void setupScrollView() {
        displayItemsViewModel
                .findAllProductsForCompany(getIntent().getStringExtra("companyName"))
                .subscribeOn(Schedulers.computation())
                .doOnSuccess(i -> items = i)
                .doAfterSuccess((i) -> initScrollView())
                .subscribe();


    }

    void initScrollView() {
        itemPicker.setOrientation(DSVOrientation.HORIZONTAL);
        itemPicker.addOnItemChangedListener(this);
        infiniteAdapter = InfiniteScrollAdapter.wrap(new ItemsAdapter(items, picasso));
        itemPicker.setAdapter(infiniteAdapter);
        itemPicker.setItemTransitionTimeMillis(100);
        itemPicker.setItemTransformer(new ScaleTransformer.Builder()
                .setMinScale(0.3f)
                .setMaxScale(2f)
                .build());
    }

    @Override
    public void onCurrentItemChanged(@Nullable RecyclerView.ViewHolder viewHolder, int adapterPosition) {
        int positionInDataSet = infiniteAdapter.getRealPosition(adapterPosition);
        DisplayItems item = items.get(positionInDataSet);
        productName.setText(item.getProductName());
        productType.setText(item.getProductType());
        ingredientDescription.setText(item.getIngredientDescription());
        additionalInfo.setText(item.getAdditionalInfo());
        description.setText(item.getDescription());
        recommendedIntake.setText(item.getRecommendedIntake());
        warnings.setText(item.getWarnings());
        size.setText(item.getSize());
    }
}
