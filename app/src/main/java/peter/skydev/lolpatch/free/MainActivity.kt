package peter.skydev.lolpatch.free

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.anjlab.android.iab.v3.BillingProcessor
import com.anjlab.android.iab.v3.TransactionDetails
import io.vrinda.kotlinpermissions.PermissionCallBack
import io.vrinda.kotlinpermissions.PermissionsActivity
import lolnews.skydev.peter.free.util.Prefs
import org.jetbrains.anko.alert
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.share
import org.jetbrains.anko.yesButton
import peter.skydev.lolpatch.free.WebViews.*

class MainActivity : PermissionsActivity(), BillingProcessor.IBillingHandler {
    private val TAG: String = "MainActivity"

    private var server: String = ""
    private var language: String = ""
    private var first: Boolean = false
    lateinit var prefs: Prefs

    private lateinit var settingsImg: ImageView
    private lateinit var shareImg: ImageView
    private lateinit var buyImg: ImageView
    private lateinit var patchButton: Button
    private lateinit var champsButton: Button
    private lateinit var allPatchButton: Button
    private lateinit var allChampsButton: Button
    private lateinit var allNewsButton: Button
    private lateinit var eSportsNewsButton: Button
    private lateinit var devNewsButton: Button
    private lateinit var loreNewsButton: Button
    private lateinit var mediaNewsButton: Button
    private lateinit var merchNewsButton: Button
    private lateinit var communityNewsButton: Button
    private lateinit var riotGamesNewsButton: Button

    // TODO Complete these
    var bp: BillingProcessor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.prefs = Prefs(this)

        bp =
            BillingProcessor(this, "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkkW0RERs1DJZW4h6dRsm/2DE2NFU6YDrTl/s2gDQ/hPokjzr5AXBEBj3rRQOzQoOYYkMGhNt9WK/DRrxKgcv9nz+K3WeX6ekqeW0u6tPq/Fnd+S/L/8MlA7brfHMRAbbf+7OjIY239JyNHKzjebNJ6nocFjhJQ3yp3uoHdxlPlVF3+BB7uXIu88HPeeaxBEqDjzMSESZe9QbjirXLcgGf8Nxkwb9vJPW7LOh9p39MqW6Ir/A5jHbP4Y7pucjKUvTiBhmDv8NdzjsKAPjF+21k5z9mlzxpCwOo+DH9/a/sc45lpJ5y2+iL+Kw4F7tD+u5252qvk/wWGdMrEVpCj02mwIDAQAB", this)
        bp!!.initialize()


        // Get chosen option
        this.language = prefs.lang
        this.server = prefs.server
        this.first = prefs.first

        if (this.first) {
            showDialog()
        }

