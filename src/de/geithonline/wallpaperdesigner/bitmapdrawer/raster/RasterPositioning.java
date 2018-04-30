package de.geithonline.wallpaperdesigner.bitmapdrawer.raster;

public enum RasterPositioning {
	RANDOM("Random"), //
	INNER("Inner to Outer"), //
	OUTER("Outer to Inner"), //
	TOPMOST("Top to Bottom"), //
	LEFTMOST("Left to Right"), //
	BOTTOMMOST("Bottom to Top"), //
	RIGHTMOST("Right to Left"), //
	TOWER("Tower"), //
	CENTER("Center"), //
	TRISTEP("TriStep"), //
	QUADSTEP("QuadStep"), //
	DUO_CENTER("DuoCenter"), //
	DUO_STEP_INNER_2_OUTER("DuoStep Inner2Outer"), //
	DUO_STEP_OUTER_2_INNER("DuoStep Outer2Inner"), //
	TOP_LEFT_2_BOTTOM_RIGHT("Top-L to Bottom-R"), //
	TOP_RIGHT_2_BOTTOM_LEFT("Top-R to Bottom-L"), //
	ALTERNATING("Alternating"), //
	ALTERNATING_TOP_LEFT_BOTTOM_RIGHT("Altern. Top-L / Bottom-R"), //
	ALTERNATING_TOP_RIGHT_BOTTOM_LEFT("Altern. Top-R / Bottom-L");

	private String name;

	public String getName() {
		return name;
	}

	private RasterPositioning(final String name) {
		this.name = name;
	}

	public static RasterPositioning getEnumForName(final String search) {
		for (final RasterPositioning e : RasterPositioning.values()) {
			if (search != null && search.equals(e.getName())) {
				return e;
			}
		}
		return RANDOM;
	}

}
