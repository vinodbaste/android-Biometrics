package com.kl.biometrics

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.biometric.BiometricPrompt

class MainActivity : AppCompatActivity(),BiometricAuthListener {

    private lateinit var buttonBiometricsLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonBiometricsLogin = findViewById(R.id.buttonBiometricsLogin)

        //button visibility
        showBiometricLoginOption()
    }


    fun onClickBiometrics(view: View) {
        BiometricUtil.showBiometricPrompt(
            activity = this,
            listener = this,
            cryptoObject = null,
            allowDeviceCredential = true
        )
    }

    override fun onBiometricAuthenticationSuccess(result: BiometricPrompt.AuthenticationResult) {
        Toast.makeText(this, "Biometric success", Toast.LENGTH_SHORT)
            .show()
    }

    override fun onBiometricAuthenticationError(errorCode: Int, errorMessage: String) {
        Toast.makeText(this, "Biometric login. Error: $errorMessage", Toast.LENGTH_SHORT)
            .show()
    }

    private fun showBiometricLoginOption() {
        buttonBiometricsLogin.visibility =
            if (BiometricUtil.isBiometricReady(this)) View.VISIBLE
            else View.GONE
    }
}