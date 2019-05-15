import com.google.gson.annotations.SerializedName




data class UserInfo (

	@SerializedName("user") val user : User,
	@SerializedName("departament") val departament : Departament,
	@SerializedName("printService") val printService : PrintService,
	@SerializedName("library") val library : Library,
	@SerializedName("acces") val acces : String
)