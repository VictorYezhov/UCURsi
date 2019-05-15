import com.google.gson.annotations.SerializedName


data class Library (

	@SerializedName("libraryCard") val libraryCard : Int,
	@SerializedName("expiryCard") val expiryCard : String,
	@SerializedName("books") val books : Map<String, Book>
)