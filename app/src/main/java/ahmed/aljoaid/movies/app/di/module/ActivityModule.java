package ahmed.aljoaid.movies.app.di.module;

import android.app.Activity;
import android.content.Context;


import javax.inject.Named;

import ahmed.aljoaid.movies.app.di.scope.MoviesApplicationScope;
import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    private final Context context;

    ActivityModule(Activity context){
        this.context = context;
    }
    @Named("activity_context")
    @MoviesApplicationScope
    @Provides
    public Context context(){ return context; }
}