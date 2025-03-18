package dev.erad.simplesoftware.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import dev.erad.simplesoftware.R
import dev.erad.simplesoftware.ui.components.TitleComponent
import dev.erad.simplesoftware.ui.components.WhatIsComponent
import dev.erad.simplesoftware.ui.theme.Dimens
import dev.erad.simplesoftware.viewmodel.WhatIsViewModel

@Composable
fun AboutScreen(
    modifier: Modifier,
    whatIsViewModel: WhatIsViewModel,
) {
    Scaffold(
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(start = Dimens.dp16, end = Dimens.dp16),
            verticalArrangement = Arrangement.spacedBy(Dimens.dp8),
            contentPadding = innerPadding
        ) {
            item { TitleComponent(titleResId = R.string.about_section_title) }
            item {
                WhatIsComponent(
                    modifier = modifier,
                    viewModel = whatIsViewModel,
                    title = stringResource(R.string.what_is_simple_title),
                    body = stringResource(R.string.what_is_simple_text)
                )
            }
            item {
                Row(
                    modifier = modifier.padding(top = Dimens.dp250),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "with ")
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_fav),
                        contentDescription = "Heart",
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Text(text = " erick.")
                }
            }
        }
    }
}