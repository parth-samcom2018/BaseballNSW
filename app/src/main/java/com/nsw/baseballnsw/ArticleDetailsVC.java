package com.nsw.baseballnsw;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.Html;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.nsw.baseballnsw.models.Article;
import com.nsw.baseballnsw.models.ArticleComment;
import com.nsw.baseballnsw.models.Event;
import com.nsw.baseballnsw.models.Group;
import com.nsw.baseballnsw.views.TextPoster;
import com.squareup.picasso.Picasso;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ArticleDetailsVC extends Fragment {


    public Article article;

    private WebView webView;
    private double PIC_WIDTH = 360;

    public ArticleDetailsVC() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        inflater = (LayoutInflater) this.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v= inflater.inflate(R.layout.fragment_article_details_vc, container, false);

        TextView firstTV = v.findViewById(R.id.firstTV);
        firstTV.setText(article.shortDescription);

        webView = v.findViewById(R.id.webView);


        TextView secondTV = v.findViewById(R.id.secondTV);
        // secondTV.setText(article.longDescription);

        loadData();

        return v;
    }


    public void loadData()
    {
        String mime = "text/html";
        String encoding = "utf-8";


        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadDataWithBaseURL(null, article.longDescription, mime, encoding, null);
        webView.setPadding(0, 0, 0, 0);
        webView.setInitialScale(getScale());
    }

    private int getScale() {
        Display display = ((WindowManager)getActivity().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        int width = display.getWidth();
        Double val = new Double(width)/new Double(PIC_WIDTH);
        val = val * 100d;
        return val.intValue();
    }


}
