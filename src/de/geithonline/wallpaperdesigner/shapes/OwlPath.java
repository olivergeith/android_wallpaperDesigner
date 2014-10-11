package de.geithonline.wallpaperdesigner.shapes;

import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;
import de.geithonline.wallpaperdesigner.shapes.base.OvalPath;

public class OwlPath extends Path {

	public OwlPath(final Point center, final float radius, final String variante) {
		super();
		switch (variante) {
		default:
		case "V1":
			drawOwl(center, radius, "V1");
			break;
		case "V2":
			drawOwl(center, radius, "V2");
			break;
		case "V3":
			drawOwlV3(center, radius);
			break;
		case "V4":
			drawOwlV4(center, radius);
			break;
		}

	}

	private void drawOwl(final Point center, final float radius, final String variante) {
		final float raster = radius / 4;

		moveTo(center.x + 1 * raster, center.y - 3 * raster);

		quadTo(center.x - 0 * raster, center.y - 3.5f * raster, // controllpoint
				center.x - 1 * raster, center.y - 3 * raster); // Zielpunkt
		lineTo(center.x - 2 * raster, center.y - 5 * raster);
		quadTo(center.x - 4 * raster, center.y - 0 * raster, // controllpoint
				center.x - 2 * raster, center.y + 3 * raster); // Zielpunkt
		quadTo(center.x - 0.5f * raster, center.y + 3 * raster, // controllpoint
				center.x - 0.5f * raster, center.y + 4 * raster); // Zielpunkt
		lineTo(center.x + 0.5f * raster, center.y + 4 * raster);
		quadTo(center.x + 0.5f * raster, center.y + 3 * raster, // controllpoint
				center.x + 2 * raster, center.y + 3 * raster); // Zielpunkt
		quadTo(center.x + 4 * raster, center.y - 0 * raster, // controllpoint
				center.x + 2 * raster, center.y - 5 * raster); // Zielpunkt
		close();

		// Augen
		switch (variante) {
		default:
		case "V1":
			drawAugeV1(center, raster);
			break;
		case "V2":
			drawAugeV2(center, raster);
			break;
		}
		// Nase
		moveTo(center.x + 0 * raster, center.y - 0.2f * raster);
		lineTo(center.x + 0.5f * raster, center.y + 0.2f * raster);
		lineTo(center.x - 0 * raster, center.y + 1.0f * raster);
		lineTo(center.x - 0.5f * raster, center.y + 0.2f * raster);
		close();
		// füsse
		addPath(new OvalPath(new PointF(center.x - 1.1f * raster, center.y + 4 * raster), raster * 0.3f, raster * 0.9f, Direction.CW));
		addPath(new OvalPath(new PointF(center.x - 1.8f * raster, center.y + 4 * raster), raster * 0.3f, raster * 0.9f, Direction.CW));
		addPath(new OvalPath(new PointF(center.x - 2.5f * raster, center.y + 4 * raster), raster * 0.3f, raster * 0.9f, Direction.CW));

		addPath(new OvalPath(new PointF(center.x + 1.1f * raster, center.y + 4 * raster), raster * 0.3f, raster * 0.9f, Direction.CW));
		addPath(new OvalPath(new PointF(center.x + 1.8f * raster, center.y + 4 * raster), raster * 0.3f, raster * 0.9f, Direction.CW));
		addPath(new OvalPath(new PointF(center.x + 2.5f * raster, center.y + 4 * raster), raster * 0.3f, raster * 0.9f, Direction.CW));
	}

	private void drawAugeV1(final Point center, final float raster) {
		final float pupillenHoehe = 0.8f; // Randomizer.getRandomFloat(0.9f, 1.5f);
		addCircle(center.x - 1.3f * raster, center.y - 1.2f * raster, raster * 1.2f, Direction.CW);
		addCircle(center.x + 1.3f * raster, center.y - 1.2f * raster, raster * 1.2f, Direction.CW);
		addCircle(center.x - 1.3f * raster, center.y - 1.2f * raster, raster * 0.9f, Direction.CCW);
		addCircle(center.x + 1.3f * raster, center.y - 1.2f * raster, raster * 0.9f, Direction.CCW);
		addCircle(center.x - 0.99f * raster, center.y - pupillenHoehe * raster, raster * 0.4f, Direction.CW);
		addCircle(center.x + 0.99f * raster, center.y - pupillenHoehe * raster, raster * 0.4f, Direction.CW);
	}

	private void drawAugeV2(final Point center, final float raster) {
		final float pupillenHoehe = 0.8f; // Randomizer.getRandomFloat(0.9f, 1.5f);
		addCircle(center.x - 1.3f * raster, center.y - 1.2f * raster, raster * 1.2f, Direction.CW);
		addCircle(center.x + 1.3f * raster, center.y - 1.2f * raster, raster * 1.2f, Direction.CW);
		addCircle(center.x - 0.99f * raster, center.y - pupillenHoehe * raster, raster * 0.4f, Direction.CCW);
		addCircle(center.x + 0.99f * raster, center.y - pupillenHoehe * raster, raster * 0.4f, Direction.CCW);
	}

