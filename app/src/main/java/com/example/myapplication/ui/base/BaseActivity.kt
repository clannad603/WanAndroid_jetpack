package com.example.myapplication.ui.base

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.example.myapplication.logic.model.Constant
import com.example.myapplication.utils.ActivityManager
import com.example.myapplication.utils.MyPreference
import java.lang.reflect.ParameterizedType

/***
 * @Override
public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
super.onCreate(savedInstanceState, persistentState);
}
此方法导致界面不显示，原因
 */
/***
 * as关键字用做类型转化
 */
abstract class BaseActivity<VM : BaseViewModel, VB : ViewBinding> : AppCompatActivity() {
    var isLogin: Boolean by MyPreference(Constant.KEY_LOGIN, false)//取值or默认值 会调用getValue()
    lateinit var mContext: FragmentActivity
    lateinit var vm: VM
    lateinit var v: VB

    @Suppress("UNCHECKED_CAST")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val window: Window = window
//        val decorView: View = window.getDecorView()
//        val option = (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
//        decorView.systemUiVisibility = option
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
//            window.setStatusBarColor(Color.TRANSPARENT)
//        }
        val type = javaClass.genericSuperclass as ParameterizedType

        /***
         * 获取泛型类型
         */
        val clazz1 = type.actualTypeArguments[0] as Class<VM>
        /***
         * 获取第一个泛型对象转化为class类
         */
        vm = ViewModelProvider(this).get(clazz1)
        /***
         * 也可以使用type.actualTypeArguments[0]::class.java
         */
        /***
         * 使用viewmodelProvider获取vm的实例来调用
         */
        /***
         * 使用vm来调用viewmodel
         */
        val clazz2 = type.actualTypeArguments[1] as Class<VB>
        val method = clazz2.getMethod("inflate", LayoutInflater::class.java)
        v = method.invoke(null, layoutInflater) as VB

        setContentView(v.root)
        /***
         * 可直接使用v.控件来调用方法
         */
        mContext = this
        initView()
        initData()
        initVM()
        initListener()

    }

    abstract fun initVM()

    fun startAnotherActivity(clazz: Class<*>) {
        val intent = Intent(this, clazz)
        startActivity(intent)
    }

    //    /***
//     * 初始化碎片
//     */
//    fun  startFragment( id:Int,fragment: BaseFragment){
//        val fragmentManager=supportFragmentManager
//        val transaction=fragmentManager.beginTransaction()
//        transaction.add(id,fragment)
//        transaction.commit()
//    }
    abstract fun initListener()

    override fun onDestroy() {
        super.onDestroy()
        ActivityManager.finishAllActivity()
    }

    abstract fun initData()

    abstract fun initView()

}