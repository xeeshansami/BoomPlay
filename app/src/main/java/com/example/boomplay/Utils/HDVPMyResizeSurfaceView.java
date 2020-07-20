package com.example.boomplay.Utils;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup.LayoutParams;


public class HDVPMyResizeSurfaceView extends TextureView {
    private int mMaxHeight = -1;
    private int mMaxWidth = -1;
    private int mMinHeight = -1;
    private int mMinWidth = -1;
    private int mPreviousHeight = -1;
    private int mPreviousWidth = -1;

    public HDVPMyResizeSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HDVPMyResizeSurfaceView(Context context) {
        super(context);
    }

    public void adjustSize(float surfaceViewWidth, float surfaceViewHeight, int videoWidth, int videoHeight, String type) {
        if (this.mMinHeight < 0 || this.mMaxHeight < 0 || this.mMinWidth < 0 || this.mMaxWidth < 0) {
            saveMinMaxVideoSize(videoWidth, videoHeight);
        }
        HDVPVideosSize size = measureVideoSize(surfaceViewWidth, surfaceViewHeight, videoWidth, videoHeight, type, false);
        LayoutParams lp = getLayoutParams();
        lp.height = size.height;
        lp.width = size.width;
        this.mPreviousWidth = size.width;
        this.mPreviousHeight = size.height;
        setLayoutParams(lp);
        setVisibility(View.VISIBLE);
    }

    private HDVPVideosSize measureVideoSize(float surfaceViewWidth, float surfaceViewHeight, int videoWidth, int videoHeight, String type, boolean cropWhileMaintaingSize) {
        HDVPVideosSize size = new HDVPVideosSize();
        String subType = null;
        if (type == HDVPFinalConstant.FinalConstant_PERCENT_100) {
            subType = HDVPFinalConstant.FinalConstant_PERCENT_100;
            type = HDVPFinalConstant.FinalConstant_FIT_SCREEN;
        }
        if (videoWidth > 0 && videoHeight > 0) {
            DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
            int windowWidth = displayMetrics.widthPixels;
            int windowHeight = displayMetrics.heightPixels;
            int margin = (int) (getContext().getResources().getDisplayMetrics().density * 0.0f);
            float videoRatio = getVideoRatio(videoWidth, videoHeight);
            if (type == HDVPFinalConstant.FinalConstant_STRETCH_SCREEN) {
                size.height = windowHeight;
                size.width = windowWidth;
                if (cropWhileMaintaingSize) {
                    if (videoWidth > videoHeight) {
                        size.width = (int) (((float) size.height) / videoRatio);
                    } else {
                        size.height = (int) (((float) size.width) * videoRatio);
                    }
                }
            } else if (windowWidth < windowHeight) {
                if (videoWidth > videoHeight) {
                    if (type != HDVPFinalConstant.FinalConstant_FIT_SCREEN) {
                        size.height = windowHeight;
                        size.width = (int) (((float) windowHeight) * videoRatio);
                    } else if (surfaceViewWidth / videoRatio > surfaceViewHeight) {
                        size.height = (int) surfaceViewHeight;
                        size.width = (int) (surfaceViewHeight * videoRatio);
                    } else {
                        size.height = (int) (surfaceViewWidth / videoRatio);
                        size.width = (int) surfaceViewWidth;
                    }
                } else if (videoWidth <= videoHeight) {
                    if (type != HDVPFinalConstant.FinalConstant_FIT_SCREEN) {
                        size.height = windowHeight;
                        size.width = (int) (((float) windowHeight) * videoRatio);
                    } else if (surfaceViewHeight * videoRatio > surfaceViewWidth) {
                        size.height = (int) (surfaceViewWidth / videoRatio);
                        size.width = (int) surfaceViewWidth;
                    } else {
                        size.height = (int) surfaceViewHeight;
                        size.width = (int) (surfaceViewHeight * videoRatio);
                    }
                }
            } else if (windowWidth > windowHeight) {
                if (videoWidth > videoHeight) {
                    int height = (int) (((float) windowWidth) * videoRatio);
                    int heightDiff = windowHeight - height;
                    if (type != HDVPFinalConstant.FinalConstant_FIT_SCREEN) {
                        height = (int) (((double) height) + ((1.5d * ((double) (heightDiff < 0 ? -1 : 1))) * ((double) heightDiff)));
                        size.height = height;
                        size.width = (int) (((float) height) / videoRatio);
                    } else if (((float) windowWidth) * videoRatio <= ((float) videoHeight)) {
                        size.height = windowHeight - margin;
                        size.width = (int) (((float) (windowHeight - margin)) / videoRatio);
                    } else if (height < windowHeight) {
                        size.height = (int) (((float) windowWidth) * videoRatio);
                        size.width = windowWidth;
                    } else {
                        size.width = windowWidth + heightDiff;
                        size.height = (int) (((double) height) + (1.5d * ((double) heightDiff)));
                    }
                } else if (videoWidth >= videoHeight) {
                    size.height = windowHeight - margin;
                    size.width = size.height;
                } else if (type == HDVPFinalConstant.FinalConstant_FIT_SCREEN) {
                    size.height = windowHeight - margin;
                    size.width = (int) (((float) (windowHeight - margin)) / videoRatio);
                } else {
                    size.height = (int) (((float) windowWidth) * videoRatio);
                    size.width = windowWidth;
                }
            }
            if (subType == HDVPFinalConstant.FinalConstant_PERCENT_100) {
                if (videoWidth > videoHeight) {
                    if (windowWidth > windowHeight) {
                        size.width /= 2;
                        size.height = (int) (((float) size.width) * videoRatio);
                    } else {
                        size.width = (int) (((double) surfaceViewWidth) / 1.5d);
                        size.height = (int) (((float) size.width) / videoRatio);
                    }
                } else if (windowWidth > windowHeight) {
                    size.height /= 2;
                    size.width = (int) (((float) size.height) / videoRatio);
                } else {
                    size.height = (int) (((double) surfaceViewHeight) / 1.5d);
                    size.width = (int) (((float) size.height) * videoRatio);
                }
            }
        }
        return size;
    }

