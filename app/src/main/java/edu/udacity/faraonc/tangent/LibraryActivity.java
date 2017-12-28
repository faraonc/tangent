package edu.udacity.faraonc.tangent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.content.Intent;

/**
 * Manages the list of selections
 *
 * @author ConardJames
 * @version 122817-01
 */
public class LibraryActivity extends AppCompatActivity {

    private MusicManager musicManager = new MusicManager();

    final static String HEADER_NAME = "HEADER_NAME";
    final static String LIST_PACKAGE = "LIST_PACKAGE";
    final static String SESSION_TYPE = "SESSION_TYPE";

    @Override
    /**
     * Set activity and layout.
     *
     * @param savedInstanceState the state of the activity.
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);

        initMusicManager();
        /*Uncomment for Debugging*/
        /*Displays contents of data structures in the Logcat */
        //debug();

        RelativeLayout categoryViewGroup = (RelativeLayout) findViewById(R.id.songs_view);
        categoryViewGroup.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LibraryActivity.this, ListActivity.class);
                intent.putExtra(HEADER_NAME, getText(R.string.song_heading));
                intent.putExtra(ListActivity.LIST_SESSION_TYPE, ListActivity.ListSessionEnum.SONG);
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
                intent.putExtra(SESSION_TYPE, MusicImageBundle.ImageBundleEnum.ARTISTS);
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
                intent.putExtra(SESSION_TYPE, MusicImageBundle.ImageBundleEnum.ALBUMS);
                startActivity(intent);
            }
        });

        categoryViewGroup = (RelativeLayout) findViewById(R.id.genres_view);
        categoryViewGroup.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LibraryActivity.this, GenreActivity.class);
                intent.putExtra(HEADER_NAME, getText(R.string.genre_heading));
                intent.putExtra(LIST_PACKAGE, musicManager);
                startActivity(intent);
            }
        });

    }

    /* All musics are licensed for private and commercial usage.*/
    /* https://artlist.io License Number 523370 */
    /* Could be better if reading from the storage or using asset manager. This can do for now.*/

    /**
     * Initialize music manager's data.
     */
    private void initMusicManager() {
        String young_rich_pixies = getString(R.string.young_rich_pixies);
        String a_group = getString(R.string.a_group);
        String evolv = getString(R.string.evolv);
        String josh_leake = getString(R.string.josh_leake);
        String the_robbery = getString(R.string.the_robbery);
        String assaf_ayalon = getString(R.string.assaf_ayalon);
        String motivational_theatrical = getString(R.string.motivational_theatrical);
        String upbeat_acoustic = getString(R.string.upbeat_acoustic);
        String electro_live_synergy = getString(R.string.electro_live_synergy);
        String pure_ambient_electronica = getString(R.string.pure_ambient_electronica);
        String lawless_rock = getString(R.string.lawless_rock);
        String real_rock_roll = getString(R.string.real_rock_roll);
        String unknown = getString(R.string.unknown);
        String thoughts_awaken_us = getString(R.string.thoughts_awaken_us);
        String benjamin = getString(R.string.benjamin);
        String rewire = getString(R.string.rewire);
        String summer_walking = getString(R.string.summer_walking);

        musicManager.addMusic(new Music(R.raw.a_good_mood, getString(R.string.a_good_mood), unknown,
                motivational_theatrical, young_rich_pixies,
                R.drawable.young_rich_pixies_album_image, R.drawable.young_rich_pixies_artist_image));
        musicManager.addMusic(new Music(R.raw.outside_the_window, getString(R.string.outside_the_window), unknown,
                motivational_theatrical, young_rich_pixies,
                R.drawable.young_rich_pixies_album_image, R.drawable.young_rich_pixies_artist_image));
        musicManager.addMusic(new Music(R.raw.rio, getString(R.string.rio), unknown,
                motivational_theatrical, young_rich_pixies,
                R.drawable.young_rich_pixies_album_image, R.drawable.young_rich_pixies_artist_image));
        musicManager.addMusic(new Music(R.raw.slow_energy, getString(R.string.slow_energy), unknown,
                motivational_theatrical, young_rich_pixies,
                R.drawable.young_rich_pixies_album_image, R.drawable.young_rich_pixies_artist_image));
        musicManager.addMusic(new Music(R.raw.verve, getString(R.string.verve), unknown,
                upbeat_acoustic, a_group,
                R.drawable.a_group_album_image, R.drawable.a_group_artist_image));
        musicManager.addMusic(new Music(R.raw.halation, getString(R.string.halation), thoughts_awaken_us,
                electro_live_synergy, evolv,
                R.drawable.thoughts_awaken_us_album_image, R.drawable.evolv_artist_image));
        musicManager.addMusic(new Music(R.raw.roots, getString(R.string.roots), benjamin,
                pure_ambient_electronica, josh_leake,
                R.drawable.benjamin_album_image, R.drawable.josh_leake_artist_image));
        musicManager.addMusic(new Music(R.raw.family, getString(R.string.family), benjamin,
                pure_ambient_electronica, josh_leake,
                R.drawable.benjamin_album_image, R.drawable.josh_leake_artist_image));
        musicManager.addMusic(new Music(R.raw.young_summer, getString(R.string.young_summer), benjamin,
                pure_ambient_electronica, josh_leake,
                R.drawable.benjamin_album_image, R.drawable.josh_leake_artist_image));
        musicManager.addMusic(new Music(R.raw.on_the_way, getString(R.string.on_the_way), unknown,
                lawless_rock, the_robbery,
                R.drawable.the_robbery_album_image, R.drawable.the_robbery_artist_image));
        musicManager.addMusic(new Music(R.raw.on_the_way_ver2, getString(R.string.on_the_way_v2), unknown,
                lawless_rock, the_robbery,
                R.drawable.the_robbery_album_image, R.drawable.the_robbery_artist_image));
        musicManager.addMusic(new Music(R.raw.the_robbery, getString(R.string.the_robbery), unknown,
                lawless_rock, the_robbery,
                R.drawable.the_robbery_album_image, R.drawable.the_robbery_artist_image));
        musicManager.addMusic(new Music(R.raw.counting_the_money, getString(R.string.counting_the_money), unknown,
                lawless_rock, the_robbery,
                R.drawable.the_robbery_album_image, R.drawable.the_robbery_artist_image));
        musicManager.addMusic(new Music(R.raw.abigail, getString(R.string.abigail), rewire,
                real_rock_roll, assaf_ayalon,
                R.drawable.rewire_album_image, R.drawable.assaf_ayalon_artist_image));
        musicManager.addMusic(new Music(R.raw.dark_sky, getString(R.string.dark_sky), rewire,
                real_rock_roll, assaf_ayalon,
                R.drawable.rewire_album_image, R.drawable.assaf_ayalon_artist_image));
        musicManager.addMusic(new Music(R.raw.born_tired_and_raised_lazy, getString(R.string.born_tired_raised_lazy), rewire,
                real_rock_roll, assaf_ayalon,
                R.drawable.rewire_album_image, R.drawable.assaf_ayalon_artist_image));
        musicManager.addMusic(new Music(R.raw.new_way, getString(R.string.new_way), summer_walking,
                motivational_theatrical, young_rich_pixies,
                R.drawable.summer_walking_album_image, R.drawable.young_rich_pixies_artist_image));
        musicManager.addMusic(new Music(R.raw.new_day_comes, getString(R.string.new_day_comes), summer_walking,
                motivational_theatrical, young_rich_pixies,
                R.drawable.summer_walking_album_image, R.drawable.young_rich_pixies_artist_image));
    }

    /**
     * Print data structures' contents in the Logcat.
     */
    private void debug() {
        musicManager.printSongsSet();
        musicManager.printGenresMap();
        musicManager.printAlbumsMap();
        musicManager.printArtistsMap();
        musicManager.printAlbumsImageMap();
        musicManager.printArtistsImageMap();
    }
}
