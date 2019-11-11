package com.trantordev.androidbankapp

import com.trantordev.androidbankapp.util.ValidationUtils
import org.junit.Assert
import org.junit.Test

class ValidationTest {

    val invalidUser = "097"
    val validUser = "elcio@elcio.com"
    val invalidPassword = "asasas"
    val validPassword = "PorcaPepa1!"

    @Test
    fun invalid_user() {
        Assert.assertFalse(ValidationUtils.isValidCPF(invalidUser)||
                ValidationUtils.isValidEmail(invalidUser))
    }

    @Test
    fun invalid_pass() {
        Assert.assertFalse(ValidationUtils.isValidPassword(invalidPassword))
    }

    @Test
    fun valid_user() {
        Assert.assertTrue(ValidationUtils.isValidCPF(validUser)||
                ValidationUtils.isValidEmail(validUser))
    }

    @Test
    fun valid_pass() {
        Assert.assertTrue(ValidationUtils.isValidPassword(validPassword))
    }

    @Test
    fun currency_conversion() {
        Assert.assertEquals(PresentationUtils.getFormatedCurrency(3.34),"R$ 3,34")
    }

    @Test
    fun date_conversion(){
        Assert.assertEquals(PresentationUtils.getFormatedDate("2019-01-01"),"01/01/2019")
    }

    @Test
    fun account_formatation(){
        Assert.assertEquals(PresentationUtils.getBankAccountFormated("123456789"),"12.345678-9")
    }

}