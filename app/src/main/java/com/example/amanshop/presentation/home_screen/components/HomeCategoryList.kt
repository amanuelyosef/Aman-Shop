package com.example.amanshop.presentation.home_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.amanshop.R
import com.example.amanshop.presentation.components.CategoryListData
import com.example.amanshop.presentation.components.ScreenRoute
import com.example.amanshop.presentation.ui.theme.Dimens
import com.example.amanshop.presentation.ui.theme.customLightGray

@Composable
fun HomeCategoryList(
    modifier: Modifier = Modifier,
    navController: NavController
) {

    Column(
        modifier = modifier
            .padding(vertical = Dimens.SmallPadding)
            .background(customLightGray)
            //.background(Color.LightGray)

    ) {
        Row(
            modifier = modifier
                .clickable {
                    navController.navigate(ScreenRoute.CategoryScreen.route)
                }
                .fillMaxWidth()
                .padding(start = Dimens.SmallPadding)
            ,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Categories",
                fontWeight = FontWeight.Bold,
                fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                modifier = Modifier.padding(end = Dimens.SmallPadding)
            )

            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_frontarrow),
                contentDescription = null,
                modifier = Modifier.size(Dimens.IconSizeSmall)
            )
        }

        LazyRow(
            modifier = modifier
                .height(Dimens.HomeCategoryListHeight),
            contentPadding = PaddingValues(horizontal = Dimens.MiniPadding),
            verticalAlignment = Alignment.CenterVertically
            ) {

            itemsIndexed(items = CategoryListData.subList(0,5), key = { _, it -> it.img}){ index, item->
                Column(
                    modifier = modifier
                        .width(Dimens.HomeCategoryItemWidth)
                        .clickable {
                            navController.navigate(ScreenRoute.SubCategoryScreen.route+"/${item.title}")
                        },
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(item.img),
                        contentDescription = null,
                        modifier = Modifier
                            .size(Dimens.HomeCategoryImageSize)
                    )

                    Text(
                        text = item.title,
                        textAlign = TextAlign.Center,
                        fontSize = MaterialTheme.typography.labelMedium.fontSize,
                        lineHeight = MaterialTheme.typography.bodySmall.fontSize,
                        modifier = Modifier
                            .padding(horizontal = Dimens.MiniPadding)
                    )
                }

                if (index==4){
                    Box(
                        modifier = Modifier
                            .padding(horizontal = Dimens.MediumPadding)
                            .size(Dimens.IconSizeLarge)
                            .clip(CircleShape)
                            .background(Color.LightGray)
                            .clickable {
                                navController.navigate(ScreenRoute.CategoryScreen.route)
                            }
                            .padding(Dimens.MediumPadding),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.ic_frontarrow),
                            contentDescription = null
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeCategoryListPreview(modifier: Modifier = Modifier) {
    HomeCategoryList(navController = rememberNavController())
}