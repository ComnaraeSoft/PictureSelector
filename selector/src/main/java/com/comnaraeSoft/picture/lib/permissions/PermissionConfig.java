package com.comnaraeSoft.picture.lib.permissions;

import android.Manifest;

/**
 * @author：luck
 * @date：2021/12/11 8:24 下午
 * @describe：PermissionConfig
 */
public class PermissionConfig {
    /**
     * 当前申请权限
     */
    public static String[] CURRENT_REQUEST_PERMISSION;


    /**
     * 读写权限
     */
    public final static String[] READ_WRITE_EXTERNAL_STORAGE =
            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE};
    /**
     * 写入权限
     */
    public final static String[] WRITE_EXTERNAL_STORAGE = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};

    /**
     * 相机权限
     */
    public final static String[] CAMERA = new String[]{Manifest.permission.CAMERA};

    /**
     * 录音权限
     */
    public final static String[] RECORD_AUDIO = new String[]{Manifest.permission.RECORD_AUDIO};
}
