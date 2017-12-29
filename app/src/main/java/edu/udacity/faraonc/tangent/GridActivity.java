package edu.udacity.faraonc.tangent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Manages the grid of music image bundles.
 *
 * @author ConardJames
 * @version 122817-02
 */
public class GridActivity extends AppCompatActivity {

    private String header;
    private MusicManager musicManager;
    private MusicImageBundle.ImageBundleEnum sessionType;

    @Override
    /**
     * Set activity and layout.
     *
     * @param savedInstanceState the state of the activity.
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        this.header = (String) intent.getSerializableExtra(LibraryActivity.HEADER_NAME);
        this.musicManager = (MusicManager) intent.getSerializableExtra(LibraryActivity.LIST_PACKAGE);
        this.sessionType = (MusicImageBundle.ImageBundleEnum) intent.getSerializableExtra(LibraryActivity.SESSION_TYPE);
        setContentView(R.layout.activity_grid);
        ((Button) findViewById(R.id.grid_home_button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GridActivity.this, LibraryActivity.class);
                startActivity(intent);
            }
        });
        display();
    }

    /**
     * Display the correct bundle information.
     */
    private void display() {
        if (this.sessionType == MusicImageBundle.ImageBundleEnum.ARTISTS) {
            displayArtistsGrid();
        } else if (this.sessionType == MusicImageBundle.ImageBundleEnum.ALBUMS) {
            displayAlbumsGrid();
        }
    }

    /**
     * Display the content for artists.
     */
    private void displayArtistsGrid() {
        ((TextView) findViewById(R.id.grid_view_heading)).setText(this.header);
        GridView gridView = (GridView) findViewById(R.id.grid);
        ArrayList<MusicImageBundle> bundles = new ArrayList<>(this.musicManager.getArtistsImageMap().values());
        Collections.sort(bundles);
        MusicImageBundleAdapter artistAdapter = new MusicImageBundleAdapter(this, bundles);
        gridView.setAdapter(artistAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(GridActivity.this, ListActivity.class);
                MusicImageBundle key = (MusicImageBundle) adapterView.getItemAtPosition(i);
                intent.putExtra(LibraryActivity.HEADER_NAME, getString(R.string.song_heading));
                intent.putExtra(ListActivity.LIST_SESSION_TYPE, ListActivity.ListSessionEnum.ARTIST);
                intent.putExtra(LibraryActivity.LIST_PACKAGE, musicManager.getArtistSongsList(key.getName()));
                startActivity(intent);
            }
        });
    }

    /**
     * Display the content for albums.
     */
    private void displayAlbumsGrid() {
        ((TextView) findViewById(R.id.grid_view_heading)).setText(this.header);
        GridView gridView = (GridView) findViewById(R.id.grid);
        ArrayList<MusicImageBundle> bundles = new ArrayList<>(this.musicManager.getAlbumsImageMap().values());
        Collections.sort(bundles);
        MusicImageBundleAdapter artistAdapter = new MusicImageBundleAdapter(this, bundles);
        gridView.setAdapter(artistAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(GridActivity.this, ListActivity.class);
                MusicImageBundle key = (MusicImageBundle) adapterView.getItemAtPosition(i);
                intent.putExtra(LibraryActivity.HEADER_NAME, getString(R.string.song_heading));
                intent.putExtra(ListActivity.LIST_SESSION_TYPE, ListActivity.ListSessionEnum.ALBUM);
                intent.putExtra(LibraryActivity.LIST_PACKAGE, musicManager.getAlbumSongsList(key.getName()));
                startActivity(intent);
            }
        });
    }
}
