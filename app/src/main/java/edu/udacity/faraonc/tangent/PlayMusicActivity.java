package edu.udacity.faraonc.tangent;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.os.Handler;

import java.util.ArrayList;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);
        seekbar = (SeekBar) findViewById(R.id.seek);
        seekbar.setClickable(false);
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        Intent intent = getIntent();
        this.position = intent.getIntExtra(ListActivity.CURRENT_POSITION, 0);
        this.musicList = (ArrayList<Music>) intent.getSerializableExtra(ListActivity.LIST_SONG);
        initImageButtonListeners();
        display();
    }

    private void initImageButtonListeners() {
        final ImageButton button = (ImageButton) findViewById(R.id.play);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int result = audioManager.requestAudioFocus(afChangeListener, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT, AudioManager.STREAM_MUSIC);
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    if (mediaPlayer == null) {
                        mediaPlayer = MediaPlayer.create(PlayMusicActivity.this, musicList.get(position).getData());
                        mediaPlayer.start();
                        button.setImageResource(R.drawable.ic_pause_black_48dp);
                        mediaPlayer.setOnCompletionListener(doOnCompletion);
                        finalTime = mediaPlayer.getDuration();
                        startTime = mediaPlayer.getCurrentPosition();

                        if (!isSeekBarInitiated) {
                            seekbar.setMax((int) finalTime);
                            isSeekBarInitiated = true;
                        }
                        seekbar.setProgress((int) startTime);
                        myHandler.postDelayed(UpdateSongTime, 100);

                    } else if (!mediaPlayer.isPlaying()) {
                        button.setImageResource(R.drawable.ic_pause_black_48dp);
                        mediaPlayer.start();

                    } else if (mediaPlayer.isPlaying()) {
                        button.setImageResource(R.drawable.ic_play_arrow_black_48dp);
                        mediaPlayer.pause();
                    }
                }
            }
        });
    }

    private Runnable UpdateSongTime = new Runnable() {
        public void run() {
            startTime = mediaPlayer.getCurrentPosition();
            seekbar.setProgress((int) startTime);
            myHandler.postDelayed(this, 100);
        }
    };

    @Override
    protected void onStop() {
        super.onStop();
        releaseResource();
    }

    private void releaseResource() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.reset();
            mediaPlayer.release();
            mediaPlayer = null;
            myHandler.removeCallbacksAndMessages(null);
            isSeekBarInitiated = false;
            seekbar.setProgress(0);
            audioManager.abandonAudioFocus(afChangeListener);
        }
    }

    private MediaPlayer.OnCompletionListener doOnCompletion = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseResource();
        }
    };

    AudioManager.OnAudioFocusChangeListener afChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT) {
                // Pause playback because your Audio Focus was
                // temporarily stolen, but will be back soon.
                // i.e. for a phone call
                if (mediaPlayer != null) {
                    mediaPlayer.pause();
                    mediaPlayer.seekTo(0);
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
                    mediaPlayer.seekTo(0);
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
                }
            }
        }
    };

    private void display() {
        updateAlbumImage();
        updateSongName();
        updateArtistName();
    }

    private void updateAlbumImage() {
        ((ImageView) findViewById(R.id.play_album_imageview)).setImageResource(this.musicList.get(position).getAlbumImage());
    }

    private void updateSongName() {
        ((TextView) findViewById(R.id.play_song_textview)).setText(this.musicList.get(position).getTitle());
    }

    private void updateArtistName() {
        ((TextView) findViewById(R.id.play_artist_textview)).setText(this.musicList.get(position).getArtist());
    }
}
