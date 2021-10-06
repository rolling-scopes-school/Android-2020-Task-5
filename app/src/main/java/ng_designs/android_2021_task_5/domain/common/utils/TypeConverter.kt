package ng_designs.android_2021_task_5.domain.common.utils

//import ng_designs.android_2021_task_5.domain.cat.entity.CatEntity
import android.net.Uri
import ng_designs.android_2021_task_5.data.cats.remote.dto.CatApiResponse
import ng_designs.android_2021_task_5.domain.cat.models.Cats


fun CatApiResponse.toCats():Cats {
    val uri = Uri.parse( url)

    return Cats(id, url)
}


//fun Cats.toCatEntity():CatEntity {
//    val uri = Uri.parse(url)
//    val bmp = BitmapFactory.decodeStream(locate<Context>().contentResolver.openInputStream(uri))
//    val stream = ByteArrayOutputStream()
//    bmp.compress(Bitmap.CompressFormat.JPEG, 100, stream)
//    val byteArray: ByteArray = stream.toByteArray()
//    return CatEntity(id.toInt(), url, byteArray)
//}
//
//fun CatApiResponse.toCatEntity():CatEntity {
//    val uri = Uri.parse(url)
//    val bmp = BitmapFactory.decodeStream(locate<Context>().contentResolver.openInputStream(uri))
//    val stream = ByteArrayOutputStream()
//    bmp.compress(Bitmap.CompressFormat.PNG, 90, stream)
//    val byteArray: ByteArray = stream.toByteArray()
//    return CatEntity(id.toInt(), url, byteArray)
//}
//
//fun CatEntity.toCats():Cats {
//    val compressedBitmap = BitmapFactory.decodeByteArray(catImage, 0, catImage.size)
//    return Cats(id.toString(), imageUrl.toString())
//}
//
//    val inputStream: InputStream =
//    val bitmap = BitmapFactory.decodeStream(inputStream)
//    ivSource.setImageBitmap(bitmap)
//    val stream = ByteArrayOutputStream()
//    bitmap.compress(Bitmap.CompressFormat.JPEG, 80, stream)
//    val byteArray: ByteArray = stream.toByteArray()
//    val compressedBitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
//
//    val bmp = MediaStore.Images.Media.getBitmap(locate<Context>().contentResolver, Uri.parse( cat.url))
