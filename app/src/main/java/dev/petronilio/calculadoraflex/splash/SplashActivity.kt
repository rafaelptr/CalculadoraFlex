package dev.petronilio.calculadoraflex.splash

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import dev.petronilio.calculadoraflex.R
import dev.petronilio.calculadoraflex.form.FormActivity
import dev.petronilio.calculadoraflex.login.LoginActivity
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    private val TEMPO_AGUARDO_SPLASHSCREEN = 3500L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val preferences = getSharedPreferences("user_preferences", Context.MODE_PRIVATE)
        val isFirstOpen = preferences.getBoolean("open_first",true)

        if (isFirstOpen) {
            markAppAlreadyOpen(preferences)
            showSplash()
        } else {
            showLogin()
        }
    }
    private fun showLogin() {
        val nextScreen = Intent(this@SplashActivity, LoginActivity::class.java)
        startActivity(nextScreen)
        finish()
    }

    private fun markAppAlreadyOpen(preferences: SharedPreferences) {
        val editor = preferences.edit()
        editor.putBoolean("open_first", false)
        editor.apply()
    }

    private fun showSplash(){
        //carrega animacao
        val anim = AnimationUtils.loadAnimation(this,R.anim.animacao_splash)
        anim.reset() //opcional
        ivLogo.clearAnimation()
        //roda a animacao;
        ivLogo.startAnimation(anim)
        //chama a proxima tela
        Handler().postDelayed({
            val proximaTela = Intent(this@SplashActivity, FormActivity::class.java)
            startActivity(proximaTela)
            finish()
        },TEMPO_AGUARDO_SPLASHSCREEN)
    }
}
