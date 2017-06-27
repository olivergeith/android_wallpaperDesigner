
package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.PointF;
import de.geithonline.wallpaperdesigner.shapes.SinusTailPath.SINUS_TAIL_STYLE;
import de.geithonline.wallpaperdesigner.utils.Randomizer;

public class QuallePath2 extends Path {

    public QuallePath2(final PointF center, final float radius, final QualleType type) {
        switch (type) {
            default:
            case qualle:
                drawQualle(center, radius);
                break;
            case inner_qualle:
                drawInnerQualle(center, radius);
                break;
            case bubbletail:
                drawBubbleTail(center, radius);
                break;
            case tail:
                drawTail(center, radius);
                break;
        }
    }

    private void drawQualle(final PointF center, final float radius) {
        final float x = center.x;
        final float y = center.y;
        final PointF c = new PointF(x - radius * 0.25f, y);
        addPath(new OvalPath(c, radius * 0.25f, radius * 0.5f, Direction.CCW));
    }

    private void drawInnerQualle(final PointF center, final float radius) {
        final float x = center.x;
        final float y = center.y;
        final PointF c = new PointF(x - radius * 0.125f, y);
        addPath(new OvalPath(c, radius * 0.125f, radius * 0.33f, Direction.CCW));

    }

    public void drawTail(final PointF center, final float radius) {
        final PointF c = new PointF();
        // start of tail is radius/2 to the left
        c.x = center.x - radius * 0.125f;
        c.y = center.y;
        addPath(new SinusTailPath(c, radius, radius * 1.0f, radius * 2f, 4 + Randomizer.getRandomInt(0, 3),
                radius * 0.2f, SINUS_TAIL_STYLE.line));
    }

    public void drawBubbleTail(final PointF center, final float radius) {
        final PointF c = new PointF();
        // start of tail is radius/2 to the left
        c.x = center.x - radius * 0.125f;
        c.y = center.y;
        addPath(new SinusTailPath(c, radius, radius * 1.3f, radius * 2f, 1, 0, SINUS_TAIL_STYLE.bubbles));
    }

}