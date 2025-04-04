package com.example.amanshop.presentation.chat_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.amanshop.R
import com.example.amanshop.domain.playback.formatDuration
import com.example.amanshop.presentation.ui.theme.Dimens
import com.example.amanshop.presentation.ui.theme.customPrimaryContainerPink
import com.example.amanshop.presentation.ui.theme.customPrimaryPink
import kotlinx.coroutines.delay

@Composable
fun RecordingDisplayComponent() {
    var timeCount by remember { mutableLongStateOf(0L) }
    LaunchedEffect(Unit) {
        while (true){
            delay(1000)
            timeCount+=1000
        }
    }

    Column(
        modifier = Modifier
            .clip(CircleShape)
            .background(customPrimaryPink)
            .size(Dimens.ChatScreenRecordingSign)
            .padding(Dimens.LargePadding)
        ,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_send_audio),
            contentDescription = null,
            tint = customPrimaryContainerPink,
            modifier = Modifier.weight(1f)
                .fillMaxWidth()
                .padding(Dimens.MediumPadding)
        )
//        Box(
//            modifier = Modifier
//                .padding(vertical = Dimens.MiniPadding)
//                .size(Dimens.MiniPadding)
//                .clip(CircleShape)
//                .background(Color.Red)
//        )
        Text(
            text = "Recording",
            color = customPrimaryContainerPink,
            fontSize = MaterialTheme.typography.bodyMedium.fontSize

        )

        Text(
            text = formatDuration(timeCount),
            color = customPrimaryContainerPink,
            fontSize = MaterialTheme.typography.bodyMedium.fontSize
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun T() {
    RecordingDisplayComponent()
}
