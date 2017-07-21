
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
    public Path inner = new Path();
    public ComposedPath tail = new ComposedPath();
    public ComposedPath bubbletail = new ComposedPath();

    public QualleTopviewPath(final PointF center, final float radius, final TailOptionsLine tailOptions, final TailOptionsBubbles bubbleOptions) {
        drawQualle(center, radius);
        drawInnerQualleV2(center, radius);
        drawTail(center, radius, tailOptions);
        drawBubbleTail(center, radius, bubbleOptions);
    }

    protected void drawQualle(final PointF center, final float radius) {
        qualle = new CirclePath(center, radius);
        addPath(qualle);
    }

    protected void drawInnerQualle(final PointF center, final float radius) {
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
        // if (false) {
        // drawInnerLines(center, radius);
        // }
        addPath(inner);
    }

    private void drawInnerLines(final PointF center, final float radius) {
        final boolean flip = Randomizer.getRandomBoolean();
        final int anz = 20;
        for (int i = 0; i < anz; i++) {
            final int repeats = 1;
            final float amplitude = radius * 0.05f;
            final float length = radius * 0.25f;
            final PointF c = new PointF();
            c.x = center.x + radius * 0.5f + length;
            c.y = center.y;
            final Path s = new SinusPath(c, length, repeats, amplitude);
            if (flip) {
                PathHelper.mirrorPathUpDown(c.x, c.y, s);
            }
            PathHelper.rotatePath(center, s, i * 360 / anz);
            inner.addPath(s);
        }
    }

    private void drawInnerQualleV2(final PointF center, final float radius) {
        final int anz = Randomizer.getRandomInt(4, 5);
        for (int i = 0; i < anz; i++) {
            final PointF c1 = new PointF();
            final PointF c2 = new PointF();
            final float raster = radius / 3;
            final float r1 = raster * 0.6f;
            final float r2 = raster * 0.45f;
            Path p1;
            Path p2;
            c1.x = center.x + raster;
            c1.y = center.y;
            c2.x = center.x + raster * 0.8f;
            c2.y = center.y;
            p1 = new CirclePath(c1, r1);
            p2 = new CirclePath(c2, r2);
            p1.op(p2, Op.DIFFERENCE);
            PathHelper.rotatePath(center, p1, i * 360 / anz);
            inner.addPath(p1);
        }
        addPath(inner);
    }

    protected void drawBubbleTail(final PointF center, final float radius, final TailOptionsBubbles tailOptions) {
        final Boolean flip = Randomizer.getRandomBoolean();
        for (int i = 0; i < tailOptions.anzTails; i++) {
            final int repeats = Randomizer.getRandomInt(tailOptions.minSinusRepeats, tailOptions.maxSinusRepeats);
            final float amplitude = radius * Randomizer.getRandomFloat(tailOptions.minAmplitude, tailOptions.maxAmplitude);

            float length = radius * Randomizer.getRandomFloat(tailOptions.minLength, tailOptions.maxLength);
            switch (tailOptions.tailRotationType) {
                default:
                case Spiral:
                    length = radius * tailOptions.minLength + i * (radius * tailOptions.maxLength - radius * tailOptions.minLength) / tailOptions.anzTails;
                    break;
                case Heart:
                    if (i < tailOptions.anzTails / 2) {
                        final int index = i;
                        length = radius * tailOptions.minLength
                                + index * (radius * tailOptions.maxLength - radius * tailOptions.minLength) / (tailOptions.anzTails / 2);
                    } else {
                        final int index = i - tailOptions.anzTails / 2;
                        length = radius * tailOptions.minLength
                                + index * (radius * tailOptions.maxLength - radius * tailOptions.minLength) / (tailOptions.anzTails / 2);
                    }
                    break;
                case Even:
                case Random:
                    break;
            }

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
                case Spiral:
                case Even:
                    PathHelper.rotatePath(center, s, i * 360 / tailOptions.anzTails);
                    break;
                case Random:
                    PathHelper.rotatePath(center, s, Randomizer.getRandomFloat(0, 360));
                    break;
                case Heart:
                    if (i < tailOptions.anzTails / 2) {
                        final int index = i;
                        PathHelper.rotatePath(center, s, -index * 180 / (tailOptions.anzTails / 2));
                    } else {
                        final int index = i - tailOptions.anzTails / 2;
                        PathHelper.rotatePath(center, s, index * 180 / (tailOptions.anzTails / 2));
                    }
                    break;
            }
            bubbletail.addPath(s);
        }
        addPath(bubbletail);
    }

    protected void drawTail(final PointF center, final float radius, final TailOptionsLine tailOptions) {
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
            c.x = center.x + radius * tailOptions.inset + length;
            c.y = center.y;
            final Path s = new SinusPath(c, length, repeats, amplitude, tailOptions.sinusAmplitudeType, !tailOptions.outline);
            if (getFlipBoolean(flip, tailOptions)) {
                PathHelper.mirrorPathUpDown(c.x, c.y, s);
            }
            switch (tailOptions.tailRotationType) {
                default:
                case Spiral:
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
