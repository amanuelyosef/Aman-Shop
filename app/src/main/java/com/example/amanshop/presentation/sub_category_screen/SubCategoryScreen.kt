package com.example.amanshop.presentation.sub_category_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.amanshop.R
import com.example.amanshop.presentation.components.ScreenRoute
import com.example.amanshop.presentation.components.SearchBarComponent
import com.example.amanshop.presentation.sub_category_screen.components.SubCategoryItemData
import com.example.amanshop.presentation.sub_category_screen.components.subCategoryMappedData
import com.example.amanshop.presentation.ui.theme.Dimens

@Composable
fun SubCategoryScreen(
    title:String,
    navController: NavController
) {
    val subList = subCategoryMappedData[title]!!
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            IconButton(
                modifier = Modifier.weight(1f),
                onClick = {
                    navController.popBackStack()
                }
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_backarrow),
                    contentDescription = null,
                    modifier = Modifier
                        .size(Dimens.IconSizeMedium),
                )
            }
            
            SearchBarComponent(
                modifier = Modifier
                    .clickable {
                        navController.navigate(ScreenRoute.SearchScreen.route)
                    }
                    .weight(7f)
                    .padding(end = Dimens.MediumPadding)
                ,
                enabled = false,
                navController = navController
            )
        }

        Text(
            text = title,
            modifier = Modifier.padding(horizontal = Dimens.SmallPadding),
            fontWeight = FontWeight.Bold,
            color = Color.Gray,
            fontSize = MaterialTheme.typography.bodyLarge.fontSize,
        )

        Spacer(
            modifier = Modifier
                .padding(top = Dimens.SmallPadding)
                .fillMaxWidth()
                .height(Dimens.OneDp)
                .background(Color.Gray.copy(0.8f))
        )
        LazyColumn {
            items(items = subList){item ->
                val searchedText = item.title
                SubCategoryListItem(
                    subCategoryItem = item,
                    modifier = Modifier.clickable {
                        navController.navigate(
                            ScreenRoute.SearchedListScreen.route+"/${searchedText}"
                        )
                    }
                )
            }
        }
        Spacer(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .background(Color.LightGray.copy(0.8f))
        )
    }
}

@Composable
fun SubCategoryListItem(
    subCategoryItem: SubCategoryItemData,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
    )  {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .height(80.dp)
                .padding(vertical = Dimens.SmallPadding)

        ){
            Image(
                painter = painterResource(subCategoryItem.img),
                contentDescription = null,
                modifier = Modifier.size(Dimens.subCategoryListItemHeight)
            )

            Column(
                modifier = Modifier.weight(1f)
                    .fillMaxHeight()
                    .padding(horizontal = Dimens.SmallPadding)
                ,
                verticalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = subCategoryItem.title,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize.times(0.9f),
                    color = Color.Black.copy(0.8f),
                )

                Text(
                    text = subCategoryItem.productNumber+" ads",
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize.times(0.8),
                    modifier = Modifier.padding(vertical = Dimens.MiniPadding),
                    color = Color.Black.copy(0.8f),
                )
            }
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_frontarrow),
                contentDescription = null,
                modifier = Modifier.padding(horizontal = Dimens.SmallPadding),
                tint = Color.Gray
            )
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(Dimens.OneDp)
                .background(Color.Gray.copy(0.8f))
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SubCategoryPreview() {
    SubCategoryScreen(
        title = "Electronics",
        navController = rememberNavController()
    )

    //SubCategoryListItem(subCategoryItem = subCategoryMappedData[CategoryListData[0].title]?.get(0)!!)
}