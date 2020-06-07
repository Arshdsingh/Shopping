package shopping.agency.shopping;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedHashSet;
import java.util.LinkedList;

import shopping.agency.shopping.adapters.ProductRecyclerAdapter;
import shopping.agency.shopping.adapters.ProductRecyclerAdapter1;

public class PersonalCareActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    String product_id;
    Button placeorder;
    Context context;
    LinkedList<ProductItem1> arr=new LinkedList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_care);
        context=this;
        TextView textView=(TextView)findViewById(R.id.name);
        placeorder=(Button) findViewById(R.id.placeorder);
        recyclerView=(RecyclerView)findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        arr=getArr();
        // textView.setText("Deliciousness jumping into the mouth");
       // arr=new SqliteDatabaseDb(context).getOrderList();
        String fontPath = "font/dancing.otf";
        // Loading Font Face
//        Typeface tf = Typeface.createFromAsset(context.getAssets(), fontPath);

        // Applying font
      //  textView.setTypeface(tf);        // Inflate the layout for this fragment
        if(arr!=null)
        {
            if(arr.size()>0) {
                arr = new LinkedList<>(new LinkedHashSet<>(arr));
                recyclerView.setAdapter(new ProductRecyclerAdapter1 (context, arr));
            }else
            {
               findViewById(R.id.nocontent).setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
            }
        }
        else {
            findViewById(R.id.nocontent).setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);

        }
        placeorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (arr.size()>0) {
                   // new SqliteDatabaseDb(context).deleteAll();
                    Intent intent=new Intent(PersonalCareActivity.this,OrderListActivity.class);
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
    public  LinkedList<ProductItem1> getArr()
    {
        LinkedList<ProductItem1> a=new LinkedList<>();
        ProductItem1 productItem =new ProductItem1();
        productItem.setId("1");
        productItem.setDescription("Nice Hat");
        productItem.setTitle("Nice Hat");
        productItem.setPrice("34");
        productItem.setProduct_id("123");
        productItem.setImageurl(R.drawable.hat);
        a.add(productItem);

         productItem =new ProductItem1();
        productItem.setId("2");
        productItem.setDescription("Nice Sunglass");
        productItem.setTitle("Nice Sunglass");
        productItem.setPrice("31");
        productItem.setProduct_id("121");
        productItem.setImageurl(R.drawable.sunglass1);
        a.add(productItem);

        productItem =new ProductItem1();
        productItem.setId("2");
        productItem.setDescription("Nice Beautiful");
        productItem.setTitle("Beautiful Cap");
        productItem.setPrice("11");
        productItem.setProduct_id("122");
        productItem.setImageurl(R.drawable.cap1);
        a.add(productItem);
        return a;
    }


}
