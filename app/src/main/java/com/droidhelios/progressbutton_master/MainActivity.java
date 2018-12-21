package com.droidhelios.progressbutton_master;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import com.progressbutton.ProgressButton;

public class MainActivity extends AppCompatActivity {

    private ProgressButton btnAction;
    private int count = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnAction = ProgressButton.newInstance(this)
                .setText("Send Request")
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        executeTask();
                    }
                });
    }

    private void executeTask() {
        new BackgroundTask().execute();
    }

    private class BackgroundTask extends AsyncTask<Void,Void,Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            btnAction.startProgress();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if(count == 3){
                btnAction.revertSuccessProgress(new ProgressButton.Listener() {
                    @Override
                    public void onAnimationCompleted() {

                    }
                });
            }else{
                btnAction.revertProgress();
            }
            count ++;
        }
    }
}
