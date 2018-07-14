package ahmed.aljoaid.movies.app.di.module;

import android.app.Application;
import android.content.Context;


import ahmed.aljoaid.movies.app.api.TopRatedMoviesApi;
import ahmed.aljoaid.movies.app.dataSource.MovieDataSourceFactory;
import ahmed.aljoaid.movies.app.di.ApplicationContext;
import ahmed.aljoaid.movies.app.di.scope.MoviesApplicationScope;
import ahmed.aljoaid.movies.app.repositories.MovieRepositoryImpl;
import ahmed.aljoaid.movies.app.viewModel.MovieViewModel;
import dagger.Module;
import dagger.Provides;

@Module(includes = NetworkModule.class)
public class MovieViewModule {
    @MoviesApplicationScope
    @Provides
    public MovieDataSourceFactory provideMovieSourceFactory(TopRatedMoviesApi topRatedMoviesApi) {
        return new MovieDataSourceFactory(topRatedMoviesApi);
    }

    @MoviesApplicationScope
    @Provides
    public MovieRepositoryImpl provideMovieRepositoryImp(MovieDataSourceFactory dataSourceFactory) {
        return new MovieRepositoryImpl(dataSourceFactory);
    }

    @MoviesApplicationScope
    @Provides
    public MovieViewModel provideMovieModel(@ApplicationContext Context context, MovieRepositoryImpl movieRepositoryImp) {
        return new MovieViewModel((Application) context, movieRepositoryImp);
    }

}
