package pl.gda.wsb.kalkulatorbmi;

import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    EditText weightInput, heightInput;
    Button calculateButton, saveButton, idealWeightButton;
    TextView resultText, lastResult, idealWeightText;
    ImageView bmiImage;
    RadioGroup genderGroup;
    RadioButton male, female;

    double lastBmiValue = 0.0;
    String lastBmiCategory = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 🔹 Powiązanie elementów z XML
        weightInput = findViewById(R.id.weightInput);
        heightInput = findViewById(R.id.heightInput);
        calculateButton = findViewById(R.id.calculateButton);
        saveButton = findViewById(R.id.saveButton);
        idealWeightButton = findViewById(R.id.idealWeightButton);
        resultText = findViewById(R.id.resultText);
        lastResult = findViewById(R.id.lastResult);
        idealWeightText = findViewById(R.id.idealWeightText);
        bmiImage = findViewById(R.id.bmiImage);
        genderGroup = findViewById(R.id.genderGroup);
        male = findViewById(R.id.male);
        female = findViewById(R.id.female);

        // 🔹 ostatni zapisany wynik
        SharedPreferences prefs = getSharedPreferences("bmi_prefs", MODE_PRIVATE);
        String savedResult = prefs.getString("last_bmi", "brak");
        lastResult.setText("Ostatni zapisany wynik: " + savedResult);

        // 🔹 Oblicz BMI
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String weightStr = weightInput.getText().toString();
                String heightStr = heightInput.getText().toString();

                if (weightStr.isEmpty() || heightStr.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Uzupełnij wszystkie pola", Toast.LENGTH_SHORT).show();
                    return;
                }

                double weight = Double.parseDouble(weightStr);
                double height = Double.parseDouble(heightStr) / 100; // cm -> m
                double bmi = weight / (height * height);

                boolean isMale = male.isChecked();
                String category;

                if (bmi < 18.5) {
                    category = isMale ? "Niedowaga (Mężczyzna)" : "Niedowaga (Kobieta)";
                    resultText.setTextColor(Color.BLUE);
                } else if (bmi < 25) {
                    category = isMale ? "Waga prawidłowa (Mężczyzna)" : "Waga prawidłowa (Kobieta)";
                    resultText.setTextColor(Color.parseColor("#388E3C")); // zielony
                } else if (bmi < 30) {
                    category = isMale ? "Nadwaga (Mężczyzna)" : "Nadwaga (Kobieta)";
                    resultText.setTextColor(Color.parseColor("#FFA000")); // pomarańczowy
                } else {
                    category = isMale ? "Otyłość (Mężczyzna)" : "Otyłość (Kobieta)";
                    resultText.setTextColor(Color.RED);
                }

                String result = String.format("Twoje BMI: %.2f\n%s", bmi, category);
                resultText.setText(result);

                lastBmiValue = bmi;
                lastBmiCategory = category;
                idealWeightText.setText(""); // wyczyść stare dane
            }
        });

        // 🔹 Zapisz wynik
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lastBmiValue == 0.0) {
                    Toast.makeText(MainActivity.this, "Najpierw oblicz BMI!", Toast.LENGTH_SHORT).show();
                    return;
                }

                String resultToSave = String.format("%.2f (%s)", lastBmiValue, lastBmiCategory);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("last_bmi", resultToSave);
                editor.apply();

                lastResult.setText("Ostatni zapisany wynik: " + resultToSave);
                Toast.makeText(MainActivity.this, "Wynik zapisany", Toast.LENGTH_SHORT).show();
            }
        });

        // 🔹 Oblicz idealną wagę
        idealWeightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String heightStr = heightInput.getText().toString();

                if (heightStr.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Podaj wzrost!", Toast.LENGTH_SHORT).show();
                    return;
                }

                double height = Double.parseDouble(heightStr) / 100; // cm -> m
                double minWeight = 18.5 * (height * height);
                double maxWeight = 24.9 * (height * height);

                String idealRange = String.format("Dla wzrostu %.0f cm prawidłowa waga to:\n%.1f kg - %.1f kg",
                        Double.parseDouble(heightStr), minWeight, maxWeight);

                idealWeightText.setText(idealRange);
                idealWeightText.setTextColor(Color.parseColor("#9C27B0")); // fioletowy
            }
        });
    }
}
