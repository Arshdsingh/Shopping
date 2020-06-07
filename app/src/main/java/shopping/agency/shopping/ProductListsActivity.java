package shopping.agency.shopping;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

import shopping.agency.shopping.adapters.ProductRecyclerAdapter;
import shopping.agency.shopping.adapters.ProductRecyclerAdapter1;

public class ProductListsActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    String product_id;
    Button placeorder;
    Context context;
    ArrayList<HashMap<String,String>> arrayList=new ArrayList<>();
    int category=1;
    LinkedList<ProductItem> arr=new LinkedList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_lists);
        context=this;
        if(getIntent().getStringExtra("category")!=null)
        {
            category=Integer.parseInt(getIntent().getStringExtra("category"));
        }
        else
        {
            category=1;
        }
        TextView textView=(TextView)findViewById(R.id.name);
        placeorder=(Button) findViewById(R.id.placeorder);
        recyclerView=(RecyclerView)findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //System.out.println("postSnapshot.getKey() = " + postSnapshot.getKey());
                    if(postSnapshot.getKey().contains("return"))
                    {
                       arrayList= (  ArrayList<HashMap<String,String>>)postSnapshot.getValue();
                        System.out.println("arrayList = " + arrayList.toString());
                       // arr=new Gson().fromJson(arrayList.toString(),new TypeToken<LinkedList<ProductItem>>(){}.getType());
                        //System.out.println("users1 = " + arrayList.get(0).getTitle());
                        //System.out.println("users2 = " + productItem.getPrice());
                        //arr.add(productItem);
                        //arr=new LinkedList<ProductItem>(arrayList);

                    }
                }
              for (int i=0;i<arrayList.size();i++){
                  HashMap<String,String > keys=new HashMap<>(arrayList.get(i));
                    if(keys.get("category").equalsIgnoreCase(category+"")) {
                        ProductItem productItem = new ProductItem();
                        productItem = new ProductItem();
                        productItem.setId(keys.get("id"));
                        productItem.setDescription(keys.get("description"));
                        productItem.setTitle(keys.get("title"));
                        productItem.setPrice(keys.get("price"));
                        productItem.setProduct_id(keys.get("product_id"));
                        productItem.setImageurl(keys.get("imageurl"));
                      //  productItem.setNoofitems(Integer.parseInt(keys.get("noofitems")));

                        arr.add(productItem);
                    }
                  
              }
                ProductRecyclerAdapter adapter=new ProductRecyclerAdapter(context,arr);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Product List", "Failed to load value.", error.toException());
            }
        });

        placeorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (arr.size()>0) {
                    // new SqliteDatabaseDb(context).deleteAll();
                    Intent intent=new Intent(ProductListsActivity.this,OrderListActivity.class);
                    startActivity(intent);
                    //   ((Activity) context).finish();
                }
                else {
                    Toast.makeText(context, "Cart is empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
        initialize();
    }
    public void initialize()
    {
        ActionBar actionBar;

        actionBar = getSupportActionBar();
        actionBar.setTitle(getString(R.string.app_name));
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
        }
        return true;
    }
}
