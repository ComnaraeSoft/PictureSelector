package com.comnaraeSoft.picture.lib.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.comnaraeSoft.picture.lib.R;
import com.comnaraeSoft.picture.lib.config.PictureSelectionConfig;
import com.comnaraeSoft.picture.lib.style.TitleBarStyle;
import com.comnaraeSoft.picture.lib.utils.StyleUtils;

/**
 * @author：luck
 * @date：2021/11/19 4:38 下午
 * @describe：PreviewTitleBar
 */
public class PreviewTitleBar extends TitleBar {

    public PreviewTitleBar(Context context) {
        super(context);
    }

    public PreviewTitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PreviewTitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setTitleBarStyle() {
        super.setTitleBarStyle();
        TitleBarStyle titleBarStyle = PictureSelectionConfig.selectorStyle.getTitleBarStyle();
        if (StyleUtils.checkStyleValidity(titleBarStyle.getPreviewTitleBackgroundColor())) {
            setBackgroundColor(titleBarStyle.getPreviewTitleBackgroundColor());
        } else if (StyleUtils.checkSizeValidity(titleBarStyle.getTitleBackgroundColor())) {
            setBackgroundColor(titleBarStyle.getTitleBackgroundColor());
        }

        if (StyleUtils.checkStyleValidity(titleBarStyle.getTitleLeftBackResource())) {
            ivLeftBack.setImageResource(titleBarStyle.getTitleLeftBackResource());
        } else if (StyleUtils.checkStyleValidity(titleBarStyle.getPreviewTitleLeftBackResource())) {
            ivLeftBack.setImageResource(titleBarStyle.getPreviewTitleLeftBackResource());
        }

        rlAlbumBg.setOnClickListener(null);
        viewAlbumClickArea.setOnClickListener(null);
        RelativeLayout.LayoutParams layoutParams = (LayoutParams) rlAlbumBg.getLayoutParams();
        layoutParams.removeRule(RelativeLayout.END_OF);
        layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        rlAlbumBg.setBackgroundResource(R.drawable.ps_ic_trans_1px);
        tvCancel.setVisibility(GONE);
        ivArrow.setVisibility(GONE);
        viewAlbumClickArea.setVisibility(GONE);
    }
}
