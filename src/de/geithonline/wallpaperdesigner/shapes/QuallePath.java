
package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.PointF;
import de.geithonline.wallpaperdesigner.shapes.CirclePath.CIRCLE_STYLE;
import de.geithonline.wallpaperdesigner.shapes.SinusTailPath.SINUS_TAIL_STYLE;
import de.geithonline.wallpaperdesigner.utils.PathHelper;
import de.geithonline.wallpaperdesigner.utils.Randomizer;

public class QuallePath extends Path {

    public QuallePath(final PointF center, final float radius, final QualleType type) {
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
        final Path qualle = new CirclePath(center, radius * 0.5f, 0, true, CIRCLE_STYLE.HALF_CIRCLE);
        PathHelper.rotatePath(center.x, center.y, qualle, -90);
        addPath(qualle);

        // Augen
        // final float raster = radius / 8;
        // c.x = center.x - 7 * raster;
        // c.y = center.y - 1 * raster;
        // addPath(new OvalPath(c, raster / 2, raster, Direction.CCW));
    }

    private void drawInnerQualle(final PointF center, final float radius) {
        final Path qualle = new CirclePath(center, radius * 0.3f, 0, true, CIRCLE_STYLE.HALF_CIRCLE);
        PathHelper.rotatePath(center.x, center.y, qualle, -90);
        addPath(qualle);
    }

    public void drawBubbleTail(final PointF center, final float radius) {
        addPath(new SinusTailPath(center, radius, radius * 1.3f, radius * 2f, 2, 0, SINUS_TAIL_STYLE.bubbles));
    }

    public void drawTail(final PointF center, final float radius) {
        addPath(new SinusTailPath(center, radius, radius * 0.9f, radius * 2f, 4 + Randomizer.getRandomInt(0, 3),
                radius * 0.2f, SINUS_TAIL_STYLE.line));
    }

}
