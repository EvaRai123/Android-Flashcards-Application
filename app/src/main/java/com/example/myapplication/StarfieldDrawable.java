package com.example.myapplication;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;

import java.util.Random;

/**
 * A lightweight custom drawable that renders a starfield with scattered stars.
 * Stars are pseudo-randomly positioned based on the drawable's bounds.
 * This approach avoids animation overhead and ensures stars appear behind all UI elements.
 */
public class StarfieldDrawable extends Drawable {

    private final Paint starPaint;
    private final Random random;
    private float[] starX;
    private float[] starY;
    private float[] starRadius;
    private static final int STAR_COUNT = 120;

    public StarfieldDrawable() {
        this.starPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        this.starPaint.setColor(Color.parseColor("#FFF4C7"));
        this.random = new Random(42); // Fixed seed for consistent starfield
    }

    @Override
    protected void onBoundsChange(android.graphics.Rect bounds) {
        super.onBoundsChange(bounds);
        generateStars(bounds.width(), bounds.height());
    }

    private void generateStars(int width, int height) {
        if (width <= 0 || height <= 0) return;

        starX = new float[STAR_COUNT];
        starY = new float[STAR_COUNT];
        starRadius = new float[STAR_COUNT];

        for (int i = 0; i < STAR_COUNT; i++) {
            starX[i] = random.nextFloat() * width;
            starY[i] = random.nextFloat() * height;
            // Vary star sizes: 0.5dp to 2.5dp
            starRadius[i] = 0.5f + random.nextFloat() * 2.0f;
        }
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        if (starX == null || starY == null || starRadius == null) {
            return;
        }

        for (int i = 0; i < STAR_COUNT; i++) {
            // Vary opacity slightly for subtle depth effect
            int alpha = 150 + random.nextInt(105); // 150-255
            starPaint.setAlpha(alpha);
            canvas.drawCircle(starX[i], starY[i], starRadius[i], starPaint);
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
