package com.example.amanshop.presentation.search_screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.amanshop.R
import com.example.amanshop.presentation.components.SearchBarComponent
import com.example.amanshop.presentation.ui.theme.Dimens
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import com.example.amanshop.presentation.components.ScreenRoute
import com.example.amanshop.presentation.search_screen.components.RecentViewedList
import com.example.amanshop.presentation.search_screen.components.peopleSearchedForList
import com.example.amanshop.presentation.search_screen.components.searchHistoryData

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    navController : NavController
) {

    var selectedText by remember{ mutableStateOf("") }

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
                    .weight(7f)
                    .padding(end = Dimens.MediumPadding)
                ,
                enabled = true,
                navController = navController,
                onSearchNavigate = {searchedText ->
                    navController.navigate(ScreenRoute.SearchedListScreen.route + "/$searchedText")
                }
            )
        }

        LazyColumn {

            item {
                var hasItem by remember { mutableStateOf(searchHistoryData.isNotEmpty()) }
                var expanded by remember { mutableStateOf(false) }

                AnimatedVisibility(visible = hasItem){
                    Column {
                        
                        Row(
                            modifier = Modifier.fillMaxWidth()
                                .padding(horizontal = Dimens.SmallPadding)
                            ,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {

                            Text(
                                text = "Search history",
                                fontWeight = FontWeight.SemiBold,
                            )

                            Text(
                                text = "Clear All",
                                color = Color.Red,
                                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                                fontFamily = FontFamily.Default,
                                modifier = Modifier
                                    .padding(horizontal = Dimens.MiniPadding)
                                    .clip(RoundedCornerShape(Dimens.MediumRoundRadius))
                                    .clickable {
                                        searchHistoryData.clear()
                                        hasItem=false
                                    }
                                    .padding(Dimens.MiniPadding)
                            )
                        }

                        FlowRow (
                            modifier = Modifier
                                .padding(Dimens.MediumPadding),
                            verticalArrangement = Arrangement.Center,
                        ) {
                            searchHistoryData.forEachIndexed {index,it ->
                                if(index<8){
                                    Text(
                                        text = it,
                                        modifier = Modifier
                                            .padding(Dimens.MiniPadding)
                                            .clip(RoundedCornerShape(Dimens.SmallRoundRadius))
                                            .background(Color.LightGray)
                                            .clickable {
                                                selectedText = it
                                                navController.navigate(ScreenRoute.SearchedListScreen.route + "/$selectedText")
                                            }
                                            .padding(horizontal = Dimens.SmallPadding, vertical = Dimens.SmallRoundRadius)
                                        ,
                                        fontSize = MaterialTheme.typography.bodySmall.fontSize
                                    )
                                }
                                else if(expanded){
                                    Text(
                                        text = it,
                                        modifier = Modifier
                                            .padding(Dimens.MiniPadding)
                                            .clip(RoundedCornerShape(Dimens.SmallRoundRadius))
                                            .background(Color.LightGray)
                                            .clickable {
                                                selectedText = it
                                                navController.navigate(ScreenRoute.SearchedListScreen.route + "/$selectedText")
                                            }
                                            .padding(horizontal = Dimens.SmallPadding, vertical = Dimens.SmallRoundRadius),
                                        fontSize = MaterialTheme.typography.bodySmall.fontSize
                                    )
                                }

                                if(searchHistoryData.size == index+1 && searchHistoryData.size>=8){
                                    Box(
                                        modifier = Modifier
                                            .clip(CircleShape)
                                            .background(Color.LightGray)
                                            .padding(Dimens.SmallRoundRadius)
                                            .size(Dimens.IconSizeSmall)
                                            .align(Alignment.CenterVertically)
                                            .clickable { expanded = !expanded },
                                        contentAlignment = Alignment.Center
                                    ){
                                        Icon(
                                            imageVector = ImageVector.vectorResource(
                                                if(!expanded) R.drawable.ic_drop_down else R.drawable.ic_drop_up
                                            ),
                                            contentDescription = null,
                                        )
                                    }
                                }
                            }
                        }
                    }

                }

            }

            item {
                RecentViewedList(
                    navController = navController
                )

                Spacer(
                    modifier = Modifier.height(Dimens.MediumPadding)
                )
            }

            item {
                var expanded by remember { mutableStateOf(false) }

                Text(
                    text = "People also search for",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                    modifier = Modifier.padding(horizontal = Dimens.SmallPadding)
                )

                FlowRow (
                    modifier = Modifier.padding(Dimens.MediumPadding),
                    verticalArrangement = Arrangement.Center,

                    ) {
                    peopleSearchedForList.forEachIndexed {index,it ->
                        if(index<14){
                            Text(
                                text = it,
                                modifier = Modifier
                                    .padding(Dimens.MiniPadding)
                                    .clip(RoundedCornerShape(Dimens.SmallRoundRadius))
                                    .background(Color.LightGray)
                                    .clickable {
                                        selectedText = it
                                        navController.navigate(ScreenRoute.SearchedListScreen.route + "/$selectedText")
                                    }
                                    .padding(horizontal = Dimens.SmallPadding, vertical = Dimens.SmallRoundRadius)
                                ,
                                fontSize = MaterialTheme.typography.bodySmall.fontSize
                            )
                        }else if(expanded){
                            Text(
                                text = it,
                                modifier = Modifier
                                    .padding(Dimens.MiniPadding)
                                    .clip(RoundedCornerShape(Dimens.SmallRoundRadius))
                                    .background(Color.LightGray)
                                    .clickable {
                                        selectedText = it
                                        navController.navigate(ScreenRoute.SearchedListScreen.route + "/$selectedText")
                                    }
                                    .padding(horizontal = Dimens.SmallPadding, vertical = Dimens.SmallRoundRadius),
                                fontSize = MaterialTheme.typography.bodySmall.fontSize
                            )
                        }
                        if(peopleSearchedForList.size == index+1 && peopleSearchedForList.size>=14){
                            Box(
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .background(Color.LightGray)
                                    .padding(Dimens.SmallRoundRadius)
                                    .size(Dimens.IconSizeSmall)
                                    .align(Alignment.CenterVertically)
                                    .clickable { expanded = !expanded },
                                contentAlignment = Alignment.Center
                            ){
                                Icon(
                                    imageVector = ImageVector.vectorResource(
                                        if(!expanded) R.drawable.ic_drop_down else R.drawable.ic_drop_up
                                    ),
                                    contentDescription = null,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun t() {
    SearchScreen(
        navController = rememberNavController()
    )
}