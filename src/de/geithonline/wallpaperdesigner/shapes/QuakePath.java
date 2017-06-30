
package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.PointF;
import de.geithonline.wallpaperdesigner.shapes.CirclePath.CIRCLE_STYLE;

public class QuakePath extends Path {

    public QuakePath(final PointF center, final float radius) {
        super();
        drawQuake(center, radius);
    }

    private void drawQuake(final PointF center, final float radius) {
        final float raster = radius / 3;
        final PointF c = new PointF();
        c.x = center.x;
        c.y = center.y - raster;
        float circleRadius = 2 * raster;
        Path path = new CirclePath(c, circleRadius, 0, true, CIRCLE_STYLE.CIRCLE);
        addPath(path);

        c.x = center.x;
        c.y = center.y - 1.25f * raster;
        circleRadius = 1.8f * raster;
        path = new CirclePath(c, circleRadius, 0, true, CIRCLE_STYLE.CIRCLE);
        op(path, Op.DIFFERENCE);

        c.x = center.x;
        c.y = center.y + 1.0f * raster;
        path = new SimpleRectanglePath(c, 0.2f * raster, 1.5f * raster, Direction.CW);
        op(path, Op.UNION);
    }

}
