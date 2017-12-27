package edu.udacity.faraonc.tangent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.content.Intent;

//TODO import back button
public class LibraryActivity extends AppCompatActivity {

    private MusicManager musicManager = new MusicManager();
    final static String HEADER_NAME = "HEADER_NAME";
    final static String LIST_PACKAGE = "LIST_PACKAGE";
    final static String SESSION_TYPE = "SESSION_TYPE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);

        initMusicManager();
        musicManager.printSongsSet();
        musicManager.printGenresMap();
        musicManager.printAlbumsMap();
        musicManager.printArtistsMap();

        RelativeLayout categoryViewGroup = (RelativeLayout) findViewById(R.id.songs_view);
        categoryViewGroup.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LibraryActivity.this, ListActivity.class);
                intent.putExtra(HEADER_NAME, getText(R.string.song_heading));
                intent.putExtra(LIST_PACKAGE, musicManager.getSongsList());
                startActivity(intent);
            }
        });

        categoryViewGroup = (RelativeLayout) findViewById(R.id.artists_view);
        categoryViewGroup.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LibraryActivity.this, GridActivity.class);
                intent.putExtra(HEADER_NAME, getText(R.string.artist_heading));
                intent.putExtra(LIST_PACKAGE, musicManager);
                intent.putExtra(SESSION_TYPE, MusicImageBundleEnum.ARTISTS);
                startActivity(intent);
            }
        });

        categoryViewGroup = (RelativeLayout) findViewById(R.id.albums_view);
        categoryViewGroup.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LibraryActivity.this, GridActivity.class);
                intent.putExtra(HEADER_NAME, getText(R.string.album_heading));
                intent.putExtra(LIST_PACKAGE, musicManager);
                intent.putExtra(SESSION_TYPE, MusicImageBundleEnum.ALBUMS);
                startActivity(intent);
            }
        });

//        TODO implement Genre View
        categoryViewGroup = (RelativeLayout) findViewById(R.id.genres_view);
        categoryViewGroup.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent colorsIntent = new Intent(MainActivity.this, ColorsActivity.class);
//                startActivity(colorsIntent);
            }
        });

    }

    //TODO onRestore

    //TODO onSave

    /* All musics are licensed for private and commercial usage.*/
    /* https://artlist.io License Number 523370 */
    /* Could be better if reading from the storage or using asset manager. This can do for now.*/
    private void initMusicManager() {
        musicManager.addMusic(new Music(R.raw.a_good_mood, "A Good Mood", "Unknown",
                "Motivational & Theatrical Electro-Acoustic", "Young Rich Pixies",
                R.drawable.young_rich_pixies_album_image, R.drawable.young_rich_pixies_artist_image));
        musicManager.addMusic(new Music(R.raw.outside_the_window, "Outside the Window", "Unknown",
                "Motivational & Theatrical Electro-Acoustic", "Young Rich Pixies",
                R.drawable.young_rich_pixies_album_image, R.drawable.young_rich_pixies_artist_image));
        musicManager.addMusic(new Music(R.raw.rio, "Rio", "Unknown",
                "Motivational & Theatrical Electro-Acoustic", "Young Rich Pixies",
                R.drawable.young_rich_pixies_album_image, R.drawable.young_rich_pixies_artist_image));
        musicManager.addMusic(new Music(R.raw.slow_energy, "Slow Energy", "Unknown",
                "Motivational & Theatrical Electro-Acoustic", "Young Rich Pixies",
                R.drawable.young_rich_pixies_album_image, R.drawable.young_rich_pixies_artist_image));
        musicManager.addMusic(new Music(R.raw.verve, "Verve", "Unknown",
                "Upbeat Acoustic & Rhythmic Songs", "A-GROUP",
                R.drawable.a_group_album_image, R.drawable.a_group_artist_image));
        musicManager.addMusic(new Music(R.raw.halation, "Halation", "Thoughts Awaken US",
                "Electro-Live Synergy", "Evolv",
                R.drawable.thoughts_awaken_us_album_image, R.drawable.evolv_artist_image));
        musicManager.addMusic(new Music(R.raw.roots, "Roots", "Benjamin",
                "Pure Ambient Electronica", "Josh Leake",
                R.drawable.benjamin_album_image, R.drawable.josh_leake_artist_image));
        musicManager.addMusic(new Music(R.raw.family, "Family", "Benjamin",
                "Pure Ambient Electronica", "Josh Leake",
                R.drawable.benjamin_album_image, R.drawable.josh_leake_artist_image));
        musicManager.addMusic(new Music(R.raw.young_summer, "Young Summer", "Benjamin",
                "Pure Ambient Electronica", "Josh Leake",
                R.drawable.benjamin_album_image, R.drawable.josh_leake_artist_image));
        musicManager.addMusic(new Music(R.raw.on_the_way, "On the Way", "Unknown",
                "Lawless Rock", "The Robbery",
                R.drawable.the_robbery_album_image, R.drawable.the_robbery_artist_image));
        musicManager.addMusic(new Music(R.raw.on_the_way_ver2, "On the Way Ver2", "Unknown",
                "Lawless Rock", "The Robbery",
                R.drawable.the_robbery_album_image, R.drawable.the_robbery_artist_image));
        musicManager.addMusic(new Music(R.raw.the_robbery, "The Robbery", "Unknown",
                "Lawless Rock", "The Robbery",
                R.drawable.the_robbery_album_image, R.drawable.the_robbery_artist_image));
        musicManager.addMusic(new Music(R.raw.counting_the_money, "Counting the Money", "Unknown",
                "Lawless Rock", "The Robbery",
                R.drawable.the_robbery_album_image, R.drawable.the_robbery_artist_image));
        musicManager.addMusic(new Music(R.raw.abigail, "Abigail", "Rewire",
                "Real Rock & Roll", "Assaf Ayalon",
                R.drawable.rewire_album_image, R.drawable.assaf_ayalon_artist_image));
        musicManager.addMusic(new Music(R.raw.dark_sky, "Dark Sky", "Rewire",
                "Real Rock & Roll", "Assaf Ayalon",
                R.drawable.rewire_album_image, R.drawable.assaf_ayalon_artist_image));
        musicManager.addMusic(new Music(R.raw.born_tired_and_raised_lazy, "Born Tired and Raised Lazy", "Rewire",
                "Real Rock & Roll", "Assaf Ayalon",
                R.drawable.rewire_album_image, R.drawable.assaf_ayalon_artist_image));
        musicManager.addMusic(new Music(R.raw.new_way, "New Way", "Summer Walking",
                "Motivational & Theatrical Electro-Acoustic", "Young Rich Pixies",
                R.drawable.summer_walking_album_image, R.drawable.young_rich_pixies_artist_image));
        musicManager.addMusic(new Music(R.raw.new_day_comes, "New Day Comes", "Summer Walking",
                "Motivational & Theatrical Electro-Acoustic", "Young Rich Pixies",
                R.drawable.summer_walking_album_image, R.drawable.young_rich_pixies_artist_image));
    }
}
