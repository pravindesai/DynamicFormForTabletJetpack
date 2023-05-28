package com.example.dynamicformfortabletjetpack.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.dynamicformfortabletjetpack.composables.dynamic_view.DynamicButton
import com.example.dynamicformfortabletjetpack.composables.dynamic_view.DynamicCheckbox
import com.example.dynamicformfortabletjetpack.composables.dynamic_view.DynamicDropDown
import com.example.dynamicformfortabletjetpack.composables.dynamic_view.DynamicHeader
import com.example.dynamicformfortabletjetpack.composables.dynamic_view.DynamicNumber
import com.example.dynamicformfortabletjetpack.composables.dynamic_view.DynamicRadioGroup
import com.example.dynamicformfortabletjetpack.composables.dynamic_view.DynamicText
import com.example.dynamicformfortabletjetpack.model.DynamicViewType
import com.example.dynamicformfortabletjetpack.model.DynamicViewType.UNKNOWN
import com.example.dynamicformfortabletjetpack.model.UiSchemaItem

@Composable
fun ShowForm(modifier: Modifier = Modifier,
             uiItems:List<UiSchemaItem>,
             uiItemsUpdated:(uiItems:List<UiSchemaItem>)->Unit){
    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        LazyColumn(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()){
            items(uiItems){

                when(it.type){
                    DynamicViewType.TEXT->{
                        DynamicText(uiSchemaItem = it){ item->
                            updateDataset(uiItems,item,uiItemsUpdated)
                        }
                    }
                    DynamicViewType.NUMBER->{
                        DynamicNumber(uiSchemaItem = it){ item->
                            updateDataset(uiItems,item,uiItemsUpdated)
                        }
                    }
                    DynamicViewType.BUTTON->{
                        DynamicButton(uiSchemaItem = it){

                        }
                    }
                    DynamicViewType.HEADER->{
                        DynamicHeader(uiSchemaItem = it){

                        }
                    }
                    DynamicViewType.SELECT->{
                        DynamicDropDown(uiSchemaItem = it){ item->
                            updateDataset(uiItems,item,uiItemsUpdated)
                        }
                    }
                    DynamicViewType.RADIO_GROUP->{
                        DynamicRadioGroup(uiSchemaItem = it){ item->
                            updateDataset(uiItems,item,uiItemsUpdated)
                        }
                    }
                    DynamicViewType.CHECKBOX_GROUP->{
                        DynamicCheckbox(uiSchemaItem = it){ item->
                            updateDataset(uiItems,item,uiItemsUpdated)
                        }
                    }
                    else->{
                        Text(modifier = modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                            text = it.type?:UNKNOWN)
                    }
                }
            }
        }
    }
}

fun updateDataset(uiItems:List<UiSchemaItem>,
                  item: UiSchemaItem,
                  uiItemsUpdated:(uiItems:List<UiSchemaItem>)->Unit){
    val changedItem = uiItems.find { it.name == item.name}
    val indexToReplace = uiItems.indexOf(changedItem)
    val newUiItemsList = mutableListOf<UiSchemaItem>()
    newUiItemsList.addAll(uiItems)
    newUiItemsList[indexToReplace] = item
    uiItemsUpdated(newUiItemsList)
}