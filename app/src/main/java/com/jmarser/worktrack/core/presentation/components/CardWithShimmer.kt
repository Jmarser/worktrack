package com.jmarser.worktrack.core.presentation.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.jmarser.worktrack.ui.theme.MyAppTheme
import com.jmarser.worktrack.ui.theme.appDimens
import com.jmarser.worktrack.ui.theme.shimmerEffect

@Composable
fun CardWithShimmer(
    modifier: Modifier = Modifier,
    cardHeight: Dp = 40.dp
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = appDimens.paddingSmall)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(cardHeight)
                .background(
                    color = Color.LightGray.copy(.6f),
                    shape = MaterialTheme.shapes.extraSmall
                )
                .shimmerEffect()
        )
    }
}

@Preview(
    showSystemUi = false,
    showBackground = true
)
@Composable
fun CardWithShimmerPreview() {
    MyAppTheme() {
        CardWithShimmer(modifier = Modifier)
    }
}
