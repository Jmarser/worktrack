package com.jmarser.worktrack.core.presentation.components


import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.jmarser.worktrack.ui.theme.MyAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    modifier: Modifier = Modifier,
    title: String,
    showOnBack: Boolean = false,
    showSettings: Boolean = true,
    showFilters: Boolean = false,
    showAddIcon: Boolean = true,
    filtersVisible: Boolean = false,
    onBackSelected: () -> Unit = {},
    onSettingsSelected: () -> Unit = {},
    onFiltersSelected: (Boolean) -> Unit = {},
    onCreatedSelected: () -> Unit = {}
) {

    var menuExpanded by remember { mutableStateOf(false) }

    TopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold
            )
        },
        navigationIcon = {
            if (showOnBack) {
                IconButton(
                    onClick = {onBackSelected()}
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "back",
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        },
        actions = {
            if (showAddIcon){
                IconButton(
                    onClick = {
                        onCreatedSelected()
                    }
                ) {
                    Icon(
                        imageVector = AppImages.ic_add,
                        contentDescription = "Crear",
                        tint = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }
            if (showSettings || showFilters){
                Box(){
                    IconButton(
                        onClick = {
                            menuExpanded = true
                        }
                    ) {
                        Icon(
                            imageVector = AppImages.ic_menu,
                            contentDescription = "más opciones",
                            tint = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                    DropdownMenu(
                        expanded = menuExpanded,
                        onDismissRequest = {
                            menuExpanded = false
                        }
                    ) {
                        if (showFilters) {
                            DropdownMenuItem(
                                text = { Text(if (filtersVisible) "Ocultar filtros" else "Mostrar filtros") },
                                onClick = {
                                    onFiltersSelected(!filtersVisible)
                                    menuExpanded = false
                                },
                                leadingIcon = { Icon(AppImages.ic_filters, contentDescription = null) }
                            )
                        }
                        if (showSettings) {
                            DropdownMenuItem(
                                text = { Text("Ajustes") },
                                onClick = {
                                    onSettingsSelected()
                                    menuExpanded = false
                                },
                                leadingIcon = { Icon(AppImages.ic_settings, contentDescription = null) }
                            )
                        }
                    }
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    )
}

@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
fun AppBarPreview() {
    MyAppTheme() {
        AppBar(
            modifier = Modifier,
            title = "Cabecera pantalla",
            showOnBack = true,
            showSettings = true,
            showFilters = true,
            filtersVisible = false,
            onBackSelected = {},
            onSettingsSelected = {},
            onFiltersSelected = {}
        )
    }
}
