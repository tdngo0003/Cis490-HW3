package edu.louisville.cis490.prs.paperrockscissors;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class PaperRockScissors extends Activity implements OnClickListener {

    public enum Option {
        ROCK, PAPER, SCISSORS
    }

    public enum Result {
        WIN, LOSS, DRAW
    }

    private Option userSelection;
    private Result gameResult;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paper_rock_scissors);

        Button buttonRock = (Button) findViewById(R.id.buttonRock);
        Button buttonPaper = (Button) findViewById(R.id.buttonPaper);
        Button buttonScissors = (Button) findViewById(R.id.buttonScissors);


        buttonRock.setOnClickListener(this);
        buttonPaper.setOnClickListener(this);
        buttonScissors.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.paper_rock_scissors, menu);
        return true;
    }

    @Override
    public void onClick(View v) {

        ImageView imageView = (ImageView) findViewById(R.id.imageViewAnswerUser);
        boolean play = true;

        switch (v.getId()) {
            case R.id.buttonRock:
                userSelection = Option.ROCK;
                imageView.setImageResource(R.drawable.rock1);
                break;
            case R.id.buttonPaper:
                userSelection = Option.PAPER;
                imageView.setImageResource(R.drawable.paper1);
                break;
            case R.id.buttonScissors:
                userSelection = Option.SCISSORS;
                imageView.setImageResource(R.drawable.scissors1);
                break;
        }

        if(play) {
            play();
            showResults();
        }
    }

    private void showResults() {
        AlertDialog.Builder builder = new AlertDialog.Builder(PaperRockScissors.this);
        builder.setCancelable(false);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Do nothing
            }
        });

        if(gameResult == Result.LOSS) {
            builder.setMessage("You Loose!");
        } else if(gameResult == Result.WIN) {
            builder.setMessage("You Win!");
        } else if(gameResult == Result.DRAW) {
            builder.setMessage("It's a draw!");
        }

        AlertDialog alert = builder.create();
        alert.show();
    }

    private void play() {
        // Generates a random play.
        int rand =  ((int)(Math.random() * 10)) % 3;
        Option androidSelection = null;
        ImageView imageView = (ImageView) findViewById(R.id.imageViewAnswerAndroid);

        // Sets the right image according to random selection.
        switch (rand) {
            case 0:
                androidSelection = Option.ROCK;
                imageView.setImageResource(R.drawable.rock1);
                break;
            case 1:
                androidSelection = Option.PAPER;
                imageView.setImageResource(R.drawable.paper1);
                break;
            case 2:
                androidSelection = Option.SCISSORS;
                imageView.setImageResource(R.drawable.scissors1);
                break;
        }
        // Determine game result according to user selection and Android selection.
        if(androidSelection == userSelection) {
            gameResult = Result.DRAW;
        }
        else if(androidSelection == Option.ROCK && userSelection == Option.SCISSORS) {
            gameResult = Result.LOSS;
        }
        else if(androidSelection == Option.PAPER && userSelection == Option.ROCK) {
            gameResult = Result.LOSS;
        }
        else if(androidSelection == Option.SCISSORS && userSelection == Option.PAPER) {
            gameResult = Result.LOSS;
        } else {
            gameResult = Result.WIN;
        }
    }
}