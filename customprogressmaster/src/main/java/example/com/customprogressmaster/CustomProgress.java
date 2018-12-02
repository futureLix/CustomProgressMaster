package example.com.customprogressmaster;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

/**
 * 自定义进度
 * Created by Lix on 2017-10-18.
 *
 * @author Lix
 */
@SuppressLint("DrawAllocation")
public class CustomProgress extends View {
    /**
     * 画笔对象的引用
     */
    private Paint paint;
    /**
     * 中间进度百分比的字符串
     */
    private int textRoundWidth;
    /**
     * 中间进度百分比的字符串的颜色
     */
    private int textColor;
    /**
     * 中间进度百分比的字符串的字体
     */
    private float textSize;
    /**
     * 圆环的宽度
     */
    private float roundWidth;
    /**
     * 最大进度 默认100
     */
    private int max;
    /**
     * 当前进度
     */
    private int mProgress;
    /**
     * 是否显示中间的进度
     */
    private boolean textIsDisplayable;
    /**
     * 背景颜色
     */
    private int foreground;
    /**
     * 进度条颜色
     */
    private int backgroundColor;
    /**
     * 获取圆中心
     */
//    private int center;
    /**
     * 圆环的半径
     */
    private int radius;
    /**
     * 文字
     */
    private String text;
    /**
     * 获取自定义View的宽和高
     */
    private float halfWidth;
    private float halfHeight;
    /**
     * 显示进度数值
     */
    private int mShowProgress;
    /**
     * 设置动态时间
     */
    private int mShowProgressTime;


    public CustomProgress(Context context) {
        this(context, null);
    }

    public CustomProgress(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomProgress(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomProgress);
        backgroundColor = typedArray.getInteger(R.styleable.CustomProgress_mBackGround, ContextCompat.getColor(context, R.color.progress_bg1));
        foreground = typedArray.getInteger(R.styleable.CustomProgress_mForeGround, ContextCompat.getColor(context, R.color.progress_bg2));
        max = typedArray.getInteger(R.styleable.CustomProgress_max, 100);
        mProgress = typedArray.getInteger(R.styleable.CustomProgress_progress, 0);
        text = typedArray.getString(R.styleable.CustomProgress_mText);
        textColor = typedArray.getInteger(R.styleable.CustomProgress_mTextColor, ContextCompat.getColor(context, R.color.progress_bg3));
        textSize = typedArray.getDimension(R.styleable.CustomProgress_mTextSize, 22);
        mShowProgressTime = typedArray.getInteger(R.styleable.CustomProgress_mTime, 30);
        roundWidth = typedArray.getFloat(R.styleable.CustomProgress_mRoundWidth, 10);
        textRoundWidth = typedArray.getInteger(R.styleable.CustomProgress_mTextRoundWidth, 0);
        textIsDisplayable = typedArray.getBoolean(R.styleable.CustomProgress_mTextIsDisplayable, true);
        typedArray.recycle();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint = new Paint();
        halfWidth = getMeasuredWidth() / 2;
        halfHeight = getMeasuredHeight() / 2;
        radius = (int) (halfWidth - roundWidth / 2);
        drawBackground(canvas);
        drawText(canvas);
        drawProgress(canvas);
    }

    /**
     * 画最外层的大圆环
     *
     * @param canvas
     */
    private void drawBackground(Canvas canvas) {
        paint.setColor(foreground);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(roundWidth);
        paint.setAntiAlias(true);
        canvas.drawCircle(halfWidth, halfWidth, radius, paint);
    }


    /***
     * 绘制进度值
     * @param canvas
     */
    private void drawProgress(Canvas canvas) {
        /**
         * 画圆弧 ，画圆环的进度
         */
        paint.setStrokeWidth(roundWidth);
        paint.setColor(backgroundColor);
        RectF oval = new RectF(halfWidth - radius, halfWidth - radius, halfWidth + radius, halfWidth + radius);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawArc(oval, -90, 360 * mProgress / max, false, paint);

    }

    /***
     * 绘制文本
     * @param canvas
     */
    private void drawText(Canvas canvas) {
        paint.setColor(textColor);
        paint.setTextSize(textSize);
        paint.setStrokeWidth(textRoundWidth);
        paint.setTypeface(Typeface.DEFAULT);
        int percent = (int) ((mProgress / (float) max) * 100);
        if (textIsDisplayable && percent > -1) {
            canvas.drawText(percent + "%", halfWidth - paint.measureText(percent + "%") / 2, halfHeight - (paint.ascent() + paint.descent()) / 2, paint);
        } else {
            if (null != text) {
                canvas.drawText(text, halfWidth - paint.measureText(text) / 2, halfHeight - (paint.ascent() + paint.descent()) / 2, paint);
            }
        }
    }


    /**
     * 设置字体大小
     *
     * @param textSize
     */
    public void setTextSize(float textSize) {
        this.textSize = textSize;
    }

    /**
     * 设置宽度
     *
     * @param roundWidth
     */
    public void setRoundWidth(float roundWidth) {
        this.roundWidth = roundWidth;
    }

    /**
     * 设置圆角
     *
     * @param radius
     */
    public void setRadius(int radius) {
        this.radius = radius;
    }

    /**
     * 设置最大值
     *
     * @param max
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     * 设置文本提示信息
     *
     * @param text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * 设置进度条的颜色值
     *
     * @param color
     */
    public void setForeground(int color) {
        this.foreground = color;
    }

    /**
     * 设置进度条的背景色
     */
    @Override
    public void setBackgroundColor(int color) {
        this.backgroundColor = color;
    }

    /***
     * 设置文本的大小
     */
    public void setTextSize(int size) {
        this.textSize = size;
    }

    /**
     * 设置文本的颜色值
     *
     * @param color
     */
    public void setTextColor(int color) {
        this.textColor = color;
    }

    /**
     * 设置进度值
     *
     * @param progress
     */
    public void setProgress(int progress) {
        if (progress > 100) {
            progress = 100;
        }
        if (progress < 0) {
            progress = 0;
        }
        mProgress = progress;
        postInvalidate();
    }

    public int getMax() {
        return max;
    }

    public int getProgress() {
        return mProgress;
    }

    /**
     * 是否显示中间的进度字体
     *
     * @return
     */
    public boolean isTextIsDisplayable() {
        return textIsDisplayable;
    }

    /**
     * 获取是否显示中间的进度字体
     *
     * @param textIsDisplayable
     */
    public void setTextIsDisplayable(boolean textIsDisplayable) {
        this.textIsDisplayable = textIsDisplayable;
    }

    /**
     * 动画时长
     *
     * @param showProgressTime
     */
    public void setShowProgressTime(int showProgressTime) {
        mShowProgressTime = showProgressTime;
    }

    /**
     * 实际展示总进度
     *
     * @param progress
     */
    public void setmShowProgress(int progress) {
        mShowProgress = progress;
        new Thread(runnable).start();//实现进度增长的动画效果
    }


    /**
     * 动画增长
     */
    Runnable runnable = new Runnable() {

        @Override
        public void run() {
            Message message = handler.obtainMessage();
            try {
                for (int i = 1; i <= mShowProgress; i++) {
                    message.what = i;
                    handler.sendEmptyMessage(message.what);
                    Thread.sleep(mShowProgressTime);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int p = msg.what;
            setProgress(p);
        }

    };

}