	private void drawOwlV3(final Point center, final float radius) {
		final float raster = radius / 6;

		moveTo(center.x + 2 * raster, center.y - 5 * raster);
		quadTo(center.x - 0 * raster, center.y - 6 * raster, // controllpoint
				center.x - 2 * raster, center.y - 5 * raster); // Zielpunkt
		lineTo(center.x - 4 * raster, center.y - 7 * raster);
		quadTo(center.x - 5 * raster, center.y - 5 * raster, // controllpoint
				center.x - 4 * raster, center.y - 3 * raster); // Zielpunkt
		lineTo(center.x - 4.5f * raster, center.y - 1 * raster);
		quadTo(center.x - 3 * raster, center.y + 1 * raster, // controllpoint
				center.x - 3 * raster, center.y + 4 * raster); // Zielpunkt
		quadTo(center.x - 2 * raster, center.y + 6 * raster, // controllpoint
				center.x - 0 * raster, center.y + 7 * raster); // Zielpunkt

		quadTo(center.x + 2 * raster, center.y + 6 * raster, // controllpoint
				center.x + 3 * raster, center.y + 4 * raster); // Zielpunkt
		quadTo(center.x + 3 * raster, center.y + 1 * raster, // controllpoint
				center.x + 4.5f * raster, center.y - 1 * raster); // Zielpunkt
		lineTo(center.x + 4 * raster, center.y - 3 * raster);
		quadTo(center.x + 5 * raster, center.y - 5 * raster, // controllpoint
				center.x + 4 * raster, center.y - 7 * raster); // Zielpunkt
		close();

		// Flügel links
		moveTo(center.x - 4.5f * raster, center.y - 1 * raster);
		quadTo(center.x - 3 * raster, center.y + 1 * raster, // controllpoint
				center.x - 3.2f * raster, center.y + 4 * raster); // Zielpunkt
		quadTo(center.x - 3 * raster, center.y + 5.5f * raster, // controllpoint ++++
				center.x - 4 * raster, center.y + 6 * raster); // Zielpunkt
		quadTo(center.x - 6 * raster, center.y + 2 * raster, // controllpoint
				center.x - 4.5f * raster, center.y - 1 * raster); // Zielpunkt
		close();
		// Flügel rechts
		moveTo(center.x + 4.5f * raster, center.y - 1 * raster);
		quadTo(center.x + 3 * raster, center.y + 1 * raster, // controllpoint
				center.x + 3.2f * raster, center.y + 4 * raster); // Zielpunkt
		quadTo(center.x + 3 * raster, center.y + 5.5f * raster, // controllpoint ++++
				center.x + 4 * raster, center.y + 6 * raster); // Zielpunkt
		quadTo(center.x + 6 * raster, center.y + 2 * raster, // controllpoint
				center.x + 4.5f * raster, center.y - 1 * raster); // Zielpunkt
		close();

		// Nase
		moveTo(center.x + 0 * raster, center.y - 1.2f * raster);
		lineTo(center.x + 1 * raster, center.y - 0.7f * raster);
		lineTo(center.x + 0 * raster, center.y + 1 * raster);
		lineTo(center.x - 1 * raster, center.y - 0.7f * raster);
		close();
		// Augen
		addCircle(center.x - 1.5f * raster, center.y - 2.5f * raster, raster * 1.4f, Direction.CW);
		addCircle(center.x + 1.5f * raster, center.y - 2.5f * raster, raster * 1.4f, Direction.CW);
		addCircle(center.x - 1.5f * raster, center.y - 1.8f * raster, raster * 0.4f, Direction.CCW);
		addCircle(center.x + 1.5f * raster, center.y - 1.8f * raster, raster * 0.4f, Direction.CCW);

		// füsse
		addPath(new OvalPath(new PointF(center.x - 1.1f * raster, center.y + 6 * raster), raster * 0.3f, raster * 0.9f, Direction.CW));
		addPath(new OvalPath(new PointF(center.x - 1.8f * raster, center.y + 6 * raster), raster * 0.3f, raster * 0.9f, Direction.CW));
		addPath(new OvalPath(new PointF(center.x - 2.5f * raster, center.y + 6 * raster), raster * 0.3f, raster * 0.9f, Direction.CW));

		addPath(new OvalPath(new PointF(center.x + 1.1f * raster, center.y + 6 * raster), raster * 0.3f, raster * 0.9f, Direction.CW));
		addPath(new OvalPath(new PointF(center.x + 1.8f * raster, center.y + 6 * raster), raster * 0.3f, raster * 0.9f, Direction.CW));
		addPath(new OvalPath(new PointF(center.x + 2.5f * raster, center.y + 6 * raster), raster * 0.3f, raster * 0.9f, Direction.CW));
	}

