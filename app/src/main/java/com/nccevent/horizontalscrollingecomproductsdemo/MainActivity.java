package com.nccevent.horizontalscrollingecomproductsdemo;import android.app.ProgressDialog;import android.support.v7.app.AppCompatActivity;import android.os.Bundle;import android.support.v7.widget.LinearLayoutManager;import android.support.v7.widget.RecyclerView;import android.util.Log;import com.android.volley.AuthFailureError;import com.android.volley.Request;import com.android.volley.RequestQueue;import com.android.volley.Response;import com.android.volley.RetryPolicy;import com.android.volley.VolleyError;import com.android.volley.toolbox.StringRequest;import com.android.volley.toolbox.Volley;import com.nccevent.horizontalscrollingecomproductsdemo.Adaptors.ProductAdaptor;import com.nccevent.horizontalscrollingecomproductsdemo.Items.ProductsItem;import org.json.JSONArray;import org.json.JSONObject;import java.util.ArrayList;import java.util.HashMap;import java.util.List;import java.util.Map;public class MainActivity extends AppCompatActivity {    private ProgressDialog progressDialog;    private List<ProductsItem> productsItems = new ArrayList<ProductsItem>();    private ProductAdaptor productAdaptor;    private RecyclerView recyclerView;    private String TAG = MainActivity.class.getSimpleName();    private LinearLayoutManager linearLayoutManager;    @Override    protected void onCreate(Bundle savedInstanceState) {        super.onCreate(savedInstanceState);        setContentView(R.layout.activity_main);        recyclerView = findViewById(R.id.products);        linearLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);        recyclerView.setLayoutManager(linearLayoutManager);        productAdaptor = new ProductAdaptor(MainActivity.this, productsItems);        recyclerView.setAdapter(productAdaptor);        getProductsFromServer();    }    private void getProductsFromServer() {        try {            StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.SERVER_IP + Constants.SERVER_FOLDER + Constants.SERVER_FILE,                    new Response.Listener<String>() {                        @Override                        public void onResponse(String response) {                            // parse data from server and asign to the list view                            Log.e(TAG, response);                            try                            {                                JSONObject jsonObject = new JSONObject(response);                                JSONArray jsonArray = jsonObject.getJSONArray("productList");                                if(jsonArray!=null)                                {                                    for(int i=0;i<jsonArray.length();i++)                                    {                                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);                                        String name  =jsonObject1.getString("productName");                                        String imageurl = jsonObject1.getString("productImageUrl");                                        String farmername = jsonObject1.getString("region");                                        String stock = jsonObject1.getString("stock");                                        ProductsItem productsItem = new ProductsItem();                                        productsItem.setProductName(name);                                        productsItem.setFarmerName(farmername);                                        productsItem.setStock(stock);                                        productsItem.setProductImageUrl(imageurl);                                        productsItems.add(productsItem);                                    }                                }                                productAdaptor.notifyDataSetChanged();                            }catch (Exception ex)                            {                                Log.e(TAG, ""+ex.getLocalizedMessage());                            }                        }                    }, new Response.ErrorListener() {                @Override                public void onErrorResponse(VolleyError error) {                    Log.e(TAG,""+error.getLocalizedMessage());                }            }) {                @Override                protected Map<String, String> getParams() throws AuthFailureError {                    Map<String, String> map = new HashMap<>();                    map.put("orderid", "1");                    return map;                }            };            RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);            requestQueue.add(stringRequest);            stringRequest.setRetryPolicy(new RetryPolicy() {                @Override                public int getCurrentTimeout() {                    return 0;                }                @Override                public int getCurrentRetryCount() {                    return 0;                }                @Override                public void retry(VolleyError error) throws VolleyError {                }            });        } catch (Exception ex) {            Log.e(TAG, "" + ex.getLocalizedMessage());        }    }}