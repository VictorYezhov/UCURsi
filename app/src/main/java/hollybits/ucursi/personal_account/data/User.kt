import com.google.gson.annotations.SerializedName




data class User (

	@SerializedName("login") val login : String,
	@SerializedName("name") val name : String,
	@SerializedName("surname") val surname : String,
	@SerializedName("email") val email : String,
	@SerializedName("dateBirth") val dateBirth : String,
	@SerializedName("phone") val phone : Int,
	@SerializedName("displayName") val displayName : String
)