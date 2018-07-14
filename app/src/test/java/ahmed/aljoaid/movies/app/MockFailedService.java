package ahmed.aljoaid.movies.app;

import com.google.gson.Gson;

import ahmed.aljoaid.movies.app.api.TopRatedMoviesApi;
import ahmed.aljoaid.movies.app.model.MovieResponse;
import ahmed.aljoaid.movies.app.model.ResponseError;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.mock.BehaviorDelegate;
import retrofit2.mock.Calls;

public class MockFailedService implements TopRatedMoviesApi {
    private static final String TAG = "MockFailedService";
    private final BehaviorDelegate<TopRatedMoviesApi> delegate;

    public MockFailedService(BehaviorDelegate<TopRatedMoviesApi> restServiceBehaviorDelegate) {
        this.delegate = restServiceBehaviorDelegate;

    }

    @Override
    public Call<MovieResponse> getTopRatedMovies(String apiKey, int page) {
        ResponseError error = new ResponseError();
        error.setStatusCode(7);
        error.setStatusMessage("Invalid API key: You must be granted a valid key.");

        Gson gson = new Gson();
        gson.toJson(error);
        String json = "";

        Response response = Response.error(404, ResponseBody.create(MediaType.parse("application/json"), json));
        if (response != null) {
            return delegate.returning(Calls.response(response)).getTopRatedMovies("", 1);
        } else {
            return null;
        }
    }
}
