package therajtec.com.myapplication.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import therajtec.com.myapplication.DetailOfMovie;
import therajtec.com.myapplication.Movie;
import therajtec.com.myapplication.R;

public class MoviesAdapter extends BaseAdapter {
    private Movie[] mMovies;


    public MoviesAdapter(Context context, Movie[] movies) {
        mMovies = movies;
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mMovies.length;
    }

    @Override
    public Object getItem(int position) {
        return mMovies[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, null);
            holder = new ViewHolder();
            holder.MovieImage = (ImageView) convertView.findViewById(R.id.grid_item);



            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        String movie = mMovies[position].getPosterPath();
        final String title = mMovies[position].getTitle();
        final String summery = mMovies[position].getSummary();
        final String imageUrl = "http://image.tmdb.org/t/p/w185" + movie;
        final String backgroundUrl = "http://image.tmdb.org/t/p/w342" + mMovies[position].getBackgroundPath();
        final String releaseDate = mMovies[position].getReleaseDate();
        final String userRating = mMovies[position].getUserRating();
        Picasso.with(parent.getContext()).load(imageUrl).into(holder.MovieImage);
        final Context mcon = parent.getContext();
        final int mpos = position;
        holder.MovieImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               //  Toast.makeText(mcon, mpos + "", Toast.LENGTH_SHORT).show();

                // mcon.startActivity(new Intent(mcon, DetailOfMovie.class));

                Intent intent = new Intent(mcon,DetailOfMovie.class);
                intent.putExtra("position", mpos + "");
                intent.putExtra("title", title);
                intent.putExtra("poster", backgroundUrl);
                intent.putExtra("summery", summery);
                intent.putExtra("releaseDate", releaseDate);
                intent.putExtra("userRating", userRating);

                mcon.startActivity(intent);

            }
        });

        return convertView;
    }

    private class ViewHolder {
        ImageView MovieImage;
    }
}
