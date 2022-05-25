//-----------------------------------com.livingtechUSA.Ability.java-----------------------------------
//
//package com.livingtechUSA;
//
//import java.io.Serializable;
//import java.util.HashMap;
//import java.util.Map;
//import javax.annotation.Generated;
//import android.os.Parcelable;
//import android.os.Parcelable.Creator;
//import android.os.Parcelable.Creator;
//import com.fasterxml.jackson.annotation.JsonAnyGetter;
//import com.fasterxml.jackson.annotation.JsonAnySetter;
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.fasterxml.jackson.annotation.JsonInclude;
//import com.fasterxml.jackson.annotation.JsonProperty;
//import com.fasterxml.jackson.annotation.JsonPropertyOrder;
//
//@JsonInclude(JsonInclude.Include.NON_NULL)
//@JsonPropertyOrder({
//    "ability"
//})
//@Generated("jsonschema2pojo")
//public class Ability implements Serializable, Parcelable
//{
//
//    @JsonProperty("ability")
//    public Ability__1 ability;
//    @JsonIgnore
//    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
//    public final static Creator<Ability> CREATOR = new Creator<Ability>() {
//
//
//        @SuppressWarnings({
//            "unchecked"
//        })
//        public Ability createFromParcel(android.os.Parcel in) {
//            return new Ability(in);
//        }
//
//        public Ability[] newArray(int size) {
//            return (new Ability[size]);
//        }
//
//    }
//    ;
//    private final static long serialVersionUID = 4768092079079241208L;
//
//    protected Ability(android.os.Parcel in) {
//        this.ability = ((Ability__1) in.readValue((Ability__1.class.getClassLoader())));
//        this.additionalProperties = ((Map<String, Object> ) in.readValue((Map.class.getClassLoader())));
//    }
//
//    public Ability() {
//    }
//
//    @JsonAnyGetter
//    public Map<String, Object> getAdditionalProperties() {
//    return this.additionalProperties;
//}
//
//    @JsonAnySetter
//    public void setAdditionalProperty(String name, Object value) {
//        this.additionalProperties.put(name, value);
//    }
//
//    public void writeToParcel(android.os.Parcel dest, int flags) {
//        dest.writeValue(ability);
//        dest.writeValue(additionalProperties);
//    }
//
//    public int describeContents() {
//        return 0;
//    }
//
//}
//-----------------------------------com.livingtechUSA.Ability__1.java-----------------------------------
//
//package com.livingtechUSA;
//
//import java.io.Serializable;
//import java.util.HashMap;
//import java.util.Map;
//import javax.annotation.Generated;
//import android.os.Parcelable;
//import android.os.Parcelable.Creator;
//import android.os.Parcelable.Creator;
//import com.fasterxml.jackson.annotation.JsonAnyGetter;
//import com.fasterxml.jackson.annotation.JsonAnySetter;
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.fasterxml.jackson.annotation.JsonInclude;
//import com.fasterxml.jackson.annotation.JsonProperty;
//import com.fasterxml.jackson.annotation.JsonPropertyOrder;
//
//@JsonInclude(JsonInclude.Include.NON_NULL)
//@JsonPropertyOrder({
//    "name",
//    "url"
//})
//@Generated("jsonschema2pojo")
//public class Ability__1 implements Serializable, Parcelable
//{
//
//    @JsonProperty("name")
//    public String name;
//    @JsonProperty("url")
//    public String url;
//    @JsonIgnore
//    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
//    public final static Creator<Ability__1> CREATOR = new Creator<Ability__1>() {
//
//
//        @SuppressWarnings({
//            "unchecked"
//        })
//        public Ability__1 createFromParcel(android.os.Parcel in) {
//            return new Ability__1(in);
//        }
//
//        public Ability__1 [] newArray(int size) {
//            return (new Ability__1[size]);
//        }
//
//    }
//    ;
//    private final static long serialVersionUID = 2422436156800149385L;
//
//    protected Ability__1(android.os.Parcel in) {
//        this.name = ((String) in.readValue((String.class.getClassLoader())));
//        this.url = ((String) in.readValue((String.class.getClassLoader())));
//        this.additionalProperties = ((Map<String, Object> ) in.readValue((Map.class.getClassLoader())));
//    }
//
//    public Ability__1() {
//    }
//
//    @JsonAnyGetter
//    public Map<String, Object> getAdditionalProperties() {
//    return this.additionalProperties;
//}
//
//    @JsonAnySetter
//    public void setAdditionalProperty(String name, Object value) {
//        this.additionalProperties.put(name, value);
//    }
//
//    public void writeToParcel(android.os.Parcel dest, int flags) {
//        dest.writeValue(name);
//        dest.writeValue(url);
//        dest.writeValue(additionalProperties);
//    }
//
//    public int describeContents() {
//        return 0;
//    }
//
//}
//-----------------------------------com.livingtechUSA.Pokemon.java-----------------------------------
//
//package com.livingtechUSA;
//
//import java.io.Serializable;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import javax.annotation.Generated;
//import android.os.Parcelable;
//import android.os.Parcelable.Creator;
//import android.os.Parcelable.Creator;
//import com.fasterxml.jackson.annotation.JsonAnyGetter;
//import com.fasterxml.jackson.annotation.JsonAnySetter;
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.fasterxml.jackson.annotation.JsonInclude;
//import com.fasterxml.jackson.annotation.JsonProperty;
//import com.fasterxml.jackson.annotation.JsonPropertyOrder;
//
//@JsonInclude(JsonInclude.Include.NON_NULL)
//@JsonPropertyOrder({
//    "abilities"
//})
//@Generated("jsonschema2pojo")
//public class Pokemon implements Serializable, Parcelable
//{
//
//    @JsonProperty("abilities")
//    public List<Ability> abilities = null;
//    @JsonIgnore
//    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
//    public final static Creator<Pokemon> CREATOR = new Creator<Pokemon>() {
//
//
//        @SuppressWarnings({
//            "unchecked"
//        })
//        public Pokemon createFromParcel(android.os.Parcel in) {
//            return new Pokemon(in);
//        }
//
//        public Pokemon[] newArray(int size) {
//            return (new Pokemon[size]);
//        }
//
//    }
//    ;
//    private final static long serialVersionUID = -3748500847136920749L;
//
//    protected Pokemon(android.os.Parcel in) {
//        in.readList(this.abilities, (com.livingtechUSA.Ability.class.getClassLoader()));
//        this.additionalProperties = ((Map<String, Object> ) in.readValue((Map.class.getClassLoader())));
//    }
//
//    public Pokemon() {
//    }
//
//    @JsonAnyGetter
//    public Map<String, Object> getAdditionalProperties() {
//    return this.additionalProperties;
//}
//
//    @JsonAnySetter
//    public void setAdditionalProperty(String name, Object value) {
//        this.additionalProperties.put(name, value);
//    }
//
//    public void writeToParcel(android.os.Parcel dest, int flags) {
//        dest.writeList(abilities);
//        dest.writeValue(additionalProperties);
//    }
//
//    public int describeContents() {
//        return 0;
//    }
//
//}