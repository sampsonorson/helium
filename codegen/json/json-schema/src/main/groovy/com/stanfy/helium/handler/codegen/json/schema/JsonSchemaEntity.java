package com.stanfy.helium.handler.codegen.json.schema;

import com.google.gson.annotations.SerializedName;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * JSON schema entity. Can be used for the root schema object or for fields.
 *
 * @author Michael Pustovit mpustovit@stanfy.com.ua
 */
public class JsonSchemaEntity {
  @SerializedName("$schema")
  String schema;

  @SerializedName("$ref")
  final String ref;

  @SerializedName("type")
  JsonType type;

  @SerializedName("description")
  String description;

  @SerializedName("properties")
  Map<String, JsonSchemaEntity> properties;

  @SerializedName("required")
  List<String> required;

  @SerializedName("items")
  JsonSchemaEntity items;

  @SerializedName("enum")
  List<String> enumeration;

  JsonSchemaEntity() {
    this(null);
  }

  public JsonSchemaEntity(String ref) {
    this.ref = ref;
  }

  public void addProperty(final String propertyName, final JsonSchemaEntity property) {
    if (properties == null) {
      this.properties = new LinkedHashMap<String, JsonSchemaEntity>();
    }

    properties.put(propertyName, property);
  }

  public void addRequired(final String requiredPropertyName) {
    if (required == null) {
      this.required = new LinkedList<String>();
    }

    required.add(requiredPropertyName);
  }

}
