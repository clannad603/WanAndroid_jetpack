package com.example.myapplication.utils


import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup

/***
 * created by huangrui
 *@Date 2021/8/31 17:30
 */


class FlowLayout : ViewGroup {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, def: Int) : super(context, attrs, def)

    override fun generateLayoutParams(p: LayoutParams?): LayoutParams {
        return MarginLayoutParams(p)
    }

    override fun generateLayoutParams(attrs: AttributeSet?): LayoutParams {
        return MarginLayoutParams(context, attrs)
    }

    override fun generateDefaultLayoutParams(): LayoutParams {
        return MarginLayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val measureWidth = MeasureSpec.getSize(widthMeasureSpec)
        val measureHeight = MeasureSpec.getSize(heightMeasureSpec)
        val measureWidthMode = MeasureSpec.getMode(widthMeasureSpec)
        val measureHeightMode = MeasureSpec.getMode(heightMeasureSpec)

        var lineWidth = 0 //记录每一行的宽度
        var linHeight = 0 //记录每一行的高度
        var totalWidth = 0 //记录整体的宽度
        var totalHeight = 0 //记录整体的高度
        val count = childCount
        for (i in 0 until count) {
            val view = getChildAt(i)
            //一定要先调用measureChild(),调用getMeasuredWidth() 才生效
            measureChild(view, widthMeasureSpec, heightMeasureSpec)
            val lp = view.layoutParams as MarginLayoutParams
            val viewWidth = view.measuredWidth + lp.leftMargin + lp.rightMargin
            val viewHeight = view.height + lp.topMargin + lp.bottomMargin
            if (lineWidth + viewWidth > measureWidth) { //当前的行宽+child的宽大于最大的测量宽度
                //换行的情况
                totalWidth = Math.max(lineWidth, viewWidth)
                totalHeight += linHeight
                lineWidth = viewWidth
                linHeight = viewHeight
            } else {
                //不换行的情况
                linHeight = Math.max(linHeight, viewHeight)
                lineWidth += viewWidth
            }
            if (i == count - 1) {
                totalHeight += linHeight
                totalWidth = Math.max(totalWidth, lineWidth)
            }
        }
        //所以的工作都是为了确定容器的宽高
        //所以的工作都是为了确定容器的宽高
        setMeasuredDimension(
            if (measureWidthMode == MeasureSpec.EXACTLY) measureWidth else totalWidth,
            if (measureHeightMode == MeasureSpec.EXACTLY) measureHeight else totalHeight
        )


    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        //四个参数 当前行的宽高  容器的累计宽高 即宽度是取能获取的最大值，高度方向是累加的值

        val count = childCount
        var lineWidth = 0 //累加当前行的行宽

        var linwHeight = 0 //累加当前的行高

        var top = 0
        var left = 0 //当前空间的top坐标和left坐标

        for (i in 0 until count) {
            val view = getChildAt(i)
            val lp = view.layoutParams as MarginLayoutParams
            val viewWidth = view.measuredWidth + lp.rightMargin + lp.leftMargin
            val viewHeight = view.measuredHeight + lp.topMargin + lp.bottomMargin
            if (viewWidth + lineWidth > measuredWidth) {
                //如果换行
                top += linwHeight
                left = 0
                linwHeight = viewHeight
                lineWidth = viewWidth
            } else {
                linwHeight = Math.max(linwHeight, viewHeight)
                lineWidth += viewWidth
            }
            //计算view的left top right bottom
            val lc = left + lp.leftMargin
            val tc = top + lp.topMargin
            val rc = lc + view.measuredWidth
            val bc = tc + view.measuredHeight
            view.layout(lc, tc, rc, bc)
            //将left置为下一个子控件的起点
            left += viewWidth
        }


    }


}
