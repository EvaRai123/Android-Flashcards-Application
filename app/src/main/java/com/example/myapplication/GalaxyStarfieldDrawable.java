package com.example.myapplication;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;

import java.util.Random;

/**
 * Custom Drawable: Renders a cute starfield and planets for the Lavender Galaxy theme.
 * Stars, sparkles, and small planets are drawn procedurally across the canvas.
 * This layer sits behind the ScrollView content, making it purely decorative and non-interactive.
 */
public class GalaxyStarfieldDrawable extends Drawable {

    private final Paint starPaint;
    private final Paint planetPaint;
    private final Paint sparkPaint;
    private final Random random;
    private float[] starX, starY, starRadius, starAlpha;
    private float[] planetX, planetY, planetRadius;
    private int[] planetColors;
    private float[] sparkX, sparkY;
    private static final int STAR_COUNT = 100;
    private static final int PLANET_COUNT = 5;
    private static final int SPARKLE_COUNT = 12;

    public GalaxyStarfieldDrawable() {
        this.starPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        this.starPaint.setColor(Color.parseColor("#FFF9FF"));
        this.planetPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        this.sparkPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        this.random = new Random(42); // Fixed seed for consistent starfield
    }

    @Override
    protected void onBoundsChange(android.graphics.Rect bounds) {
        super.onBoundsChange(bounds);
        generateStarfield(bounds.width(), bounds.height());
    }

    private void generateStarfield(int width, int height) {
        if (width <= 0 || height <= 0) return;

        // Generate stars
        starX = new float[STAR_COUNT];
        starY = new float[STAR_COUNT];
        starRadius = new float[STAR_COUNT];
        starAlpha = new float[STAR_COUNT];

        for (int i = 0; i < STAR_COUNT; i++) {
            starX[i] = random.nextFloat() * width;
            starY[i] = random.nextFloat() * height;
            starRadius[i] = 0.5f + random.nextFloat() * 1.5f; // 0.5-2dp
            starAlpha[i] = 0.3f + random.nextFloat() * 0.7f; // 30-100% opacity
        }

        // Generate cute planets
        planetX = new float[PLANET_COUNT];
        planetY = new float[PLANET_COUNT];
        planetRadius = new float[PLANET_COUNT];
        planetColors = new int[PLANET_COUNT];

        String[] planetColorHex = {"#C5A9F0", "#F2B8D8", "#9BA8F5", "#E8DDFB", "#B39DDB"};
        int[] planetColorCodes = {0xFFC5A9F0, 0xFFF2B8D8, 0xFF9BA8F5, 0xFFE8DDFB, 0xFFB39DDB};

        // Position planets at edges and corners
        float[] positions = {
            width * 0.15f, height * 0.10f,  // Top-left
            width * 0.85f, height * 0.20f,  // Top-right
            width * 0.10f, height * 0.75f,  // Bottom-left
            width * 0.80f, height * 0.70f,  // Bottom-right
            width * 0.50f, height * 0.85f   // Bottom-center
        };
        float[] sizes = {35f, 25f, 20f, 28f, 32f}; // Varied sizes

        for (int i = 0; i < PLANET_COUNT; i++) {
            planetX[i] = positions[i * 2];
            planetY[i] = positions[i * 2 + 1];
            planetRadius[i] = sizes[i];
            planetColors[i] = planetColorCodes[i];
        }

        // Generate sparkles
        sparkX = new float[SPARKLE_COUNT];
        sparkY = new float[SPARKLE_COUNT];

        for (int i = 0; i < SPARKLE_COUNT; i++) {
            sparkX[i] = random.nextFloat() * width;
            sparkY[i] = random.nextFloat() * height;
        }
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        if (starX == null || planetX == null) return;

        // Draw cute planets
        for (int i = 0; i < PLANET_COUNT; i++) {
            planetPaint.setColor(planetColors[i]);
            planetPaint.setAlpha(200);
            canvas.drawCircle(planetX[i], planetY[i], planetRadius[i], planetPaint);

            // Add subtle highlight on planets for depth
            planetPaint.setColor(Color.parseColor("#FFFFFFFF"));
            planetPaint.setAlpha(80);
            float highlightRadius = planetRadius[i] * 0.3f;
            canvas.drawCircle(
                planetX[i] - planetRadius[i] * 0.25f,
                planetY[i] - planetRadius[i] * 0.25f,
                highlightRadius,
                planetPaint
            );
        }

        // Draw stars
        for (int i = 0; i < STAR_COUNT; i++) {
            starPaint.setAlpha((int) (255 * starAlpha[i]));
            canvas.drawCircle(starX[i], starY[i], starRadius[i], starPaint);
        }

        // Draw four-point sparkles
        sparkPaint.setColor(Color.parseColor("#FFF9FF"));
        sparkPaint.setAlpha(180);
        sparkPaint.setStrokeWidth(0.8f);
        sparkPaint.setStyle(Paint.Style.STROKE);

        for (int i = 0; i < SPARKLE_COUNT; i++) {
            float cx = sparkX[i];
            float cy = sparkY[i];
            float size = 2f;
            // Draw four-point star
            canvas.drawLine(cx, cy - size, cx, cy + size, sparkPaint);
            canvas.drawLine(cx - size, cy, cx + size, cy, sparkPaint);
        }
    }

    @Override
    public void setAlpha(int alpha) {
        starPaint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(android.graphics.ColorFilter colorFilter) {
        starPaint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return android.graphics.PixelFormat.TRANSLUCENT;
    }
}
