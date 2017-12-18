package culture.baofeng.com.dpitool;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

/**
 * 再按一次退出的封装
 * Created by huangyong on 2017/12/12.
 */

public class ExitUtil {

    /**
     * 再按一次退出
     */
    private static long mExitTime = 0;

    public static void ExitBetween2s(Context context) {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            mExitTime = System.currentTimeMillis();
            Toast.makeText(context, "再按一次退出噢!", Toast.LENGTH_SHORT).show();
        } else {
            ((Activity)context).finish();
        }
    }
}
