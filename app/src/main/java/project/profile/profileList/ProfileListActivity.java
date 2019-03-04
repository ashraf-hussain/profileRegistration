package project.profile.profileList;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import project.profile.R;
import project.profile.utility.AppConstants;

public class ProfileListActivity extends AppCompatActivity {

    @BindView(R.id.tv_greet_user_name)
    TextView tvGreetUserName;
    @BindView(R.id.rv_user_profile_list)
    RecyclerView rvUserProfile;
    ArrayList<UserProfileModel> profileModelArrayList;
    UserProfileModel userProfileModel;
    UserProfileAdapter userProfileAdapter;


    String email, name, username, userDob, gender, postalAddress, userCountry;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        ButterKnife.bind(this);

        //Setting up recycler view
        rvUserProfile.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvUserProfile.setLayoutManager(linearLayoutManager);
        Bundle bundle = getIntent().getExtras();

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
//            fileOutputStream.write(email.getBytes());
            fileOutputStream.write(gender.getBytes());
           // fileOutputStream.write(username.getBytes());
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

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);

                    Log.d("fileData", receiveString);

                }

                inputStream.close();
                String ret = stringBuilder.toString();
                Log.d("fileData", ret);

            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }



    }
}
