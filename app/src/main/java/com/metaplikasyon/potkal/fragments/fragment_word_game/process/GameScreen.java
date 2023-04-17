package com.metaplikasyon.potkal.fragments.fragment_word_game.process;

public class GameScreen {
    private String question, answer, clue, kind;
    private int  unShownCnt, pts, ptsLetter, ptsGained, shownLetterCnt;
    private int answeredAs;

    public int getUnShownCnt() {
        return unShownCnt;
    }

    public void setUnShownCnt(int unShownCnt) {
        this.unShownCnt = unShownCnt;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getPts() {
        return pts;
    }

    public void setPts(int pts) {
        this.pts = pts;
    }

    public int getPtsLetter() {
        return ptsLetter;
    }

    public void setPtsLetter(int ptsLetter) {
        this.ptsLetter = ptsLetter;
    }

    public int getAnsweredAs() {
        return answeredAs;
    }

    public void setAnsweredAs(int answeredAs) {
        this.answeredAs = answeredAs;
    }

    public String getClue() {
        return clue;
    }

    public void setClue(String clue) {
        this.clue = clue;
    }

    public int getPtsGained() {
        return ptsGained;
    }

    public void setPtsGained(int ptsGained) {
        this.ptsGained = ptsGained;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public int getShownLetterCnt() {
        return shownLetterCnt;
    }

    public void setShownLetterCnt(int shownLetterCnt) {
        this.shownLetterCnt = shownLetterCnt;
    }

}
