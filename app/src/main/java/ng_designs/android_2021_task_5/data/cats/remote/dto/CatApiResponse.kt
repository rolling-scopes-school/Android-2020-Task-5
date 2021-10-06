package ng_designs.android_2021_task_5.data.cats.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CatApiResponse(
    @Json(name="id") val id: String,
    @Json(name="url") val url: String
)
//
//@JsonClass(generateAdapter = true)
//data class CatCategory (
//    @Json(name="id") val id: Int,
//    @Json(name="name") val name: String
//)
//
//@JsonClass(generateAdapter = true)
//data class CatBreed(
//    @Json(name="id") val id: String,
//    @Json(name="name") val name: String,
//    @Json(name="temperament") val temperament: String,
//    @Json(name="life_span") val lise_span: String,
//    @Json(name="alt_names") val alt_names: String,
//    @Json(name="wikipedia_url") val wiki_url: String,
//    @Json(name="origin") val origin: String,
//    @Json(name="weight_imperial") val weight: String,
//    @Json(name="experimental") val experimental: Int,
//    @Json(name="hairless") val hairless: Int,
//    @Json(name="natural") val natural: Int,
//    @Json(name="rare") val rare: Int,
//    @Json(name="rex") val rex: Int,
//    @Json(name="suppress_tail") val suppress_tail: Int,
//    @Json(name="short_legs") val short_legs: Int,
//    @Json(name="hypoallergenic") val hypoallergenic: Int,
//    @Json(name="adaptability") val adaptability: Int,
//    @Json(name="affection_level") val affection_level: Int,
//    @Json(name="county_code") val country_code: String,
//    @Json(name="child_friendly") val child_friendly: Int,
//    @Json(name="dog_friendly") val dog_friendly: Int,
//    @Json(name="energy_level") val energy_level: Int,
//    @Json(name="grooming") val grooming: Int,
//    @Json(name="health_issues") val health_issues: Int,
//    @Json(name="intelligence") val intelligence: Int,
//    @Json(name="shedding_level") val shedding_level: Int,
//    @Json(name="social_needs") val social_needs: Int,
//    @Json(name="stranger_friendly") val stranger_friendly: Int,
//    @Json(name="vocalization") val vocalization: Int
//)
