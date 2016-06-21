package net.amigochi.amigochi.features.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;

import net.amigochi.amigochi.R;
import net.amigochi.amigochi.features.home.MainActivity;

/**
 * Created by sierisimo on 20/06/16.
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();
    }

    private void init() {
        findViewById(R.id.acb_ac_login_signin).setOnClickListener(this);
    }

    private boolean validate() {
        String content = ((EditText) findViewById(R.id.et_ac_login_email)).getText().toString();
        if (content.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(content).matches()) {
            setErrorMessage((TextInputLayout) findViewById(R.id.til_ac_login_email), R.string.error_login_email);

            return false;
        }

        content = ((EditText) findViewById(R.id.et_ac_login_password)).getText().toString();

        if (content.isEmpty() || content.length() < 8) {
            setErrorMessage((TextInputLayout) findViewById(R.id.til_ac_login_email), R.string.error_login_password);

            return false;
        }
        
        return true;
    }

    private void setErrorMessage(TextInputLayout textInputLayout, int errorMsgId) {
        textInputLayout.setErrorEnabled(true);
        textInputLayout.setError(getString(errorMsgId));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.acb_ac_login_signin:
                if (validate()) {
                    startActivity(new Intent(this, MainActivity.class));
                    finish();
                } else {
                    Snackbar.make(findViewById(android.R.id.content), R.string.error_login_check, Snackbar.LENGTH_SHORT).show();
                }
                break;
        }
    }
}