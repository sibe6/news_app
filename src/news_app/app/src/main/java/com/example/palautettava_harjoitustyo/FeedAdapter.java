package com.example.palautettava_harjoitustyo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;


class Model {

    private String title;
    private String pubDate;
    private String desc;
    private String link;

    public Model(String title, String pubDate, String desc, String link) {
        this.title = title;
        this.pubDate = pubDate;
        this.desc = desc;
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getLink() {
        return link;
    }
    public void setLink(String link) {
        this.link = link;
    }
}

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.ViewHolder> {

    private final Context context;
    private final List<Model> modelList;

    public FeedAdapter(Context context, List<Model> modelArrayList) {
        this.context = context;
        this.modelList = modelArrayList;
    }

    @NonNull
    @Override
    public FeedAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card, parent, false);
        return new ViewHolder(view);
    }

    public void onBindViewHolder(@NonNull FeedAdapter.ViewHolder holder, int position) {
        Model model = modelList.get(position);
        holder.title.setText(model.getTitle());
        holder.pubDate.setText(model.getPubDate());
        holder.desc.setText(model.getDesc());
        holder.link.setText(model.getLink());
    }

    public int getItemCount() {
        return modelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView title;
        private final TextView pubDate;
        private final TextView desc;
        private final TextView link;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            pubDate = itemView.findViewById(R.id.pubdate);
            desc = itemView.findViewById(R.id.desc);
            link = itemView.findViewById(R.id.link);
        }
    }
}