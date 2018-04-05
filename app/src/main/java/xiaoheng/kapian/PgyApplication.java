package xiaoheng.kapian;

import android.app.*;
import com.pgyersdk.crash.*;

public class PgyApplication extends Application
{

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();

        PgyCrashManager.register(this);
    }
}
