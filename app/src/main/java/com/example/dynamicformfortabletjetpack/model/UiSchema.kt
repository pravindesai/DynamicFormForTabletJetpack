package com.example.dynamicformfortabletjetpack.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class UiSchemaItem(

	@SerializedName("access")
	val access: Boolean? = null,

	@SerializedName("subtype")
	val subtype: String? = null,

	@SerializedName("name")
	val name: String? = null,

	@SerializedName("className")
	val className: String? = null,

	@SerializedName("style")
	val style: String? = null,

	@SerializedName("label")
	val label: String? = null,

	@SerializedName("type")
	val type: String? = null,

	@SerializedName("other")
	val other: Boolean? = null,

	@SerializedName("inline")
	val inline: Boolean? = null,

	@SerializedName("values")
	var values: List<ValuesItem?>? = null,

	@SerializedName("toggle")
	var toggle: Boolean? = null,

	@SerializedName("required")
	val required: Boolean? = null,

	@SerializedName("multiple")
	var multiple: Boolean? = null,

	@SerializedName("value")
	var value: String? = null
):Serializable

data class ValuesItem(

	@SerializedName("label")
	val label: String? = null,

	@SerializedName("value")
	var value: String? = null,

	@SerializedName("selected")
	var selected: Boolean? = null
):Serializable
