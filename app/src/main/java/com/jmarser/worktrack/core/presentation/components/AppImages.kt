package com.jmarser.worktrack.core.presentation.components

import android.media.Image
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Apartment
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Money
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Apartment
import androidx.compose.material.icons.outlined.ErrorOutline
import androidx.compose.material.icons.outlined.EventNote
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import com.jmarser.worktrack.R

object AppImages {

    val ic_refresh: ImageVector @Composable get() = Icons.Default.Refresh
    val ic_settings: ImageVector @Composable get() = Icons.Default.Settings
    val ic_filters: ImageVector @Composable get() = Icons.Default.FilterList
    val ic_add: ImageVector @Composable get() = Icons.Default.Add
    val ic_menu: ImageVector @Composable get() = Icons.Default.MoreVert
    val ic_company: ImageVector @Composable get() = Icons.Default.Apartment
    val ic_money: ImageVector @Composable get() = Icons.Default.Money
    val ic_save: ImageVector @Composable get() = Icons.Default.Save
    val ic_empty_work_day: ImageVector @Composable get() = Icons.Outlined.EventNote
    val ic_error_screen: ImageVector @Composable get() = Icons.Outlined.ErrorOutline



}