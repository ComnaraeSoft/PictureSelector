package com.comnaraeSoft.picture.lib.basic;

import android.app.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.comnaraeSoft.picture.lib.PictureOnlyCameraFragment;
import com.comnaraeSoft.picture.lib.config.PictureConfig;
import com.comnaraeSoft.picture.lib.config.PictureMimeType;
import com.comnaraeSoft.picture.lib.config.PictureSelectionConfig;
import com.comnaraeSoft.picture.lib.config.SelectModeConfig;
import com.comnaraeSoft.picture.lib.config.VideoQuality;
import com.comnaraeSoft.picture.lib.engine.CompressEngine;
import com.comnaraeSoft.picture.lib.engine.CropEngine;
import com.comnaraeSoft.picture.lib.engine.SandboxFileEngine;
import com.comnaraeSoft.picture.lib.entity.LocalMedia;
import com.comnaraeSoft.picture.lib.interfaces.OnCameraInterceptListener;
import com.comnaraeSoft.picture.lib.interfaces.OnPermissionsInterceptListener;
import com.comnaraeSoft.picture.lib.interfaces.OnResultCallbackListener;
import com.comnaraeSoft.picture.lib.interfaces.OnSelectLimitTipsListener;
import com.comnaraeSoft.picture.lib.manager.SelectedManager;
import com.comnaraeSoft.picture.lib.utils.DoubleUtils;
import com.comnaraeSoft.picture.lib.utils.SdkVersionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author：luck
 * @date：2022/1/18 9:33 上午
 * @describe：PictureSelectionCameraModel
 */
public final class PictureSelectionCameraModel {
    private final PictureSelectionConfig selectionConfig;
    private final PictureSelector selector;

    public PictureSelectionCameraModel(PictureSelector selector, int chooseMode) {
        this.selector = selector;
        selectionConfig = PictureSelectionConfig.getCleanInstance();
        selectionConfig.chooseMode = chooseMode;
        selectionConfig.isOnlyCamera = true;
        selectionConfig.isDisplayTimeAxis = false;
        selectionConfig.isPreviewFullScreenMode = false;
        selectionConfig.isPreviewZoomEffect = false;
        selectionConfig.isOpenClickSound = false;
    }


    /**
     * Image Compress the engine
     *
     * @param engine Image Compress the engine
     * @return
     */
    public PictureSelectionCameraModel setCompressEngine(CompressEngine engine) {
        if (PictureSelectionConfig.compressEngine != engine) {
            PictureSelectionConfig.compressEngine = engine;
            selectionConfig.isCompressEngine = true;
        } else {
            selectionConfig.isCompressEngine = false;
        }
        return this;
    }

    /**
     * Image Crop the engine
     *
     * @param engine Image Crop the engine
     * @return
     */
    public PictureSelectionCameraModel setCropEngine(CropEngine engine) {
        if (PictureSelectionConfig.cropEngine != engine) {
            PictureSelectionConfig.cropEngine = engine;
        }
        return this;
    }

    /**
     * App Sandbox file path transform
     *
     * @param engine App Sandbox path transform
     * @return
     */
    public PictureSelectionCameraModel setSandboxFileEngine(SandboxFileEngine engine) {
        if (SdkVersionUtils.isQ() && PictureSelectionConfig.sandboxFileEngine != engine) {
            PictureSelectionConfig.sandboxFileEngine = engine;
            selectionConfig.isSandboxFileEngine = true;
        } else {
            selectionConfig.isSandboxFileEngine = false;
        }
        return this;
    }

    /**
     * Intercept camera click events, and users can implement their own camera framework
     *
     * @param listener
     * @return
     */
    public PictureSelectionCameraModel setCameraInterceptListener(OnCameraInterceptListener listener) {
        PictureSelectionConfig.onCameraInterceptListener = listener;
        return this;
    }


    /**
     * Custom interception permission processing
     *
     * @param listener
     * @return
     */
    public PictureSelectionCameraModel setPermissionsInterceptListener(OnPermissionsInterceptListener listener) {
        PictureSelectionConfig.onPermissionsEventListener = listener;
        return this;
    }

    /**
     * Custom limit tips
     *
     * @param listener
     * @return
     */
    public PictureSelectionCameraModel setSelectLimitTipsListener(OnSelectLimitTipsListener listener) {
        PictureSelectionConfig.onSelectLimitTipsListener = listener;
        return this;
    }

    /**
     * Do you want to open a foreground service to prevent the system from reclaiming the memory
     * of some models due to the use of cameras
     *
     * @param isForeground
     * @return
     */
    public PictureSelectionCameraModel isCameraForegroundService(boolean isForeground) {
        selectionConfig.isCameraForegroundService = isForeground;
        return this;
    }

    /**
     * Choose between photographing and shooting in ofAll mode
     *
     * @param ofAllCameraType {@link PictureMimeType.ofImage or PictureMimeType.ofVideo}
     *                        The default is ofAll() mode
     * @return
     */
    public PictureSelectionCameraModel setOfAllCameraType(int ofAllCameraType) {
        selectionConfig.ofAllCameraType = ofAllCameraType;
        return this;
    }

