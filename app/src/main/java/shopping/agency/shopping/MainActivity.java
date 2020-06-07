package shopping.agency.shopping;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.provider.Settings;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // System.out.println( "Gson Array "+new Gson().toJson(getArr()));
        Dexter.withActivity(this)
                .withPermissions(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        if (report.areAllPermissionsGranted()) {
                            //showImagePickerOptions();
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    startActivity(new Intent(MainActivity.this,MenuActivity.class));
                                    finish();
                                }
                            },1000);
                        }

                        if (report.isAnyPermissionPermanentlyDenied()) {
                            showSettingsDialog();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();
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
    private void showSettingsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(getString(R.string.dialog_permission_title));
        builder.setMessage(getString(R.string.dialog_permission_message));
        builder.setPositiveButton(getString(R.string.go_to_settings), (dialog, which) -> {
            dialog.cancel();
            openSettings();
        });
        builder.setNegativeButton(getString(android.R.string.cancel), (dialog, which) -> dialog.cancel());
        builder.show();

    }
    // navigating user to app settings
    private void openSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, 101);
    }

    public LinkedList<ProductItem> getArr()
    {
        LinkedList<ProductItem> a=new LinkedList<>();
        ProductItem productItem =new ProductItem();
        productItem.setId("1");
        productItem.setDescription("Hoodi T -shirt,  Free Size");
        productItem.setTitle("Nice T -shirt");
        productItem.setPrice("5");
        productItem.setProduct_id("GR_1565424691218");
        productItem.setImageurl("GR_1565424691218.jpg");
        a.add(productItem);

         productItem =new ProductItem();
        productItem.setId("2");
        productItem.setDescription("Hoodi T -shirt,  Free Size");
        productItem.setTitle("Nice T -shirt");
        productItem.setPrice("5");
        productItem.setProduct_id("GR_1565424691762");
        productItem.setImageurl("GR_1565424691762.jpg");
        a.add(productItem);

        productItem =new ProductItem();
        productItem.setId("3");
        productItem.setDescription("Hoodi T -shirt,  Free Size");
        productItem.setTitle("Nice T -shirt");
        productItem.setPrice("5");
        productItem.setProduct_id("GR_1565424734662");
        productItem.setImageurl("GR_1565424734662.jpg");
        a.add(productItem);

        productItem =new ProductItem();
        productItem.setId("4");
        productItem.setDescription("Hoodi T -shirt,  Free Size");
        productItem.setTitle("Nice T -shirt");
        productItem.setPrice("5");
        productItem.setProduct_id("GR_1565424735202");
        productItem.setImageurl("GR_1565424735202.jpg");
        a.add(productItem);

        productItem =new ProductItem();
        productItem.setId("5");
        productItem.setDescription("Hoodi T -shirt,  Free Size");
        productItem.setTitle("Nice T -shirt");
        productItem.setPrice("5");
        productItem.setProduct_id("GR_1565424735751");
        productItem.setImageurl("GR_1565424735751.jpg");
        a.add(productItem);

        productItem =new ProductItem();
        productItem.setId("6");
        productItem.setDescription("Hoodi T -shirt,  Free Size");
        productItem.setTitle("Nice T -shirt");
        productItem.setPrice("5");
        productItem.setProduct_id("GR_1565424736304");
        productItem.setImageurl("GR_1565424736304.jpg");
        a.add(productItem);

        productItem =new ProductItem();
        productItem.setId("7");
        productItem.setDescription("Hoodi T -shirt,  Free Size");
        productItem.setTitle("Nice T -shirt");
        productItem.setPrice("5");
        productItem.setProduct_id("GR_1565424736863");
        productItem.setImageurl("GR_1565424736863.jpg");
        a.add(productItem);

        productItem =new ProductItem();
        productItem.setId("8");
        productItem.setDescription("Hoodi T -shirt,  Free Size");
        productItem.setTitle("Nice T -shirt");
        productItem.setPrice("5");
        productItem.setProduct_id("GR_1565426729064");
        productItem.setImageurl("GR_1565426729064.jpg");
        a.add(productItem);

       productItem =new ProductItem();
        productItem.setId("9");
        productItem.setDescription("Hoodi T -shirt,  Free Size");
        productItem.setTitle("Nice T -shirt");
        productItem.setPrice("5");
        productItem.setProduct_id("GR_1565426729464");
        productItem.setImageurl("GR_1565426729464.jpg");
        a.add(productItem);

         productItem =new ProductItem();
            productItem.setId("10");
            productItem.setDescription("Hoodi T -shirt,  Free Size");
            productItem.setTitle("Nice T -shirt");
            productItem.setPrice("5");
            productItem.setProduct_id("GR_1565426729925");
            productItem.setImageurl("GR_1565426729925.jpg");
            a.add(productItem);

            productItem =new ProductItem();
            productItem.setId("11");
            productItem.setDescription("Hoodi T -shirt,  Free Size");
            productItem.setTitle("Nice T -shirt");
            productItem.setPrice("5");
            productItem.setProduct_id("GR_1565426730262");
            productItem.setImageurl("GR_1565426730262.jpg");
            a.add(productItem);


            productItem =new ProductItem();
            productItem.setId("12");
            productItem.setDescription("Hoodi T -shirt,  Free Size");
            productItem.setTitle("Nice T -shirt");
            productItem.setPrice("5");
            productItem.setProduct_id("GR_1565426730680");
            productItem.setImageurl("GR_1565426730680.jpg");
            a.add(productItem);

            productItem =new ProductItem();
            productItem.setId("13");
            productItem.setDescription("Hoodi T -shirt,  Free Size");
            productItem.setTitle("Nice T -shirt");
            productItem.setPrice("5");
            productItem.setProduct_id("GR_1565426731035");
            productItem.setImageurl("GR_1565426731035.jpg");
            a.add(productItem);

            productItem =new ProductItem();
            productItem.setId("14");
            productItem.setDescription("Hoodi T -shirt,  Free Size");
            productItem.setTitle("Nice T -shirt");
            productItem.setPrice("5");
            productItem.setProduct_id("GR_1565426812033");
            productItem.setImageurl("GR_1565426812033.jpg");
            a.add(productItem);

            productItem =new ProductItem();
            productItem.setId("15");
            productItem.setDescription("Hoodi T -shirt,  Free Size");
            productItem.setTitle("Nice shirt");
            productItem.setPrice("5");
            productItem.setProduct_id("GR_1565426812807");
            productItem.setImageurl("GR_1565426812807.jpg");
            a.add(productItem);

            productItem =new ProductItem();
            productItem.setId("16");
            productItem.setDescription("Hoodi T -shirt,  Free Size");
            productItem.setTitle( " Nice T -shirtb");
            productItem.setPrice("5");
            productItem.setProduct_id("GR_1565426815810");
            productItem.setImageurl("GR_1565426815810.jpg");
            a.add(productItem);



        return a;
    }
}
