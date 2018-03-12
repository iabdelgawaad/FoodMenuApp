package com.insta2apps.ibrahim.mfoodmenuapplication.data.source.database.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.insta2apps.ibrahim.mfoodmenuapplication.data.source.Item;

import java.util.List;

/**
 * Created by Ibrahim AbdelGawad on 3/12/2018.
 */
@Dao
public interface FoodMenuItemDao {

    @Query("SELECT * FROM foodmenuitem")
    List<Item> getAll();

    @Query("SELECT COUNT(*) from foodmenuitem")
    long countFoodMenuItems();

    @Insert
    void insertAll(Item... items);

    @Delete
    void delete(Item it);
}
