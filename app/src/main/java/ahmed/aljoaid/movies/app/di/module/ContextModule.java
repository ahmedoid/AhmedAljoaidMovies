package ahmed.aljoaid.movies.app.di.module;

import android.content.Context;


import ahmed.aljoaid.movies.app.di.ApplicationContext;
import ahmed.aljoaid.movies.app.di.scope.MoviesApplicationScope;
import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {

    Context context;

    public ContextModule(Context context){
        this.context = context;
    }
    @ApplicationContext
    @MoviesApplicationScope
    @Provides
    public Context context(){ return context.getApplicationContext(); }
}