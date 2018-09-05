package com.pharmavet.imperial.pharmavetdist.Activities;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.pharmavet.imperial.pharmavetdist.App;
import com.pharmavet.imperial.pharmavetdist.Database.Models.DisplayItems;
import com.pharmavet.imperial.pharmavetdist.R;
import com.pharmavet.imperial.pharmavetdist.ViewModels.DisplayItemsViewModel;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.schedulers.Schedulers;

public class EditItemActivity extends BaseActivity {

    @Inject
    DisplayItemsViewModel displayItemsViewModel;
    @BindView(R.id.edit_additional_info)
    EditText additionalInfo;
    @BindView(R.id.edit_ingredient_description)
    EditText ingredients;
    @BindView(R.id.edit_name)
    EditText name;
    @BindView(R.id.edit_recommended_intake)
    EditText intake;
    @BindView(R.id.edit_warnings)
    EditText warnings;
    @BindView(R.id.edit_product_description)
    EditText description;

    private static final String TAG = EditItemActivity.class.getSimpleName();
    private DisplayItems itemToEdit;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.edit_item_options, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.edit_item_save:
                DisplayItems editedItem = itemToEdit;

                editedItem.setAdditionalInfo(additionalInfo.getText().toString());
                editedItem.setIngredientDescription(ingredients.getText().toString());
                editedItem.setProductName(name.getText().toString());
                editedItem.setRecommendedIntake(intake.getText().toString());
                editedItem.setWarnings(warnings.getText().toString());
                editedItem.setDescription(description.getText().toString());

                displayItemsViewModel.changeItemWithId(editedItem);
                Toast.makeText(this, "Item updated successfully", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this, CompanyItemsActivity.class);
                intent.putExtra("companyName", itemToEdit.getCompanyName());
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        super.setupSideNavAndToolbar();

        ButterKnife.bind(this);
        ((App) getApplication()).getMainComponent().inject(this);
        String itemId = getIntent()
                .getStringExtra("itemId");
        String companyId = getIntent().getStringExtra("companyId");
        displayItemsViewModel.getSingle(itemId, companyId)
                .subscribeOn(Schedulers.io())
                .doAfterSuccess(i -> {
                    additionalInfo.setText(i.getAdditionalInfo());
                    ingredients.setText(i.getIngredientDescription());
                    name.setText(i.getProductName());
                    intake.setText(i.getRecommendedIntake());
                    warnings.setText(i.getWarnings());
                    description.setText(i.getDescription());
                    itemToEdit = i;
                })
                .subscribe();

    }
}
