package com.example.amanshop.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.amanshop.R
import com.example.amanshop.presentation.ui.theme.Dimens
import com.example.amanshop.presentation.ui.theme.nunitoFontFamily

//Todo: need to add a border at bottom that will appear when scrolled

@Composable
fun SearchBarComponent(
    modifier: Modifier = Modifier,
    enabled:Boolean = true,
    navController: NavController,
    searchText : String = "",
    label : String = "Search",
    onSearchNavigate : (String) -> Unit = {},
    onValueChange :(String)->Unit = {},
    onClearClicked : ()->Unit = {}
) {

    var searchedText by rememberSaveable { mutableStateOf(searchText) }
    var isFocused by remember { mutableStateOf(false) }
    val borderColor by remember { derivedStateOf { if (isFocused) Color.Black else Color.Gray } }

    Row(
        modifier = modifier
            .height(OutlinedTextFieldDefaults.MinHeight.times(1.4f))
            .padding(
                top = Dimens.MediumPadding.plus(Dimens.MiniPadding),
                bottom = Dimens.LargePadding.minus(Dimens.MiniPadding)
            )
            .border(
                width = Dimens.OneDp,
                shape = RoundedCornerShape(Dimens.MediumRoundRadius),
                color = borderColor
            )
            .clickable {
                if(!enabled){
                    navController.navigate(ScreenRoute.SearchScreen.route)
                }
            }
            .padding(horizontal = Dimens.MiniPadding),
        verticalAlignment = Alignment.CenterVertically
    ){

        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_lucide_search),
            contentDescription = null,
            tint = Color.Gray
        )

        BasicTextField(
            value = searchedText,
            onValueChange = {
                searchedText = it
                onValueChange(it)
                            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = if (searchedText.isEmpty()) 0.dp else 8.dp)
                .padding(start = Dimens.SmallPadding)
                .onFocusEvent {
                    isFocused = it.isFocused
                },
            enabled = enabled,
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearchNavigate(searchedText)
                }
            ),
            textStyle = TextStyle(fontFamily = nunitoFontFamily),
            decorationBox = {innerTextField ->
                Box(
                    contentAlignment = Alignment.CenterStart,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    if (searchedText.isEmpty()) {
                        Text(text = label, color = Color.Gray)
                    }
                    innerTextField()
                    if (searchedText.isNotEmpty()) {
                        IconButton(
                            onClick = {
                                searchedText = ""
                                onClearClicked()
                                      },
                            modifier = Modifier.align(Alignment.CenterEnd)
                        ) {
                            Icon(Icons.Default.Close, contentDescription = null)
                        }
                    }
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SearchBarPreview(modifier: Modifier = Modifier) {
    SearchBarComponent(navController = rememberNavController())
}