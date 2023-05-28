package com.example.dynamicformfortabletjetpack.composables.dynamic_view

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dynamicformfortabletjetpack.model.UiSchemaItem

@Composable
fun DynamicRadioGroup(
    modifier: Modifier = Modifier,
    uiSchemaItem: UiSchemaItem,
    onItemDataUpdated: (uiSchemaItem: UiSchemaItem) -> Unit
) {

    var selectedItem by remember {
        val selected = uiSchemaItem.values?.find { it?.selected == true }
        mutableStateOf(selected)
    }
    LaunchedEffect(uiSchemaItem.values) {
        selectedItem = uiSchemaItem.values?.find { it?.selected == true }
    }


    Column(
        modifier = modifier
            .padding(5.dp)
            .fillMaxWidth()
            .wrapContentHeight()
    ) {

        Text(
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(3.dp),
            color = Color.Black,
            fontSize = 14.sp,
            text = uiSchemaItem.label ?: ""
        )

            uiSchemaItem.values?.forEach { item ->

                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton( modifier = modifier
                        .wrapContentWidth()
                        .wrapContentHeight() ,
                        selected = selectedItem?.label==item?.label,
                        onClick = {
                            selectedItem = item
                            uiSchemaItem.values = uiSchemaItem.values?.map {
                                it?.selected = (it.hashCode()==item.hashCode())
                                it
                            }
                            onItemDataUpdated(uiSchemaItem)
                        }
                    )
                    Text(modifier = modifier
                        .wrapContentWidth()
                        .wrapContentHeight() ,
                        text = item?.label?:"",
                        color = Color.Black,
                        fontSize = 12.sp)
                }

            }
    }
}
