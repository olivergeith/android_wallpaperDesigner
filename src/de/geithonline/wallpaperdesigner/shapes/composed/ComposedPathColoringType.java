package de.geithonline.wallpaperdesigner.shapes.composed;

public enum ComposedPathColoringType {
	normal, increasing, random, randomcreasing, decreasing;

	public static ComposedPathColoringType enumForName(final String name) {
		switch (name) {
		default:
		case "Normal":
			return ComposedPathColoringType.normal;
		case "Increasing":
			return ComposedPathColoringType.increasing;
		case "Decreasing":
			return ComposedPathColoringType.decreasing;
		case "Random In- or Decreasing":
			return ComposedPathColoringType.randomcreasing;
		case "Random":
			return ComposedPathColoringType.random;
		}
	}

}
