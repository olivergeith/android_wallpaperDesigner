
package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.PointF;
import de.geithonline.wallpaperdesigner.shapes.SinusBubblePath.SINUS_BUBBLE_PATH_TYPE;
import de.geithonline.wallpaperdesigner.utils.PathHelper;
import de.geithonline.wallpaperdesigner.utils.Randomizer;

public class SinusTailPath extends Path {

    public enum SINUS_TAIL_STYLE {
        line, bubbles;
    }

    /**
     * @param center
     * @param radius
     *            the length of the Tail will be up to 4*radius! ....starting at center!
     * @param numberofTails
     * @param offset
     *            0== alllt ails begin at the same point
     */
    public SinusTailPath(final PointF center, final float radius, final float minLength, final float maxLength,
            final int numberofTails, final float offset, final SINUS_TAIL_STYLE style) {
        super();
        switch (style) {
            case bubbles:
                drawSinusBubbleTail(center, radius, minLength, maxLength, numberofTails, offset);
                break;
            default:
            case line:
                drawSinusTail(center, radius, minLength, maxLength, numberofTails, offset);
                break;

        }
    }

    public void drawSinusTail(final PointF center, final float radius, final float minLength, final float maxLength,
            final int numberofTails, final float offset) {
        final float x = center.x;
        final float y = center.y;
        for (int i = 0; i < numberofTails; i++) {
            final float length = Randomizer.getRandomFloat(minLength, maxLength);
            final int repeats = 1 + Randomizer.getRandomInt(0, 3);
            final float amplitude = radius * Randomizer.getRandomFloat(0.1f, 0.3f);
            final PointF c = new PointF();
            c.x = x + length;
            c.y = y + Randomizer.getRandomFloat(-offset, offset);
            final Path sinus = new SinusPath(c, length, repeats, amplitude);
            if (Randomizer.getRandomBoolean()) {
                PathHelper.mirrorPathUpDown(c.x, c.y, sinus);
            }
            addPath(sinus);
        }
    }

    public void drawSinusBubbleTail(final PointF center, final float radius, final float minLength,
            final float maxLength, final int numberofTails, final float offset) {
        final float x = center.x;
        final float y = center.y;
        for (int i = 0; i < numberofTails; i++) {
            final float length = Randomizer.getRandomFloat(minLength, maxLength);
            final int repeats = 1 + Randomizer.getRandomInt(0, 3);
            final float amplitude = radius * Randomizer.getRandomFloat(0.1f, 0.3f);
            final PointF c = new PointF();
            c.x = x + length;
            c.y = y + Randomizer.getRandomFloat(-offset, offset);
            final float maxBubbleRadius = radius * 0.05f;
            final Path sinus = new SinusBubblePath(c, length, repeats, amplitude, maxBubbleRadius, 80,
                    SINUS_BUBBLE_PATH_TYPE.random);
            if (Randomizer.getRandomBoolean()) {
                PathHelper.mirrorPathUpDown(c.x, c.y, sinus);
            }
            addPath(sinus);
        }
    }

}
