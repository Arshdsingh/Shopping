package shopping.agency.shopping;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Manoj1 on 4/7/2017.
 */

public class Utils {
    Context context;
    public Utils(Context context)
    {
        this.context=context;

    }
    public  boolean isInternetConnected()
    {
        ConnectivityManager connectivityManager=(ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager!=null)
        {
            NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
            if(networkInfo!=null)
            {
                if(networkInfo.getState()== NetworkInfo.State.CONNECTED)
                {
                    return true;
                }
            }
        }

        return false;
    }
    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    public static boolean isEmailValid(EditText email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email.getText().toString());
        if( matcher.matches()){
            return true;
        }
        else{
            email.setError("Email is not valid");
            return false;
        }
    }
    public static boolean isValidMobile(String phone) {

       if(phone.length()==10&&(phone.startsWith("8")||phone.startsWith("9"))||phone.startsWith("7")||phone.startsWith("6")){
           return true;

       }
        else if(phone.length()<10){
            return false;
        }
       else  return false;
    }
    public static boolean isValidMobile(EditText phone) {

        if(phone.getText().toString().length()==10&&(phone.getText().toString().startsWith("8")||phone.getText().toString().startsWith("9"))||phone.getText().toString().startsWith("7")||phone.getText().toString().startsWith("6")){
                 if(phone.length()<10)
                            {

                                phone.setError("Less than 10 digits");
                             return false;
                            }
                            else
                                {
                            return  true;
                           }
        }
        else{
            phone.setError("Must start with 6 or 7 or 8 or 9");

            return false;
        }

    }
    public static boolean isValidNumber(EditText phone,int number) {
        if (TextUtils.isDigitsOnly(phone.getText().toString()))
        {
            if (phone.getText().toString().length() < number) {
                phone.setError("Less than 10 digits");
                return false;
            } else {
                return true;
            }
    }
    else {
            phone.setError("Only numbers allowed");
            return false;
    }
    }
    public static boolean isValidNumber(EditText phone) {
        if (TextUtils.isDigitsOnly(phone.getText().toString()))
        {
                return true;
        }
        else {
            phone.setError("Only numbers allowed");
            return false;
        }
    }
    public static boolean isValidName(String name) {
        if(!TextUtils.isDigitsOnly(name) & name.length()>2)
        {
           return  true;
        }
        else
        {
            return false;
        }
    }
    public static boolean formInputEmptyChecking(EditText editText)
    {
        if(!TextUtils.isEmpty(editText.getText().toString())){
          if(!TextUtils.isDigitsOnly(editText.getText().toString()))
          {
              if(editText.getText().toString().length()<=2)
              {
                  editText.setError("Less than minimum characters");
                  return false;

              }
              else{
                  return true;

              }
          }else
              {
              editText.setError("Can not be digits");
              return false;
              }
        }
        else{
            editText.setError("Can not be empty");
            return false;
        }
    }

    public static boolean formInputEmptyCheckingAll(EditText editText)
    {
        if(!TextUtils.isEmpty(editText.getText().toString())){

                if(editText.getText().toString().length()<=2)
                {
                    editText.setError("Less than minimum characters ");
                    return false;

                }
                else{
                    return true;

                }
            }
        else{
            editText.setError("Can not be empty");
            return false;
        }
    }
    public static boolean isValidString(EditText editText,int minlength, int maxlength)
    {

            if(editText.getText().toString().length()<=minlength)
            {
                editText.setError("Less than minimum characters ("+minlength+")");
                return false;
            }
            else if(editText.getText().toString().length()>=maxlength)
            {
                editText.setError("greater than maximum characters ("+maxlength+")");
                return false;
            }
            else{
                return true;
            }


    }
    public static String formInputEmptyChecking(String inputtext)
    {
        if(!TextUtils.isEmpty(inputtext)){



                    return inputtext;


            }else
            {
                return null;
            }


    }
}
