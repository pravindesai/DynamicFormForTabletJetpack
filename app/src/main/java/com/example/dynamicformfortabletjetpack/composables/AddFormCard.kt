package com.example.dynamicformfortabletjetpack.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddFormCard(
    modifier: Modifier = Modifier,
    label: String,
    onAddFormClick: () -> Unit
) {
    Row(modifier = modifier.padding(5.dp)) {
        Button(onClick = { onAddFormClick() }) {
            Text(
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxWidth(),
                color = Color.Black,
                text = label,
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}