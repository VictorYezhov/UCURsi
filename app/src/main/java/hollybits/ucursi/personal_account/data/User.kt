package hollybits.ucursi.personal_account.data

import com.google.gson.annotations.SerializedName




data class User (

	@SerializedName("login") val login : String?,
	@SerializedName("name") val name : String?,
	@SerializedName("surname") val surname : String?,
	@SerializedName("email") val email : String?,
	@SerializedName("dateBirth") val dateBirth : String?,
	@SerializedName("phone") val phone : String?,
	@SerializedName("displayName") val displayName : String?
)