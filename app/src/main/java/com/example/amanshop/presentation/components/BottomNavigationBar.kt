package com.example.amanshop.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.amanshop.presentation.message_list_screen.components.messageListData
import com.example.amanshop.presentation.ui.theme.Dimens
import com.example.amanshop.presentation.ui.theme.customOnPrimaryContainerPink
import com.example.amanshop.presentation.ui.theme.customPrimaryContainerPink
import com.example.amanshop.presentation.ui.theme.nunitoFontFamily

@Composable
fun BottomNavigationBar(
    navController: NavController,
    selected : Int = 0
) {
    BottomNavigationBarListData[3].badgeNumber = messageListData.count {
        it.numberOfUnseen>0
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .height(Dimens.BottomNavigationBarHeight),
        verticalAlignment = Alignment.CenterVertically
    ) {
        BottomNavigationBarListData.forEachIndexed { index, item ->
            if(index==2){
                Button(
                    onClick ={
                        //navController.navigate(item.route)
                    },
                    colors = ButtonColors(
                        containerColor = customPrimaryContainerPink,
                        contentColor = customOnPrimaryContainerPink,
                        disabledContainerColor = Color.Unspecified,
                        disabledContentColor = Color.Unspecified
                    ),
                    modifier = Modifier.wrapContentSize()
                ) {
                    Text(
                        text=item.title,
                        fontWeight = FontWeight.Bold,
                        fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                        fontFamily = nunitoFontFamily
                    )
                }
            }
            else{
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .clickable {
                            //println("This is backstack list  ${navController.currentBackStack.value}")
                            navController.navigate(item.route) {
                                popUpTo(item.route) { inclusive = true }
                            }
                        }
                        .padding(vertical = Dimens.MiniPadding),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    BadgedBox(
                        badge = {
                            if(item.badgeNumber>0){
                                Badge(
                                    containerColor = Color.Red
                                ){
                                    Text(
                                        text = item.badgeNumber.toString(),
                                        color = Color.White,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = MaterialTheme.typography.labelSmall.fontSize,
                                    )
                                }
                            }
                        }
                    ) {

                        Icon(
                            imageVector = ImageVector.vectorResource(
                                if (selected == index) item.selectedIcon else item.unselectedIcon
                            ),
                            contentDescription = item.contentDescription,
                            tint = if (selected == index) customOnPrimaryContainerPink
                            else Color.Black,
                            modifier = Modifier
                                .size(Dimens.IconSizeMedium)
                                .padding(bottom = Dimens.MiniPadding.minus(Dimens.OneDp))
                        )
                    }

                    Text(
                        text = item.title,
                        color = if (selected == index) customOnPrimaryContainerPink else Color.Black,
                        fontSize = MaterialTheme.typography.labelMedium.fontSize
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Bottom(modifier: Modifier = Modifier) {
    BottomNavigationBar(navController = rememberNavController())
}
