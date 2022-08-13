
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.himanshoe.kalendarkit.component.day.config.KalendarDayConfig
import com.himanshoe.kalendarkit.component.day.config.KalendarDayDefaults
import com.himanshoe.kalendarkit.config.KalendarConfigDefaults
import com.himanshoe.kalendarkit.config.KalendarConfigs
import com.himanshoe.kalendarkit.model.KalendarDay
import com.himanshoe.kalendarkit.model.KalendarEvent

@Composable
fun Kalendar(
    modifier: Modifier = Modifier,
    kalendarEvents: List<KalendarEvent> = emptyList(),
    onCurrentDayClick: (KalendarDay, List<KalendarEvent>) -> Unit = { _, _ -> },
    kalendarDayConfig: KalendarDayConfig = KalendarDayDefaults.kalendarDayConfig(),
    kalendarConfigs: KalendarConfigs = KalendarConfigDefaults.kalendarConfigDefaults()

) {
}

@Composable
@Preview
private fun KalendarPreview() {
    Kalendar()
}
