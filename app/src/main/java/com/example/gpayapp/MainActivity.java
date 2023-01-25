package com.example.gpayapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private EditText et_email,et_password;
    private TextView error_email,error_password;
    private String email;
    private String password;
    public static final Pattern EMAIL_ADDRESS_REGX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",Pattern.CASE_INSENSITIVE);
    //Password must contain one digit from 1 to 9, one lowercase letter, one uppercase letter, one special character, no space, and it must be 8-16 characters long.
    public static final Pattern PASSWORD_REGX = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*\\W)(?!.* ).{8,16}$");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        et_email=(EditText) findViewById(R.id.et_email);
        et_password=(EditText) findViewById(R.id.et_password);
        error_email=(TextView) findViewById(R.id.error_email);
        error_password=(TextView) findViewById(R.id.error_password);
    }
    public void gotoRegistration(View view) {
        Intent intent=new Intent(MainActivity.this,RegistrationPage.class);
        startActivity(intent);
        finish(); //try to press Android back Biutton After going
        //2nd Activity

    }
    public void submit(View view) {
        email = et_email.getText().toString();
        password = et_password.getText().toString();

        if( validateEmail(email) | validatePassword(password)){
            Toast.makeText(this, ""+email+password, Toast.LENGTH_SHORT).show();
            //if(email.equals("admin@gmail.com")&& password.equals("Password@123"))
            if( validateEmail(email) && validatePassword(password)){

                Toast.makeText(this, "Login successfully", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(MainActivity.this,NavigationDrawer.class);
                startActivity(intent);
            }else{
                et_email.setBackgroundResource(R.drawable.error_layout);
                et_password.setBackgroundResource(R.drawable.error_layout);
                Toast.makeText(this, "Invalid Email or Password", Toast.LENGTH_SHORT).show();
            }
        }
        // validateEmail(email);
        // validatePassword(password);
    }
    //Blank validation  for email validation
    //for password validation
    // }
    private boolean validateEmail(String email) {
        if (email.isEmpty()) {
            error_email.setText("Email field is required");
            et_email.setBackgroundResource(R.drawable.error_layout);
            return false;
        }
        if (!EMAIL_ADDRESS_REGX.matcher(email).matches()) {
            error_email.setText("Email field is required");
            error_email.setText("Invalid Email");
            return false;
        }
        et_email.setBackgroundResource(R.drawable.success_layout);
        error_email.setText("");

        //  Toast.makeText(this, "Your  Email : " + email, Toast.LENGTH_SHORT).show();
        return true;
    }
    private boolean validatePassword(String password){
        if(password.isEmpty()){
            error_password.setText("Password field is required");
            et_email.setBackgroundResource(R.drawable.error_layout);
            return false;
        }
        if(!PASSWORD_REGX.matcher(password).matches()){
            error_password.setText("Invalid Password");
            et_password.setBackgroundResource(R.drawable.error_layout);
            return false;
        }
        et_password.setBackgroundResource(R.drawable.success_layout);
        error_password.setText("");
        //email.set
        Toast.makeText(this, "Your  Password : "+password, Toast.LENGTH_SHORT).show();
        return true;


    }
}