package com.hollysmart.huanwei;

import com.tencent.tinker.loader.app.TinkerApplication;
import com.tencent.tinker.loader.shareutil.ShareConstants;

/**
 * Created by sunpengfei on 2017/11/14.
 */

public class SampleApplication extends TinkerApplication {
    public SampleApplication() {
        super(ShareConstants.TINKER_ENABLE_ALL, "com.hollysmart.huanwei.SampleApplicationLike",
                "com.tencent.tinker.loader.TinkerLoader", false);
    }
}
