package com.example.pentagon.appbar.DataClass;

import com.example.pentagon.appbar.R;

/**
 * Created by anupamchugh on 26/12/15.
 */
public enum ModelObject {

    REPORT(R.string.report, R.layout.viewreport),
    PROJECT(R.string.project, R.layout.viewproject),
    CONTENT(R.string.content, R.layout.viewcontent),
    SUMMERY(R.string.summary, R.layout.viewsummary);
    private int mTitleResId;
    private int mLayoutResId;

    ModelObject(int titleResId, int layoutResId) {
        mTitleResId = titleResId;
        mLayoutResId = layoutResId;
    }

    public int getTitleResId() {
        return mTitleResId;
    }

    public int getLayoutResId() {
        return mLayoutResId;
    }

}
