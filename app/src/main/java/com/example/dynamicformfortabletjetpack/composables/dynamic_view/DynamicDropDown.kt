package com.example.dynamicformfortabletjetpack.composables.dynamic_view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dynamicformfortabletjetpack.model.UiSchemaItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DynamicDropDown(
    modifier: Modifier = Modifier,
    uiSchemaItem: UiSchemaItem,
    onItemDataUpdated: (uiSchemaItem: UiSchemaItem) -> Unit
) {


    var isExpanded by remember { mutableStateOf(false) }
    var selectedText by remember {
        val selected = uiSchemaItem.values?.find { it?.selected == true }
        mutableStateOf(selected?.label ?: "")
    }
    LaunchedEffect(uiSchemaItem.values) {
        selectedText = uiSchemaItem.values?.find { it?.selected == true }?.label?:""
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


        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            ExposedDropdownMenuBox(
                expanded = isExpanded,
                onExpandedChange = {
                    isExpanded = !isExpanded
                }
            ) {
                TextField(
                    value = selectedText,
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded) },
                    modifier = Modifier.menuAnchor()
                )

                ExposedDropdownMenu(
                    expanded = isExpanded,
                    onDismissRequest = { isExpanded = false }
                ) {
                    uiSchemaItem.values?.forEach { item ->
                        DropdownMenuItem(
                            text = { Text(text = item?.label ?: "") },
                            onClick = {
                                selectedText = item?.label ?: ""
                                isExpanded = false

                                uiSchemaItem.values = uiSchemaItem.values?.map {
                                    it?.selected = (it.hashCode()==item.hashCode())
                                    it
                                }
                                onItemDataUpdated(uiSchemaItem)
                            }
                        )
                    }
                }
            }
        }
    }
}

