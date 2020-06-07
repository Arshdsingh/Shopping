package shopping.agency.shopping;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;


import android.view.Menu;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import shopping.agency.shopping.fragments.SliderViewNewFragment;

public class MenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
        public static final String TAG="MyShopping LOGS";
        SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        sharedPreferences=getSharedPreferences("userdata",MODE_PRIVATE);

        getSupportFragmentManager().beginTransaction().replace(R.id.container, new SliderViewNewFragment()).commit();

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();

        //CollectionReference collection = database.collection("users");



      //  myRef.setValue("Myshopping, World!");
// Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                //String value = dataSnapshot.getValue(String.class);
                //Log.d(TAG, "Value is: " + value);
                Users users=new Users("arsh1","arsh1@gmail.com");
                String str=new Gson().toJson(users);

                myRef.child("01").setValue(str);//id.setvalue()

               // System.out.println("manoj----" + dataSnapshot.getChildren().iterator());
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //System.out.println("postSnapshot.getKey() = " + postSnapshot.getKey());
                    if(postSnapshot.getKey().contains("01"))
                    {
                      String as=  (String) postSnapshot.getValue();
                        Users users1=new Gson().fromJson(as,new TypeToken<Users>(){}.getType());
                        System.out.println("users1 = " + users1.username);
                        System.out.println("users1 = " + users1.email);
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Please give feedback ", Snackbar.LENGTH_LONG)
                        .setAction("Action", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                startActivity(new Intent(MenuActivity.this,FeedbackActivity.class));

                            }
                        }).show();
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View hView = navigationView.getHeaderView(0);
        final TextView signin = (TextView) hView.findViewById(R.id.username);
        signin.setText(sharedPreferences.getString("username",""));
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);

        if(sharedPreferences.getString("username","")!=null){
            if(!sharedPreferences.getString("username","").equalsIgnoreCase(""))
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.logout) {
            sharedPreferences.edit().remove("username").commit();
            startActivity(new Intent(MenuActivity.this,LoginActivity.class));
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.beautycare) {
            startActivity(new Intent(MenuActivity.this,PersonalCareActivity.class));
        } else if (id == R.id.cloths) {
            startActivity(new Intent(MenuActivity.this,SubCategory.class));

        }
        else if (id == R.id.cart) {
            startActivity(new Intent(MenuActivity.this,OrderListActivity.class));

        }
        else if (id == R.id.orders) {
            startActivity(new Intent(MenuActivity.this,OrderHistoryActivity.class));

        }
        else if (id == R.id.nav_share) {
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:")); // only email apps should handle this
            intent.putExtra(Intent.EXTRA_EMAIL, "shoppingar789@gmail.com");
            intent.putExtra(Intent.EXTRA_SUBJECT, "Best Shopping ");
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        } else if (id == R.id.nav_send) {

            startActivity(new Intent(MenuActivity.this,FeedbackActivity.class));

        }

        else if (id == R.id.login) {
            startActivity(new Intent(MenuActivity.this,LoginActivity.class));

        }
        else if (id == R.id.register) {
            startActivity(new Intent(MenuActivity.this,RegisterActivity.class));

        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
