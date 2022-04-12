package com.example.greenflagproject.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.greenflagproject.R;
import com.example.greenflagproject.ui.login.LoginActivity;
import com.example.greenflagproject.ui.login.LoginViewModel;

public class CreateAccount extends AppCompatActivity {

    int mY_PASSWORD_DIALOG_ID = 5;
    private MainViewModel mViewModel;
    private LoginViewModel loginViewModel;
    private EditText email;
    private EditText password;
    private EditText confirmPassword;
    private ImageView btnCreateAcct;
    private final String CREDENTIAL_SHARED_PREF = "out_shared_pref";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createaccount);

        email = findViewById(R.id.txtEmail);
        password = findViewById(R.id.txtPassword);
        confirmPassword = findViewById(R.id.txtPassword2);
        btnCreateAcct = (ImageView) findViewById(R.id.btnCreateAcct);

        btnCreateAcct.setOnClickListener(new View.OnClickListener() {
            //may have to erase the String codes
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(CreateAccount.this, LoginActivity.class);
                startActivity(intent);


            }


        });
        }
        }



/*
    public static CreateAccount newInstance() {
        return new CreateAccount();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment, container, false);


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        // TODO: Use the ViewModel
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        final View layout = inflater.inflate(R.layout.password_dialog, (ViewGroup) findViewById(R.id.root));
        final EditText password1 = (EditText) layout.findViewById(R.id.password2);
        final EditText password2 = (EditText) layout.findViewById(R.id.password);
        final TextView error = (TextView) layout.findViewById(R.id.TextView_PwdProblem);
    }
  public void checkPassword(){

      if (mY_PASSWORD_DIALOG_ID == 5)

  }



            }
        }*/
