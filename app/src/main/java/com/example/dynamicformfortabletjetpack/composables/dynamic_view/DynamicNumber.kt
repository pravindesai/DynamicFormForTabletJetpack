package com.example.dynamicformfortabletjetpack.composables.dynamic_view

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dynamicformfortabletjetpack.model.UiSchemaItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DynamicNumber(
    modifier: Modifier = Modifier,
    uiSchemaItem: UiSchemaItem,
    onItemDataUpdated: (uiSchemaItem: UiSchemaItem) -> Unit
) {
    var text by remember { mutableStateOf(uiSchemaItem.value?:"") }
    LaunchedEffect(uiSchemaItem.value){
        text = uiSchemaItem.value?:""
    }

    Column(
        modifier = modifier
            .padding(5.dp)
            .fillMaxWidth()
            .wrapContentHeight()
    ) {

        val colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,)


        Text(modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(3.dp),
            color = Color.Black,
            fontSize = 14.sp,
            text = uiSchemaItem.label?:"")

        TextField(modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
            colors = colors,
            maxLines = 2,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            placeholder = { Text("Placeholder text") },
            value = text,
            onValueChange = {
                text = it
                uiSchemaItem.apply {
                    value = text
                }
                onItemDataUpdated(uiSchemaItem)
            })
    }

}