package com.example.myapplication.ui.base


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

abstract class BaseFragment<VM : BaseViewModel, VB : ViewBinding> : Fragment() {
    lateinit var mContext: FragmentActivity
    var contentView: View? = null
    lateinit var vm: VM
    lateinit var v: VB
    private var lazyLoaded = false

    @Suppress("UNCHECKED_CAST")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //注意 type.actualTypeArguments[0]=BaseViewModel，type.actualTypeArguments[1]=ViewBinding
        val type = javaClass.genericSuperclass as ParameterizedType
        val clazz1 = type.actualTypeArguments[0] as Class<VM>
        vm = ViewModelProvider(this).get(clazz1)

        val clazz2 = type.actualTypeArguments[1] as Class<VB>
        val method = clazz2.getMethod("inflate", LayoutInflater::class.java)
        v = method.invoke(null, layoutInflater) as VB
        mContext = context as AppCompatActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (null == contentView) {
            contentView = v.root
            initView()
            initClick()
            initData()
            initVM()
        }
        return contentView
    }

    override fun onResume() {
        super.onResume()


    }


    abstract fun initVM()

    abstract fun initView()

    abstract fun initClick()

    abstract fun initData()

    override fun onDestroyView() {
        super.onDestroyView()
        contentView = null
    }


}