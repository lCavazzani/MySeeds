package megaleios.com.myseeds.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by MegaDev02 on 18/07/2017.
 */

public class Auth implements Serializable {

    @SerializedName("access_token")
    public String accessToken;
    @SerializedName("firstName")
    public String firstName;
    @SerializedName("lastName")
    public String lastName;
    @SerializedName("phone")
    public String phone;
    @SerializedName("email")
    public String email;
    @SerializedName("photoProfile")
    public String photoProfile;
    @SerializedName("description")
    public String description;
    @SerializedName("cityId")
    public String cityId;
    @SerializedName("stateId")
    public String stateId;
    @SerializedName("cityName")
    public String cityName;
    @SerializedName("stateName")
    public String stateName;
    @SerializedName("posts")
    public int posts;
    @SerializedName("friends")
    public int friends;
    @SerializedName("id")
    public String id;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhotoProfile() {
        return photoProfile;
    }

    public void setPhotoProfile(String photoProfile) {
        this.photoProfile = photoProfile;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getStateId() {
        return stateId;
    }

    public void setStateId(String stateId) {
        this.stateId = stateId;
    }

    public String getCityName() {
        return (cityName == null) ? "..." : cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getStateName() {
        return (stateName == null) ? "..." : stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public int getPosts() {
        return posts;
    }

    public void setPosts(int posts) {
        this.posts = posts;
    }

    public int getFriends() {
        return friends;
    }

    public void setFriends(int friends) {
        this.friends = friends;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
