package ahmed.aljoaid.movies.app.repositories;

import android.arch.lifecycle.LiveData;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.support.annotation.MainThread;


import ahmed.aljoaid.movies.app.dataSource.DataLoadState;
import ahmed.aljoaid.movies.app.dataSource.MovieDataSourceFactory;

import static android.arch.lifecycle.Transformations.switchMap;

public class MovieRepositoryImpl implements MovieRepository {
    MovieDataSourceFactory dataSourceFactory;
    private static final int PAGE_SIZE = 15;

    public MovieRepositoryImpl(MovieDataSourceFactory dataSourceFactory) {
        this.dataSourceFactory = dataSourceFactory;
    }

    @Override
    @MainThread
    public LiveData getMovies() {

        PagedList.Config config = new PagedList.Config.Builder()
                .setInitialLoadSizeHint(PAGE_SIZE)
                .setPageSize(PAGE_SIZE)
                .build();


        return new LivePagedListBuilder(dataSourceFactory, config)
                .setInitialLoadKey(1)
                .build();

    }

    public LiveData<DataLoadState> getDataLoadStatus() {
        return switchMap(dataSourceFactory.dataSourceMutableLiveData,
                dataSource -> dataSource.loadState);
    }
}
