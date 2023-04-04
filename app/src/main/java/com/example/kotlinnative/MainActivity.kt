package com.example.kotlinnative

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.kotlinnative.databinding.ActivityMainBinding
import com.example.kotlinnative.page.VideoPageFragment
import io.flutter.embedding.android.FlutterFragment
//测试注释提交
class MainActivity : FragmentActivity() {

    companion object {
        const val ENGINE_ID = "engineID"
    }

    private lateinit var binding: ActivityMainBinding

    private val homeFragment by lazy {
        VideoPageFragment()
    }

    private val friendFragment by lazy {
        VideoPageFragment()
    }

    private val messageFragment by lazy {

        FlutterFragmentUtil.createFlutterFragment(this, "message", "/message")
    }
    private val mineFragment by lazy {

        FlutterFragmentUtil.createFlutterFragment(this, "mine", "/mine")
    }

    private var currentFragment: Fragment = homeFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        val view = binding.root
        setContentView(view)

        supportFragmentManager.beginTransaction().add(R.id.fragment_container, homeFragment)
            .commit()

        binding.btHome.setOnClickListener { showPage(it) }
        binding.btFriend.setOnClickListener { showPage(it) }
        binding.btMessage.setOnClickListener { showPage(it) }
        binding.btMine.setOnClickListener { showPage(it) }
    }

    private fun showPage(view: View) {
        when (view.id) {
            R.id.bt_home -> homeFragment
            R.id.bt_friend -> friendFragment
            R.id.bt_message -> messageFragment
            R.id.bt_mine -> mineFragment
            else -> homeFragment
        }.let {
            if (currentFragment == it) {
                return
            }

            binding.btHome.setTextColor(getColor(R.color.bottom_button_color))
            binding.btFriend.setTextColor(getColor(R.color.bottom_button_color))
            binding.btMessage.setTextColor(getColor(R.color.bottom_button_color))
            binding.btMine.setTextColor(getColor(R.color.bottom_button_color))
            (view as Button).setTextColor(getColor(R.color.white))

            if (it.isAdded) {
                supportFragmentManager.beginTransaction().hide(currentFragment).show(it).commit()
            } else {
                supportFragmentManager.beginTransaction().hide(currentFragment)
                    .add(R.id.fragment_container, it).commit()
            }
            currentFragment = it
        }
    }

    fun hideBottomButton(hide: Boolean) {
        val visible = if (hide) View.GONE else View.VISIBLE
        binding.btHome.visibility = visible
        binding.btFriend.visibility = visible
        binding.btAdd.visibility = visible
        binding.btMessage.visibility = visible
        binding.btMine.visibility = visible
    }

}