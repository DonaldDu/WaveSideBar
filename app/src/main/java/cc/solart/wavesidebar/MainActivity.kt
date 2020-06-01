package cc.solart.wavesidebar

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

import java.lang.reflect.Type
import java.util.ArrayList
import java.util.Collections

import cc.solart.wave.WaveSideBarView
import cc.solart.wavesidebar.adapter.CityAdapter
import cc.solart.wavesidebar.bean.City
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: CityAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler_view.layoutManager = LinearLayoutManager(this)

        val decoration = PinnedHeaderDecoration(this)
        decoration.registerTypePinnedHeader(1, object : PinnedHeaderDecoration.PinnedHeaderCreator {
            override fun create(parent: RecyclerView, adapterPosition: Int): Boolean {
                return true
            }
        })
        recycler_view.addItemDecoration(decoration)

        Thread(Runnable {
            val listType = object : TypeToken<ArrayList<City>>() {

            }.type
            val gson = Gson()
            val list = gson.fromJson<List<City>>(City.DATA, listType)
            Collections.sort(list, LetterComparator())
            runOnUiThread {
                adapter = CityAdapter(this@MainActivity, list)
                recycler_view.adapter = adapter
            }
        }).start()

        side_view.setOnTouchLetterChangeListener { letter ->
            val pos = adapter.getLetterPosition(letter)
            if (pos != -1) {
                recycler_view.scrollToPosition(pos)
                val mLayoutManager = recycler_view.layoutManager as LinearLayoutManager?
                mLayoutManager!!.scrollToPositionWithOffset(pos, 0)
            }
        }
    }
}
