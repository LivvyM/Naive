package cc.livvy.widget.cycleview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;


public class CusConvenientBanner<T> extends CrystalCycleView {
    private ViewGroup mView;

    public void setParentView(ViewGroup view) {
        this.mView = view;
    }

    public CusConvenientBanner(Context context) {
        super(context);
    }

    public CusConvenientBanner(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent e) {
        if (this.mView != null && e.getAction() != MotionEvent.ACTION_UP) {
            this.mView.requestDisallowInterceptTouchEvent(true);
        }
        return super.dispatchTouchEvent(e);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        if (this.mView != null && e.getAction() != MotionEvent.ACTION_UP) {
            this.mView.requestDisallowInterceptTouchEvent(true);
        }
        return super.onTouchEvent(e);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        if (this.mView != null && e.getAction() != MotionEvent.ACTION_UP) {
            this.mView.requestDisallowInterceptTouchEvent(true);
        }
        return super.onInterceptTouchEvent(e);
    }
}
