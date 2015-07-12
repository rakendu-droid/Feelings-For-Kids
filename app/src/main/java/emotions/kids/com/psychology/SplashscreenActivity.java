package emotions.kids.com.psychology;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import emotions.kids.com.psychology.util.SystemUiHider;


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 * @see SystemUiHider
 */
public class SplashscreenActivity extends Activity implements Animation.AnimationListener {
    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     *
     */
    ImageView balloon1,balloon2,balloon3;
    TextView text1,text2,text3,title;


    Animation fade,move,txt1,bl1,bl2,bl3,txt2,titleAnim,fadeOut;
    int count=0;
    private static final boolean AUTO_HIDE = true;

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    /**
     * If set, will toggle the system UI visibility upon interaction. Otherwise,
     * will show the system UI visibility upon interaction.
     */
    private static final boolean TOGGLE_ON_CLICK = true;

    /**
     * The flags to pass to {@link SystemUiHider#getInstance}.
     */
    private static final int HIDER_FLAGS = SystemUiHider.FLAG_HIDE_NAVIGATION;

    /**
     * The instance of the {@link SystemUiHider} for this activity.
     */
    private SystemUiHider mSystemUiHider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);

        balloon1 =(ImageView)findViewById(R.id.b1);
        balloon2 =(ImageView)findViewById(R.id.b2);
        balloon3 =(ImageView)findViewById(R.id.b3);

        text1 =(TextView)findViewById(R.id.t1);
        text2 =(TextView)findViewById(R.id.t2);
        text3 =(TextView)findViewById(R.id.t3);
        title =(TextView)findViewById(R.id.splash_title);


        fade = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.text);
        fade.setAnimationListener(this);

        fadeOut = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_out);
        fadeOut.setAnimationListener(this);

        txt1 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.text1);
        txt1.setAnimationListener(this);

        titleAnim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.title_anim);
        titleAnim.setAnimationListener(this);


        move = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.baloon);
        move.setAnimationListener(this);

        txt2 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.text2);
        txt2.setAnimationListener(this);

        bl1 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.baloon);
        bl1.setAnimationListener(this);

        bl2 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.balloon2);
        bl2.setAnimationListener(this);

        bl3 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.balloon3);
        bl3.setAnimationListener(this);






//        final View controlsView = findViewById(R.id.fullscreen_content_controls);
//        final View contentView = findViewById(R.id.fullscreen_content);
//
//        // Set up an instance of SystemUiHider to control the system UI for
//        // this activity.
//        mSystemUiHider = SystemUiHider.getInstance(this, contentView, HIDER_FLAGS);
//        mSystemUiHider.setup();
//        mSystemUiHider
//                .setOnVisibilityChangeListener(new SystemUiHider.OnVisibilityChangeListener() {
//                    // Cached values.
//                    int mControlsHeight;
//                    int mShortAnimTime;
//
//                    @Override
//                    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
//                    public void onVisibilityChange(boolean visible) {
//                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
//                            // If the ViewPropertyAnimator API is available
//                            // (Honeycomb MR2 and later), use it to animate the
//                            // in-layout UI controls at the bottom of the
//                            // screen.
//                            if (mControlsHeight == 0) {
//                                mControlsHeight = controlsView.getHeight();
//                            }
//                            if (mShortAnimTime == 0) {
//                                mShortAnimTime = getResources().getInteger(
//                                        android.R.integer.config_shortAnimTime);
//                            }
//                            controlsView.animate()
//                                    .translationY(visible ? 0 : mControlsHeight)
//                                    .setDuration(mShortAnimTime);
//                        } else {
//                            // If the ViewPropertyAnimator APIs aren't
//                            // available, simply show or hide the in-layout UI
//                            // controls.
//                            controlsView.setVisibility(visible ? View.VISIBLE : View.GONE);
//                        }
//
//                        if (visible && AUTO_HIDE) {
//                            // Schedule a hide().
//                            delayedHide(AUTO_HIDE_DELAY_MILLIS);
//                        }
//                    }
//                });
//
//        // Set up the user interaction to manually show or hide the system UI.
//        contentView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (TOGGLE_ON_CLICK) {
//                    mSystemUiHider.toggle();
//                } else {
//                    mSystemUiHider.show();
//                }
//            }
//        });
//
//        // Upon interacting with UI controls, delay any scheduled hide()
//        // operations to prevent the jarring behavior of controls going away
//        // while interacting with the UI.
//        findViewById(R.id.dummy_button).setOnTouchListener(mDelayHideTouchListener);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);


        balloon1.startAnimation(bl1);
        balloon2.startAnimation(bl2);
        balloon3.startAnimation(bl3);



        text1.startAnimation(fade);
        text2.startAnimation(txt1);
        text2.startAnimation(txt2);

        text1.setVisibility(View.VISIBLE);
        text2.setVisibility(View.VISIBLE);



        title.startAnimation(titleAnim);

        //text3.setVisibility(View.VISIBLE);





        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        //delayedHide(100);

    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

        if(animation == bl3)
            text3.setVisibility(View.VISIBLE);
        if(animation == txt2)
        {

            text1.startAnimation(fadeOut);
            text2.startAnimation(fadeOut);
            text3.startAnimation(fadeOut);

            text1.setVisibility(View.GONE);
            text2.setVisibility(View.GONE);
            text3.setVisibility(View.GONE);
            title.setVisibility(View.VISIBLE);

        }
//        if(animation == move) {
//
//
////            switch (count) {
////                case 0:
//                    //balloon1.setVisibility(View.GONE);
//                    //balloon2.startAnimation(move);
//                    text1.setVisibility(View.VISIBLE);
//                    text1.startAnimation(fade);
//                    count = 1;
////                    break;
////                case 1:
//                    //balloon2.setVisibility(View.GONE);
//                    text2.setVisibility(View.VISIBLE);
//                   // balloon3.startAnimation(move);
//                    text2.startAnimation(fade);
//                    count = 2;
////                    break;
////                case 2:
//                    text3.setVisibility(View.VISIBLE);
//                    text3.startAnimation(fade);
//                    count = 3;
////                    break;
////                default:
////                    break;
////            }
//        }
//        else if(animation == fade){
//            if(count !=4)
//            {
//                count =4;
//                text1.setVisibility(View.GONE);
//                text2.setVisibility(View.GONE);
//                text3.setVisibility(View.GONE);
//
//                text1.startAnimation(fadeOut);
//                text2.startAnimation(fadeOut);
//                text3.startAnimation(fadeOut);
//
//                title.startAnimation(fade);
//                title.setVisibility(View.VISIBLE);
//            }
//        }


    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }


    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */
//    View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
//        @Override
//        public boolean onTouch(View view, MotionEvent motionEvent) {
//            if (AUTO_HIDE) {
//                delayedHide(AUTO_HIDE_DELAY_MILLIS);
//            }
//            return false;
//        }
//    };
//
//    Handler mHideHandler = new Handler();
//    Runnable mHideRunnable = new Runnable() {
//        @Override
//        public void run() {
//            mSystemUiHider.hide();
//        }
//    };
//
//    /**
//     * Schedules a call to hide() in [delay] milliseconds, canceling any
//     * previously scheduled calls.
//     */
//    private void delayedHide(int delayMillis) {
//        mHideHandler.removeCallbacks(mHideRunnable);
//        mHideHandler.postDelayed(mHideRunnable, delayMillis);
//    }
}
