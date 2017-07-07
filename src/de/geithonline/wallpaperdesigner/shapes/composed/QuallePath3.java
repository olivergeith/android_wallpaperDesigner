
package de.geithonline.wallpaperdesigner.shapes.composed;

import android.graphics.Path;
import android.graphics.PointF;
import de.geithonline.wallpaperdesigner.shapes.CirclePath;
import de.geithonline.wallpaperdesigner.shapes.CirclePath.CIRCLE_STYLE;
import de.geithonline.wallpaperdesigner.shapes.OvalPath;
import de.geithonline.wallpaperdesigner.utils.PathHelper;
import de.geithonline.wallpaperdesigner.utils.Randomizer;

public class QuallePath3 extends ComposedPath {

    public QuallePath3(final PointF center, final float radius, final EQualleType type) {
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
        final Path qualle = new CirclePath(center, radius, 0, true, CIRCLE_STYLE.HALF_CIRCLE);
        PathHelper.rotatePath(center.x, center.y, qualle, -90);
        addPath(qualle);
        op(new OvalPath(center, radius * 0.2f, radius, Direction.CCW), Op.UNION);
    }

    private void drawInnerQualle2(final PointF center, final float radius) {
        addPath(new OvalPath(center, radius * 0.2f, radius, Direction.CCW));

        // Augen
        final PointF c = new PointF();
        final float raster = radius / 8;
        c.x = center.x - 3f * raster;
        c.y = center.y - 1 * raster;
        Path oval = new OvalPath(c, raster * 0.4f, raster, Direction.CCW);
        PathHelper.rotatePath(c.x, c.y, oval, 15);
        addPath(oval);
        c.x = center.x - 3f * raster;
        c.y = center.y + 1 * raster;
        oval = new OvalPath(c, raster * 0.4f, raster, Direction.CCW);
        PathHelper.rotatePath(c.x, c.y, oval, -15);
        addPath(oval);

    }

    private void drawInnerQualle(final PointF center, final float radius) {
        addPath(new OvalPath(center, radius * 0.2f, radius, Direction.CCW));
        final PointF c1 = new PointF();
        final PointF c2 = new PointF();
        final float raster = radius / 3;
        final float r1 = raster * 0.75f;
        final float r2 = raster * 0.55f;
        Path p1;
        Path p2;
        c1.x = center.x - 2 * raster;
        c1.y = center.y - raster;
        c2.x = center.x - raster * 2.2f;
        c2.y = center.y - raster * 0.8f;
        p1 = new CirclePath(c1, r1);
        p2 = new CirclePath(c2, r2);
        p1.op(p2, Op.DIFFERENCE);
        addPath(p1);
        c1.x = center.x - 2 * raster;
        c1.y = center.y + raster;
        c2.x = center.x - raster * 2.2f;
        c2.y = center.y + raster * 0.8f;
        p1 = new CirclePath(c1, r1);
        p2 = new CirclePath(c2, r2);
        p1.op(p2, Op.DIFFERENCE);
        addPath(p1);
    }

    public void drawBubbleTail(final PointF center, final float radius) {
        addPath(new MultiSinusObjectsPath(center, radius, radius * 2.5f, radius * 4f, 1, 0, ESinusObjectsType.bubble));
    }

    public void drawTail(final PointF center, final float radius) {
        addPath(new MultiSinusLinesPath(center, radius, radius * 2.0f, radius * 4f, 4 + Randomizer.getRandomInt(1, 3),
                radius * 0.2f));
    }

}
