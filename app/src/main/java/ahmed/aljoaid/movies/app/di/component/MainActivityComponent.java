package ahmed.aljoaid.movies.app.di.component;


import ahmed.aljoaid.movies.app.di.module.MainActivityModule;
import ahmed.aljoaid.movies.app.di.scope.MainActivityScope;
import ahmed.aljoaid.movies.app.ui.MainActivity;
import dagger.Component;

@Component(modules = MainActivityModule.class, dependencies = AppComponent.class)
@MainActivityScope
public interface MainActivityComponent {

    void injectMainActivity(MainActivity mainActivity);

}