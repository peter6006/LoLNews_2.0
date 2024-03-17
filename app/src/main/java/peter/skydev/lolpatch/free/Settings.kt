package peter.skydev.lolpatch.free

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.core.content.ContextCompat
import com.kyleduo.switchbutton.SwitchButton
import io.vrinda.kotlinpermissions.PermissionCallBack
import io.vrinda.kotlinpermissions.PermissionsActivity
import lolnews.skydev.peter.free.util.Prefs
import lolnews.skydev.peter.free.util.ServLang
import org.jetbrains.anko.*

class Settings : PermissionsActivity(), AnkoLogger {
    var serversList = ServLang().getServers()

    lateinit var prefs: Prefs
    var server: String = ""
    var language: String = ""
    var notificationPatch: Boolean = false
    var notificationChamp: Boolean = false
    var notificationChampRotation: Boolean = false
    var notificationAll: Boolean = false
    var languages: ArrayList<String> = ArrayList<String>()

    lateinit var serverSpinner: Spinner
    lateinit var languageSpinner: Spinner
    lateinit var switch1: SwitchButton
    lateinit var switch2: SwitchButton
    //    lateinit var switch3: SwitchButton
    lateinit var switch4: SwitchButton

    val TAG = "Settings"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        this.prefs = Prefs(this)

        this.language = prefs.lang
        this.server = prefs.server
        this.notificationPatch = prefs.notificationPatch
        this.notificationChamp = prefs.notificationChamp
        this.notificationChampRotation = prefs.notificationChampRotation
        this.notificationAll = prefs.notificationAll

        this.languageSpinner = findViewById(R.id.languageSpinner)
        this.serverSpinner = findViewById(R.id.serverSpinner)

        this.serversList = ServLang().getServers()
        info("Size: " + serversList.size)

        languages.add("English (NA)")
        languages.add("English (EUW)")
        languages.add("Deutsch")
        languages.add("Español (EUW)")
        languages.add("Français")
        languages.add("Italiano")
        languages.add("English (EUNE)")
        languages.add("Polski")
        languages.add("Ελληνικά")
        languages.add("Română")
        languages.add("Magyar")
        languages.add("Čeština")
        languages.add("Español (LATAM)")
        languages.add("Português")
        languages.add("日本語")
        languages.add("Русский")
        languages.add("Türkçe")
        languages.add("English (OCE)")
        languages.add("한국어")

        // Spinner server
        var adapterServer = ArrayAdapter<String>(this, R.layout.spinner_item, this.serversList)
        adapterServer.setDropDownViewResource(R.layout.simple_spinner_dropdown)
        this.serverSpinner.adapter = adapterServer
        this.serverSpinner.setSelection(this.serversList.indexOf(this.server))

        this.serverSpinner.onItemSelectedListener = object : AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if (prefs.notificationAll) {
//                    FirebaseMessaging.getInstance().unsubscribeFromTopic("AllNews-" + ServLang().getServerByString(server) + "-" + ServLang().getLangByString(server, language))
                    Log.d("Settings", "Unsubscribe AllNews-" + ServLang().getServerByString(server) + "-" + ServLang().getLangByString(server, language))
//                    FirebaseMessaging.getInstance().subscribeToTopic("AllNews-" + ServLang().getServerByString(serversList.get(p2)) + "-" + ServLang().getLangByString(serversList.get(p2), language))
                    Log.d("Settings", "Subscribe AllNews-" + ServLang().getServerByString(serversList.get(p2)) + "-" + ServLang().getLangByString(serversList.get(p2), language))
                }

                if (prefs.notificationPatch) {
                    Log.d("Settings", "Unsubscribe Patch-" + ServLang().getServerByString(server) + "-" + ServLang().getLangByString(server, language))
//                    FirebaseMessaging.getInstance().unsubscribeFromTopic("Patch-" + ServLang().getServerByString(server) + "-" + ServLang().getLangByString(server, language))
                    Log.d("Settings", "Subscribe Patch-" + ServLang().getServerByString(serversList.get(p2)) + "-" + ServLang().getLangByString(prefs.server, prefs.lang))
//                    FirebaseMessaging.getInstance().subscribeToTopic("Patch-" + ServLang().getServerByString(serversList.get(p2)) + "-" + ServLang().getLangByString(serversList.get(p2), prefs.lang))
                }

