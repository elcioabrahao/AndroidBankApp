import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.Date

object PresentationUtils {


    fun getFormatedCurrency(value: Double?): String {
        if (value == null) {
            return NumberFormat.getCurrencyInstance(Locale("pt", "BR")).format(0.0)
        }
        return NumberFormat.getCurrencyInstance(Locale("pt", "BR")).format(value)
    }

    fun getBankAccountFormated(value: String?): String {
        if (value != null) {
            if (value.length == 9) {
                return value.substring(0, 2) + "." +
                        value.substring(2, 8) + "-" +
                        value.substring(8, value.length)
            } else {
                return value;
            }
        } else {
            return "Não Informado"
        }
    }

    fun getFormatedDate(date: String?): String {
        if(date!=null) {
            var format = SimpleDateFormat("yyyy-MM-dd")
            val newDate: Date = format.parse(date)
            format = SimpleDateFormat("dd/MM/yyyy")
            return format.format(newDate)
        }else{
            return ""
        }
    }

}
