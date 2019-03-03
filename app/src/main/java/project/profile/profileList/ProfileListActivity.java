package project.profile.profileList;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

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



        rvUserProfile.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvUserProfile.setLayoutManager(linearLayoutManager);
        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            name = bundle.getString(AppConstants.USER_NAME_DATA_KEY);
            userDob = bundle.getString(AppConstants.USER_DOB_DATA_KEY);
            email = bundle.getString(AppConstants.USER_EMAIL_DATA_KEY);
            gender = bundle.getString(AppConstants.USER_GENDER_DATA_KEY);
            username = bundle.getString(AppConstants.USER_USERNAME_DATA_KEY);
            userCountry = bundle.getString(AppConstants.USER_COUNTRY_DATA_KEY);
            postalAddress = bundle.getString(AppConstants.USER_POSTAL_ADDRESS_DATA_KEY);

            Log.d("inputthree", name);
        }

        profileModelArrayList = new ArrayList<>();
        userProfileModel = new UserProfileModel();

        userProfileModel.setName(name);
        userProfileModel.setEmail(email);
        userProfileModel.setGender(gender);
        userProfileModel.setUserDob(userDob);
        userProfileModel.setUsername(username);
        userProfileModel.setPostalAddress(postalAddress);
        profileModelArrayList.add(userProfileModel);


        userProfileAdapter = new UserProfileAdapter(profileModelArrayList);
        rvUserProfile.setAdapter(userProfileAdapter);
        Log.d("onn", "adapter");
        //userProfileAdapter.notifyDataSetChanged();

    }
}
