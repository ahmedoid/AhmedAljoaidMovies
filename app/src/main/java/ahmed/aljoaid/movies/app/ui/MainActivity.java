package ahmed.aljoaid.movies.app.ui;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import javax.inject.Inject;

import ahmed.aljoaid.movies.app.ApplicationClass;
import ahmed.aljoaid.movies.app.R;
import ahmed.aljoaid.movies.app.adapter.MovieAdapter;
import ahmed.aljoaid.movies.app.di.component.DaggerMainActivityComponent;
import ahmed.aljoaid.movies.app.di.component.MainActivityComponent;
import ahmed.aljoaid.movies.app.di.module.MainActivityModule;
import ahmed.aljoaid.movies.app.viewModel.MovieViewModel;

public class MainActivity extends AppCompatActivity {
    @Inject
    MovieViewModel movieViewModel;
    @Inject
    MovieAdapter mAdapter;

    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        ProgressBar loading = findViewById(R.id.loading);
        LinearLayout first_page = findViewById(R.id.first_page);
        recyclerView.setHasFixedSize(true);

        //dagger
        MainActivityComponent mainActivityComponent = DaggerMainActivityComponent.builder()
                .mainActivityModule(new MainActivityModule())
                .appComponent(ApplicationClass.get(this).getAppComponent())
                .build();
        mainActivityComponent.injectMainActivity(this);


        recyclerView.setAdapter(mAdapter);

        movieViewModel.dataLoadStatus().observe(this, loadStatus -> {
            if (loadStatus != null) {
                switch (loadStatus) {
                    // full screen progressBar
                    case FIRST_PAGE:
                        first_page.setVisibility(View.VISIBLE);
                        break;
                    case LOADING:
                        loading.setVisibility(View.VISIBLE);
                        break;
                    case LOADED:
                        loading.setVisibility(View.GONE);
                        if (first_page.getVisibility() == View.VISIBLE)
                            first_page.setVisibility(View.GONE);
                        first_page.setVisibility(View.GONE);
                        break;
                    case FAILED:
                        loading.setVisibility(View.GONE);
                        if (first_page.getVisibility() == View.VISIBLE)
                            first_page.setVisibility(View.GONE);
                        Snackbar.make(recyclerView, R.string.please_try_again, Snackbar.LENGTH_INDEFINITE).setAction(
                                R.string.try_again, new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        movieViewModel.getMovies().observe(MainActivity.this, pagedList -> {
                                            mAdapter.submitList(pagedList);
                                        });
                                    }
                                }
                        ).show();
                        break;
                }
            }
        });

        movieViewModel.getMovies().observe(this, pagedList -> {
            mAdapter.submitList(pagedList);
        });
    }

}
