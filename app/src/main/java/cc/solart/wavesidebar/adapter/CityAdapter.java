package cc.solart.wavesidebar.adapter;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import cc.solart.turbo.BaseTurboAdapter;
import cc.solart.turbo.BaseViewHolder;
import cc.solart.wavesidebar.R;
import cc.solart.wavesidebar.bean.City;

public class CityAdapter extends BaseTurboAdapter<City, BaseViewHolder> {

    public CityAdapter(Context context) {
        super(context);
    }

    public CityAdapter(Context context, List<City> data) {
        super(context, data);
    }

    @Override
    protected int getDefItemViewType(int position) {
        City city = getItem(position);
        return city.type;
    }

    @Override
    protected BaseViewHolder onCreateDefViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0)
            return new CityHolder(inflateItemView(R.layout.item_city, parent));
        else
            return new PinnedHolder(inflateItemView(R.layout.item_pinned_header, parent));
    }


    @Override
    protected void convert(BaseViewHolder holder, City item) {
        if (holder instanceof CityHolder) {
            ((CityHolder) holder).city_name.setText(item.name);
        } else {
            String letter = item.pys.substring(0, 1);
            ((PinnedHolder) holder).city_tip.setText(letter);
        }
    }

    public int getLetterPosition(String letter) {
        for (int i = 0; i < getData().size(); i++) {
            if (getData().get(i).type == 1 && getData().get(i).pys.equals(letter)) {
                return i;
            }
        }
        return -1;
    }


}
