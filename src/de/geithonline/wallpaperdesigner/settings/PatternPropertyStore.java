package de.geithonline.wallpaperdesigner.settings;

import java.util.HashMap;
import java.util.Map;

public class PatternPropertyStore {

	private static final Map<String, PatternProperties> patternProperties = new HashMap<String, PatternProperties>();

	static {
		// new PatternProperties(outlineOption, randomrotateOption, textOption, filledOption, leafOption, glossyOption)
		patternProperties.put("Anchor", new PatternProperties(true, true, false, false, false, false));
		patternProperties.put("Android", new PatternProperties(true, true, false, false, false, false));
		patternProperties.put("Bubbles", new PatternProperties(true, false, false, false, false, true));
		patternProperties.put("Clouds", new PatternProperties(true, true, false, false, false, false));
		patternProperties.put("Crop Circles", new PatternProperties(true, true, false, false, false, false));
		patternProperties.put("Custom Text", new PatternProperties(false, false, true, false, false, false));
		patternProperties.put("Dandelion", new PatternProperties(true, false, false, false, false, false));
		patternProperties.put("Deathstars", new PatternProperties(true, true, false, false, false, false));

		patternProperties.put("Fish V1", new PatternProperties(true, true, false, false, false, false));
		patternProperties.put("Fish V2", new PatternProperties(true, true, false, false, false, false));
		patternProperties.put("Fish Mixed", new PatternProperties(true, true, false, false, false, false));

		patternProperties.put("Flowers V1", new PatternProperties(true, true, false, false, true, false));
		patternProperties.put("Flowers V2", new PatternProperties(true, true, false, true, false, false));
		patternProperties.put("Flowers V3", new PatternProperties(true, true, false, true, true, false));
		patternProperties.put("Flowers V4", new PatternProperties(true, true, false, true, true, false));
		patternProperties.put("Flowers V5", new PatternProperties(true, true, false, true, true, false));
		patternProperties.put("Flowers Mixed", new PatternProperties(true, true, false, true, true, false));

		patternProperties.put("Gears", new PatternProperties(true, false, false, true, false, false));

		patternProperties.put("Hearts", new PatternProperties(true, true, false, false, false, true));
		patternProperties.put("Hedgehog", new PatternProperties(true, true, false, true, false, false));
		patternProperties.put("Hexagon", new PatternProperties(true, true, false, true, false, false));
		patternProperties.put("Lemons", new PatternProperties(true, true, false, false, false, false));
		patternProperties.put("Letters", new PatternProperties(true, false, false, false, false, false));
		patternProperties.put("Lighthouse", new PatternProperties(true, true, false, false, false, false));

		patternProperties.put("Mandala V1", new PatternProperties(true, true, false, false, true, false));
		patternProperties.put("Mandala V2", new PatternProperties(true, true, false, false, false, false));
		patternProperties.put("Mandala V3", new PatternProperties(true, true, false, false, true, false));
		patternProperties.put("Mandala V4", new PatternProperties(true, true, false, false, false, false));

		patternProperties.put("Marina", new PatternProperties(true, true, false, false, false, false));

		patternProperties.put("Numbers", new PatternProperties(false, false, true, false, false, false));
		patternProperties.put("Octagon", new PatternProperties(true, true, false, true, false, false));
		patternProperties.put("PacMan", new PatternProperties(true, true, false, true, false, true));
		patternProperties.put("Pentagon", new PatternProperties(true, true, false, true, false, false));
		patternProperties.put("Pillows", new PatternProperties(true, true, false, true, false, false));

		patternProperties.put("Rectangles", new PatternProperties(true, true, false, false, false, false));
		patternProperties.put("Rectangles (rounded)", new PatternProperties(true, true, false, false, false, false));

		patternProperties.put("Rings", new PatternProperties(true, false, false, true, false, false));
		patternProperties.put("Rocket", new PatternProperties(true, true, false, true, false, false));
		patternProperties.put("Roses", new PatternProperties(true, false, false, false, false, false));

		patternProperties.put("Sailboat V1", new PatternProperties(true, true, false, false, false, false));
		patternProperties.put("Sailboat V2", new PatternProperties(true, true, false, false, false, false));

		patternProperties.put("Saw", new PatternProperties(true, false, false, true, false, false));

		patternProperties.put("Shells V1", new PatternProperties(true, true, false, false, true, false));
		patternProperties.put("Shells V2", new PatternProperties(true, true, false, true, true, false));
		patternProperties.put("Shells V3", new PatternProperties(true, true, false, false, true, false));
		patternProperties.put("Shells V4", new PatternProperties(true, true, false, false, true, false));
		patternProperties.put("Shells V5", new PatternProperties(true, true, false, false, true, false));
		patternProperties.put("Shells V6", new PatternProperties(true, true, false, false, true, false));
		patternProperties.put("Shells Mixed", new PatternProperties(true, true, false, false, true, false));
		patternProperties.put("Skulls", new PatternProperties(true, true, false, false, false, false));

		patternProperties.put("Smiley", new PatternProperties(true, true, false, true, false, true));
		patternProperties.put("Space", new PatternProperties(true, true, false, true, false, false));
		patternProperties.put("Stars", new PatternProperties(true, true, false, false, true, true));
		patternProperties.put("Star Circles", new PatternProperties(true, true, false, true, false, false));
		patternProperties.put("Sun", new PatternProperties(true, true, false, true, false, false));

		patternProperties.put("Squares", new PatternProperties(true, true, false, true, false, false));

		patternProperties.put("Triangles", new PatternProperties(true, true, false, true, false, false));

		patternProperties.put("Ufo V1", new PatternProperties(true, true, false, true, false, false));
		patternProperties.put("Ufo V2", new PatternProperties(true, true, false, true, false, false));

		patternProperties.put("Virus Attack V1", new PatternProperties(true, false, false, false, false, false));
		patternProperties.put("Virus Attack V2", new PatternProperties(true, false, false, false, false, false));
		patternProperties.put("Virus Attack V3", new PatternProperties(true, false, false, true, false, false));
		patternProperties.put("Virus Attack V4", new PatternProperties(true, false, false, false, false, false));
		patternProperties.put("Virus Attack V5", new PatternProperties(true, false, false, false, false, false));
		patternProperties.put("Virus Attack V6", new PatternProperties(true, true, false, false, false, false));
		patternProperties.put("Viruses Mixed", new PatternProperties(true, true, false, true, false, false));

		patternProperties.put("XmasTrees", new PatternProperties(true, true, false, true, false, false));

	}

