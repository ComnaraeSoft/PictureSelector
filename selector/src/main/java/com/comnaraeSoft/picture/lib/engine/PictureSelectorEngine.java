package com.comnaraeSoft.picture.lib.engine;

import com.comnaraeSoft.picture.lib.entity.LocalMedia;
import com.comnaraeSoft.picture.lib.interfaces.OnInjectLayoutResourceListener;
import com.comnaraeSoft.picture.lib.interfaces.OnResultCallbackListener;

/**
 * @author：luck
 * @date：2020/4/22 11:36 AM
 * @describe：PictureSelectorEngine
 */
public interface PictureSelectorEngine {

    /**
     * Create ImageLoad Engine
     *
     * @return
     */
    ImageEngine createImageLoaderEngine();

    /**
     * Create compress Engine
     *
     * @return
     */
    CompressEngine createCompressEngine();

    /**
     * Create loader data Engine
     *
     * @return
     */
    ExtendLoaderEngine createLoaderDataEngine();

    /**
     * Create SandboxFileEngine  Engine
     *
     * @return
     */
    SandboxFileEngine createSandboxFileEngine();

    /**
     * Create LayoutResource  Listener
     *
     * @return
     */
    OnInjectLayoutResourceListener createLayoutResourceListener();

    /**
     * Create Result Listener
     *
     * @return
     */
    OnResultCallbackListener<LocalMedia> getResultCallbackListener();
}
