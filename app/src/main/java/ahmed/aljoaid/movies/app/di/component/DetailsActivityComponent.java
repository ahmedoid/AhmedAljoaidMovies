package ahmed.aljoaid.movies.app.di.component;

import ahmed.aljoaid.movies.app.di.scope.MainActivityScope;
import ahmed.aljoaid.movies.app.ui.MovieDetailsActivity;
import dagger.Component;

@Component(dependencies = AppComponent.class)
@MainActivityScope
public interface DetailsActivityComponent {
    void injectMovieDetailsActivity(MovieDetailsActivity movieDetailsActivity);
}
