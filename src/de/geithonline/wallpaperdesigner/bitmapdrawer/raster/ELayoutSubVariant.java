package de.geithonline.wallpaperdesigner.bitmapdrawer.raster;

public enum ELayoutSubVariant {
	NONE("NONE"), //

	// Logical
	INNER("Inner to Outer"), //
	OUTER("Outer to Inner"), //
	DUO_STEP_INNER_2_OUTER("DuoStep Inner2Outer"), //
	DUO_STEP_OUTER_2_INNER("DuoStep Outer2Inner"), //
	TOWER("Tower"), //
	CENTER("Center"), //
	TRISTEP("TriStep"), //
	QUADSTEP("QuadStep"), //
	DUO_CENTER("DuoCenter"), //

	// geometrical
	ALL_CORNERS("All Corners"), //
	ALL_SIDES("All Sides"), //
	LEFT_RIGHT("Left - Right"), //
	RIGHT_LEFT("Right - Left"), //
	TOP_BOTTOM("Top - Bottom"), //
	BOTTOM_TOP("Bottom - TOP"), //
	TOP_LEFT_BOTTOM_RIGHT("TopLeft - BottomRight"), //
	TOP_RIGHT_BOTTOM_LEFT("TopRight - BottomLeft"), //
	TOP_RIGHT_TOP_LEFT("TopRight - TopLeft"), //
	BOTTOM_RIGHT_BOTTOM_LEFT("BottomRight - BottomLeft");//

	private String name;

	public String getName() {
		return name;
	}

	private ELayoutSubVariant(final String name) {
		this.name = name;
	}

	public static ELayoutSubVariant getEnumForName(final String search) {
		for (final ELayoutSubVariant e : ELayoutSubVariant.values()) {
			if (search != null && search.equals(e.getName())) {
				return e;
			}
		}
		return NONE;
	}

}
