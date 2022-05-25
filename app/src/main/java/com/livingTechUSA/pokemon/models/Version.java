//
//package com.livingTechUSA.pokemon.models;
//
//import com.facebook.stetho.json.annotation.JsonProperty;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@JsonInclude(JsonInclude.Include.NON_NULL)
//@JsonPropertyOrder({
//    "name",
//    "url"
//})
//
//public class Version {
//
//    @JsonProperty("name")
//    private String name;
//    @JsonProperty("url")
//    private String url;
//
//    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
//
//    @JsonProperty("name")
//    public String getName() {
//        return name;
//    }
//
//    @JsonProperty("name")
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    @JsonProperty("url")
//    public String getUrl() {
//        return url;
//    }
//
//    @JsonProperty("url")
//    public void setUrl(String url) {
//        this.url = url;
//    }
//
//    @JsonAnyGetter
//    public Map<String, Object> getAdditionalProperties() {
//        return this.additionalProperties;
//    }
//
//    @JsonAnySetter
//    public void setAdditionalProperty(String name, Object value) {
//        this.additionalProperties.put(name, value);
//    }
//
//}
