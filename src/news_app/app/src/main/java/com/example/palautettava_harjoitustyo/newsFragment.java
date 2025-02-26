package com.example.palautettava_harjoitustyo;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;



public class newsFragment extends Fragment {

    private List<Model> feedList = new ArrayList<Model>();
    private RecyclerView recyclerView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new DoRssFeedTask().execute();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycler_view, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        FeedAdapter feedAdapter = new FeedAdapter(getContext(), feedList);
        recyclerView.setAdapter(feedAdapter);

        Log.i("MyTag", "OnCreateView : "+Integer.toString(feedList.size()).toString());
        return view;
    }

    public class DoRssFeedTask extends AsyncTask<String, Void, List<Model>> {

        @Override
        protected void onPreExecute() {
            Log.i("Fragment ASyncTask", "onPreExecute()");
        }

        @Override
        protected List<Model> doInBackground(String... params) {
            RSSReader rssReader = new RSSReader("https://feeds.yle.fi/uutiset/v1/majorHeadlines/YLE_UUTISET.rss");
            feedList = rssReader.parseXmlData(); //data tuodaan listaan
            Log.i("MyTag", "doInBackground()");
            return feedList;
        }

        @Override
        protected void onPostExecute(List<Model> result) {
            FeedAdapter feedAdapter = new FeedAdapter(getContext(), feedList);
            recyclerView.setAdapter(feedAdapter);
            Log.i("MyTag", "onPostExecute : "+Integer.toString(feedList.size()).toString());
        }
    }
}

