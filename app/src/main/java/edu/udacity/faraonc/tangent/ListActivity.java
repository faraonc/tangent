package edu.udacity.faraonc.tangent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.TreeSet;

public class ListActivity extends AppCompatActivity {

    private String header;
    private TreeSet<Music> musicList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        this.header = (String) intent.getSerializableExtra(LibraryActivity.HEADER_NAME);
        setContentView(R.layout.activity_list);
        this.musicList = (TreeSet<Music>) intent.getSerializableExtra(LibraryActivity.LIST_PACKAGE);
        displaySongs();

    }

    void displaySongs() {
        ((TextView) findViewById(R.id.list_view_heading)).setText(this.header);
        MusicAdapter musicAdapter = new MusicAdapter(this, this.musicList);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(musicAdapter);
    }

}
