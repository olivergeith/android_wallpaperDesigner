
package de.geithonline.wallpaperdesigner.shapes.composed;

import android.graphics.Path;
import android.graphics.PointF;
import de.geithonline.wallpaperdesigner.settings.specialoptions.TailOptions.SinusAmplitudeType;
import de.geithonline.wallpaperdesigner.shapes.CirclePath;
import de.geithonline.wallpaperdesigner.shapes.CirclePath.CIRCLE_STYLE;
import de.geithonline.wallpaperdesigner.shapes.HeartPath;
import de.geithonline.wallpaperdesigner.shapes.HeartPath.HEART_SHAPE;
import de.geithonline.wallpaperdesigner.shapes.StarPath;
import de.geithonline.wallpaperdesigner.shapes.StarPath.STAR_TYPE;
import de.geithonline.wallpaperdesigner.utils.PathHelper;
import de.geithonline.wallpaperdesigner.utils.Randomizer;

public class SinusObjectsPath extends ComposedPath {

	private final ESinusObjectsType objectType;

	/**
	 * Erzeugt einen waagerecht liegenden sinusförmigen Trail von Objekten.
	 *
	 * <code><br>
	 *   ...  |  ... <br>
	 *  .   . + .   .  3 Halbwellen<br>
	 *       ...<br>
	 *        |<br>
	 *      center<br>
	 * </code><br>
	 *
	 * @param center
	 * @param radius
	 * @param halbWellen
	 *            1 == one bow/Halbwelle, 2 == a complete sinus
	 * @param amplitude
	 * @param maxObjectRadius
	 * @param prozentToDraw
	 *            (0-100 allowed)
	 * @param sizeType
	 * @param objectType
	 */
	public SinusObjectsPath(final PointF center, final float radius, final int halbWellen, final float amplitude, final float maxObjectRadius,
			final int prozentToDraw, final ESinusObjectsSizingType sizeType, final ESinusObjectsType objectType, final SinusAmplitudeType amplitudeType) {
		super();
		this.objectType = objectType;
		draw(center, radius, halbWellen, amplitude, maxObjectRadius, prozentToDraw, sizeType, objectType, amplitudeType);
	}

	public SinusObjectsPath(final PointF center, final float radius, final int halbWellen, final float amplitude, final float maxObjectRadius,
			final int prozentToDraw, final ESinusObjectsSizingType sizeType, final ESinusObjectsType objectType) {
		this.objectType = objectType;
		draw(center, radius, halbWellen, amplitude, maxObjectRadius, prozentToDraw, sizeType, objectType, SinusAmplitudeType.normal);
	}

	private void draw(final PointF center, final float radius, final int halbWellen, final float amplitude, final float maxObjectRadius,
			final int prozentToDraw, final ESinusObjectsSizingType sizeType, final ESinusObjectsType objectType, final SinusAmplitudeType amplitudeType) {
		switch (sizeType) {
		case decreasing:
			drawSinusGettingSmaller(center, radius, halbWellen, amplitude, maxObjectRadius, prozentToDraw, amplitudeType);
			break;
		case increasing:
			drawSinusGettingBigger(center, radius, halbWellen, amplitude, maxObjectRadius, prozentToDraw, amplitudeType);
			break;
		case decreasing_withbigEnd:
			drawSinusGettingSmaller2(center, radius, halbWellen, amplitude, maxObjectRadius, prozentToDraw, amplitudeType);
			break;
		default:
		case random:
			drawSinus(center, radius, halbWellen, amplitude, maxObjectRadius, prozentToDraw, amplitudeType);
			break;
		case connected:
			drawSinusConnected(center, radius, halbWellen, amplitude, maxObjectRadius, prozentToDraw, amplitudeType);
			break;

		}
	}

