package com.rifkiannas.tryoutunmatematika;


import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.rifkiannas.tryoutunmatematika.eventbus.Event;
import com.rifkiannas.tryoutunmatematika.eventbus.GlobalBus;

import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentSoal extends android.support.v4.app.Fragment {


    @BindView(R.id.img_soal)
    ImageView imgSoal;
    @BindView(R.id.rb_jawaban_a)
    RadioButton rbJawabanA;
    @BindView(R.id.rb_jawaban_b)
    RadioButton rbJawabanB;
    @BindView(R.id.rb_jawaban_c)
    RadioButton rbJawabanC;
    @BindView(R.id.rb_jawaban_d)
    RadioButton rbJawabanD;
    @BindView(R.id.rb_jawaban_e)
    RadioButton rbJawabanE;
    @BindView(R.id.rg_jawaban)
    RadioGroup rgJawaban;
    Unbinder unbinder;

    OnCallbackReceived mCallback;

    public interface OnCallbackReceived {
        public void Update(int score);
    }

    public FragmentSoal() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallback = (OnCallbackReceived) activity;
        } catch (ClassCastException e) {

        }

    }

    @Override
    public void onDetach() {
        mCallback = null;
        super.onDetach();
    }

    View v;

    int score;
    String trueAnswer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_fragment_soal, container, false);
        unbinder = ButterKnife.bind(this, v);
        return v;
    }

    @Subscribe
    public void getImage(Event.ActivityToFragment activityToFragment) {
        Log.i("eventImage", String.valueOf(activityToFragment.getImage()));
        imgSoal.setImageResource(activityToFragment.getImage());
    }

    @Subscribe
    public void getChoice(Event.ActivityToFragment activityToFragment) {
        String[] a = activityToFragment.getChoice();
        Log.i("eventChoieA", a[0]);
        Log.i("eventChoieB", a[1]);
        Log.i("eventChoieC", a[2]);
        Log.i("eventChoieD", a[3]);
        Log.i("eventChoieE", a[4]);
        rbJawabanA.setText(a[0]);
        rbJawabanB.setText(a[1]);
        rbJawabanC.setText(a[2]);
        rbJawabanD.setText(a[3]);
        rbJawabanE.setText(a[4]);
    }

    @Subscribe
    public void getTrueAnswer(Event.ActivityToFragment activityToFragment) {
        trueAnswer = String.valueOf(activityToFragment.getTrueAnswer());
        Log.i("eventTrueAnswer", trueAnswer);
    }

    @Override
    public void onStop() {
        super.onStop();
        GlobalBus.getEventBus().unregister(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        GlobalBus.getEventBus().register(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void clearRB() {
        rbJawabanA.setChecked(false);
        rbJawabanB.setChecked(false);
        rbJawabanC.setChecked(false);
        rbJawabanD.setChecked(false);
        rbJawabanE.setChecked(false);
    }

    public void disableRB() {
        rbJawabanA.setEnabled(false);
        rbJawabanB.setEnabled(false);
        rbJawabanC.setEnabled(false);
        rbJawabanD.setEnabled(false);
        rbJawabanE.setEnabled(false);
    }

    public void enableRB() {
        rbJawabanA.setEnabled(true);
        rbJawabanB.setEnabled(true);
        rbJawabanC.setEnabled(true);
        rbJawabanD.setEnabled(true);
        rbJawabanE.setEnabled(true);
    }

    public void gotScore() {
        String rbA = rbJawabanA.getText().toString();
        String rbB = rbJawabanB.getText().toString();
        String rbC = rbJawabanC.getText().toString();
        String rbD = rbJawabanD.getText().toString();
        String rbE = rbJawabanE.getText().toString();
        if (rbA.equals(trueAnswer) && rbJawabanA.isChecked() ) {
            score = score + 5;
//            Toast.makeText(getActivity(), "benar A " + String.valueOf(score), Toast.LENGTH_SHORT).show();
        } else {
            score = score + 0;
//            Toast.makeText(getActivity(), "salah A " + String.valueOf(score), Toast.LENGTH_SHORT).show();
        }

        if (rbB.equals(trueAnswer) && rbJawabanB.isChecked()) {
            score = score + 5;
//            Toast.makeText(getActivity(), "benar B " + String.valueOf(score), Toast.LENGTH_SHORT).show();
        } else {
            score = score + 0;
//            Toast.makeText(getActivity(), "salah B " + String.valueOf(score), Toast.LENGTH_SHORT).show();
        }
        
        if (rbC.equals(trueAnswer) && rbJawabanC.isChecked()) {
            score = score + 5;
//            Toast.makeText(getActivity(), "benar C " + String.valueOf(score), Toast.LENGTH_SHORT).show();
        } else {
            score = score + 0;
//            Toast.makeText(getActivity(), "salah C " + String.valueOf(score), Toast.LENGTH_SHORT).show();
        }

        if (rbD.equals(trueAnswer) && rbJawabanD.isChecked()) {
            score = score + 5;
//            Toast.makeText(getActivity(), "benar D " + String.valueOf(score), Toast.LENGTH_SHORT).show();
        } else {
            score = score + 0;
//            Toast.makeText(getActivity(), "salah D " + String.valueOf(score), Toast.LENGTH_SHORT).show();
        }

        if (rbE.equals(trueAnswer) && rbJawabanE.isChecked()) {
            score = score + 5;
//            Toast.makeText(getActivity(), "benar E " + String.valueOf(score), Toast.LENGTH_SHORT).show();
        } else {
            score = score + 0;
//            Toast.makeText(getActivity(), "salah E " + String.valueOf(score), Toast.LENGTH_SHORT).show();
        }
        mCallback.Update(score);
        Log.i("Score", String.valueOf(score));
    }

    public void resetScore(){
        score = 0;
    }

}
