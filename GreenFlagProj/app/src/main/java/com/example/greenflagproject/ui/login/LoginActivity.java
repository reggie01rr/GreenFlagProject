package com.example.greenflagproject.ui.login;

import android.app.Activity;

import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.wifi.hotspot2.pps.Credential;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.greenflagproject.R;
import com.example.greenflagproject.ui.login.LoginViewModel;
import com.example.greenflagproject.ui.login.LoginViewModelFactory;
import com.example.greenflagproject.ui.main.CreateAccount;

import java.util.regex.Pattern;


public class LoginActivity extends AppCompatActivity {


    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[0-9])" +
                    "(?=.*[a-z])" +
                    "(?=.*[A-Z])" +
                  //  "(?=\\S+$)"+
                    ".{8,}" +
                    "$");
    private Toolbar toolbarReturn;
    private ImageView check1;
    private ImageView check2;
    private ImageView check3;
    private LoginViewModel loginViewModel;
    private EditText email;
    private EditText password;
    private EditText password2;
    private ImageButton btnNext;
    private ImageView back;
    private ImageView errorX;
    private ImageView errorX2;
    private ImageView errorX3;
    private EditText passwordBorder;
    private EditText passwordBorder2;
    private final String CREDENTIAL_SHARED_PREF = "out_shared_pref";


    TextView Emailerror,PasswordError,ConfirmPassError;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        btnNext = findViewById(R.id.btnNext);
        errorX = findViewById(R.id.errorX);
        errorX2 = findViewById(R.id.errorX2);
        errorX3 = findViewById(R.id.errorX3);
        email = findViewById(R.id.txtEmail);
        password = findViewById(R.id.txtPassword3);
        password2 = findViewById(R.id.txtPassword2);
        passwordBorder = findViewById(R.id.txtEmail);
        check1 = findViewById(R.id.correct1);
        check2 = findViewById(R.id.correct2);
        check3 = findViewById(R.id.correct3);
        back = findViewById(R.id.back);
        //
        Emailerror = findViewById(R.id.EmailAlert);
        PasswordError = findViewById(R.id.ConfirmPassAlert);
        ConfirmPassError = findViewById(R.id.ConfirmPassAlert);
        initView();
    }


    private void initView() {
            back = findViewById(R.id.back);
            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
         /*   (__)->{

        Intent navigateBack = new Intent();
            navigateBack.setClass(LoginActivity.this, CreateAccount.class);
            startActivity(navigateBack);
        */
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateEmail();
                validatePassword();
            }
        });

        }

//      I think the red letters looks better than the border around the error messages
    // so I left it off and did it my way. If you need me to make a border around the
    // messages, the I will make one if need.


    private boolean validateEmail() {
        String emailInput = email.getText().toString().trim();
        if (emailInput.isEmpty()) {
            Emailerror.setText("     Field can't be empty");
            email.setBackground(ContextCompat.getDrawable(this, R.drawable.border_error_textbox));
            btnNext.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
            errorX.setVisibility(View.VISIBLE);

            return false;

        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            Emailerror.setText("     Please enter a valid email address");
            email.setBackground(ContextCompat.getDrawable(this, R.drawable.border_error_textbox));
            btnNext.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
            errorX.setVisibility(View.VISIBLE);

            return false;
        } else {
            Emailerror.setError(null);
            check1.setVisibility(View.VISIBLE);
            email.setBackground(ContextCompat.getDrawable(this, R.drawable.border_correct_textbox));
            return true;
        }
    }
    private boolean validatePassword() {
        String passwordInput = password.getText().toString().trim();
        String confirmPasswordInput = password2.getText().toString().trim();
        if (passwordInput.isEmpty()) {

            PasswordError.setText("     Field can't be empty");
//            password.setBackground(ContextCompat.getDrawable(this, R.drawable.border_error_textbox));
//            password2.setBackground(ContextCompat.getDrawable(this, R.drawable.border_error_textbox));
            password.setBackground(getDrawable(R.drawable.border_error_textbox));
            password2.setBackground(getDrawable(R.drawable.border_error_textbox));
            btnNext.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
            errorX2.setVisibility(View.VISIBLE);


            return false;

        }  else if(!PASSWORD_PATTERN.matcher(passwordInput).matches()){
            PasswordError.setText("     Password too weak");
//            password.setBackground(ContextCompat.getDrawable(this, R.drawable.border_error_textbox));
//            password2.setBackground(ContextCompat.getDrawable(this, R.drawable.border_error_textbox));

            password.setBackground(getDrawable(R.drawable.border_error_textbox));
            password2.setBackground(getDrawable(R.drawable.border_error_textbox));
            btnNext.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
            errorX2.setVisibility(View.VISIBLE);

            return false;
        }
        else if (passwordInput.length()<8) {
            PasswordError.setText("     Password must be at least 8 characters");
//            password.setBackground(ContextCompat.getDrawable(this, R.drawable.border_error_textbox));
            password.setBackground(getDrawable(R.drawable.border_error_textbox));
            password2.setBackground(getDrawable(R.drawable.border_error_textbox));
            btnNext.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
            errorX2.setVisibility(View.VISIBLE);


            return false;

        }


        if (!passwordInput.equals(confirmPasswordInput)) {
            ConfirmPassError.setText("     Password did not be matched");
            password2.setBackground(ContextCompat.getDrawable(this, R.drawable.border_error_textbox));

            btnNext.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
            errorX2.setVisibility(View.VISIBLE);

            return false;

        }else {
            ConfirmPassError.setText("Password Matched");
            ConfirmPassError.setTextColor(Color.GREEN);
            check2.setVisibility(View.VISIBLE);
            check3.setVisibility(View.VISIBLE);
            password.setBackground(ContextCompat.getDrawable(this, R.drawable.border_correct_textbox));
            password2.setBackground(ContextCompat.getDrawable(this, R.drawable.border_correct_textbox));
            Toast.makeText(this, "Account Created", Toast.LENGTH_SHORT).show();
            return true;
        }
    }

    }

/*
        btnNext.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(LoginActivity.this, CreateAccount.class);
            startActivity(intent);
        }

    });



        btnNext.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            SharedPreferences credentials = getSharedPreferences(CREDENTIAL_SHARED_PREF, Context.MODE_PRIVATE);
            String strUsername = credentials.getString("Username", null);
            String strPassword = credentials.getString("Password", null);

            String username_from_me = email.getText().toString();
            String password_from_me = password.getText().toString();
            String password_from_me2 = password2.getText().toString();


            if (strUsername != null && strUsername.equalsIgnoreCase(username_from_me)) {
                if (strPassword != null && password_from_me != null && strPassword.equalsIgnoreCase(password_from_me2)) {
                    Toast.makeText(LoginActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
            }
        }
    });
}










   @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        email = findViewById(R.id.txtEmail);
        password = findViewById(R.id.txtPassword);
        password2 = findViewById(R.id.txtPassword2);
        btnNext = findViewById(R.id.btnNext);
*/


