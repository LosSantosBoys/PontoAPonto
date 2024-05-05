package com.lossantos.pontoaponto.feature.auth.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lossantos.pontoaponto.utils.masker.MaskerTransformation

class InputComponents {
    @Composable
    fun Checkbox (
        checked: Boolean = false,
        onChange: (checked: Boolean) -> Unit,
        text: String = ""
    ) {
        androidx.compose.material3.Checkbox(
            checked = checked,
            onCheckedChange = onChange)
        Text(
            text = text,
            style = MaterialTheme.typography.labelSmall.copy(fontSize = 14.sp)
        )
    }


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun TextInput(
        label: String?,
        placeholder: String,
        value: String,
        onChange: (value: String) -> Unit,
        modifier: Modifier = Modifier,
        isError: Boolean = false
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = modifier
                .padding(vertical = 10.dp)
                .fillMaxWidth()
        ) {
            if (label != null) {
                Text(
                    label,
                    style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.SemiBold)
                )
            }

            TextField(
                value = value,
                onValueChange = onChange,
                label = {
                    Text(
                        placeholder,
                        style = MaterialTheme.typography.labelSmall,
                        color = Color(0xFF7D7D7D)
                    )
                },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color(0xFFFAFAFA),
                    disabledIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                shape = RoundedCornerShape(8.dp),
                singleLine = true,
                modifier = modifier.fillMaxWidth(),
                isError = isError,
            )
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun MaskInput(
        label: String?,
        placeholder: String,
        value: String,
        onChange: (value: String) -> Unit,
        mask: String,
        characterLimit: Int,
        modifier: Modifier = Modifier,
        isError: Boolean = false
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = modifier
                .padding(vertical = 10.dp)
                .fillMaxWidth()
        ) {
            if (label != null) {
                Text(
                    label,
                    style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.SemiBold)
                )
            }

            val textFieldColors = if (isError) {
                TextFieldDefaults.textFieldColors(
                    containerColor = Color(0xFFFAFAFA),
                    disabledIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Color.Red,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            } else {
                TextFieldDefaults.textFieldColors(
                    containerColor = Color(0xFFFAFAFA),
                    disabledIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            }

            TextField(
                value = value,
                onValueChange = {
                    if (it.length <= characterLimit) {
                        onChange(it)
                    }
                },
                label = {
                    Text(
                        placeholder,
                        style = MaterialTheme.typography.labelSmall,
                        color = Color(0xFF7D7D7D)
                    )
                },
                colors = textFieldColors,
                shape = RoundedCornerShape(8.dp),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                visualTransformation = MaskerTransformation(mask, characterLimit),
                modifier = modifier.fillMaxWidth(),
                isError = isError
            )
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun PhoneInput(
        label: String?,
        placeholder: String,
        value: String,
        onChange: (value: String) -> Unit,
        modifier: Modifier = Modifier,
        isError: Boolean = false
    ) {
        MaskInput(
            label = label,
            placeholder = placeholder,
            value = value,
            onChange = onChange,
            "(##)#####-####",
            11,
            modifier = modifier,
            isError = isError
        )
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun CpfInput(
        label: String?,
        placeholder: String,
        value: String,
        onChange: (value: String) -> Unit,
        modifier: Modifier = Modifier,
        isError: Boolean = false
    ) {
        MaskInput(
            label = label,
            placeholder = placeholder,
            value = value,
            onChange = onChange,
            "###.###.###-##",
            11,
            modifier = modifier,
            isError = isError
        )
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun DateInput(
        label: String?,
        placeholder: String,
        value: String,
        onChange: (value: String) -> Unit,
        modifier: Modifier = Modifier,
        isError: Boolean = false
    ) {
        MaskInput(
            label = label,
            placeholder = placeholder,
            value = value,
            onChange = onChange,
            "##/##/####",
            8,
            modifier = modifier,
            isError = isError
        )
    }
}