
package de.geithonline.wallpaperdesigner.shapes.composed;

import android.graphics.Path;
import android.graphics.PointF;
import de.geithonline.wallpaperdesigner.shapes.CirclePath;
import de.geithonline.wallpaperdesigner.shapes.SinusPath;
import de.geithonline.wallpaperdesigner.utils.PathHelper;
import de.geithonline.wallpaperdesigner.utils.Randomizer;

public class QuallePath4 extends ComposedPath {

    public QuallePath4(final PointF center, final float radius, final EQualleType type) {
        switch (type) {
            default:
            case qualle:
                drawQualle(center, radius);
                break;
            case inner_qualle:
                drawInnerQualle(center, radius);
                break;
            case tail:
                drawTail(center, radius);
                break;
            case bubbletail:
                drawBubbleTail(center, radius);
                break;
        }
    }

    private void drawQualle(final PointF center, final float radius) {
        final Path qualle = new CirclePath(center, radius);
        addPath(qualle);
        // op(new OvalPath(center, radius * 0.1f, radius * 0.5f, Direction.CCW),
        // Op.UNION);
    }

    private void drawInnerQualle(final PointF center, final float radius) {
        // addPath(new OvalPath(center, radius * 0.1f, radius * 0.5f,
        // Direction.CCW));
        //
        // // Augen
        // final PointF c = new PointF();
        // final float raster = radius / 8;
        // c.x = center.x - 3f * raster;
        // c.y = center.y - 1 * raster;
        // Path oval = new OvalPath(c, raster * 0.4f, raster, Direction.CCW);
        // PathHelper.rotatePath(c.x, c.y, oval, 15);
        // addPath(oval);
        // c.x = center.x - 3f * raster;
        // c.y = center.y + 1 * raster;
        // oval = new OvalPath(c, raster * 0.4f, raster, Direction.CCW);
        // PathHelper.rotatePath(c.x, c.y, oval, -15);
        // addPath(oval);

    }

    public void drawBubbleTail(final PointF center, final float radius) {
        for (int i = 0; i < 15; i++) {
            final int repeats = Randomizer.getRandomInt(1, 3);
            final float amplitude = radius * Randomizer.getRandomFloat(0.1f, 0.3f);
            final float length = radius * Randomizer.getRandomFloat(1.5f, 2.5f);
            final PointF c = new PointF();
            c.x = center.x + radius + length;
            c.y = center.y;
            final float maxBubbleRadius = radius * 0.1f;
            final Path s = new SinusObjectsPath(c, length, repeats, amplitude, maxBubbleRadius, 95,
                    ESinusObjectsSizingType.random, ESinusObjectsType.bubble);
            PathHelper.rotatePath(center, s, Randomizer.getRandomFloat(0, 360));
            addPath(s);
        }
    }

    public void drawTail(final PointF center, final float radius) {
        for (int i = 0; i < 50; i++) {
            final int repeats = Randomizer.getRandomInt(1, 3);
            final float amplitude = radius * Randomizer.getRandomFloat(0.1f, 0.3f);
            final float length = radius * Randomizer.getRandomFloat(0.5f, 1.7f);
            final PointF c = new PointF();
            c.x = center.x + radius + length;
            c.y = center.y;
            final Path s = new SinusPath(c, length, repeats, amplitude);
            if (Randomizer.getRandomBoolean()) {
                PathHelper.mirrorPathUpDown(c.x, c.y, s);
            }
            PathHelper.rotatePath(center, s, Randomizer.getRandomFloat(0, 360));
            addPath(s);
        }
    }

}
