package edu.orangecoastcollege.cs273.touchgestures;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * The <code>MainActivity</code> implements both the OnGestureListener and the OnDoubleTapListener
 * interfaces.
 * The <code>MainActivity</code> is where all of the gestures take place as well as where
 * all the TextViews are connected to the xml file.
 */
public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {

    private ScrollView gesturesScrollView;

    private TextView gesturesLogTextView;
    private TextView singleTapTextView;
    private TextView doubleTapTextView;
    private TextView longPressTextView;
    private TextView scrollTextView;
    private TextView flingTextView;

    private int singleTaps = 0, doubleTaps = 0, longPresses = 0, scrolls = 0, flings = 0;

    // Member variable to detect gestures
    private GestureDetector mGestureDetector;

    /**
     * The <code>onCreate</code> connects the TextViews with the IDs.
     * It also sets up the GestureDetector.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gesturesLogTextView = (TextView) findViewById(R.id.gesturesLogTextView);
        singleTapTextView = (TextView) findViewById(R.id.singleTapTextView);
        doubleTapTextView = (TextView) findViewById(R.id.doubleTapTextView);
        longPressTextView = (TextView) findViewById(R.id.longPressTextView);
        scrollTextView = (TextView) findViewById(R.id.scrollTextView);
        flingTextView = (TextView) findViewById(R.id.flingTextView);

        gesturesScrollView = (ScrollView) findViewById(R.id.gesturesScrollView);

        mGestureDetector = new GestureDetector(gesturesScrollView.getContext(), this);
    }

    /**
     * Sends the touch event to all the children in ViewGroup:
     * e.g. ScrollView down to TextView
     * @param ev The motion event triggering the touch.
     * @returnTrue if the event was handled, false otherwise.
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        super.dispatchTouchEvent(ev);
        return mGestureDetector.onTouchEvent(ev);
    }

    /**
     * Handles a single-tap gesture. Not part of a double-tap.
     * @param motionEvent The motion event triggering the touch.
     * @return True if the event was handled, false otherwise.
     */
    @Override
    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {

        // Display the message
        gesturesLogTextView.append("\nonSingleTapConfirmed gesture occurred");

        // Increment the counter
        singleTapTextView.setText(String.valueOf(++singleTaps));

        return true;
    }

    /**
     * Responds to a double-tap gesture. Double-tap is a succession of two single-taps.
     * @param motionEvent The motion event triggering the touch.
     * @return True if the event was handled, false otherwise.
     */
    @Override
    public boolean onDoubleTap(MotionEvent motionEvent) {

        // Display the message
        gesturesLogTextView.append("\nonDoubleTap gesture occurred");

        // Increment the counter
        doubleTapTextView.setText(String.valueOf(++doubleTaps));

        return true;
    }

    /**
     * During a double tap, another event occurs (including move).
     * @param motionEvent The motion event triggering the touch.
     * @return True if the event was handled, false otherwise.
     */
    @Override
    public boolean onDoubleTapEvent(MotionEvent motionEvent) {
        return false;
    }

    /**
     * User made initial contact with device.
     * Every gesture begins with onDown.
     * @param motionEvent
     * @return
     */
    @Override
    public boolean onDown(MotionEvent motionEvent) {
        gesturesLogTextView.append("\nonDown gesture occurred");
        return true;
    }

    /**
     * Down event where user does not let go, short duration of time.
     * @param motionEvent
     */
    @Override
    public void onShowPress(MotionEvent motionEvent) {
        gesturesLogTextView.append("\nonShow gesture occurred");

    }

    /**
     * Similar to onSingleTapConfirmed, but it could be part of a double-tap.
     * @param motionEvent The motion event triggering the touch.
     * @return
     */
    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        gesturesLogTextView.append("\nonSingleTapUp gesture occurred");
        return true;
    }

    /**
     * Down event, followed by a press and a lateral movement, without
     * relinquishing contact with device.
     * @param motionEvent The event where scroll started.
     * @param motionEvent1 The event where scroll stopped.
     * @param distanceX The distance in X direction (pixels).
     * @param distanceY The distance in Y direction (pixels).
     * @return
     */
    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float distanceX, float distanceY) {
        gesturesLogTextView.append("\nonScroll gesture occurred, distanceX = " + distanceX + ", distanceY = " + distanceY);
        scrollTextView.setText(String.valueOf(++scrolls));
        return true;
    }

    /**
     * Down event, followed by a long hold.
     * @param motionEvent The event where the press starts.
     */
    @Override
    public void onLongPress(MotionEvent motionEvent) {

        gesturesLogTextView.append("\nonLongPress gesture occurred");

        longPressTextView.setText(String.valueOf(++longPresses));

    }

    /**
     * Similar to scroll, with faster velocity and user releases
     * @param motionEvent
     * @param motionEvent1
     * @param v Velocity in the x-direction (pixels/second).
     * @param v1 Velocity in the y-direction (pixels/second).
     * @return
     */
    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        gesturesLogTextView.append("\nonFling gesture occurred, velocityX = " + v + ", velocityY = " + v1);
        flingTextView.setText(String.valueOf(++flings));
        return true;
    }

    /**
     * This clears the log view as well as setting all the
     * variables back to 0 and setting the TextView to zero.
     * @param v The button
     */
    public void clearAll(View v)
    {
        gesturesLogTextView.setText("");

        // Set all counters to 0
        singleTaps = 0;
        doubleTaps = 0;
        longPresses = 0;
        scrolls = 0;
        flings = 0;

        // Set all TextView(s) to 0
        singleTapTextView.setText(R.string.zero);
        doubleTapTextView.setText(R.string.zero);
        longPressTextView.setText(R.string.zero);
        scrollTextView.setText(R.string.zero);
        flingTextView.setText(R.string.zero);
    }
}
