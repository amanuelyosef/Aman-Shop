package com.example.amanshop.presentation.chat_screen.components

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.amanshop.R
import com.example.amanshop.domain.recorder.AndroidAudioRecorder
import com.example.amanshop.presentation.chat_screen.ChatScreenViewModel
import com.example.amanshop.presentation.ui.theme.Dimens
import com.example.amanshop.presentation.ui.theme.customOnPrimaryContainerPink
import com.example.amanshop.presentation.ui.theme.customPrimaryPink
import kotlinx.coroutines.delay
import java.io.File
import java.util.UUID

@Composable
fun ChatTextField(
    modifier: Modifier = Modifier,
    context : Context,
    onRecording : ()->Unit,
    onStopRecording : (File?)->Unit,
    onSendMessage : (String) ->Unit,
    onImagePicker : ()-> Unit,
    launchPermission : MutableState<Boolean>
) {

    val viewModel = viewModel<ChatScreenViewModel>()

    val audioRecorder = remember { AndroidAudioRecorder(context) }
    var audioFile by remember { mutableStateOf<File?>(null) }

    var recording by remember { mutableStateOf(false) }

    var writtenMessage by remember { mutableStateOf("") }
    var audioVisible by remember { mutableStateOf(true) }

    val recordAudioPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) {isGranted ->
        if (isGranted) {
            // Start recording after permission is granted
            audioFile = File(context.cacheDir, "${UUID.randomUUID()}.mp3").also {
                audioRecorder.start(it)
            }
        } else {
            viewModel.onPermissionResult(
                permission = Manifest.permission.RECORD_AUDIO,
                isGranted= isGranted
            )
        }
    }

    LaunchedEffect(launchPermission.value) {
        if(launchPermission.value){
            recordAudioPermissionLauncher.launch(Manifest.permission.RECORD_AUDIO)
        }
    }

    Row(
        modifier = Modifier
            .background(Color.White)
            .padding(vertical = Dimens.MediumPadding),
        verticalAlignment = Alignment.CenterVertically
    ) {
        var focused by remember { mutableStateOf(false) }

        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_select_file),
            contentDescription = null,
            tint = Color.Gray,
            modifier = Modifier
                .clip(CircleShape)
                .clickable { onImagePicker() }
                .padding(Dimens.MiniPadding)
                .size(Dimens.IconSizeMedium)
        )

        Box(
            contentAlignment = Alignment.CenterStart,
            modifier = modifier
                .weight(1f)
                .clip(RoundedCornerShape(Dimens.MediumRoundRadius))
                .background(Color.White)
                .heightIn(min = Dimens.TextFieldMinHeight, max = Dimens.TextFieldMaxHeight)
        ){
            BasicTextField(
                value = writtenMessage,
                onValueChange = {writtenMessage = it},
                modifier = modifier
                    .fillMaxWidth()
                    .padding(vertical = if (writtenMessage.isEmpty()) 0.dp else 8.dp)
                    .padding(start = Dimens.SmallPadding.plus(Dimens.OneDp))
                    .onFocusChanged {
                        if(it.isFocused){
                            focused=true
                        }else{
                            focused=false
                        }
                    }
            )

            if(!focused){
                Text(
                    text = "Send Message",
                    modifier = Modifier
                        .padding(horizontal = Dimens.MiniPadding + Dimens.MiniPadding),
                    color = Color.Gray
                )
            }

        }

        if(writtenMessage.isEmpty()){
            audioVisible = true
        }else{
            audioVisible = false
        }

        if(audioVisible){
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_send_audio),
                contentDescription = null,
                tint = if (recording) Color.White else Color.Gray,
                modifier = Modifier
                    .clip(CircleShape)
                    .background(if (recording) customOnPrimaryContainerPink else Color.White)
                    .pointerInput(Unit) {
                        detectTapGestures(
                            onPress = {
                                val startTime = System.currentTimeMillis()
                                // Finger goes down
                                when {
                                    ContextCompat.checkSelfPermission(
                                        context,
                                        Manifest.permission.RECORD_AUDIO
                                    ) == PackageManager.PERMISSION_GRANTED -> {
                                        // Permission already granted, start recording
                                        audioFile = File(context.cacheDir, "${UUID.randomUUID()}.mp3").also {
                                            audioRecorder.start(it)
                                        }
                                    }

                                    else -> {
                                        // Request permission
                                        recordAudioPermissionLauncher.launch(Manifest.permission.RECORD_AUDIO)
                                    }
                                }
                                onRecording()
                                recording = true

                                tryAwaitRelease()

                                val endTime = System.currentTimeMillis()
                                val pressDuration = endTime - startTime
                                // Finger goes up
                                if(pressDuration>1000L){
                                    audioRecorder.stop()
                                    onStopRecording(audioFile)
                                    recording = false

                                }else{
                                    onStopRecording(null)
                                    recording = false;
                                    delay(500)
                                    audioRecorder.stop()
                                }
                            }
                        )
                    }
                    .padding(Dimens.MiniPadding)
                    .size(Dimens.IconSizeMedium)
            )
        }else{
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_feedback_send),
                contentDescription = null,
                tint = customPrimaryPink,
                modifier = Modifier
                    .clip(CircleShape)
                    .clickable {
                        onSendMessage(writtenMessage)
                        writtenMessage = ""
                    }
                    .padding(vertical = Dimens.MiniPadding, horizontal = Dimens.SmallPadding)
                    .size(Dimens.IconSizeMedium)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun T() {
    ChatTextField(
        context = LocalContext.current,
        onRecording = {},
        onStopRecording = {},
        onSendMessage = {},
        onImagePicker = {},
        launchPermission = remember { mutableStateOf(false) }
    )
}