    private float getVideoRatio(int videoWidth, int videoHeight) {
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        if (displayMetrics.widthPixels < displayMetrics.heightPixels) {
            return ((float) videoWidth) / ((float) videoHeight);
        }
        return ((float) videoHeight) / ((float) videoWidth);
    }

    public void saveMinMaxVideoSize(int width, int height) {
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        int windowWidth = displayMetrics.widthPixels;
        int windowHeight = displayMetrics.heightPixels;
        float videoRatio = getVideoRatio(width, height);
        if (width > height) {
            if (windowWidth > windowHeight) {
                this.mMaxWidth = windowWidth * 3;
                this.mMinWidth = width / 3;
                this.mMinHeight = (int) (((float) this.mMinWidth) * videoRatio);
                this.mMaxHeight = (int) (((float) this.mMaxWidth) * videoRatio);
                return;
            }
            this.mMaxHeight = windowHeight * 4;
            this.mMinHeight = height / 3;
            this.mMinWidth = (int) (((float) this.mMinHeight) * videoRatio);
            this.mMaxWidth = (int) (((float) this.mMaxHeight) * videoRatio);
        } else if (windowWidth > windowHeight) {
            this.mMaxHeight = windowHeight * 4;
            this.mMinHeight = height / 3;
            this.mMinWidth = (int) (((float) this.mMinHeight) / videoRatio);
            this.mMaxWidth = (int) (((float) this.mMaxHeight) / videoRatio);
        } else {
            this.mMaxWidth = windowWidth * 3;
            this.mMinWidth = width / 3;
            this.mMinHeight = (int) (((float) this.mMinWidth) / videoRatio);
            this.mMaxHeight = (int) (((float) this.mMaxWidth) / videoRatio);
        }
    }

}
