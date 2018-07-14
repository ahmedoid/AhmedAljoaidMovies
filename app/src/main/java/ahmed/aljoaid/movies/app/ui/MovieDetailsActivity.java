package ahmed.aljoaid.movies.app.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Objects;

import javax.inject.Inject;

import ahmed.aljoaid.movies.app.ApplicationClass;
import ahmed.aljoaid.movies.app.R;
import ahmed.aljoaid.movies.app.di.component.DaggerDetailsActivityComponent;
import ahmed.aljoaid.movies.app.di.component.DetailsActivityComponent;
import ahmed.aljoaid.movies.app.model.Movie;

public class MovieDetailsActivity extends AppCompatActivity {

    @Inject
    Picasso picasso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        // dagger
        DetailsActivityComponent mainActivityComponent = DaggerDetailsActivityComponent.builder()
                .appComponent(ApplicationClass.get(this).getAppComponent())
                .build();
        mainActivityComponent.injectMovieDetailsActivity(this);

        Movie movie = Objects.requireNonNull(getIntent().getExtras()).getParcelable("movie");
        if (movie != null) {
            initViews(movie);
        }

    }

    private void initViews(Movie movie) {
        ImageView posterImage = findViewById(R.id.movieHeader);
        ImageView miniPoster = findViewById(R.id.moviePoster);
        TextView movieTitle = findViewById(R.id.movieTitle);
        AppCompatTextView rateMovie = findViewById(R.id.movieRate);
        AppCompatTextView movieOverView = findViewById(R.id.movieOverview);
        AppCompatTextView movieDate = findViewById(R.id.movieDate);
        picasso.load(movie.getPosterPath()).into(posterImage);
        picasso.load(movie.getPosterPath()).into(miniPoster);
        movieTitle.setText(movie.getTitle());
        rateMovie.setText(String.valueOf(movie.getVoteAverage()));
        movieOverView.setText(movie.getOverview());
        movieDate.setText(movie.getReleaseDate());
    }

}
