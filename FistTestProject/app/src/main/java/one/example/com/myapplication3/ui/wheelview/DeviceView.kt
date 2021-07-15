//package one.example.com.myapplication3.ui.wheelview
//
//import android.content.Context
//import android.content.res.TypedArray
//import android.graphics.Color
//import android.util.AttributeSet
//import android.util.Log
//import android.view.LayoutInflater
//import android.widget.LinearLayout
//import androidx.databinding.DataBindingUtil
//import one.example.com.myapplication3.R
//
///**
//
// * @Author Albert
// * @Date 2021/3/27-14:03
// * 描述:网关子控件，用于显示设备电量信息
// */
//class DeviceView(context: Context, attrs: AttributeSet) :
//    LinearLayout(context, attrs) {
//    private var TAG = "DeviceView"
//
//    lateinit var binding: DeviceInfoViewBinding
//
//    /**
//     * 中间的控件
//     */
//    var isMain = false
//
//
//    var color: Int = 0
//
//    var value = 0f
//
//    /**
//     * 当前状态是否可用，为0或者设备离线时不可用
//     */
//    var isEnable = true
//
//
//    init {
//        Log.e(TAG, "------------------------init-----------------------")
////        binding = GateviewBinding.inflate(LayoutInflater.from(getContext()), this, true)
//        binding = DataBindingUtil.inflate(
//            LayoutInflater.from(context),
//            R.layout.device_info_view,
//            this,
//            true
//        )
//        val typedArray: TypedArray =
//            getContext().obtainStyledAttributes(attrs, R.styleable.DeviceView)
//        typedArray.getDrawable(R.styleable.DeviceView_gate_bg)?.let {
//            binding.layBg.background = it
//        }
//        typedArray.getBoolean(R.styleable.DeviceView_gate_is_main, false).let {
//            isMain = it
//            binding.tvValue.paint.isFakeBoldText = true
//        }
//
//        typedArray.getFloat(
//            R.styleable.DeviceView_gate_text_size,
//            9f
//        ).let {
//            binding.tvValue.textSize = it.toFloat()
//        }
//
//        typedArray.getFloat(
//            R.styleable.DeviceView_gate_name_size,
//            10f
//        ).let {
//            binding.tvName.textSize = it
//        }
//
//        typedArray.getDimension(
//            R.styleable.DeviceView_gate_value_margin_top,
//            4f
//        ).let {
//            val layoutParams = binding.tvValue.layoutParams as MarginLayoutParams
//            layoutParams.topMargin = it.toInt()
//
//        }
//
//        typedArray.getDimension(
//            R.styleable.DeviceView_gate_name_margin_top,
//            1f
//        ).let {
//            val layoutParams = binding.tvValue.layoutParams as MarginLayoutParams
//            layoutParams.topMargin = it.toInt()
//        }
//
//
//        color = typedArray.getColor(R.styleable.DeviceView_gate_color, Color.RED)
//
//        binding.tvName.text = typedArray.getString(R.styleable.DeviceView_gate_name) ?: ""
//
//        val resourceId = typedArray.getResourceId(R.styleable.DeviceView_gate_icon, 0)
//        binding.ivIcon.setImageResource(resourceId)
//    }
//
//
//    fun setText(text: String) {
//        binding.tvName.text = text
//    }
//
//    fun setValue(text: String, unit: String) {
//        try {
//            value = text.toFloat()
//        } catch (e: Exception) {
//            value = 0f
//        }
//        binding.tvValue.text = text + unit
//    }
//}