package edu.udacity.faraonc.tangent;

import android.content.Context;
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
 * Created by faraonc on 12/26/17.
 */

class MusicAdapter extends ArrayAdapter<Music> {

    MusicAdapter(Context context, TreeSet<Music> musics) {
        super(context, 0, new ArrayList<Music>(musics));
    }


    @NonNull
    @Override
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

        ViewHolder viewHolder = (ViewHolder) listItemView.getTag();
        Music m = getItem(position);
        viewHolder.artistTextView.setText(m.getArtist());
        viewHolder.songTextView.setText(m.getTitle());
        viewHolder.albumImageView.setImageResource(m.getAlbumImage());
        return listItemView;
    }

    private class ViewHolder {
        private TextView songTextView;
        private TextView artistTextView;
        private ImageView albumImageView;
    }
}
