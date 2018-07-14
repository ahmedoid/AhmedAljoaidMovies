package ahmed.aljoaid.movies.app.dataSource;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;

import ahmed.aljoaid.movies.app.api.TopRatedMoviesApi;
import ahmed.aljoaid.movies.app.model.Movie;


public class MovieDataSourceFactory extends DataSource.Factory<Integer, Movie> {
    public MutableLiveData<MovieDataSource> dataSourceMutableLiveData = new MutableLiveData<>();
    TopRatedMoviesApi topRatedMoviesApi;

    public MovieDataSourceFactory(TopRatedMoviesApi topRatedMoviesApi) {
        this.topRatedMoviesApi = topRatedMoviesApi;
    }

    @Override
    public DataSource<Integer, Movie> create() {
        MovieDataSource dataSource = new MovieDataSource(topRatedMoviesApi);
        dataSourceMutableLiveData.postValue(dataSource);
        return dataSource;
    }
}
