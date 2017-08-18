package de.geithonline.wallpaperdesigner.settings.specialoptions;

public class CircularMazeOptions {

	public enum MazeType {
		normal, segmented;
		public static MazeType enumForName(final String name) {
			switch (name) {
			default:
			case "Normal":
				return MazeType.normal;
			case "Segmented":
				return MazeType.segmented;
			}
		}
	}

	public MazeType type = MazeType.normal;
	public int anzArcs = 15;
	public int minSweep = 90;
	public int maxSweep = 270;

	public float minThickness = 0.01f;
	public float maxThickness = 0.2f;

	public int minBrightness = -48;
	public int maxBrightness = 48;

}
