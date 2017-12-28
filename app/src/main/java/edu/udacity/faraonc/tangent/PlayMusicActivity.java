package edu.udacity.faraonc.tangent;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.os.Handler;

import java.util.ArrayList;

/**
 * Plays the music in a separate user-interface.
 *
 * @author ConardJames
 * @version 122817-01
 */
public class PlayMusicActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private AudioManager audioManager;
    private SeekBar seekbar;
    private double startTime = 0;
    private double finalTime = 0;
    private boolean isSeekBarInitiated = false;
    private Handler myHandler = new Handler();
    private int position;
    private ArrayList<Music> musicList;
    private ImageButton playButton;

    @Override
    /**
     * Set activity and layout.
     *
     * @param savedInstanceState the state of the activity.
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);

        //prevent the screen for turning off while playing music
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        this.audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        Intent intent = getIntent();

        //current position in the list
        this.position = intent.getIntExtra(ListActivity.CURRENT_POSITION, 0);
        //the list of songs
        this.musicList = (ArrayList<Music>) intent.getSerializableExtra(ListActivity.LIST_SONG);
        this.playButton = (ImageButton) findViewById(R.id.play);
        initSeekBar();
        initImageButtonListeners();
        display();
    }

    /**
     * Initialize the seekbar for seeking music.
     */
    private void initSeekBar() {
        this.seekbar = (SeekBar) findViewById(R.id.seek);
        this.seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChanged = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //the position in the current music
                progressChanged = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                if (mediaPlayer == null) {
                    //music needs to be set to ready state (prepared)
                    prepareMusic();
                }
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //seek the media player after touching
                mediaPlayer.seekTo(progressChanged);
            }
        });
    }

    /**
     * Add listeners to the buttons
     */
    private void initImageButtonListeners() {
        this.playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                play(playButton);
            }
        });

        //implement the previous button
        ImageButton button = (ImageButton) findViewById(R.id.prev);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //check for bound
                if (position != 0) {
                    position--;
                    display();
                    //if the music was playing, then play the prev music
                    boolean hasToAutoPlay = false;
                    if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                        hasToAutoPlay = true;
                    }
                    releaseResource();
                    if (hasToAutoPlay) {
                        play(playButton);
                    }
                }
            }
        });

        //implement the next button
        button = (ImageButton) findViewById(R.id.next);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //check for bound
                if (position != musicList.size() - 1) {
                    position++;
                    display();
                    //if the music was playing, then play the prev music
                    boolean hasToAutoPlay = false;
                    if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                        hasToAutoPlay = true;
                    }
                    releaseResource();
                    if (hasToAutoPlay) {
                        play(playButton);
                    }
                }
            }
        });
    }

    /**
     * Prepare music to play on certain time using the seekbar.
     */
    private void prepareMusic() {
        this.mediaPlayer = MediaPlayer.create(this, musicList.get(this.position).getData());
        this.mediaPlayer.setOnCompletionListener(this.doOnCompletion);
        this.finalTime = this.mediaPlayer.getDuration();
        this.startTime = this.mediaPlayer.getCurrentPosition();

        //set seekbar and update the seekbar while music is playing.
        if (!this.isSeekBarInitiated) {
            this.seekbar.setMax((int) this.finalTime);
            this.isSeekBarInitiated = true;
        }
        this.seekbar.setProgress((int) this.startTime);
        this.myHandler.postDelayed(this.UpdateSongTime, 100);
    }

    /**
     * Play the current Music.
     *
     * @param button the play button.
     */
    private void play(ImageButton button) {

        //check for audio focus
        int result = this.audioManager.requestAudioFocus(this.afChangeListener, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT, AudioManager.STREAM_MUSIC);
        if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

            //create the next audio
            if (mediaPlayer == null) {
                this.mediaPlayer = MediaPlayer.create(this, musicList.get(this.position).getData());
                this.mediaPlayer.start();

                //change the play icon to pause icon
                button.setImageResource(R.drawable.ic_pause_black_48dp);
                this.mediaPlayer.setOnCompletionListener(this.doOnCompletion);
                this.finalTime = this.mediaPlayer.getDuration();
                this.startTime = this.mediaPlayer.getCurrentPosition();

                //set seekbar and update the seekbar while music is playing.
                if (!this.isSeekBarInitiated) {
                    this.seekbar.setMax((int) this.finalTime);
                    this.isSeekBarInitiated = true;
                }
                this.seekbar.setProgress((int) this.startTime);
                this.myHandler.postDelayed(this.UpdateSongTime, 100);

            } else if (!this.mediaPlayer.isPlaying()) {
                //change the play icon to pause icon
                button.setImageResource(R.drawable.ic_pause_black_48dp);
                this.mediaPlayer.start();

            } else if (this.mediaPlayer.isPlaying()) {
                //change the pause icon to play icon
                button.setImageResource(R.drawable.ic_play_arrow_black_48dp);
                this.mediaPlayer.pause();
            }
        }
    }

    /**
     * Update the seekbar.
     */
    private Runnable UpdateSongTime = new Runnable() {
        public void run() {
            startTime = mediaPlayer.getCurrentPosition();
            seekbar.setProgress((int) startTime);
            myHandler.postDelayed(this, 100);
        }
    };

    @Override
    /**
     * Pause the media player until the user gets back.
     */
    protected void onStop() {
        super.onStop();
        playButton.setImageResource(R.drawable.ic_play_arrow_black_48dp);
        if (mediaPlayer != null) {
            mediaPlayer.pause();
        }
    }

    @Override
    /**
     * Free up resources.
     */
    protected void onDestroy() {
        super.onDestroy();
        releaseResource();
    }

    /**
     * Release resource for memory and reset seekbar states.
     */
    private void releaseResource() {
        if (this.mediaPlayer != null) {
            this.mediaPlayer.stop();
            this.mediaPlayer.reset();
            this.mediaPlayer.release();
            this.mediaPlayer = null;
            this.myHandler.removeCallbacksAndMessages(null);
            this.isSeekBarInitiated = false;
            this.seekbar.setProgress(0);
            this.audioManager.abandonAudioFocus(this.afChangeListener);
            playButton.setImageResource(R.drawable.ic_play_arrow_black_48dp);
        }
    }

    /**
     * Release resource and play the next song in the list.
     */
    private MediaPlayer.OnCompletionListener doOnCompletion = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseResource();
            if (position != musicList.size() - 1) {
                position++;
                display();
                play(playButton);
            }
        }
    };


    /**
     * Handle audio focus.
     */
    AudioManager.OnAudioFocusChangeListener afChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT) {
                // Pause playback because your Audio Focus was
                // temporarily stolen, but will be back soon.
                // i.e. for a phone call
                if (mediaPlayer != null) {
                    mediaPlayer.pause();
                    playButton.setImageResource(R.drawable.ic_play_arrow_black_48dp);
                }
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                // Stop playback, because you lost the Audio Focus.
                // i.e. the user started some other playback app
                // Remember to unregister your controls/buttons here.
                // And release the kra — Audio Focus!
                // You’re done.
                releaseResource();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                // Lower the volume, because something else is also
                // playing audio over you.
                // i.e. for notifications or navigation directions
                // Depending on your audio playback, you may prefer to
                // pause playback here instead. You do you.
                if (mediaPlayer != null) {
                    mediaPlayer.pause();
                    playButton.setImageResource(R.drawable.ic_play_arrow_black_48dp);
                }
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                // Resume playback, because you hold the Audio Focus
                // again!
                // i.e. the phone call ended or the nav directions
                // are finished
                // If you implement ducking and lower the volume, be
                // sure to return it to normal here, as well.
                if (mediaPlayer != null) {
                    mediaPlayer.start();
                    playButton.setImageResource(R.drawable.ic_pause_black_48dp);
                }
            }
        }
    };

    /**
     * Update the current music for display.
     */
    private void display() {
        updateAlbumImage();
        updateSongName();
        updateArtistName();
    }

    /**
     * Update the album image name.
     */
    private void updateAlbumImage() {
        ((ImageView) findViewById(R.id.play_album_imageview)).setImageResource(this.musicList.get(this.position).getAlbumImage());
    }

    /**
     * Update the song name.
     */
    private void updateSongName() {
        ((TextView) findViewById(R.id.play_song_textview)).setText(this.musicList.get(this.position).getTitle());
    }

    /**
     * Update the artist name.
     */
    private void updateArtistName() {
        ((TextView) findViewById(R.id.play_artist_textview)).setText(this.musicList.get(this.position).getArtist());
    }
}
