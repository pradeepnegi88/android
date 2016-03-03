package demo.agrostar.com.agrostardemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import demo.agrostar.com.agrostardemo.adapter.ProductDetailsAdapter;
import demo.agrostar.com.agrostardemo.model.ProductDetails;

/**
 * Created by prade on 02-03-2016.
 */
public class ProductDetailActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private ProductDetails productDetails;
    private ProductDetailsAdapter contactAdapter;
    public static final String BUNDLE_TAG_PRODUCT_DETAIL = "product_detail";
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Bundle b = getIntent().getExtras();
        if (b != null) {
            productDetails = (ProductDetails) b.getSerializable(BUNDLE_TAG_PRODUCT_DETAIL);
            if (productDetails != null) {
                mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
                mRecyclerView.setHasFixedSize(true);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(ProductDetailActivity.this, LinearLayoutManager.HORIZONTAL, false));
                contactAdapter = new ProductDetailsAdapter(ProductDetailActivity.this, productDetails.getProductImages());
                mRecyclerView.setAdapter(contactAdapter);
                collapsingToolbarLayout.setTitle(productDetails.getProductName());
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
