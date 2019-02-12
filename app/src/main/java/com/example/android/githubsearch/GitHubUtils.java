package com.example.android.githubsearch;

import android.net.Uri;

import com.google.gson.Gson;

import java.io.Serializable;

public class GitHubUtils {
    private final static String GITHUB_SEARCH_BASE_URL = "https://api.github.com/search/repositories";
    private final static String GITHUB_SEARCH_QUERY_PARAM = "q";
    private final static String GITHUB_SEARCH_SORT_PARAM = "sort";
    private final static String GITHUB_SEARCH_SORT_VALUE = "stars";

    public static final String EXTRA_GITHUB_REPO = "GitHubUtils.GitHubRepo";

    public static class GitHubRepo implements Serializable {
        public String full_name;
        public String description;
        public String html_url;
        public int stargazers_count;
    }

    public static class GitHubSearchResults {
        public GitHubRepo[] items;
    }

    public static String buildGitHubSearchURL(String query) {
        return Uri.parse(GITHUB_SEARCH_BASE_URL).buildUpon()
                .appendQueryParameter(GITHUB_SEARCH_QUERY_PARAM, query)
                .appendQueryParameter(GITHUB_SEARCH_SORT_PARAM, GITHUB_SEARCH_SORT_VALUE)
                .build()
                .toString();
    }

    public static GitHubRepo[] parseGitHubSearchResults(String json) {
        Gson gson = new Gson();
        GitHubSearchResults results = gson.fromJson(json, GitHubSearchResults.class);
        if (results != null && results.items != null) {
            return results.items;
        } else {
            return null;
        }
    }
}
