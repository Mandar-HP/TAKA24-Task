package com.example.taka24;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.sip.SipSession;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Adapter;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Queue;

public class MainActivity extends AppCompatActivity {

    private RecyclerView MainActRecycler;
    private JSONObject jsonObjectObtained,responseObject,latJSONObj;
    private RequestQueue volleyReqQue;
    private JsonObjectRequest reqForJSONObj;


    private ArrayList<Data> arrayListOfJsonObj;
    private MyAdapter adapterclassreff;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();

    }

    public void initialize(){

        // INITIALIZING REFERENCES :

        MainActRecycler = findViewById(R.id.recyclerObj);



        // INFLATE ADAPTER VIEW USING LAYOUTMANAGER:

        MainActRecycler.setLayoutManager(new LinearLayoutManager(MainActivity.this,RecyclerView.HORIZONTAL,false));

        arrayListOfJsonObj = new ArrayList<Data>();


        // ADD JSON OBJ TO QUEUE & HIT API:

        reqForJSONObj = new JsonObjectRequest(Request.Method.GET,"https://taka24t.in/&key=eyJ0eXAiOiJKV1QiLCJ\n" +
                "hbGciOiJSUzI1NiJ9.e\n" +
                "yJhdWQiOiIzIiwianRp\n" +
                "IjoiZTYzMDdmNDBk\n" +
                "N2RmN2NmMTRiYzk\n" +
                "4YTc4MTc3NDI4NDc\n" +
                "5NTQ0NzhiMDllYTQ\n" +
                "4NmEyOGRmNTJjZjY\n" +
                "xOTlmYzY1NDE1ZmV\n" +
                "lOGM4MDdlNDRmO\n" +
                "DgiLCJpYXQiOjE2Mz\n" +
                "Y1NDM3OTkuOTE4\n" +
                "MDM1LCJuYmYiOjE2\n" +
                "MzY1NDM3OTkuOT\n" +
                "E4MDM5LCJleHAiOj\n" +
                "E2NjgwNzk3OTkuOT\n" +
                "E0NTk4LCJzdWIiOiIx\n" +
                "ODQiLCJzY29wZXMi\n" +
                "OltdfQ.goR2Q4qEs4S\n" +
                "suAKZ4QJdECGEwDJl\n" +
                "j-Pzjy-\n" +
                "FjtCNZbvLdGfsl7QhQ\n" +
                "ZRnbxqy6od-\n" +
                "uFVFNjNsCG5QOyAK\n" +
                "KQ6_roCMIJM1TpnL\n" +
                "ndUoy24v_qOXrE4N\n" +
                "calNyryxfXJroGlqn3dj\n" +
                "TGIAnpKzfUaO80xNn\n" +
                "-E8QLt7mN3qR-\n" +
                "WjJvjrzI0y7JhqWtCv\n" +
                "6ZTJeni9COp7mmqA\n" +
                "gcguzaqNVjpETsHCm\n" +
                "JGqONkBED_p6av1v\n" +
                "NPJTmEzaL4Hmc7qY\n" +
                "s9h6lRN98CORUZJX_\n" +
                "yT19E7agxyb-\n" +
                "OFAp0tiZVZYmSKr49\n" +
                "WNFR5ZYqNmMvdc\n" +
                "GO2gSdDJasfI1-\n" +
                "YsoqKCemqufM2G8\n" +
                "mqBM6LY6kIy6vN3x\n" +
                "eqa0wXPl6uiytn6-\n" +
                "ZBw6m8DfECSHTsz0\n" +
                "-\n" +
                "oMdNk0ov7VMbUjE\n" +
                "ESfNP8hTAsYlCsNPJ1\n" +
                "XOvyCw6dt_qA9KbBf\n" +
                "9lCMBWaWUF3bAgK\n" +
                "sxIlzwK_cojtcRqsKHb\n" +
                "sv1MnxGgiob88hmN\n" +
                "-7VX6JSz-\n" +
                "UvyssTrpqfvutOFOg\n" +
                "m7pBc11BSnV4IdMk\n" +
                "wPN7TSDTcN_uSNb0\n" +
                "tEdjUOSKE7TLz_QGv\n" +
                "uaIl09n-\n" +
                "wG2gs2UHwTqIDk8ic\n" +
                "Wx_a9ahxMYpy8vz6\n" +
                "wgprCkaF0JLX6PmO\n" +
                "_KtPaEGL9uSCRP3pn\n" +
                "MLRkMKUbiHnGHqh\n" +
                "c9mmUEgChaecxOI4\n" +
                "kkHQZc_pi_YTGyRwq\n" +
                "Zbs",null,new ResponseListener(),new ErrorListener());

        volleyReqQue = Volley.newRequestQueue(MainActivity.this);

        volleyReqQue.add(reqForJSONObj);

        //

    }





    private class ResponseListener implements Response.Listener<JSONObject>{

        JSONArray catagoriesJSONArr,childCatag;
        JSONObject childCatJSONObj;

        @Override
        public void onResponse(JSONObject response) {


            catagoriesJSONArr = new JSONArray();
            childCatag = new JSONArray();
            childCatJSONObj = new JSONObject();


            try {

                responseObject = response.getJSONObject("data");

                catagoriesJSONArr = responseObject.getJSONArray("categories");                // MAIN ARRAY

                for (int i = 0; i < catagoriesJSONArr.length();i++){

                    jsonObjectObtained = catagoriesJSONArr.getJSONObject(i);

                    childCatag = jsonObjectObtained.getJSONArray("child_cat");



                    for (int m = 0; m <childCatag.length(); m++ ){

                        childCatJSONObj = childCatag.getJSONObject(m);

                        String childCatagStrng;

                        childCatagStrng = childCatJSONObj.getString("category_name");

                        arrayListOfJsonObj.add(new Data(childCatagStrng));

                        // ADAPTER ATTACH TO RECYCLERVIEW :
                        adapterclassreff = new MyAdapter(arrayListOfJsonObj);
                        MainActRecycler.setAdapter((RecyclerView.Adapter) adapterclassreff);

                    }


                }



            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }





    private class ErrorListener implements Response.ErrorListener{

        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(MainActivity.this,error.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

}