package co.elcrow.battlecontainer.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import co.elcrow.battlecontainer.R
import com.rizzi.bouquet.ResourceType
import com.rizzi.bouquet.VerticalPDFReader
import com.rizzi.bouquet.rememberVerticalPdfReaderState

@Composable
fun RulebookPage(appInfo: AppInfo) {
    val context = LocalContext.current

    val pdfState = rememberVerticalPdfReaderState(
        resource = ResourceType.Remote(context.getString(R.string.url_rulebook_pdf)),
        isZoomEnable = true)

    VerticalPDFReader(
        state = pdfState,
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Gray))
}