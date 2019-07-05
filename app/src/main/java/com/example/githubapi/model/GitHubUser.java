package com.example.githubapi.model;

import com.google.gson.annotations.SerializedName;

public class GitHubUser {

    @SerializedName("email")
    private String email;

    @SerializedName("avatar_url")
    private String avatar;

    @SerializedName("followers")
    private String followers;

    @SerializedName("following")
    private String following;

    @SerializedName("name")
    private String name;

    @SerializedName("login")
    private String login;


    public GitHubUser(String email, String avatar, String followers, String following, String name, String login) {
        this.email = email;
        this.avatar = avatar;
        this.followers = followers;
        this.following = following;
        this.name = name;
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFollowers() {
        return followers;
    }

    public void setFollowers(String followers) {
        this.followers = followers;
    }

    public String getFollowing() {
        return following;
    }

    public void setFollowing(String following) {
        this.following = following;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



}
