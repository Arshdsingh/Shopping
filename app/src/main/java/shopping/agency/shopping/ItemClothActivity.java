package shopping.agency.shopping;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

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


public class ItemClothActivity extends AppCompatActivity implements View.OnClickListener{
    String id,title,price="0";
    String product_id = "",picpath="";
    final String IMAGE_URL = "gs://myshopping-55a99.appspot.com";

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

         String imageurl = "";
        if (getIntent() != null) {
            title = getIntent().getStringExtra("title");
            id = getIntent().getStringExtra("id");
            product_id = getIntent().getStringExtra("product_id");
            imageurl=getIntent().getStringExtra("imageurl");
            pricetext.setText("$"+getIntent().getStringExtra("price"));
            price=getIntent().getStringExtra("price");
            if (title != null) {
                titletext.setText("" + title);


            }




        }
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageReference = storage.getReferenceFromUrl(IMAGE_URL).child(imageurl);
        storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                System.out.println("uri = " + uri);
                DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true)
                        .resetViewBeforeLoading(true)
                        .showImageForEmptyUri(R.drawable.svg1)
                        .showImageOnFail(R.drawable.svg1)
                        .showImageOnLoading(R.drawable.svg2)
                        .cacheInMemory(true)
                        .considerExifParams(true)
                        .bitmapConfig(Bitmap.Config.RGB_565)
                        .build();
                MyApplication.getInstance().getInstanceImage() .loadImage(uri.toString(),options, new SimpleImageLoadingListener() {
                    @Override
                    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                        super.onLoadingComplete(imageUri, view, loadedImage);
                        imageView.setImageBitmap(loadedImage);
                    }

                    @Override
                    public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                        super.onLoadingFailed(imageUri, view, failReason);
                        imageView.setImageResource(R.drawable.svg1);
                    }

                    @Override
                    public void onLoadingStarted(String imageUri, View view) {
                        super.onLoadingStarted(imageUri, view);
                        imageView.setImageResource(R.drawable.svg2);
                    }
                });
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
                Toast.makeText(context, "Product is in cart, Continue shopping...", Toast.LENGTH_SHORT).show();
                finish();

        }
        itemcounter.setText(""+counter);
        pricetext.setText("$"+(Integer.parseInt(price)*counter)+"/-");

    }

    private void fetchData() {

    }
}
