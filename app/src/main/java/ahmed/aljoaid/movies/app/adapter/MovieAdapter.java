package ahmed.aljoaid.movies.app.adapter;

import android.arch.paging.PagedListAdapter;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import ahmed.aljoaid.movies.app.R;
import ahmed.aljoaid.movies.app.model.Movie;
import ahmed.aljoaid.movies.app.ui.MainActivity;
import ahmed.aljoaid.movies.app.ui.MovieDetailsActivity;
import ahmed.aljoaid.movies.app.viewModel.MovieViewModel;


public class MovieAdapter extends PagedListAdapter<Movie, MovieAdapter.MovieViewHolder> {

    private final Picasso picasso;


    public MovieAdapter(Picasso picasso) {
        super(DIFF_CALLBACK);
        this.picasso = picasso;

    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MovieViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item,
                parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = getItem(position);
        if (movie != null) {
            holder.movieTitle.setText(movie.getTitle()
            );
            picasso
                    .load(movie.getPosterPath())
                    .into(holder.moviePoster);


            holder.itemView.setOnClickListener(v -> {
                holder.moviePoster.getContext().startActivity(new Intent(holder.moviePoster.getContext(), MovieDetailsActivity.class).putExtra("movie", movie));
            });
        }

    }


    public class MovieViewHolder extends RecyclerView.ViewHolder {
        public AppCompatTextView movieTitle;
        public AppCompatImageView moviePoster;

        public MovieViewHolder(View itemView) {
            super(itemView);
            movieTitle = itemView.findViewById(R.id.title);
            moviePoster = itemView.findViewById(R.id.poster);

        }
    }

    public static final DiffUtil.ItemCallback<Movie> DIFF_CALLBACK = new DiffUtil.ItemCallback<Movie>() {

        @Override
        public boolean areItemsTheSame(Movie oldItem, Movie newItem) {
            return oldItem.getTitle().equals(newItem.getTitle());
        }

        @Override
        public boolean areContentsTheSame(Movie oldItem, Movie newItem) {
            return oldItem.getTitle().equals(newItem.getTitle());
        }
    };
}