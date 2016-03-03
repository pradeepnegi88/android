package demo.agrostar.com.agrostardemo.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import demo.agrostar.com.agrostardemo.ProductDetailActivity;
import demo.agrostar.com.agrostardemo.R;
import demo.agrostar.com.agrostardemo.model.ProductDetails;


/**
 * Created by prade on 02-12-2015.
 */
public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.DataObjectHolder> {

    private final Context context;
    private ArrayList<ProductDetails> mProductDetails;

    public class DataObjectHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView productName;
        private TextView productRate;
        private ImageView productImage;

        public DataObjectHolder(View itemView) {
            super(itemView);
            productName = (TextView) itemView.findViewById(R.id.productName);
            productRate = (TextView) itemView.findViewById(R.id.productRate);
            productImage = (ImageView) itemView.findViewById(R.id.productImage);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(v.getContext(), ProductDetailActivity.class);
            intent.putExtra(ProductDetailActivity.BUNDLE_TAG_PRODUCT_DETAIL, (Serializable) mProductDetails.get(getAdapterPosition()));
            v.getContext().startActivity(intent);
        }
    }


    public ProductAdapter(Context context, ArrayList<ProductDetails> productDetails) {
        mProductDetails = productDetails;
        this.context = context;

    }


    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_product_show, parent, false);
        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
        holder.productName.setText(mProductDetails.get(position).getProductName());
        holder.productRate.setText(context.getString(R.string.rupees_symbol) + mProductDetails.get(position).getProductRate());
        holder.productImage.setImageResource(mProductDetails.get(position).getProductImages().get(position));
    }


    @Override
    public int getItemCount() {
        return mProductDetails.size();
    }
    public interface listenerForInvitation {
        void invitation(String pid);
    }

    public void animateTo(List<ProductDetails> models) {
        applyAndAnimateRemovals(models);
        applyAndAnimateAdditions(models);
        applyAndAnimateMovedItems(models);
    }

    private void applyAndAnimateRemovals(List<ProductDetails> newModels) {
        for (int i = mProductDetails.size() - 1; i >= 0; i--) {
            final ProductDetails model = mProductDetails.get(i);
            if (!newModels.contains(model)) {
                removeItem(i);
            }
        }
    }

    private void applyAndAnimateAdditions(List<ProductDetails> newModels) {
        for (int i = 0, count = newModels.size(); i < count; i++) {
            final ProductDetails model = newModels.get(i);
            if (!mProductDetails.contains(model)) {
                addItem(i, model);
            }
        }
    }

    private void applyAndAnimateMovedItems(List<ProductDetails> newModels) {
        for (int toPosition = newModels.size() - 1; toPosition >= 0; toPosition--) {
            final ProductDetails model = newModels.get(toPosition);
            final int fromPosition = mProductDetails.indexOf(model);
            if (fromPosition >= 0 && fromPosition != toPosition) {
                moveItem(fromPosition, toPosition);
            }
        }
    }

    public ProductDetails removeItem(int position) {
        final ProductDetails model = mProductDetails.remove(position);
        notifyItemRemoved(position);
        return model;
    }

    public void addItem(int position, ProductDetails model) {
        mProductDetails.add(position, model);
        notifyItemInserted(position);
    }

    public void moveItem(int fromPosition, int toPosition) {
        final ProductDetails model = mProductDetails.remove(fromPosition);
        mProductDetails.add(toPosition, model);
        notifyItemMoved(fromPosition, toPosition);
    }

}