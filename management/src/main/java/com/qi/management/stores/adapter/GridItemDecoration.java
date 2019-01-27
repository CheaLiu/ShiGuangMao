package com.qi.management.stores.adapter;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Creator LiuQi
 * Date 2018/4/28
 * 表格分割线
 */
public class GridItemDecoration extends RecyclerView.ItemDecoration {

    private int mHorizontalDividerColor;
    private int mVerticalDividerColor;
    private int mHdw;
    private int mVdw;
    private Paint mHorizontalPaint;
    private Paint mVerticalPaint;

    public GridItemDecoration(int dividerColor, int dividerWidth) {
        this(dividerColor, dividerColor, dividerWidth, dividerWidth);
    }

    /**
     * @param horizontalDividerColor 水平分割线颜色
     * @param verticalDividerColor   垂直分割线颜色
     * @param hdw                    水平分割线宽度
     * @param vdw                    垂直分割线宽度
     */
    public GridItemDecoration(int horizontalDividerColor, int verticalDividerColor, int hdw, int vdw) {
        mHorizontalDividerColor = horizontalDividerColor;
        mVerticalDividerColor = verticalDividerColor;
        mHdw = hdw;
        mVdw = vdw;
        mHorizontalPaint = new Paint();
        mHorizontalPaint.setColor(mHorizontalDividerColor);
        mHorizontalPaint.setStyle(Paint.Style.FILL);
        mVerticalPaint = new Paint();
        mVerticalPaint.setColor(mVerticalDividerColor);
        mVerticalPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        //计算分割线偏移量，算完分割线的位置剩下的就是ItemView。
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            int orientation = ((GridLayoutManager) layoutManager).getOrientation();
            int spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
            int position = layoutManager.getPosition(view);
            int itemCount = parent.getAdapter().getItemCount();
            int index = position % spanCount;//如果方向是垂直，index表示列号；如果是水平，index表示行号(从0开始)
            int lines;//总行(列)数
            lines = itemCount / spanCount + (itemCount % spanCount == 0 ? 0 : 1);
            int linePosition;//如果方向是垂直，linePosition表示行号；如果是水平，linePosition表示列号（从1开始）
            if (position < spanCount) linePosition = 1;
            else linePosition = position / spanCount + 1;
            int right = mVdw;
            int bottom = mHdw;
            if (orientation == GridLayoutManager.VERTICAL) {
                if (index == spanCount - 1) {
                    right = 0;
                }
                if (linePosition == lines) {
                    bottom = 0;
                }
            } else if (orientation == GridLayoutManager.HORIZONTAL) {
                if (linePosition == lines) {
                    right = 0;
                }
                if (index == spanCount - 1) {
                    bottom = 0;
                }
            }
            outRect.set(0, 0, right, bottom);
        }
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        drawHorizontalDivider(c, parent, state);
        drawVerticalDivider(c, parent, state);
    }

    /**
     * 画垂直分割线
     *
     * @param c      canvas
     * @param parent RecyclerView
     * @param state  RecyclerView.State
     */
    private void drawVerticalDivider(Canvas c, RecyclerView parent, RecyclerView.State state) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            int itemCount = layoutManager.getChildCount();
            int childCount = parent.getChildCount();
            int orientation = ((GridLayoutManager) layoutManager).getOrientation();
            int spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
            int offset = 0;
            for (int i = 0; i < childCount; i++) {
                int index = i % spanCount;
                View child = parent.getChildAt(i);
                if (layoutManager.getPosition(child) == itemCount - 1) {
                    return;
                }
                int left = child.getRight();
                int top = offset;
                int right = left + mVdw;
                int bottom = child.getBottom();
                c.drawRect(new Rect(left, top, right, bottom), mVerticalPaint);
                if (orientation == GridLayoutManager.VERTICAL) {
                    //index是child所在列的位置索引
                    if (index == spanCount - 1) offset = bottom;
                } else if (orientation == GridLayoutManager.HORIZONTAL) {
                    offset = top;
                    if (index == spanCount - 1) offset = 0;
                }
            }
        }
    }

    /**
     * 画水平分割线
     *
     * @param c      canvas
     * @param parent RecyclerView
     * @param state  RecyclerView.State
     */
    private void drawHorizontalDivider(Canvas c, RecyclerView parent, RecyclerView.State state) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            int childCount = parent.getChildCount();
            int orientation = ((GridLayoutManager) layoutManager).getOrientation();
            int spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
            int offset = 0;
            for (int i = 0; i < childCount; i++) {
                int index = i % spanCount;
                View child = parent.getChildAt(i);
                int left = offset;
                int top = child.getBottom();
                int right = child.getRight();
                int bottom = top + mHdw;
                c.drawRect(new Rect(left, top, right, bottom), mHorizontalPaint);
                if (orientation == GridLayoutManager.VERTICAL) {
                    //index是child所在行的位置索引
                    offset = right;
                    if (index == spanCount - 1) offset = 0;
                } else if (orientation == GridLayoutManager.HORIZONTAL) {
                    if (index == spanCount - 1) offset = right;
                }
            }
        }
    }
}
