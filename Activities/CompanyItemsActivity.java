package com.pharmavet.imperial.pharmavetdist.Activities;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

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

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.schedulers.Schedulers;

@RequiresApi(api = Build.VERSION_CODES.N)
public class CompanyItemsActivity extends BaseActivity implements DiscreteScrollView.OnItemChangedListener {
    private static final String TAG = CompanyItemsActivity.class.getSimpleName();
    @BindView(R.id.item_search)
    SearchView searchView;
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
    private String companyName = "";
    private DisplayItems displayitem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_items);
        super.setupSideNavAndToolbar();

        ((App) getApplication()).getMainComponent().inject(this);
        ButterKnife.bind(this);
        setupScrollView();
        // Get the intent, verify the action and get the query
        searchProducts(getIntent());
        searchView.setOnCloseListener(() -> {
            setupScrollView();
            return false;
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_bar_options, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.edit_item:
               // Toast.makeText(this, "I am editing " + displayitem.getName() , Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(CompanyItemsActivity.this, EditItemActivity.class);
                intent.putExtra("itemId", displayitem.getName());
                intent.putExtra("companyId", displayitem.getCompanyName());
                startActivity(intent);
                break;
            case R.id.delete_item:
                Toast.makeText(this, "Item deleted", Toast.LENGTH_SHORT).show();
                Intent deleteIntent = new Intent(CompanyItemsActivity.this, CompanyItemsActivity.class);
                deleteIntent.putExtra("companyName", displayitem.getCompanyName());
                displayItemsViewModel.delete(displayitem);
                // todo: for now as this but item list should be observable to track this changes!
                finish();
                startActivity(deleteIntent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        searchProducts(intent);
    }

    private void searchProducts(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = "%" + intent.getStringExtra(SearchManager.QUERY) + "%";
            items = new ArrayList<>();
            // Do work using string
            displayItemsViewModel
                    .findAllProductsForCompanyWithProductName(companyName, query)
                    .subscribeOn(Schedulers.computation())
                    .doOnSuccess(i -> items = i)
                    .doAfterSuccess((i) -> runOnUiThread(this::initScrollView))
                    .subscribe();

        }

    }

    private void setupScrollView() {
        items = new ArrayList<>();
        if (companyName == null) {
            companyName = getIntent().getStringExtra("companyName");
        }
        if (companyName.equals("")) {
            companyName = getIntent().getStringExtra("companyName");
        }
        displayItemsViewModel
                .findAllProductsForCompany(companyName)
                .subscribeOn(Schedulers.computation())
                .doOnSuccess(i -> items = i)
                .doAfterSuccess((i) -> runOnUiThread(this::initScrollView))
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
        displayitem = items.get(positionInDataSet);
        productName.setText(displayitem.getProductName());
        productType.setText(displayitem.getProductType());
        ingredientDescription.setText(displayitem.getIngredientDescription());
        additionalInfo.setText(displayitem.getAdditionalInfo());
        description.setText(displayitem.getDescription());
        recommendedIntake.setText(displayitem.getRecommendedIntake());
        warnings.setText(displayitem.getWarnings());
        size.setText(displayitem.getSize());
    }
}
