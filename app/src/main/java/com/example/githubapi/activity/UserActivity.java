package com.example.githubapi.activity;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.githubapi.R;
import com.example.githubapi.Tasks.ImageDownloader;
import com.example.githubapi.model.GitHubUser;
import com.example.githubapi.rest.APIClient;
import com.example.githubapi.rest.GitHubUserEndPoints;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserActivity extends AppCompatActivity {

    private ImageView avatarImg;
    private TextView userNameTV, followersTV, followingTV, logIn, email;
    private Button ownedrepos;
    private Bundle extras;
    private String newString;
    private Bitmap myImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        avatarImg = (ImageView) findViewById(R.id.avatar);
        userNameTV = (TextView) findViewById(R.id.username);
        followersTV = (TextView) findViewById(R.id.followers);
        followingTV = (TextView) findViewById(R.id.following);
        logIn = (TextView) findViewById(R.id.logIn);
        email = (TextView) findViewById(R.id.email);
        ownedrepos = (Button) findViewById(R.id.ownedReposBtn);

        extras = getIntent().getExtras();
        newString = extras.getString("STRING_I_NEED");

        System.out.println(newString);

        loadData();

    }

    private void loadOwnRepos() {


    }

    private void loadData() {
        final GitHubUserEndPoints apiService =
                APIClient.getClient().create(GitHubUserEndPoints.class);

        Call<GitHubUser> call = apiService.getUser(newString);
        call.enqueue(new Callback<GitHubUser>() {
            @Override
            public void onResponse(Call<GitHubUser> call, Response<GitHubUser> response) {

                if (response.body().getName() == null) {
                    userNameTV.setText("No name provided");
                } else {
                    userNameTV.setText("Username: " + response.body().getName());
                }

                followersTV.setText("Followers: " + response.body().getFollowers());
                followingTV.setText("Following: " + response.body().getFollowing());
                logIn.setText("LogIn: " + response.body().getLogin());

                if (response.body().getEmail() == null) {
                    email.setText("No email provided");
                } else {
                    email.setText("Email: " + response.body().getEmail());
                }

                ImageDownloader task = new ImageDownloader();

                try{
                    myImage = task.execute(response.body().getAvatar()).get();

                    avatarImg.setImageBitmap(myImage);
                    avatarImg.getLayoutParams().height = 220;
                    avatarImg.getLayoutParams().width = 220;

                }catch (Exception e){
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(Call<GitHubUser> call, Throwable t) {

            }
        });
    }


}
