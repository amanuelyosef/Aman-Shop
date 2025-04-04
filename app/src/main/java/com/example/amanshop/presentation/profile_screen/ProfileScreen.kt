package com.example.amanshop.presentation.profile_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.amanshop.R
import com.example.amanshop.presentation.components.BottomNavigationBar
import com.example.amanshop.presentation.components.ProfileInformation
import com.example.amanshop.presentation.profile_screen.components.profileSettingListData
import com.example.amanshop.presentation.ui.theme.Dimens
import com.example.amanshop.presentation.ui.theme.LocalString
import com.example.amanshop.presentation.ui.theme.homeHeadlineTitle
import com.example.amanshop.presentation.ui.theme.nunitoFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    navController: NavController
){
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()



    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Profile",
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
                scrollBehavior = scrollBehavior,
                colors = TopAppBarDefaults.topAppBarColors(
                    scrolledContainerColor = Color.White
                )
            )
        },
        bottomBar = {
            BottomNavigationBar(
                navController = navController,
                selected = 4
            )
        }
    ) {
        Column(
            modifier = Modifier.padding(it)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
                    .height(Dimens.ProfileInfoDisplayHeight),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(ProfileInformation.profileImg ?: R.drawable.ic_profile_picture),
                    contentDescription = null,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize()
                )

                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.weight(2f)
                        .padding(horizontal = Dimens.MediumPadding)
                ) {
                    Text(
                        text = ProfileInformation.Name,
                        fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "Location: "+ProfileInformation.Location,
                        color = Color.Gray,
                        fontSize = MaterialTheme.typography.bodySmall.fontSize
                    )
                }
            }

            LazyColumn {

                items(profileSettingListData) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(horizontal = Dimens.MediumPadding, vertical = Dimens.MiniPadding)
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(Dimens.MediumRoundRadius))
                            .background(Color.LightGray.copy(0.7f))
                            .clickable {  }
                            .padding(start = Dimens.MiniPadding)
                            .padding(Dimens.SmallPadding)
                    ) {

                        Text(
                            text = it.title,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = MaterialTheme.typography.bodyMedium.fontSize
                        )

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
private fun t() {
    ProfileScreen(
        navController = rememberNavController()
    )
}