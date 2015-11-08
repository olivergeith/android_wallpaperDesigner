package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.PointF;
import de.geithonline.wallpaperdesigner.utils.PathHelper;

public class LeafPath extends Path {

	public enum LEAF_STYLE {
		MAPLE, ROUND, WEED, FINGER_MAPLE
	}

	public LeafPath(final PointF center, final float radius, final LEAF_STYLE variante) {
		super();
		switch (variante) {
		default:
		case MAPLE:
			drawMaple(center, radius);
			break;
		case FINGER_MAPLE:
			drawFingerAhorn(center, radius);
			break;
		case ROUND:
			drawRoundLeaf(center, radius);
			break;
		case WEED:
			drawWeed(center, radius);
			break;
		}

	}

	private void drawRoundLeaf(final PointF center, final float radius) {
		final float raster = radius / 7;
		moveTo(center.x - 0.1f * raster, center.y + 7 * raster);
		lineTo(center.x - 0.1f * raster, center.y + 4 * raster);

		quadTo(center.x - 1 * raster, center.y + 5 * raster, // controllpoint
				center.x - 3 * raster, center.y + 5 * raster); // Zielpunkt
		lineTo(center.x - 3 * raster, center.y + 4 * raster);

		quadTo(center.x - 5 * raster, center.y + 4 * raster, // controllpoint
				center.x - 6 * raster, center.y + 3 * raster); // Zielpunkt
		lineTo(center.x - 5 * raster, center.y + 2 * raster);

		quadTo(center.x - 6 * raster, center.y + 1 * raster, // controllpoint
				center.x - 6 * raster, center.y - 1 * raster); // Zielpunkt
		lineTo(center.x - 5 * raster, center.y - 1 * raster);

		quadTo(center.x - 5 * raster, center.y - 3 * raster, // controllpoint
				center.x - 4 * raster, center.y - 4 * raster); // Zielpunkt
		lineTo(center.x - 3 * raster, center.y - 3 * raster);

		quadTo(center.x - 2 * raster, center.y - 6 * raster, // controllpoint
				center.x - 0 * raster, center.y - 7 * raster); // Zielpunkt

		quadTo(center.x + 2 * raster, center.y - 6 * raster, // controllpoint
				center.x + 3 * raster, center.y - 3 * raster); // Zielpunkt
		lineTo(center.x + 4 * raster, center.y - 4 * raster);

		quadTo(center.x + 5 * raster, center.y - 3 * raster, // controllpoint
				center.x + 5 * raster, center.y - 1 * raster); // Zielpunkt
		lineTo(center.x + 6 * raster, center.y - 1 * raster);

		quadTo(center.x + 6 * raster, center.y + 1 * raster, // controllpoint
				center.x + 5 * raster, center.y + 2 * raster); // Zielpunkt
		lineTo(center.x + 6 * raster, center.y + 3 * raster);

		quadTo(center.x + 5 * raster, center.y + 4 * raster, // controllpoint
				center.x + 3 * raster, center.y + 4 * raster); // Zielpunkt
		lineTo(center.x + 3 * raster, center.y + 5 * raster);

		quadTo(center.x + 1 * raster, center.y + 5 * raster, // controllpoint
				center.x + 0.1f * raster, center.y + 4 * raster); // Zielpunkt
		lineTo(center.x + 0.1f * raster, center.y + 7 * raster);
		close();

	}

