package de.geithonline.wallpaperdesigner.settings.specialoptions;

import de.geithonline.wallpaperdesigner.shapes.composed.ComposedPathColoringType;

public class CircularMazeOptions {

	public int anzArcs = 15;
	public int minSweep = 90;
	public int maxSweep = 270;

	public float minThickness = 0.01f;
	public float maxThickness = 0.2f;

	public int minBrightness = -48;
	public int maxBrightness = 48;

	public int minSegments = 1;
	public int maxSegments = 1;

	public ComposedPathColoringType coloringType = ComposedPathColoringType.normal;
	public int outlineShift = 0;

}
