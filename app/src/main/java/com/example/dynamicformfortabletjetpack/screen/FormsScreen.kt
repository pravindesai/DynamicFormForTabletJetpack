package com.example.dynamicformfortabletjetpack.screen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import com.example.dynamicformfortabletjetpack.composables.AddFormCard
import com.example.dynamicformfortabletjetpack.composables.AddFormJsonArea
import com.example.dynamicformfortabletjetpack.composables.FormCard
import com.example.dynamicformfortabletjetpack.composables.ShowForm
import com.example.dynamicformfortabletjetpack.ui.theme.Purple40
import com.example.dynamicformfortabletjetpack.ui.theme.Purple80

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun FormsScreen(modifier: Modifier = Modifier, viewModel: FormsViewModel) {

    Row(modifier = Modifier.fillMaxSize()) {

        val formList by remember { viewModel._formList }.collectAsState()
        val selectedForm by remember { viewModel._selectedForm }.collectAsState()
        val addNewForm by remember { viewModel._addNewForm }.collectAsState()
        val invalidJson by remember { viewModel._invalidJson }.collectAsState()

        val keyboardController = LocalSoftwareKeyboardController.current
        val focusManager = LocalFocusManager.current

        Column(
            modifier = Modifier
                .weight(3f)
                .fillMaxHeight()
                .background(Purple80)
        ) {

            LazyColumn(modifier = Modifier.fillMaxHeight()) {
                items(formList) {
                    FormCard(
                        label = "Form - ${formList.indexOf(it)}",
                        isSelected = it.hashCode()==selectedForm.hashCode(),
                        uiItems = it) {
                        viewModel._addNewForm.value = false
                        viewModel._selectedForm.value = it

                        keyboardController?.hide()
                        focusManager.clearFocus()
                    }
                }

                item {
                    AddFormCard(label = "Add New Form Json") {
                        viewModel._addNewForm.value = true
                        viewModel._selectedForm.value = listOf()

                    }
                }
            }
        }
        Column(
            modifier = Modifier
                .weight(5f)
                .fillMaxHeight()
                .background(Purple40)
        ) {
            if (addNewForm) {
                AddFormJsonArea(invalidJson = invalidJson,
                  addFormEvent = {
                    Log.e("***Add Form JSON ", it)
                    viewModel.addJsonForm(it)
                },
                    resetJson = {
                        viewModel._invalidJson.value = false
                    })
            }else{
                ShowForm(uiItems = selectedForm,
                    uiItemsUpdated = {
                        val indexOfSelectedForm = viewModel._formList.value.indexOf(selectedForm)
                        val newUIList = viewModel._formList.value
                        newUIList[indexOfSelectedForm] = it
                        viewModel.updateUiList(newUIList)
                    })
            }
        }

    }
}






