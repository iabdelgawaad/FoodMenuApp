package com.insta2apps.ibrahim.minddownloaderlib.utils;

import android.graphics.Bitmap;

import com.insta2apps.ibrahim.minddownloaderlib.interfaces.Cache;

/**
 * Created by Ibrahim AbdelGawad on 4/8/2018.
 * //Just to demo Bitmap caching
 */

public class ImageCache<T> extends android.util.LruCache<String, Bitmap> implements Cache<Bitmap> {
    private int maxSize;
    private static ImageCache<Bitmap> instance = null;

    /**
     * @param maxSize for caches that do not override {@link #sizeOf}, this is
     *                the maximum number of entries in the cache. For all other caches,
     *                this is the maximum sum of the sizes of the entries in this cache.
     */
    private ImageCache(int maxSize) {
        super(maxSize);
        this.maxSize = maxSize;
    }

    public static ImageCache<Bitmap> getInstance(int cacheSize) {
        if (instance == null) {
            instance = new ImageCache<>(cacheSize);
        }
        return instance;
    }

    @Override
    protected int sizeOf(String key, Bitmap bitmap) {
        // The cache size will be measured in kilobytes rather than
        // number of items.
        return bitmap.getByteCount() / 1024;
    }

    @Override
    protected void entryRemoved(boolean evicted, String key, Bitmap oldValue, Bitmap newValue) {
        super.entryRemoved(evicted, key, oldValue, newValue);
    }

    @Override
    public Bitmap pop(String key) {
        return get(key);
    }

    @Override
    public void set(String key, Bitmap value) {
        put(key, value);
    }

    @Override
    public void setMaximumCacheSize(int size) {
        this.maxSize = size;
    }

    @Override
    public int getMaximumCacheSize() {
        return maxSize;
    }
}
