
package de.geithonline.wallpaperdesigner.shapes.composed;

import android.graphics.Path;
import android.graphics.PointF;
import de.geithonline.wallpaperdesigner.shapes.CirclePath;
import de.geithonline.wallpaperdesigner.shapes.LinePath;
import de.geithonline.wallpaperdesigner.shapes.LinePath.LINE_STYLE;
import de.geithonline.wallpaperdesigner.utils.PathHelper;
import de.geithonline.wallpaperdesigner.utils.Randomizer;

public class QuallePath5 extends ComposedPath {

    public QuallePath5(final PointF center, final float radius, final int arms, final EQualleType type) {
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
                drawBubbleTail(center, radius, arms);
                break;
        }
    }

    private void drawQualle(final PointF center, final float radius) {
        final Path qualle = new CirclePath(center, radius);
        addPath(qualle);
    }

    private void drawInnerQualle(final PointF center, final float radius) {
        final PointF c1 = new PointF();
        final float raster = radius / 4;
        final float r1 = raster * 0.75f;
        Path p1;
        c1.x = center.x - raster;
        c1.y = center.y - raster;
        p1 = new CirclePath(c1, r1);
        addPath(p1);
        c1.x = center.x + raster;
        c1.y = center.y - raster;
        p1 = new CirclePath(c1, r1);
        addPath(p1);
        c1.x = center.x - raster;
        c1.y = center.y + raster;
        p1 = new CirclePath(c1, r1);
        addPath(p1);
        c1.x = center.x + raster;
        c1.y = center.y + raster;
        p1 = new CirclePath(c1, r1);
        addPath(p1);
    }

    private void drawBubbleTail(final PointF center, final float radius, final int arms) {
        for (int i = 0; i < 11 + arms; i++) {
            final int repeats = Randomizer.getRandomInt(1, 4);
            final float amplitude = radius * Randomizer.getRandomFloat(0.1f, 0.4f);
            final float length = radius * Randomizer.getRandomFloat(2.0f, 3.5f);
            final PointF c = new PointF();
            c.x = center.x + radius + length;
            c.y = center.y;
            final float maxBubbleRadius = radius * 0.1f;
            final Path s = new SinusObjectsPath(c, length, repeats, amplitude, maxBubbleRadius, 85,
                    ESinusObjectsSizingType.decreasing, ESinusObjectsType.bubble);
            if (Randomizer.getRandomBoolean()) {
                PathHelper.mirrorPathUpDown(c.x, c.y, s);
            }
            PathHelper.rotatePath(center, s, Randomizer.getRandomFloat(0, 360));
            addPath(s);
        }
    }

    private void drawTail(final PointF center, final float radius) {
        final int anz = 30;
        for (int i = 0; i < anz; i++) {
            final float length = radius * Randomizer.getRandomFloat(3f, 8f);
            final PointF c = new PointF();
            c.x = center.x + radius * 0.5f + length;
            c.y = center.y;
            final Path s = new LinePath(c, length, LINE_STYLE.straight, true);
            PathHelper.rotatePath(center, s, Randomizer.getRandomFloat(0, 360));
            addPath(s);
        }
    }

}
