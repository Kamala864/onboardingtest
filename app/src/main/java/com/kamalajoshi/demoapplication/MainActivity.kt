package com.kamalajoshi.demoapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private  lateinit var onboardingItemsAdapter: OnboardingItemsAdapter
    private lateinit var indicatorContainer:LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setOnboardingItem()
        setupIndicators()
        setCurrentIndicator(0)
    }
    private fun setOnboardingItem(){
        onboardingItemsAdapter= OnboardingItemsAdapter(
            listOf(
                OnboardingItem(
                    onboardingImage = R.drawable.onboard1,
                    title= "Varities of gifts",
                    description = "Choose your of gift from bunch of gifts and save your valuable time for your loved once"
                ),
                OnboardingItem(
                    onboardingImage = R.drawable.onboard2,
                    title= "On time delivery",
                    description ="deliver your gift on shedueled time and places. extend your hand ful of gifts everywhere"
                ),
                OnboardingItem(
                    onboardingImage = R.drawable.onboard3,
                    title= "Easy payment",
                    description ="pay as you which. payments are available - cash on delivery - card payment- esewa - khalti."
                )
            )

        )
        val onboardingViewPager= findViewById<ViewPager2>(R.id.onboardingViewPager)
        onboardingViewPager.adapter=onboardingItemsAdapter
        onboardingViewPager.registerOnPageChangeCallback(object:
            ViewPager2.OnPageChangeCallback(){
            override fun onPageScrollStateChanged(position: Int) {
                super.onPageScrollStateChanged(position)
                setCurrentIndicator(position)
            }
            })
        (onboardingViewPager.getChildAt(0)as RecyclerView).overScrollMode=RecyclerView.OVER_SCROLL_NEVER
    }
    private fun navigationActivity(){
        startActivity(Intent)
    }
    private fun setupIndicators(){
        indicatorContainer = findViewById(R.id.indicatorsContainer)
        val indicators = arrayOfNulls<ImageView>(onboardingItemsAdapter.itemCount)
        val layoutParams:LinearLayout.LayoutParams=
            LinearLayout.LayoutParams(WRAP_CONTENT,WRAP_CONTENT)
        layoutParams.setMargins(8,0,8,0)
        for (i in indicators.indices){
            indicators[i]= ImageView(application)
            indicators[i]?.let{
               it.setImageDrawable(
                   ContextCompat.getDrawable(
                       applicationContext,
                       R.drawable.indicator_inactive_background
                   )

               )

                it.layoutParams=layoutParams
                indicatorsContainer.addView(it)
            }
        }
    }
    private fun setCurrentIndicator(position:Int){
        val childCount=indicatorContainer.childCount
        for(i in 0 until childCount){
            val imageView=indicatorContainer.getChildAt(i)as ImageView
            if(i==position){
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_active_background
                    )
                )

            }else{
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive_background
                    )
                )
            }
        }
    }
}


