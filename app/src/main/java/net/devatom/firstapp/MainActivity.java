package net.devatom.firstapp;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import net.devatom.firstapp.tools.LogApp;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private EditText ttValue;
    private Button btTest;

    private int valToFind = 0;
    private int nbEssais = 0, nbEssaisMax = 5;

    private final int minValue = 1;
    private final int maxValue = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(this, R.string.creation, Toast.LENGTH_SHORT).show();
        Log.i("FirstApp",  getString(R.string.creation));


        ttValue = findViewById(R.id.ttValue);
        btTest = findViewById(R.id.btTest);
        valToFind = setValToFind();

        ttValue.requestFocus();

        btTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Je commence par récupérer la valeur saisie dans le champ texte
                int val = 0;
                String message;
                if (ttValue.getText().length() != 0) {
                    val = Integer.parseInt(ttValue.getText().toString());
                }

                //Je compare cette valeur castée en int aux valeurs de mes bornes : 1 et 100
                if (val < minValue || val > maxValue){
                    Toast.makeText(getApplicationContext(), R.string.outofbound, Toast.LENGTH_LONG).show();
                    ttValue.setTextColor(Color.RED);
                }else{
                    ttValue.setTextColor(Color.BLACK);
                    //Je regarde si j'ai déjà atteint le nombre d'essais
                    if (nbEssais < nbEssaisMax - 1){
                        nbEssais++;
                        if (val != valToFind){
                            message = getString(R.string.notfound) + " " + String.format(getString(R.string.tries), (nbEssaisMax - nbEssais));
                            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                        }else{
                            //J'ai gagné J'affiche la deuxième activité
                            message = getString(R.string.found);
                            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();

                            Intent msg = new Intent(MainActivity.this, MessageActivity.class);
                            msg.putExtra("msg", message);
                            startActivity(msg);

                            restart();
                        }
                    }else{
                        //J'ai perdu j'affiche mon autre actvité
                        message = String.format(getString(R.string.gameover), valToFind);
                        Intent msg = new Intent(MainActivity.this, MessageActivity.class);
                        msg.putExtra("msg", message);
                        startActivity(msg);

                        restart(); //Je redémarre la partie
                    }
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, R.string.reveil, Toast.LENGTH_SHORT).show();
        LogApp.i(getString(R.string.reveil));
    }

    @Override
    protected void onPause() {
        super.onPause();
        EditText ttValue = findViewById(R.id.ttValue);
        LogApp.i(ttValue.getText().toString());
    }

    private int setValToFind(){
        //Calcul de la valeur à trouver
        int retVal = new Random().nextInt((maxValue - minValue) + minValue) + minValue;
        LogApp.i(String.valueOf(retVal));
        return retVal;
    }

    private void restart(){
        nbEssais = 0;
        ttValue.setText("");
        valToFind = setValToFind();
    }
}