    /**
     * Do you need to display the original controller
     * <p>
     * It needs to be used with setSandboxFileEngine
     * {@link LocalMedia .setOriginalPath()}
     * </p>
     *
     * @param isOriginalControl
     * @return
     */
    public PictureSelectionCameraModel isOriginalControl(boolean isOriginalControl) {
        selectionConfig.isOriginalControl = isOriginalControl;
        selectionConfig.isCheckOriginalImage = isOriginalControl;
        return this;
    }

    /**
     * The video quality output mode is only for system recording, and there are only two modes: poor quality or high quality
     *
     * @param videoQuality video quality and 0 or 1
     *                     Use {@link VideoQuality}
     *                     <p>
     *                     There are limitations, only high or low
     *                     </p>
     * @return
     */
    @Deprecated
    public PictureSelectionCameraModel setVideoQuality(int videoQuality) {
        selectionConfig.videoQuality = videoQuality;
        return this;
    }


    /**
     * # file size The unit is KB
     *
     * @param fileKbSize Filter max file size
     * @return
     */
    public PictureSelectionCameraModel setSelectMaxFileSize(long fileKbSize) {
        if (fileKbSize >= PictureConfig.MB) {
            selectionConfig.selectMaxFileSize = fileKbSize;
        } else {
            selectionConfig.selectMaxFileSize = fileKbSize * 1024;
        }
        return this;
    }

    /**
     * # file size The unit is KB
     *
     * @param fileKbSize Filter min file size
     * @return
     */
    public PictureSelectionCameraModel setSelectMinFileSize(long fileKbSize) {
        if (fileKbSize >= PictureConfig.MB) {
            selectionConfig.selectMinFileSize = fileKbSize;
        } else {
            selectionConfig.selectMinFileSize = fileKbSize * 1024;
        }
        return this;
    }


    /**
     * camera output image format
     *
     * @param imageFormat PictureSelector media format
     * @return
     */
    public PictureSelectionCameraModel setCameraImageFormat(String imageFormat) {
        selectionConfig.cameraImageFormat = imageFormat;
        return this;
    }

    /**
     * camera output image format
     *
     * @param imageFormat PictureSelector media format
     * @return
     */
    public PictureSelectionCameraModel setCameraImageFormatForQ(String imageFormat) {
        selectionConfig.cameraImageFormatForQ = imageFormat;
        return this;
    }

    /**
     * camera output video format
     *
     * @param videoFormat PictureSelector media format
     * @return
     */
    public PictureSelectionCameraModel setCameraVideoFormat(String videoFormat) {
        selectionConfig.cameraVideoFormat = videoFormat;
        return this;
    }

    /**
     * camera output video format
     *
     * @param videoFormat PictureSelector media format
     * @return
     */
    public PictureSelectionCameraModel setCameraVideoFormatForQ(String videoFormat) {
        selectionConfig.cameraVideoFormatForQ = videoFormat;
        return this;
    }

    /**
     * The max duration of video recording. If it is system recording, there may be compatibility problems
     *
     * @param maxSecond video record second
     * @return
     */
    public PictureSelectionCameraModel setRecordVideoMaxSecond(int maxSecond) {
        selectionConfig.recordVideoMaxSecond = maxSecond;
        return this;
    }

    /**
     * @param minSecond video record second
     * @return
     */
    public PictureSelectionCameraModel setRecordVideoMinSecond(int minSecond) {
        selectionConfig.recordVideoMinSecond = minSecond;
        return this;
    }


    /**
     * Select the max number of seconds for video or audio support
     *
     * @param maxDurationSecond select video max second
     * @return
     */
    public PictureSelectionCameraModel setSelectMaxDurationSecond(int maxDurationSecond) {
        selectionConfig.selectMaxDurationSecond = maxDurationSecond * 1000;
        return this;
    }

    /**
     * Select the min number of seconds for video or audio support
     *
     * @param minDurationSecond select video min second
     * @return
     */
    public PictureSelectionCameraModel setSelectMinDurationSecond(int minDurationSecond) {
        selectionConfig.selectMinDurationSecond = minDurationSecond * 1000;
        return this;
    }


    /**
     * @param outPutCameraDir Camera output path
     *                        <p>Audio mode setting is not supported</p>
     * @return
     */
    public PictureSelectionCameraModel setOutputCameraDir(String outPutCameraDir) {
        selectionConfig.outPutCameraDir = outPutCameraDir;
        return this;
    }

    /**
     * @param outPutAudioDir Audio output path
     * @return
     */
    public PictureSelectionCameraModel setOutputAudioDir(String outPutAudioDir) {
        selectionConfig.outPutAudioDir = outPutAudioDir;
        return this;
    }

    /**
     * Camera IMAGE custom local file name
     * # Such as xxx.png
     *
     * @param fileName
     * @return
     */
    public PictureSelectionCameraModel setOutputCameraImageFileName(String fileName) {
        selectionConfig.outPutCameraImageFileName = fileName;
        return this;
    }

