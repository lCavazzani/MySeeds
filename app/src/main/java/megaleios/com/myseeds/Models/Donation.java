package megaleios.com.myseeds.Models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by MegaDev02 on 17/07/2017.
 */

public class Donation implements Serializable {

    @SerializedName("donatations")
    @Expose
    public String donatations;
    @SerializedName("profileId")
    @Expose
    public String profileId;
    @SerializedName("creditCardId")
    @Expose
    public String creditCardId;

    public String getDonatations() {
        return donatations;
    }

    public void setDonatations(String donatations) {
        this.donatations = donatations;
    }

    public String getProfileId() {
        return profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    public String getCreditCardId() {
        return creditCardId;
    }

    public void setCreditCardId(String creditCardId) {
        this.creditCardId = creditCardId;
    }

    public String getIsPaidFees() {
        return isPaidFees;
    }

    public void setIsPaidFees(String isPaidFees) {
        this.isPaidFees = isPaidFees;
    }

    public String getIsRequestDonatation() {
        return isRequestDonatation;
    }

    public void setIsRequestDonatation(String isRequestDonatation) {
        this.isRequestDonatation = isRequestDonatation;
    }

    public String getAmountFees() {
        return amountFees;
    }

    public void setAmountFees(String amountFees) {
        this.amountFees = amountFees;
    }

    public String getTotalDonationAmount() {
        return totalDonationAmount;
    }

    public void setTotalDonationAmount(String totalDonationAmount) {
        this.totalDonationAmount = totalDonationAmount;
    }

    public String getInstitutionId() {
        return institutionId;
    }

    public void setInstitutionId(String institutionId) {
        this.institutionId = institutionId;
    }

    @SerializedName("isPaidFees")
    @Expose
    public String isPaidFees;
    @SerializedName("isRequestDonatation")
    @Expose
    public String isRequestDonatation;
    @SerializedName("amountFees")
    @Expose
    public String amountFees;
    @SerializedName("totalDonationAmount")
    @Expose
    public String totalDonationAmount;
    @SerializedName("institutionId")
    @Expose
    public String institutionId;



}
