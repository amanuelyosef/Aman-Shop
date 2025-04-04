package com.example.amanshop.presentation.create_new_account_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.amanshop.R
import com.example.amanshop.presentation.ui.theme.Dimens
import com.example.amanshop.presentation.ui.theme.customLightGray
import com.example.amanshop.presentation.ui.theme.customPrimaryPink
import com.example.amanshop.presentation.ui.theme.facebookBlue

@Composable
fun CreateNewAccountScreen(navController : NavController) {
    var email by remember{ mutableStateOf("") }
    var phoneNumber by remember{ mutableStateOf("") }
    var firstName by remember{ mutableStateOf("") }
    var lastName by remember{ mutableStateOf("") }
    var password by remember{ mutableStateOf("") }
    val focusManager = LocalFocusManager.current

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
                text = "Create new account",
                fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Default
            )
        }

        Row(
            modifier =  Modifier
                .padding(  horizontal = Dimens.SmallPadding)
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
                modifier = Modifier.padding(horizontal = Dimens.MiniPadding)
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
                .padding( Dimens.SmallPadding)
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
                modifier = Modifier.padding(horizontal = Dimens.MiniPadding)
                    .size(Dimens.IconSizeSmall)
            )
            Text(
                text = "Continue with Facebook",
                fontWeight = FontWeight.SemiBold,
                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                color = Color.White
            )
        }

        Spacer(
            modifier = Modifier.fillMaxWidth()
                .height(Dimens.MiniPadding)
                .background(customLightGray)
        )

        LazyColumn(
            modifier = Modifier.padding( horizontal = Dimens.LargePadding)
        ){
            item {

                OutlinedTextField(
                    value = email,
                    onValueChange = {email = it},
                    label = {
                        Text(
                            text = "Email*",
                            fontSize = MaterialTheme.typography.bodyMedium.fontSize
                        )
                    },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                        .padding(vertical = Dimens.SmallPadding),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                    keyboardActions = KeyboardActions(
                        onNext = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }
                    ),
                )

                OutlinedTextField(
                    value = firstName,
                    onValueChange = {firstName = it},
                    label = {
                        Text(
                            text = "First Name*",
                            fontSize = MaterialTheme.typography.bodyMedium.fontSize
                        )
                    },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                        .padding(vertical = Dimens.SmallPadding),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                    keyboardActions = KeyboardActions(
                        onNext = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }
                    )
                )

                OutlinedTextField(
                    value = lastName,
                    onValueChange = {lastName = it},
                    label = {
                        Text(
                            text = "Last Name*",
                            fontSize = MaterialTheme.typography.bodyMedium.fontSize
                        )
                    },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                        .padding(vertical = Dimens.SmallPadding),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                    keyboardActions = KeyboardActions(
                        onNext = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }
                    )
                )

                OutlinedTextField(
                    value = phoneNumber,
                    onValueChange = {phoneNumber = it},
                    label = {
                        Text(
                            text = "Phone Number*",
                            fontSize = MaterialTheme.typography.bodyMedium.fontSize
                        )
                    },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                        .padding(vertical = Dimens.SmallPadding),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                    keyboardActions = KeyboardActions(
                        onNext = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }
                    )
                )

                OutlinedTextField(
                    value = password,
                    onValueChange = {password = it},
                    label = {
                        Text(
                            text = "Password*",
                            fontSize = MaterialTheme.typography.bodyMedium.fontSize
                        )
                    },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                        .padding(vertical = Dimens.SmallPadding),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            navController.popBackStack()
                        }
                    )
                )

                Row(
                    modifier =  Modifier
                        .padding(  Dimens.LargePadding)
                        .fillMaxWidth()
                        .clip(CircleShape)
                        .background(customPrimaryPink)
                        .clickable {
                            navController.popBackStack()
                        }
                        .padding(vertical = Dimens.MediumPadding)
                    ,
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Sign Up",
                        fontWeight = FontWeight.Bold,
                        fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun T() {
    CreateNewAccountScreen(
        rememberNavController()
    )
}
