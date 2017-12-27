package edu.udacity.faraonc.tangent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class GridActivity extends AppCompatActivity {

    private String header;
    private MusicManager musicManager;
    private MusicImageBundleEnum sessionType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        this.header = (String) intent.getSerializableExtra(LibraryActivity.HEADER_NAME);
        setContentView(R.layout.activity_grid);
        this.musicManager = (MusicManager) intent.getSerializableExtra(LibraryActivity.LIST_PACKAGE);
        this.sessionType = (MusicImageBundleEnum) intent.getSerializableExtra(LibraryActivity.SESSION_TYPE);
        display();
    }

    private void display(){
        if(this.sessionType == MusicImageBundleEnum.ARTISTS){
            displayArtistsGrid();
        } else if(this.sessionType == MusicImageBundleEnum.ALBUMS){
            displayAlbumsGrid();
        }
    }

    private void displayArtistsGrid(){
        ((TextView) findViewById(R.id.grid_view_heading)).setText(this.header);
        GridView gridView = (GridView) findViewById(R.id.grid);
        ArrayList<MusicImageBundle> bundles = new ArrayList<>(this.musicManager.getArtistsImageMap().values());
        Collections.sort(bundles);
        MusicImageBundleAdapter artistAdapter = new MusicImageBundleAdapter(this, bundles, this.musicManager.getArtistsMap());
        gridView.setAdapter(artistAdapter);

    }

    private void displayAlbumsGrid(){
        ((TextView) findViewById(R.id.grid_view_heading)).setText(this.header);
        GridView gridView = (GridView) findViewById(R.id.grid);
        ArrayList<MusicImageBundle> bundles = new ArrayList<>(this.musicManager.getAlbumsImageMap().values());
        Collections.sort(bundles);
        MusicImageBundleAdapter artistAdapter = new MusicImageBundleAdapter(this, bundles, this.musicManager.getAlbumsMap());
        gridView.setAdapter(artistAdapter);

    }
}
