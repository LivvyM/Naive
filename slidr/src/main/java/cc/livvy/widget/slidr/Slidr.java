package cc.livvy.widget.slidr;

import android.animation.ArgbEvaluator;
import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;

import cc.livvy.widget.slidr.model.SlidrConfig;
import cc.livvy.widget.slidr.model.SlidrInterface;
import cc.livvy.widget.slidr.widget.SliderPanel;


/**
 * This attacher class is used to attach the sliding mechanism to any {@link android.app.Activity}
 * that lets the user slide (or swipe) the activity away as a form of back or up action. The action
 * causes {@link android.app.Activity#finish()} to be called.
 *
 *
 * Created by r0adkll on 8/18/14.
 */
public class Slidr {

    public static SlidrInterface attach(Activity activity){
        return attach(activity, -1, -1);
    }

    public static SlidrInterface attach(final Activity activity, final int statusBarColor1, final int statusBarColor2){

		final SliderPanel panel = initSliderPanel(activity, null);

        panel.setOnPanelSlideListener(new SliderPanel.OnPanelSlideListener() {

            private final ArgbEvaluator mEvaluator = new ArgbEvaluator();

            @Override
            public void onStateChanged(int state) {

            }

            @Override
            public void onClosed() {
                activity.finish();
                activity.overridePendingTransition(0, 0);
            }

            @Override
            public void onOpened() {

			}

            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onSlideChange(float percent) {
                // Interpolate the statusbar color
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP &&
                        statusBarColor1 != -1 && statusBarColor2 != -1){
                    int newColor = (int) mEvaluator.evaluate(percent, statusBarColor1, statusBarColor2);
                    activity.getWindow().setStatusBarColor(newColor);
                }
            }
        });

		return initInterface(panel);
    }

    public static SlidrInterface attach(final Activity activity, final SlidrConfig config){

        final SliderPanel panel = initSliderPanel(activity, config);

        panel.setOnPanelSlideListener(new SliderPanel.OnPanelSlideListener() {

            private final ArgbEvaluator mEvaluator = new ArgbEvaluator();

            @Override
            public void onStateChanged(int state) {
                if(config.getListener() != null){
                    config.getListener().onSlideStateChanged(state);
                }
            }

            @Override
            public void onClosed() {
                if(config.getListener() != null){
                    config.getListener().onSlideClosed();
                }

                activity.finish();
                activity.overridePendingTransition(0, 0);
            }

            @Override
            public void onOpened() {
                if(config.getListener() != null){
                    config.getListener().onSlideOpened();
                }
            }

            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onSlideChange(float percent) {
                // TODO: Add support for KitKat
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP &&
                        config.areStatusBarColorsValid()){

                    int newColor = (int) mEvaluator.evaluate(percent, config.getPrimaryColor(),
                            config.getSecondaryColor());

                    activity.getWindow().setStatusBarColor(newColor);
                }

                if(config.getListener() != null){
                    config.getListener().onSlideChange(percent);
                }
            }
        });

        return initInterface(panel);
    }

	private static SliderPanel initSliderPanel(final Activity activity, final SlidrConfig config) {
        ViewGroup decorView = (ViewGroup)activity.getWindow().getDecorView();
        View oldScreen = decorView.getChildAt(0);
		decorView.removeViewAt(0);

		SliderPanel panel = new SliderPanel(activity, oldScreen, config);
		panel.setId(R.id.slidable_panel);
		oldScreen.setId(R.id.slidable_content);
		panel.addView(oldScreen);
		decorView.addView(panel, 0);
		return panel;
	}

	private static SlidrInterface initInterface(final SliderPanel panel) {
		SlidrInterface slidrInterface = new SlidrInterface() {
			@Override
			public void lock() {
				panel.lock();
			}

			@Override
			public void unlock() {
				panel.unlock();
			}
		};

		return slidrInterface;
	}

}
