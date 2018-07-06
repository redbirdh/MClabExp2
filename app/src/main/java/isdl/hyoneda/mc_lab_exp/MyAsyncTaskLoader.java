package isdl.hyoneda.mc_lab_exp;

import android.content.AsyncTaskLoader;
import android.content.Context;

public abstract class MyAsyncTaskLoader<D> extends AsyncTaskLoader<D> {

    private D mResult;
    private boolean mIsStarted = false;

    public MyAsyncTaskLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        if (mResult != null) {
            deliverResult(mResult);
            return;
        }
        if (!mIsStarted || takeContentChanged()) {
            forceLoad();
        }
    }

    @Override
    protected void onForceLoad() {
        super.onForceLoad();
        mIsStarted = true;
    }

    @Override
    public void deliverResult(D data) {
        mResult = data;
        super.deliverResult(data);
    }
}