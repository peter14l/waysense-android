package com.waysense.app.ui.screens.onboarding

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun OnboardingScreen(
    onOnboardingComplete: () -> Unit,
    viewModel: OnboardingViewModel = viewModel(),
) {
    val state by remember { viewModel.state }

    when (state.currentStep) {
        1 -> OnboardingScreen1Welcome(onGetStarted = { viewModel.nextStep() })
        2 -> OnboardingScreen2NavigationStyle(
            selectedStyles = state.navigationStyles,
            onToggle = { viewModel.toggleNavigationStyle(it) },
            onNext = { viewModel.nextStep() },
        )
        3 -> OnboardingScreen3Guidance(
            selectedOptions = state.guidanceOptions,
            onToggle = { viewModel.toggleGuidance(it) },
            onNext = { viewModel.nextStep() },
        )
        4 -> OnboardingScreen4Ready(onEnter = onOnboardingComplete)
    }
}
