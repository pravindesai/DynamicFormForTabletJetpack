package com.example.dynamicformfortabletjetpack.composables.dynamic_view

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Checkbox
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
fun DynamicCheckbox(
    modifier: Modifier = Modifier,
    uiSchemaItem: UiSchemaItem,
    onItemDataUpdated: (uiSchemaItem: UiSchemaItem) -> Unit
) {
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
                var checkboxItemSelected by remember { mutableStateOf(item?.selected?:false) }
                LaunchedEffect(key1 = item ){
                    checkboxItemSelected = item?.selected?:false
                }
                Checkbox( modifier = modifier
                    .wrapContentWidth()
                    .wrapContentHeight() ,
                    checked = checkboxItemSelected,
                    onCheckedChange = {
                        val i = uiSchemaItem.values?.find { it.hashCode()==item.hashCode() }
                        i?.selected = it
                        checkboxItemSelected = it
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
