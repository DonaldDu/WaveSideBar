package cc.solart.wavesidebar.adapter;

import android.view.View;
import android.widget.TextView;

import cc.solart.turbo.BaseViewHolder;
import cc.solart.wavesidebar.R;

public class PinnedHolder extends BaseViewHolder {

    TextView city_tip;

    public PinnedHolder(View view) {
        super(view);
        city_tip = findViewById(R.id.city_tip);
    }
}
