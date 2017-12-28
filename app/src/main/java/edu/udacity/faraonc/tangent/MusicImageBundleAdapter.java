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


/**
 * Adapter for the grid of music image bundle.
 *
 * @author ConardJames
 * @version 122817-01
 */
class MusicImageBundleAdapter extends ArrayAdapter {

    /**
     * Constuct and adapter for bundles of music images and names.
     *
     * @param context      for resource access.
     * @param imageBundles the bundles of images and names.
     */
    MusicImageBundleAdapter(Context context, ArrayList<MusicImageBundle> imageBundles) {
        super(context, 0, imageBundles);
    }

    @NonNull
    @Override
    /**
     * Get the view for each entry of the GridView.
     *
     * @param position current position in the adapter's list.
     * @param convertView the grid item view.
     * @param parent the parent view group.
     */
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
        MusicImageBundle m = (MusicImageBundle) getItem(position);
        viewHolder.titleTextView.setText(m.getName());
        viewHolder.imageView.setImageResource(m.getImage());
        return listItemView;
    }

    /**
     * For caching the resource ids.
     */
    private class ViewHolder {
        private TextView titleTextView;
        private ImageView imageView;
    }
}
