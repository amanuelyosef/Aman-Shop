package com.example.amanshop.presentation.searched_list_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.amanshop.R
import com.example.amanshop.presentation.ui.theme.Dimens
import com.example.amanshop.presentation.ui.theme.customPrimaryContainerPink
import com.example.amanshop.presentation.ui.theme.customPrimaryPink

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FilterSearchedListScreen(
    navController: NavController
) {

    var minPriceFilter by remember { mutableStateOf("0") }
    var maxPriceFilter by remember { mutableStateOf("0") }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = {navController.popBackStack()}
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_backarrow),
                    contentDescription = null
                )
            }

            Text(
                text = "Filter",
                fontWeight = FontWeight.ExtraBold,
                fontSize = MaterialTheme.typography.headlineMedium.fontSize
            )
        }

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = Dimens.SmallPadding, vertical = Dimens.MediumPadding)
        ) {

            item {
                var expanded by remember { mutableStateOf(false) }

                Text(
                    text = "Brand Filter",
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Default,
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                    modifier = Modifier.padding(top = Dimens.SmallPadding)
                )

                FlowRow (
                    modifier = Modifier.padding(Dimens.MediumPadding),
                    verticalArrangement = Arrangement.Center,
                    ) {
                    brandFilterData.forEachIndexed { index, item ->
                        var selected by remember { mutableStateOf(item.selected) }
                        if(index<8){
                            Text(
                                text = item.name,
                                modifier = Modifier
                                    .padding(Dimens.MiniPadding)
                                    .clip(RoundedCornerShape(Dimens.SmallRoundRadius))
                                    .background(if(selected) customPrimaryContainerPink else Color.LightGray)
                                    .clickable {
                                        item.selected = !item.selected
                                        selected = !selected
                                    }
                                    .padding(horizontal = Dimens.SmallPadding, vertical = Dimens.SmallRoundRadius)
                                ,
                                fontSize = MaterialTheme.typography.bodySmall.fontSize
                            )
                        }else if(expanded){
                            Text(
                                text = item.name,
                                modifier = Modifier
                                    .padding(Dimens.MiniPadding)
                                    .clip(RoundedCornerShape(Dimens.SmallRoundRadius))
                                    .background(if(selected) customPrimaryContainerPink else Color.LightGray)
                                    .clickable {
                                        item.selected = !item.selected
                                        selected = !selected
                                    }
                                    .padding(horizontal = Dimens.SmallPadding, vertical = Dimens.SmallRoundRadius),
                                fontSize = MaterialTheme.typography.bodySmall.fontSize
                            )
                        }

                        if(brandFilterData.size == index+1 && brandFilterData.size>=8){
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

            item {
                var selected by remember{ mutableStateOf(false) }
                Text(
                    text = "Delivery Service Filter",
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Default,
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                    modifier = Modifier.padding(vertical = Dimens.SmallPadding)
                )

                Text(
                    text = "Free Delivery",
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                    modifier = Modifier
                        .padding(horizontal = Dimens.MediumPadding)
                        .clip(RoundedCornerShape(Dimens.MediumRoundRadius))
                        .background(if(selected) customPrimaryContainerPink else Color.LightGray)
                        .clickable { selected = !selected }
                        .padding(horizontal = Dimens.MediumPadding, vertical = Dimens.SmallPadding)
                )

                Spacer(Modifier.height(Dimens.MediumPadding))

            }

            item {
                Text(
                    text = "Price Filter",
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Default,
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                    modifier = Modifier.padding(vertical = Dimens.SmallPadding)
                )

                Row(
                    modifier = Modifier.fillMaxWidth()
                        .padding(horizontal = Dimens.MediumPadding)
                    ,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = "Min.Price",
                            fontWeight = FontWeight.SemiBold,
                            fontFamily = FontFamily.Default,
                            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                            //modifier = Modifier.padding(vertical = Dimens.SmallPadding)
                        )

                        OutlinedTextField(
                            value = minPriceFilter,
                            onValueChange = {
                                try {
                                    it.toInt()
                                    minPriceFilter = it
                                }catch(_: Exception){}

                                            },
                            singleLine = true,
                            textStyle = TextStyle(
                                fontSize = MaterialTheme.typography.bodyMedium.fontSize
                            )
                        )
                    }
                    Text(text = "    _    ", fontWeight = FontWeight.Bold)

                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = "Max.Price",
                            fontWeight = FontWeight.SemiBold,
                            fontFamily = FontFamily.Default,
                            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                            //modifier = Modifier.padding(vertical = Dimens.SmallPadding)
                        )

                        OutlinedTextField(
                            value = maxPriceFilter,
                            onValueChange = {
                                try {
                                    it.toInt()
                                    minPriceFilter = it
                                }catch(_: Exception){}
                            },
                            singleLine = true,
                            textStyle = TextStyle(
                                fontSize = MaterialTheme.typography.bodyMedium.fontSize
                            )
                        )
                    }
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimens.MediumPadding),
            horizontalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .padding(horizontal = Dimens.SmallPadding)
                    .clip(CircleShape)
                    .background(Color.LightGray.copy(0.8f))
                    .clickable {
                        navController.popBackStack()
                    }
                    .padding(horizontal = Dimens.LargePadding.plus(Dimens.SmallPadding), vertical = Dimens.MediumPadding)
                ,
                contentAlignment = Alignment.Center
            ){
                Text(
                    text = "Cancel",
                    fontWeight = FontWeight.Bold,
                    color = customPrimaryPink,
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize
                )
            }

            Box(
                modifier = Modifier
                    .padding(horizontal = Dimens.SmallPadding)
                    .clip(CircleShape)
                    .background(customPrimaryPink.copy(0.8f))
                    .clickable {
                        navController.popBackStack()
                    }
                    .padding(horizontal = Dimens.LargePadding.plus(Dimens.SmallPadding), vertical = Dimens.MediumPadding)
                ,
                contentAlignment = Alignment.Center
            ){
                Text(
                    text = "Show Result",
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun T() {
    FilterSearchedListScreen(
        rememberNavController()
    )
}