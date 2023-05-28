package com.example.dynamicformfortabletjetpack.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dynamicformfortabletjetpack.model.UiSchemaItem
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class FormsViewModel @Inject constructor():ViewModel() {

    private val form1Json = "[{\"type\":\"text\",\"required\":false,\"label\":\"NAME\",\"className\":\"form-control\",\"name\":\"text-1685117190139-0\",\"access\":false,\"value\":\"Pravin Desai\",\"subtype\":\"text\"},{\"type\":\"number\",\"required\":false,\"label\":\"phonenumber\",\"className\":\"form-control\",\"name\":\"number-1685117602108-0\",\"access\":false},{\"type\":\"select\",\"required\":true,\"label\":\"AVAILABILITY\",\"className\":\"form-control\",\"name\":\"select-1685117616827-0\",\"access\":false,\"multiple\":false,\"values\":[{\"label\":\"DAY 1\",\"value\":\"d1\",\"selected\":true},{\"label\":\"DAY 2\",\"value\":\"d2\",\"selected\":false}]},{\"type\":\"radio-group\",\"required\":false,\"label\":\"GEDER\",\"inline\":false,\"name\":\"radio-group-1685117209189-0\",\"access\":false,\"other\":false,\"values\":[{\"label\":\"MALE\",\"value\":\"m\",\"selected\":true},{\"label\":\"FEMALE\",\"value\":\"f\",\"selected\":false}]},{\"type\":\"checkbox-group\",\"required\":false,\"label\":\"HOBBIES\",\"toggle\":false,\"inline\":false,\"name\":\"checkbox-group-1685117236625-0\",\"access\":false,\"other\":false,\"values\":[{\"label\":\"SPORTS\",\"value\":\"SP\",\"selected\":true},{\"label\":\"COMPUTER GAMES\",\"value\":\"CG\",\"selected\":false},{\"label\":\"EXCERCISE\",\"value\":\"EX\",\"selected\":false}]},{\"type\":\"button\",\"subtype\":\"submit\",\"label\":\"SUBMIT\",\"className\":\"btn-default btn\",\"name\":\"button-1685117301679-0\",\"access\":false,\"style\":\"default\"}]"
    private val form2Json = "[{\"type\":\"text\",\"required\":true,\"label\":\"NAME\",\"className\":\"form-control\",\"name\":\"text-1685117190139-0\",\"access\":false,\"value\":\"Pravin Desai\",\"subtype\":\"text\"},{\"type\":\"number\",\"required\":false,\"label\":\"phonenumber\",\"className\":\"form-control\",\"name\":\"number-1685117602108-0\",\"access\":false},{\"type\":\"select\",\"required\":true,\"label\":\"AVAILABILITY\",\"className\":\"form-control\",\"name\":\"select-1685117616827-0\",\"access\":false,\"multiple\":false,\"values\":[{\"label\":\"DAY 1\",\"value\":\"d1\",\"selected\":true},{\"label\":\"DAY 2\",\"value\":\"d2\",\"selected\":false}]},{\"type\":\"radio-group\",\"required\":false,\"label\":\"GEDER\",\"inline\":false,\"name\":\"radio-group-1685117209189-0\",\"access\":false,\"other\":false,\"values\":[{\"label\":\"MALE\",\"value\":\"m\",\"selected\":false},{\"label\":\"FEMALE\",\"value\":\"f\",\"selected\":true}]},{\"type\":\"button\",\"subtype\":\"submit\",\"label\":\"SUBMIT\",\"className\":\"btn-default btn\",\"name\":\"button-1685117301679-0\",\"access\":false,\"style\":\"default\"}]"

    private val formsList = MutableStateFlow(mutableListOf<List<UiSchemaItem>>())
    val _formList = formsList.asStateFlow()

    val _selectedForm = MutableStateFlow<List<UiSchemaItem>>(listOf())
    val _addNewForm = MutableStateFlow<Boolean>(false)
    val _invalidJson = MutableStateFlow<Boolean>(false)

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val uiFormsList = listOf(form1Json,form2Json).map {
                Gson().fromJson(it, Array<UiSchemaItem>::class.java)
            }
            formsList.value  = uiFormsList.map { it.toMutableList() }.toMutableList()

            uiFormsList.firstOrNull()?.toList()?.let {
                _selectedForm.value = it
                _addNewForm.value = false
            }?: run {
                _addNewForm.value = true
            }
        }
    }

    fun addJsonForm(json:String){
        try {
            val uiForm = Gson().fromJson(json, Array<UiSchemaItem>::class.java)
            val list = arrayListOf<List<UiSchemaItem>>()
            list.addAll(formsList.value)
            list.add(uiForm.toList())
            formsList.value = list

        }catch (e:Exception){
            e.printStackTrace()
            _invalidJson.value = true
        }
    }

    fun updateUiList(newUIList: MutableList<List<UiSchemaItem>>) {
        formsList.value = newUIList
    }
}