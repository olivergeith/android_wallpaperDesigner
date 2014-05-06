package de.geithonline.wallpaperdesigner.utils;

/*  Add this to activity

 private SensorManager mSensorManager;
 private ShakeEventListener mSensorListener;

 public void initSensors() {
 mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
 mSensorListener = new ShakeEventListener();
 mSensorListener.setOnShakeListener(new ShakeEventListener.OnShakeListener() {

 @Override
 public void onShake() {
 generate();
 }
 });
 }

 @Override
 protected void onResume() {
 super.onResume();
 mSensorManager.registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_UI);
 }

 @Override
 protected void onPause() {
 mSensorManager.unregisterListener(mSensorListener);
 super.onPause();
 }


 */

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

/**
 * Listener that detects shake gesture.
 */
public class ShakeEventListener implements SensorEventListener {

	/** Minimum movement force to consider. */
	private static final int MIN_FORCE = 13;

	/**
	 * Minimum times in a shake gesture that the direction of movement needs to
	 * change.
	 */
	private static final int MIN_DIRECTION_CHANGE = 4;

	/** Maximum pause between movements. */
	private static final int MAX_PAUSE_BETHWEEN_DIRECTION_CHANGE = 100;

	/** Maximum allowed time for shake gesture. */
	private static final int MAX_TOTAL_DURATION_OF_SHAKE = 400;

	/** Time when the gesture started. */
	private long mFirstDirectionChangeTime = 0;

	/** Time when the last movement started. */
	private long mLastDirectionChangeTime;

	/** How many movements are considered so far. */
	private int mDirectionChangeCount = 0;

	/** The last x position. */
	private float lastX = 0;

	/** The last y position. */
	private float lastY = 0;

	/** The last z position. */
	private float lastZ = 0;

	/** OnShakeListener that is called when shake is detected. */
	private OnShakeListener mShakeListener;

	/**
	 * Interface for shake gesture.
	 */
	public interface OnShakeListener {

		/**
		 * Called when shake gesture is detected.
		 */
		void onShake();
	}

	public void setOnShakeListener(final OnShakeListener listener) {
		mShakeListener = listener;
	}

	@Override
	public void onSensorChanged(final SensorEvent se) {
		// get sensor data
		final float x = se.values[SensorManager.DATA_X];
		final float y = se.values[SensorManager.DATA_Y];
		final float z = se.values[SensorManager.DATA_Z];

		// calculate movement
		final float totalMovement = Math.abs(x + y + z - lastX - lastY - lastZ);

		if (totalMovement > MIN_FORCE) {

			// get time
			final long now = System.currentTimeMillis();

			// store first movement time
			if (mFirstDirectionChangeTime == 0) {
				mFirstDirectionChangeTime = now;
				mLastDirectionChangeTime = now;
			}

			// check if the last movement was not long ago
			final long lastChangeWasAgo = now - mLastDirectionChangeTime;
			if (lastChangeWasAgo < MAX_PAUSE_BETHWEEN_DIRECTION_CHANGE) {

				// store movement data
				mLastDirectionChangeTime = now;
				mDirectionChangeCount++;

				// store last sensor data
				lastX = x;
				lastY = y;
				lastZ = z;

				// check how many movements are so far
				if (mDirectionChangeCount >= MIN_DIRECTION_CHANGE) {

					// check total duration
					final long totalDuration = now - mFirstDirectionChangeTime;
					if (totalDuration < MAX_TOTAL_DURATION_OF_SHAKE) {
						mShakeListener.onShake();
						resetShakeParameters();
					}
				}

			} else {
				resetShakeParameters();
			}
		}
	}

	/**
	 * Resets the shake parameters to their default values.
	 */
	private void resetShakeParameters() {
		mFirstDirectionChangeTime = 0;
		mDirectionChangeCount = 0;
		mLastDirectionChangeTime = 0;
		lastX = 0;
		lastY = 0;
		lastZ = 0;
	}

	@Override
	public void onAccuracyChanged(final Sensor sensor, final int accuracy) {
	}

}