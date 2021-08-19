package com.example.benefits.ui.screens

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.benefits.R
import com.example.benefits.ui.navigation.Screens
import com.example.benefits.ui.notNull


enum class HomeScreen(
    @DrawableRes val iconId: Int,
    @StringRes val titleId: Int,
    val route: String
) {
    LIST(R.drawable.ic_list, R.string.navigation_tab_list, Screens.HomeScreens.List.screenName),
    MAP(R.drawable.ic_map, R.string.navigation_tab_map, Screens.HomeScreens.Map.screenName),
    CARD(R.drawable.ic_card, R.string.navigation_tab_card, Screens.HomeScreens.Card.screenName)
}

@Composable
fun BottomBarNavigator(navController: NavController = rememberNavController()) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val homeScreens = HomeScreen.values()

    val rootSelectedScreen =
        currentDestination?.hierarchy?.firstOrNull { it.route in homeScreens.map { it.route } }
    if (rootSelectedScreen.notNull()) {
        BenefitsBottomNavBar(
            homeScreens,
            rootSelectedScreen?.route
        ) { navController.navigate(it) }
    }
}

@Composable
private fun BenefitsBottomNavBar(
    items: Array<HomeScreen>,
    selectedScreenRoute: String?,
    onClick: (String) -> Unit
) {
    BottomNavigation {
        items.forEach { screen ->
            BottomNavItem(
                screen.route == selectedScreenRoute,
                icon = {
                    Image(
                        painter = painterResource(id = screen.iconId),
                        contentDescription = stringResource(screen.titleId)
                    )
                },
                text = {
                    Text(
                        text = stringResource(screen.titleId).uppercase(),
                        fontWeight = FontWeight.Bold
                    )
                },
            ) { onClick(screen.route) }
        }
    }
}

@Composable
private fun RowScope.BottomNavItem(
    isSelected: Boolean,
    icon: @Composable () -> Unit,
    text: @Composable () -> Unit,
    onClick: () -> Unit
) {
    val styledLabel: @Composable () -> Unit = @Composable {
        val style = MaterialTheme.typography.caption.copy(textAlign = TextAlign.Center)
        ProvideTextStyle(style, content = text)
    }

    val ripple = rememberRipple(bounded = false, color = MaterialTheme.colors.surface)

    Box(
        modifier = Modifier
            .selectable(
                selected = isSelected,
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple,
                role = Role.Tab,
                onClick = onClick
            )
            .weight(1f),
        contentAlignment = Alignment.Center
    ) {
        BottomNavItemTransition(isSelected) { progressAnimationValue, textAnimationValue ->
            SelecteableBottomNavItem(
                isSelected,
                icon,
                styledLabel,
                progressAnimationValue,
                textAnimationValue
            )
        }
    }
}

@Composable
private fun BottomNavItemTransition(
    isSelected: Boolean,
    content: @Composable (colorAnimationProgress: Float, textAnimationProgress: Float) -> Unit
) {
    val progressAnimationState = animateFloatAsState(
        targetValue = if (isSelected) 1f else 0f,
        BenefitsBottomNavItemProgressAnimationSpec
    )

    val textAnimationState = animateFloatAsState(
        targetValue = if (isSelected) 1f else 0f,
        BenefitsBottomNavItemTextAnimationSpec
    )

    content(progressAnimationState.value, textAnimationState.value)
}

@Composable
private fun SelecteableBottomNavItem(
    isSelected: Boolean,
    icon: @Composable () -> Unit,
    text: @Composable () -> Unit,
    progressAnimationValue: Float,
    textAnimationValue: Float,
) {
    Surface(
        modifier = Modifier.padding(PaddingValue),
        color = MaterialTheme.colors.primary,
        shape = RoundedCornerShape(50),
        border = BorderStroke(BorderWidth, Color.White.copy(alpha = progressAnimationValue))
    ) {
        Layout(
            content = {
                Box(modifier = Modifier.layoutId("icon")) { icon() }
                Box(modifier = Modifier.layoutId("text").alpha(textAnimationValue)) { text() }
            }
        ) { measureable, constraints ->
            val iconPlaceable = measureable.first { it.layoutId == "icon" }.measure(constraints.copy(minHeight = 0))
            val labelPlaceable = measureable.first { it.layoutId == "text" }.measure(constraints.copy(minHeight = 0))
            placeIconAndText(
                icon = iconPlaceable,
                label = labelPlaceable,
                constraints = constraints,
                isSelected = isSelected
            )
        }
    }
}

private fun MeasureScope.placeIcon(
    icon: Placeable,
    constraints: Constraints
): MeasureResult {
    val height = constraints.maxHeight
    val width = constraints.maxWidth

    val iconX = (width - icon.width) / 2
    val iconY = (height - icon.height) / 2

    return layout(width, height) {
        icon.placeRelative(iconX, iconY)
    }
}

private fun MeasureScope.placeIconAndText(
    icon: Placeable,
    label: Placeable,
    constraints: Constraints,
    isSelected: Boolean
): MeasureResult {
    val height = constraints.maxHeight
    val width = constraints.maxWidth

    val iconHeight = icon.height
    val iconWidth = icon.width
    val labelHeight = label.height
    val labelWidth = label.width

    val iconY = (height - iconHeight) / 2

    // Selected label should be placed at same line with icon
    val labelY = iconY + (iconHeight - labelHeight) / 2

    val iconX = if (isSelected) {
        (width - (iconWidth + labelWidth)) / 2
    } else {
        (width - icon.width) / 2
    }

    val labelX = ((width - (iconWidth + labelWidth)) / 2) + iconWidth

    return layout(width, height) {
        icon.placeRelative(iconX, iconY)
        label.placeRelative(labelX, labelY)
    }
}

private val PaddingValue = 8.dp
private val BorderWidth = 2.dp
private val BenefitsBottomNavItemProgressAnimationSpec = TweenSpec<Float>(durationMillis = 250)
private val BenefitsBottomNavItemTextAnimationSpec = TweenSpec<Float>(durationMillis = 50)


@Preview(showSystemUi = true)
@Composable
private fun navPreview() {
    Scaffold(
        bottomBar = { BenefitsBottomNavBar(items = HomeScreen.values(), HomeScreen.LIST.route) {} }
    ) {}
}