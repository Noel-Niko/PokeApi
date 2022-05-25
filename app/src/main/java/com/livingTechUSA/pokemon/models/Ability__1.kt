//package com.livingTechUSA.pokemon.models
//
//import com.facebook.stetho.json.annotation.JsonProperty
//import javax.annotation.Generated
//
//@JsonInclude(JsonInclude.Include.NON_NULL)
//@JsonPropertyOrder(["name", "url"])
//@Generated("jsonschema2pojo")
//class Ability__1 {
//    @get:JsonProperty("name")
//    @set:JsonProperty("name")
//    @JsonProperty("name")
//    var name: String? = null
//
//    @get:JsonProperty("url")
//    @set:JsonProperty("url")
//    @JsonProperty("url")
//    var url: String? = null
//
//    @JsonIgnore
//    private val additionalProperties: MutableMap<String, Any> = HashMap()
//    @JsonAnyGetter
//    fun getAdditionalProperties(): Map<String, Any> {
//        return additionalProperties
//    }
//
//    @JsonAnySetter
//    fun setAdditionalProperty(name: String, value: Any) {
//        additionalProperties[name] = value
//    }
//}