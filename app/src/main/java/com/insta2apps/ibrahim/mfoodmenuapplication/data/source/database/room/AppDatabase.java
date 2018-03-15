package com.insta2apps.ibrahim.mfoodmenuapplication.data.source.database.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.insta2apps.ibrahim.mfoodmenuapplication.data.source.Item;

/**
 * Created by Ibrahim AbdelGawad on 3/12/2018.
 */
@Database(entities = {Item.class}, version = AppDatabase.VERSION, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    static final int VERSION = 1;

    public abstract FoodMenuItemDao getFoodMenuItemDao();
}
