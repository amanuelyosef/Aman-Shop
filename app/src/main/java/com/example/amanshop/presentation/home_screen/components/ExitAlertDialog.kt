package com.example.amanshop.presentation.home_screen.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.amanshop.presentation.ui.theme.Dimens

@Composable
fun ExitAlertDialog(
    onConfirm : () -> Unit,
    onDismiss : ()->Unit
) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        confirmButton = {
            Button(
                onClick = { onConfirm() },
                colors = ButtonColors(
                    containerColor = Color.Red.copy(0.5f),
                    contentColor = Color.White,
                    disabledContentColor = Color.Unspecified,
                    disabledContainerColor = Color.Unspecified
                ),
                modifier = Modifier.padding(horizontal = Dimens.SmallPadding)
            ) {
                Text(
                    text = "Yes",
                    fontWeight = FontWeight.Bold
                )
            }
        },
        dismissButton = {
            Button(
                onClick = { onDismiss() },
                colors = ButtonColors(
                    containerColor = Color.Green.copy(0.7f),
                    contentColor = Color.White,
                    disabledContentColor = Color.Unspecified,
                    disabledContainerColor = Color.Unspecified
                )
            ) {
                Text(
                    text = "No",
                    //color = Color.Green,
                    fontWeight = FontWeight.Bold
                )
            }
        },
        title = {
            Text(
                text = "Exit",
                fontWeight = FontWeight.Bold,
                fontSize = MaterialTheme.typography.bodyLarge.fontSize
            )
                },
        text = { Text("Are you sure you want to exit?") },
        //modifier = Modifier.background(Color.White)
    )
}

@Preview
@Composable
private fun T() {
    ExitAlertDialog(
        onConfirm = {},
        onDismiss = {}
    )
}