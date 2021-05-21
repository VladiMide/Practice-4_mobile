package ru.mirea.vaganov.thread;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView infoTextView = findViewById(R.id.textView);
        Thread mainThread = Thread.currentThread();
        infoTextView.setText("Текущий поток: " + mainThread.getName());
        mainThread.setName("MireaThread");
        infoTextView.append("\n Новое имя потока: " + mainThread.getName());
    }

    public void onClick(View view){
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                EditText edit = (EditText) findViewById(R.id.editTextData);
                String data = edit.getText().toString();
                String[] nums = data.split(" ");
                double result = Integer.parseInt(nums[0]) / Integer.parseInt(nums[1]);
                TextView text = (TextView) findViewById(R.id.resCalc);
                text.setText(Double.toString(result));
            }
        });
        t1.start();
    }

    int counter = 0;
    public void onClickImit(View view){
        Runnable runnable = new Runnable() {
            public void run() {
                int numberThread = counter++;
                Log.i("ThreadProject", "Запущен поток No " + numberThread);
                long endTime = System.currentTimeMillis()+ 20 * 1000;
                while (System.currentTimeMillis() < endTime) {
                    synchronized (this) {
                        try {
                            wait(endTime -System.currentTimeMillis());
                        } catch (Exception e) {

                        }
                    }
                }
                Log.i("ThreadProject", "Выполнен поток No " + numberThread);
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }
}