package de.geithonline.wallpaperdesigner.bitmapdrawer.raster;

public enum RasterPositioning {
	RANDOM, BOOK, BOOK_REVERSE, TOWER, CENTER, INNER, OUTER, TOPMOST, LEFTMOST, BOTTOMMOST, RIGHTMOST, TRISTEP, QUADSTEP, DUO_CENTER;

	public static RasterPositioning getEnumForName(final String name) {
		switch (name) {
		default:
		case "Random":
			return RANDOM;
		case "Book":
			return BOOK;
		case "Book Reverse":
			return BOOK_REVERSE;
		case "Tower":
			return TOWER;
		case "Center":
			return CENTER;
		case "DuoCenter":
			return DUO_CENTER;
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
		case "TriStep":
			return TRISTEP;
		case "QuadStep":
			return QUADSTEP;
		}

	}
}
