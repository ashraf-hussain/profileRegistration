package project.profile.registrationTwo;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import project.profile.R;
import project.profile.profileList.ProfileListActivity;
import project.profile.utility.AppConstants;

public class RegistrationTwoActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.et_dob)
    EditText etDob;
    @BindView(R.id.btn_dob_picker)
    Button btnDobPicker;
    @BindView(R.id.rb_gender)
    RadioGroup rbGender;
    @BindView(R.id.iv_user_profile)
    ImageView ivUserProfile;
    @BindView(R.id.et_postal_address)
    EditText etPostalAddress;
    @BindView(R.id.spinner_country_list)
    Spinner spinnerCountryList;
    DatePickerDialog.OnDateSetListener date;
    Calendar calendar;

    String email, password, repeatPassword, name, username, userPassword,
            userDob, gender, postalAddress, userCountry;
    String[] countryNames;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_two);
        ButterKnife.bind(this);
        calendar = Calendar.getInstance();
        etDob.setEnabled(false);
        spinnerCountryList.setPrompt(getResources().getString(R.string.select_country));

        //Spinner drop down elements

        countryNames = new String[]{"Nepal", "India", "United States ", "Japan", "Malaysia",
                "New Zealand", "Indonesia", "Australia", "Not Specified"};
        int flags[] = {R.drawable.ic_nepal, R.drawable.ic_india, R.drawable.ic_usa,
                R.drawable.ic_japan, R.drawable.ic_malaysia, R.drawable.ic_newzealand,
                R.drawable.ic_indonesia, R.drawable.ic_australia, R.drawable.ic_county};

        //Creating adapter for spinner
        // Initializing an ArrayAdapter
        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), flags, countryNames);


        spinnerCountryList.setAdapter(customAdapter);
        spinnerCountryList.setSelection(customAdapter.getCount());
        spinnerCountryList.setOnItemSelectedListener(this);
        customAdapter.notifyDataSetChanged();

        // Toast.makeText(this, userCountry, Toast.LENGTH_SHORT).show();


        //DatePicker
        date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateDate();
            }

            private void updateDate() {
                String dateFormat = "MM-dd-yy";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat, Locale.US);
                etDob.setText(simpleDateFormat.format(calendar.getTime()));
            }
        };

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


    //Performing action onItemSelected and onNothing selected
    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        Toast.makeText(getApplicationContext(), countryNames[position], Toast.LENGTH_LONG).show();
        userCountry = countryNames[position];
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }


    public void onRadioButtonClicked(View view) {

        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.rb_female:

                if (checked)
                    gender = "Female";
                //Toast.makeText(this, gender, Toast.LENGTH_SHORT).show();

                break;

            case R.id.rb_male:
                if (checked) {
                    gender = "Male";
                    // Toast.makeText(this, gender, Toast.LENGTH_SHORT).show();

                }
                break;
            case R.id.rb_not_specified:
                if (checked) {
                    gender = "Not Specified";
                    //  Toast.makeText(this, gender, Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @OnClick({R.id.btn_change_pic, R.id.btn_dob_picker, R.id.rb_female, R.id.rb_male,
            R.id.rb_not_specified, R.id.btn_save, R.id.et_dob})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_change_pic:
                break;
            case R.id.btn_dob_picker:
                new DatePickerDialog(RegistrationTwoActivity.this, date, calendar
                        .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
                break;
            case R.id.et_dob:
                new DatePickerDialog(RegistrationTwoActivity.this, date, calendar
                        .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
                break;
            case R.id.rb_female:
                onRadioButtonClicked(view);

                break;
            case R.id.rb_male:
                onRadioButtonClicked(view);
                break;
            case R.id.rb_not_specified:
                onRadioButtonClicked(view);
                break;
            case R.id.btn_save:
                processData();


                break;
        }

    }

    private void processData() {
        // Validating user's data.
        name = etName.getText().toString().trim();
        userDob = etDob.getText().toString().trim();
        username = etUsername.getText().toString().trim();
        userPassword = etPassword.getText().toString().trim();
        postalAddress = etPostalAddress.getText().toString().trim();


        if (name.isEmpty()) {
            etName.setError("Name Required !");

        } else if (username.isEmpty()) {
            etUsername.setError("Username Required !");

        } else if (userPassword.isEmpty()) {
            etPassword.setError("Password Required !");

        } else if (!userPassword.equalsIgnoreCase(password)) {
            etPassword.setError("Wrong Password !");

        } else if (userDob.isEmpty()) {
            etDob.setError("DOB Required !");
        } else if (userCountry.equalsIgnoreCase("Not Specified")) {

            Toast.makeText(this, "Please Select Your Country!",
                    Toast.LENGTH_SHORT).show();
        }

        if (!name.equalsIgnoreCase("")
                && !username.equalsIgnoreCase("")
                && !userPassword.equalsIgnoreCase("")
                && !userDob.matches("")
                && !userCountry.equalsIgnoreCase("Not Specified")
                && userPassword.equalsIgnoreCase(password)) {

            Intent intent = new Intent(RegistrationTwoActivity.this,
                    ProfileListActivity.class);
            Bundle bundle = new Bundle();

            bundle.putString(AppConstants.USER_NAME_DATA_KEY, name);
            bundle.putString(AppConstants.USER_DOB_DATA_KEY, userDob);
            bundle.putString(AppConstants.USER_GENDER_DATA_KEY, gender);
            bundle.putString(AppConstants.USER_USERNAME_DATA_KEY, username);
            bundle.putString(AppConstants.USER_COUNTRY_DATA_KEY, userCountry);
            bundle.putString(AppConstants.USER_SAVED_PASSWORD_DATA_KEY, userPassword);
            bundle.putString(AppConstants.USER_POSTAL_ADDRESS_DATA_KEY, postalAddress);
            intent.putExtras(bundle);
            startActivity(intent);

            Log.d("inputDataFromTwo", username + " " + userPassword + " "
                    + userDob + " " + gender + " " + userCountry + " " + postalAddress);
        }

    }
}
