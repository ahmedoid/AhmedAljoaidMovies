package ahmed.aljoaid.movies.app.api;



import ahmed.aljoaid.movies.app.model.MovieResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface TopRatedMoviesApi {

    @GET("top_rated")
    Call<MovieResponse> getTopRatedMovies(@Query("api_key") String apiKey, @Query("page") int page);
}