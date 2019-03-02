package project.profile.registrationTwo;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import project.profile.R;
import project.profile.utility.AppConstants;

public class RegistrationTwoActivity extends AppCompatActivity {
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.tv_dob)
    TextView tvDob;
    @BindView(R.id.btn_dob_picker)
    Button btnDobPicker;
    @BindView(R.id.rb_gender)
    RadioGroup rbGender;
    @BindView(R.id.iv_user_profile)
    ImageView ivUserProfile;
    @BindView(R.id.et_postal_address)
    EditText etPostalAddress;
    String email, password, repeatPassword;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_two);
        ButterKnife.bind(this);


        //Receiving data via bundle from RegistrationOneActivity class.

        Bundle bundle = getIntent().getExtras();

        //Checking null
        if (bundle != null) {
            email = bundle.getString(AppConstants.USER_EMAIL_DATA_KEY);
            password = bundle.getString(AppConstants.USER_PASSWORD_DATA_KEY);
            repeatPassword = bundle.getString(AppConstants.USER_REPEAT_PASSWORD_DATA_KEY);
            Log.d("inputDataFromOne", email + " " + password + " " + repeatPassword);
        }

    }

    @OnClick({R.id.btn_change_pic, R.id.btn_dob_picker, R.id.rb_female, R.id.rb_male,
            R.id.rb_not_specified})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_change_pic:
                break;
            case R.id.btn_dob_picker:
                break;
            case R.id.rb_female:
                break;
            case R.id.rb_male:
                break;
            case R.id.rb_not_specified:
                break;
        }
    }
}