	private void drawMaple(final PointF center, final float radius) {
		final float raster = radius / 6;
		moveTo(center.x - 0.1f * raster, center.y + 6 * raster);
		lineTo(center.x - 0.1f * raster, center.y + 3 * raster);
		lineTo(center.x - 2 * raster, center.y + 4 * raster);
		lineTo(center.x - 2 * raster, center.y + 3 * raster);
		lineTo(center.x - 4 * raster, center.y + 3 * raster);

		lineTo(center.x - 3 * raster, center.y + 2 * raster);
		lineTo(center.x - 6 * raster, center.y + 0 * raster);
		lineTo(center.x - 4.5f * raster, center.y + 0 * raster);
		lineTo(center.x - 6 * raster, center.y - 3 * raster);
		lineTo(center.x - 3 * raster, center.y - 1.5f * raster);
		lineTo(center.x - 3 * raster, center.y - 3 * raster);
		lineTo(center.x - 1.5f * raster, center.y - 0.5f * raster);
		lineTo(center.x - 2 * raster, center.y - 4 * raster);
		lineTo(center.x - 1 * raster, center.y - 3 * raster);

		lineTo(center.x - 0 * raster, center.y - 6 * raster);

		lineTo(center.x + 1 * raster, center.y - 3 * raster);
		lineTo(center.x + 2 * raster, center.y - 4 * raster);
		lineTo(center.x + 1.5f * raster, center.y - 0.5f * raster);
		lineTo(center.x + 3 * raster, center.y - 3 * raster);
		lineTo(center.x + 3 * raster, center.y - 1.5f * raster);
		lineTo(center.x + 6 * raster, center.y - 3 * raster);
		lineTo(center.x + 4.5f * raster, center.y + 0 * raster);
		lineTo(center.x + 6 * raster, center.y + 0 * raster);
		lineTo(center.x + 3 * raster, center.y + 2 * raster);

		lineTo(center.x + 4 * raster, center.y + 3 * raster);
		lineTo(center.x + 2 * raster, center.y + 3 * raster);
		lineTo(center.x + 2 * raster, center.y + 4 * raster);

		lineTo(center.x + 0.1f * raster, center.y + 3 * raster);
		lineTo(center.x + 0.1f * raster, center.y + 6 * raster);
		close();
	}

	private void drawWeed(final PointF center, final float radius) {
		// (1 + 0.9 Cos[8 t]) (1 + 0.1 Cos[24 t]) (0.9 + 0.05 Cos[200 t]) (1 + Sin[t])

		final int ecken = 200;
		final float winkelStep = (float) (2 * Math.PI / ecken);
		final Path path = new Path();
		for (int i = 0; i < ecken; i++) {
			final float t = i * winkelStep; // winkel
			float r = radius * (float) ((1 + 0.9f * Math.cos(8 * t)) //
					* (1 + 0.1f * Math.cos(24 * t)) //
					* (0.9f + 0.14f * Math.cos(96 * t)) //
					* (1 + Math.sin(t)));
			r = r / 2.8f;
			final PointF p = new PointF();
			p.x = (float) (center.x + Math.cos(t) * r);
			p.y = (float) (center.y + Math.sin(t) * r) - radius * 0.58f;
			if (i == 0) {
				path.moveTo(p.x, p.y);
			} else {
				path.lineTo(p.x, p.y);
			}
		}
		path.close();
		PathHelper.rotatePath(center.x, center.y, path, 180);
		addPath(path);
	}

	private void drawFingerAhorn(final PointF center, final float radius) {
		// (1 + 0.9 Cos[8 t]) (1 + 0.1 Cos[24 t]) (0.9 + 0.05 Cos[200 t]) (1 + Sin[t])

		final int ecken = 200;
		final float winkelStep = (float) (2 * Math.PI / ecken);
		final Path path = new Path();
		for (int i = 0; i < ecken; i++) {
			final float t = i * winkelStep; // winkel
			float r = radius * (float) //
			((1 + 0.3f * Math.cos(8 * t)) //
					* (1 + 0.1f * Math.cos(24 * t)) //
					* (0.9f + 0.14f * Math.cos(48 * t)) //
					* (1 + Math.sin(t)));
			r = r / 1.8f;
			final PointF p = new PointF();
			p.x = (float) (center.x + Math.cos(t) * r);
			p.y = (float) (center.y + Math.sin(t) * r) - radius * 0.58f;
			if (i == 0) {
				path.moveTo(p.x, p.y);
			} else {
				path.lineTo(p.x, p.y);
			}
		}
		path.close();
		PathHelper.rotatePath(center.x, center.y, path, 180);
		addPath(path);
	}

}
