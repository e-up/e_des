package me.iamee.e_des

import androidx.annotation.NonNull
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import javax.crypto.Cipher
import javax.crypto.SecretKey
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.DESKeySpec
import javax.crypto.spec.IvParameterSpec

/** EDesPlugin */
class EDesPlugin : FlutterPlugin, MethodCallHandler {
    /// The MethodChannel that will the communication between Flutter and native Android
    ///
    /// This local reference serves to register the plugin with the Flutter Engine and unregister it
    /// when the Flutter Engine is detached from the Activity
    private lateinit var channel: MethodChannel

    override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
        channel = MethodChannel(flutterPluginBinding.binaryMessenger, "e_des")
        channel.setMethodCallHandler(this)
    }

    @ExperimentalUnsignedTypes
    override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: Result) {
        if (call.method == "encode") {
            val secret: String = call.argument<String>("secret") ?: "";
            val data: String = call.argument<String>("data") ?: "";
            val iv: String = call.argument<String>("iv") ?: "";
            result.success(encode(secret, iv, data));
        } else {
            result.notImplemented()
        }
    }

    override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
        channel.setMethodCallHandler(null)
    }

    @ExperimentalUnsignedTypes
    fun encode(secret: String, iv: String, data: String): String {
        try {
            val generateSecret: SecretKey =
                SecretKeyFactory.getInstance("DES").generateSecret(DESKeySpec(secret.toByteArray()))
            val instance: Cipher = Cipher.getInstance("DES/CBC/PKCS5Padding")
            instance.init(1, generateSecret, IvParameterSpec(iv.toByteArray()))
            return instance.doFinal(data.toByteArray()).toHex();
        } catch (e: Exception) {
            e.printStackTrace()
            return data
        }
    }
}


@ExperimentalUnsignedTypes
fun ByteArray.toHex(): String = asUByteArray().joinToString("") { it.toString(radix = 16).padStart(2, '0') }
