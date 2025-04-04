package com.example.amanshop.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.example.amanshop.presentation.ui.theme.Dimens

@Composable
fun ProductGridDisplayItem(
    modifier: Modifier = Modifier,
    item:ProductDisplayListItemData
) {
    Column(
        modifier = modifier
            .padding(Dimens.SmallPadding)
    ) {
        Image(
            painter = painterResource(item.img),
            contentDescription = null,
            modifier = Modifier.clip(RoundedCornerShape(Dimens.SmallRoundRadius))
        )

        Text(
            text = item.title,
            fontWeight = FontWeight.Medium,
            fontSize = MaterialTheme.typography.bodySmall.fontSize,
            overflow = TextOverflow.Ellipsis,
            maxLines = 2,
        )
        Text(
            text = "ETB ${item.price}",
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier
                .padding(bottom = Dimens.OneDp)
        )

        Text(
            text = item.descriptionWords,
            color = Color.DarkGray,
            fontSize = MaterialTheme.typography.bodySmall.fontSize
        )
    }
}


@Preview(showBackground = true)
@Composable
fun ProductDisplayItemPreview(modifier: Modifier = Modifier) {
    ProductGridDisplayItem(item = ProductDisplayListData[0])
}