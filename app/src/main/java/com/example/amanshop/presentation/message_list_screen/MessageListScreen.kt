package com.example.amanshop.presentation.message_list_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.amanshop.R
import com.example.amanshop.presentation.chat_screen.components.ConversationsDataList
import com.example.amanshop.presentation.components.BottomNavigationBar
import com.example.amanshop.presentation.components.ScreenRoute
import com.example.amanshop.presentation.components.SearchBarComponent
import com.example.amanshop.presentation.message_list_screen.components.messageListData
import com.example.amanshop.presentation.ui.theme.Dimens
import com.example.amanshop.presentation.ui.theme.customOnPrimaryContainerPink
import com.example.amanshop.presentation.ui.theme.customPrimaryPink
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MessageListScreen(
    navController : NavController
) {
    var searchedMessage by remember { mutableStateOf("") }
    val msgList = remember { mutableStateListOf(*messageListData.toTypedArray()) }

    var isRefreshing by remember{ mutableStateOf(false) }
    val pullToRefreshState = rememberPullToRefreshState()
    val coroutineScope = rememberCoroutineScope()
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(Unit) {
        messageListData.forEachIndexed { index, item ->
            if(item.numberOfUnseen!=0){
                messageListData[index].numberOfUnseen = ConversationsDataList.find { it.productId==item.productId }!!.chat.count{!it.seen && it.seller}
            }
        }
    }
    LaunchedEffect(searchedMessage) {
        println("Here is got in")
        msgList.clear()
        ConversationsDataList.forEachIndexed { index, item ->
            if(searchedMessage in item.sellerInfo.name){
                msgList.add(messageListData.find { it.productId==item.productId }!!)
                return@forEachIndexed
            }

            if(searchedMessage in item.productTitle){
                msgList.add(messageListData.find { it.productId==item.productId }!!)
                return@forEachIndexed
            }

            item.chat.forEach {
                if(it.message!=null && searchedMessage in it.message){
                    msgList.add(messageListData.find { it.productId==item.productId }!!)
                    return@forEachIndexed
                }
            }
        }
    }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                navController = navController,
                selected = 3
            )
        }
    ) {
        Column(
            modifier = Modifier.padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,

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
                        .padding(end = Dimens.MediumPadding),
                    enabled = true,
                    navController = navController,
                    searchText = searchedMessage,
                    label = "Search Messages",
                    onSearchNavigate = {keyboardController?.hide() },
                    onValueChange = { searchedMessage = it },
                    onClearClicked = { searchedMessage = "" }
                )
            }

            if (msgList.isEmpty()){
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.weight(1f)
                ) {
                    Image(
                        painter = painterResource(R.drawable.ic_no_message),
                        contentDescription = null,
                        modifier = Modifier
                            .size(Dimens.NoMessageIconSize)
                    )

                    Text(
                        text = "No Message Yet",
                        fontSize = MaterialTheme.typography.displaySmall.fontSize,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(vertical = Dimens.MediumPadding),

                    )

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(Dimens.LargePadding))
                            .clickable { navController.navigate(ScreenRoute.CategoryScreen.route) }
                            .background(customPrimaryPink)
                            .padding(Dimens.MediumPadding)
                            .padding(horizontal = Dimens.SmallPadding)
                    ) {
                        Text(
                            text = "Explore Categories",
                            fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }

            }
            else{
                Spacer(
                    modifier = Modifier
                        .height(Dimens.OneDp)
                        .background(Color.LightGray)
                        .fillMaxWidth()
                )

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
                    state = pullToRefreshState
                ) {
                    LazyColumn(
                        modifier = Modifier
                            .background(Color.LightGray.copy(0.6f))
                    ) {
                        items(items = msgList) { item ->
                            var dropdownMenuExpanded by remember { mutableStateOf(false) }

                            Column {

                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(Dimens.MessageListItemHeight)
                                        .background(Color.White)
                                        .pointerInput(Unit) {
                                            detectTapGestures(
                                                onTap = { offset ->
                                                    navController.navigate(ScreenRoute.ChatScreen.route + "/${item.productId}")
                                                },
                                                onLongPress = {
                                                    dropdownMenuExpanded = true
                                                }
                                            )
                                        }
                                        .padding(Dimens.MiniPadding)
                                ) {

                                    Image(
                                        painter = painterResource(item.productImg),
                                        contentDescription = null,
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier.size(Dimens.MessageItemImageSize)
                                            .padding(end = Dimens.SmallPadding)
                                    )

                                    Column(
                                        modifier = Modifier.weight(1f)
                                    ) {
                                        Text(
                                            text = item.sellerName,
                                            fontWeight = if (item.numberOfUnseen != 0) FontWeight.Bold else FontWeight.Normal,
                                            fontSize = MaterialTheme.typography.bodySmall.fontSize,
                                            color = Color.Gray
                                        )

                                        Text(
                                            text = item.productTitle,
                                            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                                            fontWeight = if (item.numberOfUnseen != 0) FontWeight.Bold else FontWeight.Normal,
                                            overflow = TextOverflow.Ellipsis,
                                            maxLines = 1
                                        )

                                        Text(
                                            text = item.latestMessage ?: "audio message",
                                            fontWeight = if (item.numberOfUnseen != 0) FontWeight.Bold else FontWeight.Normal,
                                            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                                            overflow = TextOverflow.Ellipsis,
                                            maxLines = 1,
                                            color = if (item.numberOfUnseen != 0) Color.Black else Color.DarkGray,
                                            modifier = Modifier.weight(1f)
                                                .padding(
                                                    top = Dimens.SmallPadding,
                                                    bottom = Dimens.MiniPadding
                                                )
                                        )
                                    }

                                    Column(
                                        verticalArrangement = Arrangement.Center,
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Text(
                                            text = item.latestMessageDate ?: "",
                                            fontSize = MaterialTheme.typography.bodySmall.fontSize
                                        )

                                        Box(
                                            modifier = Modifier.weight(1f),
                                            contentAlignment = Alignment.Center
                                        ) {

                                            if (item.numberOfUnseen != 0) {
                                                Box(
                                                    modifier = Modifier
                                                        .clip(CircleShape)
                                                        .background(customOnPrimaryContainerPink)
                                                        .size(Dimens.IconSizeSmall),
                                                    contentAlignment = Alignment.Center
                                                ) {
                                                    Text(
                                                        text = item.numberOfUnseen.toString(),
                                                        color = Color.White,
                                                        fontSize = MaterialTheme.typography.bodySmall.fontSize,
                                                        fontWeight = FontWeight.SemiBold
                                                    )
                                                }
                                            }
                                        }
                                    }
                                }

                                Spacer(
                                    modifier = Modifier
                                        .height(Dimens.OneDp)
                                        .background(Color.LightGray)
                                        .fillMaxWidth()
                                )

                                Box(
                                    modifier = Modifier.padding(horizontal = Dimens.LargePadding)
                                ) {

                                    DropdownMenu(
                                        expanded = dropdownMenuExpanded,
                                        onDismissRequest = { dropdownMenuExpanded = false },
                                        modifier = Modifier.padding(horizontal = Dimens.MediumPadding)
                                    ) {
                                        DropdownMenuItem(
                                            text = {
                                                Text(
                                                    text = "Delete chat",
                                                    color = Color.Red
                                                )
                                            },
                                            leadingIcon = {
                                                Icon(
                                                    imageVector = Icons.Outlined.Delete,
                                                    contentDescription = null,
                                                    tint = Color.Red
                                                )
                                            },
                                            onClick = {
                                                dropdownMenuExpanded = false
                                                msgList.remove(item)
                                                messageListData.remove(item)
                                                ConversationsDataList.remove(ConversationsDataList.find { it.productId == item.productId })
                                            },
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun T() {
    MessageListScreen(
        navController = rememberNavController()
    )
}