package megaleios.com.myseeds.Models;
import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.List;

/**
 * Created by user on 14/07/17.
 */

public class Institution implements Serializable {


    public Institution() {
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getTotalContributions() {
        return totalContributions;
    }

    public void setTotalContributions(int totalContributions) {
        this.totalContributions = totalContributions;
    }

    public String getTotalCampaigns() {
        return totalCampaigns;
    }

    public void setTotalCampaigns(String totalCampaigns) {
        this.totalCampaigns = totalCampaigns;
    }

    public int getMinimumContribution() {
        return minimumContribution;
    }

    public void setMinimumContribution(int minimumContribution) {
        this.minimumContribution = minimumContribution;
    }


//    @SerializedName("profile")
//    private Profile profile;
    @SerializedName("address")
    private String address;
    @SerializedName("isFavorite")
    private boolean isFavorite;
    @SerializedName("name")
    private String name;
    @SerializedName("logo")
    private String logo;
    @SerializedName("id")
    private String id;
    @SerializedName("totalContributions")
    private int totalContributions;
    @SerializedName("totalCampaigns")
    private String totalCampaigns;
    @SerializedName("minimumContribution")
    private int minimumContribution;


//    public Profile getProfile() {
//        return profile;
//    }
//
//    public void setProfile(Profile profile) {
//        this.profile = profile;
//    }

}
