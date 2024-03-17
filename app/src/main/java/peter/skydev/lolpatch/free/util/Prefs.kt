package lolnews.skydev.peter.free.util

import android.content.Context
import android.content.SharedPreferences

class Prefs(context: Context) {
    private val PREFS_FILENAME: String = "lolnews.skydev.peter.lolnews"
    private val SERVER_STRING_PREFS: String = "server"
    private val LANGUAGE_STRING_PREFS: String = "lang"
    private val FIRST_STRING_PREFS: String = "first"
    private val NOTIFICATIONSP_STRING_PREFS: String = "notificationsPatch"
    private val NOTIFICATIONSC_STRING_PREFS: String = "notificationsChamp"
    private val NOTIFICATIONSCR_STRING_PREFS: String = "notificationsChampRotation"
    private val NOTIFICATIONSA_STRING_PREFS: String = "notificationsAll"
    private val PATCHTOSEE_STRING_PREFS: String = "patchToSee"
    private val CHAMPTOSEE_STRING_PREFS: String = "champToSee"
    private val CHAMPROTATION_STRING_PREFS: String = "champRotation"
    private val ITEMBOUGHT_STRING_PREFS: String = "champRotation"
    private val NEW_STRING_PREFS: String = "new"
    var prefs: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, 0)

    var server: String
        get() = this.prefs.getString(this.SERVER_STRING_PREFS, "North America")!!
        set(value) = this.prefs.edit().putString(this.SERVER_STRING_PREFS, value).apply()

    var lang: String
        get() = this.prefs.getString(this.LANGUAGE_STRING_PREFS, "English (NA)")!!
        set(value) = this.prefs.edit().putString(this.LANGUAGE_STRING_PREFS, value).apply()

    var first: Boolean
        get() = this.prefs.getBoolean(this.FIRST_STRING_PREFS, true)
        set(value) = this.prefs.edit().putBoolean(this.FIRST_STRING_PREFS, value).apply()

    var notificationPatch: Boolean
        get() = this.prefs.getBoolean(this.NOTIFICATIONSP_STRING_PREFS, false)
        set(value) = this.prefs.edit().putBoolean(this.NOTIFICATIONSP_STRING_PREFS, value).apply()

    var notificationChamp: Boolean
        get() = this.prefs.getBoolean(this.NOTIFICATIONSC_STRING_PREFS, false)
        set(value) = this.prefs.edit().putBoolean(this.NOTIFICATIONSC_STRING_PREFS, value).apply()

    var notificationChampRotation: Boolean
        get() = this.prefs.getBoolean(this.NOTIFICATIONSCR_STRING_PREFS, false)
        set(value) = this.prefs.edit().putBoolean(this.NOTIFICATIONSCR_STRING_PREFS, value).apply()

    var notificationAll: Boolean
        get() = this.prefs.getBoolean(this.NOTIFICATIONSA_STRING_PREFS, false)
        set(value) = this.prefs.edit().putBoolean(this.NOTIFICATIONSA_STRING_PREFS, value).apply()

    var patch: String
        get() = this.prefs.getString(this.PATCHTOSEE_STRING_PREFS, "")!!
        set(value) = this.prefs.edit().putString(this.PATCHTOSEE_STRING_PREFS, value).apply()

    var champ: String
        get() = this.prefs.getString(this.CHAMPTOSEE_STRING_PREFS, "")!!
        set(value) = this.prefs.edit().putString(this.CHAMPTOSEE_STRING_PREFS, value).apply()

    var champRotation: String
        get() = this.prefs.getString(this.CHAMPROTATION_STRING_PREFS, "")!!
        set(value) = this.prefs.edit().putString(this.CHAMPROTATION_STRING_PREFS, value).apply()

    var news: String
        get() = this.prefs.getString(this.NEW_STRING_PREFS, "")!!
        set(value) = this.prefs.edit().putString(this.NEW_STRING_PREFS, value).apply()

    var itemBought: Boolean
        get() = this.prefs.getBoolean(this.ITEMBOUGHT_STRING_PREFS, false)
        set(value) = this.prefs.edit().putBoolean(this.ITEMBOUGHT_STRING_PREFS, value).apply()
}