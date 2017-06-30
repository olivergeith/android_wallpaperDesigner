
package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;

public class SimpleTrianglePath extends Path {

    public SimpleTrianglePath(final PointF center, final float radiusW, final float radiusH) {
        draw(center, radiusW, radiusH);
    }

    private void draw(final PointF center, final float radiusW, final float radiusH) {
        final RectF oval = new RectF();
        oval.left = center.x - radiusW;
        oval.right = center.x + radiusW;
        oval.top = center.y - radiusH;
        oval.bottom = center.y + radiusH;

        moveTo(oval.left, oval.bottom);
        lineTo(center.x, oval.top);
        lineTo(oval.right, oval.bottom);
        close();
    }

}
