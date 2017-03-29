package cc.livvy.common.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import cc.livvy.common.stack.ActivityStack;

/**
 * Created by livvy on 17-3-29.
 */

public class BaseActivity extends AppCompatActivity {
    private ActivityStack stack = ActivityStack.getInstanse();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        stack.addActivity(this);
    }

    @Override
    public void finish() {
        super.finish();
        stack.removeActivity(this);
    }
}
