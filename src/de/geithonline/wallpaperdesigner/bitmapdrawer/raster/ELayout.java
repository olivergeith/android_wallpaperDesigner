package de.geithonline.wallpaperdesigner.bitmapdrawer.raster;

import java.util.Arrays;
import java.util.List;

public enum ELayout {

	RANDOM("Random Layout", true, true, false, false, false, false, Arrays.asList( //
			ELayoutVariant.RANDOM, //
			ELayoutVariant.GEOMETRICAL_DIRECTED, //
			ELayoutVariant.ALTERNATING //
	)), //
	GEOMETRIC_GRID("Geometric Grid", false, true, true, false, false, false, Arrays.asList( //
			ELayoutVariant.RANDOM, //
			ELayoutVariant.LOGICAL_DIRECTED, //
			ELayoutVariant.GEOMETRICAL_DIRECTED, //
			ELayoutVariant.ALTERNATING //
	)), //
	HEX_GRID("Hex Grid", false, true, true, false, false, false, Arrays.asList( //
			ELayoutVariant.RANDOM, //
			ELayoutVariant.LOGICAL_DIRECTED, //
			ELayoutVariant.GEOMETRICAL_DIRECTED, //
			ELayoutVariant.ALTERNATING //
	)), //
	DIAGONAL_GRID("Diagonal Grid", false, true, true, false, false, false, Arrays.asList( //
			ELayoutVariant.RANDOM, //
			ELayoutVariant.LOGICAL_DIRECTED, //
			ELayoutVariant.GEOMETRICAL_DIRECTED, //
			ELayoutVariant.ALTERNATING //
	)), //
	MATERIAL_GRID("Material Grid", false, false, true, false, false, false, Arrays.asList( //
			ELayoutVariant.RANDOM, //
			ELayoutVariant.GEOMETRICAL_DIRECTED, //
			ELayoutVariant.LOGICAL_DIRECTED //
	)), //
	CIRCULAR_GRID("Circular", false, true, true, true, true, false, Arrays.asList( //
			ELayoutVariant.LOGICAL_DIRECTED, //
			ELayoutVariant.GEOMETRICAL_DIRECTED, //
			ELayoutVariant.ALTERNATING, //
			ELayoutVariant.RANDOM //
	)), //
	CIRCULAR_GRID_ADJUSTABLE_CENTER("Circular Adjustable Center", false, true, true, true, true, true, Arrays.asList( //
			ELayoutVariant.LOGICAL_DIRECTED, //
			ELayoutVariant.GEOMETRICAL_DIRECTED, //
			ELayoutVariant.ALTERNATING, //
			ELayoutVariant.RANDOM //
	)), //
	SPIRAL_GRID("Spiral", false, true, true, true, true, false, Arrays.asList( //
			ELayoutVariant.LOGICAL_DIRECTED, //
			ELayoutVariant.GEOMETRICAL_DIRECTED, //
			ELayoutVariant.ALTERNATING, //
			ELayoutVariant.RANDOM //
	)), //
	SPIRAL_GRID_ADJUSTABLE_CENTER("Spiral Adjustable Center", false, true, true, true, true, true, Arrays.asList( //
			ELayoutVariant.LOGICAL_DIRECTED, //
			ELayoutVariant.GEOMETRICAL_DIRECTED, //
			ELayoutVariant.ALTERNATING, //
			ELayoutVariant.RANDOM //
	)), //
	HALF_CIRCLE_GRID("Half Circle", false, true, true, true, true, false, Arrays.asList( //
			ELayoutVariant.LOGICAL_DIRECTED, //
			ELayoutVariant.GEOMETRICAL_DIRECTED, //
			ELayoutVariant.ALTERNATING, //
			ELayoutVariant.RANDOM //
	));

	private String name;
	private final boolean anzahlPatterns;
	private final boolean blurring;
	private final boolean overlap;
	private final boolean counterClockwise;
	private final boolean randomStartWinkel;
	private final boolean adjustableCenter;
	private final List<ELayoutVariant> variants;

	public boolean hasAnzahlPatterns() {
		return anzahlPatterns;
	}

	public boolean hasBlurring() {
		return blurring;
	}

	public boolean hasOverlap() {
		return overlap;
	}

	public boolean hasCounterClockwise() {
		return counterClockwise;
	}

	public boolean hasRandomStartWinkel() {
		return randomStartWinkel;
	}

	public boolean hasAdjustableCenter() {
		return adjustableCenter;
	}

	public List<ELayoutVariant> getEVariants() {
		return variants;
	}

	public CharSequence[] getVariants() {
		final CharSequence[] sequence = new CharSequence[variants.size()];
		for (int i = 0; i < variants.size(); i++) {
			sequence[i] = variants.get(i).getName();
		}
		return sequence;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	private ELayout(//
			final String name, //
			final boolean anzahlPatterns, //
			final boolean blurring, //
			final boolean overlap, //
			final boolean counterClockwise, //
			final boolean randomStartWinkel, //
			final boolean adjustableCenter, //
			final List<ELayoutVariant> variants) {
		this.name = name;
		this.anzahlPatterns = anzahlPatterns;
		this.blurring = blurring;
		this.overlap = overlap;
		this.counterClockwise = counterClockwise;
		this.randomStartWinkel = randomStartWinkel;
		this.adjustableCenter = adjustableCenter;
		this.variants = variants;
	}

	public static ELayout getEnumForName(final String search) {
		for (final ELayout e : ELayout.values()) {
			if (search != null && search.equals(e.getName())) {
				return e;
			}
		}
		return RANDOM;
	}

	public boolean hasVariants() {
		return variants != null;
	}

}
