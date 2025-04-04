package com.example.amanshop.presentation.login_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.amanshop.R
import com.example.amanshop.presentation.ui.theme.Dimens
import com.example.amanshop.presentation.ui.theme.customPrimaryPink
import com.example.amanshop.presentation.ui.theme.facebookBlue

@Composable
fun LoginScreen(
    navController: NavController
) {

    var emailOrPhoneNumber by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val focusRequester = FocusRequester()


    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = {
                    navController.popBackStack()
                },
                modifier = Modifier.padding(horizontal = Dimens.MediumPadding)
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_backarrow),
                    contentDescription = null
                )
            }

            Text(
                text = "Log in",
                fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                fontWeight = FontWeight.Bold,
            )
        }


        OutlinedTextField(
            value = emailOrPhoneNumber,
            onValueChange = {emailOrPhoneNumber = it},
            label = {
                Text(
                    text = "Email or Phone number",
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = Dimens.SmallPadding, horizontal = Dimens.LargePadding)
            ,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(
                onNext = {
                    focusRequester.requestFocus()
                }
            ),
            textStyle = TextStyle(fontSize = MaterialTheme.typography.bodyMedium.fontSize),
            singleLine = true
        )

        OutlinedTextField(
            value = password,
            onValueChange = {password = it},
            label = {
                Text(
                    text = "Password",
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = Dimens.SmallPadding, horizontal = Dimens.LargePadding)
                .focusRequester(focusRequester)
            //.focusRequester(focusRequester)
            ,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = {
                    navController.popBackStack()
                }
            ),
            singleLine = true,
            textStyle = TextStyle(fontSize = MaterialTheme.typography.bodyMedium.fontSize)
        )
        Row(
            modifier =  Modifier
                .padding(Dimens.SmallPadding)
                .fillMaxWidth()
                .clip(CircleShape)
                .background(customPrimaryPink)
                .clickable {
                    navController.popBackStack()
                }
                .padding(vertical = Dimens.MediumPadding),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Log in",
                fontWeight = FontWeight.Bold,
                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                color = Color.White
            )
        }

        Row(
            modifier =  Modifier
                .fillMaxWidth()
                .clip(CircleShape)
                .padding(vertical = Dimens.MediumPadding),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Forgot password?",
                fontWeight = FontWeight.SemiBold,
                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                color = customPrimaryPink
            )
        }


        Row(
            modifier =  Modifier
                .padding(horizontal = Dimens.SmallPadding)
                .padding(top = Dimens.LargePadding, bottom = Dimens.SmallPadding)
                .fillMaxWidth()
                .clip(CircleShape)
                .background(Color.LightGray.copy(0.8f))
                .padding(vertical = Dimens.MediumPadding)
            ,
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                imageVector = ImageVector.vectorResource(R.drawable.ic_google_logo),
                contentDescription = null,
                modifier = Modifier
                    .padding(horizontal = Dimens.MiniPadding)
                    .size(Dimens.IconSizeSmall)
            )
            Text(
                text = "Continue with Google",
                fontWeight = FontWeight.SemiBold,
                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
            )
        }

        Row(
            modifier =  Modifier
                .padding(Dimens.SmallPadding)
                .fillMaxWidth()
                .clip(CircleShape)
                .background(facebookBlue)
                .padding(vertical = Dimens.MediumPadding)
            ,
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_facebook_logo),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .padding(horizontal = Dimens.MiniPadding)
                    .size(Dimens.IconSizeSmall)
            )
            Text(
                text = "Continue with Facebook",
                fontWeight = FontWeight.SemiBold,
                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                color = Color.White
            )
        }

        Text(
            text = "By continuing you agree to the Terms and Conditions and  privacy policy",
            color = Color.Gray,
            fontSize = MaterialTheme.typography.bodySmall.fontSize,
            fontFamily = FontFamily.Default,
            modifier = Modifier
                .padding(horizontal = Dimens.LargePadding)
        )

    }

}

@Preview(showBackground = true)
@Composable
private fun T() {
    LoginScreen(rememberNavController())
}