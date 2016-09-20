package com.vinaymaneti.golfscoreproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private static final String PREFS_FILE = "com.vinaymaneti.golfscoreproject.preferences";
    private static final String KEY_STROKE = "KEY_STROKE";

    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor mEditor;

    GolfScore[] mGolfScore = new GolfScore[18];
    int strokes = 0;
    ScoreAdapter scoreAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);

        sharedPreferences = getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE);
        mEditor = sharedPreferences.edit();


        for (int i = 0; i < mGolfScore.length; i++) {
            strokes = sharedPreferences.getInt(KEY_STROKE + i, 0);
            mGolfScore[i] = new GolfScore("Hole " + (i + 1) + " :", strokes);
        }

        scoreAdapter = new ScoreAdapter(this, mGolfScore);
        mRecyclerView.setAdapter(scoreAdapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.setHasFixedSize(true);
    }

    @Override
    protected void onPause() {
        super.onPause();
        for (int i = 0; i < mGolfScore.length; i++) {
            mEditor.putInt(KEY_STROKE + i, mGolfScore[i].getStrokeCount());
        }
        mEditor.apply();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.overflow_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_favorite) {
            boolean isclear = sharedPreferences.edit().clear().commit();
            if (isclear) {
                for (int i = 0; i < mGolfScore.length; i++) {
                    mGolfScore[i] = new GolfScore("Hole " + (i + 1) + " :", 0);
                }
                scoreAdapter.notifyDataSetChanged();
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
