package megaleios.com.myseeds.Models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by MegaDev02 on 17/07/2017.
 */

public class ProfileUser implements Serializable {

    @SerializedName("fullName")
    @Expose
    public String fullName;
    @SerializedName("cellphone")
    @Expose
    public String cellPhone;
    @SerializedName("email")
    @Expose
    public String email;
    @SerializedName("login")
    @Expose
    public String login;
    @SerializedName("password")
    @Expose
    public String password;
    @SerializedName("facebookId")
    @Expose
    public String facebookId;
    @SerializedName("cpf")
    @Expose
    public String cpf;
    @SerializedName("dateBirth")
    @Expose
    public String dateBirth;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
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


}
