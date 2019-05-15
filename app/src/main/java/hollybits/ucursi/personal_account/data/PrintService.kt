package hollybits.ucursi.personal_account.data

import com.google.gson.annotations.SerializedName



data class PrintService (

	@SerializedName("balance") val balance : Double,
	@SerializedName("login") val login : String
)