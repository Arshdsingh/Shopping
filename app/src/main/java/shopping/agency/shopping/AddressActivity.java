package shopping.agency.shopping;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class AddressActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
EditText email,phone,city,country,address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        email=(EditText)findViewById(R.id.email);
        phone=(EditText)findViewById(R.id.phone);
        city=(EditText)findViewById(R.id.city);
        country=(EditText)findViewById(R.id.country);
        address=(EditText)findViewById(R.id.address);
        sharedPreferences=getSharedPreferences("userdata",MODE_PRIVATE);
        email =findViewById(R.id.email);
        email.setText(sharedPreferences.getString("username",""));

        findViewById(R.id.submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, "Order placed successfully.", Toast.LENGTH_SHORT).show();
               if(Utils.isEmailValid(email)&&Utils.isValidNumber(phone,10)&&Utils.formInputEmptyChecking(city)&&Utils.formInputEmptyChecking(country)&&Utils.formInputEmptyCheckingAll(address)) {
                   Intent intent = new Intent(AddressActivity.this, PaymentActivity.class);
                   startActivity(intent);
               }
            }
        });

        initialize();
    }
    public void initialize()
    {
        ActionBar actionBar;

        actionBar = getSupportActionBar();
        actionBar.setTitle("Address");
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
