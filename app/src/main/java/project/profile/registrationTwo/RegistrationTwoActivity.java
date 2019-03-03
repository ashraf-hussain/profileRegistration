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


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_two);
        ButterKnife.bind(this);
        calendar = Calendar.getInstance();
        etDob.setEnabled(false);


        //Spinner drop down elements
        List<String> countryList = new ArrayList<String>();
        countryList.add("Nepal");
        countryList.add("USA");
        countryList.add("China");
        countryList.add("Japan");
        countryList.add("India");
        countryList.add("Not-Specified");

        //Creating adapter for spinner
        // Initializing an ArrayAdapter
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                this, R.layout.custom_spinner_country_item, R.id.tv_spinner_country_list,
                countryList
        );
        spinnerArrayAdapter.setDropDownViewResource(R.layout.custom_spinner_country_item);
        spinnerCountryList.setAdapter(spinnerArrayAdapter);
        //attaching adapter to spinner
        spinnerCountryList.setAdapter(spinnerArrayAdapter);
        userCountry = spinnerCountryList.getSelectedItem().toString();

        spinnerCountryList.setSelection(spinnerArrayAdapter.getCount());        //set the hint the default selection so it appears on launch.
        spinnerCountryList.setOnItemSelectedListener(this);
        spinnerArrayAdapter.notifyDataSetChanged();

        Toast.makeText(this, userCountry, Toast.LENGTH_SHORT).show();


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
                String dateFormat = "MM/dd/yy";
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        //On selecting a spinner item
        userCountry = spinnerCountryList.getItemAtPosition(position).toString();
        Log.d("userCountry", userCountry);

        Toast.makeText(this, userCountry, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(this, "Country not selected !", Toast.LENGTH_SHORT).show();
        Log.d("userCountry", "no");

    }

    public void onRadioButtonClicked(View view) {

        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.rb_female:

                if (checked)
                    gender = "female";
                Toast.makeText(this, gender, Toast.LENGTH_SHORT).show();

                break;

            case R.id.rb_male:
                if (checked) {
                    gender = "male";
                    Toast.makeText(this, gender, Toast.LENGTH_SHORT).show();

                }
                break;
            case R.id.rb_not_specified:
                if (checked) {
                    gender = "Not Specified";
                    Toast.makeText(this, gender, Toast.LENGTH_SHORT).show();
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
                } else if (userCountry.equalsIgnoreCase("Not-Specified")) {

                    Toast.makeText(this, "Please Select Your Country!",
                            Toast.LENGTH_SHORT).show();
                }

                if (!name.equalsIgnoreCase("")
                        && !username.equalsIgnoreCase("")
                        && !userPassword.equalsIgnoreCase("")
                        && !userDob.matches("")
                        && !userCountry.equalsIgnoreCase("Not-Specified")
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
                break;
        }

    }


}
