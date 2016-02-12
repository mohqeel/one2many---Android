package com.cmpe195.sjsu.one2many;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.Map;

public class sign_up extends AppCompatActivity {
    EditText username;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Firebase.setAndroidContext(this);
    }

    public void signUpButton(final View view){
        username = (EditText) findViewById(R.id.userNameTextEntry);
        password = (EditText) findViewById(R.id.passwordTextEntry);
        String usernameString = username.getText().toString();
        String passwordString = password.getText().toString();
        Firebase myFirebaseRef = new Firebase("https://luminous-inferno-1813.firebaseio.com/");
        myFirebaseRef.createUser(usernameString, passwordString, new Firebase.ValueResultHandler<Map<String, Object>>() {
            @Override
            public void onSuccess(Map<String, Object> result) {
                System.out.println("Successfully created user account with uid: " + result.get("uid"));
                Snackbar.make(view, "There was success", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
            @Override
            public void onError(FirebaseError firebaseError) {
                Snackbar.make(view, "There was an error", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }


}
