package com.metaplikasyon.potkal.fragments.fragment_word_game.process;

import android.content.Context;

import com.metaplikasyon.potkal.file.operator.FileManager;
import com.metaplikasyon.potkal.file.path_picker.PathPicker;
import com.metaplikasyon.potkal.fragments.fragment_word_game.FragmentWordGame;
import com.metaplikasyon.potkal.fragments.fragment_word_game.first.FragmentWordGameFirst;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public class GameCreator {
    private ArrayList<GameScreen> gameScreenList;
    private Context context;
    public GameCreator(Context context) {
        gameScreenList=new ArrayList<>();
        this.context=context;
    }

    public ArrayList<GameScreen> createGame(ArrayList<String> setNameList, int qType) {
        createPool(setNameList, qType);
        orderList(gameScreenList, qType);
        return gameScreenList;
    }

    private void createPool(ArrayList<String> setNameList, int qType) {
        ArrayList<JSONObject> jObjList=getPairsJ(setNameList);

        int order=0;
        for(int i=0;i<jObjList.size();i++) {
            JSONObject jTemp=jObjList.get(i);

            Iterator<String> keys=jObjList.get(i).keys();
            while (keys.hasNext()) {
                String key=keys.next();

                int spaceCount=0;
                for(int k=0;k<key.length();k++) {
                    if(String.valueOf(key.charAt(k)).equals(" ")) spaceCount++;
                }

                try {
                    if(jTemp.getJSONObject(key).isNull("def")) continue;
                    else {
                        //setQuestionChoicePair(key, jTemp.getJSONObject(key).getString("def"));
                        GameScreen gs = new GameScreen();
                        gs.setQuestion(jTemp.getJSONObject(key).getString("def"));
                        gs.setAnswer(key);
                        int pts = jTemp.getJSONObject(key).getInt("pts");
                        pts = pts > 5 ? 5 : pts;
                        gs.setPts((pts * 10) * (key.length()-spaceCount));
                        gs.setPtsLetter(pts * 10);

                        if (jTemp.getJSONObject(key).has("exmp")) {
                            String clue = jTemp.getJSONObject(key).getString("exmp");

                            clue = clue.replaceAll("İ", "i");

                            clue = clue.toLowerCase();
                            clue = clue.replaceAll(key.toLowerCase(), "........");
                            gs.setClue(clue);
                        }

                        if (jTemp.getJSONObject(key).has("kind")) {
                            String kind = jTemp.getJSONObject(key).getString("kind");
                            gs.setKind(kind);
                        }

                        gameScreenList.add(gs);
                        order++;
                    }
                }catch (JSONException jsonException) {
                    jsonException.printStackTrace();
                }
            }
        }
    }

    private void orderList(ArrayList<GameScreen> gsl, int qType) {
        if(qType== FragmentWordGameFirst.QTypeByLetterCount) Collections.sort(gsl, Comparators.LETTER_COUNT);
        if(qType== FragmentWordGameFirst.QTypeByRandom) Collections.shuffle(gsl);
    }

    private ArrayList<JSONObject> getPairsJ(ArrayList<String> setNameList) {
        ArrayList<JSONObject> jObjList=new ArrayList<>();
        FileManager fileManager=new FileManager();
        for(int i=0;i<setNameList.size();i++) {
            try {
                jObjList.add(new JSONObject(fileManager.operate().read(new PathPicker(context).get(PathPicker.WORDSET) + File.separator+ setNameList.get(i))));
            } catch (JSONException jsonException) {
                jsonException.printStackTrace();
            }
        }
        return jObjList;
    }

    public static class Comparators {
        public static Comparator<GameScreen> LETTER_COUNT = new Comparator<GameScreen>() {
            @Override
            public int compare(GameScreen gs1, GameScreen gs2) {
                return gs1.getAnswer().length()- gs2.getAnswer().length();
            }
        };
    }
}
