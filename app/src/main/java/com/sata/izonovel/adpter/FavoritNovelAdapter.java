package com.sata.izonovel.adpter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sata.izonovel.DetailNovelActivity;
import com.sata.izonovel.Model.FavoritResponseModel;
import com.sata.izonovel.R;

import java.util.List;

public class FavoritNovelAdapter  extends RecyclerView.Adapter<FavoritNovelAdapter.AdapterHolder> {
    private Context context;
    private List<FavoritResponseModel.Document> documentList;

    public FavoritNovelAdapter(Context context, List<FavoritResponseModel.Document> documentList) {
        this.context = context;
        this.documentList = documentList;
    }

    @NonNull
    @Override
    public FavoritNovelAdapter.AdapterHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_novel, parent, false);
        AdapterHolder holder = new AdapterHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull FavoritNovelAdapter.AdapterHolder holder, int position) {
        final FavoritResponseModel.Document document = documentList.get(position);

        String judulNovel = document.getJudul();
        String tahunDanPengarang = document.getTahunTerbit() +" | "+ document.getPengarang();
        String sinopsis = document.getSinopsis();
        String genre = document.getGenre();

        holder.JudulNovel.setText(judulNovel);
        holder.TahunDanPengarang.setText(tahunDanPengarang);
        holder.Sinopsis.setText(trimString(sinopsis));
        holder.Genre.setText(genre);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailNovelActivity.class);
                intent.putExtra("id",document.get_id());
                intent.putExtra("judul", judulNovel);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return documentList.size();
    }

    public class AdapterHolder extends RecyclerView.ViewHolder {
        TextView JudulNovel, TahunDanPengarang, Sinopsis, Genre;
        ImageView imgPoster;

        public AdapterHolder(@NonNull View itemView) {
            super(itemView);
            JudulNovel = itemView.findViewById(R.id.tvJudulNovel);
            TahunDanPengarang = itemView.findViewById(R.id.tvTahunDanPengarang);
            Sinopsis = itemView.findViewById(R.id.tvSinopsis);
            Genre = itemView.findViewById(R.id.tvGenre);
            imgPoster = itemView.findViewById(R.id.image_poster);
        }
    }

    public String trimString(String item) {
        if (item.length() > 150) {
            return item.substring(0,150 )+"...";
        }
        return  item;
    }
}
