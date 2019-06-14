package com.geodata.dfis.Tools;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * Created by dnherrera on 12/6/2017.
 */

public class TextWatcherAdapter implements TextWatcher {


    public interface TextWatcherListener {

        void onTextChanged(EditText view, String text);

    }

    private final EditText view;
    private final TextWatcherListener listener;

    public TextWatcherAdapter(EditText view, TextWatcherListener listener) {
        this.view = view;
        this.listener = listener;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        listener.onTextChanged(view, s.toString());
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}

