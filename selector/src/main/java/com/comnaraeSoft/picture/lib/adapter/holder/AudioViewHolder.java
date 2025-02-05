package com.comnaraeSoft.picture.lib.adapter.holder;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.comnaraeSoft.picture.lib.R;
import com.comnaraeSoft.picture.lib.config.PictureSelectionConfig;
import com.comnaraeSoft.picture.lib.entity.LocalMedia;
import com.comnaraeSoft.picture.lib.style.SelectMainStyle;
import com.comnaraeSoft.picture.lib.utils.DateUtils;
import com.comnaraeSoft.picture.lib.utils.StyleUtils;

/**
 * @author：luck
 * @date：2021/11/20 3:59 下午
 * @describe：AudioViewHolder
 */
public class AudioViewHolder extends BaseRecyclerMediaHolder {
    private final TextView tvDuration;

    public AudioViewHolder(@NonNull View itemView, PictureSelectionConfig config) {
        super(itemView, config);
        tvDuration = itemView.findViewById(R.id.tv_duration);
        SelectMainStyle adapterStyle = PictureSelectionConfig.selectorStyle.getSelectMainStyle();
        int drawableLeft = adapterStyle.getAdapterDurationDrawableLeft();
        if (StyleUtils.checkStyleValidity(drawableLeft)) {
            tvDuration.setCompoundDrawablesRelativeWithIntrinsicBounds(drawableLeft, 0, 0, 0);
        }
        int textSize = adapterStyle.getAdapterDurationTextSize();
        if (StyleUtils.checkSizeValidity(textSize)) {
            tvDuration.setTextSize(textSize);
        }
        int textColor = adapterStyle.getAdapterDurationTextColor();
        if (StyleUtils.checkStyleValidity(textColor)) {
            tvDuration.setTextColor(textColor);
        }

        int shadowBackground = adapterStyle.getAdapterDurationBackgroundResources();
        if (StyleUtils.checkStyleValidity(shadowBackground)) {
            tvDuration.setBackgroundResource(shadowBackground);
        }

        int[] durationGravity = adapterStyle.getAdapterDurationGravity();
        if (StyleUtils.checkArrayValidity(durationGravity)) {
            if (tvDuration.getLayoutParams() instanceof RelativeLayout.LayoutParams) {
                ((RelativeLayout.LayoutParams) tvDuration.getLayoutParams()).removeRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                for (int i : durationGravity) {
                    ((RelativeLayout.LayoutParams) tvDuration.getLayoutParams()).addRule(i);
                }
            }
        }
    }

    @Override
    public void bindData(LocalMedia media, int position) {
        super.bindData(media, position);
        tvDuration.setText(DateUtils.formatDurationTime(media.getDuration()));
    }

    @Override
    protected void loadCover(String path) {
        ivPicture.setImageResource(R.drawable.ps_audio_placeholder);
    }
}
