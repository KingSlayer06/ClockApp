package com.kingslayer06.clockapp.presentation.views.clock

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.kingslayer06.clockapp.presentation.viewModels.ClockViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ClockView(
    viewModel: ClockViewModel = koinViewModel()
) {

}

@Preview
@Composable
fun ClockViewPreview() {
    ClockView()
}