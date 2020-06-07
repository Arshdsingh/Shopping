package shopping.agency.shopping;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.LinkedHashSet;
import java.util.LinkedList;

import shopping.agency.shopping.adapters.OrderConfirmedRecyclerAdapter;
import shopping.agency.shopping.adapters.OrderRecyclerAdapter;

public class OrderHistoryActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    String product_id;
    Button placeorder;
    Context context;
    LinkedList<OrderItems> arr=new LinkedList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);
        context=this;
        TextView textView=(TextView)findViewById(R.id.name);
        placeorder=(Button) findViewById(R.id.placeorder);
        recyclerView=(RecyclerView)findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        arr=new SqliteDatabaseDb(context).getUserOrderedList();
        if(arr!=null)
        {
            if(arr.size()>0) {
                arr = new LinkedList<>(new LinkedHashSet<>(arr));
                recyclerView.setAdapter(new OrderConfirmedRecyclerAdapter(context, arr));
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
