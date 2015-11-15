package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;

public class PillowPath extends Path {

	public enum PILLOW_TYPE {
		PLEKTRUM, FINGERNAIL, TREKY, YINGYANG, PEEEK, ARMOR, MESSER, BLAZON;
	}

	/**
	 * @param center
	 *            center Point
	 * @param radius
	 *            Radius von 1f bis 5f....
	 */
	public PillowPath(final PointF center, final float radius, final PILLOW_TYPE type) {
		super();

		switch (type) {
		default:
		case PLEKTRUM:
			drawPlektrum(center, radius);
			break;
		case FINGERNAIL:
			drawFingerNail(center, radius);
			break;
		case TREKY:
			drawTreky(center, radius);
			break;
		case YINGYANG:
			drawYingYang(center, radius);
			break;
		case PEEEK:
			drawPeeek(center, radius);
			break;
		case ARMOR:
			drawArmor(center, radius);
			break;
		case MESSER:
			drawMesser(center, radius);
			break;
		case BLAZON:
			drawWappen(center, radius);
			break;
		}
	}

	private void drawPlektrum(final PointF center, final float radius) {
		final float raster = radius / 2;
		moveTo(center.x - 2 * raster, center.y + 2 * raster);
		quadTo(center.x - 2 * raster, center.y - 1 * raster, // controllpoint
				center.x + 0 * raster, center.y - 2 * raster); // Zielpunkt
		quadTo(center.x + 2 * raster, center.y - 1 * raster, // controllpoint
				center.x + 2 * raster, center.y + 2 * raster); // Zielpunkt
		quadTo(center.x + 0 * raster, center.y + 3 * raster, // controllpoint
				center.x - 2 * raster, center.y + 2 * raster); // Zielpunkt
		close();
	}

	private void drawFingerNail(final PointF center, final float radius) {
		final float raster = radius / 2;
		moveTo(center.x - 2 * raster, center.y + 2 * raster);
		quadTo(center.x - 2 * raster, center.y - 1 * raster, // controllpoint
				center.x + 0 * raster, center.y - 2 * raster); // Zielpunkt
		quadTo(center.x + 2 * raster, center.y - 1 * raster, // controllpoint
				center.x + 2 * raster, center.y + 2 * raster); // Zielpunkt
		quadTo(center.x + 0 * raster, center.y + 1 * raster, // controllpoint
				center.x - 2 * raster, center.y + 2 * raster); // Zielpunkt
		close();
	}

	private void drawTreky(final PointF center, final float radius) {
		final float raster = radius / 2;
		moveTo(center.x - 2 * raster, center.y + 2 * raster);
		quadTo(center.x - 2 * raster, center.y - 1 * raster, // controllpoint
				center.x + 0 * raster, center.y - 2 * raster); // Zielpunkt
		quadTo(center.x + 2 * raster, center.y - 1 * raster, // controllpoint
				center.x + 2 * raster, center.y + 1 * raster); // Zielpunkt
		quadTo(center.x + 1 * raster, center.y - 1 * raster, // controllpoint
				center.x - 2 * raster, center.y + 2 * raster); // Zielpunkt
		close();
	}

	private void drawYingYang(final PointF center, final float radius) {
		final float raster = radius / 2;
		moveTo(center.x + 0 * raster, center.y - 2 * raster);
		final RectF oval = new RectF();
		oval.left = center.x - 1 * raster;
		oval.right = center.x + 1 * raster;
		oval.top = center.y - 2 * raster;
		oval.bottom = center.y + 0 * raster;
		arcTo(oval, -90, 180);
		oval.left = center.x - 1 * raster;
		oval.right = center.x + 1 * raster;
		oval.top = center.y + 0 * raster;
		oval.bottom = center.y + 2 * raster;
		arcTo(oval, -90, -180);
		oval.left = center.x - 2 * raster;
		oval.right = center.x + 2 * raster;
		oval.top = center.y - 2 * raster;
		oval.bottom = center.y + 2 * raster;
		arcTo(oval, 90, 180);
		close();
	}

	private void drawPeeek(final PointF center, final float radius) {
		final float raster = radius / 2;
		moveTo(center.x + 0 * raster, center.y + 2 * raster);
		quadTo(center.x + 0 * raster, center.y - 0 * raster, // controllpoint
				center.x + 2 * raster, center.y - 0 * raster); // Zielpunkt
		final RectF oval = new RectF();
		oval.left = center.x - 2 * raster;
		oval.right = center.x + 2 * raster;
		oval.top = center.y - 2 * raster;
		oval.bottom = center.y + 2 * raster;
		arcTo(oval, 0, -180);
		quadTo(center.x + 0 * raster, center.y - 0 * raster, // controllpoint
				center.x + 0 * raster, center.y + 2 * raster); // Zielpunkt
		close();
	}

