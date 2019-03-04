package project.profile.registrationOne;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import project.profile.R;
import project.profile.registrationTwo.RegistrationTwoActivity;
import project.profile.utility.AppConstants;

public class RegistrationOneActivity extends AppCompatActivity {


    @BindView(R.id.et_email)
    EditText etEmail;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.et_repeat_password)
    EditText etRepeatPassword;
    @BindView(R.id.btn_back_create_ac)
    TextView btnBackCreateAc;
    @BindView(R.id.tv_valid_password_instruction)
    TextView tvValidPasswordInstruction;

    String email, password, repeatPassword;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_one);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_back_create_ac, R.id.btn_next_create_account})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_back_create_ac:
                onBackPressed();
                break;
            case R.id.btn_next_create_account:

                // Validating user's data.
                email = etEmail.getText().toString().trim();
                password = etPassword.getText().toString().trim();
                repeatPassword = etRepeatPassword.getText().toString().trim();

                if (email.isEmpty()) {
                    etEmail.setError("Email Required !");

                } else if (!email.matches(emailPattern)) {
                    etEmail.setError("Valid Email Required !");

                } else if (password.isEmpty()) {
                    etPassword.setError("Password Required !");
                } else if (password.length() <= 8 && !isValidPassword(password)) {
                    etPassword.setError("Valid Password Required !");
                    tvValidPasswordInstruction.setTextColor(Color.RED);

                } else if (repeatPassword.isEmpty()) {
                    etRepeatPassword.setError("Repeat Password Required !");

                } else if (!repeatPassword.equalsIgnoreCase(password)) {
                    etRepeatPassword.setError("Password Don't Match !");
                } else {


                    if (!email.equalsIgnoreCase("")
                            && email.matches(emailPattern)
                            && !password.equalsIgnoreCase("")
                            && !repeatPassword.equalsIgnoreCase("")
                            && !email.equalsIgnoreCase("")
                            && etRepeatPassword.getText().toString().trim().equalsIgnoreCase(password)) {

                        //  Receiving user's data and proceeding to next page.

                        Intent intent = new Intent(RegistrationOneActivity.this,
                                RegistrationTwoActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString(AppConstants.USER_EMAIL_DATA_KEY, email);
                        bundle.putString(AppConstants.USER_PASSWORD_DATA_KEY, password);
                        bundle.putString(AppConstants.USER_REPEAT_PASSWORD_DATA_KEY, repeatPassword);
                        Log.d("inputDataOne", email + " " + password + " " + repeatPassword);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                }
                break;
        }
    }


    public static boolean isValidPassword(final String password) {

        Pattern pattern;
        Matcher matcher;
       // final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
        final String PASSWORD_PATTERN = "^([a-zA-Z+]+[0-9+]+[&@!#+]+)$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();

    }
}
