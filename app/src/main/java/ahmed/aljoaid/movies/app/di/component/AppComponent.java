package ahmed.aljoaid.movies.app.di.component;

import com.squareup.picasso.Picasso;

import ahmed.aljoaid.movies.app.di.module.MovieViewModule;
import ahmed.aljoaid.movies.app.di.module.PicassoModule;
import ahmed.aljoaid.movies.app.di.scope.MoviesApplicationScope;
import ahmed.aljoaid.movies.app.viewModel.MovieViewModel;
import dagger.Component;

@MoviesApplicationScope
@Component(modules = {MovieViewModule.class, PicassoModule.class})
public interface AppComponent {
    Picasso getPicasso();
    MovieViewModel getMovieViewModel();
}
