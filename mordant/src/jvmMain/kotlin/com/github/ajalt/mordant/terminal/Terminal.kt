package com.github.ajalt.mordant.terminal

import com.github.ajalt.mordant.components.Text
import com.github.ajalt.mordant.internal.renderLinesAnsi
import com.github.ajalt.mordant.rendering.*

interface Terminal {
    val theme: Theme
    val tabWidth: Int
    val info: TerminalInfo
    val colors: TerminalColors
    val cursor: TerminalCursor

    fun success(
            message: Any?,
            whitespace: Whitespace = Whitespace.PRE,
            align: TextAlign = TextAlign.NONE,
            overflowWrap: OverflowWrap = OverflowWrap.NORMAL,
            width: Int? = null
    ) {
        println(message, theme.style("success"), whitespace, align, overflowWrap, width)
    }

    fun danger(
            message: Any?,
            whitespace: Whitespace = Whitespace.PRE,
            align: TextAlign = TextAlign.NONE,
            overflowWrap: OverflowWrap = OverflowWrap.NORMAL,
            width: Int? = null
    ) {
        println(message, theme.style("danger"), whitespace, align, overflowWrap, width)
    }

    fun warning(
            message: Any?,
            whitespace: Whitespace = Whitespace.PRE,
            align: TextAlign = TextAlign.NONE,
            overflowWrap: OverflowWrap = OverflowWrap.NORMAL,
            width: Int? = null
    ) {
        println(message, theme.style("warning"), whitespace, align, overflowWrap, width)
    }

    fun renderWarning(
            message: Any?,
            whitespace: Whitespace = Whitespace.PRE,
            align: TextAlign = TextAlign.NONE,
            overflowWrap: OverflowWrap = OverflowWrap.NORMAL,
            width: Int? = null
    ): String {
        return render(message, theme.style("warning"), whitespace, align, overflowWrap, width)
    }

    fun info(
            message: Any?,
            whitespace: Whitespace = Whitespace.PRE,
            align: TextAlign = TextAlign.NONE,
            overflowWrap: OverflowWrap = OverflowWrap.NORMAL,
            width: Int? = null
    ) {
        println(message, theme.style("info"), whitespace, align, overflowWrap, width)
    }

    fun muted(
            message: Any?,
            whitespace: Whitespace = Whitespace.PRE,
            align: TextAlign = TextAlign.NONE,
            overflowWrap: OverflowWrap = OverflowWrap.NORMAL,
            width: Int? = null
    ) {
        println(message, theme.style("muted"), whitespace, align, overflowWrap, width)
    }

    fun print(
            message: Any?,
            style: TextStyle = DEFAULT_STYLE,
            whitespace: Whitespace = Whitespace.PRE,
            align: TextAlign = TextAlign.NONE,
            overflowWrap: OverflowWrap = OverflowWrap.NORMAL,
            width: Int? = null
    ) {
        rawPrint(render(message, style, whitespace, align, overflowWrap, width))
    }

    fun println(
            message: Any?,
            style: TextStyle = DEFAULT_STYLE,
            whitespace: Whitespace = Whitespace.PRE,
            align: TextAlign = TextAlign.NONE,
            overflowWrap: OverflowWrap = OverflowWrap.NORMAL,
            width: Int? = null
    ) {
        rawPrintln(render(message, style, whitespace, align, overflowWrap, width))
    }

    fun print(renderable: Renderable) {
        rawPrint(render(renderable))
    }

    fun println(renderable: Renderable) {
        rawPrintln(render(renderable))
    }

    fun renderSuccess(
            message: Any?,
            whitespace: Whitespace = Whitespace.PRE,
            align: TextAlign = TextAlign.NONE,
            overflowWrap: OverflowWrap = OverflowWrap.NORMAL,
            width: Int? = null
    ): String {
        return render(message, theme.style("success"), whitespace, align, overflowWrap, width)
    }

    fun renderDanger(
            message: Any?,
            whitespace: Whitespace = Whitespace.PRE,
            align: TextAlign = TextAlign.NONE,
            overflowWrap: OverflowWrap = OverflowWrap.NORMAL,
            width: Int? = null
    ): String {
        return render(message, theme.style("danger"), whitespace, align, overflowWrap, width)
    }

    fun renderInfo(
            message: Any?,
            whitespace: Whitespace = Whitespace.PRE,
            align: TextAlign = TextAlign.NONE,
            overflowWrap: OverflowWrap = OverflowWrap.NORMAL,
            width: Int? = null
    ): String {
        return render(message, theme.style("info"), whitespace, align, overflowWrap, width)
    }

    fun renderMuted(
            message: Any?,
            whitespace: Whitespace = Whitespace.PRE,
            align: TextAlign = TextAlign.NONE,
            overflowWrap: OverflowWrap = OverflowWrap.NORMAL,
            width: Int? = null
    ): String {
        return render(message, theme.style("muted"), whitespace, align, overflowWrap, width)
    }

    fun render(
            message: Any?,
            style: TextStyle = DEFAULT_STYLE,
            whitespace: Whitespace = Whitespace.PRE,
            align: TextAlign = TextAlign.NONE,
            overflowWrap: OverflowWrap = OverflowWrap.NORMAL,
            width: Int? = null
    ): String {
        return when (message) {
            is Lines -> renderLinesAnsi(message, info.ansiLevel, info.ansiHyperLinks)
            is Renderable -> render(message)
            else -> render(Text(message.toString(), style, whitespace, align, overflowWrap, width))
        }
    }

    fun render(renderable: Renderable): String {
        return renderLinesAnsi(renderable.render(this), info.ansiLevel, info.ansiHyperLinks)
    }

    fun println()
    fun rawPrintln(message: String)
    fun rawPrint(message: String)
}

@Suppress("FunctionName")
fun Terminal(
        ansiLevel: AnsiLevel? = null,
        theme: Theme = Theme.Default,
        width: Int? = null,
        height: Int? = null,
        hyperlinks: Boolean? = null,
        tabWidth: Int = 8
): Terminal {
    return AnsiTerminal(ansiLevel, theme, width, height, hyperlinks, tabWidth)
}
