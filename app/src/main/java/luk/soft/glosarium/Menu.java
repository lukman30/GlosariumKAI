package luk.soft.glosarium;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.pc_render.glosairumapi.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import luk.soft.glosarium.config.Config;
import luk.soft.glosarium.config.MyApplication_p;
import luk.soft.glosarium.isimenu.AcronymandAbbreviationEnglish;
import luk.soft.glosarium.isimenu.AcronymandAbbreviationIndonesia;
import luk.soft.glosarium.isimenu.TecnicalDefinitions;
import luk.soft.glosarium.isimenu.TermandMeanings;
import luk.soft.glosarium.isimenu.TrainPart;
import luk.soft.glosarium.listviewABE.DepanABE;
import luk.soft.glosarium.listviewABE.DepanABI;
import luk.soft.glosarium.listviewABE.DepanTD;
import luk.soft.glosarium.listviewABE.DepanTM;
import luk.soft.glosarium.listviewABE.DepanTP;

/**
 * Created by PC-RENDER on 26/03/2018.
 */

public class Menu extends AppCompatActivity implements View.OnClickListener {
    Button btn1, btn2, btn3, btn4, btn5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

//        getSupportActionBar().setElevation(0);

        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        btn5 = (Button) findViewById(R.id.btn5);


        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);


        Cekapp();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                startActivity(new Intent(this, DepanTM.class));
                break;
            case R.id.btn2:
                startActivity(new Intent(this, DepanABE.class));
                break;
            case R.id.btn3:
                startActivity(new Intent(this, DepanABI.class));
                break;
            case R.id.btn4:
                startActivity(new Intent(this, DepanTP.class));
                break;
            case R.id.btn5:
                startActivity(new Intent(this, DepanTD.class));
                break;
        }
    }

    private void Cekapp() {

        JsonArrayRequest reqData = new JsonArrayRequest(Request.Method.POST,
                Config.home + "/glosarium/cekapp.php", null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject json_data = response.getJSONObject(i);
                                String cekapp = json_data.getString("status");
                                if (cekapp == "stop" || cekapp.equals("stop")) {
                                    alertcekapp();
                                } else {

                                }


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

//                        Toast.makeText(getApplication(), "Ops..ada kesalahan", Toast.LENGTH_LONG).show();
                    }
                });

        MyApplication_p.getInstance().addToRequestQueue(reqData);
    }


    public void alertcekapp() {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(Menu.this);
        LayoutInflater inflater = getLayoutInflater();
        View alertDialogView = inflater.inflate(R.layout.under, null);
        alertDialog.setView(alertDialogView);
        alertDialog.setCancelable(false);
        alertDialog.setPositiveButton("Tutup", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        alertDialog.show();

    }

}
