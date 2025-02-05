package com.comnaraeSoft.pictureselector;

import android.content.Context;
import android.util.Log;

import com.comnaraeSoft.picture.lib.config.InjectResourceSource;
import com.comnaraeSoft.picture.lib.engine.CompressEngine;
import com.comnaraeSoft.picture.lib.engine.ExtendLoaderEngine;
import com.comnaraeSoft.picture.lib.engine.ImageEngine;
import com.comnaraeSoft.picture.lib.engine.PictureSelectorEngine;
import com.comnaraeSoft.picture.lib.engine.SandboxFileEngine;
import com.comnaraeSoft.picture.lib.entity.LocalMedia;
import com.comnaraeSoft.picture.lib.interfaces.OnInjectLayoutResourceListener;
import com.comnaraeSoft.picture.lib.interfaces.OnResultCallbackListener;

import java.util.ArrayList;

/**
 * @author：luck
 * @date：2020/4/22 12:15 PM
 * @describe：PictureSelectorEngineImp
 */
public class PictureSelectorEngineImp implements PictureSelectorEngine {
    private static final String TAG = PictureSelectorEngineImp.class.getSimpleName();

    /**
     * 重新创建{@link ImageEngine}引擎
     *
     * @return
     */
    @Override
    public ImageEngine createImageLoaderEngine() {
        // TODO 这种情况是内存极度不足的情况下，比如开启开发者选项中的不保留活动或后台进程限制，导致ImageEngine被回收
        return GlideEngine.createGlideEngine();
    }

    /**
     * 重新创建{@link CompressEngine}引擎
     *
     * @return
     */
    @Override
    public CompressEngine createCompressEngine() {
        // TODO 这种情况是内存极度不足的情况下，比如开启开发者选项中的不保留活动或后台进程限制，导致CompressEngine被回收
        return null;
    }

    /**
     * 重新创建{@link ExtendLoaderEngine}引擎
     *
     * @return
     */
    @Override
    public ExtendLoaderEngine createLoaderDataEngine() {
        // TODO 这种情况是内存极度不足的情况下，比如开启开发者选项中的不保留活动或后台进程限制，导致ExtendLoaderEngine被回收
        return null;
    }

    /**
     * 重新创建{@link SandboxFileEngine}引擎
     *
     * @return
     */
    @Override
    public SandboxFileEngine createSandboxFileEngine() {
        // TODO 这种情况是内存极度不足的情况下，比如开启开发者选项中的不保留活动或后台进程限制，导致SandboxFileEngine被回收
        return null;
    }

    /**
     * 如果出现内存不足导致OnInjectLayoutResourceListener被回收，需要重新引入自定义布局
     *
     * @return
     */
    @Override
    public OnInjectLayoutResourceListener createLayoutResourceListener() {
        return new OnInjectLayoutResourceListener() {
            @Override
            public int getLayoutResourceId(Context context, int resourceSource) {
                switch (resourceSource) {
                    case InjectResourceSource.MAIN_SELECTOR_LAYOUT_RESOURCE:
                        return R.layout.ps_custom_fragment_selector;
                    case InjectResourceSource.PREVIEW_LAYOUT_RESOURCE:
                        return R.layout.ps_custom_fragment_preview;
                    case InjectResourceSource.MAIN_ITEM_IMAGE_LAYOUT_RESOURCE:
                        return R.layout.ps_custom_item_grid_image;
                    case InjectResourceSource.MAIN_ITEM_VIDEO_LAYOUT_RESOURCE:
                        return R.layout.ps_custom_item_grid_video;
                    case InjectResourceSource.MAIN_ITEM_AUDIO_LAYOUT_RESOURCE:
                        return R.layout.ps_custom_item_grid_audio;
                    case InjectResourceSource.ALBUM_ITEM_LAYOUT_RESOURCE:
                        return R.layout.ps_custom_album_folder_item;
                    case InjectResourceSource.PREVIEW_ITEM_IMAGE_LAYOUT_RESOURCE:
                        return R.layout.ps_custom_preview_image;
                    case InjectResourceSource.PREVIEW_ITEM_VIDEO_LAYOUT_RESOURCE:
                        return R.layout.ps_custom_preview_video;
                    case InjectResourceSource.PREVIEW_GALLERY_ITEM_LAYOUT_RESOURCE:
                        return R.layout.ps_custom_preview_gallery_item;
                    default:
                        return 0;
                }
            }
        };
    }

    @Override
    public OnResultCallbackListener<LocalMedia> getResultCallbackListener() {
        return new OnResultCallbackListener<LocalMedia>() {
            @Override
            public void onResult(ArrayList<LocalMedia> result) {
                // TODO 这种情况是内存极度不足的情况下，比如开启开发者选项中的不保留活动或后台进程限制，导致OnResultCallbackListener被回收
                // 可以在这里进行一些补救措施，通过广播或其他方式将结果推送到相应页面，防止结果丢失的情况
                Log.i(TAG, "onResult:" + result.size());
            }

            @Override
            public void onCancel() {
                Log.i(TAG, "PictureSelector onCancel");
            }
        };
    }
}
