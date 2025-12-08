package edu.kirkwood.model.json;

import java.util.List;

public class TmdbMovieResponse {
    private List<edu.kirkwood.model.json.MovieSearchResult> results;
    private int total_pages;

    public List<MovieSearchResult> getResults() {
        return results;
    }

    public int getTotal_pages() {
        return total_pages;
    }
}
