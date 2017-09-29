package megaleios.com.myseeds.Models;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by user on 14/07/17.
 */

public class Campaing implements Serializable {
    @SerializedName("institutionId")
    private String institutionId;
    //    @SerializedName("profile")
//    private Profile profile;
    @SerializedName("message")
    private String message;
    @SerializedName("date")
    private String date;
    @SerializedName("start")
    private String start;
    @SerializedName("photo")
    private String photo;
    @SerializedName("likes")
    private int likes;
    @SerializedName("coments")
    private int coments;
    @SerializedName("referenceId")
    private String referenceId;
    @SerializedName("type")
    private int type;
    @SerializedName("name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @SerializedName("id")
    private String id;

    @SerializedName("liked")
    private boolean liked;



    @SerializedName("formattedAddress")
    private String formattedAddress;

//    public long getCreated() {
//        return created;
//    }
//
//    public void setCreated(long created) {
//        this.created = created;
//    }
//
//    public Profile getProfile() {
//        return profile;
//    }
//
//    public void setProfile(Profile profile) {
//        this.profile = profile;
//    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getComents() {
        return coments;
    }

    public void setComents(int coments) {
        this.coments = coments;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFormattedAddress() {
        return formattedAddress;
    }

    public void setFormattedAddress(String formattedAddress) {
        this.formattedAddress = formattedAddress;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }
}
