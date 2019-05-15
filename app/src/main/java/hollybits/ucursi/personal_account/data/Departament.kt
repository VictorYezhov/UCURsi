import com.google.gson.annotations.SerializedName



data class Departament (

	@SerializedName("departmentName") val departmentName : String,
	@SerializedName("distinguishedName") val distinguishedName : String
)