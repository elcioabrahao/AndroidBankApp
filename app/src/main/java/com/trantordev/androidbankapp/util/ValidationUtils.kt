package com.trantordev.androidbankapp.util

object ValidationUtils {

    private val CPF = Regex("^([0-9]{3}\\.?){3}-?[0-9]{2}$")
    private val EMAIL = Regex(
        "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
    )
    private val NUMBER = Regex("[-+]?\\d*\\.?\\d+")
    private val PASSWORD =
        Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")


    fun isValidCPF(cpf: String): Boolean {
        return cpf.matches(CPF)
    }

    fun isValidEmail(email: String): Boolean {
        return email.matches(EMAIL)
    }

    fun isNumber(value: String?): Boolean {
        return value != null && value.matches(NUMBER)
    }

    fun isValidPassword(password: String): Boolean {
        return password.matches(PASSWORD)
    }

}