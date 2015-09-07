package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.PointF;
import de.geithonline.wallpaperdesigner.shapes.FlamePath.FLAME_TYPE;
import de.geithonline.wallpaperdesigner.utils.PathHelper;

public class SunPath extends Path {

	public enum SUN_TYPE {
		SUN_V1, SUN_FLAMES, SUN_FLAMES_DROP, SUN_TRIANGLE, SUN_ARROW_TRIANGLES, SUN_ARROW_V2, SUN_SHARP_TOOTH
	}

	/**
	 * More smooth and round no random peeks....
	 * 
	 * @param center
	 * @param radius
	 * @param arms
	 */
	public SunPath(final int arms, final PointF center, final float radius, final boolean filled, final SUN_TYPE type) {
		switch (type) {
			default:
			case SUN_V1:
				drawSunV1(arms, center, radius, filled);
				break;
			case SUN_FLAMES:
				drawSunFlames(arms, center, radius, filled, FLAME_TYPE.FLAME);
				break;
			case SUN_FLAMES_DROP:
				drawSunDropFlames(arms, center, radius, filled);
				break;
			case SUN_TRIANGLE:
				drawSunFlames(arms, center, radius, filled, FLAME_TYPE.TRIANGLE);
				break;
			case SUN_ARROW_TRIANGLES:
				drawSunFlames(arms, center, radius, filled, FLAME_TYPE.ARROW_TRIANGLE);
				break;
			case SUN_ARROW_V2:
				drawSunFlames(arms, center, radius, filled, FLAME_TYPE.ARROW_V2);
				break;
			case SUN_SHARP_TOOTH:
				drawSunFlames(arms, center, radius, filled, FLAME_TYPE.SHARP_TOOTH);
				break;
		}

	}

	private void drawSunV1(final int arms, final PointF center, final float radius, final boolean filled) {
		final float angle = (float) (2 * Math.PI / (arms));
		final float cpRadius = radius * 0.3f;
		for (int i = 0; i <= arms; i++) {
			final PointF cp = new PointF();
			final PointF p = new PointF();
			cp.x = (int) (center.x + Math.cos((i - 0.5f) * angle) * cpRadius);
			cp.y = (int) (center.y + Math.sin((i - 0.5f) * angle) * cpRadius);
			p.x = (int) (center.x + Math.cos((i) * angle) * radius * 1.3f);
			p.y = (int) (center.y + Math.sin((i) * angle) * radius * 1.3f);
			if (i == 0) {
				moveTo(p.x, p.y);
			} else {
				quadTo(cp.x, cp.y, p.x, p.y);
			}
		}
		close();

		if (!filled) {
			final float innerRadius = radius * 0.5f;
			addCircle(center.x, center.y, innerRadius, Direction.CCW);
		}
	}

	private void drawSunFlames(final int arms, final PointF center, final float radius, final boolean filled, final FLAME_TYPE style) {
		final float winkelProArm = (float) (2 * Math.PI / (arms));
		final float circleRadius = radius * 0.5f;
		final float innerCircleRadius = radius * 0.4f;
		final float flameCenterRadius = radius * 0.55f;
		final float flameSize = radius * 0.45f;

		addCircle(center.x, center.y, circleRadius, Direction.CW);

		for (int i = 0; i < arms; i++) {
			final int winkelInGrad = (int) ((i * winkelProArm) * 360 / (2 * Math.PI));
			// Log.i("Winkel", "=" + winkelInGrad);
			final PointF p = new PointF();
			p.x = (int) (center.x + Math.cos((i) * winkelProArm) * flameCenterRadius);
			p.y = (int) (center.y + Math.sin((i) * winkelProArm) * flameCenterRadius);
			final Path flame = new FlamePath(p, flameSize, style);
			PathHelper.rotatePath(p.x, p.y, flame, winkelInGrad);
			addPath(flame);
		}

		if (!filled) {
			addCircle(center.x, center.y, innerCircleRadius, Direction.CCW);
		}
	}

	private void drawSunDropFlames(final int arms, final PointF center, final float radius, final boolean filled) {
		final float winkelProArm = (float) (2 * Math.PI / (arms));
		final float circleRadius = radius * 0.47f;
		final float innerCircleRadius = radius * 0.4f;
		final float flameCenterRadius = radius * 0.75f;
		final float flameSize = radius * 0.25f;

		addCircle(center.x, center.y, circleRadius, Direction.CW);

		for (int i = 0; i < arms; i++) {
			final int winkelInGrad = (int) ((i * winkelProArm) * 360 / (2 * Math.PI));
			// Log.i("Winkel", "=" + winkelInGrad);
			final PointF p = new PointF();
			p.x = (int) (center.x + Math.cos((i) * winkelProArm) * flameCenterRadius);
			p.y = (int) (center.y + Math.sin((i) * winkelProArm) * flameCenterRadius);
			final Path flame = new DropPath(p, flameSize);
			PathHelper.rotatePath(p.x, p.y, flame, winkelInGrad + 90);
			addPath(flame);
		}

		if (!filled) {
			addCircle(center.x, center.y, innerCircleRadius, Direction.CCW);
		}
	}

}
