
package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import de.geithonline.wallpaperdesigner.shapes.CirclePath.CIRCLE_STYLE;
import de.geithonline.wallpaperdesigner.shapes.SquarePath.SQUARE_STYLE;
import de.geithonline.wallpaperdesigner.utils.PathHelper;

public class LogoPathHandys extends Path {

    public enum HANDY_STYLE {
        NEXUS_V1, NEXUS_V2, ONEPLUSONE_V1, ONEPLUSONE_V2, NEXUS_V3, LG_V1, LG_V2, Moto, MotoInvert
    }

    public LogoPathHandys(final PointF center, final float radius, final HANDY_STYLE variante) {
        super();
        switch (variante) {
            default:
            case NEXUS_V1:
                drawNexusV1(center, radius);
                break;
            case NEXUS_V2:
                drawNexusV2(center, radius);
                break;
            case NEXUS_V3:
                drawNexusV3(center, radius);
                break;
            case ONEPLUSONE_V1:
                drawOnePlusOneV1(center, radius);
                break;
            case ONEPLUSONE_V2:
                drawOnePlusOneV2(center, radius);
                break;
            case LG_V1:
                drawLGV1(center, radius);
                break;
            case LG_V2:
                drawLGV2(center, radius);
                break;
            case Moto:
                drawMoto(center, radius);
                break;
            case MotoInvert:
                drawMotoInvert(center, radius);
                break;
        }

    }

    private void drawMotoInvert(final PointF center, final float radius) {
        drawMoto(center, radius * 0.65f);
        final Path path = new CirclePath(center, radius, 0, true, CIRCLE_STYLE.CIRCLE);
        op(path, Op.REVERSE_DIFFERENCE);

    }

    private void drawMoto(final PointF center, final float radius) {
        final float raster = radius / 8;
        final PointF pos = new PointF();
        pos.x = center.x - 3 * raster;
        pos.y = center.y;
        Path path = new SimpleTrianglePath(pos, 4 * raster, 8 * raster);
        addPath(path);
        pos.x = center.x + 3 * raster;
        pos.y = center.y;
        path = new SimpleTrianglePath(pos, 4 * raster, 8 * raster);
        op(path, Op.UNION);

        pos.x = center.x - 2.6f * raster;
        pos.y = center.y + 8.5f * raster;
        path = new OvalPath(pos, 3.2f * raster, 6 * raster, Direction.CW);
        op(path, Op.DIFFERENCE);

        pos.x = center.x + 2.6f * raster;
        pos.y = center.y + 8.5f * raster;
        path = new OvalPath(pos, 3.2f * raster, 6 * raster, Direction.CW);
        op(path, Op.DIFFERENCE);
    }

    private void drawLGV1(final PointF center, final float radius) {
        addPath(getLGCircle(center, radius));

    }

    private void drawLGV2(final PointF center, final float radius) {
        addCircle(center.x, center.y, radius, Direction.CCW);
        addPath(getLGCircle(center, radius * 0.8f));

    }

    private void drawNexusV1(final PointF center, final float radius) {
        addPath(new SquarePath(center, radius, true, SQUARE_STYLE.ROUNDED, Direction.CCW));
        addPath(getNexusArm(center, radius, 45));
        addPath(getNexusArm(center, radius, 135));
        addPath(getNexusArm(center, radius, 225));
        addPath(getNexusArm(center, radius, 315));
    }

    private void drawNexusV2(final PointF center, final float radius) {
        addPath(new SquarePath(center, radius, true, SQUARE_STYLE.ROUNDED, Direction.CCW));
        addPath(new SquarePath(center, radius * 0.9f, true, SQUARE_STYLE.ROUNDED, Direction.CW));
        addPath(getNexusArm(center, radius, 45));
        addPath(getNexusArm(center, radius, 135));
        addPath(getNexusArm(center, radius, 225));
        addPath(getNexusArm(center, radius, 315));
    }

    private void drawNexusV3(final PointF center, final float radius) {
        final float factor = 1.4f;
        addPath(getNexusArmV2(center, radius * factor, 45));
        addPath(getNexusArmV2(center, radius * factor, 135));
        addPath(getNexusArmV2(center, radius * factor, 225));
        addPath(getNexusArmV2(center, radius * factor, 315));
    }

    private void drawOnePlusOneV1(final PointF center, final float radius) {
        addPath(getOnePlusSquare(center, radius));
    }

    private void drawOnePlusOneV2(final PointF center, final float radius) {
        addPath(new SquarePath(center, radius, true, SQUARE_STYLE.NORMAL, Direction.CCW));
        addPath(getOnePlusSquare(center, radius * 0.85f));
    }

    private Path getNexusArm(final PointF center, final float radius, final int rotate) {
        final Path p = new Path();
        final float raster = radius / 15;

        p.moveTo(center.x + 1 * raster, center.y + 0 * raster);
        p.lineTo(center.x + 3 * raster, center.y - 2 * raster);
        p.lineTo(center.x + 14 * raster, center.y - 2 * raster);
        p.lineTo(center.x + 14 * raster, center.y + 2 * raster);
        p.lineTo(center.x + 3 * raster, center.y + 2 * raster);
        close();
        PathHelper.rotatePath(center.x, center.y, p, rotate);
        return p;
    }

