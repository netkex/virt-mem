import org.jfree.chart.ChartFactory
import org.jfree.chart.ChartPanel
import org.jfree.chart.JFreeChart
import org.jfree.chart.block.BlockBorder
import org.jfree.chart.plot.PlotOrientation
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer
import org.jfree.chart.title.TextTitle
import org.jfree.data.xy.XYDataset
import org.jfree.data.xy.XYSeries
import org.jfree.data.xy.XYSeriesCollection
import java.awt.BasicStroke
import java.awt.Color
import java.awt.EventQueue
import java.awt.Font
import javax.swing.BorderFactory
import javax.swing.JFrame


/**
 * drawing chart class
 * @param [argsFIFO] dataset of FIFO algorithm
 * @param [argsLRU]  dataset of LRU algorithm
 * @param [argsOPT] dataset of OPT algorithm
 * @param [maintitle] chart window name
 * @param [xlabel] name for the abscissa axis
 * @param [ylabel] names for the ordinate axis
 */
private class LineChart(argsFIFO: List<Double>, argsLRU: List<Double>, argsOPT: List<Double>,
                        maintitle: String, name: String, xlabel: String, ylabel: String) : JFrame() {
    private fun initUI(argsFIFO: List<Double>, argsLRU: List<Double>, argsOPT: List<Double>,
                       maintitle: String, name: String, xlabel: String, ylabel: String) {
        val dataset = createDataset(argsFIFO, argsLRU, argsOPT)
        val chart = createChart(dataset, xlabel, ylabel, name)
        val chartPanel = ChartPanel(chart)
        chartPanel.border = BorderFactory.createEmptyBorder(15, 15, 15, 15)
        chartPanel.background = Color.white
        add(chartPanel)
        pack()
        title = maintitle
        setLocationRelativeTo(null)
        defaultCloseOperation = EXIT_ON_CLOSE
    }

    /**
     * transform [argsFIFO], [argsLRU], [argsOPT] to one dataset
     * @return XYDataset
     */
    private fun createDataset(argsFIFO: List<Double>, argsLRU: List<Double>, argsOPT: List<Double>): XYDataset {
        val series1 = XYSeries("FIFO")
        for (i in argsFIFO.indices) {
            series1.add(i.toDouble(), argsFIFO[i])
        }

        val series2 = XYSeries("LRU")
        for (i in argsLRU.indices) {
            series2.add(i.toDouble(), argsLRU[i])
        }

        val series3 = XYSeries("OPT")
        for (i in argsOPT.indices) {
            series3.add(i.toDouble(), argsOPT[i])
        }
        val dataset = XYSeriesCollection()
        dataset.addSeries(series1)
        dataset.addSeries(series2)
        dataset.addSeries(series3)
        return dataset
    }

    private fun createChart(dataset: XYDataset, xlabel: String, ylabel: String, name:String): JFreeChart {
        val chart = ChartFactory.createXYLineChart(
                "",
                xlabel,
                ylabel,
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        )
        val plot = chart.plot
        val renderer = XYLineAndShapeRenderer()
        renderer.setSeriesPaint(0, Color.RED) //set color to line
        renderer.setSeriesStroke(0, BasicStroke(2.0f))
        renderer.setSeriesPaint(1, Color.BLUE)
        renderer.setSeriesStroke(1, BasicStroke(2.0f))
        renderer.setSeriesPaint(2, Color.GREEN)
        renderer.setSeriesStroke(2, BasicStroke(0.5f))
        plot.backgroundPaint = Color.white
        chart.legend.frame = BlockBorder.NONE
        chart.title = TextTitle(name,
                Font("Serif", Font.BOLD, 18)
        )
        return chart
    }

    init {
        initUI(argsFIFO, argsLRU, argsOPT, maintitle, name, xlabel, ylabel)
    }
}

/**
 * transform list of algorithm's actions [changes]  to list of number of swaps on each step
 */
fun toNumOfSwaps(changes: List<Int>): List<Double> {
    val ans = MutableList(changes.size) {0}
    for (i in changes.indices) {
        if(i > 0)
            ans[i] = ans[i - 1]
        if(changes[i] > 0)
            ans[i]++
    }
    return ans.map {it.toDouble()}
}

/**
 * transform list of algorithm's actions [changes] to list of effectivity on each step (in percent)
 */
fun toSwapPercent(changes: List<Int>): List<Double> {
    var swaps = 0 // number of swaps
    val ans = MutableList(changes.size) {.0}
    for (i in changes.indices) {
        if(changes[i] > 0)
            swaps++
        ans[i] = (i + 1 - swaps) * 100.0 / (i + 1)
    }

    return ans
}


/**
 * draw chart
 * @param [ansFIFO] FIFO algorithm actions with memory
 * @param [ansLRU]  LRU algorithm actions with memory
 * @param [ansOPT] OPT algorithm actions with memory
 * @param [inputFile] name of inputFile (to give name to window)
 * @param [chartFlag] shows what kind of charts should be drawn
 */
fun draw(ansFIFO: List<Int>, ansLRU: List<Int>, ansOPT: List<Int>, inputFile: String, chartFlag: Int) {

    if(chartFlag == 1 || chartFlag == 3) {
        EventQueue.invokeLater {
            val num = LineChart(toNumOfSwaps(ansFIFO), toNumOfSwaps(ansLRU), toNumOfSwaps(ansOPT),
                    "chart of $inputFile: swaps", "", "number of swaps", "number of hints")
            num.isVisible = true
        }
    }

    if(chartFlag == 2 || chartFlag == 3) {
        EventQueue.invokeLater {
            val effectivity = LineChart(toSwapPercent(ansFIFO), toSwapPercent(ansLRU), toSwapPercent(ansOPT),
                    "chart of $inputFile: effectivity", "effectivity", "% of swaps", "number of hints")
            effectivity.isVisible = true
        }
    }
}