package com.jmarser.worktrack.presentation.companyDetails.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.room.util.TableInfo
import com.jmarser.worktrack.ui.theme.MyAppTheme
import com.jmarser.worktrack.ui.theme.appDimens

@Composable
fun StatusBox(
    modifier: Modifier = Modifier,
    label: String,
    value: String,
    containerColor: Color,
    contentColor: Color,
    isRight: Boolean
) {

     val right = if (isRight) Modifier.fillMaxWidth() else Modifier
    Column(
        modifier = modifier
            .clip(MaterialTheme.shapes.medium)
            .background(containerColor)
            .padding(appDimens.paddingNormal)
    ) {
        Text(
            modifier = right,
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = contentColor.copy(alpha = .6f),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.End
        )
        Text(
            modifier = right,
            text = value,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Black,
            color = contentColor,
            textAlign = TextAlign.End
        )
    }

}

@Preview(
    showSystemUi = false,
    showBackground = true
)
@Composable
fun StatusBoxPreview() {
    MyAppTheme() {
        StatusBox(
            modifier = Modifier,
            label = "DÍAS PENDIENTES",
            value = "3",
            containerColor = Color(0xFFFFF0F0),
            contentColor = Color(0xFFC92A2A),
            isRight = true
        )
    }
}
