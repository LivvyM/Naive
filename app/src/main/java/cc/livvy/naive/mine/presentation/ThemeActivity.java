package cc.livvy.naive.mine.presentation;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import cc.livvy.naive.R;
import cc.livvy.naive.base.AppBaseActivity;
import cc.livvy.widget.skin.SkinLoaderListener;
import cc.livvy.widget.skin.loader.SkinManager;

/**
 *
 * Created by livvy on 17-3-29.
 */

public class ThemeActivity extends AppBaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme);

        setTitle(getString(R.string.str_mine_menu_theme));

        findViewById(R.id.mLayoutThemeWhite).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SkinManager.getInstance().loadSkin("skin-default.skin",
                        new SkinLoaderListener() {
                            @Override
                            public void onStart() {
                                Log.i("SkinLoaderListener", "正在切换中");
                            }

                            @Override
                            public void onSuccess() {
                                Log.i("SkinLoaderListener", "切换成功");
                            }

                            @Override
                            public void onFailed(String errMsg) {
                                Log.i("SkinLoaderListener", "切换失败:" + errMsg);
                            }

                            @Override
                            public void onProgress(int progress) {
                                Log.i("SkinLoaderListener", "皮肤文件下载中:" + progress);
                            }
                        }
                );
            }
        });

        findViewById(R.id.mLayoutThemeBlack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SkinManager.getInstance().loadSkin("skin-black.skin",
                        new SkinLoaderListener() {
                            @Override
                            public void onStart() {
                                Log.i("SkinLoaderListener", "正在切换中");
                            }

                            @Override
                            public void onSuccess() {
                                Log.i("SkinLoaderListener", "切换成功");
                            }

                            @Override
                            public void onFailed(String errMsg) {
                                Log.i("SkinLoaderListener", "切换失败:" + errMsg);
                            }

                            @Override
                            public void onProgress(int progress) {
                                Log.i("SkinLoaderListener", "皮肤文件下载中:" + progress);
                            }
                        }
                );
            }
        });

        findViewById(R.id.mLayoutThemeRed).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SkinManager.getInstance().loadSkin("skin-red.skin",
                        new SkinLoaderListener() {
                            @Override
                            public void onStart() {
                                Log.i("SkinLoaderListener", "正在切换中");
                            }

                            @Override
                            public void onSuccess() {
                                Log.i("SkinLoaderListener", "切换成功");
                            }

                            @Override
                            public void onFailed(String errMsg) {
                                Log.i("SkinLoaderListener", "切换失败:" + errMsg);
                            }

                            @Override
                            public void onProgress(int progress) {
                                Log.i("SkinLoaderListener", "皮肤文件下载中:" + progress);
                            }
                        }

                );
            }
        });
    }

}
