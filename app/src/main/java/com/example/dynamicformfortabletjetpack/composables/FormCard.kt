package com.example.dynamicformfortabletjetpack.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dynamicformfortabletjetpack.model.UiSchemaItem
import com.example.dynamicformfortabletjetpack.ui.theme.Purple40
import com.example.dynamicformfortabletjetpack.ui.theme.PurpleGrey40

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormCard(
    modifier: Modifier = Modifier,
    label: String,
    uiItems: List<UiSchemaItem>,
    isSelected:Boolean=false,
    onShowFormClick: (items: List<UiSchemaItem>) -> Unit
) {
    Row(modifier = modifier) {
        val cardColors = CardDefaults.cardColors(
            containerColor = if (isSelected) PurpleGrey40 else Purple40,
        )
        Card(
            modifier = Modifier
                .padding(3.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            border = BorderStroke(1.5.dp, Color.Gray),
            colors = cardColors,
            onClick = {
                onShowFormClick(uiItems)
            }
        ) {
            Text(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                color = Color.White,
                text = label,
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}
