package com.example.amanshop.presentation.notification_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.amanshop.R
import com.example.amanshop.presentation.chat_screen.components.ChatScreenData
import com.example.amanshop.presentation.chat_screen.components.ConversationsDataList
import com.example.amanshop.presentation.components.BottomNavigationBar
import com.example.amanshop.presentation.components.ScreenRoute
import com.example.amanshop.presentation.message_list_screen.components.MessageListItem
import com.example.amanshop.presentation.message_list_screen.components.messageListData
import com.example.amanshop.presentation.notification_screen.components.NotificationListItem
import com.example.amanshop.presentation.notification_screen.components.notificationList
import com.example.amanshop.presentation.ui.theme.Dimens
import com.example.amanshop.presentation.ui.theme.LocalString
import com.example.amanshop.presentation.ui.theme.homeHeadlineTitle
import com.example.amanshop.presentation.ui.theme.nunitoFontFamily
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationScreen(
    navController : NavController
) {
    LaunchedEffect(Unit) {
        delay(2500)
        notificationList.forEach {
            it.seen = true
        }
    }

    var isRefreshing by remember{ mutableStateOf(false) }
    val pullToRefreshState = rememberPullToRefreshState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Notification",
                        fontSize = MaterialTheme.typography.headlineLarge.fontSize,
                        fontWeight = FontWeight.Bold,
                        color = homeHeadlineTitle,
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
                            contentDescription = LocalString.notificationIcCd,
                            modifier = Modifier
                                .size(Dimens.IconSizeMedium),
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    scrolledContainerColor = Color.White
                )
            )
        },
        bottomBar = {
            BottomNavigationBar(
                navController = navController,
                selected = 5
            )
        }
    ) { scaffoldPadding ->

        PullToRefreshBox(
            isRefreshing = isRefreshing,
            onRefresh = {
                isRefreshing = true
                coroutineScope.launch {
                    isRefreshing = true
                    delay(5000L)
                    isRefreshing = false
                }
            },
            indicator = {
                Indicator(
                    modifier = Modifier.align(Alignment.TopCenter),
                    isRefreshing = isRefreshing,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    state = pullToRefreshState,
                )
            },
            state = pullToRefreshState,
            modifier = Modifier.padding(scaffoldPadding)
        ) {
            LazyColumn {
                items(notificationList) { item ->
                    NotificationListItem(item, navController)
                }
            }
        }
    }
}

@Composable
private fun NotificationListItem(
    item: NotificationListItem,
    navController: NavController
) {
    Row(
        modifier = Modifier
            .background(Color.LightGray)
            .padding(vertical = Dimens.OneDp.times(0.5f))
            .background(Color.White)
            .background(
                if (!item.seen) { Color.LightGray.copy(0.8f) }
                else if(!item.checked){Color.LightGray.copy(0.3f) }
                else Color.White
            )
            .clickable {
                item.checked = true

                val check = ConversationsDataList.find {it.productId == item.productId }

                if(check==null) {
                    ConversationsDataList.add(0,
                        ChatScreenData(
                            productId = item.productId,
                            chat = mutableListOf()
                        )
                    )

                    messageListData.add(0,
                        MessageListItem(
                            productId = item.productId
                        )
                    )
                }
                navController.navigate(ScreenRoute.ChatScreen.route+"/${item.productId}")
                //navController.navigate(item.navigationRoute)
            }
            .padding(Dimens.MiniPadding)
            .height(Dimens.NotificationListItemHeight),
    ) {
        Image(
            painter = painterResource(item.productImg),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(end = Dimens.SmallPadding)
                .size(Dimens.MessageItemImageSize)
                .clip(RoundedCornerShape(Dimens.SmallRoundRadius))
                .align(Alignment.CenterVertically)
        )

        Column {
            Text(
                text = item.date,
                fontSize = MaterialTheme.typography.bodySmall.fontSize,
                color = Color.Gray,
                modifier = Modifier.align(Alignment.End)
            )

            Text(
                text = item.notification,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                fontWeight = if (item.checked) FontWeight.Normal else FontWeight.SemiBold,
                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
            )

            Text(
                text = item.timeStamp,
                color = Color.Gray,
                fontSize = MaterialTheme.typography.bodySmall.fontSize,
                modifier = Modifier.padding(top = Dimens.SmallPadding)
            )
        }
    }
}

@Preview
@Composable
private fun T() {
    NotificationScreen(
        navController = rememberNavController()
    )
}