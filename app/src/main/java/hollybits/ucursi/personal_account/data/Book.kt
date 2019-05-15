package hollybits.ucursi.personal_account.data

import com.google.gson.annotations.SerializedName



data class Book (
	@SerializedName("title") val title : String,
	@SerializedName("author") val author : String,
	@SerializedName("holdingbranch") val holdingbranch : String,
	@SerializedName("itemcallnumber") val itemcallnumber : String
)