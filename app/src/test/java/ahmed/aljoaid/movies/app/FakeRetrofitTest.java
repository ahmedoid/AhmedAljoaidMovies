package ahmed.aljoaid.movies.app;

import java.util.ArrayList;

import ahmed.aljoaid.movies.app.api.TopRatedMoviesApi;
import ahmed.aljoaid.movies.app.model.Movie;
import ahmed.aljoaid.movies.app.model.MovieResponse;
import retrofit2.Call;
import retrofit2.mock.BehaviorDelegate;
import retrofit2.mock.Calls;

public class FakeRetrofitTest implements TopRatedMoviesApi {
    private static String MOVIE_API_KEY = "5814cc8d38b22134edaf12f6bb13f918";

    private final BehaviorDelegate<TopRatedMoviesApi> delegate;

    public FakeRetrofitTest(BehaviorDelegate<TopRatedMoviesApi> service) {
        this.delegate = service;
    }

    @Override
    public Call<MovieResponse> getTopRatedMovies(String apiKey, int page) {
        MovieResponse movieResponse = new MovieResponse();
        Movie movie = new Movie();
        movie.setTitle("Test");
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(movie);
        movieResponse.setMovies(movies);
        return delegate.returning(Calls.response(movieResponse)).getTopRatedMovies(MOVIE_API_KEY, 1);
    }
}
