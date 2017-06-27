
package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.PointF;
import de.geithonline.wallpaperdesigner.shapes.CirclePath.CIRCLE_STYLE;
import de.geithonline.wallpaperdesigner.utils.Randomizer;

public class SinusBubblePath extends Path {

    enum SINUS_BUBBLE_PATH_TYPE {
        random, decreasing;
    }

    /**
     * @param center
     * @param radius
     * @param sinRepeats
     *            1 == one bow/Halbwelle, 2 == a complete sinus
     * @param amplitude
     * @param maxBubbleRadius
     * @param prozentToDraw
     *            (0-100 allowed)
     */
    public SinusBubblePath(final PointF center, final float radius, final int sinRepeats, final float amplitude,
            final float maxBubbleRadius, final int prozentToDraw, final SINUS_BUBBLE_PATH_TYPE type) {
        super();
        switch (type) {
            case decreasing:
                drawBubbleSinusGettingSmaller(center, radius, sinRepeats, amplitude, maxBubbleRadius, prozentToDraw);
                break;
            default:
            case random:
                drawBubbleSinus(center, radius, sinRepeats, amplitude, maxBubbleRadius, prozentToDraw);
                break;

        }
    }

    private void drawBubbleSinus(final PointF center, final float radius, final int sinRepeats, final float amplitude,
            final float maxBubbleRadius, final int prozentToDraw) {
        // nach links
        final float l = center.x - radius;
        final float r = center.x + radius;
        final float mitteY = center.y;
        // und nun der Sinus
        final int points = (int) (2 * radius / (maxBubbleRadius * 1.6f));
        for (int i = 1; i < points; i++) {
            // nicht immer einen bubble zeichnen...
            if (Randomizer.getRandomInt(0, 100) <= prozentToDraw) {
                final float bubbleRadius = Randomizer.getRandomFloat(0, maxBubbleRadius);
                final float x = l + i * (r - l) / points;
                final float angle = (float) ((float) i / points * Math.PI * sinRepeats);
                final float y = mitteY + (float) (amplitude * Math.sin(angle));
                final PointF c = new PointF(x, y);
                addPath(new CirclePath(c, bubbleRadius, 0, true, CIRCLE_STYLE.CIRCLE));
            }
        }
    }

    private void drawBubbleSinusGettingSmaller(final PointF center, final float radius, final int sinRepeats,
            final float amplitude, final float maxBubbleRadius, final int prozentToDraw) {
        // nach links
        final float l = center.x - radius;
        final float r = center.x + radius;
        final float mitteY = center.y;
        // und nun der Sinus
        final int anzahlBubbles = (int) (2 * radius / (maxBubbleRadius * 1.6f));
        for (int i = 1; i < anzahlBubbles; i++) {
            // nicht immer einen bubble zeichnen...
            if (Randomizer.getRandomInt(0, 100) <= prozentToDraw) {
                final float bubbleRadius = (anzahlBubbles - (float) i) * (maxBubbleRadius / anzahlBubbles); // Randomizer.getRandomFloat(0,
                // maxBubbleRadius);
                final float x = l + i * (r - l) / anzahlBubbles;
                final float angle = (float) ((float) i / anzahlBubbles * Math.PI * sinRepeats);
                final float y = mitteY + (float) (amplitude * Math.sin(angle));
                final PointF c = new PointF(x, y);
                addPath(new CirclePath(c, bubbleRadius, 0, true, CIRCLE_STYLE.CIRCLE));
            }
        }
    }

}
