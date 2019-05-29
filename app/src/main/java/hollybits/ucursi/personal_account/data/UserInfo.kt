package hollybits.ucursi.personal_account.data

import com.google.gson.annotations.SerializedName




data class UserInfo (

	@SerializedName("User") val user : User,
	@SerializedName("Departament") val departament : Departament,
	@SerializedName("PrintService") val printService : PrintService,
	@SerializedName("Library") val library : Library,
	@SerializedName("Acces") val acces : String
)