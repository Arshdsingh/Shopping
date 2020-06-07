package shopping.agency.shopping;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedList;

public class PaymentActivity extends AppCompatActivity {
EditText name,month,year,cvv,cardnumber,price;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        name=(EditText)findViewById(R.id.name);
        month=(EditText)findViewById(R.id.month);
        year=(EditText)findViewById(R.id.year);
        cvv=(EditText)findViewById(R.id.cvv);
        cardnumber=(EditText)findViewById(R.id.cardnumber);
        price=(EditText)findViewById(R.id.price);
        findViewById(R.id.cod).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent=new Intent(PersonalCareActivity.this,AddressActivity.class);
               // startActivity(intent);
                Toast.makeText(PaymentActivity.this, "Order placed successfully.", Toast.LENGTH_SHORT).show();
                findViewById(R.id.payonlinecontainer).setVisibility(View.GONE);
                LinkedList<ProductItem> productItems=new SqliteDatabaseDb(PaymentActivity.this).getCartList();
                for (int i=0;i<productItems.size();i++)
                {
                    //    public long insertOrder(String title1, String id1, String product_id1, String price1, String pic1, int n, String username) {
                    new SqliteDatabaseDb(PaymentActivity.this).insertOrder(productItems.get(i).getTitle(),productItems.get(i).getId(),productItems.get(i).getProduct_id(),productItems.get(i).getPrice(),productItems.get(i).getImageurl(),productItems.get(i).getNoofitems(),getSharedPreferences("userdata",MODE_PRIVATE).getString("username",""));
                }
                new SqliteDatabaseDb(PaymentActivity.this).deleteAll();
                startActivity(new Intent(PaymentActivity.this,ThanksActivity.class));
                finish();

            }
        });
        findViewById(R.id.payonline).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent=new Intent(PersonalCareActivity.this,AddressActivity.class);
                // startActivity(intent);
               // Toast.makeText(PaymentActivity.this, "Order placed successfully.", Toast.LENGTH_SHORT).show();
                findViewById(R.id.payonlinecontainer).setVisibility(View.VISIBLE);
            }
        });
        findViewById(R.id.submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent=new Intent(PersonalCareActivity.this,AddressActivity.class);
                // startActivity(intent);
                if(Utils.formInputEmptyChecking(name)&&Utils.isValidNumber(month,2)&&Utils.isValidNumber(year,4)&&Utils.isValidNumber(cvv,3)&&Utils.isValidNumber(price)&&Utils.isValidNumber(cardnumber,19)) {
                    Toast.makeText(PaymentActivity.this, "Order placed successfully.", Toast.LENGTH_SHORT).show();

                    LinkedList<ProductItem> productItems = new SqliteDatabaseDb(PaymentActivity.this).getCartList();
                    for (int i = 0; i < productItems.size(); i++) {
                        //    public long insertOrder(String title1, String id1, String product_id1, String price1, String pic1, int n, String username) {
                        new SqliteDatabaseDb(PaymentActivity.this).insertOrder(productItems.get(i).getTitle(), productItems.get(i).getId(), productItems.get(i).getProduct_id(), productItems.get(i).getPrice(), productItems.get(i).getImageurl(), productItems.get(i).getNoofitems(), getSharedPreferences("userdata", MODE_PRIVATE).getString("username", ""));
                    }
                    startActivity(new Intent(PaymentActivity.this, ThanksActivity.class));
                    finish();
                    //findViewById(R.id.payonlinecontainer).setVisibility(View.VISIBLE);
                    new SqliteDatabaseDb(PaymentActivity.this).deleteAll();
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
