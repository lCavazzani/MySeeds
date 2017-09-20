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
    @SerializedName("fullName")
    public String fullName;
    @SerializedName("cellphone")
    public String cellphone;
    @SerializedName("email")
    public String email;
    @SerializedName("photo")
    public String photo;
    @SerializedName("dateBirth")
    public String dateBirth;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(String dateBirth) {
        this.dateBirth = dateBirth;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @SerializedName("cpf")
    public String cpf;
    @SerializedName("id")
    public String id;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
