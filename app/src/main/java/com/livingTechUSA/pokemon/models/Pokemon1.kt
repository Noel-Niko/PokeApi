//package com.livingTechUSA.pokemon.models
//
//import javax.annotation.Generated
//
//@JsonInclude(JsonInclude.Include.NON_NULL)
//@JsonPropertyOrder(["abilities", "base_experience", "forms", "game_indices"])
//@Generated("jsonschema2pojo")
//class Pokemon {
//    @get:JsonProperty("abilities")
//    @set:JsonProperty("abilities")
//    @JsonProperty("abilities")
//    var abilities: List<Ability>? = null
//
//    @get:JsonProperty("base_experience")
//    @set:JsonProperty("base_experience")
//    @JsonProperty("base_experience")
//    var baseExperience: Int? = null
//
//    @get:JsonProperty("forms")
//    @set:JsonProperty("forms")
//    @JsonProperty("forms")
//    var forms: List<Form>? = null
//
//    @get:JsonProperty("game_indices")
//    @set:JsonProperty("game_indices")
//    @JsonProperty("game_indices")
//    var gameIndices: List<GameIndex>? = null
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