                if (prefs.notificationChamp) {
                    Log.d("Settings", "Unsubscribe Champ-" + ServLang().getServerByString(server) + "-" + ServLang().getLangByString(server, language))
//                    FirebaseMessaging.getInstance().unsubscribeFromTopic("Champ-" + ServLang().getServerByString(server) + "-" + ServLang().getLangByString(server, language))
                    Log.d("Settings", "Subscribe Champ-" + ServLang().getServerByString(serversList.get(p2)) + "-" + ServLang().getLangByString(prefs.server, prefs.lang))
//                    FirebaseMessaging.getInstance().subscribeToTopic("Champ-" + ServLang().getServerByString(serversList.get(p2)) + "-" + ServLang().getLangByString(serversList.get(p2), prefs.lang))
                }

                if (prefs.notificationChampRotation) {
                    Log.d("Settings", "Unsubscribe ChampRotation-" + ServLang().getServerByString(server) + "-" + ServLang().getLangByString(server, language))
//                    FirebaseMessaging.getInstance().unsubscribeFromTopic("ChampRotation-" + ServLang().getServerByString(server) + "-" + ServLang().getLangByString(server, language))
                    Log.d("Settings", "Subscribe ChampRotation-" + ServLang().getServerByString(serversList.get(p2)) + "-" + ServLang().getLangByString(prefs.server, prefs.lang))
//                    FirebaseMessaging.getInstance().subscribeToTopic("ChampRotation-" + ServLang().getServerByString(serversList.get(p2)) + "-" + ServLang().getLangByString(serversList.get(p2), prefs.lang))
                }

