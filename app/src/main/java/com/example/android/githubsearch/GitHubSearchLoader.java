package com.example.android.githubsearch;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;

import java.io.IOException;

public class GitHubSearchLoader extends AsyncTaskLoader<String> {

    private String mURL;

    GitHubSearchLoader(Context context, String url) {
        super(context);
        mURL = url;
    }

    @Override
    protected void onStartLoading() {
        if (mURL != null) {
            forceLoad();
        }
    }

    @Nullable
    @Override
    public String loadInBackground() {
        if (mURL != null) {
            String results = null;
            try {
                results = NetworkUtils.doHTTPGet(mURL);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return results;
        } else {
            return null;
        }
    }
}
