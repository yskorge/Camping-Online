package com.example.android.campingonline

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.boguszpawlowski.composecalendar.SelectableCalendar
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
        // TODO find a way to representate unavailabe dates
        SelectableCalendar(
            calendarState = calendarState,
            monthHeader = { CustomMonthHeader(monthState = it) },
            weekHeader = { CustomWeekHeader(daysOfWeek = it) })

        SelectionControls(selectionState = calendarState.selectionState)
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