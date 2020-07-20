package com.example.boomplay.Activities;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.PixelFormat;
import android.graphics.SurfaceTexture;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GestureDetectorCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.boomplay.R;
import com.example.boomplay.Utils.HDVPFinalConstant;
import com.example.boomplay.Utils.HDVPMyResizeSurfaceView;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static android.provider.Settings.ACTION_MANAGE_OVERLAY_PERMISSION;
import static android.provider.Settings.canDrawOverlays;

public class HDVPVideoDetailActivityFliper extends AppCompatActivity
        {
    public static final String Broadcast_PLAY_NEW_AUDIO = "com.video.android.playerapp.PlayNewAudio";
    boolean serviceBoundVideo = false;
    private ScaleGestureDetector mScaleGestureDetector;
    private int video_column_index;
    private String mScreenSize = HDVPFinalConstant.FinalConstant_FIT_SCREEN;
    RelativeLayout relativbAck;
    AudioManager audioManager;
    String gestvalue;
    ArrayList<String> videoActivitySongsList;
    float volume;
    Boolean boolnot;
    static String[] thumbColumns = {MediaStore.Video.Thumbnails.DATA, MediaStore.Video.Thumbnails.VIDEO_ID};
    RecyclerView recyclerView;
    int videoIndex;
    private String filename = "", title = "", albu = "", artist = "";
    private int id;
    Integer s;
    private ImageButton orientation, next, prev, play_pause, locker, videoSizeChanger;
    private int songPosn = 0;
    int mProgress = 0;
    private SeekBar seekbar, seekForRev, volumeControl, brightcontrol;
    public static int prog;
    private TextView ablumVideo, sizeChangerText, current, runingduration, ontouchShow, seekTouchDurationTotal, voltouchshow, brighttouchshow;
    private long dura, curr;
    ImageButton share, openlist, background, floating, vol, ratio;
    //    ImageView three60;
    float perc;
    LinearLayout brightShowLayout, volShowLayout, seekTouchLayout;
    private Toolbar toolbar;
    int brightprogress;
    SharedPreferences mSharedPrefs = null;
    int backprogress1;
    //    int threeprogress;
    private boolean true1 = false;
    String true2 = "", true360two = "";
    Boolean true360 = false;
    String finaltrue;
    private int APP_PERMISSION_REQUEST = 1220;
    private boolean isShowing = false;
    LinearLayout controller;
    private Handler mHandler;
    private int mInterval;
    private ImageView brightupper, volupper, seekupper;
    private boolean scrollstate = true;
    private static final String DEBUG_TAG = "Gestures_";
    private GestureDetectorCompat mDetector;
    private int heightdp;
    private int volprogess = 50;
    private int widthdp;
    float delx, dely, deltaX, deltaY, halfwidth, halfheight, delx1, lastY;
    int checkin = 0;
    public boolean mIsScrolling = true;
    boolean isLock = false;
    MediaPlayer mMediaPlayer;
    private int MAX_VOLUME = 100;
    private HDVPMyResizeSurfaceView textureView;
    android.view.ViewGroup.LayoutParams lp;
    private int screenWidth, screenHeight;
    private int clickCount;
    private boolean showButtons;
    private boolean showNoti = false;
    private AlertDialog alertDialog;
    ProgressDialog progressDialog;
    ProgressDialog prodialog;
    private AudioManager mAudioManager;
    int floatprogress;
    private float SWIPE_THRESHOLDVOl = 10;
    private float SWIPE_THRESHOLD = 10;
    int backprogress = 0;
    private String scroll = null;
    private int oreintat;
    private String file_name;
    int videoWidth;
    int videoHeight;
    private Cursor videoCursorActivity;
    int folderposition;
    int curBrightnessValue;
    private int screenorientation;
    private boolean showNotification;
    Boolean bolseek, boolvol, boolbri, boolmute = false;
    public ArrayList<String> recent, recentpath;
    String insert_query;
    int curr_value;
    int currentVol;
    Bundle extras;

    // Get the screen current brightness
    protected int getScreenBrightness() {
        /*
            public static int getInt (ContentResolver cr, String name, int def)
                Convenience function for retrieving a single system settings value as an integer.
                Note that internally setting values are always stored as strings; this function
                converts the string to an integer for you. The default value will be returned
                if the setting is not defined or not an integer.

            Parameters
                cr : The ContentResolver to access.
                name : The name of the setting to retrieve.
                def : Value to return if the setting is not defined.
            Returns
                The setting's current value, or 'def' if it is not defined or not a valid integer.
        */
        int brightnessValue = Settings.System.getInt(
                getContentResolver(),
                Settings.System.SCREEN_BRIGHTNESS, 0);
        System.out.println("Sysytem bright" + brightnessValue);
        return brightnessValue;
    }

    public void getDeviceWidthAndHeight() {
        lp = textureView.getLayoutParams();
        screenWidth = getWindowManager().getDefaultDisplay().getWidth();
        screenHeight = getWindowManager().getDefaultDisplay().getHeight();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint({"ClickableViewAccessibility", "ObsoleteSdkInt"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hdvp_activity_video_detail);
        toolbar=findViewById(R.id.toolbar_video);
        toolbar.setTitle("Play Song");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }



    @SuppressLint("WrongConstant")
    public boolean isLandscape() {
        return getRequestedOrientation() == ActivityInfo.COLOR_MODE_DEFAULT;
    }

    public void toggleRotateScreen() {
        if (isLandscape()) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
        }
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        if (level >= TRIM_MEMORY_RUNNING_LOW || level >= TRIM_MEMORY_RUNNING_CRITICAL || level >= TRIM_MEMORY_COMPLETE || level >= TRIM_MEMORY_BACKGROUND) {
            System.gc();
        }
    }



    public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
        if (this.videoHeight > 0 && this.videoWidth > 0) {
            this.textureView.adjustSize((float) this.relativbAck.getWidth(), (float) this.relativbAck.getHeight(), this.mMediaPlayer.getVideoWidth(), this.mMediaPlayer.getVideoHeight(), HDVPFinalConstant.FinalConstant_FIT_SCREEN);
        }
    }


    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void fullScreen() {
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);

    }

    public void setBrightness(float brightness) {
        float BackLightValue = brightness / 255;
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes(); // Get Params
        layoutParams.screenBrightness = BackLightValue; // Set Value
        getWindow().setAttributes(layoutParams);
    }

    Runnable mStatusChecker = new Runnable() {
        @Override
        public void run() {
            try {
                System.out.println("Show hide");
                if (mMediaPlayer != null) {
                    if (mMediaPlayer.isPlaying()) {
                        locker.setVisibility(View.INVISIBLE);
                        share.setVisibility(View.INVISIBLE);
                        openlist.setVisibility(View.INVISIBLE);
                        ratio.setVisibility(View.INVISIBLE);
                        orientation.setVisibility(View.INVISIBLE);
                        floating.setVisibility(View.INVISIBLE);
                        vol.setVisibility(View.INVISIBLE);
                        controller.setVisibility(View.INVISIBLE);
                        toolbar.setVisibility(View.INVISIBLE);
                        toolbar.animate().translationY(-toolbar.getBottom()).setInterpolator(new AccelerateDecelerateInterpolator()).start();
                    } else if (!isLock) {
                        if (!mMediaPlayer.isPlaying()) {
                            controller.setVisibility(View.VISIBLE);
                            locker.setVisibility(View.VISIBLE);
                            share.setVisibility(View.VISIBLE);
                            openlist.setVisibility(View.VISIBLE);
                            ratio.setVisibility(View.VISIBLE);
                            orientation.setVisibility(View.VISIBLE);
                            floating.setVisibility(View.VISIBLE);
                            vol.setVisibility(View.VISIBLE);
                            toolbar.setVisibility(View.VISIBLE);
                            toolbar.animate().translationY(-toolbar.getTop()).setInterpolator(new DecelerateInterpolator()).start();
                        }
                    }
                }
            } finally {
                mHandler.postDelayed(mStatusChecker, mInterval);
            }
        }
    };

    void startRepeatingTask() {
        mStatusChecker.run();
    }

    void stopRepeatingTask() {
        mHandler.removeCallbacks(mStatusChecker);
    }

    public String milliSecondsToTimer(long milliseconds) {
        String finalTimerString = "";
        String secondsString = "";

        // Convert total duration into time
        int hours = (int) (milliseconds / (1000 * 60 * 60));
        int minutes = (int) (milliseconds % (1000 * 60 * 60)) / (1000 * 60);
        int seconds = (int) ((milliseconds % (1000 * 60 * 60)) % (1000 * 60) / 1000);
// Add hours if there
        if (hours > 0) {
            finalTimerString = hours + ":";
        }

// Prepending 0 to seconds if it is one digit
        if (seconds < 10) {
            secondsString = "0" + seconds;
        } else {
            secondsString = "" + seconds;
        }

        finalTimerString = finalTimerString + minutes + ":" + secondsString;

// return timer string
        return finalTimerString;
    }

    private Runnable onEverySecond = new Runnable() {

        @Override
        public void run() {
            if (seekbar != null && mMediaPlayer != null) {
                curr = mMediaPlayer.getCurrentPosition();
                seekbar.setProgress((int) curr);
                seekForRev.setProgress((int) curr);
                runingduration.setText(milliSecondsToTimer(curr));
                ontouchShow.setText("[" + milliSecondsToTimer(curr));
                if (mMediaPlayer.isPlaying()) {
                    seekbar.postDelayed(onEverySecond, 300);
                } else if (!mMediaPlayer.isPlaying()) {
                    seekbar.postDelayed(onEverySecond, 300);
                }
            }
        }
    };

    private Intent createShareForecastIntent() {
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        Uri screenshotUri = Uri.parse(filename);

        String text = "From MazharPlayer";
        sharingIntent.setType("video/*");
        sharingIntent.putExtra(Intent.EXTRA_TEXT, text);
        sharingIntent.putExtra(Intent.EXTRA_STREAM, screenshotUri);
        startActivity(Intent.createChooser(sharingIntent, "Share HDVPVideoModel"));

        return sharingIntent;

    }
}



