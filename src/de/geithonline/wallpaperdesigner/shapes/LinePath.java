
package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.PointF;
import de.geithonline.wallpaperdesigner.utils.Randomizer;

public class LinePath extends Path {

    public enum LINE_STYLE {
        straight, sinus, zigzag, bow;
    }

    public LinePath(final PointF center, final float radius, final LINE_STYLE style, final boolean filled) {
        super();
        final float l = center.x - radius;
        final float r = center.x + radius;
        final float mitteY = center.y;
        switch (style) {
            default:
            case straight:
                moveTo(l, center.y);
                lineTo(r, center.y);
                break;
            case sinus: {
                // nach links
                final float sinradius = radius * 0.1f;
                final int sinRepeats = 7;
                moveTo(l, mitteY);
                // und nun der Sinus
                final int points = 30;
                for (int i = 1; i <= points; i++) {
                    final float x = l + i * (r - l) / points;
                    final float angle = (float) ((float) i / points * Math.PI * sinRepeats);
                    final float y = mitteY + (float) (sinradius * Math.sin(angle));
                    lineTo(x, y);
                }
            }
                break;
            case zigzag: {
                // nach links
                final float sinradius = radius * 0.1f;
                final int sinRepeats = 7;
                moveTo(l, mitteY);
                // und nun der Sinus
                final int points = 2 * sinRepeats;
                for (int i = 1; i <= points; i++) {
                    final float x = l + i * (r - l) / points;
                    final float angle = (float) ((float) i / points * Math.PI * sinRepeats);
                    final float y = mitteY + (float) (sinradius * Math.sin(angle));
                    lineTo(x, y);
                }
            }
                break;
            case bow: {
                // nach links
                float sinradius = radius * 0.4f;
                if (filled) {
                    sinradius = Randomizer.getRandomFloat(0, radius * 0.4f);
                } else {
                    sinradius = Randomizer.getRandomFloat(-radius * 0.4f, 0);
                }
                final int sinRepeats = 1;
                moveTo(l, mitteY);
                // und nun der Sinus
                final int points = 30;
                for (int i = 1; i <= points; i++) {
                    final float x = l + i * (r - l) / points;
                    final float angle = (float) ((float) i / points * Math.PI * sinRepeats);
                    final float y = mitteY + (float) (sinradius * Math.sin(angle));
                    lineTo(x, y);
                }
            }
                break;
        }

    }
}
