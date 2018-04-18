
package de.geithonline.wallpaperdesigner.tasks;

// ##########################################################
public interface AsyncTaskWithProgress {

    public void settingMax(final int max);

    public void settingProgress(final int p, final String message);

    public boolean isCancelled();

}
