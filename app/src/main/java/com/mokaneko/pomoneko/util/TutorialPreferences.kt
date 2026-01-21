package com.mokaneko.pomoneko.util

import android.content.Context

object TutorialPreferences {

    private const val PREF_NAME = "pomonako_prefs"
    private const val KEY_TUTORIAL_DONE = "tutorial_done"

    fun isTutorialDone(context: Context): Boolean {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return prefs.getBoolean(KEY_TUTORIAL_DONE, false)
    }

    fun setTutorialDone(context: Context) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        prefs.edit()
            .putBoolean(KEY_TUTORIAL_DONE, true)
            .apply()
    }
}
