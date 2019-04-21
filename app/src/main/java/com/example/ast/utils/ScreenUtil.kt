package com.dbnow.hw.utils

import android.content.Context
import android.content.res.Configuration

/**
 * Created by dfgden on 9/8/17.
 */

object ScreenUtil {


    fun isInLandscapeOrientation(context: Context): Boolean {
        val configuration = context.resources.configuration
        return configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
    }

    fun hasSmallScreen(context: Context): Boolean {
        return getScreenSize(context) == Configuration.SCREENLAYOUT_SIZE_SMALL
    }

    fun hasNormalScreen(context: Context): Boolean {
        return getScreenSize(context) == Configuration.SCREENLAYOUT_SIZE_NORMAL
    }

    fun hasLargeScreen(context: Context): Boolean {
        return getScreenSize(context) == Configuration.SCREENLAYOUT_SIZE_LARGE
    }

    fun hasXLargeScreen(context: Context): Boolean {
        return getScreenSize(context) == Configuration.SCREENLAYOUT_SIZE_XLARGE
    }

    fun getScreenSize(context: Context): Int {
        val configuration = context.resources.configuration
        return configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK
    }

    fun getDisplayWidth(context: Context): Int {
        val displayMetrics = context.resources.displayMetrics
        return displayMetrics.widthPixels
    }

    fun getDisplayHeight(context: Context): Int {
        val displayMetrics = context.resources.displayMetrics
        return displayMetrics.heightPixels
    }

    fun getMinDisplaySide(context: Context): Int {
        return Math.min(getDisplayHeight(context), getDisplayWidth(context))
    }

    fun getMaxDisplaySide(context: Context): Int {
        return Math.max(getDisplayHeight(context), getDisplayWidth(context))
    }




}
