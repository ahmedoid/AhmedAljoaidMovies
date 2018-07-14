package ahmed.aljoaid.movies.app.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.paging.PagedList;
import android.support.annotation.NonNull;


import javax.inject.Inject;

import ahmed.aljoaid.movies.app.dataSource.DataLoadState;
import ahmed.aljoaid.movies.app.model.Movie;
import ahmed.aljoaid.movies.app.repositories.MovieRepository;
import ahmed.aljoaid.movies.app.repositories.MovieRepositoryImpl;

public class MovieViewModel extends AndroidViewModel {


    private MovieRepository repository;

    @Inject
    public MovieViewModel(@NonNull Application application, MovieRepositoryImpl movieRepositoryImp) {
        super(application);
        this.repository = movieRepositoryImp;
    }

    public LiveData<PagedList<Movie>> getMovies() {
        return repository.getMovies();
    }

    public LiveData<DataLoadState> dataLoadStatus() {
        return repository.getDataLoadStatus();
    }

}