package one.example.com.myapplication3.ui.wheelview.kotlin

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.Shader.TileMode
import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import android.os.Message
import android.text.TextPaint
import android.text.TextUtils
import android.util.AttributeSet
import android.util.Log
import android.util.TypedValue
import android.view.Gravity
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.VelocityTracker
import android.view.View
import android.view.ViewConfiguration
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import android.view.animation.Interpolator
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.TextView.OnEditorActionListener;
import one.example.com.myapplication3.R
import java.util.ArrayList;


class WheelView : FrameLayout {
    /**
     * 控件宽度
     */
    private var controlWidth //px
            = 0f

    /**
     * 控件高度
     */
    private var controlHeight //px
            = 0f

    /**
     * measure的高度，用于校正尺寸变化引起的文字错乱
     */
    private var lastMeasuredHeight = 0f

    /**
     * 是否正在滑动
     */
    /**
     * 是否滑动中
     */
    var isScrolling = false
        private set

    /**
     * 选择的内容
     */
    private val itemList: ArrayList<ItemObject>? = ArrayList()

    /**
     * 设置数据
     */
    private var dataList: ArrayList<String> = ArrayList()

    /**
     * 按下的Y坐标
     */
    private var downY = 0

    /**
     * 上一次MotionEvent的Y坐标
     */
    private var lastY = 0

    /**
     * 按下的时间
     */
    private var downTime: Long = 0 //ms

    /**
     * 当前设备的density
     */
    private var density = 1f
    private var slowMoveSpeed = SLOW_MOVE_SPEED
    private var clickDistance = CLICK_DISTANCE

    /**
     * 判断为点击的时间间隔
     */
    private var clickTimeout = 100 //ms

    /**
     * 滚动的最大速度
     */
    private var mMaximumFlingVelocity = 0

    /**
     * 滚动的最小速度
     */
    private var mMinimumFlingVelocity = 0

    /**
     * 滑动速度检测器
     */
    private var mVelocityTracker: VelocityTracker? = null

    /**
     * 画线画笔
     */
    private var linePaint: Paint? = null
    private var mastPaint: Paint? = null

    /**
     * 线的默认颜色
     */
    private var lineColor = -0x1000000

    /**
     * 线的默认宽度
     */
    private var lineHeight = 2f //px

    /**
     * 默认字体
     */
    private var normalFont = 14.0f //px

    /**
     * 选中的时候字体
     */
    private var selectedFont = 22.0f //px

    /**
     * 单元格高度
     */
    private var unitHeight = 50f //px

    /**
     * 显示多少个内容
     */
    private var itemNumber = 7

    /**
     * 默认字体颜色
     */
    private var normalColor = -0x1000000

    /**
     * 选中时候的字体颜色
     */
    private var selectedColor = -0x10000

    /**
     * 遮罩的深色颜色
     */
    private var maskDarkColor = DEFAULT_MASK_DARK_COLOR

    /**
     * 遮罩的浅色部分
     */
    private var maskLightColor = DEFAULT_MASK_LIGHT_COLOR

    /**
     * 选择监听
     */
    private var selectListener: OnSelectListener? = null

    /**
     * 输入监听
     */
    private var inputListener: onInputListener? = null

    /**
     * 是否可用
     */
    /**
     * 设置是否可用
     */
    /**
     * 是否可用
     */
    var isEnable = true

    /**
     * 是否允许选空
     */
    private var noEmpty = true

    /**
     * 设定的是否循环滚动
     */
    private var isCyclic = true

    /**
     * 真实使用的是否循环滚动——当item个数少于展示个数时，强制不使用循环滚动
     */
    private var _isCyclic = true

    /**
     * 正在修改数据，避免ConcurrentModificationException异常
     */
    private var isClearing = false

    /**
     * 连续滑动使用的插值器
     */
    var goonInterpolator: Interpolator = DecelerateInterpolator(2F)

    /**
     * 连续滑动的距离，为unitHeight的整数倍
     */
    var goOnLimit = 0

    /**
     * 连续滑动动画的绘制计数
     */
    private var showTime = 0

    /**
     * 保存最近一次连续滑动的距离的原始值
     */
    private var goOnMove = 0

    /**
     * 当前连续滑动中已经滑动的距离
     */
    private var goOnDistance = 0

    /**
     * 是否正在连续滑动状态中
     */
    private var isGoOnMove = false

    /**
     * 滑动动画的HandlerThread
     */
    private var moveHandlerThread: HandlerThread? = null

    /**
     * 用于计算滑动动画位置的Handler，保证同一时刻只有一个滑动
     */
    private var moveHandler: Handler? = null

    /**
     * 所有item的移动距离，用同一个变量记录，减少计算
     */
    private var moveDistance = 0

    /**
     * 是否允许使用文字输入
     */
    var isWithInputText = false