        if (android.os.Build.VERSION.SDK_INT >= 23) {
            val permissionCheck = ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.GET_ACCOUNTS
            )
            if (permissionCheck == PackageManager.PERMISSION_DENIED) {
                Log.d(TAG, "Permission denied")
                requestPermissions(android.Manifest.permission.GET_ACCOUNTS, object :
                    PermissionCallBack {
                    override fun permissionGranted() {
                        super.permissionGranted()
                        Log.v("Call permissions", "Granted")
                        //startService(intentFor<PatchService>())
                    }

                    override fun permissionDenied() {
                        super.permissionDenied()
                        Log.v("Call permissions", "Denied")
                        alert(
                            "Permission",
                            "If you don't allow us we can not send any notification to you"
                        ) {
                            yesButton {}
                        }.show()
                    }
                })
            } else {
                Log.d(TAG, "Permission granted")
            }
        }
        // region Buttons
        // Patch button
        this.patchButton = findViewById(R.id.buttonPatch)
        this.patchButton.setOnClickListener {
            startActivity(intentFor<PatchWebView>())
        }

        // Champs button
        this.champsButton = findViewById(R.id.buttonChamp)
        this.champsButton.setOnClickListener {
            startActivity(intentFor<ChampWebView>())
        }

        // All patches button
        this.allPatchButton = findViewById(R.id.buttonAllPatch)
        this.allPatchButton.setOnClickListener {
            startActivity(intentFor<AllPatchWebView>())
        }

        // All champs button
        this.allChampsButton = findViewById(R.id.buttonAllChampNews)
        this.allChampsButton.setOnClickListener {
            startActivity(intentFor<AllChampWebView>())
        }

        // All patches button
        this.allNewsButton = findViewById(R.id.buttonAllNews)
        this.allNewsButton.setOnClickListener {
            startActivity(intentFor<AllNewsWebView>())
        }

        // All patches button****************************************************************
        this.eSportsNewsButton = findViewById(R.id.buttonAllESportsNews)
        this.eSportsNewsButton.setOnClickListener {
            val intent = Intent(this, OtherWebView::class.java)
            intent.putExtra("url", "esports/")

            startActivity(intent)
        }

        // All patches button
        this.devNewsButton = findViewById(R.id.buttonAllDevNews)
        this.devNewsButton.setOnClickListener {
            val intent = Intent(this, OtherWebView::class.java)
            intent.putExtra("url", "dev/")

            startActivity(intent)
        }

        // All patches button
        this.loreNewsButton = findViewById(R.id.buttonAllLoreNews)
        this.loreNewsButton.setOnClickListener {
            val intent = Intent(this, OtherWebView::class.java)
            intent.putExtra("url", "lore/")

            startActivity(intent)
        }

        // All patches button
        this.mediaNewsButton = findViewById(R.id.buttonAllMediaNews)
        this.mediaNewsButton.setOnClickListener {
            val intent = Intent(this, OtherWebView::class.java)
            intent.putExtra("url", "media/")

            startActivity(intent)
        }

        // All patches button
        this.merchNewsButton = findViewById(R.id.buttonAllMerchNews)
        this.merchNewsButton.setOnClickListener {
            val intent = Intent(this, OtherWebView::class.java)
            intent.putExtra("url", "merch/")

            startActivity(intent)
        }

        // All patches button
        this.communityNewsButton = findViewById(R.id.buttonAllCommunityNews)
        this.communityNewsButton.setOnClickListener {
            val intent = Intent(this, OtherWebView::class.java)
            intent.putExtra("url", "community/")

            startActivity(intent)
        }

        // All patches button
        this.riotGamesNewsButton = findViewById(R.id.buttonAllRiotGamesNews)
        this.riotGamesNewsButton.setOnClickListener {
            val intent = Intent(this, OtherWebView::class.java)
            intent.putExtra("url", "riot-games/")

            startActivity(intent)
        }

        // Settings image
        this.settingsImg = findViewById(R.id.settingsImg)
        this.settingsImg.setOnClickListener {
            startActivity(intentFor<Settings>())
        }

        // Share image
        this.shareImg = findViewById(R.id.shareImg)
        this.shareImg.setOnClickListener {
            // TODO: Share app
            share(
                "Check this app! LoL News\n\nhttps://play.google.com/store/apps/details?id=peter.skydev.lolpatch.free",
                "Check out his app!"
            )
        }
        // endregion Buttons

        // Buy image
        this.buyImg = findViewById(R.id.buyImg)
        this.buyImg.setOnClickListener {
            Log.d(TAG, "Buy pressed")
            if (bp!!.isOneTimePurchaseSupported) {
                bp!!.purchase(this, "buy_the_app")
            }
        }
        if (prefs.itemBought) {
            this.buyImg.visibility = View.GONE
        }
    }

    private fun showDialog() {
        alert("This is the first time you use the application, by default its set to NA - EN, but you can change it anytime") {
            positiveButton("Got that!") {}
        }.show()
        prefs.first = false
    }

    override fun onDestroy() {
        if (bp != null) {
            bp!!.release()
        }
        super.onDestroy()
    }

    override fun onProductPurchased(productId: String, details: TransactionDetails?) {
        prefs.itemBought = true
        this.buyImg.visibility = View.GONE
    }

    override fun onPurchaseHistoryRestored() {
        prefs.itemBought = true
        this.buyImg.visibility = View.GONE
    }

    override fun onBillingError(errorCode: Int, error: Throwable?) {
    }

    override fun onBillingInitialized() {
    }
}