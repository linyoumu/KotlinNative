package com.example.kotlinnative.page

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.kotlinnative.R
import com.example.kotlinnative.databinding.FragmentHomePageBinding

/**
 * 首页Fragment，首页采用原生编写
 */
class VideoPageFragment : Fragment() {

    companion object {
        const val TAG = "VideoPageFragment"
    }

    private var _binding: FragmentHomePageBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomePageBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

            binding.viewPager.apply {
            adapter = VideoPagerAdapter(requireActivity())
            setCurrentItem(1, false)
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    val normalColor = resources.getColor(R.color.dim, null)
                    val shadowColor = resources.getColor(R.color.dim_shadow, null)

                    binding.focus.setTextColor(normalColor)
                    binding.focus.setShadowLayer(5f, 0f, 5f, shadowColor)
                    binding.city.setTextColor(normalColor)
                    binding.city.setShadowLayer(5f, 0f, 5f, shadowColor)
                    binding.recommend.setTextColor(normalColor)
                    binding.recommend.setShadowLayer(5f, 0f, 5f, shadowColor)

                    when (position) {
                        0 -> binding.city.setTextColor(Color.WHITE)
                        1 -> binding.focus.setTextColor(Color.WHITE)
                        2 -> binding.recommend.setTextColor(Color.WHITE)
                    }
                }
            })
        }
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        Log.e(TAG, "onHiddenChanged : $hidden")
        (binding.viewPager.adapter as VideoPagerAdapter).onHiddenChanged(hidden)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}