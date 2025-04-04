package com.example.amanshop.presentation.feedback_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.amanshop.R
import com.example.amanshop.presentation.components.FeedbackDataItem
import com.example.amanshop.presentation.components.ReportScreenHint
import com.example.amanshop.presentation.components.ScreenRoute
import com.example.amanshop.presentation.components.SellersInfoList
import com.example.amanshop.presentation.components.getCurrentDate
import com.example.amanshop.presentation.feedback_screen.components.FeedbackTextField
import com.example.amanshop.presentation.ui.theme.Dimens
import com.example.amanshop.presentation.ui.theme.customPrimaryPink
import com.example.amanshop.presentation.ui.theme.nunitoFontFamily


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedbackScreen(
    navController: NavController,
    sellerId : String
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val feedbacks = remember { mutableStateListOf(*(SellersInfoList.find{it.id == sellerId}!!.feedbackList).toTypedArray()) }
    val keyboardController = LocalSoftwareKeyboardController.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Feedback",
                        fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                        fontWeight = FontWeight.Bold,
                        color = customPrimaryPink,
                        fontFamily = nunitoFontFamily
                    )
                },
                navigationIcon = {
                    IconButton(
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
                },
                scrollBehavior = scrollBehavior,
                colors = TopAppBarDefaults.topAppBarColors(
                    scrolledContainerColor = Color.White
                )
            )
        }
    ){
        Column(
            modifier = Modifier.padding(it)
        ) {

            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .nestedScroll(scrollBehavior.nestedScrollConnection)
            ) {
                items(items = feedbacks, key = {it.id}) {feedbackItem ->
                    var feedbackOptionExpanded by remember { mutableStateOf(false) }
                    var customerReaction by remember{ mutableStateOf(feedbackItem.customerReaction) }

                    Column(
                        modifier = Modifier
                            .padding(
                                horizontal = Dimens.MediumPadding,
                                vertical = Dimens.MiniPadding
                            )
                    ) {
                        Column(
                            modifier = Modifier
                                .clip(RoundedCornerShape(Dimens.MediumRoundRadius))
                                .background(Color.LightGray.copy(0.8f))
                                .padding(Dimens.SmallPadding)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {

                                    Image(
                                        painter = painterResource(feedbackItem.customerImage),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .padding(horizontal = Dimens.MiniPadding)
                                            .size(Dimens.IconSizeSmall)
                                    )

                                    Text(
                                        text = feedbackItem.customerName,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = MaterialTheme.typography.bodySmall.fontSize
                                    )
                                }

                                Text(
                                    text = feedbackItem.date,
                                    fontSize = MaterialTheme.typography.bodySmall.fontSize,
                                    color = Color.DarkGray
                                )
                            }

                            Text(
                                text = feedbackItem.feedback,
                                fontSize = MaterialTheme.typography.bodySmall.fontSize
                            )
                        }

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(Dimens.MiniPadding)
                        ) {

                            Row(
                                modifier = Modifier.clickable {
                                    if(customerReaction==true){
                                        customerReaction =null
                                        SellersInfoList.find { it.id == sellerId }!!.feedbackList.find { it==feedbackItem }!!.customerReaction=null

                                        SellersInfoList.find { it.id == sellerId }!!.feedbackList.find { it==feedbackItem }!!.numberOfLike--

                                    }else{
                                        if(customerReaction==false){
                                            SellersInfoList.find { it.id == sellerId }!!.feedbackList.find { it==feedbackItem }!!.numberOfDislike--
                                        }

                                        customerReaction =true
                                        SellersInfoList.find { it.id == sellerId }!!.feedbackList.find { it==feedbackItem }!!.customerReaction=true

                                        SellersInfoList.find { it.id == sellerId }!!.feedbackList.find { it==feedbackItem }!!.numberOfLike++
                                    }
                                }
                            ){
                                Icon(
                                    imageVector = ImageVector.vectorResource(
                                        if(customerReaction==true){
                                            R.drawable.ic_filled_thumbs_up
                                        }else{
                                            R.drawable.ic_outlined_thumbs_up
                                        }
                                    ),
                                    contentDescription = null,
                                    modifier = Modifier.padding(horizontal = Dimens.MiniPadding)
                                        .size(Dimens.IconSizeSmall)
                                )

                                Text(
                                    text = "Like",
                                    color = Color.Gray,
                                    fontSize = MaterialTheme.typography.bodySmall.fontSize,
                                    fontFamily = FontFamily.Default,
                                    modifier = Modifier.padding(end = Dimens.MiniPadding)
                                )

                                Text(
                                    text = feedbackItem.numberOfLike.toString(),
                                    fontSize = MaterialTheme.typography.bodySmall.fontSize,
                                    modifier = Modifier.padding(end = Dimens.MediumPadding),
                                    fontFamily = FontFamily.Default,
                                )
                            }
                            Row(
                                modifier = Modifier.clickable {

                                    if(customerReaction==false){
                                        customerReaction =null
                                        SellersInfoList.find { it.id == sellerId }!!.feedbackList.find { it==feedbackItem }!!.customerReaction=null

                                        SellersInfoList.find { it.id == sellerId }!!.feedbackList.find { it==feedbackItem }!!.numberOfDislike--
                                    }else{
                                        //println(SellersInfoList.find{it.id == sellerId}!!.feedbackList)
                                        if(customerReaction==true){
                                            SellersInfoList.find { it.id == sellerId }!!.feedbackList.find { it==feedbackItem }!!.numberOfLike--
                                        }

                                        customerReaction =false
                                        SellersInfoList.find { it.id == sellerId }!!.feedbackList.find {
                                            println(it.toString()+"\t\t"+feedbackItem)
                                            it==feedbackItem
                                        }!!.customerReaction=false

                                        SellersInfoList.find { it.id == sellerId }!!.feedbackList.find { it==feedbackItem }!!.numberOfDislike++
                                    }
                                }
                            ){

                                Icon(
                                    imageVector = ImageVector.vectorResource(
                                        if(customerReaction==false){
                                            R.drawable.ic_filled_thumbs_down
                                        }else{
                                            R.drawable.ic_outlined_thumbs_down
                                        }
                                    ),
                                    contentDescription = null,
                                    modifier = Modifier.padding(horizontal = Dimens.MiniPadding)
                                        .size(Dimens.IconSizeSmall)
                                )

                                Text(
                                    text = "Dislike",
                                    color = Color.Gray,
                                    fontSize = MaterialTheme.typography.bodySmall.fontSize,
                                    modifier = Modifier.padding(end = Dimens.MiniPadding),
                                    fontFamily = FontFamily.Default,
                                )

                                Text(
                                    text = feedbackItem.numberOfDislike.toString(),
                                    fontSize = MaterialTheme.typography.bodySmall.fontSize,
                                    modifier = Modifier.padding(end = Dimens.MiniPadding),
                                    fontFamily = FontFamily.Default,
                                )
                            }

                            Box {

                                IconButton(
                                    onClick = {
                                        feedbackOptionExpanded = true
                                    },
                                    modifier = Modifier
                                        .size(Dimens.IconSizeSmall)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.MoreVert,
                                        contentDescription = null,
                                        modifier = Modifier
                                            .size(Dimens.IconSizeSmall)
                                    )
                                }

                                DropdownMenu(
                                    expanded = feedbackOptionExpanded,
                                    onDismissRequest = {feedbackOptionExpanded=false}
                                ) {
                                    DropdownMenuItem(
                                        text = {
                                            Text(
                                                text = "Report",
                                                fontSize = MaterialTheme.typography.bodySmall.fontSize,
                                                color = Color.Red.copy(0.6f)
                                            )
                                        },
                                        onClick = {
                                            val screenHint = ReportScreenHint.Feedback.hint
                                            val reportedName = feedbackItem.customerName
                                            feedbackOptionExpanded = false
                                            navController.navigate(
                                                ScreenRoute.ReportScreen.route +"/${screenHint} ${reportedName}"
                                            )
                                        },
                                        modifier = Modifier.height(Dimens.IconSizeMedium)

                                    )
                                }
                            }
                        }
                    }
                }
            }

            FeedbackTextField(
                onSend = {feedback ->
                    feedbacks.add(
                        0,
                        FeedbackDataItem(
                            customerName = "Test User",
                            customerImage = R.drawable.ic_profile_picture,
                            feedback = feedback,
                            date = getCurrentDate(),
                            numberOfLike = 0,
                            numberOfDislike = 0
                        )
                    )

                    SellersInfoList.find{it.id == sellerId}!!.feedbackList.add(0, feedbacks[0] )
                    keyboardController?.hide()
                }
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun T() {
    FeedbackScreen(
        navController = rememberNavController(),
        sellerId = ""
    )

    FeedbackDataItem(
        id="cfebb321-13a6-4de4-9af1-28386e7a68c4",
        customerName="Test User",
        customerImage=2130968641,
        feedback="hello",
        date="02/04/2025",
        numberOfLike=0,
        numberOfDislike=0,
        customerReaction=null
    )

    FeedbackDataItem(
        id="6e1f6290-39f3-4e12-a499-2c14709db2b7",
        customerName="Test User",
        customerImage=2130968641,
        feedback="hello",
        date="02/04/2025",
        numberOfLike=0,
        numberOfDislike=0,
        customerReaction=null)
}

