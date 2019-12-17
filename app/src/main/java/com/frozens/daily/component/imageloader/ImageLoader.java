package com.frozens.daily.component.imageloader;

import android.content.Context;

import java.io.File;

/**
 * Created by xiang23 on 2018/3/22.
 */

public class ImageLoader {
    public static final String FILEHEAD = "file://";

    private BaseImageLoaderStrategy mStrategy;

    private static class ImageLoaderHolder {
        private static final ImageLoader INSTANCE = new ImageLoader();
    }

    public static ImageLoader getInstance() {
        return ImageLoaderHolder.INSTANCE;
    }


    /**
     * 加载图片
     *
     * @param context
     * @param config
     * @param <T>
     */
    public <T extends ImageConfig> void loadImage(Context context, T config) {
        this.mStrategy.loadImage(context, config);
    }

    /**
     * 停止加载或清理缓存
     *
     * @param context
     * @param config
     * @param <T>
     */
    public <T extends ImageConfig> void clear(Context context, T config) {
        this.mStrategy.clear(context, config);
    }


    public <T extends ImageConfig> void getBitmap(Context context, T config) {
        this.mStrategy.getBitmap(context, config);
    }

    public <T extends ImageConfig> File downloadFile(Context context, T config) {
        return this.mStrategy.downloadFile(context, config);
    }

    /**
     * 可在运行时随意切换 {@link BaseImageLoaderStrategy}
     *
     * @param strategy
     */
    public void setLoadImgStrategy(BaseImageLoaderStrategy strategy) {
        this.mStrategy = strategy;
    }

    public BaseImageLoaderStrategy getLoadImgStrategy() {
        return mStrategy;
    }


}
