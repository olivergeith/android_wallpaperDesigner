
package de.geithonline.wallpaperdesigner.shapes;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Path;
import android.graphics.PointF;

public class KochSnowFlakePath extends Path {

    // level 1 macht Mitsubishi Logo ;-)
    public KochSnowFlakePath(final PointF center, final float radius, final boolean filled, final int level) {

        final int ecken = 3;
        final float angle = (float) (Math.PI / ecken) * 2;
        final List<PointF> points = new ArrayList<>();

        for (int i = 0; i < ecken; i++) {
            final float r = radius;
            final PointF p = new PointF();
            p.x = (float) (center.x + Math.cos(i * angle) * r);
            p.y = (float) (center.y + Math.sin(i * angle) * r);
            points.add(p);
        }

        final PointF p1 = points.get(0);
        final PointF p2 = points.get(1);
        final PointF p3 = points.get(2);
        moveTo(p1.x, p1.y);
        drawSnow(level, p1.x, p1.y, p2.x, p2.y);
        drawSnow(level, p2.x, p2.y, p3.x, p3.y);
        drawSnow(level, p3.x, p3.y, p1.x, p1.y);
        close();

    }

    private void drawSnow(final int lev, final float x1, final float y1, final float x5, final float y5) {
        float deltaX, deltaY, x2, y2, x3, y3, x4, y4;

        if (lev == 0) {
            lineTo(x5, y5);
        } else {
            deltaX = x5 - x1;
            deltaY = y5 - y1;

            x2 = x1 + deltaX / 3;
            y2 = y1 + deltaY / 3;

            x3 = (float) (0.5f * (x1 + x5) - Math.sqrt(3) * (y1 - y5) / 6f);
            y3 = (float) (0.5f * (y1 + y5) - Math.sqrt(3) * (x5 - x1) / 6f);

            x4 = x1 + 2 * deltaX / 3;
            y4 = y1 + 2 * deltaY / 3;

            drawSnow(lev - 1, x1, y1, x2, y2);
            drawSnow(lev - 1, x2, y2, x3, y3);
            drawSnow(lev - 1, x3, y3, x4, y4);
            drawSnow(lev - 1, x4, y4, x5, y5);
        }
    }

}
