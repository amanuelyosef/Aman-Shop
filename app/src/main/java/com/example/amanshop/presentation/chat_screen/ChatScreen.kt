package com.example.amanshop.presentation.chat_screen

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
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
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat.shouldShowRequestPermissionRationale
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.amanshop.R
import com.example.amanshop.domain.playback.AndroidAudioPlayer
import com.example.amanshop.domain.playback.formatDuration
import com.example.amanshop.domain.playback.getAudioDuration
import com.example.amanshop.presentation.chat_screen.components.ChatDataItem
import com.example.amanshop.presentation.chat_screen.components.ChatTextField
import com.example.amanshop.presentation.chat_screen.components.ConversationsDataList
import com.example.amanshop.presentation.chat_screen.components.ImageviewComponent
import com.example.amanshop.presentation.chat_screen.components.RecordingDisplayComponent
import com.example.amanshop.presentation.components.PermissionRationaleDialog
import com.example.amanshop.presentation.components.RecordAudioPermissionTextProvider
import com.example.amanshop.presentation.components.ReportScreenHint
import com.example.amanshop.presentation.components.ScreenRoute
import com.example.amanshop.presentation.components.getCurrentDate
import com.example.amanshop.presentation.components.monthNumberToName
import com.example.amanshop.presentation.components.openDialer
import com.example.amanshop.presentation.message_list_screen.components.messageListData
import com.example.amanshop.presentation.ui.theme.Dimens
import com.example.amanshop.presentation.ui.theme.LocalString
import com.example.amanshop.presentation.ui.theme.customOnPrimaryContainerPink
import com.example.amanshop.presentation.ui.theme.customPrimaryContainerPink
import com.example.amanshop.presentation.ui.theme.customPrimaryPink
import com.example.amanshop.presentation.ui.theme.homeHeadlineTitle
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun ChatScreen(
    navController : NavController,
    productId : String
) {
    val viewModel = viewModel<ChatScreenViewModel>()

    val screenData = ConversationsDataList.find { it.productId == productId }!!
    val chatList = remember { mutableStateListOf(*(screenData.chat).toTypedArray()) }
    var optionExpanded by remember { mutableStateOf(false) }
    val scrollState = rememberLazyListState()
    val localContext = LocalContext.current
    var recordingSignVisible by remember { mutableStateOf(false) }
    val audioPlayer = remember { AndroidAudioPlayer(localContext,) }
    val scope = rememberCoroutineScope()
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val clipboardManager = LocalClipboardManager.current
    val photoPicker = rememberLauncherForActivityResult( contract = ActivityResultContracts.PickVisualMedia()  ) {imageUri ->
        if(imageUri!=null){
            chatList.add(0,
                ChatDataItem(
                    type = "image",
                    seller = false,
                    uri = imageUri,
                    timestamp = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date()),
                    date = "13/02/2025"
                )
            )
        }

        scope.launch {
            scrollState.scrollToItem(0)
        }

        ConversationsDataList.find { it.productId == productId }!!.chat.add(0,
            ChatDataItem(
                type = "image",
                seller = false,
                uri = imageUri,
                timestamp = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date()),
                date = "13/02/2025"
            )
        )
    }

    val imageList = chatList.mapNotNull{it.uri}.reversed()
    var initialImageIndex by remember { mutableIntStateOf(0) }
    var isImageViewVisible by remember { mutableStateOf(false) }
    val dialogQueue = viewModel.visiblePermissionDialogQueue
    val launchPermission = remember { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current

    BackHandler {
        if(isImageViewVisible){
            isImageViewVisible = false
        }else{
            navController.popBackStack()
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Column(
            modifier = Modifier.fillMaxSize()
        ){
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .height(Dimens.ChatTopNameDisplayHeight)
                    .background(customPrimaryContainerPink.copy(0.5f))
            )  {
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

                Image(
                    painter = painterResource(screenData.sellerInfo.image),
                    contentDescription = null,
                    modifier = Modifier
                        .clip(CircleShape)
                        .clickable {
                            navController.navigate(ScreenRoute.SellerShopScreen.route+"/${screenData.sellerInfo.id}")
                        }
                        .size(Dimens.IconSizeLarge)
                )

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .clickable(indication = null, interactionSource = null) {
                            navController.navigate(ScreenRoute.SellerShopScreen.route+"/${screenData.sellerInfo.id}")
                        }
                        .padding(horizontal = Dimens.SmallPadding),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = screenData.sellerInfo.name,
                        fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                        fontWeight = FontWeight.Bold,
                        color = homeHeadlineTitle,
                        fontFamily = FontFamily.Default,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                    Text(
                        text = "Last seen: "+screenData.sellerInfo.lastSeen,
                        fontSize = MaterialTheme.typography.labelMedium.fontSize,
                        fontFamily = FontFamily.Default,
                        color = Color.Gray
                    )
                }

                IconButton(
                    onClick = {
                        openDialer(localContext,screenData.sellerInfo.phoneNumber)
                    }
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Call,
                        contentDescription = LocalString.notificationIcCd,
                        modifier = Modifier
                            .size(Dimens.IconSizeMedium),
                    )
                }
                Box{

                    IconButton(
                        onClick = {
                            optionExpanded=true
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = null,
                            modifier = Modifier
                                .size(Dimens.IconSizeMedium),
                        )
                    }

                    DropdownMenu(
                        expanded = optionExpanded,
                        onDismissRequest = {optionExpanded=false}
                    ) {
                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = "Delete Chat",
                                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                                    fontFamily = FontFamily.Default,
                                    color = Color.Black
                                )
                            },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Outlined.Delete,
                                    contentDescription = null
                                )
                            },
                            onClick = {
                                optionExpanded=false
                                navController.popBackStack()
                                messageListData.remove(messageListData.find { it.productId==productId })
                            }
                        )

                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = "Report",
                                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                                    fontFamily = FontFamily.Default,
                                    color = Color.Red.copy(0.8f)
                                )
                            },
                            leadingIcon = {
                                Icon(
                                    imageVector = ImageVector.vectorResource(R.drawable.ic_outlined_flag),
                                    contentDescription = null,
                                    tint = Color.Red.copy(0.8f)
                                )
                            },
                            onClick = {
                                val screenHint = ReportScreenHint.Seller.hint
                                val reportedName = screenData.sellerInfo.name
                                optionExpanded=false
                                navController.navigate(
                                    ScreenRoute.ReportScreen.route+"/${screenHint} $reportedName"
                                )
                            }
                        )
                    }
                }
            }

            Spacer(
                modifier = Modifier
                    .height(Dimens.OneDp)
                    .background(Color.LightGray)
                    .fillMaxWidth()
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .background(Color.White)
                    .clickable(indication = null, interactionSource = null) {
                        navController.navigate(ScreenRoute.ProductDetailScreen.route + "/${screenData.productId}")
                    }
                    .padding(vertical = Dimens.SmallPadding)
                    .height(Dimens.TopAppBarHeight)
            )  {
                Image(
                    painter = painterResource(screenData.productImg),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(horizontal = Dimens.MiniPadding)
                        .clip(RoundedCornerShape(Dimens.SmallRoundRadius))
                )

                Column {
                    Text(
                        text = screenData.productTitle,
                        fontSize = MaterialTheme.typography.bodySmall.fontSize,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        modifier = Modifier.padding(bottom = Dimens.MiniPadding)
                    )

                    Text(
                        text = "ETB "+screenData.productPrice,
                        fontSize = MaterialTheme.typography.bodySmall.fontSize,
                        fontWeight = FontWeight.Bold
                    )

                }
            }

            Spacer(
                modifier = Modifier
                    .height(Dimens.OneDp)
                    .background(Color.LightGray)
                    .fillMaxWidth()
            )

            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .background(Color.LightGray.copy(0.4f)),
                verticalArrangement = Arrangement.Bottom,
                state = scrollState,
                reverseLayout = true
            ) {

                itemsIndexed(items = chatList, key = {_, item -> item.id}) { index, item ->
                    val splitDate = item.date.split("/")
                    var prevMonth by remember { mutableStateOf("") }
                    var prevDay by remember { mutableStateOf("") }
                    var dropDownExpanded by remember{ mutableStateOf(false) }

                    if(!item.seen){
                        ConversationsDataList.find{it.productId == productId}!!.chat[index].seen = true
                        println("${index}  ${ConversationsDataList.find{it.productId == productId}!!.chat.map { it.seen }}")
                        //messageListData.find { it.conversationIndex == conversationsDataIndex }?.numberOfUnseen = ConversationsDataList[conversationsDataIndex].chat.count{!it.seen && it.seller}
                    }

                    if (index < chatList.size-1) {
                        val nextSplitDate = chatList[index + 1].date.split("/")
                        prevMonth = nextSplitDate[1]
                        prevDay = nextSplitDate[0]
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {

                        if (splitDate[0] != prevDay || splitDate[1] != prevMonth) {
                            val month = monthNumberToName(splitDate[1].toInt())
                            Text(
                                text = "${month} ${splitDate[0]} ",
                                fontSize = MaterialTheme.typography.bodySmall.fontSize,
                                color = Color.Black,
                                modifier = Modifier
                                    .align(Alignment.CenterHorizontally)
                            )
                        }

                        if (item.seller) {
                            Column(
                                modifier = Modifier
                                    .align(Alignment.Start)
                                    .padding(
                                        horizontal = Dimens.MiniPadding,
                                        vertical = Dimens.SmallPadding
                                    )
                                    .clip(RoundedCornerShape(Dimens.MediumRoundRadius))
                                    .background(Color.White)
                                    .widthIn(max = screenWidth * 0.75f)
                                    .wrapContentWidth()
                                    .pointerInput(Unit) {
                                        detectTapGestures(
                                            onLongPress = {
                                                dropDownExpanded = true
                                            }
                                        )
                                    }
                                    .padding(Dimens.MiniPadding)
                            ) {
                                if(item.type == "text"){
                                    Text(
                                        text = item.message!!,
                                        fontSize = MaterialTheme.typography.bodySmall.fontSize
                                    )
                                }
                                else if(item.type == "audio"){
                                    val totalTimeMillis by remember{ mutableLongStateOf(getAudioDuration(item.file)) }
                                    var isRunning by remember { mutableStateOf(false) }
                                    var elapsedTime by remember { mutableLongStateOf(0L) }

                                    val progress = animateFloatAsState(
                                        targetValue =  if(totalTimeMillis==0L) 0f else elapsedTime.toFloat() / totalTimeMillis,
                                        animationSpec = tween(durationMillis = 250, easing = LinearEasing),
                                        label = "timerProgress"
                                    )

                                    //TODO: Why do the launched effect be executed for every item since it created for each item
                                    LaunchedEffect(isRunning) {
                                        if(isRunning && (elapsedTime==totalTimeMillis || elapsedTime == 0L)){
                                            elapsedTime = 0L
                                            audioPlayer.playFile(item.file ?: return@LaunchedEffect){isRunning = false}
                                        }
                                        else if(isRunning && elapsedTime<totalTimeMillis){
                                            audioPlayer.resume()
                                        }
                                        else if(!isRunning && elapsedTime<totalTimeMillis){
                                            audioPlayer.pause()
                                        }

                                        while (isRunning){
                                            delay(300)
                                            elapsedTime+=300
                                            if(elapsedTime > totalTimeMillis) {
                                                elapsedTime = totalTimeMillis
                                                isRunning = false;
                                            }
                                        }
                                    }

                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .clip(RoundedCornerShape(Dimens.MediumRoundRadius))
                                        ,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {

                                        IconButton(
                                            onClick = {
                                                isRunning = !isRunning
                                            },
                                            modifier = Modifier
                                                .clip(CircleShape)
                                                .background(customPrimaryPink)
                                        ) {
                                            Icon(
                                                imageVector = if(isRunning) ImageVector.vectorResource(R.drawable.ic_sound_pause)
                                                                else ImageVector.vectorResource(R.drawable.ic_sound_play),
                                                contentDescription = null,
                                                tint = Color.White
                                            )
                                        }

                                        Column(
                                            verticalArrangement = Arrangement.Center,
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(horizontal = Dimens.MediumPadding)
                                        ) {
                                            Box(
                                                modifier = Modifier
                                                    .height(Dimens.MiniPadding)
                                                    .fillMaxWidth()
                                                    .clip(CircleShape)
                                                    .background(Color.LightGray)
                                            ){
                                                Box(
                                                    modifier = Modifier
                                                        .height(Dimens.MiniPadding)
                                                        .fillMaxWidth(progress.value)
                                                        .clip(CircleShape)
                                                        .background(customOnPrimaryContainerPink)
                                                )
                                            }

                                            Text(
                                                text = "${formatDuration(elapsedTime)}/${formatDuration(totalTimeMillis)}",
                                                fontSize = MaterialTheme.typography.bodySmall.fontSize,
                                                color = Color.Gray
                                            )
                                        }
                                    }
                                }
                                else if(item.type == "image"){
                                    AsyncImage(
                                        modifier = Modifier
                                            .fillMaxWidth(0.75f)
                                            .heightIn(min = Dimens.ChatMaximumImageHeight,max = Dimens.ProductDetailImageHeight)
                                            .clip(RoundedCornerShape(Dimens.SmallRoundRadius))
                                            .clickable {
                                                initialImageIndex = imageList.indexOf(item.uri)
                                                isImageViewVisible = true
                                            }
                                        ,
                                        model = item.uri,
                                        contentDescription = null,
                                        contentScale = ContentScale.FillWidth
                                    )
                                }

                                Box(
                                    modifier = Modifier.padding(horizontal = Dimens.SmallPadding)
                                        .align(Alignment.End)
                                ){
                                    DropdownMenu(
                                        expanded = dropDownExpanded,
                                        onDismissRequest = { dropDownExpanded = false },
                                        modifier = Modifier.padding(horizontal = Dimens.SmallPadding)
                                    ) {
                                        if(item.type=="text"){
                                            DropdownMenuItem(
                                                text = {
                                                    Text(
                                                        text="Copy",
                                                        fontSize = MaterialTheme.typography.bodyMedium.fontSize
                                                    )
                                                },
                                                onClick = {
                                                    clipboardManager.setText(AnnotatedString(item.message!!))
                                                    dropDownExpanded = false
                                                }
                                            )
                                        }
                                    }
                                }

                                Row(
                                    modifier = Modifier
                                        .align(Alignment.End)
                                        .padding(horizontal = Dimens.MiniPadding)
                                        .padding(top = Dimens.MiniPadding)
                                ) {

                                    Text(
                                        text = item.timestamp,
                                        fontSize = MaterialTheme.typography.bodySmall.fontSize,
                                        color = Color.Gray,
                                    )
                                }
                            }
                        }
                        else {
                            Column(
                                modifier = Modifier
                                    .align(Alignment.End)
                                    .padding(
                                        horizontal = Dimens.MiniPadding,
                                        vertical = Dimens.SmallPadding
                                    )
                                    .clip(RoundedCornerShape(Dimens.MediumRoundRadius))
                                    .background(customOnPrimaryContainerPink.copy(0.2f))
                                    .widthIn(max = screenWidth * 0.75f)
                                    .wrapContentWidth()
                                    .pointerInput(Unit) {
                                        detectTapGestures(
                                            onLongPress = {
                                                dropDownExpanded = true
                                            }
                                        )
                                    }
                                    .padding(Dimens.MiniPadding)
                            ) {

                                if(item.type == "text"){
                                    Text(
                                        text = item.message!!,
                                        fontSize = MaterialTheme.typography.bodySmall.fontSize,
                                        modifier = Modifier
                                            .padding(horizontal = Dimens.MiniPadding)
                                    )
                                }

                                else if(item.type == "audio"){
                                    val totalTimeMillis by remember{ mutableLongStateOf(getAudioDuration(item.file)) }
                                    var isRunning by remember { mutableStateOf(false) }
                                    var elapsedTime by remember { mutableLongStateOf(0L) }

                                    val progress = animateFloatAsState(
                                        targetValue =  if(totalTimeMillis==0L) 0f else elapsedTime.toFloat() / totalTimeMillis,
                                        animationSpec = tween(durationMillis = 250, easing = LinearEasing),
                                        label = "timerProgress"
                                    )

                                    LaunchedEffect(isRunning) {
                                        if(isRunning && (elapsedTime==totalTimeMillis || elapsedTime == 0L)){
                                            elapsedTime = 0L
                                            audioPlayer.playFile(item.file ?: return@LaunchedEffect){isRunning = false}
                                        }
                                        else if(isRunning && elapsedTime<totalTimeMillis){
                                            audioPlayer.resume()
                                        }
                                        else if(!isRunning && elapsedTime<totalTimeMillis){
                                            audioPlayer.pause()
                                        }

                                        while (isRunning){
                                            delay(300)
                                            elapsedTime+=300
                                            if(elapsedTime > totalTimeMillis) {
                                                elapsedTime = totalTimeMillis
                                                isRunning = false;
                                            }
                                        }
                                    }

                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .clip(RoundedCornerShape(Dimens.MediumRoundRadius))
                                        ,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {

                                        IconButton(
                                            onClick = {
                                                isRunning = !isRunning
                                            },
                                            modifier = Modifier
                                                .clip(CircleShape)
                                                .background(customPrimaryPink)
                                        ) {
                                            Icon(
                                                imageVector = if(isRunning) ImageVector.vectorResource(R.drawable.ic_sound_pause) else ImageVector.vectorResource(R.drawable.ic_sound_play),
                                                contentDescription = null,
                                                tint = Color.White
                                            )
                                        }

                                        Column(
                                            verticalArrangement = Arrangement.Center,
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(horizontal = Dimens.MediumPadding)
                                        ) {
                                            Box(
                                                modifier = Modifier
                                                    .height(Dimens.MiniPadding)
                                                    .fillMaxWidth()
                                                    .clip(CircleShape)
                                                    .background(Color.LightGray)
                                            ){
                                                Box(
                                                    modifier = Modifier
                                                        .height(Dimens.MiniPadding)
                                                        .fillMaxWidth(progress.value)
                                                        .clip(CircleShape)
                                                        .background(customOnPrimaryContainerPink)
                                                )
                                            }

                                            Text(
                                                text = "${formatDuration(elapsedTime)}/${formatDuration(totalTimeMillis)}",
                                                fontSize = MaterialTheme.typography.bodySmall.fontSize,
                                                color = Color.Gray
                                            )
                                        }
                                    }
                                }

                                else if(item.type == "image"){
                                    AsyncImage(
                                        modifier = Modifier
                                            .fillMaxWidth(0.65f)
                                            .heightIn(max = Dimens.ChatMaximumImageHeight)
                                            .clip(RoundedCornerShape(Dimens.SmallRoundRadius))
                                            .pointerInput(Unit) {
                                                detectTapGestures(
                                                    onTap = {
                                                        initialImageIndex = imageList.indexOf(item.uri)
                                                        isImageViewVisible = true
                                                    },
                                                    onLongPress = {
                                                        dropDownExpanded = true
                                                    }
                                                )
                                            }
                                        ,
                                        model = item.uri,
                                        contentDescription = null,
                                        contentScale = ContentScale.Crop
                                    )
                                }

                                Box(
                                    modifier = Modifier.padding(horizontal = Dimens.SmallPadding)
                                ){
                                    DropdownMenu(
                                        expanded = dropDownExpanded,
                                        onDismissRequest = { dropDownExpanded = false },
                                        modifier = Modifier.padding(horizontal = Dimens.SmallPadding)
                                    ) {
                                        if(item.type=="text"){
                                            DropdownMenuItem(
                                                text = {
                                                    Text(
                                                        text="Copy",
                                                        fontSize = MaterialTheme.typography.bodyMedium.fontSize
                                                    )
                                                },
                                                onClick = {
                                                    clipboardManager.setText(AnnotatedString(item.message!!))
                                                    dropDownExpanded = false
                                                }
                                            )
                                        }

                                        DropdownMenuItem(
                                            text = {
                                                Text(
                                                    text="Delete",
                                                    fontSize = MaterialTheme.typography.bodyMedium.fontSize
                                                )
                                            },
                                            onClick = {
                                                chatList.remove(item)
                                                ConversationsDataList.find{it.productId == productId}!!.chat.remove(item)
                                            }
                                        )
                                    }
                                }

                                Row(
                                    modifier = Modifier
                                        .align(Alignment.End)
                                        .padding(horizontal = Dimens.MiniPadding)
                                        .padding(top = Dimens.MiniPadding)
                                ) {
                                    Text(
                                        text = item.timestamp,
                                        fontSize = MaterialTheme.typography.bodySmall.fontSize,
                                        color = Color.Gray,

                                        )

                                    if (item.seen) {
                                        Icon(
                                            imageVector = ImageVector.vectorResource(R.drawable.ic_seen_checkmark),
                                            contentDescription = null,
                                            tint = Color.Gray,
                                            modifier = Modifier
                                                .size(Dimens.IconSizeSmall)
                                        )
                                    } else {
                                        Icon(
                                            imageVector = ImageVector.vectorResource(R.drawable.ic_unseen_checkmark),
                                            contentDescription = null,
                                            tint = Color.Gray,
                                            modifier = Modifier
                                                .size(Dimens.IconSizeSmall)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }


            ChatTextField(
                context = localContext,
                onRecording = {recordingSignVisible = true},
                onStopRecording = {passedAudio ->
                    if(passedAudio!=null){
                        recordingSignVisible = false
                        chatList.add(0,
                            ChatDataItem(
                                type = "audio",
                                seller = false,
                                file = passedAudio,
                                timestamp = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date()),
                                date = "13/02/2025"
                            )
                        )
                        scope.launch {
                            scrollState.scrollToItem(0)
                        }

                        ConversationsDataList.find{it.productId == productId}!!.chat.add(0,
                            ChatDataItem(
                                type = "audio",
                                seller = false,
                                file = passedAudio,
                                timestamp = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date()),
                                date = "13/02/2025"
                            )
                        )

                        println("chatList: $chatList")
                        println("conversation: $ConversationsDataList")
                    }
                    else{
                        recordingSignVisible = false
                        println(chatList)
                    }
                },
                onSendMessage = {
                    keyboardController?.hide()
                    chatList.add(0,
                        ChatDataItem(
                            seller = false,
                            message = it,
                            timestamp = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date()),
                            date = getCurrentDate()
                        )
                    )
                    scope.launch {
                        scrollState.scrollToItem(0)
                    }
                    ConversationsDataList.find{it.productId == productId}!!.chat.add(0, chatList[0])
                    messageListData.find { it.productId==screenData.productId }!!.latestMessage = it
                    keyboardController?.hide()
                },
                onImagePicker = {
                    photoPicker.launch( PickVisualMediaRequest( ActivityResultContracts.PickVisualMedia.ImageOnly ) )
                },
                launchPermission = launchPermission
            )
        }

        AnimatedVisibility(visible = recordingSignVisible,
            enter = fadeIn()+ expandIn(expandFrom = Alignment.Center) ,
            exit = shrinkOut(shrinkTowards = Alignment.Center) + fadeOut(),
            modifier = Modifier.clip(CircleShape)
        ) {
            RecordingDisplayComponent()
        }

        AnimatedVisibility(visible = isImageViewVisible,
            enter = fadeIn()+ expandIn(expandFrom = Alignment.Center) ,
            exit = shrinkOut(shrinkTowards = Alignment.Center) + fadeOut(),
        ) {
            ImageviewComponent(
                imageList = imageList,
                initialPage = initialImageIndex,
                onDismissRequest = {
                    isImageViewVisible = false
                }
            )
        }


        dialogQueue
            .reversed()
            .forEach { permission ->
                PermissionRationaleDialog(
                    permissionTextProvider = when (permission) {
                        Manifest.permission.RECORD_AUDIO -> {
                            RecordAudioPermissionTextProvider()
                        }
                        else -> return@forEach
                    },
                    isPermanentlyDeclined = !shouldShowRequestPermissionRationale(localContext as Activity, permission),
                    onDismiss = viewModel::dismissDialog,
                    onOkClick = {
                        viewModel.dismissDialog()
                        launchPermission.value = true
                    },
                    onGoToSettingClick = {
                        openAppSettings(localContext)
                        viewModel.dismissDialog()
                    }
                )
            }
    }
}


fun openAppSettings(context: Context) {
    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
    val uri = Uri.fromParts("package", context.packageName, null)
    intent.data = uri
    context.startActivity(intent)
}


@Preview(showBackground = true)
@Composable
private fun T() {
    ChatScreen(
        navController = rememberNavController(),
        productId = ""
    )
}