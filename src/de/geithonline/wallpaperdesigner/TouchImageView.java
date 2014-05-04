package de.geithonline.wallpaperdesigner;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

public class TouchImageView extends ImageView implements OnTouchListener {

	Matrix matrix = new Matrix();

	// We can be in one of these 3 states
	static final int NONE = 0;
	static final int DRAG = 1;
	static final int ZOOM = 2;
	int mode = NONE;

	// Remember some things for zooming
	PointF last = new PointF();
	PointF start = new PointF();
	float minScale = 1f;
	float maxScale = 3f;
	float[] m;

	float redundantXSpace, redundantYSpace, origRedundantXSpace, origRedundantYSpace;;

	float width, height;
	static final int CLICK = 3;
	static final float SAVE_SCALE = 1f;
	float saveScale = SAVE_SCALE;

	float right, bottom, origWidth, origHeight, bmWidth, bmHeight, origScale, origBottom, origRight;

	ScaleGestureDetector mScaleDetector;
	GestureDetector mGestureDetector;

	Context context;

	public TouchImageView(final Context context, final AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public TouchImageView(final Context context) {
		super(context);
		init(context);
	}

	public void init(final Context context) {
		super.setClickable(true);
		this.context = context;
		mScaleDetector = new ScaleGestureDetector(context, new ScaleListener());

		matrix.setTranslate(1f, 1f);
		m = new float[9];
		setImageMatrix(matrix);
		setScaleType(ScaleType.MATRIX);

		setOnTouchListener(this);

		mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
			@Override
			public boolean onDoubleTapEvent(final MotionEvent e) {
				return true;
			}
		});
	}

	@Override
	public boolean onTouch(final View v, final MotionEvent event) {

		final boolean onDoubleTapEvent = mGestureDetector.onTouchEvent(event);
		if (onDoubleTapEvent) {
			fit2Screen();
			return true;
		}

		mScaleDetector.onTouchEvent(event);

		matrix.getValues(m);
		final float x = m[Matrix.MTRANS_X];
		final float y = m[Matrix.MTRANS_Y];
		final PointF curr = new PointF(event.getX(), event.getY());

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			last.set(event.getX(), event.getY());
			start.set(last);
			mode = DRAG;
			break;
		case MotionEvent.ACTION_MOVE:
			if (mode == DRAG) {
				float deltaX = curr.x - last.x;
				float deltaY = curr.y - last.y;
				final float scaleWidth = Math.round(origWidth * saveScale);
				final float scaleHeight = Math.round(origHeight * saveScale);
				if (scaleWidth < width) {
					deltaX = 0;
					if (y + deltaY > 0) {
						deltaY = -y;
					} else if (y + deltaY < -bottom) {
						deltaY = -(y + bottom);
					}
				} else if (scaleHeight < height) {
					deltaY = 0;
					if (x + deltaX > 0) {
						deltaX = -x;
					} else if (x + deltaX < -right) {
						deltaX = -(x + right);
					}
				} else {
					if (x + deltaX > 0) {
						deltaX = -x;
					} else if (x + deltaX < -right) {
						deltaX = -(x + right);
					}

					if (y + deltaY > 0) {
						deltaY = -y;
					} else if (y + deltaY < -bottom) {
						deltaY = -(y + bottom);
					}
				}
				matrix.postTranslate(deltaX, deltaY);
				last.set(curr.x, curr.y);
			}
			break;

		case MotionEvent.ACTION_UP:
			mode = NONE;
			final int xDiff = (int) Math.abs(curr.x - start.x);
			final int yDiff = (int) Math.abs(curr.y - start.y);
			if (xDiff < CLICK && yDiff < CLICK) {
				performClick();
			}
			break;

		case MotionEvent.ACTION_POINTER_UP:
			mode = NONE;
			break;
		}

		setImageMatrix(matrix);
		invalidate();

		return true; // indicate event was handled
	}

	public void fit2Screen() {
		// Reset Image to original scale values
		mode = NONE;
		bottom = origBottom;
		right = origRight;
		last = new PointF();
		start = new PointF();
		m = new float[9];
		saveScale = SAVE_SCALE;
		matrix = new Matrix();
		matrix.setScale(origScale, origScale);
		matrix.postTranslate(origRedundantXSpace, origRedundantYSpace);
		setImageMatrix(matrix);
		invalidate();
	}

	@Override
	public void setImageBitmap(final Bitmap bm) {
		super.setImageBitmap(bm);
		bmWidth = bm.getWidth();
		bmHeight = bm.getHeight();
	}

	public void setMaxZoom(final float x) {
		maxScale = x;
	}

	private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
		@Override
		public boolean onScaleBegin(final ScaleGestureDetector detector) {
			mode = ZOOM;
			return true;
		}

		@Override
		public boolean onScale(final ScaleGestureDetector detector) {
			float mScaleFactor = (float) Math.min(Math.max(.95f, detector.getScaleFactor()), 1.05);
			final float origScale = saveScale;
			saveScale *= mScaleFactor;
			if (saveScale > maxScale) {
				saveScale = maxScale;
				mScaleFactor = maxScale / origScale;
			} else if (saveScale < minScale) {
				saveScale = minScale;
				mScaleFactor = minScale / origScale;
			}
			right = width * saveScale - width - (2 * redundantXSpace * saveScale);
			bottom = height * saveScale - height - (2 * redundantYSpace * saveScale);
			if (origWidth * saveScale <= width || origHeight * saveScale <= height) {
				matrix.postScale(mScaleFactor, mScaleFactor, width / 2, height / 2);
				if (mScaleFactor < 1) {
					matrix.getValues(m);
					final float x = m[Matrix.MTRANS_X];
					final float y = m[Matrix.MTRANS_Y];
					if (mScaleFactor < 1) {
						if (Math.round(origWidth * saveScale) < width) {
							if (y < -bottom) {
								matrix.postTranslate(0, -(y + bottom));
							} else if (y > 0) {
								matrix.postTranslate(0, -y);
							}
						} else {
							if (x < -right) {
								matrix.postTranslate(-(x + right), 0);
							} else if (x > 0) {
								matrix.postTranslate(-x, 0);
							}
						}
					}
				}
			} else {
				matrix.postScale(mScaleFactor, mScaleFactor, detector.getFocusX(), detector.getFocusY());
				matrix.getValues(m);
				final float x = m[Matrix.MTRANS_X];
				final float y = m[Matrix.MTRANS_Y];
				if (mScaleFactor < 1) {
					if (x < -right) {
						matrix.postTranslate(-(x + right), 0);
					} else if (x > 0) {
						matrix.postTranslate(-x, 0);
					}
					if (y < -bottom) {
						matrix.postTranslate(0, -(y + bottom));
					} else if (y > 0) {
						matrix.postTranslate(0, -y);
					}
				}
			}
			return true;

		}
	}

	@Override
	protected void onMeasure(final int widthMeasureSpec, final int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		width = MeasureSpec.getSize(widthMeasureSpec);
		height = MeasureSpec.getSize(heightMeasureSpec);
		// Fit to screen.
		float scale;
		final float scaleX = width / bmWidth;
		final float scaleY = height / bmHeight;
		scale = Math.min(scaleX, scaleY);
		matrix.setScale(scale, scale);
		setImageMatrix(matrix);
		saveScale = SAVE_SCALE;
		origScale = scale;

		// Center the image
		redundantYSpace = height - (scale * bmHeight);
		redundantXSpace = width - (scale * bmWidth);
		redundantYSpace /= 2;
		redundantXSpace /= 2;

		origRedundantXSpace = redundantXSpace;
		origRedundantYSpace = redundantYSpace;

		matrix.postTranslate(redundantXSpace, redundantYSpace);

		origWidth = width - 2 * redundantXSpace;
		origHeight = height - 2 * redundantYSpace;
		right = width * saveScale - width - (2 * redundantXSpace * saveScale);
		bottom = height * saveScale - height - (2 * redundantYSpace * saveScale);
		origRight = right;
		origBottom = bottom;
		setImageMatrix(matrix);
	}

}