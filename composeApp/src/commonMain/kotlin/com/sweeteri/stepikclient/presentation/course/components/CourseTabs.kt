package com.sweeteri.stepikclient.presentation.course.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.sweeteri.stepikclient.generated.resources.Res
import com.sweeteri.stepikclient.generated.resources.detailscreen_tab_info
import com.sweeteri.stepikclient.generated.resources.detailscreen_tab_reviews
import com.sweeteri.stepikclient.generated.resources.detailscreen_tab_syllabus
import com.sweeteri.stepikclient.presentation.ui.theme.StepikTheme
import org.jetbrains.compose.resources.stringResource



@Composable
fun CourseTabs(
    selectedTab: Int,
    onTabSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val tabs = listOf(
        stringResource(Res.string.detailscreen_tab_info),
        stringResource(Res.string.detailscreen_tab_reviews),
        stringResource(Res.string.detailscreen_tab_syllabus)
    )

    TabRow(
        selectedTabIndex = selectedTab,
        modifier = modifier.fillMaxWidth(),
        containerColor = StepikTheme.colors.textFieldBackground,

        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTab]),
                color = StepikTheme.colors.accent,
                height = 3.dp
            )
        },

        divider = {}
    ) {
        tabs.forEachIndexed { index, title ->
            val isSelected = selectedTab == index

            Tab(
                selected = isSelected,
                onClick = { onTabSelected(index) },

                selectedContentColor = StepikTheme.colors.accent,
                unselectedContentColor = StepikTheme.colors.textSecondary,

                text = {
                    Text(
                        text = title,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                    )
                }
            )
        }
    }
}
