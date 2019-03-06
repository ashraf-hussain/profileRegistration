package project.profile.profileList;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import project.profile.R;
import project.profile.registrationOne.RegistrationOneActivity;
import project.profile.sqlite.UserDataSqliteDatabase;
import project.profile.utility.AppConstants;

public class ProfileListActivity extends AppCompatActivity {

    @BindView(R.id.tv_greet_user_name)
    TextView tvGreetUserName;
    @BindView(R.id.iv_add_button)
    ImageView fbAddButton;
    @BindView(R.id.rv_user_profile_list)
    RecyclerView rvUserProfile;
    ArrayList<UserProfileModel> profileModelArrayList;
    UserProfileModel userProfileModel;
    UserProfileAdapter userProfileAdapter;
    SQLiteDatabase sqLiteDatabase;
    ContentValues contentValues;

    UserDataSqliteDatabase userDataSqliteDatabase;
    Bundle bundle;
    String check = "SKIP";
    ArrayList<UserProfileModel> userProfileModelArrayList;


    String email, name, username, userDob, gender, postalAddress, userCountry;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        ButterKnife.bind(this);
        userDataSqliteDatabase = new UserDataSqliteDatabase(this);
        //Setting up recycler view
        rvUserProfile.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvUserProfile.setLayoutManager(linearLayoutManager);
        bundle = getIntent().getExtras();

        populateData();
//        if (sqLiteDatabase.insert("USER", null, contentValues) > 0) {
//            //setting  adapter to recycler view.
//            readUserData();
//            rvUserProfile.setAdapter(userProfileAdapter);
//        } else {
//        }


        //check = bundle.getString(AppConstants.SKIP_DATA);
        // Log.d("checking", String.valueOf(check));


//        if (!check.equalsIgnoreCase("SKIP")) {
//
//            Log.d("skipCheck", "FALSE!");
//
//
//        } else {
//
//            Log.d("skipCheck", "TRUE!");
//
//
//        }
    }

    private void populateData() {
        //getting user's final data
        if (bundle != null) {
            name = bundle.getString(AppConstants.USER_NAME_DATA_KEY);
            userDob = bundle.getString(AppConstants.USER_DOB_DATA_KEY);
            email = bundle.getString(AppConstants.USER_EMAIL_DATA_KEY);
            gender = bundle.getString(AppConstants.USER_GENDER_DATA_KEY);
            username = bundle.getString(AppConstants.USER_USERNAME_DATA_KEY);
            userCountry = bundle.getString(AppConstants.USER_COUNTRY_DATA_KEY);
            postalAddress = bundle.getString(AppConstants.USER_POSTAL_ADDRESS_DATA_KEY);

            Log.d("inputThree", userCountry + " ");
        }


        saveUserData();

        File userFile;
        //setting filename
        String userDataFile = "userDataFile";
        //helps to write into file
        FileOutputStream fileOutputStream;

        new File(this.getFilesDir(), userDataFile);


        //defies path of saved file
        Log.d("ss", String.valueOf(getFilesDir()));

        //writing into file which can be accessed
        // via device explorer acct-> data -> data-> project package filename.

        try {
            fileOutputStream = openFileOutput(userDataFile, Context.MODE_PRIVATE);
            fileOutputStream.write(name.getBytes());
            fileOutputStream.write(userDob.getBytes());
            fileOutputStream.write(email.getBytes());
            fileOutputStream.write(gender.getBytes());
            fileOutputStream.write(username.getBytes());
            fileOutputStream.write(userCountry.getBytes());
            fileOutputStream.write(postalAddress.getBytes());
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        tvGreetUserName.setText(name);

        //initializing array list and user model.
        profileModelArrayList = new ArrayList<>();
        userProfileModel = new UserProfileModel();

        //adding up users data to model and array list.
        userProfileModel.setName(name);
        userProfileModel.setEmail(email);
        userProfileModel.setGender(gender);
        userProfileModel.setUserDob(userDob);
        userProfileModel.setUsername(username);
        userProfileModel.setCountry(userCountry);
        userProfileModel.setPostalAddress(postalAddress);
        profileModelArrayList.add(userProfileModel);

        //setting  adapter to recycler view.
        userProfileAdapter = new UserProfileAdapter(profileModelArrayList);
        rvUserProfile.setAdapter(userProfileAdapter);
        //userProfileAdapter.notifyDataSetChanged();

        try {
            InputStream inputStream = this.openFileInput(userDataFile);

            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveUserData = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ((receiveUserData = bufferedReader.readLine()) != null) {
                    stringBuilder.append(receiveUserData);

                    Log.d("fileData", receiveUserData);

                }

                inputStream.close();
            }
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }
    }


    private void saveUserData() {


        sqLiteDatabase = userDataSqliteDatabase.getWritableDatabase();

        contentValues = new ContentValues();

        contentValues.put("name", name);
        contentValues.put("userName", username);
        contentValues.put("gender", gender);
        contentValues.put("email", email);
        contentValues.put("country", userCountry);
        contentValues.put("userDob", userDob);
        contentValues.put("address", postalAddress);
        Log.d("saveUserTest: ", String.valueOf(sqLiteDatabase.insert("USER", null, contentValues)));

        if (sqLiteDatabase.insert("USER", null, contentValues) > 0) {
            Toast.makeText(this, "DATA SAVED", Toast.LENGTH_SHORT).show();


            Log.d("saveUserData: ", "saved");
        } else {
            Toast.makeText(this, "NOT SAVED", Toast.LENGTH_SHORT).show();

        }

    }

    private void readUserData() {

        SQLiteDatabase sqLiteDatabase = userDataSqliteDatabase.getReadableDatabase();

        String[] projection = {"_id", "name", "email",
                "address", "userDob", "gender", "country", "userName"};


        Cursor cursor = sqLiteDatabase.query(
                "USER",
                projection,
                null,
                null,
                null,
                null,
                null
        );
        String[] columnNames = cursor.getColumnNames();
        Log.d("test1", String.valueOf(columnNames));


        List userId = new ArrayList<>();
        while (cursor.moveToNext()) {

            long itemId =
                    cursor.getLong(
                    cursor.getColumnIndexOrThrow("_id"));


           // userProfileModel.setUsername(userName);
            //userProfileModelArrayList.add(userProfileModel);

            userId.add(itemId);

            int col = cursor.getColumnCount();
            int row = cursor.getCount();

            Log.d("column", String.valueOf(col));
            Log.d("row", String.valueOf(row));

            Log.d("test2", String.valueOf(itemId));
            String result="";
            for (String items:columnNames){
                result += items + "\t\t";

            }


            Log.d("readUserData: ", result);
                result += "\n";

            for(cursor.moveToFirst();!cursor.isAfterLast(); cursor.moveToNext()){
                result += cursor.getString(1);
                result += cursor.getString(2);
                result += cursor.getString(3);
                result += cursor.getString(4);
                result += cursor.getString(5);
                result += cursor.getString(6);
                result += cursor.getString(7);
            }

            cursor.close();

            Log.d("datalist", result);



        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }

    @OnClick(R.id.iv_add_button)
    public void onViewClicked() {

        Intent i = new Intent(ProfileListActivity.this, RegistrationOneActivity.class);
        startActivity(i);
    }
}
