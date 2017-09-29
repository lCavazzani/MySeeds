package megaleios.com.myseeds.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class Company implements Serializable {

    @SerializedName("name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @SerializedName("logo")
    private String logo;
    @SerializedName("id")
    private String id;


}