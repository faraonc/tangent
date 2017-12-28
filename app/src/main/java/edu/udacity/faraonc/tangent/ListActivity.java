package edu.udacity.faraonc.tangent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeSet;

/**
 * Manages the list of musics.
 *
 * @author ConardJames
 * @version 122817-01
 */
public class ListActivity extends AppCompatActivity {

    //keys for intent's data used by PlayMusicActivity
    final static String LIST_SESSION_TYPE = "LIST_SESSION_TYPE";
    final static String LIST_SONG = "LIST_SONG";
    final static String CURRENT_POSITION = "CURRENT_POSITION";

    private String header;
    private Collection<Music> musicList;
    private ListSessionEnum type;

    enum ListSessionEnum {
        SONG,
        GENRE,
        ALBUM,
        ARTIST
    }

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
        this.type = (ListSessionEnum) intent.getSerializableExtra(LIST_SESSION_TYPE);
        setContentView(R.layout.activity_list);
        this.musicList = (Collection<Music>) intent.getSerializableExtra(LibraryActivity.LIST_PACKAGE);
        displaySongs();
    }

    /**
     * Display the songs in the list view.
     */
    void displaySongs() {
        ((TextView) findViewById(R.id.list_view_heading)).setText(this.header);
        MusicAdapter musicAdapter = null;
        if (this.type == ListSessionEnum.SONG) {
            musicAdapter = new MusicAdapter(this, (TreeSet<Music>) this.musicList);
        } else {
            musicAdapter = new MusicAdapter(this, (ArrayList<Music>) this.musicList);
        }
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(musicAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ListActivity.this, PlayMusicActivity.class);
                intent.putExtra(CURRENT_POSITION, i);
                intent.putExtra(LIST_SONG, new ArrayList<Music>(musicList));
                startActivity(intent);
            }
        });
    }
}
