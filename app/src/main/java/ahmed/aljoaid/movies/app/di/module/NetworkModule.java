package ahmed.aljoaid.movies.app.di.module;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ahmed.aljoaid.movies.app.BuildConfig;
import ahmed.aljoaid.movies.app.api.TopRatedMoviesApi;
import ahmed.aljoaid.movies.app.di.scope.MoviesApplicationScope;
import ahmed.aljoaid.movies.app.IdlingResources;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = OkHttpClientModule.class)
public class NetworkModule {
    @Provides
    public TopRatedMoviesApi getTopRatedMovies(Retrofit retrofit) {
        return retrofit.create(TopRatedMoviesApi.class);
    }

    @MoviesApplicationScope
    @Provides
    public Retrofit retrofit(OkHttpClient okHttpClient,
                             GsonConverterFactory gsonConverterFactory) {
        // Espresso Test Okhttp
        if (BuildConfig.DEBUG)
            IdlingResources.register(okHttpClient);
        return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://api.themoviedb.org/3/movie/")
                .addConverterFactory(gsonConverterFactory)
                .build();
    }

    @Provides
    public Gson gson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        return gsonBuilder.create();
    }

    @Provides
    public GsonConverterFactory gsonConverterFactory(Gson gson) {
        return GsonConverterFactory.create(gson);
    }
}
