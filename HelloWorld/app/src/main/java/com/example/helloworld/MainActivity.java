package com.example.helloworld;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.ArrayList;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    EditText test1;
    // TODO change this to some sort of mapping
    String[] stringEnglish = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l",
            "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x",
            "y", "z", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0",
            ",", ".", "?", " " };

    String[] stringMorse = { ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..",
            ".---", "-.-", ".-..", "--", "-.", "---", ".---.", "--.-", ".-.",
            "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--..", ".----",
            "..---", "...--", "....-", ".....", "-....", "--...", "---..", "----.",
            "-----", "--..--", ".-.-.-", "..--..", "/" };

    ArrayList<String> english = new ArrayList<>(Arrays.asList(stringEnglish));
    ArrayList<String> morse = new ArrayList<>(Arrays.asList(stringMorse));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }



    public void changeTxt(View view){
        // gets I/O
        TextView txtIn = findViewById(R.id.txtInput);
        TextView txtOut = findViewById(R.id.txtMessage);


        String strIn = txtIn.getText().toString();
        String strOut = translate(strIn);

        txtOut.setText(strOut);
    }

    private  String translate(String input){
        char c;
        boolean nat2morse = false;
        String s;
        int idx;
        StringBuilder out = new StringBuilder();


        // cleaning
        input = input.toLowerCase();

        // easy check if the code is morse or natural
        if (input.length() >= 1){
            c = input.charAt(0);
            if (c == '.' || c == '-'){
                nat2morse = true;
            }
        }


        if (nat2morse) {

            String[] splited = input.split("\\s+");
            System.out.println("len:splited:" + splited.length);

            for (String value : splited) {
                s = value;
                idx = morse.indexOf(s);

                // error handling such that one has all the symbols
                if (idx == -1) {
                    return "Unknown symbol " + s;
                }
                out.append(english.get(idx));
            }

        }
        else{
            // loops through the input checks the translation of the items and then prints the translation
            for (int i = 0; i < input.length(); i++) {
                c = input.charAt(i);
                s = String.valueOf(c);
                idx = english.indexOf(s);

                // error handling such that one has all the symbols
                if (idx == -1){
                    return "Unknown symbol " + s;
                }
                out.append(" ");
                out.append(morse.get(idx));
            }
        }
        System.out.println("final: " + out.toString());
        return out.toString();


    }

}