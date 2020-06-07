package shopping.agency.shopping;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;


public class ItemActivity extends AppCompatActivity implements View.OnClickListener{
String id,title,price="0";
    String product_id = "",picpath="";
    final String IMAGE_URL = "gs://myshopping-55a99.appspot.com";
    int imageid = 0;

Integer counter=1,limit=10;
Context context;
Button addtocart;
TextView titletext,descriptiontext,pricetext,itemcounter,cancel;
ImageView add,remove,imageView;
    ActionBar actionBar;
    LinearLayout back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        context=this;
        addtocart = (Button) findViewById(R.id.addtocart);
        addtocart.setOnClickListener(this);
        titletext = (TextView) findViewById(R.id.title);
        descriptiontext = (TextView) findViewById(R.id.desc);
        back = (LinearLayout) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        pricetext = (TextView) findViewById(R.id.price);
        itemcounter = (TextView) findViewById(R.id.itemcount);
        add = (ImageView) findViewById(R.id.add);
        imageView = (ImageView) findViewById(R.id.imageView);
        remove = (ImageView) findViewById(R.id.remove);
        add.setOnClickListener(this);
        remove.setOnClickListener(this);

        if (getIntent() != null) {
            title = getIntent().getStringExtra("title");
            id = getIntent().getStringExtra("id");
            product_id = getIntent().getStringExtra("product_id");
            pricetext.setText("$"+getIntent().getStringExtra("price"));
            price=getIntent().getStringExtra("price");
              imageid=getIntent().getIntExtra("imageurl",0);

            if (title != null) {
                titletext.setText("" + title);


            }
            imageView.setImageResource(imageid);


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
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.remove:
                if(counter<=1)
                {
                    Toast.makeText(this, "Minimum 1 item is required", Toast.LENGTH_SHORT).show();


                }
                else
                {
                    --counter;
                }
              break;
            case R.id.add:
                if(counter>=10)
                {
                    Toast.makeText(this, "Item can not be exceeded more than "+limit, Toast.LENGTH_SHORT).show();

                }
                else
                {
                    ++counter;
                }
                break;
            case R.id.addtocart:
                new SqliteDatabaseDb(context).insert(title,id,product_id,price,imageid+"",counter);

                Toast.makeText(context, "Product is in cart, Continue shopping...", Toast.LENGTH_SHORT).show();
                finish();

        }
        itemcounter.setText(""+counter);
        pricetext.setText("$"+(Integer.parseInt(price)*counter)+"/-");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.optiontry,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
        }
        if (item.getItemId()==R.id.personaltry)
        {
            Intent intent=new Intent(ItemActivity.this,FaceActivity.class);
            intent.putExtra("productname",imageid);
            intent.putExtra("product_id",product_id);
            startActivity(intent);
        }
        return true;
    }
}
