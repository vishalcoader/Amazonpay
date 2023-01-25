package com.example.gpayapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

public class RegistrationPage extends AppCompatActivity {
    private EditText email,password,name,number;
    private   TextView err_email,err_password,err_name,err_number;
    private RadioGroup radioGroup;
    private  String gender="";

    private TextView textView;
    private String email1;
    private String name1;
    private String password1;
    private String number1;
    private int radioId;


    public static final Pattern EMAIL_ADDRESS_REGX=Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",Pattern.CASE_INSENSITIVE);
    //Password must contain one digit from 1 to 9, one lowercase letter, one uppercase letter, one special character, no space, and it must
    public static final Pattern PASSWORD_REGX=Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*\\W)(?!.* ).{8,16}$");
    // public static final Pattern USER_NAME_REGX=Pattern.compile("^[A-Za-z][A-Za-z0-9_]{7,29}$");
    public static final Pattern NUMBER_REGX=Pattern.compile("^(?:(?:\\+|0{0,2})91(\\s*|[\\-])?|[0]?)?([6789]\\d{2}([ -]?)\\d{3}([ -]?)\\d{4})$");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page);
        getSupportActionBar().hide();

        email=(EditText) findViewById(R.id.email);
        err_email=(TextView) findViewById(R.id.err_email);

        password=(EditText) findViewById((R.id.password));
        err_password=(TextView) findViewById(R.id.err_password);

        name=(EditText) findViewById(R.id.name);
        err_name=(TextView) findViewById(R.id.err_name);

        number=(EditText) findViewById(R.id.number);
        err_number=(TextView) findViewById(R.id.err_number);

        radioGroup=(RadioGroup) findViewById(R.id.et_gender);
        textView=(TextView) findViewById(R.id.gender_error);

//         radioId=radioGroup.getCheckedRadioButtonId();
//        clickedRadioButton=(RadioButton) findViewById(radioId);




    }

    public void gotoLogin(View view) {
        Intent intent=new Intent(RegistrationPage.this,MainActivity.class);
        startActivity(intent);
        //finish(); //try to press Android back Button After going
        //2nd Activity

    }

    public void getGender(View view) {
        int radioId = radioGroup.getCheckedRadioButtonId();
        RadioButton clickedRbBtn = (RadioButton) findViewById(radioId);
        gender = clickedRbBtn.getText().toString();
    }


    public void submit(View view) {
        email1 = email.getText().toString();
        number1 = number.getText().toString();
        password1 = password.getText().toString();
        name1 = name.getText().toString();


        if(validateNumber(number1) | validateName(name1) | validateEmail(email1) | validatePassword(password1) | validateGender(gender))
        {
            Toast.makeText(this, ""+name1+number1+email1+password1+gender, Toast.LENGTH_SHORT).show();
            //if(number1.equals("+919076796734") && name1.equals("vishal123_dubey") && email1.equals("admin123@gmail.com") && password1.equals("Password@123"))
            if(validateNumber(number1) && validateName(name1) && validateEmail(email1) && validatePassword(password1) && validateGender(gender)){
                name.setBackgroundResource(R.drawable.success_layout);
                number.setBackgroundResource(R.drawable.success_layout);
                textView.setBackgroundResource(R.drawable.success_layout);
                email.setBackgroundResource(R.drawable.success_layout);
                password.setBackgroundResource(R.drawable.success_layout);
                Toast.makeText(this, " Signed Up Successfully", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(RegistrationPage.this,MainActivity.class);
                startActivity(intent);

            } else{
                name.setBackgroundResource(R.drawable.error_layout);
                textView.setBackgroundResource(R.drawable.error_layout);
                number.setBackgroundResource(R.drawable.error_layout);
                email.setBackgroundResource(R.drawable.error_layout);
                password.setBackgroundResource(R.drawable.error_layout);
                Toast.makeText(this, "Invalid data ", Toast.LENGTH_SHORT).show();
            }
        }

//        if(validateNumber(number1) | validateName(name1))
//        {
//            Toast.makeText(this, ""+name1+number1, Toast.LENGTH_SHORT).show();
//            if(number1.equals("+919876543210") && name1.equals("user123_name")) {
//                Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
//            } else{
//                name.setBackgroundResource(R.drawable.error_layout);
//                number.setBackgroundResource(R.drawable.error_layout);
//                Toast.makeText(this, "Invalid data ", Toast.LENGTH_SHORT).show();
//            }
//        }
//
//        if (validateEmail(email1) | validatePassword(password1)) {
//            Toast.makeText(this, "" + email1 + password1, Toast.LENGTH_SHORT).show();
//            if (email1.equals("admin123@gmail.com") && password1.equals("Password@123")) {
//                Toast.makeText(this, "Login Successfully", Toast.LENGTH_SHORT).show();
//            } else {
//                email.setBackgroundResource(R.drawable.error_layout);
//                password.setBackgroundResource((R.drawable.error_layout));
//                Toast.makeText(this, "Invalid email or Password", Toast.LENGTH_SHORT).show();
//            }
//        }



    }

    private boolean validateEmail(String email1){
        if(email1.isEmpty())
        {
            err_email.setText("Email field is required");
            email.setBackgroundResource(R.drawable.error_layout);
            return false;
        }
        if(!EMAIL_ADDRESS_REGX.matcher(email1).matches()){
            err_email.setText("Invalid email");
            email.setBackgroundResource(R.drawable.error_layout);

            return false;
        }
        email.setBackgroundResource(R.drawable.success_layout);
        err_email.setText("");
        return true;


    }
    private boolean validatePassword(String password1){
        if(password1.isEmpty()){
            err_password.setText("Password field is required");
            password.setBackgroundResource((R.drawable.error_layout));
            return false;
        }
        if(!PASSWORD_REGX.matcher(password1).matches()){
            err_password.setText("invalid password");
            password.setBackgroundResource(R.drawable.error_layout);
            return false;
        }
        password.setBackgroundResource(R.drawable.success_layout);
        err_password.setText("");
        return true;
    }



    private boolean validateName(String name1){

        if(name1.isEmpty()){
            err_name.setText("Please Field is required ");
            name.setBackgroundResource(R.drawable.error_layout);
            return false;
        }
//        if(!USER_NAME_REGX.matcher(name1).matches()){
//            err_name.setText("Invalid User name");
//            err_name.setBackgroundResource(R.drawable.error_layout);
//            return false;
//        }
        name.setBackgroundResource(R.drawable.success_layout);
        err_password.setText("");
        return true;


    }

    private boolean validateNumber(String number1){
        if(number1.isEmpty()){
            err_number.setText("Please field is required");
            number.setBackgroundResource(R.drawable.error_layout);
            return false;
        }
        if(!NUMBER_REGX.matcher(number1).matches()){
            err_number.setText("Invalid number");
            number.setBackgroundResource(R.drawable.error_layout);
            return false;
        }
        number.setBackgroundResource(R.drawable.success_layout);
        err_number.setText("");
        return true;

    }



    private boolean validateGender(String gender)
    {
        if(gender.isEmpty()){
            textView.setText(("Gender is required"));
            return false;
        }
        textView.setTextColor(Color.parseColor("#4CAF50"));
        textView.setText("Success");
        return true;

    }



}
