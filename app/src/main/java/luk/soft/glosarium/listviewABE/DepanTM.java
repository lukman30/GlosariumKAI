package luk.soft.glosarium.listviewABE;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.pc_render.glosairumapi.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import luk.soft.glosarium.config.Config;
import luk.soft.glosarium.config.MyApplication_p;

public class DepanTM extends AppCompatActivity implements View.OnClickListener{
    RecyclerView mRecyclerview;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mManager;
    List<Data> mItems;
    Button btnInsert, btnDelete;
    ProgressDialog pd;



    EditText pencarian;
    ImageView btncari;
    String temp;
    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.depantm);
        getSupportActionBar().setElevation(0);

        mRecyclerview = (RecyclerView) findViewById(R.id.list);
        pd = new ProgressDialog(DepanTM.this);
        mItems = new ArrayList<>();



        mManager = new LinearLayoutManager(DepanTM.this, LinearLayoutManager.VERTICAL,false);
        mRecyclerview.setLayoutManager(mManager);
        mAdapter = new AdapterListTM(DepanTM.this,mItems);
        mRecyclerview.setAdapter(mAdapter);

        loadJson();
        pencarian=(EditText)findViewById(R.id.pencarian);
        btncari=(ImageView)findViewById(R.id.btncari);
        btncari.setOnClickListener(this);
    }


    private void loadJson()
    {
        pd.setMessage("Mengambil Data");
        pd.setCancelable(false);
        pd.show();



        JsonArrayRequest reqData = new JsonArrayRequest(Request.Method.GET,
                Config.home+"/glosarium/list_tm.php?cari="+temp,null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        pd.cancel();
                        for(int i = 0 ; i < response.length(); i++)
                        {
                            try {
                                JSONObject data = response.getJSONObject(i);
                                Data md = new Data();
                                md.setNama(data.getString("nama"));


                                mItems.add(md);

                            } catch (JSONException e) {
                                e.printStackTrace();
                                System.out.println("data kosong");

                            }
                        }

                        mAdapter.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

//                        Toast.makeText(getApplication(), "Ada Kesalahan Mohon Periksa Kembali", Toast.LENGTH_LONG).show();
                    }
                });

        MyApplication_p.getInstance().addToRequestQueue(reqData);
    }
    @Override
    public void onClick(View v) {
        mItems.clear();
        temp=pencarian.getText().toString().replace(" ", "%20");
        loadJson();
        try {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