	private void drawSinus(final PointF center, final float radius, final int sinRepeats, final float amplitude, final float maxBubbleRadius,
			final int prozentToDraw, final SinusAmplitudeType amplitudeType) {
		// nach links
		final float l = center.x - radius;
		final float r = center.x + radius;
		final float mitteY = center.y;
		// und nun der Sinus
		final int points = (int) (2 * radius / (maxBubbleRadius * 1.6f));
		for (int i = 1; i < points; i++) {
			final float a = calculateAmplitude(amplitude, amplitudeType, points, i);
			// nicht immer einen bubble zeichnen...
			if (Randomizer.getRandomBooleanTrueInPercentOfCases(prozentToDraw)) {
				final float bubbleRadius = Randomizer.getRandomFloat(0, maxBubbleRadius);
				final float x = l + i * (r - l) / points;
				final float angle = (float) ((float) i / points * Math.PI * sinRepeats);
				final float y = mitteY + (float) (a * Math.sin(angle));
				final PointF c = new PointF(x, y);
				addPath(getObject(bubbleRadius, c));
			} else if (Randomizer.getRandomBooleanTrueInPercentOfCases(5)) {
				// manchmal einen fetten Bubble zeichnen
				final float bubbleRadius = Randomizer.getRandomFloat(maxBubbleRadius * 1.5f, maxBubbleRadius * 2);
				final float x = l + i * (r - l) / points;
				final float angle = (float) ((float) i / points * Math.PI * sinRepeats);
				final float y = mitteY + (float) (a * Math.sin(angle));
				final PointF c = new PointF(x, y);
				addPath(getObject(bubbleRadius, c));
			}
		}
	}

	private void drawSinusConnected(final PointF center, final float radius, final int sinRepeats, final float amplitude, final float maxBubbleRadius,
			final int prozentToDraw, final SinusAmplitudeType amplitudeType) {
		// nach links
		final float l = center.x - radius;
		final float r = center.x + radius;
		final float mitteY = center.y;
		// und nun der Sinus
		final int points = (int) (2 * radius / (maxBubbleRadius * 0.6f));
		for (int i = 1; i < points; i++) {
			final float a = calculateAmplitude(amplitude, amplitudeType, points, i);
			// nicht immer einen bubble zeichnen...
			if (Randomizer.getRandomBooleanTrueInPercentOfCases(prozentToDraw)) {
				final float bubbleRadius = Randomizer.getRandomFloat(maxBubbleRadius / 2, maxBubbleRadius);
				final float x = l + i * (r - l) / points;
				final float angle = (float) ((float) i / points * Math.PI * sinRepeats);
				final float y = mitteY + (float) (a * Math.sin(angle));
				final PointF c = new PointF(x, y);
				addPath(getObject(bubbleRadius, c));
			} else if (Randomizer.getRandomBooleanTrueInPercentOfCases(5)) {
				// manchmal einen fetten Bubble zeichnen
				final float bubbleRadius = Randomizer.getRandomFloat(maxBubbleRadius, maxBubbleRadius * 3);
				final float x = l + i * (r - l) / points;
				final float angle = (float) ((float) i / points * Math.PI * sinRepeats);
				final float y = mitteY + (float) (a * Math.sin(angle));
				final PointF c = new PointF(x, y);
				addPath(getObject(bubbleRadius, c));
			}
		}
	}

	private void drawSinusGettingSmaller(final PointF center, final float radius, final int sinRepeats, final float amplitude, final float maxBubbleRadius,
			final int prozentToDraw, final SinusAmplitudeType amplitudeType) {
		// nach links
		final float l = center.x - radius;
		final float r = center.x + radius;
		final float mitteY = center.y;
		// und nun der Sinus
		final int anzahlBubbles = (int) (2 * radius / (maxBubbleRadius * 1.6f));
		for (int i = 1; i < anzahlBubbles; i++) {
			// nicht immer einen bubble zeichnen...
			if (Randomizer.getRandomBooleanTrueInPercentOfCases(prozentToDraw)) {
				final float bubbleRadius = (anzahlBubbles - (float) i) * (maxBubbleRadius / anzahlBubbles); // Randomizer.getRandomFloat(0,
				// maxBubbleRadius);
				final float x = l + i * (r - l) / anzahlBubbles;
				final float angle = (float) ((float) i / anzahlBubbles * Math.PI * sinRepeats);
				final float a = calculateAmplitude(amplitude, amplitudeType, anzahlBubbles, i);
				final float y = mitteY + (float) (a * Math.sin(angle));
				final PointF c = new PointF(x, y);
				addPath(getObject(bubbleRadius, c));
			}
		}
	}

