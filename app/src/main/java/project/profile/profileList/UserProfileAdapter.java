package project.profile.profileList;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import project.profile.R;

public class UserProfileAdapter extends RecyclerView.Adapter<UserProfileAdapter.ViewHolder> {


    private List<UserProfileModel> userProfileModelList;
    UserProfileModel userProfileModel;
    Context context;

    public UserProfileAdapter(List<UserProfileModel> userProfileModelList) {
        this.userProfileModelList = userProfileModelList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.adapter_user_profile, viewGroup, false);
        context = view.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        userProfileModel = userProfileModelList.get(i);

        viewHolder.tvUserName.setText(userProfileModel.getName());
        viewHolder.tvCountry.setText(userProfileModel.getCountry());
        viewHolder.tvUserDob.setText(userProfileModel.getUserDob());
        viewHolder.tvUserGender.setText(userProfileModel.getGender());
        viewHolder.tvStreet.setText(userProfileModel.getPostalAddress());
    }

    @Override
    public int getItemCount() {
        Log.d("size", String.valueOf(userProfileModelList.size()));
        return userProfileModelList.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_user_name)
        TextView tvUserName;
        @BindView(R.id.tv_state_Address)
        TextView tvStateAddress;
        @BindView(R.id.tv_street)
        TextView tvStreet;
        @BindView(R.id.tv_country)
        TextView tvCountry;
        @BindView(R.id.tv_user_dob)
        TextView tvUserDob;
        @BindView(R.id.tv_user_gender)
        TextView tvUserGender;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);


        }
    }
}
