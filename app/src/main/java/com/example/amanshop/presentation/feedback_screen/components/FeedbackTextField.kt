package com.example.amanshop.presentation.feedback_screen.components

import androidx.compose.foundation.background
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
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.amanshop.R
import com.example.amanshop.presentation.ui.theme.Dimens
import com.example.amanshop.presentation.ui.theme.customPrimaryContainerPink

@Composable
fun FeedbackTextField(
    onSend : (String)->Unit
) {

    Row(
        modifier = Modifier
            .background(customPrimaryContainerPink.copy(0.5f))
            .padding(Dimens.MediumPadding),
        verticalAlignment = Alignment.CenterVertically
    ) {

        var feedbackText by remember { mutableStateOf("") }

        Box(
            contentAlignment = Alignment.CenterStart,
            modifier = Modifier
                .weight(1f)
                .clip(RoundedCornerShape(Dimens.MediumRoundRadius))
                .background(Color.White)
                .heightIn(min = Dimens.TextFieldMinHeight, max = Dimens.TextFieldMaxHeight)
        ){
            BasicTextField(
                value = feedbackText,
                onValueChange = {feedbackText = it},
                modifier = Modifier
                    .padding(vertical = if (feedbackText.isEmpty()) 0.dp else 8.dp)
                    .padding(start = Dimens.SmallPadding)
                    .fillMaxWidth()
            )

            if(feedbackText.isEmpty()){

                Text(
                    text = "Send Feedback",
                    modifier = Modifier
                        .padding(horizontal = Dimens.SmallPadding),
                    color = Color.Gray
                )
            }
        }

        IconButton(
            onClick ={
                if(feedbackText.isNotEmpty()){
                    onSend(feedbackText)
                    feedbackText = ""
                }
            },
            modifier = Modifier
                .padding(start = Dimens.SmallPadding)
                .clip(CircleShape)
                .background(Color.Green)
                .size(Dimens.IconSizeLarge)
        ) {

            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_feedback_send),
                contentDescription = null,
                tint = Color.White
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun T() {
    FeedbackTextField(
        {}
    )
}