package cc.solart.wavesidebar.adapter;

import android.view.View;
import android.widget.TextView;

import cc.solart.turbo.BaseViewHolder;
import cc.solart.wavesidebar.R;

public class CityHolder extends BaseViewHolder {

    TextView city_name;

    public CityHolder(View view) {
        super(view);
        city_name = findViewById(R.id.city_name);
    }
}
