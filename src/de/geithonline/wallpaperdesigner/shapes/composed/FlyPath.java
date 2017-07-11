
package de.geithonline.wallpaperdesigner.shapes.composed;

import android.graphics.Path;
import android.graphics.PointF;
import de.geithonline.wallpaperdesigner.shapes.LensePath;
import de.geithonline.wallpaperdesigner.shapes.OvalPath;
import de.geithonline.wallpaperdesigner.utils.PathHelper;

public class FlyPath extends ComposedPath {

    private final float raster;

    public Path body;

    public Path wings;

    public FlyPath(final PointF center, final float radius) {
        raster = radius / 4;
        drawBody(center, radius);
        drawWings(center, radius);
    }

    private void drawBody(final PointF center, final float radius) {
        final PointF c = new PointF();
        c.x = center.x;
        c.y = center.y + 0.5f * raster;
        body = new OvalPath(c, raster, 2.5f * raster);

        c.x = center.x;
        c.y = center.y - 2f * raster;
        final Path head = new OvalPath(c, 0.75f * raster, 0.5f * raster);

        body.op(head, Op.UNION);
        addPath(body);
    }

    private void drawWings(final PointF center, final float radius) {
        final PointF c = new PointF();
        c.x = center.x - 1.3f * raster;
        c.y = center.y + raster;
        wings = new LensePath(c, 2.5f * raster);
        PathHelper.rotatePath(c, wings, -40);
        c.x = center.x + 1.3f * raster;
        c.y = center.y + raster;
        final Path fR = new LensePath(c, 2.5f * raster);
        PathHelper.rotatePath(c, fR, 40);
        wings.op(fR, Op.UNION);
        addPath(wings);
    }

}
