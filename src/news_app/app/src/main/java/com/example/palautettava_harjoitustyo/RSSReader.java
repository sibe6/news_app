package com.example.palautettava_harjoitustyo;


import android.util.Log;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class RSSReader{

    private InputStream urlStream;
    private XmlPullParserFactory factory;
    private XmlPullParser parser;

    private List<Model> rssFeedList;
    private Model model;

    private String urlString;
    private String tagName;
    private String title;
    private String link;
    private String description;
    private String pubDate;

    public static final String ITEM = "item";
    public static final String CHANNEL = "channel";
    public static final String TITLE = "title";
    public static final String LINK = "link";
    public static final String DESCRIPTION = "description";
    public static final String PUBLISHEDDATE = "pubDate";

    public RSSReader(String urlString) {
        this.urlString = urlString;
    }


    public static InputStream downloadStream(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setDoInput(true);
        conn.connect();
        InputStream stream = conn.getInputStream();
        return stream;
    }

    public List<Model> parseXmlData() {
        try {
            factory = XmlPullParserFactory.newInstance();
            parser = factory.newPullParser();
            urlStream = downloadStream(urlString);
            parser.setInput(urlStream, null);
            int eventType = parser.getEventType();
            boolean done = false;
            //model = new Model();
            rssFeedList = new ArrayList<>();
            while (eventType != XmlPullParser.END_DOCUMENT && !done) {
                tagName = parser.getName();
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        break;
                    case XmlPullParser.START_TAG:
                        if (tagName.equals(ITEM)) {
                            //model = new Model();
                        }
                        if (tagName.equals(TITLE)) {
                            title = parser.nextText().toString();
                        }
                        if (tagName.equals(LINK)) {
                            link = parser.nextText().toString();
                        }
                        if (tagName.equals(DESCRIPTION)) {
                            description = parser.nextText().toString();
                        }
                        if (tagName.equals(PUBLISHEDDATE)) {
                            pubDate = parser.nextText().toString();
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if (tagName.equals(CHANNEL)) {
                            done = true;
                        } else if (tagName.equals(ITEM)) {
                            model = new Model(title, pubDate, description, link);
                            rssFeedList.add(model);
                        }
                        break;
                }
                eventType = parser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i("MyTag", " : "+Integer.toString(rssFeedList.size()).toString());
        return rssFeedList;
    }
}

