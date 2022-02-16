package com.example.android.campingonline

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.boguszpawlowski.composecalendar.SelectableCalendar
import io.github.boguszpawlowski.composecalendar.day.DayState
import io.github.boguszpawlowski.composecalendar.day.DefaultDay
import io.github.boguszpawlowski.composecalendar.rememberSelectableCalendarState
import io.github.boguszpawlowski.composecalendar.selection.DynamicSelectionState
import io.github.boguszpawlowski.composecalendar.selection.SelectionMode

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SelectableCalendar() {
    val calendarState = rememberSelectableCalendarState()

    Column(
        Modifier.verticalScroll(rememberScrollState())
    ) {
        SelectableCalendar(
            calendarState = calendarState,
            dayContent = { dayState -> MyDay(dayState) }
        )

        SelectionControls(selectionState = calendarState.selectionState)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MyDay(dayState: DayState<DynamicSelectionState>) {
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
//        )
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(
                text = dayState.date.dayOfMonth.toString(),
                style = TextStyle(
                    textDecoration = TextDecoration.LineThrough,
                    // TODO With Elizabeth figure out proper theming colors here
                    color = Color.Red
                ),
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