    /**
     * The text for showing the current value.
     */
    private lateinit var mInputText: EditText
    private lateinit var callbackHandler: Handler
    private lateinit var linearGradientUp: LinearGradient
    private lateinit var linearGradientDown: LinearGradient

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        init(context, attrs)
        initData()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context, attrs)
        initData()
    }

    constructor(context: Context) : this(context, null) {}

    /**
     * 初始化，获取设置的属性
     */
    private fun init(context: Context, attrs: AttributeSet?) {
        val attribute: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.WheelView)
        unitHeight = attribute.getDimension(R.styleable.WheelView_unitHeight, unitHeight)
        //按理说应该是奇数，但是此次不做判断，使用者有义务设置正确
        itemNumber = attribute.getInt(R.styleable.WheelView_itemNumber, itemNumber)
        normalFont = attribute.getDimension(R.styleable.WheelView_normalTextSize, normalFont)
        selectedFont = attribute.getDimension(R.styleable.WheelView_selectedTextSize, selectedFont)
        normalColor = attribute.getColor(R.styleable.WheelView_normalTextColor, normalColor)
        selectedColor = attribute.getColor(R.styleable.WheelView_selectedTextColor, selectedColor)
        lineColor = attribute.getColor(R.styleable.WheelView_lineColor, lineColor)
        lineHeight = attribute.getDimension(R.styleable.WheelView_lineHeight, lineHeight)
        noEmpty = attribute.getBoolean(R.styleable.WheelView_noEmpty, true)
        isEnable = attribute.getBoolean(R.styleable.WheelView_isEnable, true)
        isCyclic = attribute.getBoolean(R.styleable.WheelView_isCyclic, true)
        isWithInputText = attribute.getBoolean(R.styleable.WheelView_withInputText, false)
        maskDarkColor = attribute.getColor(
            R.styleable.WheelView_maskDarkColor,
            DEFAULT_MASK_DARK_COLOR
        )
        maskLightColor = attribute.getColor(
            R.styleable.WheelView_maskLightColor,
            DEFAULT_MASK_LIGHT_COLOR
        )
        density = context.resources.displayMetrics.density
        slowMoveSpeed = (density * SLOW_MOVE_SPEED).toInt()
        clickDistance = (density * CLICK_DISTANCE).toInt()
        controlHeight = itemNumber * unitHeight
        toShowItems = arrayOfNulls(itemNumber + 2)
        val configuration = ViewConfiguration.get(context)
        //        clickDistance = configuration.getScaledTouchSlop();
        clickTimeout = ViewConfiguration.getTapTimeout()
        mMinimumFlingVelocity = configuration.scaledMinimumFlingVelocity
        mMaximumFlingVelocity = configuration.scaledMaximumFlingVelocity
        callbackHandler = Handler(Looper.getMainLooper())
        val inputColor = attribute.getColor(R.styleable.WheelView_inputTextColor, Color.BLACK)
        val background = attribute.getResourceId(R.styleable.WheelView_inputBackground, 0)
        mInputText = EditText(context)
        if (background == 0) {
            mInputText.setBackgroundColor(Color.TRANSPARENT)
        } else {
            mInputText.setBackgroundResource(background)
        }
        mInputText.setTextColor(inputColor)
        mInputText.setTextSize(TypedValue.COMPLEX_UNIT_PX, selectedFont)
        mInputText.gravity = Gravity.CENTER
        mInputText.setPadding(0, 0, 0, 0)
        mInputText.onFocusChangeListener = object : OnFocusChangeListener {
            override fun onFocusChange(v: View?, hasFocus: Boolean) {
                if (hasFocus) {
                    mInputText.setText(selectedText)
                    showInputMethod(context)
                    if (inputListener != null) {
                        inputListener?.onStartInput(this@WheelView, mInputText, selectedText)
                    }
                    mInputText.selectAll()
                } else {
                    mInputText.setSelection(0, 0)
                    mInputText.visibility = View.GONE
                    if (inputListener != null) {
                        inputListener?.endInput(
                            this@WheelView,
                            mInputText,
                            mInputText.text.toString()
                        )
                    }
                    hideInputMethod(mInputText!!)
                }
            }
        }
        var inputType = EditorInfo.TYPE_NULL
        inputType = attribute.getInt(R.styleable.WheelView_android_inputType, EditorInfo.TYPE_NULL)
        mInputText.inputType = inputType
        mInputText.imeOptions = EditorInfo.IME_ACTION_DONE
        mInputText.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
            if ((actionId == EditorInfo.IME_ACTION_DONE
                        || (event != null && event.keyCode === KeyEvent.KEYCODE_ENTER))
            ) {
                mInputText.visibility = View.GONE
                if (inputListener != null) {
                    inputListener?.endInput(
                        this@WheelView,
                        mInputText,
                        mInputText.text.toString()
                    )
                }
                return@OnEditorActionListener true
            }
            false
        })
        val layoutParams = LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            unitHeight.toInt()
        )
        layoutParams.gravity = Gravity.CENTER
        layoutParams.leftMargin = attribute
            .getDimensionPixelOffset(R.styleable.WheelView_inputMarginLeft, 0)
        layoutParams.rightMargin = attribute
            .getDimensionPixelOffset(R.styleable.WheelView_inputMarginRight, 0)
        Log.e(TAG, "leftMargin=" + layoutParams.leftMargin)
        Log.e(TAG, "rightMargin=" + layoutParams.rightMargin)
        addView(mInputText, layoutParams)
        mInputText.visibility = View.GONE
        setWillNotDraw(false)
        attribute.recycle()
    }

    private fun showInputMethod(context: Context) {
        val imm: InputMethodManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(mInputText, InputMethodManager.SHOW_IMPLICIT)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        moveHandlerThread = HandlerThread("goOnHandlerThread")
        moveHandlerThread!!.priority = Thread.MIN_PRIORITY
        moveHandlerThread!!.start()
        moveHandler = GoOnHandler(moveHandlerThread!!.looper)
    }

    override fun onDetachedFromWindow() {
        //销毁线程和handler
        if (moveHandlerThread != null && moveHandlerThread!!.isAlive) {
            moveHandlerThread!!.quit()
        }
        super.onDetachedFromWindow()
    }

    private fun _setIsCyclic(cyclic: Boolean) {
        _isCyclic = if (dataList.size < itemNumber + 2) {
            false
        } else {
            cyclic
        }
    }

    private inner class GoOnHandler internal constructor(looper: Looper?) : Handler(looper) {
        override fun handleMessage(msg: Message) {
            val lastDistance = goOnDistance
            if (moveHandler == null) {
                return
            }
            when (msg.what) {
                GO_ON_MOVE_REFRESH -> {
                    //                    Log.d(TAG,"GO_ON_MOVE_REFRESH,showTime="+showTime);
                    showTime++
                    goOnDistance =
                        (goonInterpolator.getInterpolation(showTime.toFloat() / SHOWTIME.toFloat()) * goOnLimit).toInt()
                    actionThreadMove(
                        if (goOnMove > 0) goOnDistance - lastDistance else (goOnDistance - lastDistance) * -1
                    )
                    if (showTime < SHOWTIME && isGoOnMove && (showTime < SHOWTIME / 5
                                || Math.abs(lastDistance - goOnDistance) >= slowMoveSpeed)
                    ) {
                        // Math.abs(lastDistance-goOnDistance)>SLOW_MOVE_SPEED是为了让滚动更加连贯，showTime<SHOWTIME/5是为了防止刚启动时的误判
                        // 否则在slowMove函数中滚动的速度反而可能超过这里的滚动速度，会有卡了一下的感觉
                        moveHandler?.sendEmptyMessageDelayed(
                            GO_ON_MOVE_REFRESH,
                            GO_ON_REFRESH_INTERVAL_MILLIS.toLong()
                        )
                    } else {
                        //                        Log.d(TAG,"lastDistance-goOnDistance="+(lastDistance-goOnDistance));
                        isGoOnMove = false
                        moveHandler?.sendEmptyMessage(GO_ON_MOVE_END)
                    }
                }
                GO_ON_MOVE_END -> {
                    //                    Log.d(TAG,"GO_ON_MOVE_END,goOnDistance="+goOnDistance);
                    slowMove(if (goOnMove > 0) slowMoveSpeed else slowMoveSpeed * -1)
                    isScrolling = false
                    isGoOnMove = false
                    goOnDistance = 0
                    goOnLimit = 0
                }
                GO_ON_MOVE_INTERRUPTED -> {
                    //在滑动的过程中被打断，则以当前已经滑动的而距离作为新的起点，继续下一次滑动
                    //                    Log.d(TAG,"GO_ON_MOVE_INTERRUPTED");
                    moveDistance += if (goOnMove > 0) goOnDistance - lastDistance else (goOnDistance - lastDistance) * -1
                    goOnDistance = 0
                    isScrolling = false
                    isGoOnMove = false
                    findItemsToShow()
                    postInvalidate()
                }
            }
        }
    }

    /**
     * 继续快速移动一段距离，连续滚动动画，滚动速度递减，速度减到SLOW_MOVE_SPEED之下后调用slowMove
     *
     * @param velocity 滑动的初始速度
     * @param move 滑动的距离
     */
    @Synchronized
    private fun goonMove(velocity: Int, move: Long) {
        showTime = 0
        val newGoonMove = Math.abs(velocity / 10)
        if (goOnMove * move > 0) {
            goOnLimit += newGoonMove
        } else {
            goOnLimit = newGoonMove
        }
        goOnMove = move.toInt()
        isGoOnMove = true
        //将MotionEvent.ACTION_MOVE引起的滑动的距离设置为新的起点，然后再开始新的滑动
        //防止重复滑动同一次Action_Down中滑动的部分
        if (moveHandler == null) {
            return
        }
        moveHandler?.sendEmptyMessage(GO_ON_MOVE_REFRESH)
        //        Log.d(TAG,"goonMove : newGoonMove="+newGoonMove);
        //        Log.d(TAG,"goonMove : goOnLimit="+goOnLimit);
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (!isEnable) {
            return true
        }
        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain()
        }
        mVelocityTracker!!.addMovement(event)
        val y = event.y.toInt()
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                mInputText.visibility = View.GONE
                mInputText.clearFocus()
                //防止被其他可滑动View抢占焦点，比如嵌套到ListView中使用时
                parent.requestDisallowInterceptTouchEvent(true)
                if (isScrolling) {
                    isGoOnMove = false
                    if (moveHandler != null) {
                        //清除当前快速滑动的动画，进入下一次滑动动作
                        moveHandler?.removeMessages(GO_ON_MOVE_REFRESH)
                        moveHandler?.sendEmptyMessage(GO_ON_MOVE_INTERRUPTED)
                    }
                }
                isScrolling = true
                downY = event.y.toInt()
                lastY = event.y.toInt()
                downTime = System.currentTimeMillis()
            }
            MotionEvent.ACTION_MOVE -> {
                isGoOnMove = false
                isScrolling = true
                actionMove(y - lastY)
                lastY = y
            }
            MotionEvent.ACTION_UP -> {
                val time = System.currentTimeMillis() - downTime
                // 判断这段时间移动的距离
                //                Log.d(TAG,"time="+time+",y - downY="+(y - downY));

                //用速度来判断是非快速滑动
                val velocityTracker = mVelocityTracker
                velocityTracker!!.computeCurrentVelocity(1000, mMaximumFlingVelocity.toFloat())
                val initialVelocity = velocityTracker.yVelocity.toInt()
                if (Math.abs(initialVelocity) > mMinimumFlingVelocity) {
                    goonMove(initialVelocity, y - downY.toLong())
                } else {
                    //如果移动距离较小，则认为是点击事件，否则认为是小距离滑动
                    if (Math.abs(y - downY) <= clickDistance && time <= clickTimeout) {
                        if (downY < unitHeight * (itemNumber / 2) && downY > 0) {
                            //如果不先move再up，而是直接up，则无法产生点击时的滑动效果
                            //通过调整move和up的距离，可以调整点击的效果
                            actionMove((unitHeight / 3).toInt())
                            slowMove(unitHeight.toInt() * 2 / 3)
                        } else if (downY > controlHeight - unitHeight * (itemNumber / 2)
                            && downY < controlHeight
                        ) {
                            actionMove((-(unitHeight / 3)).toInt())
                            slowMove((-unitHeight).toInt() * 2 / 3)
                        } else {
                            if (isWithInputText) {
                                mInputText.visibility = View.VISIBLE
                                mInputText.requestFocus()
                            }
                            noEmpty(y - downY)
                        }
                    } else {
                        slowMove(y - downY)
                    }
                    isScrolling = false
                }
                mVelocityTracker!!.recycle()
                mVelocityTracker = null
            }
            else -> {
            }
        }
        return true
    }

    /**
     * 初始化数据
     */
    private fun initData() {
        isClearing = true
        itemList!!.clear()
        for (i in 0 until dataList.size) {
            val itemObject = ItemObject()
            itemObject.id = i
            itemObject.setItemText(dataList[i])
            itemObject.x = 0
            itemObject.y = (i * unitHeight).toInt()
            itemList.add(itemObject)
        }
        isClearing = false
        _setIsCyclic(isCyclic)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        if (heightMode == MeasureSpec.AT_MOST) {
            val atMostHeight = MeasureSpec.getSize(heightMeasureSpec)
            //AT_MOST模式下，如果最大值小于当前view高度，则缩小当前view高度，以适应窗口大小
            if (atMostHeight < controlHeight && atMostHeight != 0) {
                controlHeight = atMostHeight.toFloat()
                unitHeight = (controlHeight / itemNumber)
            }
        } else if (heightMode == MeasureSpec.EXACTLY) {
            //EXACTLY模式下，就以设置的大小为准,调整当前View
            val height = MeasureSpec.getSize(heightMeasureSpec)
            controlHeight = height.toFloat()
            unitHeight = (controlHeight / itemNumber)
        } else if (heightMode == MeasureSpec.UNSPECIFIED) {
            //UNSPECIFIED保持原状不变
        }
        val width = MeasureSpec.getSize(widthMeasureSpec)
        setMeasuredDimension(width, controlHeight.toInt())
        mInputText.measure(
            MeasureSpec.makeMeasureSpec(
                measuredWidth - (mInputText
                    ?.layoutParams as LayoutParams).leftMargin - (mInputText
                    ?.layoutParams as LayoutParams).rightMargin, MeasureSpec.EXACTLY
            ),
            MeasureSpec.makeMeasureSpec((controlHeight / itemNumber).toInt(), MeasureSpec.EXACTLY)
        )

        //用于解决尺寸变化引起的文字位置错乱的问题
        if (Math.abs(lastMeasuredHeight - controlHeight) > 0.1) {
            val index = selected
            initData()
            if (index != -1) {
                setDefault(index)
            } else {
                setDefault(defaultIndex)
            }
            lastMeasuredHeight = controlHeight
            mInputText.layoutParams.height = (controlHeight / itemNumber).toInt()
        }
        //        controlWidth = getWidth();
        //        if (controlWidth != 0) {
        //            setMeasuredDimension(getWidth(), (int) controlHeight);
        //        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        //因为在recycler中添加的时候会导致文字不居中
        controlWidth = width.toFloat()
        drawLine(canvas)
        drawList(canvas)
        drawMask(canvas)
    }

    /**
     * 绘制分隔线条
     */
    private fun drawLine(canvas: Canvas) {
        if (linePaint == null) {
            linePaint = Paint()
            linePaint?.color = lineColor
            linePaint?.isAntiAlias = true
            linePaint?.strokeWidth = lineHeight
        }
        canvas.drawLine(
            0F, controlHeight / 2 - unitHeight / 2 + lineHeight, controlWidth,
            controlHeight / 2 - unitHeight / 2 + lineHeight, linePaint!!
        )
        canvas.drawLine(
            0F, controlHeight / 2 + unitHeight / 2 - lineHeight, controlWidth,
            controlHeight / 2 + unitHeight / 2 - lineHeight, linePaint!!
        )
    }

    /**
     * 绘制待选项目
     */
    @Synchronized
    private fun drawList(canvas: Canvas) {
        if (isClearing) {
            return
        }
        synchronized(toShowItems) {
            for (itemObject: ItemObject? in toShowItems) {
                if (itemObject != null) {
                    itemObject.drawSelf(canvas, measuredWidth)
                }
            }
        }
    }

    /**
     * 绘制上下的遮盖板
     */
    private fun drawMask(canvas: Canvas) {
        if (mastPaint == null) {
            mastPaint = Paint()
            linearGradientUp = LinearGradient(
                0f, 0f, 0f, unitHeight, maskDarkColor, maskLightColor,
                TileMode.CLAMP
            )
            linearGradientDown = LinearGradient(
                0f, controlHeight - unitHeight, 0f, controlHeight, maskLightColor,
                maskDarkColor, TileMode.CLAMP
            )
        }
        mastPaint?.shader = linearGradientUp
        canvas.drawRect(0f, 0f, controlWidth, itemNumber / 2 * unitHeight, mastPaint!!)
        mastPaint?.shader = linearGradientDown
        canvas.drawRect(
            0f, controlHeight - itemNumber / 2 * unitHeight, controlWidth, controlHeight,
            mastPaint!!
        )
    }

    /**
     * 不能为空，必须有选项,滑动动画结束时调用 判断当前应该被选中的项目，如果其不在屏幕中间，则将其移动到屏幕中间
     *
     * @param moveSymbol 移动的距离，实际上只需要其符号，用于判断当前滑动方向
     */
    private fun noEmpty(moveSymbol: Int) {
        if (!noEmpty) {
            return
        }
        // 将当前选择项目移动到正中间，防止出现偏差

        //        Log.d(TAG,"noEmpty start");
        synchronized(toShowItems) {
            findItemsToShow()
            for (item: ItemObject? in toShowItems) {
                if (item != null) {
                    if (item.selected()) {
                        val move: Int = item.moveToSelected().toInt()
                        onEndSelecting(item)
                        defaultMove(move)
                        //                        Log.d(TAG, "noEmpty selected=" + item.id+",movoToSelected= "+move);
                        return
                    }
                }
            }
            // 如果当前没有项目选中，则将根据滑动的方向，将最近的一项设为选中项目，并移动到正中间
            if (moveSymbol > 0) {
                for (i in toShowItems.indices) {
                    if (toShowItems.get(i) != null && toShowItems.get(i)!!.couldSelected()) {
                        val move: Int = toShowItems.get(i)!!.moveToSelected().toInt()
                        onEndSelecting(toShowItems.get(i))
                        defaultMove(move)
                        //                        Log.d(TAG, "noEmpty couldSelected=" + toShowItems[i].id+",movoToSelected= "+move);
                        return
                    }
                }
            } else {
                for (i in toShowItems.indices.reversed()) {
                    if (toShowItems.get(i) != null && toShowItems.get(i)!!.couldSelected()) {
                        val move: Int = toShowItems.get(i)!!.moveToSelected().toInt()
                        onEndSelecting(toShowItems.get(i))
                        defaultMove(move)
                        //                        Log.d(TAG, "noEmpty couldSelected=" + toShowItems[i].id+",movoToSelected= "+move);
                        return
                    }
                }
            }
        }
    }

    private fun onEndSelecting(toShowItem: ItemObject?) {
        if (selectListener != null) {
            callbackHandler.removeCallbacksAndMessages(null)
            callbackHandler.post(Runnable {
                selectListener!!.endSelect(
                    toShowItem!!.id,
                    toShowItem.rawText
                )
            })
        }
    }

    /**
     * 处理MotionEvent.ACTION_MOVE中的移动
     *
     * @param move 移动的距离
     */
    private fun actionMove(move: Int) {
        moveDistance -= move
        //        Log.d(TAG,"move="+move+",moveDistance="+moveDistance);
        findItemsToShow()
        invalidate()
    }

    /**
     * 移动，线程中调用
     *
     * @param move 移动的距离
     */
    private fun actionThreadMove(move: Int) {
        moveDistance -= move
        findItemsToShow()
        postInvalidate()
    }

    /**
     * 找到将会显示的几个item
     */
    //其长度等于itemNumber+2
    private lateinit var toShowItems: Array<ItemObject?>

    private fun findItemsToShow(callListener: Boolean = true) {
        if (itemList!!.isEmpty()) {
            return
        }
        if (_isCyclic) {
            //循环模式下，将moveDistance限定在一定的范围内循环变化，同时要保证滚动的连续性
            if (moveDistance > unitHeight * itemList.size) {
                moveDistance %= (unitHeight.toInt() * itemList.size)
            } else if (moveDistance < 0) {
                moveDistance = (moveDistance % (unitHeight.toInt() * itemList.size)
                        + unitHeight.toInt() * itemList.size)
            }
            val move = moveDistance
            val first = itemList[0]
            val firstY = first.y + move
            val firstNumber =
                Math.abs(firstY / unitHeight).toInt() //滚轮中显示的第一个item的index
            val restMove = (firstY - unitHeight * firstNumber).toInt() //用以保证滚动的连续性
            val takeNumberStart = firstNumber
            synchronized(toShowItems) {
                for (i in toShowItems.indices) {
                    val takeNumber: Int = takeNumberStart + i
                    var realNumber: Int = takeNumber
                    if (takeNumber < 0) {
                        realNumber = itemList.size + takeNumber //调整循环滚动显示的index
                    } else if (takeNumber >= itemList.size) {
                        realNumber = takeNumber - itemList.size //调整循环滚动显示的index
                    }
                    toShowItems[i] = itemList.get(realNumber)
                    toShowItems.get(i)!!.move(
                        (unitHeight * ((i - realNumber) % itemList.size)).toInt() - restMove
                    ) //设置滚动的相对位置
                    //            Log.e(TAG," toShowItems["+i+"] = "+ toShowItems[i].id);
                }
            }
        } else {
            //非循环模式下，滚动到边缘即停止动画
            if (moveDistance > unitHeight * itemList.size - itemNumber / 2 * unitHeight - unitHeight) {
                moveDistance =
                    ((unitHeight * itemList.size - itemNumber / 2 * unitHeight - unitHeight).toInt())
                if (moveHandler != null) {
                    moveHandler?.removeMessages(GO_ON_MOVE_REFRESH)
                    moveHandler?.sendEmptyMessage(GO_ON_MOVE_INTERRUPTED)
                }
            } else if (moveDistance < -itemNumber / 2 * unitHeight) {
                moveDistance = (-itemNumber / 2 * unitHeight).toInt()
                if (moveHandler != null) {
                    moveHandler?.removeMessages(GO_ON_MOVE_REFRESH)
                    moveHandler?.sendEmptyMessage(GO_ON_MOVE_INTERRUPTED)
                }
            }
            val move = moveDistance
            val first = itemList[0]
            val firstY = first.y + move
            val firstNumber = (firstY / unitHeight).toInt() //滚轮中显示的第一个item的index
            val restMove = (firstY - unitHeight * firstNumber).toInt() //用以保证滚动的连续性
            val takeNumberStart = firstNumber
            synchronized(toShowItems) {
                for (i in toShowItems.indices) {
                    val takeNumber: Int = takeNumberStart + i
                    var realNumber: Int = takeNumber
                    if (takeNumber < 0) {
                        realNumber = -1 //用以标识超出的部分
                    } else if (takeNumber >= itemList.size) {
                        realNumber = -1 //用以标识超出的部分
                    }
                    if (realNumber == -1) {
                        toShowItems[i] = null //设置为null，则会留出空白
                    } else {
                        toShowItems[i] = itemList.get(realNumber)
                        toShowItems.get(i)!!
                            .move((unitHeight * (i - realNumber)).toInt() - restMove) //设置滚动的相对位置
                    }
                    //            Log.e(TAG," toShowItems["+i+"] = "+ toShowItems[i].id);
                }
            }
        }

        //调用回调
        if (callListener && selectListener != null && toShowItems[itemNumber / 2] != null) {
            callbackHandler.post(Runnable {
                selectListener!!.selecting(
                    toShowItems[itemNumber / 2]!!.id,
                    toShowItems[itemNumber / 2]!!.rawText
                )
            })
        }
    }

    /**
     * 缓慢移动一段距离，移动速度为SLOW_MOVE_SPEED， 注意这个距离不是move参数，而是先将选项坐标移动move的距离以后，再判断当前应该选中的项目，然后将改项目移动到中间
     * 移动完成后调用noEmpty
     *
     * @param move 立即设置的新坐标移动距离，不是缓慢移动的距离
     */
    @Synchronized
    private fun slowMove(move: Int) {
        if (moveHandler == null) {
            return
        }
        //        Log.d(TAG,"slowMove start");
        moveHandler?.post(Runnable { //                Log.d(TAG,"slowMove run start");
            var newMove = 0
            findItemsToShow()
            //根据当前滑动方向，选择选中项来移到中心显示
            val selected = selected
            if (selected != -1) {
                newMove = itemList!![selected].moveToSelected().toInt()
                //                    Log.e(TAG,"getSelected:"+selected+"  , newMove="+newMove);
            } else {
                synchronized(toShowItems) {
                    if (move > 0) {
                        for (i in toShowItems.indices) {
                            if (toShowItems.get(i) != null && toShowItems.get(i)!!
                                    .couldSelected()
                            ) {
                                newMove = toShowItems.get(i)!!.moveToSelected().toInt()
                                //                                    Log.e(TAG, "move > 0 couldSelected:" + toShowItems[i].id);
                                break
                            }
                        }
                    } else {
                        for (i in toShowItems.indices.reversed()) {
                            if (toShowItems.get(i) != null && toShowItems.get(i)!!
                                    .couldSelected()
                            ) {
                                newMove = toShowItems.get(i)!!.moveToSelected().toInt()
                                //                                    Log.e(TAG, "move < 0 couldSelected:" + toShowItems[i].id);
                                break
                            }
                        }
                    }
                }
            }
            // 判断正负
            var m = if (newMove > 0) newMove else newMove * (-1)
            val symbol = if (newMove > 0) 1 else (-1)
            // 移动速度
            val speed = slowMoveSpeed
            while (true && m != 0) {
                m = m - speed
                if (m < 0) {
                    moveDistance -= m * symbol
                    findItemsToShow()
                    postInvalidate()
                    try {
                        Thread.sleep(10)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                    break
                }
                moveDistance -= speed * symbol
                findItemsToShow()
                postInvalidate()
                try {
                    Thread.sleep(10)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
            //                Log.d(TAG,"slowMove run end");
            noEmpty(move)
        })
        //        Log.d(TAG,"slowMove end move="+move);
    }

    /**
     * 移动到默认位置
     *
     * @param move 移动的距离
     */
    private fun defaultMove(move: Int) {
        moveDistance -= move
        findItemsToShow(false)
        postInvalidate()
    }

    /**
     * 设置数据 （第一次）
     *
     * @param data 数据集
     */
    fun setData(data: ArrayList<String>) {
        dataList = data
        initData()
    }

    /**
     * 重置数据
     *
     * @param data 数据集
     */
    fun refreshData(data: ArrayList<String>) {
        setData(data)
        findItemsToShow()
        invalidate()
    }

    /**
     * 获取返回项 id
     */
    val selected: Int
        get() {
            synchronized(toShowItems) {
                for (item: ItemObject? in toShowItems) {
                    if (item != null && item.selected()) {
                        return item.id
                    }
                }
                return -1
            }
        }

    /**
     * 获取返回的内容
     */
    val selectedText: String
        get() {
            synchronized(toShowItems) {
                for (item: ItemObject? in toShowItems) {
                    if (item != null && item.selected()) {
                        return item.rawText
                    }
                }
                return ""
            }
        }

    /**
     * 设置默认选项
     */
    fun setDefault(index: Int) {
        defaultIndex = index
        if (itemList!!.isEmpty()) {
            return
        }
        if (index > itemList.size - 1) {
            return
        }
        moveDistance = 0
        for (item in itemList) {
            item.move = 0
        }
        findItemsToShow()
        val move = itemList[index].moveToSelected()
        onEndSelecting(itemList[index])
        defaultMove(move.toInt())
    }

    private var defaultIndex //用于恢复
            = 0

    /**
     * 获取列表大小
     */
    val listSize: Int
        get() = itemList?.size ?: 0

    /**
     * 获取某项的内容
     */
    fun getItemText(index: Int): String {
        return itemList?.get(index)?.rawText ?: ""
    }

    /**
     * 对WheelView设置监听，在滑动过程或者滑动停止返回数据信息。
     */
    fun setOnSelectListener(onSelectListener: OnSelectListener?) {
        this.selectListener = onSelectListener
    }

    fun setOnInputListener(onInputListener: onInputListener?) {
        this.inputListener = onInputListener
    }

    /**
     * 获取当前展示的项目数量
     */
    fun getItemNumber(): Int {
        return itemNumber
    }

    /**
     * 设置展示的项目数量
     */
    fun setItemNumber(itemNumber: Int) {
        this.itemNumber = itemNumber
        controlHeight = itemNumber * unitHeight
        toShowItems = arrayOfNulls(itemNumber + 2)
        requestLayout()
    }

    /**
     * 获取是否循环滚动
     */
    fun isCyclic(): Boolean {
        return isCyclic
    }

    /**
     * 设置是否循环滚动
     */
    fun setCyclic(cyclic: Boolean) {
        isCyclic = cyclic
        _setIsCyclic(cyclic)
    }

    private inner class ItemObject {
        /**
         * id
         */
        var id = 0

        /**
         * 内容
         */
        private var itemText = ""

        /**
         * 原始内容，itemText可能会缩短成...，导致使用时出错
         */
        var rawText = ""
            private set

        /**
         * x坐标
         */
        var x = 0

        /**
         * y坐标,代表绝对位置，由id和unitHeight决定
         */
        var y = 0

        /**
         * 移动距离，代表滑动的相对位置，用以调整当前位置
         */
        var move = 0

        /**
         * 字体画笔
         */
        private var textPaint: TextPaint? = null

        /**
         * 字体范围矩形
         */
        private var textRect: Rect? = null
        private var shouldRefreshTextPaint = true

        /**
         * 绘制自身
         *
         * @param canvas 画板
         * @param containerWidth 容器宽度
         */
        fun drawSelf(canvas: Canvas, containerWidth: Int) {

            // 判断是否可视
            // 通过将判断移到绘制的函数开始的位置，同时放宽判断的条件，可以减少计算量，提高性能
            if (!isInView) {
                return
            }

            // 返回包围整个字符串的最小的一个Rect区域
            if (textPaint == null) {
                textPaint = TextPaint()
                textPaint?.isAntiAlias = true
            }
            if (textRect == null) {
                textRect = Rect()
            }

            // 判断是否可被选择
            if (couldSelected()) {
                textPaint?.color = selectedColor
                // 获取距离标准位置的距离
                var moveToSelect = moveToSelected()
                moveToSelect = if (moveToSelect > 0) moveToSelect else moveToSelect * -1
                // 计算当前字体大小
                val textSize =
                    normalFont + (selectedFont - normalFont) * (1.0f - moveToSelect / unitHeight)
                textPaint?.textSize = textSize
            } else {
                textPaint?.color = normalColor
                textPaint?.textSize = normalFont
            }
            if (unitHeight < Math.max(selectedFont, normalFont)) {
                //如果高度太小了，则调整字体大小，以匹配高度
                val textSize = unitHeight - lineHeight * 2
                textPaint?.textSize = textSize
                mInputText.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize)
            }
            if (shouldRefreshTextPaint) {
                //有可能导致文字消失，itemText变成空字符串，
                // 是因为文字设置过大，而containerWidth太小，
                // 本来会将无法显示的文字用"..."表示，但是连"..."本身也无法显示的时候，就会变成空字符串
                itemText = TextUtils.ellipsize(
                    itemText, textPaint, containerWidth.toFloat(),
                    TextUtils.TruncateAt.END
                ) as String
                textPaint?.getTextBounds(itemText, 0, itemText.length, textRect)
                if (selectedFont == normalFont) {
                    shouldRefreshTextPaint = false
                }
            }

            //            // 判断是否可视
            //            if (!isInView()) {
            //                return;
            //            }

            // 绘制内容
            canvas.drawText(
                itemText,
                x.toFloat() + controlWidth / 2f - (textRect?.width()?.toFloat()?.div(2f) ?: 0f),
                (y + move).toFloat() + unitHeight / 2 + (textRect?.height()?.toFloat()?.div(2f) ?: 0f),
                textPaint!!
            )
        }//放宽判断的条件，否则就不能再onDraw的开头执行，而要到textRect测量完成才能执行//            if (y + move > controlHeight || ((float)y + (float)move + (float)unitHeight / 2 + (float)textRect.height() / 2f) < 0)

        /**
         * 是否在可视界面内
         */
        @get:Synchronized
        val isInView: Boolean
            get() {

                //            if (y + move > controlHeight || ((float)y + (float)move + (float)unitHeight / 2 + (float)textRect.height() / 2f) < 0)
                return if (y + move > controlHeight || (y.toFloat() + move.toFloat() + unitHeight)
                    < 0
                ) {
                    false
                } else true
            }

        /**
         * 设置相对移动的位置
         *
         * @param _move 移动的距离
         */
        @Synchronized
        fun move(_move: Int) {
            move = _move
        }

        /**
         * 判断是否在可以选择区域内,用于在没有刚好被选中项的时候判断备选项 考虑到文字的baseLine是其底部，而y+m的高度是文字的顶部的高度 因此判断为可选区域的标准是需要减去文字的部分的
         * 也就是y+m在正中间和正中间上面一格的范围内，则判断为可选
         */
        @Synchronized
        fun couldSelected(): Boolean {
            var isSelect = true
            if (y + move <= itemNumber / 2 * unitHeight - unitHeight
                || y + move >= itemNumber / 2 * unitHeight + unitHeight
            ) {
                isSelect = false
            }
            return isSelect
        }

        /**
         * 判断是否刚好在正中间的选择区域内
         */
        @Synchronized
        fun selected(): Boolean {
            var isSelect = false
            if (textRect == null) {
                return false
            }
            if (y + move >= itemNumber / 2 * unitHeight - unitHeight / 2 + (textRect?.height()?.toFloat()
                    ?.div(2) ?: 0f)
                && (y + move
                        <= itemNumber / 2 * unitHeight + unitHeight / 2 - (textRect?.height()?.toFloat()
                    ?.div(2) ?: 0f))
            ) {
                isSelect = true
            }
            return isSelect
        }

        fun setItemText(itemText: String) {
            shouldRefreshTextPaint = true
            this.itemText = itemText
            rawText = itemText
        }

        /**
         * 获取移动到选中位置需要的距离
         */
        @Synchronized
        fun moveToSelected(): Float {
            return controlHeight / 2 - unitHeight / 2 - (y + move)
        }
    }

    interface OnSelectListener {
        /**
         * 结束选择，滑动停止时回调
         */
        fun endSelect(id: Int, text: String?)

        /**
         * 选中的内容，滑动的过程中会不断回调
         */
        fun selecting(id: Int, text: String?)
    }

    interface onInputListener {
        /**
         * 输入的内容，输入完成后，按回车键时回调
         */
        fun endInput(
            wheelView: WheelView?,
            editText: EditText?,
            text: String?
        )

        /**
         * 开始输入时回调
         *
         * @param editText 输入框控件
         * @param selected 已选内容
         */
        fun onStartInput(
            wheelView: WheelView?,
            editText: EditText?,
            selected: String?
        )
    }

    companion object {
        private const val TAG = "WheelView"

        /**
         * 刷新界面
         */
        private const val REFRESH_VIEW = 0x001

        /**
         * 持续滑动刷新界面
         */
        private const val GO_ON_MOVE_REFRESH = 10010

        /**
         * 持续滑动刷新界面结束
         */
        private const val GO_ON_MOVE_END = 10011

        /**
         * 打断持续滑动
         */
        private const val GO_ON_MOVE_INTERRUPTED = 10012
        const val DEFAULT_MASK_DARK_COLOR = -0x27000001
        const val DEFAULT_MASK_LIGHT_COLOR = -0x3f000001

        /**
         * 缓慢滚动的时候的速度
         */
        private const val SLOW_MOVE_SPEED = 1 //dp

        /**
         * 判断为点击的移动距离
         */
        private const val CLICK_DISTANCE = 2 //dp

        /**
         * 快速滑动时，移动的基础个数
         */
        private const val MOVE_NUMBER = 1

        /**
         * 连续滑动动画的绘制间隔
         */
        private const val GO_ON_REFRESH_INTERVAL_MILLIS = 10

        /**
         * 连续滑动动画的最大绘制次数
         */
        private const val SHOWTIME = 200
        fun hideInputMethod(view: View) {
            val imm: InputMethodManager = view.context
                .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        }
    }
}