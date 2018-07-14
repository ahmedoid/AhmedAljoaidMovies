package ahmed.aljoaid.movies.app;

import android.app.Activity;
import android.app.Application;
import ahmed.aljoaid.movies.app.di.component.AppComponent;
import ahmed.aljoaid.movies.app.di.component.DaggerAppComponent;
import ahmed.aljoaid.movies.app.di.module.ContextModule;
import timber.log.Timber;

public class ApplicationClass extends Application {

    private AppComponent appComponent;

    public static ApplicationClass get(Activity activity){
        return (ApplicationClass) activity.getApplication();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());

        appComponent = DaggerAppComponent.builder()
                .contextModule(new ContextModule(this))
                .build();
    }

    public AppComponent getAppComponent(){
        return appComponent;
    }
}