package ahmed.aljoaid.movies.app;

import android.test.InstrumentationTestCase;
import android.test.suitebuilder.annotation.SmallTest;

import junit.framework.Assert;

import java.lang.annotation.Annotation;

import ahmed.aljoaid.movies.app.api.TopRatedMoviesApi;
import ahmed.aljoaid.movies.app.model.MovieResponse;
import ahmed.aljoaid.movies.app.model.ResponseError;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.mock.BehaviorDelegate;
import retrofit2.mock.MockRetrofit;
import retrofit2.mock.NetworkBehavior;

public class RetrofitTest extends InstrumentationTestCase {
    private MockRetrofit mockRetrofit;
    private Retrofit retrofit;
    private static String MOVIE_API_KEY = "5814cc8d38b22134edaf12f6bb13f918";

    @Override
    public void setUp() throws Exception {
        super.setUp();
        retrofit = new Retrofit.Builder().baseUrl("https://api.themoviedb.org/3/movie/")
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        NetworkBehavior behavior = NetworkBehavior.create();

        mockRetrofit = new MockRetrofit.Builder(retrofit)
                .networkBehavior(behavior)
                .build();
    }


    @SmallTest
    public void testMoviesList() throws Exception {
        BehaviorDelegate<TopRatedMoviesApi> delegate = mockRetrofit.create(TopRatedMoviesApi.class);
        TopRatedMoviesApi mockQodService = new FakeRetrofitTest(delegate);
        //Actual Test
        Call<MovieResponse> quote = mockQodService.getTopRatedMovies(MOVIE_API_KEY, 1);
        Response<MovieResponse> quoteOfTheDayResponse = quote.execute();
        //Asserting response
        Assert.assertTrue(quoteOfTheDayResponse.code() != 200);
        Assert.assertEquals("TopMovies", quoteOfTheDayResponse.body().getMovies().get(0).getTitle());

    }

    @SmallTest
    public void testServerErrors() throws Exception {
        BehaviorDelegate<TopRatedMoviesApi> delegate = mockRetrofit.create(TopRatedMoviesApi.class);
        MockFailedService mockQodService = new MockFailedService(delegate);

        //Actual Test
        Call<MovieResponse> quote = mockQodService.getTopRatedMovies("", 1);
        Response<MovieResponse> quoteOfTheDayResponse = quote.execute();
        Assert.assertFalse(quoteOfTheDayResponse.isSuccessful());

        Converter<ResponseBody, ResponseError> errorConverter = retrofit.responseBodyConverter(ResponseError.class, new Annotation[0]);
        ResponseError error = errorConverter.convert(quoteOfTheDayResponse.errorBody());

        //Asserting response
        Assert.assertEquals(7, quoteOfTheDayResponse.code());
        Assert.assertEquals("Invalid API key: You must be granted a valid key.", error.getStatusMessage());

    }
}