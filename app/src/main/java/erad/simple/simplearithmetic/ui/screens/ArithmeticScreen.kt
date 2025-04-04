package erad.simple.simplearithmetic.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import erad.simple.simplearithmetic.R
import erad.simple.simplearithmetic.ui.components.ImportantPeopleHorizontalGrid
import erad.simple.simplearithmetic.ui.components.OperationCard
import erad.simple.simplearithmetic.ui.components.SubtitleComponent
import erad.simple.simplearithmetic.ui.components.TitleComponent
import erad.simple.simplearithmetic.ui.components.WhatIsComponent
import erad.simple.simplearithmetic.ui.theme.Dimens
import erad.simple.simplearithmetic.viewmodel.ArithmeticViewModel
import erad.simple.simplearithmetic.viewmodel.WhatIsViewModel

@Composable
fun ArithmeticScreen(
    viewModel: ArithmeticViewModel,
    whatIsViewModel: WhatIsViewModel,
    modifier: Modifier,
    onOperationClick: (Int) -> Unit
) {
    val operations by viewModel.operations.collectAsState()
    val title by viewModel.title.collectAsState()

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
            item { TitleComponent(titleResId = title) }
            item {
                WhatIsComponent(
                    modifier = modifier,
                    viewModel = whatIsViewModel,
                    title = stringResource(R.string.title_what_is),
                    body = stringResource(R.string.what_is_text)
                )
            }
            item {
                SubtitleComponent(
                    modifier = modifier,
                    text = R.string.subtitle_important_people
                )
            }
            item { ImportantPeopleHorizontalGrid(modifier = modifier, viewModel = viewModel) }
            item { Spacer(modifier = modifier.padding(bottom = Dimens.dp32)) }
            item { SubtitleComponent(modifier = modifier, text = R.string.subtitle_operations) }
            items(operations) { operation ->
                OperationCard(
                    modifier = modifier,
                    drawable = operation.drawable,
                    text = operation.text,
                    onClick = { onOperationClick(operation.text) }
                )
            }
            item { Spacer(modifier = modifier.padding(bottom = Dimens.dp32)) }
        }
    }
}

@Preview(widthDp = 360, heightDp = 640)
@Composable
fun ArithmeticScreenPreview() {
    ArithmeticScreen(
        viewModel = ArithmeticViewModel(),
        whatIsViewModel = WhatIsViewModel(),
        onOperationClick = {},
        modifier = Modifier
    )
}