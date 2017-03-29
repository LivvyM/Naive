package cc.livvy.naive;

import cc.livvy.widget.skin.SkinConfig;
import cc.livvy.widget.skin.base.SkinBaseApplication;

/**
 * Created by livvy on 17-3-29.
 */

public class Application extends SkinBaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        SkinConfig.setCanChangeStatusColor(true);
//        SkinConfig.setCanChangeFont(true);
//        SkinConfig.addSupportAttr("tabLayoutIndicator", new TabLayoutIndicatorAttr());
    }

}
