package com.example.android.campingonline

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.boguszpawlowski.composecalendar.SelectableCalendar
import io.github.boguszpawlowski.composecalendar.rememberSelectableCalendarState
import io.github.boguszpawlowski.composecalendar.selection.DynamicSelectionState
import io.github.boguszpawlowski.composecalendar.selection.SelectionMode

@Composable
fun SelectableCalendar() {
    val calendarState = rememberSelectableCalendarState()

    Column(
        Modifier.verticalScroll(rememberScrollState())
    ) {
        // TODO find a way to representate unavailabe dates
        SelectableCalendar(calendarState = calendarState)
        
        SelectionControls(selectionState = calendarState.selectionState)
    }
}

//@Composable
//fun MyDay(dayState: DayState<DynamicSelectionState>) {
//    if (dayState.date.dayOfMonth == 1) {
//        Text(
//            text = dayState.date.dayOfMonth.toString(),
//            color = Color.Green
//        )
//    } else {
//        DefaultDay(state = dayState)
//    }
//}

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