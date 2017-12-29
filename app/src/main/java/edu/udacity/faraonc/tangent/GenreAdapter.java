package edu.udacity.faraonc.tangent;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Adapter for the list of genres.
 *
 * @author ConardJames
 * @version 122817-02
 */
class GenreAdapter extends ArrayAdapter {

    /**
     * Construct an adapter for genres.
     *
     * @param context for resource access.
     * @param genres  arraylist of genres.
     */
    GenreAdapter(Context context, ArrayList<String> genres) {
        super(context, 0, genres);
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
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.genre_item, parent, false);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.genreTextView = (TextView) listItemView.findViewById(R.id.genre_textview);
            listItemView.setTag(viewHolder);
        }

        ViewHolder viewHolder = (ViewHolder) listItemView.getTag();
        String genreType = (String) getItem(position);
        viewHolder.genreTextView.setText(genreType);
        return listItemView;
    }

    /**
     * For caching the resource id.
     */
    private class ViewHolder {
        private TextView genreTextView;
    }
}
