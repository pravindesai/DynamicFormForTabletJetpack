package com.example.dynamicformfortabletjetpack.composables.dynamic_view

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dynamicformfortabletjetpack.model.UiSchemaItem

@Composable
fun DynamicButton(
    modifier: Modifier = Modifier,
    uiSchemaItem: UiSchemaItem,
    onButtonClicked: (uiSchemaItem: UiSchemaItem) -> Unit
) {
    Column(
        modifier = modifier
            .padding(5.dp)
            .fillMaxWidth()
            .wrapContentHeight()
    ) {

        Button(
            modifier = Modifier.fillMaxWidth(),
            shape = CutCornerShape(5),
            onClick = {
                onButtonClicked(uiSchemaItem)
            }
            ){
            Text(modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(3.dp),
                color = Color.Black,
                fontSize = 14.sp,
                text = uiSchemaItem.label?:"")
        }
    }

}