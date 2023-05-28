package com.example.dynamicformfortabletjetpack.composables.dynamic_view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dynamicformfortabletjetpack.model.UiSchemaItem

@Composable
fun DynamicHeader(
    modifier: Modifier = Modifier,
    uiSchemaItem: UiSchemaItem,
    onTextClicked: (uiSchemaItem: UiSchemaItem) -> Unit
) {

    Column(
        modifier = modifier
            .padding(5.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
    ) {

            Text(modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(3.dp)
                .clickable {
                       onTextClicked(uiSchemaItem)
                },
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                fontSize = 18.sp,
                text = uiSchemaItem.label?:"",
                )

        }


}