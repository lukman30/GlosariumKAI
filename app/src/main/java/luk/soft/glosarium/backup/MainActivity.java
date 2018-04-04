package luk.soft.glosarium.backup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

import com.example.pc_render.glosairumapi.R;

public class MainActivity extends AppCompatActivity {

    private ExpandableHeightListView listview;
    private ArrayList<Bean> Bean;
    private ListBaseAdapter baseAdapter;


    private int[] IMAGE1 = {R.drawable.newsname1, R.drawable.newsname1, R.drawable.newsname1};
    private int[] IMAGE2 = {R.drawable.img1, R.drawable.img1, R.drawable.img1};
    private int[] IMAGE3 = {R.drawable.more, R.drawable.more, R.drawable.more};
    private String[] NEWSNAME = {"Fox News .", "Fox News .", "Fox News ."};
    private String[] TITLE = {"1 day ago", "1 day ago", "1 day ago"};
    private String[] NEWS = {"Trump’s Plan for AmericanMade iPhonew Wold Be Disastrous. Trump’s Plan for AmericanMade iPhonew Wold Be Disastrous",
            "Trump’s Plan for AmericanMade iPhonew Wold Be Disastrous. Trump’s Plan for AmericanMade iPhonew Wold Be Disastrous",
            "Trump’s Plan for AmericanMade iPhonew Wold Be Disastrous. Trump’s Plan for AmericanMade iPhonew Wold Be Disastrous"};
    private String[] NEWSSUB = {"Why even a President Trump couldn’t make Apple manufacture iPhone in the state.","Why even a President Trump couldn’t make Apple manufacture iPhone in the state.",
            "Why even a President Trump couldn’t make Apple manufacture iPhone in the state."};
    private String[] INTREST = {"You've shown interest in iPhone","You've shown interest in iPhone","You've shown interest in iPhone"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listview = (ExpandableHeightListView)findViewById(R.id.listview);

        Bean = new ArrayList<Bean>();

        for (int i= 0; i< IMAGE1.length; i++){

            Bean bean = new Bean(IMAGE1[i], IMAGE2[i], IMAGE3[i], NEWSNAME[i], TITLE[i], NEWS[i], NEWSSUB[i], INTREST[i]);
            Bean.add(bean);

        }

        baseAdapter = new ListBaseAdapter(MainActivity.this, Bean) {
        };

        listview.setAdapter(baseAdapter);

    }
}
