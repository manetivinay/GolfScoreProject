package com.vinaymaneti.golfscoreproject;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by vinaymaneti on 8/23/16.
 */
public class ScoreAdapter extends RecyclerView.Adapter<ScoreAdapter.ScoreViewHolder> {

    private Context mContext;
    private GolfScore[] mGolfScore;

    public ScoreAdapter(Context context, GolfScore[] golfScore) {
        mContext = context;
        mGolfScore = golfScore;
    }

    @Override
    public ScoreViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.score_card, parent, false);
        ScoreViewHolder scoreViewHolder = new ScoreViewHolder(view);
        return scoreViewHolder;
    }

    @Override
    public void onBindViewHolder(final ScoreViewHolder holder, final int position) {
        holder.holeNumberLabel.setText(mGolfScore[position].getName());
        holder.scoreLabel.setText(mGolfScore[position].getStrokeCount() + "");
        holder.minusIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int updatedStrokeCount = mGolfScore[position].getStrokeCount() - 1;
                if (updatedStrokeCount < 0) {
                    updatedStrokeCount = 0;
                }
                mGolfScore[position].setStrokeCount(updatedStrokeCount);
                holder.scoreLabel.setText(updatedStrokeCount + "");
            }
        });
        holder.plusIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int updateStrokeCount = mGolfScore[position].getStrokeCount() + 1;
                mGolfScore[position].setStrokeCount(updateStrokeCount);
                holder.scoreLabel.setText(updateStrokeCount + "");
            }
        });
    }

    @Override
    public int getItemCount() {
        return mGolfScore.length;
    }

    public class ScoreViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.holeEditText)
        TextView holeNumberLabel;
        @Bind(R.id.scoreTextView)
        TextView scoreLabel;
        @Bind(R.id.plusImageView)
        ImageView plusIcon;
        @Bind(R.id.minusImageView)
        ImageView minusIcon;

        public ScoreViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
