package cc.solart.wavesidebar

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import cc.solart.wavesidebar.adapter.CarBrandInfoAdapter
import cc.solart.wavesidebar.adapter.CityAdapter
import cc.solart.wavesidebar.bean.CarBrandInfo
import kotlinx.android.synthetic.main.activity_car_brand_info.*
import kotlinx.android.synthetic.main.activity_car_brand_info.recycler_view


class CarBrandInfoActivity : AppCompatActivity() {
    private lateinit var adapter: CarBrandInfoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_car_brand_info)

        val decoration = PinnedHeaderDecoration(this)
        decoration.registerTypePinnedHeader(1)
        recycler_view.addItemDecoration(decoration)
        recycler_view.layoutManager = LinearLayoutManager(this)

        val datas = createData()
        adapter = CarBrandInfoAdapter(this, datas)
        recycler_view.adapter = adapter

        side_view.setOnTouchLetterChangeListener { letter ->
            val pos = adapter.getLetterPosition(letter)
            if (pos != -1) {
                recycler_view.scrollToPosition(pos)
                val mLayoutManager = recycler_view.layoutManager as LinearLayoutManager
                mLayoutManager.scrollToPositionWithOffset(pos, 0)
            }
        }
    }

    private fun createData(): List<CarBrandInfo> {
        val datas = CarBrandInfo.getDatas().toMutableList()
        val letters = datas.groupBy { it.alphabetCode }.keys
//        side_view.letters = letters.toList()
        val alphabets = letters.map { alphabet ->
            CarBrandInfo().apply {
                alphabetCode = alphabet
                dataType = 1
            }
        }
        datas.addAll(alphabets)
        datas.sortWith(compareBy({ it.alphabetCode }, { it.id }))
        return datas
    }
}
