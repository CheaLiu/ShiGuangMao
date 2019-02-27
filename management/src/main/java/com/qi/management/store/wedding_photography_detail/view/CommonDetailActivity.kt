package com.qi.management.store.wedding_photography_detail.view

import android.graphics.drawable.Drawable
import android.graphics.drawable.LevelListDrawable
import android.os.Bundle
import android.text.Html
import android.widget.TextView
import com.alibaba.android.arouter.facade.annotation.Route
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.qi.management.R
import com.qi.management.bean.CombosBean
import com.qi.management.store.wedding_photography_detail.dagger.CombosDetailModule
import com.qi.management.store.wedding_photography_detail.dagger.DaggerCombosDetailComponent
import com.qi.management.store.wedding_photography_detail.presenter.CombosDetailPresenterImpl
import com.yizhipin.base.ext.loadUrl
import com.yizhipin.base.ui.activity.BaseMvpActivity
import com.yizhipin.base.widgets.BannerImageLoader
import com.yizhipin.provider.router.RouterPath.Management.Combos_Detail
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import kotlinx.android.synthetic.main.activity_combos_detail.*
import kotlinx.android.synthetic.main.item_combos_detail_banner_holder.*
import java.math.BigDecimal
import java.math.RoundingMode

@Route(path = Combos_Detail)
class CommonDetailActivity : BaseMvpActivity<CombosDetailPresenterImpl>(), CombosDetailView {

    companion object {
        const val PARAM_COMBOS_BEAN = "PARAM_COMBOS_BEAN"
        const val PARAM_TITLE = "PARAM_TITLE"
    }

    override fun injectComponent() {
        DaggerCombosDetailComponent.builder().activityComponent(mActivityComponent).combosDetailModule(CombosDetailModule(this)).build().inject(this)
    }

    override fun onCreateView(): Int {
        return R.layout.activity_combos_detail
    }

    override fun initView(savedInstanceState: Bundle?) {
        titleDetailView.setOnLeftIconClickListener { onBackPressed() }
    }

    override fun initData(savedInstanceState: Bundle?) {
        super.initData(savedInstanceState)
        val bean = intent.getSerializableExtra(PARAM_COMBOS_BEAN) as CombosBean
        val title = intent.getStringExtra(PARAM_TITLE)
        titleDetailView.setTitle(title)
        titleText.text = bean.title
        countText.text = String.format(resources.getString(R.string.sellCount), bean.sellCount)
        priceText.text = "￥ " + bean.price
        marketPriceText.text = "￥ " + bean.marketPrice
        storeIcon.loadUrl(bean.imgurl)
        storeNameText.text = bean.storeName
        clothNumText.text = String.format(resources.getString(R.string.clothNum), bean.clothCount)
        negativeNumText.text = String.format(resources.getString(R.string.negativeNum), bean.filmCount)
        enterNumText.text = String.format(resources.getString(R.string.enterNum), bean.rucheCount)
        detailImageView.text = Html.fromHtml(bean.content, MImageGetter(detailImageView), null)
        initBanner()
        val bannerImages = bean.imgurls.split(",") as MutableList<String>
        //设置图片集合
        banner.setImages(bannerImages)
        //banner设置方法全部调用完毕时最后调用
        banner.start()
    }

    private fun initBanner() {
        //设置图片加载器
        banner.setImageLoader(BannerImageLoader())
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage)
        //设置自动轮播，默认为true
        banner.isAutoPlay(true)
        //设置轮播时间
        banner.setDelayTime(2000)
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER)
    }


    /**
     * html中图片地址处理
     */
    inner class MImageGetter(internal var container: TextView) : Html.ImageGetter {

        override fun getDrawable(source: String): Drawable {
            val drawable = LevelListDrawable()
            Glide.with(container).load(source).into(object : SimpleTarget<Drawable>() {
                override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                    val scale = BigDecimal(resource.intrinsicWidth).divide(BigDecimal(resource.intrinsicHeight), 2, RoundingMode.HALF_UP)
                    drawable.addLevel(1, 1, resource)
                    drawable.setBounds(0, 0, container.width, BigDecimal(container.width).divide(scale, 0, RoundingMode.HALF_UP).toInt())
                    drawable.level = 1
                    container.invalidate()
                    container.text = container.text
                }
            })
            return drawable
        }
    }
}