	private void drawArmor(final PointF center, final float radius) {
		final float raster = radius / 2;
		moveTo(center.x + 0 * raster, center.y + 2 * raster);
		quadTo(center.x + 0 * raster, center.y - 1 * raster, // controllpoint
				center.x + 2 * raster, center.y - 1 * raster); // Zielpunkt
		quadTo(center.x + 1 * raster, center.y - 1 * raster, // controllpoint
				center.x + 1 * raster, center.y - 2 * raster); // Zielpunkt

		quadTo(center.x + 0 * raster, center.y - 1 * raster, // controllpoint
				center.x - 1 * raster, center.y - 2 * raster); // Zielpunkt

		quadTo(center.x - 1 * raster, center.y - 1 * raster, // controllpoint
				center.x - 2 * raster, center.y - 1 * raster); // Zielpunkt

		quadTo(center.x + 0 * raster, center.y - 1 * raster, // controllpoint
				center.x + 0 * raster, center.y + 2 * raster); // Zielpunkt
		close();
	}

	private void drawMesser(final PointF center, final float radius) {
		final float raster = radius / 2;
		moveTo(center.x + 0 * raster, center.y + 1 * raster);
		quadTo(center.x + 2 * raster, center.y + 1 * raster, // controllpoint
				center.x + 2 * raster, center.y - 1 * raster); // Zielpunkt
		final RectF oval = new RectF();
		oval.left = center.x - 0 * raster;
		oval.right = center.x + 2 * raster;
		oval.top = center.y - 2 * raster;
		oval.bottom = center.y + 0 * raster;
		arcTo(oval, 0, 180);

		quadTo(center.x - 2 * raster, center.y - 1 * raster, // controllpoint
				center.x - 2 * raster, center.y + 1 * raster); // Zielpunkt
		oval.left = center.x - 2 * raster;
		oval.right = center.x + 0 * raster;
		oval.top = center.y - 0 * raster;
		oval.bottom = center.y + 2 * raster;
		arcTo(oval, 180, 180);
		close();
	}

	private void drawWappen(final PointF center, final float radius) {
		final float raster = radius / 2;
		moveTo(center.x + 0 * raster, center.y + 2 * raster);
		quadTo(center.x + 2 * raster, center.y + 1 * raster, // controllpoint
				center.x + 2 * raster, center.y - 1 * raster); // Zielpunkt

		quadTo(center.x + 0.5f * raster, center.y - 1 * raster, // controllpoint
				center.x + 0 * raster, center.y - 2 * raster); // Zielpunkt
		quadTo(center.x - 0.5f * raster, center.y - 1 * raster, // controllpoint
				center.x - 2 * raster, center.y - 1 * raster); // Zielpunkt

		quadTo(center.x - 2 * raster, center.y + 1 * raster, // controllpoint
				center.x + 0 * raster, center.y + 2 * raster); // Zielpunkt
		close();
	}

	/**
	 * @param center
	 *            center Point
	 * @param radius
	 *            Radius von 1f bis 5f....
	 */
	public PillowPath(final PointF center, final float radius) {
		super();
		final float raster = radius / 2;
		moveTo(center.x - 2 * raster, center.y - 2 * raster);
		quadTo(center.x + 0 * raster, center.y - 1 * raster, // controllpoint
				center.x + 2 * raster, center.y - 2 * raster); // Zielpunkt
		quadTo(center.x + 1 * raster, center.y - 0 * raster, // controllpoint
				center.x + 2 * raster, center.y + 2 * raster); // Zielpunkt
		quadTo(center.x + 0 * raster, center.y + 1 * raster, // controllpoint
				center.x - 2 * raster, center.y + 2 * raster); // Zielpunkt
		quadTo(center.x - 1 * raster, center.y + 0 * raster, // controllpoint
				center.x - 2 * raster, center.y - 2 * raster); // Zielpunkt
		close();
	}

	/**
	 * @param center
	 *            center Point
	 * @param radius
	 *            Radius von 1f bis 5f....
	 */
	public PillowPath(final int arms, final PointF center, final float radius) {
		super();
		drawPillow(arms, center, radius);
	}

	private void drawPillow(final int arms, final PointF center, float radius) {
		radius = radius * 1.5f;
		final float angle = (float) (2 * Math.PI / (arms));
		final float cpRadius = radius * (arms - 1) * 0.1f;
		for (int i = 0; i <= arms; i++) {
			final PointF cp = new PointF();
			final PointF p = new PointF();
			cp.x = (float) (center.x + Math.cos((i - 0.5f) * angle) * cpRadius);
			cp.y = (float) (center.y + Math.sin((i - 0.5f) * angle) * cpRadius);
			p.x = (float) (center.x + Math.cos((i) * angle) * radius);
			p.y = (float) (center.y + Math.sin((i) * angle) * radius);
			if (i == 0) {
				moveTo(p.x, p.y);
			} else {
				quadTo(cp.x, cp.y, p.x, p.y);
			}
		}
		close();
	}
}
