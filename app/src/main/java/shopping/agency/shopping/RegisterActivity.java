package shopping.agency.shopping;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
EditText email,password;
Button submit;
FirebaseAuth   firebaseAuth ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        email=(EditText)findViewById(R.id.email);
        password=(EditText)findViewById(R.id.password);
        submit=(Button)findViewById(R.id.submit);
        submit.setOnClickListener(this);
        firebaseAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.submit)
        {
            String email1 = email.getText().toString();
            String paswd1 = password.getText().toString();
            if (email1.isEmpty()) {
                email.setError("Provide your Email first!");
                email.requestFocus();
            } else if (paswd1.isEmpty()) {
                password.setError("Set your password");
                password.requestFocus();
            } else if (email1.isEmpty() &&!Utils.isEmailValid(email)&& paswd1.isEmpty()) {
                Toast.makeText(RegisterActivity.this, "Fields Empty!", Toast.LENGTH_SHORT).show();
            } else if (!(email1.isEmpty() &&Utils.isEmailValid(email) && paswd1.isEmpty())) {
                firebaseAuth.createUserWithEmailAndPassword(email1, paswd1).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {

                        if (!task.isSuccessful()) {
                            Toast.makeText(RegisterActivity.this.getApplicationContext(),
                                    "SignUp unsuccessful: " + task.getException().getMessage(),
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(RegisterActivity.this, "SignUp Success", Toast.LENGTH_SHORT).show();

                            if(getIntent()!=null)
                            {
                                if(getIntent().getStringExtra("products")!=null)
                                {
                                    Intent I = new Intent(RegisterActivity.this, LoginActivity.class);
                                    I.putExtra("products","productsincart");
                                    startActivity(I);
                                }


                            }
                            else {
                                Intent I = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(I);
                            }
                           // startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                        }
                    }
                });
            } else {
                Toast.makeText(RegisterActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
