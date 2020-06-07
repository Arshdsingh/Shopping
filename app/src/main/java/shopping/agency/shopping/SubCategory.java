package shopping.agency.shopping;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedHashSet;
import java.util.LinkedList;

import shopping.agency.shopping.adapters.ProductRecyclerAdapter;
import shopping.agency.shopping.adapters.ProductRecyclerAdapter1;
import shopping.agency.shopping.adapters.ProductSubRecyclerAdapter;

public class SubCategory extends AppCompatActivity {

    RecyclerView recyclerView;
    String product_id;
    Button placeorder;
    Context context;
    LinkedList<ProductItem1> arr=new LinkedList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category);
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
                recyclerView.setAdapter(new ProductSubRecyclerAdapter(context, arr));
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
                    Intent intent=new Intent(SubCategory.this,LoginActivity.class);
                    startActivity(intent);
                    //   ((Activity) context).finish();
                }
                else {
                    Toast.makeText(context, "Cart is empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public  LinkedList<ProductItem1> getArr()
    {
        LinkedList<ProductItem1> a=new LinkedList<>();
        ProductItem1 ProductItem=new ProductItem1();
        ProductItem.setId("1");
        ProductItem.setDescription("T-shirt ");
        ProductItem.setTitle("T-shirt");
        ProductItem.setPrice("5");
        ProductItem.setProduct_id("101");
        ProductItem.setImageurl(R.drawable.tshirt);
        ProductItem.setCateogory("1");

        a.add(ProductItem);

         ProductItem=new ProductItem1();
        ProductItem.setId("1");
        ProductItem.setDescription("Shoes");
        ProductItem.setTitle("Footwear");
        ProductItem.setPrice("35");
        ProductItem.setProduct_id("102");
        ProductItem.setImageurl(R.drawable.shoes);
        ProductItem.setCateogory("3");

        a.add(ProductItem);

        ProductItem=new ProductItem1();
        ProductItem.setId("1");
        ProductItem.setDescription("Shirt");
        ProductItem.setTitle("Shirt");
        ProductItem.setPrice("43");
        ProductItem.setProduct_id("103");
        ProductItem.setImageurl(R.drawable.shirt);
        ProductItem.setCateogory("2");

        a.add(ProductItem);
        return a;
    }

}