    private Path getNexusArmV2(final PointF center, final float radius, final int rotate) {
        final Path p = new Path();
        final float raster = radius / 16;

        p.moveTo(center.x + 1 * raster, center.y + 0 * raster);
        p.lineTo(center.x + 3 * raster, center.y - 2 * raster);
        p.lineTo(center.x + 14 * raster, center.y - 2 * raster);
        p.lineTo(center.x + 16 * raster, center.y - 0 * raster);
        p.lineTo(center.x + 14 * raster, center.y + 2 * raster);
        p.lineTo(center.x + 3 * raster, center.y + 2 * raster);
        close();
        PathHelper.rotatePath(center.x, center.y, p, rotate);
        return p;
    }

    private Path getOnePlusSquare(final PointF center, final float radius) {
        final Path p = new Path();
        final float raster = radius / 11;

        // square
        p.moveTo(center.x - 9 * raster, center.y - 9 * raster);
        p.lineTo(center.x + 3 * raster, center.y - 9 * raster);
        p.lineTo(center.x + 3 * raster, center.y - 7 * raster);
        p.lineTo(center.x - 7 * raster, center.y - 7 * raster);
        p.lineTo(center.x - 7 * raster, center.y + 7 * raster);
        p.lineTo(center.x + 7 * raster, center.y + 7 * raster);
        p.lineTo(center.x + 7 * raster, center.y - 3 * raster);
        p.lineTo(center.x + 9 * raster, center.y - 3 * raster);
        p.lineTo(center.x + 9 * raster, center.y + 9 * raster);
        p.lineTo(center.x - 9 * raster, center.y + 9 * raster);
        p.close();
        // plus
        p.moveTo(center.x + 5 * raster, center.y - 9 * raster);
        p.lineTo(center.x + 7 * raster, center.y - 9 * raster);
        p.lineTo(center.x + 7 * raster, center.y - 11 * raster);
        p.lineTo(center.x + 9 * raster, center.y - 11 * raster);
        p.lineTo(center.x + 9 * raster, center.y - 9 * raster);
        p.lineTo(center.x + 11 * raster, center.y - 9 * raster);
        p.lineTo(center.x + 11 * raster, center.y - 7 * raster);
        p.lineTo(center.x + 9 * raster, center.y - 7 * raster);
        p.lineTo(center.x + 9 * raster, center.y - 5 * raster);
        p.lineTo(center.x + 7 * raster, center.y - 5 * raster);
        p.lineTo(center.x + 7 * raster, center.y - 7 * raster);
        p.lineTo(center.x + 5 * raster, center.y - 7 * raster);
        p.close();
        // one
        p.moveTo(center.x - 3 * raster, center.y - 3 * raster);
        p.lineTo(center.x - 3 * raster, center.y - 5 * raster);
        p.lineTo(center.x + 1 * raster, center.y - 5 * raster);
        p.lineTo(center.x + 1 * raster, center.y + 3 * raster);
        p.lineTo(center.x + 3 * raster, center.y + 3 * raster);
        p.lineTo(center.x + 3 * raster, center.y + 5 * raster);
        p.lineTo(center.x - 3 * raster, center.y + 5 * raster);
        p.lineTo(center.x - 3 * raster, center.y + 3 * raster);
        p.lineTo(center.x - 1 * raster, center.y + 3 * raster);
        p.lineTo(center.x - 1 * raster, center.y - 3 * raster);
        p.close();
        return p;
    }

    private Path getLGCircle(final PointF center, final float radius) {
        final Path p = new Path();
        final float raster = radius / 8;

        // Circle
        final RectF oval = new RectF();
        oval.left = center.x - radius;
        oval.right = center.x + radius;
        oval.top = center.y - radius;
        oval.bottom = center.y + radius;

        p.moveTo(center.x + 3 * raster, center.y + 0 * raster);
        p.lineTo(center.x + radius, center.y + 0 * raster);
        p.arcTo(oval, 0, 270);
        p.lineTo(center.x + 0 * raster, center.y - 7 * raster);

        oval.left = center.x - radius + raster;
        oval.right = center.x + radius - raster;
        oval.top = center.y - radius + raster;
        oval.bottom = center.y + radius - raster;
        p.arcTo(oval, 270, -262);
        p.lineTo(center.x + 3 * raster, center.y + 1 * raster);
        p.close();

        // L
        p.moveTo(center.x - 1 * raster, center.y - 4 * raster);
        p.lineTo(center.x + 0 * raster, center.y - 4 * raster);
        p.lineTo(center.x + 0 * raster, center.y + 3 * raster);
        p.lineTo(center.x + 3 * raster, center.y + 3 * raster);
        p.lineTo(center.x + 3 * raster, center.y + 4 * raster);
        p.lineTo(center.x - 1 * raster, center.y + 4 * raster);
        p.close();
        // Auge
        p.addCircle(center.x - 3.5f * raster, center.y - 3 * raster, raster, Direction.CW);

        return p;
    }

}
