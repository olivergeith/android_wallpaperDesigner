
package de.geithonline.wallpaperdesigner.shapes.composed;

public enum ESinusObjectsSizingType {
    random, decreasing, connected, decreasing_withbigEnd, increasing;

    public static ESinusObjectsSizingType enumForName(final String name) {
        switch (name) {
            case "Random":
                return ESinusObjectsSizingType.random;
            default:
            case "Decreasing":
                return ESinusObjectsSizingType.decreasing;
            case "Connected":
                return ESinusObjectsSizingType.connected;
            case "Decreasing with big End":
                return ESinusObjectsSizingType.decreasing_withbigEnd;
            case "Increasing":
                return ESinusObjectsSizingType.increasing;
        }
    }

}
