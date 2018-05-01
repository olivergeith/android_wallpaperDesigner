// package de.geithonline.wallpaperdesigner.bitmapdrawer.raster;
//
// import java.util.List;
//
// public class LayoutProperties {
// private final boolean anzahlPatterns;
// private final boolean blurring;
// private final boolean overlap;
// private final boolean counterClockwise;
//
// private final List<ELayoutVariant> variants;
// private final boolean randomStartWinkel;
// private final boolean adjustableCenter;
//
// public LayoutProperties(final boolean anzahlPatterns, final boolean blurring, final boolean overlap, final boolean counterClockwise,
// final boolean randomStartWinkel, final List<ELayoutVariant> variants) {
// super();
// this.anzahlPatterns = anzahlPatterns;
// this.blurring = blurring;
// this.overlap = overlap;
// this.counterClockwise = counterClockwise;
// this.variants = variants;
// this.randomStartWinkel = randomStartWinkel;
// adjustableCenter = false;
// }
//
// public LayoutProperties(final boolean anzahlPatterns, final boolean blurring, final boolean overlap, final boolean counterClockwise,
// final boolean randomStartWinkel, final boolean adjustableCenter, final List<ELayoutVariant> variants) {
// super();
// this.anzahlPatterns = anzahlPatterns;
// this.blurring = blurring;
// this.overlap = overlap;
// this.counterClockwise = counterClockwise;
// this.adjustableCenter = adjustableCenter;
// this.variants = variants;
// this.randomStartWinkel = randomStartWinkel;
// }
//
// public boolean hasAnzahlPatterns() {
// return anzahlPatterns;
// }
//
// public boolean hasAdjustableCenter() {
// return adjustableCenter;
// }
//
// public boolean hasBlurring() {
// return blurring;
// }
//
// public boolean hasOverlap() {
// return overlap;
// }
//
// public boolean hasCounterClockwise() {
// return counterClockwise;
// }
//
// public boolean hasVariants() {
// return variants != null;
// }
//
// public CharSequence[] getVariants() {
// final CharSequence[] sequence = new CharSequence[variants.size()];
// for (int i = 0; i < variants.size(); i++) {
// sequence[i] = variants.get(i).getName();
// }
// return sequence;
// }
//
// public List<ELayoutVariant> getEVariants() {
// return variants;
// }
//
// public boolean hasRandomStartWinkel() {
// return randomStartWinkel;
// }
//
// }
