package com.example.android.githubsearch;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import java.io.IOException;

public class GitHubSearchLoader extends AsyncTaskLoader<String> {
    private static final String TAG = GitHubSearchLoader.class.getSimpleName();

    private String mGitHubSearchJSON;
    private String mURL;

    GitHubSearchLoader(Context context, String url) {
        super(context);
        mURL = url;
    }

    @Override
    protected void onStartLoading() {
        if (mURL != null) {
            if (mGitHubSearchJSON != null) {
                Log.d(TAG, "Delivering cached results");
                deliverResult(mGitHubSearchJSON);
            } else {
                forceLoad();
            }
        }
    }

    @Nullable
    @Override
    public String loadInBackground() {
        if (mURL != null) {
            String results = null;
            try {
                Log.d(TAG, "loading results from GitHub with URL: " + mURL);
                results = NetworkUtils.doHTTPGet(mURL);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return results;
        } else {
            return null;
        }
    }

    @Override
    public void deliverResult(@Nullable String data) {
        mGitHubSearchJSON = data;
        super.deliverResult(data);
    }
}
