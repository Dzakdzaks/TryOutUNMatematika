package com.rifkiannas.tryoutunmatematika.eventbus;

public class Event {

    public static class ActivityToFragment {
        private int Image;
        private String TrueAnswer;
        private String[] Choice;

        public ActivityToFragment(int image, String[] choice, String trueAnswer) {
            Image = image;
            Choice = choice;
            TrueAnswer = trueAnswer;
        }

        public int getImage() {
            return Image;
        }

        public String[] getChoice() {
            return Choice;
        }

        public String getTrueAnswer() {
            return TrueAnswer;
        }

    }

    public static class FragmentToActivity {
       private int score;

        public FragmentToActivity(int score) {
            this.score = score;
        }

        public int getScore() {
            return score;
        }

    }
}
