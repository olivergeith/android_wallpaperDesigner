package de.geithonline.wallpaperdesigner.bitmapdrawer.raster;

public enum RasterPositioning {
	RANDOM, TOWER, CENTER, INNER, OUTER, TOPMOST, LEFTMOST, BOTTOMMOST, RIGHTMOST, TRISTEP, QUADSTEP, DUO_CENTER, DUO_STEP_INNER_2_OUTER, DUO_STEP_OUTER_2_INNER, TOP_LEFT_2_BOTTOM_RIGHT, TOP_RIGHT_2_BOTTOM_LEFT;

	public static RasterPositioning getEnumForName(final String name) {
		switch (name) {
			default:
			case "Random":
				return RANDOM;

			case "Tower":
				return TOWER;
			case "Center":
				return CENTER;
			case "DuoCenter":
				return DUO_CENTER;
			case "TriStep":
				return TRISTEP;
			case "QuadStep":
				return QUADSTEP;

			case "Inner to Outer":
				return INNER;
			case "Outer to Inner":
				return OUTER;

			case "Top to Bottom":
				return TOPMOST;
			case "Left to Right":
				return LEFTMOST;
			case "Bottom to Top":
				return BOTTOMMOST;
			case "Right to Left":
				return RIGHTMOST;

			case "DuoStep Inner2Outer":
				return DUO_STEP_INNER_2_OUTER;
			case "DuoStep Outer2Inner":
				return DUO_STEP_OUTER_2_INNER;

			case "Top-Left to Bottom-Right":
				return TOP_LEFT_2_BOTTOM_RIGHT;
			case "Top-Right to Bottom-Left":
				return TOP_RIGHT_2_BOTTOM_LEFT;
		}
	}
}
