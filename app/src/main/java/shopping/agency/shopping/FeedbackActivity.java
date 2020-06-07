package shopping.agency.shopping;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class FeedbackActivity extends AppCompatActivity {

    Context context;
    EditText name,email,phone,subject,message;
    LinearLayout alertarea;
    TextView forgot,registerlink,alert;
    Button submit;
    ProgressBar progressBar;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        context=this;
        ActionBar actionBar=getSupportActionBar();
        // actionBar.setIcon(R.drawable.logo25);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        String text ="Feedback";
        actionBar.setTitle(text);
        context=this;

        initialize();
    }
    public void initialize(){
        ActionBar actionBar;

        actionBar = getSupportActionBar();
        actionBar.setTitle(getString(R.string.app_name));
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        alertarea=(LinearLayout) findViewById(R.id.alertarea);
        alertarea.setVisibility(View.GONE);
        alert=(TextView) findViewById(R.id.alert);
        progressBar=(ProgressBar) findViewById(R.id.progress_circular);
        progressBar.setVisibility(View.GONE);
        name=(EditText)findViewById(R.id.name);
        email=(EditText)findViewById(R.id.email);
        phone=(EditText)findViewById(R.id.phone);
        subject=(EditText)findViewById(R.id.subject);
        message=(EditText)findViewById(R.id.message);
        submit=(Button)findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isEmptyChecking())
                {
                    // setWebData(context,MAINAPI);
                    Toast.makeText(context,"Thank you for the valuable feedback",Toast.LENGTH_LONG).show();
                }
            }
        });
        sharedPreferences=getSharedPreferences("userdata",MODE_PRIVATE);

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
    public boolean isEmptyChecking(){
        boolean status=false;

        if(!TextUtils.isEmpty(name.getText().toString())){
            if(!TextUtils.isEmpty(email.getText().toString())){
                if(!TextUtils.isEmpty(phone.getText().toString())){
                    if(!TextUtils.isEmpty(subject.getText().toString())) {

                        if (!TextUtils.isEmpty(message.getText().toString())) {
                            if (message.getText().toString().length() > 50) {

                                status = true;

                            } else {
                                message.setError("Can not be less than 50 characters");
                                status = false;
                            }
                        } else {
                            message.setError("not valid");
                            status = false;
                        }
                    }else {
                        subject.setError("not valid");
                        status=  false;
                    }
                }
                else{
                    phone.setError("not valid");
                    status=  false;

                }

            }
            else{
                email.setError("not valid");
                status=  false;
            }
        }
        else{
            name.setError("not valid");
            status=  false;
        }

        return  status;
    }

    public void setWebData(final Context context,String url)
    {
        if(new Utils(context).isInternetConnected())
        {
            StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if(response!=null) {
                        System.out.println("contact /message user " + response);
                        try {
                            JSONObject jsonObject1=    new JSONObject(response);
                            String returnvalue = jsonObject1.getString("status");
                            String   msg = jsonObject1.getString("msg");
                            // String   result = jsonObject1.getString("result");
                            if (returnvalue.equalsIgnoreCase("false"))
                            {
                                // Toast.makeText(context, "No data", Toast.LENGTH_SHORT).show();
                                Toast.makeText(context, ""+msg, Toast.LENGTH_LONG).show();
                            }

                            else {
                                if (msg != null)
                                {
                                    Toast.makeText(context, ""+msg, Toast.LENGTH_LONG).show();
                                    finish();
                                }
                            }
                            // webView.loadData("<div style=\"margin:0px; text-align:left;\">"+result+"</div>", mimeType, encoding);

                            progressBar.setVisibility(View.GONE);

                        }
                        catch (Exception e){
                            progressBar.setVisibility(View.GONE);

                            Toast.makeText(context, "Server error", Toast.LENGTH_SHORT).show();
                        }

                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progressBar.setVisibility(View.GONE);

                    Toast.makeText(context, "Network error", Toast.LENGTH_SHORT).show();
                }
            }
            )
            {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                        /*api_type = 'feedback'
                            name = {Name}
                            phone = {Mobile number}
                            email = {Email Address}
                            rate = {start rating 1-5}
                            message = {Message by user minimum 50 character}}*/
                    //String uri = String.format("http://steelbaba.com/api/User_Api?
                    // api_type=contact&name="+name.getText()+"&email="+email.getText()+"&phone="+phone.getText()+"&message="+message.getText().toString().trim());
                    Map<String ,String> map=new HashMap<>();
                    map.put("api_type","request" );
                    map.put("name",name.getText().toString() );
                    map.put("phone",phone.getText().toString() );
                    map.put("subject",subject.getText().toString() );
                    map.put("email",email.getText().toString() );
                    map.put("message",message.getText().toString() );
                    return map;
                }
            }
                    ;
            stringRequest.setRetryPolicy(new DefaultRetryPolicy(7000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            MyApplication.getInstance().addToRequestQueue(stringRequest);

        }
        else
        {
                // progressDialog.hide();
            Toast.makeText(context, "No internet found", Toast.LENGTH_SHORT).show();
        }

    }
}
