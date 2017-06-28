
package de.geithonline.wallpaperdesigner.shapes.composed;

import android.graphics.Path;
import android.graphics.PointF;
import de.geithonline.wallpaperdesigner.shapes.SinusPath;
import de.geithonline.wallpaperdesigner.utils.PathHelper;
import de.geithonline.wallpaperdesigner.utils.Randomizer;

public class MultiSinusLinesPath extends ComposedPath {

    /**
     * @param center
     * @param radius
     *            the length of the Tail will be up to 4*radius! ....starting at center!
     * @param numberofTails
     * @param offset
     *            0== alllt ails begin at the same point
     */
    public MultiSinusLinesPath(final PointF center, final float radius, final float minLength, final float maxLength,
            final int numberofTails, final float offset) {
        super();
        drawSinusTail(center, radius, minLength, maxLength, numberofTails, offset);
    }

    private void drawSinusTail(final PointF center, final float radius, final float minLength, final float maxLength,
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

}
