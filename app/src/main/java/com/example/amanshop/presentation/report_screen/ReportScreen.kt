package com.example.amanshop.presentation.report_screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.amanshop.R
import com.example.amanshop.presentation.components.ReportScreenHint
import com.example.amanshop.presentation.report_screen.component.feedbackReasons
import com.example.amanshop.presentation.report_screen.component.productReason
import com.example.amanshop.presentation.report_screen.component.sellerReason
import com.example.amanshop.presentation.ui.theme.Dimens
import com.example.amanshop.presentation.ui.theme.nunitoFontFamily


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReportScreen(
    navController: NavController,
    prevScreenHint : String,
    reportedName : String
) {
    var dropDownList =  productReason
    var reasonQuestion : String = "Tell us why you are reporting This"
    var context = LocalContext.current


    when(prevScreenHint){
        ReportScreenHint.Product.hint -> {
            reasonQuestion = "Tell us why are you reporting this ad?"
            dropDownList = productReason
        }
        ReportScreenHint.Seller.hint -> {
            reasonQuestion = "Tell us why are you reporting this seller?"
            dropDownList = sellerReason
        }
        ReportScreenHint.Feedback.hint -> {
            reasonQuestion = "Tell us why are you reporting this feedback?"
            dropDownList = feedbackReasons
        }
        else -> {  }
    }

    var isExpanded by remember { mutableStateOf(false) }
    var selectedDropDownReason by  remember { mutableStateOf(dropDownList[0]) }
    var description by  remember { mutableStateOf("") }


    Column(
        modifier = Modifier
            .fillMaxSize()
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
                text = "Report",
                color = Color.Red,
                fontSize = MaterialTheme.typography.headlineLarge.fontSize,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = nunitoFontFamily
            )
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimens.SmallPadding),
            contentPadding = PaddingValues(Dimens.SmallPadding),
        ) {
            item {

                Text(
                    text = reasonQuestion,
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = Dimens.SmallPadding)
                    )
                Text(
                    text = reportedName,
                    fontSize = MaterialTheme.typography.bodySmall.fontSize,
                    //fontWeight = FontWeight.Bold,
                    color = Color.Gray,
                    modifier = Modifier.padding(bottom = Dimens.SmallPadding)
                )

                ExposedDropdownMenuBox(
                    expanded = isExpanded,
                    onExpandedChange = {isExpanded = !isExpanded},
                    modifier = Modifier.fillMaxWidth()
                ) {
                    OutlinedTextField(
                        value = selectedDropDownReason,
                        onValueChange = {},
                        readOnly = true,
                        trailingIcon = {ExposedDropdownMenuDefaults.TrailingIcon(isExpanded)},
                        textStyle = TextStyle(
                            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                            fontFamily = FontFamily.Default
                        ),
                        modifier = Modifier.menuAnchor()
                            .fillMaxWidth()
                    )

                    ExposedDropdownMenu(
                        expanded = isExpanded,
                        onDismissRequest = {isExpanded = false}
                    ) {
                        dropDownList.forEach {reason ->
                            DropdownMenuItem(
                                text = {
                                    Text(text = reason)
                                },
                                onClick = {
                                    selectedDropDownReason = reason
                                    isExpanded = false
                                }
                            )
                        }
                    }
                }
            }

            item {
                Text(
                    text = "Description:",
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = Dimens.LargePadding, bottom = Dimens.SmallPadding)
                )

                OutlinedTextField(
                    value = description,
                    onValueChange = {description = it},
                    minLines = 3,
                    textStyle = TextStyle(
                        fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                        fontFamily = FontFamily.Default
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                Box(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(vertical = Dimens.MediumPadding)
                        .clip(CircleShape)
                        .background(Color.Red)
                        .clickable {
                            if(description.isEmpty()){
                                Toast.makeText(context, "Please fill Description", Toast.LENGTH_SHORT).show()
                            }else {
                                navController.popBackStack()
                            }
                            //display a toast here
                            //Toast.makeText(getApplicationContext(), "Reported is Submitted", Toast.LENGTH_SHORT).show()
                        }
                        .padding(horizontal = Dimens.LargePadding, vertical = Dimens.SmallPadding)
                ) {
                    Text(
                        text = "Submit",
                        fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Default,
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
    ReportScreen(
        navController = rememberNavController(),
        prevScreenHint = ReportScreenHint.Product.hint,
        reportedName = "Abebe Kebede"
    )
}
