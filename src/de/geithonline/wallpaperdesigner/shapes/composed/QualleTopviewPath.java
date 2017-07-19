
package de.geithonline.wallpaperdesigner.shapes.composed;

import android.graphics.Path;
import android.graphics.PointF;
import de.geithonline.wallpaperdesigner.settings.TailOptions;
import de.geithonline.wallpaperdesigner.settings.TailOptionsBubbles;
import de.geithonline.wallpaperdesigner.settings.TailOptionsLine;
import de.geithonline.wallpaperdesigner.shapes.CirclePath;
import de.geithonline.wallpaperdesigner.shapes.SinusPath;
import de.geithonline.wallpaperdesigner.utils.PathHelper;
import de.geithonline.wallpaperdesigner.utils.Randomizer;

public class QualleTopviewPath extends ComposedPath {

    public Path qualle;
    public Path inner;
    public ComposedPath tail;
    public ComposedPath bubbletail;

    public QualleTopviewPath(final PointF center, final float radius, final TailOptionsLine tailOptions, final TailOptionsBubbles bubbleOptions) {
        drawQualle(center, radius);
        drawInnerQualle(center, radius);
        drawTail(center, radius, tailOptions);
        drawBubbleTail(center, radius, bubbleOptions);
    }

    protected void drawQualle(final PointF center, final float radius) {
        qualle = new CirclePath(center, radius);
        addPath(qualle);
    }

    protected void drawInnerQualle(final PointF center, final float radius) {
        inner = new Path();
        final PointF c1 = new PointF();
        final PointF c2 = new PointF();
        final float raster = radius / 4;
        final float r1 = raster * 0.75f;
        final float r2 = raster * 0.55f;
        final float offsetInnen = 0.85f;
        Path p1;
        Path p2;
        c1.x = center.x - raster;
        c1.y = center.y - raster;
        c2.x = center.x - raster * offsetInnen;
        c2.y = center.y - raster * offsetInnen;
        p1 = new CirclePath(c1, r1);
        p2 = new CirclePath(c2, r2);
        p1.op(p2, Op.DIFFERENCE);
        inner.addPath(p1);
        c1.x = center.x + raster;
        c1.y = center.y - raster;
        c2.x = center.x + raster * offsetInnen;
        c2.y = center.y - raster * offsetInnen;
        p1 = new CirclePath(c1, r1);
        p2 = new CirclePath(c2, r2);
        p1.op(p2, Op.DIFFERENCE);
        inner.addPath(p1);
        c1.x = center.x - raster;
        c1.y = center.y + raster;
        c2.x = center.x - raster * offsetInnen;
        c2.y = center.y + raster * offsetInnen;
        p1 = new CirclePath(c1, r1);
        p2 = new CirclePath(c2, r2);
        p1.op(p2, Op.DIFFERENCE);
        inner.addPath(p1);
        c1.x = center.x + raster;
        c1.y = center.y + raster;
        c2.x = center.x + raster * offsetInnen;
        c2.y = center.y + raster * offsetInnen;
        p1 = new CirclePath(c1, r1);
        p2 = new CirclePath(c2, r2);
        p1.op(p2, Op.DIFFERENCE);
        inner.addPath(p1);
        addPath(inner);
    }

    protected void drawBubbleTail(final PointF center, final float radius, final TailOptionsBubbles tailOptions) {
        bubbletail = new ComposedPath();
        final Boolean flip = Randomizer.getRandomBoolean();
        for (int i = 0; i < tailOptions.anzTails; i++) {
            final int repeats = Randomizer.getRandomInt(tailOptions.minSinusRepeats, tailOptions.maxSinusRepeats);
            final float amplitude = radius * Randomizer.getRandomFloat(tailOptions.minAmplitude, tailOptions.maxAmplitude);
            final float length = radius * Randomizer.getRandomFloat(tailOptions.minLength, tailOptions.maxLength);
            final PointF c = new PointF();
            c.x = center.x + radius + length;
            c.y = center.y;
            final float maxBubbleRadius = radius * tailOptions.bubbleRadius;
            final Path s = new SinusObjectsPath(c, length, repeats, amplitude, maxBubbleRadius, //
                    tailOptions.percentOfBubblesToDraw, //
                    tailOptions.sinusObjectsSizingType, //
                    tailOptions.sinusObjectType, tailOptions.sinusAmplitudeType);
            if (getFlipBoolean(flip, tailOptions)) {
                PathHelper.mirrorPathUpDown(c.x, c.y, s);
            }
            switch (tailOptions.tailRotationType) {
                default:
                case Even:
                    PathHelper.rotatePath(center, s, i * 360 / tailOptions.anzTails);
                    break;
                case Random:
                    PathHelper.rotatePath(center, s, Randomizer.getRandomFloat(0, 360));
                    break;

            }
            bubbletail.addPath(s);
        }
        addPath(bubbletail);
    }

    protected void drawTail(final PointF center, final float radius, final TailOptionsLine tailOptions) {
        tail = new ComposedPath();
        drawSinusTail(center, radius, tailOptions);
        addPath(tail);
    }

    private void drawSinusTail(final PointF center, final float radius, final TailOptionsLine tailOptions) {
        final Boolean flip = Randomizer.getRandomBoolean();
        for (int i = 0; i < tailOptions.anzTails; i++) {
            final int repeats = Randomizer.getRandomInt(tailOptions.minSinusRepeats, tailOptions.maxSinusRepeats);
            final float amplitude = radius * Randomizer.getRandomFloat(tailOptions.minAmplitude, tailOptions.maxAmplitude);
            final float length = radius * Randomizer.getRandomFloat(tailOptions.minLength, tailOptions.maxLength);
            final PointF c = new PointF();
            c.x = center.x + radius + length;
            c.y = center.y;
            final Path s = new SinusPath(c, length, repeats, amplitude, tailOptions.sinusAmplitudeType, !tailOptions.outline);
            if (getFlipBoolean(flip, tailOptions)) {
                PathHelper.mirrorPathUpDown(c.x, c.y, s);
            }
            switch (tailOptions.tailRotationType) {
                default:
                case Even:
                    PathHelper.rotatePath(center, s, i * 360 / tailOptions.anzTails);
                    break;
                case Random:
                    PathHelper.rotatePath(center, s, Randomizer.getRandomFloat(0, 360));
                    break;

            }
            tail.addPath(s);
        }
    }

    private boolean getFlipBoolean(final Boolean flip, final TailOptions tailOptions) {
        if (tailOptions.randomFlip) {
            return Randomizer.getRandomBoolean();
        }
        return flip;
    }

}
