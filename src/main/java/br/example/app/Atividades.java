package br.example.app;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Atividades extends AppCompatActivity {
    public static String notify_title = "notify_title";
    public static String notify_content = "notify_content";

    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atividades);
        textView = findViewById(R.id.notifyText);
        updateUI();
    }


    private void updateUI() {
        String notifyData =
                getIntent().getExtras().get(notify_title) + " \n" +
                        getIntent().getExtras().get(notify_content);
        textView.setText(notifyData);
    }
}