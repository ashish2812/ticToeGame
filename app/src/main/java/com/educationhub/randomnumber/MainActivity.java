package com.educationhub.randomnumber;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    //activePlayer =0, nextPlayer = 1;
    int activePlayer = 0;
    int gameState[] = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int winningPositions[][] = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    boolean gameIsActive = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }


    public void dropIn(View view) {

        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if (gameState[tappedCounter] == 2 && gameIsActive) {
            gameState[tappedCounter] = activePlayer;

            counter.setTranslationY(-1000f);
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow_amongus);
                counter.animate().translationYBy(1000f).rotation(360).setDuration(300);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red_amongus);
                counter.animate().translationYBy(1000f).rotation(360).setDuration(300);
                activePlayer = 0;
            }

            for (int[] winningPosition : winningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                        gameState[winningPosition[1]] == gameState[winningPosition[2]]
                        && gameState[winningPosition[0]] != 2) {
                        gameIsActive =false;


                        //someone won the match

                    String winner = "Red";
                    if (gameState[winningPosition[0]] == 0) {
                        winner = "Yellow";
                        TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);
                        winnerMessage.setText(winner + " won the match!");
                        LinearLayout winnerLayout = (LinearLayout) findViewById(R.id.winnerLayout);

                        winnerLayout.setBackgroundColor(Color.parseColor("#FFEA00"));
                        winnerLayout.setVisibility(View.VISIBLE);

                    } else {
                        TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);
                        winnerMessage.setText(winner + " won the match!");
                        LinearLayout winnerLayout = (LinearLayout) findViewById(R.id.winnerLayout);
                        winnerLayout.setBackgroundColor(Color.parseColor("#B71C1C"));
                        winnerLayout.setVisibility(View.VISIBLE);
                    }

                } else {
                    boolean gameIsOver = true;
                    for (int counterState : gameState) {
                        if (counterState == 2) gameIsOver = false;

                    }
                    if (gameIsOver) {

                        TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);
                        winnerMessage.setText("It's a Draw");
                        LinearLayout winnerLayout = (LinearLayout) findViewById(R.id.winnerLayout);
                        winnerLayout.setBackgroundColor(Color.parseColor("#E65100"));
                        winnerLayout.setVisibility(View.VISIBLE);
                    }

                }
            }
        }

    }




    public void playAgain(View view) {
gameIsActive = true;
        LinearLayout winnerLayout = (LinearLayout)findViewById(R.id.winnerLayout);
        winnerLayout.setVisibility(View.GONE);

        activePlayer =0;
        for(int i =0;i<gameState.length;i++){

            gameState[i]=2;

        }
        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        for(int i =0;i<gridLayout.getChildCount();i++){
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }

    }
}