    /**
     * Camera VIDEO custom local file name
     * # Such as xxx.png
     *
     * @param fileName
     * @return
     */
    public PictureSelectionCameraModel setOutputCameraVideoFileName(String fileName) {
        selectionConfig.outPutCameraVideoFileName = fileName;
        return this;
    }

    /**
     * Camera VIDEO custom local file name
     * # Such as xxx.amr
     *
     * @param fileName
     * @return
     */
    public PictureSelectionCameraModel setOutputAudioFileName(String fileName) {
        selectionConfig.outPutAudioFileName = fileName;
        return this;
    }


    /**
     * @param selectedList Select the selected picture set
     * @return
     */
    public PictureSelectionCameraModel setSelectedData(List<LocalMedia> selectedList) {
        if (selectedList == null) {
            return this;
        }
        if (selectionConfig.selectionMode == SelectModeConfig.SINGLE && selectionConfig.isDirectReturnSingle) {
            SelectedManager.clearSelectResult();
        } else {
            SelectedManager.addAllSelectResult(new ArrayList<>(selectedList));
        }
        return this;
    }

    /**
     * After recording with the system camera, does it support playing the video immediately using the system player
     *
     * @param isQuickCapture
     * @return
     */
    public PictureSelectionCameraModel isQuickCapture(boolean isQuickCapture) {
        selectionConfig.isQuickCapture = isQuickCapture;
        return this;
    }

    /**
     * Set camera direction (after default image)
     */
    public PictureSelectionCameraModel isCameraAroundState(boolean isCameraAroundState) {
        selectionConfig.isCameraAroundState = isCameraAroundState;
        return this;
    }

    /**
     * Camera image rotation, automatic correction
     */
    public PictureSelectionCameraModel isCameraRotateImage(boolean isCameraRotateImage) {
        selectionConfig.isCameraRotateImage = isCameraRotateImage;
        return this;
    }


    /**
     * Start PictureSelector
     *
     * @param call
     */
    public void forResult(OnResultCallbackListener<LocalMedia> call) {
        if (!DoubleUtils.isFastDoubleClick()) {
            Activity activity = selector.getActivity();
            if (activity == null) {
                throw new NullPointerException("Activity cannot be null");
            }
            if (call == null) {
                throw new NullPointerException("OnResultCallbackListener cannot be null");
            }
            // 绑定回调监听
            selectionConfig.isResultListenerBack = true;
            selectionConfig.isActivityResultBack = false;
            PictureSelectionConfig.onResultCallListener = call;
            FragmentManager fragmentManager = null;
            if (activity instanceof AppCompatActivity) {
                fragmentManager = ((AppCompatActivity) activity).getSupportFragmentManager();
            } else if (activity instanceof FragmentActivity) {
                fragmentManager = ((FragmentActivity) activity).getSupportFragmentManager();
            }
            if (fragmentManager == null) {
                throw new NullPointerException("FragmentManager cannot be null");
            }
            Fragment fragment = fragmentManager.findFragmentByTag(PictureOnlyCameraFragment.TAG);
            if (fragment != null) {
                fragmentManager.beginTransaction().remove(fragment).commitAllowingStateLoss();
            }
            FragmentInjectManager.injectSystemRoomFragment(fragmentManager,
                    PictureOnlyCameraFragment.TAG, PictureOnlyCameraFragment.newInstance());
        }
    }


    /**
     * Start PictureSelector
     * <p>
     * The {@link IBridgePictureBehavior} interface needs to be
     * implemented in the activity or fragment you call to receive the returned results
     * </p>
     */
    public void forResult() {
        if (!DoubleUtils.isFastDoubleClick()) {
            Activity activity = selector.getActivity();
            if (activity == null) {
                throw new NullPointerException("Activity cannot be null");
            }
            selectionConfig.isResultListenerBack = false;
            selectionConfig.isActivityResultBack = true;
            FragmentManager fragmentManager = null;
            if (activity instanceof AppCompatActivity) {
                fragmentManager = ((AppCompatActivity) activity).getSupportFragmentManager();
            } else if (activity instanceof FragmentActivity) {
                fragmentManager = ((FragmentActivity) activity).getSupportFragmentManager();
            }
            if (fragmentManager == null) {
                throw new NullPointerException("FragmentManager cannot be null");
            }
            if (!(activity instanceof IBridgePictureBehavior)) {
                throw new NullPointerException("Use only camera openCamera mode," +
                        "Activity or Fragment interface needs to be implemented " + IBridgePictureBehavior.class);
            }
            Fragment fragment = fragmentManager.findFragmentByTag(PictureOnlyCameraFragment.TAG);
            if (fragment != null) {
                fragmentManager.beginTransaction().remove(fragment).commitAllowingStateLoss();
            }
            FragmentInjectManager.injectSystemRoomFragment(fragmentManager,
                    PictureOnlyCameraFragment.TAG, PictureOnlyCameraFragment.newInstance());
        }
    }
}
