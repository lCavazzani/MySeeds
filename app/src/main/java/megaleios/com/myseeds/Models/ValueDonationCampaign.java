package megaleios.com.myseeds.Models;


import android.util.Log;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Leonardo on 2017-05-15.
 */
public class ValueDonationCampaign {


    public String getCampanha1() {
        return campanha1;
    }

    public void setCampanha1(String campanha1) {
        this.campanha1 = campanha1;
    }

    public String getCamapnha2() {
        return camapanha2;
    }

    public void setCamapnha2(String camapnha2) {
        this.camapanha2 = camapnha2;
    }

    public static ValueDonationCampaign getCampanhas() {
        return campanhas;
    }

    private String campanha1 = "";
    private String camapanha2 = "";

    private static final ValueDonationCampaign campanhas = new ValueDonationCampaign();

    public static ValueDonationCampaign getInstance() {
        return campanhas;
    }

    public ValueDonationCampaign() {
    }

}
