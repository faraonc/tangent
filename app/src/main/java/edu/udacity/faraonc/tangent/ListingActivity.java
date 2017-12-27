package edu.udacity.faraonc.tangent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.TreeSet;

public class ListingActivity extends AppCompatActivity {

    private SessionEnum sessionType;
    private TreeSet<Music> musicList;
    private MusicManager musicManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        this.sessionType = (SessionEnum) intent.getSerializableExtra(LibraryActivity.SESSION);
        if (this.sessionType == SessionEnum.SONGS) {
            setContentView(R.layout.activity_list);
            this.musicList = (TreeSet<Music>) intent.getSerializableExtra(LibraryActivity.LIST_PACKAGE);
            displaySongs();
        } else if (this.sessionType == SessionEnum.ARTISTS) {
            setContentView(R.layout.activity_grid);
            this.musicManager = (MusicManager) intent.getSerializableExtra(LibraryActivity.LIST_PACKAGE);
            displayArtists();
        }
    }

    void displaySongs() {
        ((TextView) findViewById(R.id.list_view_heading)).setText(R.string.song_heading);
        MusicAdapter musicAdapter = new MusicAdapter(this, this.musicList);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(musicAdapter);
    }
    void displayArtists() {
        ((TextView) findViewById(R.id.grid_view_heading)).setText(R.string.artist_heading);
//        MusicAdapter musicAdapter = new MusicAdapter(this, new ArrayList<Music>());
        GridView listView = (GridView) findViewById(R.id.grid);
        listView.setAdapter(musicAdapter);
    }

}
