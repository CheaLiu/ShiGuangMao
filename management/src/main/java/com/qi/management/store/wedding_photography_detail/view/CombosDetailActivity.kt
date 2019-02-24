package com.qi.management.store.wedding_photography_detail.view

import android.graphics.drawable.Drawable
import android.graphics.drawable.LevelListDrawable
import android.os.Bundle
import android.text.Html
import android.widget.CheckBox
import android.widget.TextView
import com.alibaba.android.arouter.facade.annotation.Route
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.qi.management.R
import com.qi.management.bean.CombosBean
import com.qi.management.store.wedding_photography_detail.adapter.CombosDetailBannerAdapter
import com.qi.management.store.wedding_photography_detail.dagger.CombosDetailModule
import com.qi.management.store.wedding_photography_detail.dagger.DaggerCombosDetailComponent
import com.qi.management.store.wedding_photography_detail.presenter.CombosDetailPresenterImpl
import com.yizhipin.base.ext.loadUrl
import com.yizhipin.base.ui.activity.BaseMvpActivity
import com.yizhipin.provider.router.RouterPath.Management.Combos_Detail
import kotlinx.android.synthetic.main.activity_combos_detail.*
import kotlinx.android.synthetic.main.item_combos_detail_banner_holder.*

@Route(path = Combos_Detail)
class CombosDetailActivity : BaseMvpActivity<CombosDetailPresenterImpl>(), CombosDetailView {

    companion object {
        const val PARAM_COMBOS_BEAN = "PARAM_COMBOS_BEAN"
    }

    override fun injectComponent() {
        DaggerCombosDetailComponent.builder().activityComponent(mActivityComponent).combosDetailModule(CombosDetailModule(this)).build().inject(this)
    }

    override fun onCreateView(): Int {
        return R.layout.activity_combos_detail
    }

    override fun initView(savedInstanceState: Bundle?) {
        backBtn.setOnClickListener { onBackPressed() }
    }

    override fun initData(savedInstanceState: Bundle?) {
        super.initData(savedInstanceState)
        val bean = intent.getSerializableExtra(PARAM_COMBOS_BEAN) as CombosBean
        val bannerImages = bean.imgurls.split(",")
        if (!bannerImages.isEmpty()) {
            for (uri in bannerImages) {
                val checkBox = CheckBox(this)
                checkBox.setButtonDrawable(R.drawable.dot)
                dotLayout.addView(checkBox)
            }
            viewPager.adapter = CombosDetailBannerAdapter(bannerImages)
        }
        titleText.text = bean.title
        countText.text = String.format(resources.getString(R.string.sellCount), bean.sellCount)
        priceText.text = "￥ " + bean.price
        marketPriceText.text = "￥ " + bean.marketPrice
        storeIcon.loadUrl(bean.imgurl)
        clothNumText.text = String.format(resources.getString(R.string.clothNum), bean.clothCount)
        negativeNumText.text = String.format(resources.getString(R.string.negativeNum), bean.filmCount)
        enterNumText.text = String.format(resources.getString(R.string.enterNum), bean.rucheCount)
        detailImageView.text = Html.fromHtml(bean.content, MImageGetter(detailImageView),null)
    }

    /**
     * html中图片地址处理
     */
    inner class MImageGetter(internal var container: TextView) : Html.ImageGetter {

        override fun getDrawable(source: String): Drawable {
            val drawable = LevelListDrawable()
            Glide.with(container).load(source).into(object : SimpleTarget<Drawable>() {
                override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                    if (resource != null) {
                        drawable.addLevel(1, 1, resource)
                        drawable.setBounds(0, 0, resource.intrinsicWidth, resource.intrinsicHeight)
                        drawable.level = 1
                        container.invalidate()
                        container.text = container.text
                    }
                }
            })
            return drawable
        }
    }
}
