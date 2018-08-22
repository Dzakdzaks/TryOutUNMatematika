package com.rifkiannas.tryoutunmatematika;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.rifkiannas.tryoutunmatematika.eventbus.Event;
import com.rifkiannas.tryoutunmatematika.eventbus.GlobalBus;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements FragmentSoal.OnCallbackReceived {

    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.btn_start)
    Button btnStart;

    CountDownTimer countDownTimer;
    private static final String FORMAT = "%02d:%02d:%02d";
    int timeInMillis = 10000;
    //      int timeInMillis = 5400000;
    int countDownInterval = 1000;

    ConstraintLayout.LayoutParams layoutParams;
    @BindView(R.id.btn_prev)
    Button btnPrev;
    @BindView(R.id.btn_next)
    Button btnNext;

    int img, choice, answer;

    int[] imageList = {
            R.drawable.jkt,
            R.drawable.ic_launcher_background
    };

    String[][] choiceList = new String[][]{
            new String[]{"JKT 48","AKB 48","JGL 48","BKS 48","TGL 48"},
            new String[]{"Android","IOS","Unix","Linux","Windows"}
    };


    String[] trueAnswerList = {
            "JKT 48",
            "Android",
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        layoutParams = (ConstraintLayout.LayoutParams) tvTime.getLayoutParams();
        Log.i("choice", String.valueOf(choiceList[0]));
//        disableRBOEG();
        btnNext.setEnabled(false);
        btnPrev.setEnabled(false);
        start();
        frag();
        next();
        prev();
    }

    public void start() {
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countDownTimer = new CountDownTimer(timeInMillis, countDownInterval) {
                    @Override
                    public void onTick(long l) {
                        btnNext.setEnabled(true);
                        btnPrev.setEnabled(true);
                        tvTime.setText("" + String.format(FORMAT,
                                TimeUnit.MILLISECONDS.toHours(l),
                                TimeUnit.MILLISECONDS.toMinutes(l) - TimeUnit.HOURS.toMinutes(
                                        TimeUnit.MILLISECONDS.toHours(l)),
                                TimeUnit.MILLISECONDS.toSeconds(l) - TimeUnit.MINUTES.toSeconds(
                                        TimeUnit.MILLISECONDS.toMinutes(l))));
                        btnStart.setVisibility(View.INVISIBLE);
                        layoutParams.horizontalBias = 0.5f;
                        tvTime.setLayoutParams(layoutParams);
//                        enableRBOEG();
                        try {
                            Event.ActivityToFragment activityToFragment = new Event.ActivityToFragment(imageList[img], choiceList[choice], trueAnswerList[answer]);
                            GlobalBus.getEventBus().post(activityToFragment);
                        } catch (Exception e){
//                            Toast.makeText(MainActivity.this, "Selamat Anda TOLOL", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFinish() {
                        tvTime.setText("Time's Up!");
                        btnStart.setVisibility(View.VISIBLE);
                        layoutParams.horizontalBias = 0.4f;
                        tvTime.setLayoutParams(layoutParams);
                        img = 0;
                        choice = 0;
                        answer = 0;
                        btnNext.setEnabled(false);
                        btnPrev.setEnabled(false);
                        getResetScore();
                    }
                }.start();
            }
        });
    }

    private void frag() {
        FragmentSoal fragmentSoal = new FragmentSoal();
        fragmentSoal.setArguments(getIntent().getExtras());
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame, fragmentSoal, "FragmentSoal").commit();
    }

    private void next() {
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img++;
                choice++;
                answer++;
                gotScores();
//                clearRBCOEG();
            }
        });
    }

    private void prev() {
        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img--;
                choice--;
                answer--;
            }
        });
    }

    private void clearRBCOEG(){
        FragmentSoal fragment = (FragmentSoal) getSupportFragmentManager().findFragmentById(R.id.frame);
        fragment.clearRB();
    }

    private void disableRBOEG(){
        FragmentSoal fragment = (FragmentSoal) getSupportFragmentManager().findFragmentById(R.id.frame);
        fragment.disableRB();
    }

    private void enableRBOEG(){
        FragmentSoal fragment = (FragmentSoal) getSupportFragmentManager().findFragmentById(R.id.frame);
        fragment.enableRB();
    }

    private void gotScores(){
        FragmentSoal fragment = (FragmentSoal) getSupportFragmentManager().findFragmentById(R.id.frame);
        fragment.gotScore();
    }

    private void getResetScore(){
        FragmentSoal fragment = (FragmentSoal) getSupportFragmentManager().findFragmentById(R.id.frame);
        fragment.resetScore();
    }

    @Override
    public void Update(int score) {
        Toast.makeText(this, "Total Score : " + score, Toast.LENGTH_SHORT).show();

    }
}
