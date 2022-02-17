package com.example.android.campingonline

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.boguszpawlowski.composecalendar.SelectableCalendar
import io.github.boguszpawlowski.composecalendar.day.DayState
import io.github.boguszpawlowski.composecalendar.day.DefaultDay
import io.github.boguszpawlowski.composecalendar.header.MonthState
import io.github.boguszpawlowski.composecalendar.rememberSelectableCalendarState
import io.github.boguszpawlowski.composecalendar.selection.DynamicSelectionState
import io.github.boguszpawlowski.composecalendar.selection.SelectionMode
import java.time.DayOfWeek
import java.time.format.TextStyle
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SelectableCalendar() {
    val calendarState = rememberSelectableCalendarState()

    Column(
        Modifier.verticalScroll(rememberScrollState())
    ) {
        SelectableCalendar(
            calendarState = calendarState,
            monthHeader = { CustomMonthHeader(monthState = it) },
            weekHeader = { CustomWeekHeader(daysOfWeek = it) },
            dayContent = { dayState -> Days(dayState) }
        )
        SelectionControls(selectionState = calendarState.selectionState)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Days(dayState: DayState<DynamicSelectionState>) {
    // Example of how the unavailable dates can be returned from the server
    val listOfUnavailableDates = listOf("2022-02-20", "2022-02-21", "2022-02-22")

    val currentDate = dayState.date.toString()

    if (listOfUnavailableDates.contains(currentDate)) {
        CustomUnavailableDay(dayState)
    } else {
        DefaultDay(state = dayState)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CustomUnavailableDay(dayState: DayState<DynamicSelectionState>, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .aspectRatio(1f)
            .padding(2.dp),
//        elevation = if (dayState.isFromCurrentMonth) 4.dp else 0.dp,
//        border = if (state.isCurrentDay) BorderStroke(1.dp, currentDayColor) else null,
//        contentColor = if (isSelected) selectionColor else contentColorFor(
//            backgroundColor = MaterialTheme.colors.surface
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(
                text = dayState.date.dayOfMonth.toString(),
                style = androidx.compose.ui.text.TextStyle(
                    textDecoration = TextDecoration.LineThrough,
                    // TODO With Elizabeth figure out proper theming colors here
                    color = Color.Red
                ),
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun CustomMonthHeader(monthState: MonthState, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
    ) {
        IconButton(
            modifier = Modifier.testTag("Decrement"),
            onClick = { monthState.currentMonth = monthState.currentMonth.minusMonths(1) }
        ) {
            Image(
                imageVector = Icons.Default.KeyboardArrowLeft,
                colorFilter = ColorFilter.tint(MaterialTheme.colors.onSurface),
                contentDescription = "Previous",
            )
        }
        Text(
            modifier = Modifier.testTag("MonthLabel"),
            text = monthState.currentMonth.month.name.lowercase()
                .replaceFirstChar { it.titlecase() },
            style = MaterialTheme.typography.h4,
            color = MaterialTheme.colors.onSurface
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = monthState.currentMonth.year.toString(),
            style = MaterialTheme.typography.h4,
            color = MaterialTheme.colors.onSurface
        )
        IconButton(
            modifier = Modifier.testTag("Increment"),
            onClick = { monthState.currentMonth = monthState.currentMonth.plusMonths(1) }
        ) {
            Image(
                imageVector = Icons.Default.KeyboardArrowRight,
                colorFilter = ColorFilter.tint(MaterialTheme.colors.onSurface),
                contentDescription = "Next",
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
public fun CustomWeekHeader(
    daysOfWeek: List<DayOfWeek>,
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier) {
        daysOfWeek.forEach { dayOfWeek ->
            Text(
                textAlign = TextAlign.Center,
                text = dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.ROOT),
                modifier = modifier
                    .weight(1f)
                    .wrapContentHeight(),
                color = MaterialTheme.colors.onSurface
            )
        }
    }
}

@Composable
private fun SelectionControls(
    selectionState: DynamicSelectionState,
) {
    selectionState.selectionMode = SelectionMode.Period

    Text(
        text = "Calendar Selection Mode",
        style = MaterialTheme.typography.h5,
    )

    Text(
        text = "Selection: ${selectionState.selection.joinToString { it.toString() }}",
        style = MaterialTheme.typography.h6,
    )
}

@Preview
@Composable
fun PreviewSelectableCalendar() {
    SelectableCalendar()
}