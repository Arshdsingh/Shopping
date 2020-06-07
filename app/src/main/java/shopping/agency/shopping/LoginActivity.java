package shopping.agency.shopping;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    EditText email, password;
    Button submit;
    FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    SharedPreferences sharedPreferences;
    TextView registerlink;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = (EditText) findViewById(R.id.username);
        registerlink = (TextView) findViewById(R.id.registerlink);
        registerlink.setOnClickListener(this);
        password = (EditText) findViewById(R.id.password);
        submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(this);
        sharedPreferences=getSharedPreferences("userdata",MODE_PRIVATE);
        firebaseAuth = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            sharedPreferences.edit().putString("username",email.getText().toString()).commit();
                            Toast.makeText(LoginActivity.this, "User logged in ", Toast.LENGTH_SHORT).show();

                            if(getIntent()!=null)
                            {
                                if(getIntent().getStringExtra("products")!=null)
                                {
                                    Intent I = new Intent(LoginActivity.this, AddressActivity.class);
                                    startActivity(I);
                                }
                                else {
                                    Intent I = new Intent(LoginActivity.this, MenuActivity.class);
                                    startActivity(I);
                                }

                            }
                            else {
                                Intent I = new Intent(LoginActivity.this, MenuActivity.class);
                                startActivity(I);
                            }

                        }
                    },400);

                } else {
                    Toast.makeText(LoginActivity.this, "Login to continue", Toast.LENGTH_SHORT).show();
                }
            }};
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
    public void onClick(View v) {
        if (v.getId() == R.id.submit) {

                    String userEmail = email.getText().toString();
                    String userPaswd = password.getText().toString();
                    if (userEmail.isEmpty()) {
                        email.setError("Provide your Email first!");
                        password.requestFocus();
                    } else if (userPaswd.isEmpty()) {
                        password.setError("Enter Password!");
                        password.requestFocus();
                    } else if (userEmail.isEmpty()&&!Utils.isEmailValid(email) && userPaswd.isEmpty()) {
                        Toast.makeText(LoginActivity.this, "Fields Empty!", Toast.LENGTH_SHORT).show();
                    } else if (!(userEmail.isEmpty() &&Utils.isEmailValid(email) && userPaswd.isEmpty())) {
                        firebaseAuth.signInWithEmailAndPassword(userEmail, userPaswd).addOnCompleteListener(LoginActivity.this, new OnCompleteListener() {
                            @Override
                            public void onComplete(@NonNull Task task) {
                                if (!task.isSuccessful()) {
                                    Toast.makeText(LoginActivity.this, "Not sucessfull", Toast.LENGTH_SHORT).show();
                                } else {
                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            sharedPreferences.edit().putString("username",userEmail).commit();
                                            startActivity(new Intent(LoginActivity.this, MenuActivity.class));
                                            Toast.makeText(LoginActivity.this, "Login success", Toast.LENGTH_SHORT).show();
                                        }
                                    },400);
                                }
                            }
                        });
                    } else {
                        Toast.makeText(LoginActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                }
        if(v.getId()==R.id.registerlink)
        {
            if(getIntent()!=null)
            {
                if(getIntent().getStringExtra("products")!=null)
                {
                    Intent I = new Intent(LoginActivity.this, RegisterActivity.class);
                    I.putExtra("products","productsincart");
                    startActivity(I);
                }


            }
            else {
                Intent I = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(I);
            }
        }


    }
}
