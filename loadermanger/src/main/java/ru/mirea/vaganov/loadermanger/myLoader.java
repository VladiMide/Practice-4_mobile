package ru.mirea.vaganov.loadermanger;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;

import androidx.annotation.NonNull;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class myLoader extends AsyncTaskLoader<String> {
    private String shuffleWord;
    public static final String  ARG_WORD = "word";

    public myLoader(@NonNull Context context, Bundle args){
        super(context);
        if (args != null){
            shuffleWord = args.getString(ARG_WORD);
        }
    }

    @Override
    protected void onStartLoading(){
        super.onStartLoading();
        forceLoad();
    }

    @Override
    public String loadInBackground() {
        SystemClock.sleep(5000);
        List<String> letters = Arrays.asList(shuffleWord.split(""));
        Collections.shuffle(letters);
        String shuffled = "";
        for (String letter : letters) {
            shuffled += letter;
        }
        return shuffled;
    }
}