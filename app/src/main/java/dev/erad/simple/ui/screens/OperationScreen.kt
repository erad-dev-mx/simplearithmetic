package dev.erad.simple.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import dev.erad.simple.R
import dev.erad.simple.ui.components.TitleComponent
import dev.erad.simple.ui.theme.Dimens
import dev.erad.simple.ui.theme.SimpleArithmeticTheme
import dev.erad.simple.viewmodel.OperationViewModel
import kotlin.math.pow

@Composable
fun OperationScreen(
    modifier: Modifier,
    operationId: Int?,
    viewModel: OperationViewModel
) {
    LaunchedEffect(operationId) {
        viewModel.resetValues()
    }
    Scaffold { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = Dimens.dp8, vertical = Dimens.dp16),
        ) {
            val titleResId = when (operationId) {
                R.string.operation_addition -> R.string.operation_addition
                R.string.operation_subtraction -> R.string.operation_subtraction
                R.string.operation_multiplication -> R.string.operation_multiplication
                R.string.operation_division -> R.string.operation_division
                R.string.operation_exponentiation -> R.string.operation_exponentiation
                R.string.operation_modulo -> R.string.operation_modulo
                R.string.operation_square_root -> R.string.operation_square_root
                else -> R.string.operation_unknown
            }

            TitleComponent(
                titleResId = titleResId,
                modifier = modifier.padding(bottom = Dimens.dp16)
            )

            when (operationId) {
                R.string.operation_addition -> AdditionComponent(modifier, viewModel)
                R.string.operation_subtraction -> SubtractionComponent(modifier, viewModel)
                R.string.operation_multiplication -> MultiplicationComponent(modifier, viewModel)
                R.string.operation_division -> DivisionComponent(modifier, viewModel)
                R.string.operation_exponentiation -> ExponentiationComponent(modifier, viewModel)
                R.string.operation_modulo -> ModuloComponent(modifier, viewModel)
                R.string.operation_square_root -> SquareRootComponent(modifier, viewModel)
            }
        }
    }
}

@Composable
fun BasicOperationComponent(
    modifier: Modifier,
    viewModel: OperationViewModel,
    operation: (Double, Double) -> Double
) {
    val num1 by viewModel.num1.collectAsState()
    val num2 by viewModel.num2.collectAsState()
    val result by viewModel.result.collectAsState()
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row(modifier = modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = num1,
                onValueChange = { viewModel.onNum1Change(it) },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                textStyle = MaterialTheme.typography.headlineLarge.copy(textAlign = TextAlign.Center),
                singleLine = true,
                modifier = modifier
                    .weight(1f)
                    .height(Dimens.dp120)
            )

            Spacer(modifier = modifier.width(Dimens.dp8))

            OutlinedTextField(
                value = num2,
                onValueChange = { viewModel.onNum2Change(it) },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = {
                        viewModel.calculate(operation)
                        keyboardController?.hide()
                    }
                ),
                textStyle = MaterialTheme.typography.headlineLarge.copy(textAlign = TextAlign.Center),
                singleLine = true,
                modifier = modifier
                    .weight(1f)
                    .height(Dimens.dp120)
            )
        }

        Spacer(modifier = modifier.height(Dimens.dp16))

        Button(onClick = {
            viewModel.calculate(operation)
            keyboardController?.hide()
        }) {
            Text(text = stringResource(id = R.string.operation_calculate))
        }

        Spacer(modifier = modifier.height(Dimens.dp40))

        result?.let {
            Text(it.toString(), style = MaterialTheme.typography.headlineLarge)
        }
    }
}

@Preview(name = "Light Mode", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "Dark Mode", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun BasicOperationComponentPreview(modifier: Modifier = Modifier) {
    SimpleArithmeticTheme {
        BasicOperationComponent(
            modifier = modifier,
            viewModel = OperationViewModel(),
            operation = { a, b -> a + b })
    }
}

@Composable
fun AdditionComponent(modifier: Modifier, viewModel: OperationViewModel) {
    BasicOperationComponent(modifier, viewModel, operation = { a, b -> a + b })
}

@Composable
fun SubtractionComponent(modifier: Modifier, viewModel: OperationViewModel) {
    BasicOperationComponent(modifier, viewModel, operation = { a, b -> a - b })
}

@Composable
fun MultiplicationComponent(modifier: Modifier, viewModel: OperationViewModel) {
    BasicOperationComponent(modifier, viewModel, operation = { a, b -> a * b })
}

@Composable
fun DivisionComponent(modifier: Modifier, viewModel: OperationViewModel) {
    BasicOperationComponent(
        modifier,
        viewModel,
        operation = { a, b -> if (b != 0.0) a / b else Double.NaN })
}

@Composable
fun ExponentiationComponent(modifier: Modifier, viewModel: OperationViewModel) {
    BasicOperationComponent(modifier, viewModel, operation = { a, b -> a.pow(b) })
}

@Composable
fun ModuloComponent(modifier: Modifier, viewModel: OperationViewModel) {
    BasicOperationComponent(
        modifier,
        viewModel,
        operation = { a, b -> if (b != 0.0) a % b else Double.NaN })
}

@Composable
fun SquareRootComponent(modifier: Modifier, viewModel: OperationViewModel) {
    val num1 by viewModel.num1.collectAsState()
    val result by viewModel.result.collectAsState()

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        OutlinedTextField(
            value = num1,
            onValueChange = { viewModel.onNum1Change(it) },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            textStyle = MaterialTheme.typography.headlineLarge.copy(textAlign = TextAlign.Center),
            singleLine = true,
            modifier = modifier
                .fillMaxWidth()
                .height(Dimens.dp120)
        )

        Spacer(modifier = modifier.height(Dimens.dp16))

        Button(onClick = { viewModel.calculateSquareRoot() }) {
            Text(text = stringResource(id = R.string.operation_calculate))
        }

        Spacer(modifier = modifier.height(Dimens.dp40))

        result?.let {
            Text(it.toString(), style = MaterialTheme.typography.headlineLarge)
        }
    }
}

@Preview(name = "Light Mode", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "Dark Mode", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun SquareRootComponentPreview(modifier: Modifier = Modifier) {
    SimpleArithmeticTheme {
        SquareRootComponent(
            modifier = modifier,
            viewModel = OperationViewModel()
        )
    }
}