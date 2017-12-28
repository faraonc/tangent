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
import java.util.TreeMap;

/**
 * Created by faraonc on 12/27/17.
 */

class GenreAdapter extends ArrayAdapter {

    GenreAdapter(Context context, ArrayList<String> genres) {
        super(context, 0, genres);
    }

    @NonNull
    @Override
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

    private class ViewHolder {
        private TextView genreTextView;
    }

}
