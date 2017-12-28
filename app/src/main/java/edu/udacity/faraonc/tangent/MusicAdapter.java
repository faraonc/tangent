package edu.udacity.faraonc.tangent;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.TreeSet;

/**
 * Adapter for the list of music.
 *
 * @author ConardJames
 * @version 122817-01
 */
class MusicAdapter extends ArrayAdapter<Music> {

    /**
     * Constuct an adapter using TreeSet.
     *
     * @param context for resource access
     * @param musics  the list of Musics
     */
    MusicAdapter(Context context, TreeSet<Music> musics) {
        super(context, 0, new ArrayList<Music>(musics));
    }

    /**
     * Constuct an adapter using ArrayList.
     *
     * @param context for resource access
     * @param musics  the list of Musics
     */
    MusicAdapter(Context context, ArrayList<Music> musics) {
        super(context, 0, musics);
    }

    @NonNull
    @Override
    /**
     * Get the view for each entry of the ListView.
     *
     * @param position current position in the adapter's list.
     * @param convertView the list item view.
     * @param parent the parent view group.
     */
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);

            ViewHolder viewHolder = new ViewHolder();
            viewHolder.artistTextView = (TextView) listItemView.findViewById(R.id.artist_name_textview);
            viewHolder.songTextView = (TextView) listItemView.findViewById(R.id.song_name_textview);
            viewHolder.albumImageView = (ImageView) listItemView.findViewById(R.id.album_imageview);
            listItemView.setTag(viewHolder);
        }

        final ViewHolder viewHolder = (ViewHolder) listItemView.getTag();
        final Music m = getItem(position);
        viewHolder.artistTextView.setText(m.getArtist());
        viewHolder.songTextView.setText(m.getTitle());

        /* Put the task in the MessageQueue of the UI thread for future processing to gain performance. */
        /* Setting image is expensive. */
        Handler imageHandler = new Handler(Looper.getMainLooper());
        imageHandler.post(new Runnable() {
            public void run() {
                viewHolder.albumImageView.setImageResource(m.getAlbumImage());
            }
        });
        return listItemView;
    }

    /**
     * For caching the resource id.
     */
    private class ViewHolder {
        private TextView songTextView;
        private TextView artistTextView;
        private ImageView albumImageView;
    }
}
