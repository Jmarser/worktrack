package com.jmarser.worktrack.core.presentation.components

import android.media.Image
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

object AppImages {

    val ic_refresh: ImageVector @Composable get() = Icons.Default.Refresh
    val ic_settings: ImageVector @Composable get() = Icons.Default.Settings
    val ic_filters: ImageVector @Composable get() = Icons.Default.FilterList
    val ic_add: ImageVector @Composable get() = Icons.Default.Add
    val ic_menu: ImageVector @Composable get() = Icons.Default.MoreVert
}