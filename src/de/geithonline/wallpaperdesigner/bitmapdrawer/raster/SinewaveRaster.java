package de.geithonline.wallpaperdesigner.bitmapdrawer.raster;

import android.graphics.Point;
import de.geithonline.wallpaperdesigner.utils.Randomizer;

public class SinewaveRaster extends AbstractRaster {

	public SinewaveRaster(final int width, final int height, final int radius, final float overlap, final RasterPositioning positioning,
			final int numberOfWaves) {
		super(radius, overlap, width, height);

		setPositioning(positioning);
		for (int n = 0; n < numberOfWaves; n++) {

			final double frequency = Randomizer.getRandomFloat(1, 10); // 1 will bin one sinuswawe from left to right
			final int abstand = (int) Math.round(radius * 2 * overlap / frequency);
			final int anzW = width / abstand + 2;

			int amplitude = 0;
			if (Randomizer.getRandomBoolean()) {
				amplitude = Randomizer.getRandomInt(0, height / 2);
			} else {
				amplitude = -Randomizer.getRandomInt(0, height / 2);
			}

			final int zeroLine = height / 2;

			for (int w = 0; w < anzW; w++) {
				// random koordinate an der gemalt werden soll
				final int x = w * abstand;
				final int y = (int) (-amplitude * Math.sin(frequency * x * 2 * Math.PI / width)) + zeroLine;
				final Point p = new Point(x, y);
				addPoint2List(width, height, p);
			}
		}
	}

	@Override
	public Point drawNextPoint() {
		return super.drawNextPoint();
	}

}
