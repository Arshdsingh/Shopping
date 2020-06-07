package shopping.agency.shopping;

import android.content.Context;
import android.os.Bundle;

import android.view.MenuItem;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;


public class OrderListActivity extends AppCompatActivity {
ActionBar actionBar;
Context context;
Button placeorder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.orderlistlayout);
            OrderListFragment orderlist = new OrderListFragment();
            Bundle bundle = new Bundle();
            orderlist.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.container, orderlist).commit();

    initialize();
    }
    public void initialize()
    {
        actionBar = getSupportActionBar();
        actionBar.setTitle("My Cart");
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        context=this;

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