	private void drawSinusGettingBigger(final PointF center, final float radius, final int sinRepeats, final float amplitude, final float maxBubbleRadius,
			final int prozentToDraw, final SinusAmplitudeType amplitudeType) {
		// nach links
		final float l = center.x - radius;
		final float r = center.x + radius;
		final float mitteY = center.y;
		// und nun der Sinus
		final int anzahlBubbles = (int) (2 * radius / (maxBubbleRadius * 1.6f));
		for (int i = 1; i < anzahlBubbles; i++) {
			// nicht immer einen bubble zeichnen...
			if (Randomizer.getRandomBooleanTrueInPercentOfCases(prozentToDraw)) {
				final float bubbleRadius = i * (maxBubbleRadius / anzahlBubbles); // Randomizer.getRandomFloat(0,
				// maxBubbleRadius);
				final float x = l + i * (r - l) / anzahlBubbles;
				final float angle = (float) ((float) i / anzahlBubbles * Math.PI * sinRepeats);
				final float a = calculateAmplitude(amplitude, amplitudeType, anzahlBubbles, i);
				final float y = mitteY + (float) (a * Math.sin(angle));
				final PointF c = new PointF(x, y);
				addPath(getObject(bubbleRadius, c));
			}
		}
	}

	private void drawSinusGettingSmaller2(final PointF center, final float radius, final int sinRepeats, final float amplitude, final float maxBubbleRadius,
			final int prozentToDraw, final SinusAmplitudeType amplitudeType) {
		// nach links
		final float l = center.x - radius;
		final float r = center.x + radius;
		final float mitteY = center.y;
		// und nun der Sinus
		final int anzahlBubbles = (int) (2 * radius / (maxBubbleRadius * 1.6f));
		for (int i = 1; i < anzahlBubbles; i++) {
			// nicht immer einen bubble zeichnen...
			if (Randomizer.getRandomBooleanTrueInPercentOfCases(prozentToDraw)) {
				float bubbleRadius = (anzahlBubbles - (float) i) * (maxBubbleRadius / anzahlBubbles);
				if (i + 1 == anzahlBubbles) {
					bubbleRadius = maxBubbleRadius * 2;
				}
				final float x = l + i * (r - l) / anzahlBubbles;
				final float angle = (float) ((float) i / anzahlBubbles * Math.PI * sinRepeats);
				final float a = calculateAmplitude(amplitude, amplitudeType, anzahlBubbles, i);
				final float y = mitteY + (float) (a * Math.sin(angle));
				final PointF c = new PointF(x, y);
				addPath(getObject(bubbleRadius, c));
			}
		}
	}

	private float calculateAmplitude(final float amplitude, final SinusAmplitudeType amplitudeType, final int anzahlBubbles, final int i) {
		float a = amplitude;
		switch (amplitudeType) {
		case increasingAmplitude:
			a = (i) * amplitude / anzahlBubbles;
			break;
		case decreasingAmplitude:
			a = (anzahlBubbles - i) * amplitude / anzahlBubbles;
			break;
		default:
		case normal:
			break;
		}
		return a;
	}

	private Path getObject(final float radius, final PointF center) {
		switch (objectType) {
		default:
		case bubble:
			return new CirclePath(center, radius, 0, true, CIRCLE_STYLE.CIRCLE);
		case star:
			return new StarPath(5, center, radius, STAR_TYPE.NORMAL, true);
		case heart: {
			final Path p = new HeartPath(center, radius, false, HEART_SHAPE.Lovely);
			PathHelper.rotatePath(center.x, center.y, p, Randomizer.getRandomFloat(-90 - 19, -90 + 19));
			return p;
		}
		case heart90: {
			final Path p = new HeartPath(center, radius, false, HEART_SHAPE.Lovely);
			PathHelper.rotatePath(center.x, center.y, p, 90);
			return p;
		}
		case heart270: {
			final Path p = new HeartPath(center, radius, false, HEART_SHAPE.Lovely);
			PathHelper.rotatePath(center.x, center.y, p, 270);
			return p;
		}
		}

	}

}
