package ahmed.aljoaid.movies.app.repositories;

import android.arch.lifecycle.LiveData;

import ahmed.aljoaid.movies.app.dataSource.DataLoadState;


public interface MovieRepository {
    LiveData getMovies();

    LiveData<DataLoadState> getDataLoadStatus();
}
