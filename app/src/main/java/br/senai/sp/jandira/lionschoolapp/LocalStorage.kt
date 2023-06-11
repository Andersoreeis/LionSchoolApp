package br.senai.sp.jandira.lionschoolapp
import android.content.Context

object LocalStorage {


    // Função para salvar um valor nas SharedPreferences
    fun saveToSharedPreferences(context: Context, key: String, value: String) {
        val sharedPrefs = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val editor = sharedPrefs.edit()
        editor.putString(key, value)
        editor.apply()
    }

    // Função para recuperar um valor das SharedPreferences
    fun getFromSharedPreferences(context: Context, key: String): String? {
        val sharedPrefs = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        return sharedPrefs.getString(key, null)
    }

}