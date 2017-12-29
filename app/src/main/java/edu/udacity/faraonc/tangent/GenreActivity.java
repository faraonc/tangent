package edu.udacity.faraonc.tangent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Manages the list of genres.
 *
 * @author ConardJames
 * @version 122817-01
 */
public class GenreActivity extends AppCompatActivity {

    private String header;
    private MusicManager musicManager;

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
        setContentView(R.layout.activity_list);
        display();
    }

    /**
     * Display the user-interface.
     */
    private void display() {
        ((TextView) findViewById(R.id.list_view_heading)).setText(this.header);
        ArrayList<String> genreList = new ArrayList<>(this.musicManager.getGenres());
        Collections.sort(genreList);
        GenreAdapter genreAdapter = new GenreAdapter(this, genreList);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(genreAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(GenreActivity.this, ListActivity.class);
                String key = (String) adapterView.getItemAtPosition(i);
                intent.putExtra(LibraryActivity.HEADER_NAME, getString(R.string.song_heading));
                intent.putExtra(ListActivity.LIST_SESSION_TYPE, ListActivity.ListSessionEnum.GENRE);
                intent.putExtra(LibraryActivity.LIST_PACKAGE, musicManager.getGenreSongsList(key));
                startActivity(intent);
            }
        });
    }
}
