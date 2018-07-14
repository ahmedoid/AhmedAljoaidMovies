package ahmed.aljoaid.movies.app.di.module;

import com.squareup.picasso.Picasso;

import ahmed.aljoaid.movies.app.adapter.MovieAdapter;
import ahmed.aljoaid.movies.app.di.scope.MainActivityScope;
import ahmed.aljoaid.movies.app.viewModel.MovieViewModel;
import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {


    @Provides
    @MainActivityScope
    public MovieAdapter movieAdapter(Picasso picasso) {
        return new MovieAdapter(picasso);
    }


}