	private void drawOwlV4(final Point center, final float radius) {
		final float raster = radius / 6;

		moveTo(center.x + 4 * raster, center.y - 7 * raster);
		quadTo(center.x + 2 * raster, center.y - 7 * raster, // controllpoint
				center.x - 0 * raster, center.y - 5 * raster); // Zielpunkt
		quadTo(center.x - 2 * raster, center.y - 7 * raster, // controllpoint
				center.x - 4 * raster, center.y - 7 * raster); // Zielpunkt

		lineTo(center.x - 3 * raster, center.y - 5 * raster);
		lineTo(center.x - 4 * raster, center.y - 2 * raster);

		// fl
		quadTo(center.x - 2 * raster, center.y + 1 * raster, // controllpoint
				center.x - 3 * raster, center.y + 3 * raster); // Zielpunkt
		quadTo(center.x - 2 * raster, center.y + 5 * raster, // controllpoint
				center.x - 0 * raster, center.y + 6 * raster); // Zielpunkt

		quadTo(center.x + 2 * raster, center.y + 5 * raster, // controllpoint
				center.x + 3 * raster, center.y + 3 * raster); // Zielpunkt
		quadTo(center.x + 2 * raster, center.y + 1 * raster, // controllpoint
				center.x + 4 * raster, center.y - 2 * raster); // Zielpunkt
		lineTo(center.x + 3 * raster, center.y - 5 * raster);
		lineTo(center.x + 4 * raster, center.y - 7 * raster);
		close();

		// Flügel links
		moveTo(center.x - 4 * raster, center.y - 2 * raster);
		quadTo(center.x - 2 * raster, center.y + 1 * raster, // controllpoint
				center.x - 3.2f * raster, center.y + 3 * raster); // Zielpunkt
		quadTo(center.x - 3.5f * raster, center.y + 4 * raster, // controllpoint ++++
				center.x - 5 * raster, center.y + 4.5f * raster); // Zielpunkt
		quadTo(center.x - 6 * raster, center.y + 1 * raster, // controllpoint
				center.x - 4 * raster, center.y - 2 * raster); // Zielpunkt
		close();
		// Flügel rechts
		moveTo(center.x + 4 * raster, center.y - 2 * raster);
		quadTo(center.x + 2 * raster, center.y + 1 * raster, // controllpoint
				center.x + 3.2f * raster, center.y + 3 * raster); // Zielpunkt
		quadTo(center.x + 3.5f * raster, center.y + 4 * raster, // controllpoint ++++
				center.x + 5 * raster, center.y + 4.5f * raster); // Zielpunkt
		quadTo(center.x + 6 * raster, center.y + 1 * raster, // controllpoint
				center.x + 4 * raster, center.y - 2 * raster); // Zielpunkt
		close();

		// Nase
		moveTo(center.x + 0 * raster, center.y - 1.5f * raster);
		lineTo(center.x + 0.5f * raster, center.y - 1 * raster);
		lineTo(center.x + 0 * raster, center.y + 0 * raster);
		lineTo(center.x - 0.5f * raster, center.y - 1 * raster);
		close();
		// Augen
		addCircle(center.x - 1.5f * raster, center.y - 2 * raster, raster * 1.4f, Direction.CW);
		addCircle(center.x + 1.5f * raster, center.y - 2 * raster, raster * 1.4f, Direction.CW);
		addCircle(center.x - 1.2f * raster, center.y - 2 * raster, raster * 0.4f, Direction.CCW);
		addCircle(center.x + 1.2f * raster, center.y - 2 * raster, raster * 0.4f, Direction.CCW);

		// füsse
		addPath(new OvalPath(new PointF(center.x - 1.1f * raster, center.y + 6 * raster), raster * 0.3f, raster * 0.9f, Direction.CW));
		addPath(new OvalPath(new PointF(center.x - 1.8f * raster, center.y + 6 * raster), raster * 0.3f, raster * 0.9f, Direction.CW));
		addPath(new OvalPath(new PointF(center.x - 2.5f * raster, center.y + 6 * raster), raster * 0.3f, raster * 0.9f, Direction.CW));

		addPath(new OvalPath(new PointF(center.x + 1.1f * raster, center.y + 6 * raster), raster * 0.3f, raster * 0.9f, Direction.CW));
		addPath(new OvalPath(new PointF(center.x + 1.8f * raster, center.y + 6 * raster), raster * 0.3f, raster * 0.9f, Direction.CW));
		addPath(new OvalPath(new PointF(center.x + 2.5f * raster, center.y + 6 * raster), raster * 0.3f, raster * 0.9f, Direction.CW));
	}

}
