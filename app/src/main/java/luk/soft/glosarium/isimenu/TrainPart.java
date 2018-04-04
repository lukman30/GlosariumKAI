package luk.soft.glosarium.isimenu;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.pc_render.glosairumapi.R;
import luk.soft.glosarium.adaptertermsandmeaning.SuggestionAdapterTM;
import luk.soft.glosarium.adatertrainpart.SuggestionAdapterTrainpart;
import luk.soft.glosarium.config.Config;
import luk.soft.glosarium.config.MyApplication_p;

/**
 * Created by PC-RENDER on 26/03/2018.
 */

public class TrainPart extends AppCompatActivity implements View.OnClickListener {
ImageView img;
    AutoCompleteTextView acTextView;
    ImageView btnCari;
    TextView ambilterms,ambilmeaning;
    String ambil;
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tp);

        img=(ImageView)findViewById(R.id.ambilgambar);




        pd = new ProgressDialog(this);
        acTextView = (AutoCompleteTextView) findViewById(R.id.autoComplete);
        acTextView.setAdapter(new SuggestionAdapterTrainpart(this,acTextView.getText().toString()));


        ambilmeaning=(TextView)findViewById(R.id.isimeaning);
        ambilterms=(TextView)findViewById(R.id.terms);
        btnCari=(ImageView) findViewById(R.id.btnCari);


        btnCari.setOnClickListener(this);


        Intent data = getIntent();
        final int update = data.getIntExtra("update", 0);
        ambil = data.getStringExtra("cari");


        if (update == 1) {
            Tampilisi();
        }




    }


    private void Tampilisi() {

        pd.setMessage("Mengambil Data");
        pd.setCancelable(true);
        pd.show();
        //        String temp=acTextView.getText().toString().replace(" ", "%20");
        String temp=ambil.replace(" ", "%20");
        JsonArrayRequest reqData = new JsonArrayRequest(Request.Method.GET,
                Config.home+"/glosarium/trainpartlist.php?cari="+ambil, null,

                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        pd.cancel();
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject data = response.getJSONObject(i);
                                Picasso.with(TrainPart.this)
                                        .load(data.getString("url"))
                                        .resize(400,400)
                                        .into(img);
//                                ambilmeaning.setText( data.getString("arti"));
                                ambilterms.setText( data.getString("nama"));



                            } catch (JSONException e) {
                                e.printStackTrace();
                                pd.cancel();
                            }
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.cancel();
                        Toast.makeText(getApplicationContext(),"Gagal Menampilkan data...",Toast.LENGTH_LONG).show();
                    }
                });

        MyApplication_p.getInstance().addToRequestQueue(reqData);
    }

    @Override
    public void onClick(View v) {
        Tampilisi();
        try {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            // TODO: handle exception
        }

    }
}