                if (server != serversList.get(p2)) {
//                    loadLanguages(serversList.get(p2), languageList.indexOf(ServLang().getLangByString(serversList.get(p2), language)))
                }
                server = serversList.get(p2)
                savePreferences()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}

            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

            }
        }

        // Spinner language
        var adapterLanguage = ArrayAdapter<String>(this, R.layout.spinner_item, languages)
        adapterLanguage.setDropDownViewResource(R.layout.simple_spinner_dropdown)
        this.languageSpinner.adapter = adapterLanguage

        this.languageSpinner.setSelection(this.languages.indexOf(this.language))

        this.languageSpinner.onItemSelectedListener = object : AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                info("language Spinner clicked " + languages[p2])

                if (prefs.notificationAll) {
//                    FirebaseMessaging.getInstance().unsubscribeFromTopic("AllNews-" + ServLang().getServerByString(server) + "-" + ServLang().getLangByString(server, language))
                    Log.d("Settings", "Unsubscribe AllNews-" + ServLang().getServerByString(server) + "-" + ServLang().getLangByString(server, language))
//                    FirebaseMessaging.getInstance().subscribeToTopic("AllNews-" + ServLang().getServerByString(server) + "-" + ServLang().getLangByString(server, languageList.get(p2)))
                    Log.d("Settings", "Subscribe AllNews-" + ServLang().getServerByString(server) + "-" + ServLang().getLangByString(server, languages.get(p2)))
                }

                if (prefs.notificationPatch) {
                    Log.d("Settings", "Unsubscribe Patch-" + ServLang().getServerByString(server) + "-" + ServLang().getLangByString(server, language))
//                    FirebaseMessaging.getInstance().unsubscribeFromTopic("Patch-" + ServLang().getServerByString(server) + "-" + ServLang().getLangByString(server, language))
                    Log.d("Settings", "Subscribe Patch-" + ServLang().getServerByString(server) + "-" + ServLang().getLangByString(prefs.server, languages.get(p2)))
//                    FirebaseMessaging.getInstance().subscribeToTopic("Patch-" + ServLang().getServerByString(server) + "-" + ServLang().getLangByString(server, languageList.get(p2)))
                }

                if (prefs.notificationChamp) {
                    Log.d("Settings", "Unsubscribe Champ-" + ServLang().getServerByString(server) + "-" + ServLang().getLangByString(server, language))
                    //FirebaseMessaging.getInstance().unsubscribeFromTopic("Champ-" + ServLang().getServerByString(server) + "-" + ServLang().getLangByString(server, language))
                    Log.d("Settings", "Subscribe Champ-" + ServLang().getServerByString(server) + "-" + ServLang().getLangByString(prefs.server, languages.get(p2)))
                    //FirebaseMessaging.getInstance().subscribeToTopic("Champ-" + ServLang().getServerByString(server) + "-" + ServLang().getLangByString(server, languageList.get(p2)))
                }

                if (prefs.notificationChampRotation) {
                    Log.d("Settings", "Unsubscribe ChampRotation-" + ServLang().getServerByString(server) + "-" + ServLang().getLangByString(server, language))
                    //FirebaseMessaging.getInstance().unsubscribeFromTopic("ChampRotation-" + ServLang().getServerByString(server) + "-" + ServLang().getLangByString(server, language))
                    Log.d("Settings", "Subscribe ChampRotation-" + ServLang().getServerByString(server) + "-" + ServLang().getLangByString(prefs.server, languages.get(p2)))
                    //FirebaseMessaging.getInstance().subscribeToTopic("ChampRotation-" + ServLang().getServerByString(server) + "-" + ServLang().getLangByString(server, languageList.get(p2)))
                }

                language = languages[p2]
                savePreferences()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}

            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {}
        }

        this.loadLanguages(this.server, this.languages.indexOf(this.language))

        // TODO Check functionality of every switch
        // switch 1
        this.switch1 = findViewById(R.id.switch1)
        this.switch1.setCheckedImmediately(this.notificationPatch)
        this.switch1.setOnClickListener {
            if (this.switch2.isChecked) {
                Log.d("Settings", "Subscribe Patch-" + ServLang().getServerByString(prefs.server) + "-" + ServLang().getLangByString(prefs.server, prefs.lang))
                //FirebaseMessaging.getInstance().subscribeToTopic("Patch-" + ServLang().getServerByString(prefs.server) + "-" + ServLang().getLangByString(prefs.server, prefs.lang))
            } else {
                //FirebaseMessaging.getInstance().unsubscribeFromTopic("Patch-" + ServLang().getServerByString(server) + "-" + ServLang().getLangByString(server, language))
                Log.d("Settings", "Unsubscribe Patch-" + ServLang().getServerByString(server) + "-" + ServLang().getLangByString(server, language))
            }
            prefs.notificationPatch = this.switch1.isChecked
        }

        // switch 2
        this.switch2 = findViewById(R.id.switch2)
        this.switch2.setCheckedImmediately(this.notificationChamp)
        this.switch2.setOnClickListener {
            if (this.switch2.isChecked) {
                Log.d("Settings", "Subscribe Champ-" + ServLang().getServerByString(prefs.server) + "-" + ServLang().getLangByString(prefs.server, prefs.lang))
                //FirebaseMessaging.getInstance().subscribeToTopic("Champ-" + ServLang().getServerByString(prefs.server) + "-" + ServLang().getLangByString(prefs.server, prefs.lang))
            } else {
                //FirebaseMessaging.getInstance().unsubscribeFromTopic("Champ-" + ServLang().getServerByString(server) + "-" + ServLang().getLangByString(server, language))
                Log.d("Settings", "Unsubscribe Champ-" + ServLang().getServerByString(server) + "-" + ServLang().getLangByString(server, language))
            }
            prefs.notificationChamp = this.switch2.isChecked
        }

        // switch 3
