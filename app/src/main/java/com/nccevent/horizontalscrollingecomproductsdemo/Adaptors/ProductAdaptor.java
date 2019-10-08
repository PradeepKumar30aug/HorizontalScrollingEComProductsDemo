package com.nccevent.horizontalscrollingecomproductsdemo.Adaptors;import android.content.Context;import android.support.annotation.NonNull;import android.support.v7.widget.RecyclerView;import android.view.LayoutInflater;import android.view.View;import android.view.ViewGroup;import android.widget.Button;import android.widget.ImageView;import android.widget.LinearLayout;import android.widget.TextView;import android.widget.Toast;import com.bumptech.glide.Glide;import com.nccevent.horizontalscrollingecomproductsdemo.Items.ProductsItem;import com.nccevent.horizontalscrollingecomproductsdemo.R;import java.util.List;public class ProductAdaptor extends RecyclerView.Adapter<ProductAdaptor.ViewHolder> {    List<ProductsItem> productsItems;    Context context;    public ProductAdaptor(Context context, List<ProductsItem> productsItems)    {        this.context = context;        this.productsItems = productsItems;    }    @NonNull    @Override    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {        View view = LayoutInflater.from(context).inflate(R.layout.single_product_design,viewGroup, false);        return new ProductAdaptor.ViewHolder(view);    }    @Override    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {            final ProductsItem productsItem = productsItems.get(i);            viewHolder.productname.setText(productsItem.getProductName());        Glide.with(context).load(productsItem.getProductImageUrl()).into(viewHolder.productImage);        viewHolder.stock.setText(productsItem.getStock());        viewHolder.farmername.setText(productsItem.getFarmerName());        // add listener on single Item        viewHolder.btnAdd.setOnClickListener(new View.OnClickListener() {            @Override            public void onClick(View v) {                Toast.makeText(context, "Clicked", Toast.LENGTH_LONG).show();            }        });        viewHolder.btnPlus.setOnClickListener(new View.OnClickListener() {            @Override            public void onClick(View v) {                // apply code to increment the number                // first of all get the value form text counter and increment after that bind on the UI            }        });        viewHolder.btnMinus.setOnClickListener(new View.OnClickListener() {            @Override            public void onClick(View v) {                // first of all get the value form text counter and decrement after that bind on the UI            }        });    }    @Override    public long getItemId(int position) {        return position;    }    @Override    public int getItemViewType(int position) {        return position;    }    @Override    public int getItemCount() {        return productsItems.size();    }    public class ViewHolder extends RecyclerView.ViewHolder    {        TextView productname;        ImageView productImage;        TextView stock, farmername, specility,qty;        Button btnPlus, btnMinus;        LinearLayout btnAdd;        public ViewHolder(@NonNull View itemView)        {            super(itemView);            productname = itemView.findViewById(R.id.productname);            productImage = itemView.findViewById(R.id.productimage);            stock = itemView.findViewById(R.id.stcokValue);            farmername = itemView.findViewById(R.id.farmerName);            qty = itemView.findViewById(R.id.qty);            specility = itemView.findViewById(R.id.specility);            btnPlus = itemView.findViewById(R.id.plusbutton);            btnMinus = itemView.findViewById(R.id.minusbtn);            btnAdd = itemView.findViewById(R.id.add_button);        }    }}