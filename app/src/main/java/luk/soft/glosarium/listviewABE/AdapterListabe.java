package luk.soft.glosarium.listviewABE;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.pc_render.glosairumapi.R;

import java.util.List;

import luk.soft.glosarium.isimenu.AcronymandAbbreviationEnglish;

public class AdapterListabe extends RecyclerView.Adapter<AdapterListabe.HolderData> {
    private List<Data> mItems;
    private Context context;

    public AdapterListabe(Context context, List<Data> items) {
        this.mItems = items;
        this.context = context;
    }

    @Override
    public HolderData onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.tampil_listabe, parent, false);
        HolderData holderData = new HolderData(layout);
        return holderData;
    }

    @Override
    public void onBindViewHolder(HolderData holder, int position) {
        Data md = mItems.get(position);
        holder.nama.setText(md.getNama());

        holder.md = md;


    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }


    class HolderData extends RecyclerView.ViewHolder {
        TextView nama;
        Data md;

        RelativeLayout rel;

        public HolderData(View view) {
            super(view);
            nama = (TextView) view.findViewById(R.id.istilah);
            rel=(RelativeLayout)view.findViewById(R.id.relativ);



            rel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent update = new Intent(context, AcronymandAbbreviationEnglish.class);
                    update.putExtra("update", 1);
                    update.putExtra("cari", md.getNama());
                    context.startActivity(update);
                }
            });
        }
    }
}
