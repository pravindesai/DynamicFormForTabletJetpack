package com.example.dynamicformfortabletjetpack.composables

import android.text.SpannableString
import android.text.method.LinkMovementMethod
import android.text.style.URLSpan
import android.text.util.Linkify
import android.util.Log
import android.util.Patterns
import android.widget.TextView
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.util.LinkifyCompat

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddFormJsonArea(
    modifier: Modifier = Modifier,
    invalidJson:Boolean=false,
    addFormEvent: (formJson: String) -> Unit,
    resetJson: () -> Unit,

) {
    var text by remember { mutableStateOf("") }
    if (invalidJson){
        val mContext = LocalContext.current
        text = ""
        Toast.makeText(mContext, "InValid Json", Toast.LENGTH_LONG).show()
        resetJson()
    }
    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {

        TextField(modifier = modifier
            .fillMaxWidth()
            .weight(8f), value = text,
            onValueChange = { text = it },
            placeholder = {

                Column {
                    Text("Enter Valid JSON UI from")

                    AnnotatedClickableText()

                    Text(
                        "Supported Views are \n" +
                                "TEXT\n" +
                                "NUMBER\n" +
                                "SELECT\n" +
                                "RADIO_GROUP\n" +
                                "CHECKBOX_GROUP\n" +
                                "BUTTON\n" +
                                "HEADER\n")
                }
            })

        Button(modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .weight(1f),
            onClick = { addFormEvent(text) }) {
            Text(text = "Add Form")
        }
    }
}

@Composable
fun AnnotatedClickableText() {
    val link = "https://formbuilder.online/"
    val uriHandler = LocalUriHandler.current

    val annotatedText = buildAnnotatedString {
        pushStringAnnotation(
            tag = link,
            annotation = link
        )
        withStyle(
            style = SpanStyle(
                color = Color.Cyan,
            )
        ) {
            append(link)
        }
        pop()
    }

    ClickableText(
        text = annotatedText,
        onClick = { offset ->
            annotatedText.getStringAnnotations(
                tag = link,
                start = offset,
                end = offset
            )[0].let { annotation ->
                uriHandler.openUri(annotation.item)
            }
        }
    )
}