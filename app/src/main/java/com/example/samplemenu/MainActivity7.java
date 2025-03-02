package com.example.samplemenu;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity7 extends AppCompatActivity {
    EditText Et_text;
    Button button;
    TextView Text_view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main7);

        Et_text = findViewById(R.id.inputText);
        Text_view = findViewById(R.id.outputText);
        Et_text.setText("scenario 1");
        button = findViewById(R.id.submitButton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    String numberString ="0"; // Et_text.getText().toString();
                    int number = Integer.parseInt(numberString);

                    StringBuilder result = new StringBuilder();

//                    for (int i = 1; i <= 10; i++) {
//                        result.append(number).append(" x ").append(i).append(" = ").append(number * i).append("\n");
//                    }
                    for (int i = 1; i <= 3; i++) {
                        int number2=Integer.parseInt("10");;
                        result.append("motor").append("  ").append(i).append(" = ").append(number2 * i).append("\n");
                    }
                    for (int j = 1; j <= 4; j++) {
                        int number3=Integer.parseInt("10");;
                        result.append("RGB").append("  ").append(j).append(" = ").append(number3 * j).append("\n");
                    }
                    for (int k = 1; k <= 3; k++) {
                        int number4=Integer.parseInt("10");;
                        result.append("Display").append("  ").append(k).append(" = ").append(number4 * k).append("\n");
                    }

                    Text_view.setText(result.toString());
                } catch (NumberFormatException e) {
                    Text_view.setText("Please enter a valid number");
                } catch (Exception e) {
                    Text_view.setText("An error occurred");
                }

            }
        });
    }
}