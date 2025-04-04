package com.example.amanshop.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.amanshop.presentation.ui.theme.Dimens

@Composable
fun PermissionRationaleDialog(
    permissionTextProvider: PermissionTextProvider,
    isPermanentlyDeclined: Boolean,
    onDismiss:()->Unit,
    onOkClick:()->Unit,
    onGoToSettingClick: ()->Unit,
    modifier: Modifier = Modifier
) {

    AlertDialog(
        title = {
            Text(text = "Permission Required")
        },
        text = {
            Text(
                text = permissionTextProvider.getDescription(isPermanentlyDeclined)
            )
        },
        confirmButton = {
            TextButton(
                onClick = {
                    if(isPermanentlyDeclined){
                        onGoToSettingClick()
                    }else{
                        onOkClick()
                    }
                }
            ) {

                Text(
                    text = if (isPermanentlyDeclined) "Go to Settings" else "OK",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(horizontal = Dimens.MediumPadding)
                )
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismiss()
                }
            ) {
                Text(
                    text = "Cancel",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(horizontal = Dimens.MediumPadding)
                )
            }
        },
        onDismissRequest = onDismiss,
        modifier = modifier
    )
}

interface PermissionTextProvider {
    fun getDescription(isPermanentlyDeclined: Boolean): String
}

class RecordAudioPermissionTextProvider : PermissionTextProvider{
    override fun getDescription(isPermanentlyDeclined: Boolean): String {
        return if(isPermanentlyDeclined){
            "It seems you permanently declined record permission. " +
                    "You can go to the app settings to grant it."
        }else{
            "This app needs access to your audio recorder so that you can " +
                    "record and send voice message "
        }
    }
}

@Preview
@Composable
private fun T() {
    PermissionRationaleDialog(
        permissionTextProvider = RecordAudioPermissionTextProvider(),
        isPermanentlyDeclined = false,
        onDismiss = {},
        onOkClick = {},
        onGoToSettingClick = {}
    )
}