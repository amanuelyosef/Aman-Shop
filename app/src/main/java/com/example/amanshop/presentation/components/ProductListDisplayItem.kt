package com.example.amanshop.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.example.amanshop.presentation.ui.theme.Dimens

@Composable
fun ProductListDisplayItem(
    modifier: Modifier = Modifier,
    item:ProductDisplayListItemData
) {
    Row(
        modifier = modifier
            .height(Dimens.SearchedListItemHeight)
            .background(Color.LightGray)
            .padding(bottom = Dimens.OneDp)
            .background(Color.White)
            .padding(Dimens.MiniPadding)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(item.img),
            contentDescription = null,
            modifier = Modifier
                .weight(3.3f)
                .clip(RoundedCornerShape(Dimens.SmallRoundRadius))
        )

        Column(
            modifier = Modifier
                .padding(Dimens.MiniPadding)
                .weight(7f)
        ) {

            Text(
                text = item.title,
                fontWeight = FontWeight.Medium,
                fontSize = MaterialTheme.typography.bodySmall.fontSize,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2
            )

            Text(
                text = "ETB ${item.price}",
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier
                    .padding(bottom = Dimens.MiniPadding)
            )

            Text(
                text = item.descriptionWords,
                color = Color.DarkGray,
                fontSize = MaterialTheme.typography.bodySmall.fontSize
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun T() {
    ProductListDisplayItem(
        item = ProductDisplayListData[0]
    )
}



