package megaleios.com.myseeds.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by MegaDev02 on 02/10/2017.
 */


public class Donatations implements Serializable {


    @SerializedName("campaignId")
    @Expose
    public String campaignId;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("type")
    @Expose
    public String type;
    @SerializedName("donatationValue")
    @Expose
    public String donatationValue;


    public String getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(String campaignId) {
        this.campaignId = campaignId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDonatationValue() {
        return donatationValue;
    }

    public void setDonatationValue(String donatationValue) {
        this.donatationValue = donatationValue;
    }
}
