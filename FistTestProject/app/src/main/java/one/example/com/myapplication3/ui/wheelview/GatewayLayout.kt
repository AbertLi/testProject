//package one.example.com.myapplication3.ui.wheelview
//
//import android.animation.ValueAnimator
//import android.content.Context
//import android.graphics.*
//import android.util.AttributeSet
//import android.util.Log
//import android.view.View
//import android.view.ViewGroup
//import android.view.animation.DecelerateInterpolator
//import androidx.core.content.ContextCompat
//import one.example.com.myapplication3.R
//
//
///**
//
// * @Author 黄工
// * @Date 2020/12/31-15:52
// * 描述:网关布局
// */
//class GatewayLayout(context: Context, attrs: AttributeSet) :
//    ViewGroup(context, attrs) {
//
//    private val TAG = "GatewayLayout"
//    var showCount = 0
//
//    /**
//     * 记录所有小圆中心坐标
//     */
//    var points = arrayListOf<PointF>()
//    var colors = arrayListOf<Int>()
//
//    var water: Bitmap
//
//    /**
//     * 水滴列表
//     */
//    var bitmapList = HashMap<Int, Bitmap?>()
//    var childs = HashMap<Int, DeviceView>()
//
//    var isThreeMode = false
//
//    /**
//     * 每次向前步数
//     */
//    var step: Float = 0f
//
//
//    init {
//        setWillNotDraw(false)
//        water = BitmapFactory.decodeResource(getContext().resources, R.mipmap.water)
//
//        val anim = ValueAnimator.ofFloat(0f, 1f)
//        anim.interpolator = DecelerateInterpolator()
//        anim.repeatCount = ValueAnimator.INFINITE
//        anim.duration = 2000
//        anim.addUpdateListener { animation ->
//            step = animation.animatedValue.toString().toFloat()
//            postInvalidate()
//        }
//        anim.start()
//    }
//
//
//    override fun onLayout(
//        changed: Boolean,
//        l: Int,
//        t: Int,
//        r: Int,
//        b: Int
//    ) {
//        var cWidth = 0
//        var cHeight = 0
//        points.clear()
//        colors.clear()
//        bitmapList.clear()
//        //记录需要展示的控件数
//        showCount = 0
//        repeat(childCount) {
//            val childView: DeviceView = getChildAt(it) as DeviceView
//            if (!childView.isMain && childView.visibility == View.VISIBLE) {
//                showCount++
//            }
//        }
//        isThreeMode = showCount == 3
//        Log.e(TAG,"mode:" + isThreeMode)
//        //每份角度
//        val pieAngle = 360 / showCount
//        var step = 0
//
//        repeat(childCount) {
//            val childView: DeviceView = getChildAt(it) as DeviceView
//            cWidth = childView.getMeasuredWidth()
//            cHeight = childView.getMeasuredHeight()
//            if (childView.isMain) {
//                childView.layout(
//                    width / 2 - cWidth / 2,
//                    height / 2 - cHeight / 2,
//                    width / 2 + cWidth / 2,
//                    height / 2 + cHeight / 2
//                )
//            } else {
//                var rotateBitmap: Bitmap? = null
//                //如果是显示则按比例展示，否则不展示
//                if (childView.visibility == View.VISIBLE) {
//                    val angle = pieAngle * (step + 1) - pieAngle - 90
//                    var x1 = width / 2 + ((height / 2 - cWidth / 2) * Math.cos(angle * 3.14 / 180))
//                    var y1 =
//                        height / 2 + ((height / 2 - cHeight / 2) * Math.sin(angle * 3.14 / 180))
//                    points.add(PointF(x1.toFloat(), y1.toFloat()))
//                    if (!childView.isEnable) {
//                        colors.add(ContextCompat.getColor(context, R.color.gate_null))
//                    } else {
//                        //为负数时 反向
//                        if(childView.value > 0){
//                            colors.add(childView.color)
//                        }else{
//                            colors.add(
//                                ContextCompat.getColor(context, R.color.gate_home)
//                            )
//                        }
//
//                    }
//                    rotateBitmap = when (it) {
//                        1 -> {
//                            rotateBitmap(water, dropUp())
//                        }
//                        2 -> {
//                            rotateBitmap(water, dropLeft())
//                        }
//                        3 -> {
//                            if (isThreeMode) {
//                                if (childView.value > 0) {
//                                    rotateBitmap(water, dropRightDown())
//                                } else if (childView.value < 0) {
//                                    rotateBitmap(water, dropRightUp())
//                                } else {
//                                    rotateBitmap(water, dropRightUp())
//                                }
//                            } else {
//                                if (childView.value > 0) {
//                                    rotateBitmap(water, dropDown())
//                                } else if (childView.value < 0) {
//                                    rotateBitmap(water, dropUp())
//                                } else {
//                                    rotateBitmap(water, dropUp())
//                                }
//                            }
//                        }
//                        4 -> {
//                            if (isThreeMode) {
//                                if (childView.value > 0) {
//                                    rotateBitmap(water, dropLeftDown())
//                                } else if (childView.value < 0) {
//                                    rotateBitmap(water, dropLeftUp())
//                                } else {
//                                    rotateBitmap(water, dropLeftUp())
//                                }
//                            } else {
//                                if (childView.value > 0) {
//                                    rotateBitmap(water, dropRight())
//                                } else if (childView.value < 0) {
//                                    rotateBitmap(water, dropLeft())
//                                } else {
//                                    rotateBitmap(water, dropLeft())
//                                }
//                            }
//                        }
//                        else -> null
//                    }
//                    Log.d(TAG, "onLayout:$it $angle $x1 , $y1 $pieAngle")
//                    childView.layout(
//                        (x1 - cWidth / 2).toInt(),
//                        (y1 - cHeight / 2).toInt(),
//                        (x1 + cWidth / 2).toInt(),
//                        (y1 + cHeight / 2).toInt()
//                    )
//                    step++
//                    bitmapList.put(step, rotateBitmap)
//                    childs.put(step, childView)
//                }
//
//            }
//
//
//        }
//
//    }
//
//    /**
//     * 水滴方向
//     */
//    private fun dropDown(): Float {
//        return 180f
//    }
//
//    private fun dropUp(): Float {
//        return 0f
//    }
//
//    private fun dropLeft(): Float {
//        return 90f
//    }
//
//    private fun dropRight(): Float {
//        return 270f
//    }
//
//    private fun dropLeftDown(): Float {
//        return 235f
//    }
//
//    private fun dropLeftUp(): Float {
//        return 60f
//    }
//
//    private fun dropRightDown(): Float {
//        return 120f
//    }
//
//    private fun dropRightUp(): Float {
//        return 300f
//    }
//
//
//    /**
//     * 选择变换
//     *
//     * @param origin 原图
//     * @param alpha  旋转角度，可正可负
//     * @return 旋转后的图片
//     */
//    private fun rotateBitmap(origin: Bitmap?, alpha: Float): Bitmap? {
//        if (origin == null) {
//            return null
//        }
//        val width = origin.width
//        val height = origin.height
//        val matrix = Matrix()
//        matrix.setRotate(alpha)
//        // 围绕原地进行旋转
//
//        val newBM = Bitmap.createBitmap(origin, 0, 0, width, height, matrix, false)
//        if (newBM == origin) {
//            return newBM
//        }
//        return newBM
//    }
//
//    override fun onDraw(canvas: Canvas) {
//        super.onDraw(canvas)
//        points.forEachIndexed { index, it ->
//            var paint = Paint()
//            paint.setStyle(Paint.Style.STROKE);
//            var path = Path()
//            path.moveTo((width / 2).toFloat(), (height / 2).toFloat())
//            path.lineTo(it.x, it.y)
//            paint.color = colors.get(index)
//
//            var mPathMeasure = PathMeasure(path, false);
//            var pathLength = mPathMeasure.length
//            var stop = 0f
//            if (childs.size > index) {
//                val child = childs.get(index + 1)
//                child?.let {
//                    if (it.isEnable) {
//                        if (it.value > 0 || index < 1) {
//                            stop = pathLength * (1 - step)
//                        } else if (it.value < 0) {
//                            stop = pathLength * step
//                        }
//                    }
//                }
//            }
//            val coords = floatArrayOf(0f, 0f)
//            mPathMeasure.getPosTan(stop, coords, null);
//            paint.colorFilter = PorterDuffColorFilter(paint.color, PorterDuff.Mode.SRC_IN)
//            bitmapList.get(index + 1)
//                ?.let { it1 ->
//                    canvas.drawBitmap(
//                        it1,
//                        coords[0] - it1.width / 2,
//                        coords[1] - it1.height / 2,
//                        paint
//                    )
//                }
//            paint.strokeWidth = DisplayUtil.dip2px(context, 1f).toFloat()
//            canvas?.drawPath(path, paint)
//        }
//
//
//    }
//
//
//    /**
//     * 计算所有ChildView的宽度和高度 然后根据ChildView的计算结果，设置自己的宽和高
//     */
//    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
//        /**
//         * 获得此ViewGroup上级容器为其推荐的宽和高，以及计算模式
//         */
//        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
//        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
//        val sizeWidth = MeasureSpec.getSize(widthMeasureSpec)
//        val sizeHeight = MeasureSpec.getSize(heightMeasureSpec)
//
//
//        // 计算出所有的childView的宽和高
//        measureChildren(widthMeasureSpec, heightMeasureSpec)
//        /**
//         * 如果是wrap_content设置为我们计算的值
//         * 否则：直接设置为父容器计算的值
//         */
//        setMeasuredDimension(
//            if (widthMode == MeasureSpec.EXACTLY) sizeWidth else width,
//            if (heightMode == MeasureSpec.EXACTLY) sizeHeight else height
//        )
//    }
//
//
//}