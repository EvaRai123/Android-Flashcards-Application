package com.example.myapplication;

import android.annotation.SuppressLint;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ConstraintLayout mainLayout;
    private View galaxyStarsOverlay;
    private final List<Flashcard> flashcards = new ArrayList<>();
    private boolean isDarkTheme = false;

    private EditText questionInput;
    private EditText answerInput;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainLayout = findViewById(R.id.main_layout);
        galaxyStarsOverlay = findViewById(R.id.galaxy_stars_overlay);

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
            hideGalaxyOverlay();
            if (isDarkTheme) {
                mainLayout.setBackgroundResource(R.drawable.beautiful_gradient);
            } else {
                mainLayout.setBackgroundColor(getColorCompat(R.color.black));
            }
            isDarkTheme = !isDarkTheme;
        });

        lavenderGalaxyButton.setOnClickListener(view -> {
            mainLayout.setBackgroundResource(R.drawable.lavender_galaxy_background);
            showGalaxyOverlay();
        });
        roseGalaxyButton.setOnClickListener(view -> {
            hideGalaxyOverlay();
            mainLayout.setBackgroundColor(getColorCompat(R.color.rose));
        });
        daisyGalaxyButton.setOnClickListener(view -> {
            hideGalaxyOverlay();
            mainLayout.setBackgroundColor(getColorCompat(R.color.daisy_galaxy));
        });
        sunflowerGalaxyButton.setOnClickListener(view -> {
            hideGalaxyOverlay();
            mainLayout.setBackgroundColor(getColorCompat(R.color.sunflower));
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

    private void showGalaxyOverlay() {
        galaxyStarsOverlay.setVisibility(View.VISIBLE);
        if (galaxyStarsOverlay.getBackground() instanceof AnimationDrawable) {
            AnimationDrawable stars = (AnimationDrawable) galaxyStarsOverlay.getBackground();
            stars.stop();
            stars.start();
        }
    }

    private void hideGalaxyOverlay() {
        galaxyStarsOverlay.setVisibility(View.GONE);
        if (galaxyStarsOverlay.getBackground() instanceof AnimationDrawable) {
            ((AnimationDrawable) galaxyStarsOverlay.getBackground()).stop();
        }
    }

    private @ColorInt int getColorCompat(int colorResId) {
        return getResources().getColor(colorResId, getTheme());
    }
}
