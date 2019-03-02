package project.profile.introPage;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;
import project.profile.R;
import project.profile.registrationOne.RegistrationOneActivity;

public class IntroPageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_page);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_create_account_intro_page)
    public void onViewClicked() {
        Intent intent = new Intent(this, RegistrationOneActivity.class);
        startActivity(intent);
    }
}
