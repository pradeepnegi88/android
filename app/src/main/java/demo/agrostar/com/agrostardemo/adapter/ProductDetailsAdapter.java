package demo.agrostar.com.agrostardemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import demo.agrostar.com.agrostardemo.R;


/**
 * Created by prade on 02-12-2015.
 */
public class ProductDetailsAdapter extends RecyclerView.Adapter<ProductDetailsAdapter.DataObjectHolder> {
    private final Context context;
    private List<Integer> mProductPhoto;

    public class DataObjectHolder extends RecyclerView.ViewHolder {
        private ImageView productImage;

        public DataObjectHolder(View itemView) {
            super(itemView);
            productImage = (ImageView) itemView.findViewById(R.id.productImage);

        }
    }

    public ProductDetailsAdapter(Context context, List<Integer> productPhoto) {
        mProductPhoto = productPhoto;
        this.context = context;
    }


    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_product_details, parent, false);
        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
        holder.productImage.setImageResource(mProductPhoto.get(position));
    }


    @Override
    public int getItemCount() {
        return mProductPhoto.size();
    }


}