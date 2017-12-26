package edu.udacity.faraonc.tangent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.content.Intent;

public class LibraryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);

        RelativeLayout categoryViewGroup = (RelativeLayout) findViewById(R.id.songs_view);
        categoryViewGroup.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent numbersIntent = new Intent(MainActivity.this, NumbersActivity.class);
//                startActivity(numbersIntent);
            }
        });

//        TODO implement Artist view
        categoryViewGroup = (RelativeLayout) findViewById(R.id.artists_view);
        categoryViewGroup.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent familyIntent = new Intent(MainActivity.this, FamilyActivity.class);
//                startActivity(familyIntent);
            }
        });

//        TODO implement Album View
        categoryViewGroup = (RelativeLayout) findViewById(R.id.albums_view);
        categoryViewGroup.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent colorsIntent = new Intent(MainActivity.this, ColorsActivity.class);
//                startActivity(colorsIntent);
            }
        });

    }
}
