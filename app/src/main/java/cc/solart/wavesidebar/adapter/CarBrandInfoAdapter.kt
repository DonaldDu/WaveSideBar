package cc.solart.wavesidebar.adapter

import android.content.Context
import android.view.ViewGroup
import cc.solart.turbo.BaseTurboAdapter
import cc.solart.turbo.BaseViewHolder
import cc.solart.wavesidebar.R
import cc.solart.wavesidebar.bean.CarBrandInfo

class CarBrandInfoAdapter(context: Context, datas: List<CarBrandInfo>) : BaseTurboAdapter<CarBrandInfo, BaseViewHolder>(context, datas) {
    override fun getDefItemViewType(position: Int): Int {
        return getItem(position).dataType
    }

    override fun onCreateDefViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return if (viewType == 0) {//default data
            CityHolder(inflateItemView(R.layout.item_brand, parent))
        } else {
            PinnedHolder(inflateItemView(R.layout.item_pinned_header, parent))
        }
    }

    override fun convert(holder: BaseViewHolder, item: CarBrandInfo) {
        if (holder is CityHolder) {
            holder.city_name.text = item.brandName
        } else {
            (holder as PinnedHolder).city_tip.text = item.alphabetCode
        }
    }

    fun getLetterPosition(letter: String): Int {
        for (i in 0 until data.size) {
            if (data[i].dataType == 1 && data[i].alphabetCode == letter) {
                return i
            }
        }
        return -1
    }
}