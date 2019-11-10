import java.text.NumberFormat

import java.util.*

object PresentationUtils {


    fun getFormatedCurrency(value: Double)
            = NumberFormat.getCurrencyInstance(Locale("pt", "BR")).format(value);



}