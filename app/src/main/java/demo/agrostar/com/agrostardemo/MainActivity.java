package demo.agrostar.com.agrostardemo;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import demo.agrostar.com.agrostardemo.adapter.ProductAdapter;
import demo.agrostar.com.agrostardemo.model.ProductDetails;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private ArrayList<ProductDetails> mProductDetails;
    private ProductAdapter contactAdapter;
    private ArrayList<ProductDetails> mOriginalProductDetails = new ArrayList<ProductDetails>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialiseUI();

    }

    private void initialiseUI() {
        mToolbar = (Toolbar) findViewById(R.id.tabanim_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        loadDummyData();

    }


    private void loadDummyData() {
        String[] productNames = getResources().getStringArray(R.array.products_name);
        String[] productRates = getResources().getStringArray(R.array.products_rate);

        Integer[] detailImage = {
                R.drawable.images, R.drawable.images1,
                R.drawable.images2, R.drawable.images3,
                R.drawable.images4, R.drawable.images5,
                R.drawable.images6, R.drawable.imagess,
                R.drawable.images4, R.drawable.images3
        };
        mProductDetails = new ArrayList<ProductDetails>();
        int size = productNames.length;

        for (int i = 0; i < size; i++) {
            ProductDetails productDetails = new ProductDetails();
            productDetails.setProductName(productNames[i]);
            productDetails.setProductRate(Double.parseDouble(productRates[i]));
            productDetails.setProductImages(Arrays.asList(detailImage));
            mProductDetails.add(productDetails);
        }
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
        contactAdapter = new ProductAdapter(MainActivity.this, (ArrayList<ProductDetails>) mProductDetails);
        mOriginalProductDetails.addAll(mProductDetails);
        mRecyclerView.setAdapter(contactAdapter);
        contactAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_search, menu);
        final MenuItem item = menu.findItem(R.id.search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Configuration config = new Configuration();
        Locale locale;

        int id = item.getItemId();
        switch (id) {
            case R.id.action_english:
                config.locale = Locale.ENGLISH;
                break;
            case R.id.action_japan:
                locale = new Locale("ja");
                config.locale = locale;
                break;
            default:
                config.locale = Locale.ENGLISH;
                break;
        }
        getResources().updateConfiguration(config, null);
        loadDummyData();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (newText.isEmpty()) {
            contactAdapter.animateTo(mOriginalProductDetails);
            mRecyclerView.scrollToPosition(0);
        } else {
            final List<ProductDetails> filteredModelList = filter(newText);
            contactAdapter.animateTo(filteredModelList);
            mRecyclerView.scrollToPosition(0);
        }
        return true;
    }

    private List<ProductDetails> filter(String query) {
        query = query.toLowerCase();
        final List<ProductDetails> filteredModelList = new ArrayList<>();
        for (ProductDetails model : mProductDetails) {
            final String text = model.getProductName().toLowerCase().trim();
            if (text.contains(query)) {
                filteredModelList.add(model);
            }
        }
        return filteredModelList;
    }
}
