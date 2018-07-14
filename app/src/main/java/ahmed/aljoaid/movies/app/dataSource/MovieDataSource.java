package ahmed.aljoaid.movies.app.dataSource;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;


import java.io.IOException;

import ahmed.aljoaid.movies.app.api.TopRatedMoviesApi;
import ahmed.aljoaid.movies.app.model.Movie;
import ahmed.aljoaid.movies.app.model.MovieResponse;
import retrofit2.Call;
import retrofit2.Response;

public class MovieDataSource extends PageKeyedDataSource<Integer, Movie> {
    private static String MOVIE_API_KEY = "5814cc8d38b22134edaf12f6bb13f918";
    private TopRatedMoviesApi topRatedMoviesApi;

    public final MutableLiveData<DataLoadState> loadState;

    public MovieDataSource(TopRatedMoviesApi topRatedMoviesApi) {

        this.topRatedMoviesApi = topRatedMoviesApi;
        loadState = new MutableLiveData<>();

    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Movie> callback) {
        loadState.postValue(DataLoadState.FIRST_PAGE);

        Call<MovieResponse> request = topRatedMoviesApi.getTopRatedMovies(MOVIE_API_KEY, 1);

        Response<MovieResponse> response = null;
        try {
            response = request.execute();
            if (response != null) {
                callback.onResult(response.body().getMovies(), 1, 2);
            } else {
                loadState.postValue(DataLoadState.FAILED);
                callback.onResult(null, null, 2);
            }
            loadState.postValue(DataLoadState.LOADED);
        } catch (IOException ex) {
            loadState.postValue(DataLoadState.FAILED);
        }
    }


    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Movie> callback) {
        loadState.postValue(DataLoadState.LOADING);

        Call<MovieResponse> request = topRatedMoviesApi.getTopRatedMovies(MOVIE_API_KEY, params.key);

        Response<MovieResponse> response = null;
        try {
            response = request.execute();
            if (response != null) {
                callback.onResult(response.body().getMovies(), params.key + 1);
            } else {
                loadState.postValue(DataLoadState.FAILED);
                callback.onResult(null, params.key + 1);
            }
            loadState.postValue(DataLoadState.LOADED);
        } catch (IOException ex) {
            //networkState.postValue();
            loadState.postValue(DataLoadState.FAILED);
        }
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Movie> callback) {

    }
}