//        this.switch3 = findViewById(R.id.switch3)
//        this.switch3.setCheckedImmediately(this.notificationChampRotation)
//        this.switch3.setOnClickListener {
//            if (this.switch3.isChecked) {
//                Log.d("Settings", "Subscribe ChampRotation-" + ServLang().getServerByString(prefs.server) + "-" + ServLang().getLangByString(prefs.server, prefs.lang))
//                FirebaseMessaging.getInstance().subscribeToTopic("ChampRotation-" + ServLang().getServerByString(prefs.server) + "-" + ServLang().getLangByString(prefs.server, prefs.lang))
//            } else {
//                FirebaseMessaging.getInstance().unsubscribeFromTopic("ChampRotation-" + ServLang().getServerByString(server) + "-" + ServLang().getLangByString(server, language))
//                Log.d("Settings", "Unsubscribe ChampRotation-" + ServLang().getServerByString(server) + "-" + ServLang().getLangByString(server, language))
//            }
//            prefs.notificationChampRotation = this.switch3.isChecked
//        }

        // switch 4
        this.switch4 = findViewById(R.id.switch4)
        this.switch4.setCheckedImmediately(this.notificationAll)
        if (this.notificationAll) {
            this.switch1.setCheckedImmediately(true)
            this.switch2.setCheckedImmediately(true)
//            this.switch3.setCheckedImmediately(true)
        }
        this.switch4.setOnClickListener {
            if (isGranted()) {

                if (this.switch4.isChecked) {
                    Log.d("Settings", "Subscribe AllNews-" + ServLang().getServerByString(prefs.server) + "-" + ServLang().getLangByString(prefs.server, prefs.lang))
                    //FirebaseMessaging.getInstance().subscribeToTopic("AllNews-" + ServLang().getServerByString(prefs.server) + "-" + ServLang().getLangByString(prefs.server, prefs.lang))

                    Log.d("Settings", "All news subscription")
                    prefs.notificationPatch = true
                    prefs.notificationChamp = true
                    prefs.notificationChampRotation = true
                    this.switch1.setCheckedImmediately(true)
                    this.switch2.setCheckedImmediately(true)
//                    this.switch3.setCheckedImmediately(true)
                    this.switch1.callOnClick()
                    this.switch2.callOnClick()
//                    this.switch3.callOnClick()
                } else {
                    //FirebaseMessaging.getInstance().unsubscribeFromTopic("AllNews-" + ServLang().getServerByString(server) + "-" + ServLang().getLangByString(server, language))
                    Log.d("Settings", "Unsubscribe AllNews-" + ServLang().getServerByString(server) + "-" + ServLang().getLangByString(server, language))
                }
                prefs.notificationAll = this.switch4.isChecked

            } else {
                askForPermission()
            }
        }

        /*if (!isGranted()) {
            textViewSwitch1.visibility = View.GONE
            textViewSwitch2.visibility = View.GONE
//            textViewSwitch3.visibility = View.GONE
            textViewSwitch4.visibility = View.GONE
            switch1.visibility = View.GONE
            switch2.visibility = View.GONE
//            switch3.visibility = View.GONE
            switch4.visibility = View.GONE

            tvNoPermissionNotification.visibility = View.VISIBLE
            buttonPermission.visibility = View.VISIBLE
        } else {
            makeVisible()
        }

        buttonPermission.setOnClickListener {
            askForPermission()
        }*/
    }
    fun savePreferences() {
        prefs.lang = this.language
        prefs.server = this.server
    }

    fun loadLanguages(server: String, i: Int) {
        var adapterLanguage = ArrayAdapter<String>(this, R.layout.spinner_item, this.languages)
        adapterLanguage.setDropDownViewResource(R.layout.simple_spinner_dropdown)
        this.languageSpinner.adapter = adapterLanguage

        runOnUiThread {
            this.languageSpinner.setSelection(i)
        }
    }

    fun askForPermission() {
        if (android.os.Build.VERSION.SDK_INT >= 23) {
            val permissionCheck = ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.GET_ACCOUNTS)
            if (permissionCheck == PackageManager.PERMISSION_DENIED) {
                Log.d(TAG, "Permission denied")
                requestPermissions(android.Manifest.permission.GET_ACCOUNTS, object :
                    PermissionCallBack {
                    override fun permissionGranted() {
                        super.permissionGranted()
                        Log.v("Call permissions", "Granted")
                        //startService(intentFor<PatchService>())
                        makeVisible()
                    }

                    override fun permissionDenied() {
                        super.permissionDenied()
                        Log.v("Call permissions", "Denied")
                        alert(getString(R.string.settingsMessagePermission), getString(R.string.settingsMessagePermissionTittle)) {
                            yesButton {}
                        }.show()
                    }
                })
            } else {
                Log.d(TAG, "Permission granted")
            }
        }
    }

    fun isGranted(): Boolean {
        if (android.os.Build.VERSION.SDK_INT >= 23) {
            val permissionCheck = ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.GET_ACCOUNTS)
            return permissionCheck != PackageManager.PERMISSION_DENIED
        } else {
            return true
        }
    }

    fun makeVisible() {
        /*textViewSwitch1.visibility = View.VISIBLE
        textViewSwitch2.visibility = View.VISIBLE
//        textViewSwitch3.visibility = View.VISIBLE
        textViewSwitch4.visibility = View.VISIBLE
        switch1.visibility = View.VISIBLE
        switch2.visibility = View.VISIBLE
//        switch3.visibility = View.VISIBLE
        switch4.visibility = View.VISIBLE

        tvNoPermissionNotification.visibility = View.GONE
        buttonPermission.visibility = View.GONE*/
    }
}