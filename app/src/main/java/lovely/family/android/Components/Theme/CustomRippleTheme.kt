package lovely.family.android.Components.Theme

import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class)
val CustomRippleConfiguration = RippleConfiguration(
    color = Color.Companion.Unspecified,
    rippleAlpha = RippleAlpha(
        draggedAlpha = 0f,
        focusedAlpha = 0f,
        hoveredAlpha = 0f,
        pressedAlpha = 0f,
    )
)