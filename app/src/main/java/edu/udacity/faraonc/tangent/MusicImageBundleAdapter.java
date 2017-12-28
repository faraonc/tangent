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
import java.util.TreeMap;

/**
 * Created by ConardJames on 12/26/2017.
 */

class MusicImageBundleAdapter extends ArrayAdapter {

    MusicImageBundleAdapter(Context context, ArrayList<MusicImageBundle> imageBundles) {
        super(context, 0, imageBundles);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.grid_item, parent, false);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.titleTextView = (TextView) listItemView.findViewById(R.id.grid_item_title);
            viewHolder.imageView = (ImageView) listItemView.findViewById(R.id.grid_item_image);
            listItemView.setTag(viewHolder);
        }

        ViewHolder viewHolder = (ViewHolder) listItemView.getTag();
        MusicImageBundle m = (MusicImageBundle)getItem(position);
        viewHolder.titleTextView.setText(m.getName());
        viewHolder.imageView.setImageResource(m.getImage());
        return listItemView;
    }

    private class ViewHolder {
        private TextView titleTextView;
        private ImageView imageView;
    }
}
