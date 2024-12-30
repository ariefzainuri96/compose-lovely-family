package lovely.family.android.Components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.repeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import lovely.family.android.Components.Theme.TextPrimaryColor
import lovely.family.android.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpandableCard(
    modifier: Modifier = Modifier,
    isDefaultExpanded: Boolean = false,
    padding: Dp = 0.dp,
    contentExpandedSpacerHeight: Dp = 8.dp,
    headerVerticalAlignment: Alignment.Vertical = Alignment.CenterVertically,
    header: @Composable () -> Unit,
    expandableContent: @Composable () -> Unit
) {
    var expandedState by remember { mutableStateOf(isDefaultExpanded) }

    val rotationState by animateFloatAsState(
        targetValue = if (expandedState) 180f else 0f,
        animationSpec = tween(
            durationMillis = 300,
            easing = LinearOutSlowInEasing
        ),
        label = ""
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            )
            .padding(padding)
    ) {
        Row(
            modifier = Modifier
                .clickable {
                    expandedState = !expandedState
                }, verticalAlignment = headerVerticalAlignment
        ) {
            Box(modifier = Modifier.weight(1f)) {
                header()
            }

            Icon(
                painter = painterResource(R.drawable.ic_expand), contentDescription = null,
                tint = TextPrimaryColor,
                modifier = Modifier
                    .rotate(rotationState)
            )
        }

        // put your expanded content here
        if (expandedState) {
            Spacer(Modifier.height(contentExpandedSpacerHeight))

            expandableContent()
        }
    }
}