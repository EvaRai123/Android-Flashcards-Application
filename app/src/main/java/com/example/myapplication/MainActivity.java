package com.example.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.FrameLayout;
import android.widget.ScrollView;
import androidx.constraintlayout.widget.ConstraintLayout;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FrameLayout mainLayout;
    private ScrollView contentScroll;
    private ConstraintLayout contentLayout;
    private final List<Flashcard> flashcards = new ArrayList<>();
    private boolean isDarkTheme = false;
    private boolean isLavenderGalaxyActive = false;

    private EditText questionInput;
    private EditText answerInput;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainLayout = findViewById(R.id.main_layout);
        contentScroll = findViewById(R.id.content_scroll);
        contentLayout = findViewById(R.id.content_layout);

        questionInput = findViewById(R.id.edit_question);
        answerInput = findViewById(R.id.edit_answer);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        Button addFlashcardButton = findViewById(R.id.add_flashcard_button);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        Button showFlashcardsButton = findViewById(R.id.show_flashcards_button);
        Button toggleThemeButton = findViewById(R.id.toggle_theme_button);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        Button lavenderGalaxyButton = findViewById(R.id.lavender_galaxy_button);
        Button roseGalaxyButton = findViewById(R.id.rose_galaxy_button);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        Button daisyGalaxyButton = findViewById(R.id.daisy_galaxy_button);
        Button sunflowerGalaxyButton = findViewById(R.id.sunflower_galaxy_button);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        Button editFlashcardButton = findViewById(R.id.edit_flashcard_button);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        Button deleteFlashcardButton = findViewById(R.id.delete_flashcard_button);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        Button totalFlashcardsButton = findViewById(R.id.total_flashcards_button);

        addFlashcardButton.setOnClickListener(view -> {
            String question = questionInput.getText().toString().trim();
            String answer = answerInput.getText().toString().trim();

            if (question.isEmpty() || answer.isEmpty()) {
                Toast.makeText(this, "Please enter both question and answer.", Toast.LENGTH_SHORT).show();
                return;
            }

            Flashcard flashcard = new Flashcard(flashcards.size() + 1, question, answer);
            flashcards.add(flashcard);
            Toast.makeText(this, "Flashcard Added!", Toast.LENGTH_SHORT).show();

            questionInput.setText("");
            answerInput.setText("");
        });

        showFlashcardsButton.setOnClickListener(view -> {
            if (flashcards.isEmpty()) {
                Toast.makeText(this, "No flashcards to show.", Toast.LENGTH_SHORT).show();
                return;
            }
            StringBuilder builder = new StringBuilder();
            for (Flashcard f : flashcards) {
                builder.append(f.getQuestion()).append(" - ").append(f.getAnswer()).append("\n");
            }
            Toast.makeText(this, builder.toString(), Toast.LENGTH_LONG).show();
        });

        toggleThemeButton.setOnClickListener(view -> {
            isLavenderGalaxyActive = false;
            if (isDarkTheme) {
                mainLayout.setBackground(getResources().getDrawable(R.drawable.beautiful_gradient, getTheme()));
            } else {
                mainLayout.setBackgroundColor(getColorCompat(R.color.black));
            }
            contentLayout.setBackgroundColor(getColorCompat(R.color.white));
            isDarkTheme = !isDarkTheme;
        });

        lavenderGalaxyButton.setOnClickListener(view -> {
            isLavenderGalaxyActive = true;
            // Set the cosmic background
            mainLayout.setBackground(getResources().getDrawable(R.drawable.lavender_galaxy_background, getTheme()));
            // Add the starfield and planets
            GalaxyStarfieldDrawable starfield = new GalaxyStarfieldDrawable();
            mainLayout.setForeground(starfield);
            // Keep content area white but slightly transparent for readability
            contentLayout.setBackgroundColor(0xF5FFFFFF);
        });

        roseGalaxyButton.setOnClickListener(view -> {
            isLavenderGalaxyActive = false;
            mainLayout.setForeground(null);
            mainLayout.setBackgroundColor(getColorCompat(R.color.rose));
            contentLayout.setBackgroundColor(getColorCompat(R.color.white));
        });

        daisyGalaxyButton.setOnClickListener(view -> {
            isLavenderGalaxyActive = false;
            mainLayout.setForeground(null);
            mainLayout.setBackgroundColor(getColorCompat(R.color.daisy_galaxy));
            contentLayout.setBackgroundColor(getColorCompat(R.color.white));
        });

        sunflowerGalaxyButton.setOnClickListener(view -> {
            isLavenderGalaxyActive = false;
            mainLayout.setForeground(null);
            mainLayout.setBackgroundColor(getColorCompat(R.color.sunflower));
            contentLayout.setBackgroundColor(getColorCompat(R.color.white));
        });

        editFlashcardButton.setOnClickListener(view -> {
            if (!flashcards.isEmpty()) {
                Flashcard flashcard = flashcards.get(0);
                flashcard.setQuestion("Edited Question");
                flashcard.setAnswer("Edited Answer");
                Toast.makeText(this, "Flashcard Edited!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "No flashcards to edit.", Toast.LENGTH_SHORT).show();
            }
        });

        deleteFlashcardButton.setOnClickListener(view -> {
            if (!flashcards.isEmpty()) {
                flashcards.remove(0);
                Toast.makeText(this, "Flashcard Deleted!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "No flashcards to delete.", Toast.LENGTH_SHORT).show();
            }
        });

        totalFlashcardsButton.setOnClickListener(view ->
                Toast.makeText(this, "Total Flashcards: " + flashcards.size(), Toast.LENGTH_SHORT).show());
    }

    private @ColorInt int getColorCompat(int colorResId) {
        return getResources().getColor(colorResId, getTheme());
    }
}