	public static boolean hasPatternTextOption(final String pattern) {
		final PatternProperties p = patternProperties.get(pattern);
		if (p == null) {
			return false;
		}
		return p.hasTextOption();
	}

	public static boolean hasPatternGlossyEffect(final String pattern) {
		final PatternProperties p = patternProperties.get(pattern);
		if (p == null) {
			return false;
		}
		return p.hasGlossyOption();
	}

	public static boolean hasNumberOfLeafsOption(final String pattern) {
		final PatternProperties p = patternProperties.get(pattern);
		if (p == null) {
			return false;
		}
		return p.hasLeafOption();
	}

	public static boolean hasPatternFilledOption(final String pattern) {
		final PatternProperties p = patternProperties.get(pattern);
		if (p == null) {
			return false;
		}
		return p.hasFilledOption();
	}

	public static boolean hasPatternRandomRotate(final String pattern) {
		final PatternProperties p = patternProperties.get(pattern);
		if (p == null) {
			return false;
		}
		return p.hasRandomrotateOption();
	}

	public static boolean hasPatternOutlineEffect(final String pattern) {
		final PatternProperties p = patternProperties.get(pattern);
		if (p == null) {
			return false;
		}
		return p.hasOutlineOption();
	}

	public static boolean hasPatternSpecialSettings(final String pattern) {
		return PatternPropertyStore.hasPatternGlossyEffect(pattern)//
				|| PatternPropertyStore.hasPatternOutlineEffect(pattern)//
				|| PatternPropertyStore.hasPatternRandomRotate(pattern)//
				|| PatternPropertyStore.hasPatternTextOption(pattern)//
				|| PatternPropertyStore.hasNumberOfLeafsOption(pattern)//
				|| PatternPropertyStore.hasPatternFilledOption(pattern);
	}
}
