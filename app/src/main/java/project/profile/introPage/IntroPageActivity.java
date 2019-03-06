package project.profile.introPage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import project.profile.R;
import project.profile.profileList.ProfileListActivity;
import project.profile.registrationOne.RegistrationOneActivity;
import project.profile.utility.AppConstants;

public class IntroPageActivity extends AppCompatActivity {
    @BindView(R.id.btn_create_account_intro_page)
    Button btnCreateAccountIntroPage;
    @BindView(R.id.tv_green_flag)
    TextView tvGreenFlag;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_page);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_create_account_intro_page)
    public void onViewClicked() {

    }

    @OnClick({R.id.tv_green_flag, R.id.btn_create_account_intro_page})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_green_flag:
//                Bundle bundle = new Bundle();
//                Intent i = new Intent(this, ProfileListActivity.class);
//                bundle.putString(AppConstants.SKIP_DATA, "SKIP");
//                i.putExtras(bundle);
//                startActivity(i);
                break;
            case R.id.btn_create_account_intro_page:
                Bundle bundle1 = new Bundle();

                Intent intent1 = new Intent(this, RegistrationOneActivity.class);
               // bundle1.putString(AppConstants.SKIP_DATA, "NEW");
                intent1.putExtras(bundle1);
                startActivity(intent1);

                break;
        }
    }
}
