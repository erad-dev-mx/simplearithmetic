package dev.erad.simplesoftware.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import dev.erad.simplesoftware.ui.theme.Dimens
import dev.erad.simplesoftware.ui.theme.SimpleArithmeticTheme
import dev.erad.simplesoftware.viewmodel.WhatIsViewModel

@Composable
fun WhatIsComponent(
    viewModel: WhatIsViewModel,
    title: String,
    body: String,
    modifier: Modifier
) {
    val showBottomSheet by viewModel.showBottomSheet.collectAsState()

    Card(
        modifier = modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.medium)
            .padding(bottom = Dimens.dp32)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = LocalIndication.current,
                onClick = { viewModel.showSheet() }
            ),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer,
            contentColor = MaterialTheme.colorScheme.onSurface
        )
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = Dimens.dp16, vertical = Dimens.dp16)
        ) {
            Text(
                text = title, // Ahora es dinámico
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                modifier = modifier.paddingFromBaseline(bottom = Dimens.dp24)
            )
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSecondaryContainer,
                modifier = modifier.size(Dimens.dp56)
            )
        }
    }

    if (showBottomSheet) {
        WhatIsBottomSheet(
            modifier = modifier,
            title = title,
            body = body,
            onDismiss = { viewModel.hideSheet() }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WhatIsBottomSheet(
    title: String,
    body: String,
    modifier: Modifier,
    onDismiss: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = Dimens.dp24, vertical = Dimens.dp24)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineMedium,
                modifier = modifier.padding(bottom = Dimens.dp16)
            )

            Text(
                text = body,
                style = MaterialTheme.typography.bodyLarge,
                modifier = modifier.padding(bottom = Dimens.dp24)
            )
        }
    }
}

@Preview(name = "Light Mode", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "Dark Mode", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun WhatIsComponentPreview(modifier: Modifier = Modifier) {
    SimpleArithmeticTheme {
        WhatIsComponent(
            viewModel = WhatIsViewModel(),
            "What is",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Eros, nec ultricies eros eros nec eros.",
            modifier = modifier
        )
    }
}