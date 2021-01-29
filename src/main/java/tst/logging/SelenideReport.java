package tst.logging;


import com.codeborne.selenide.logevents.EventsCollector;
import com.codeborne.selenide.logevents.LogEvent;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.codeborne.selenide.logevents.SimpleReport;
import com.google.common.base.Joiner;

import java.util.Collections;
import java.util.Iterator;
import java.util.OptionalInt;
import java.util.logging.Logger;

public class SelenideReport extends SimpleReport {
    private  static final Logger log = Logger.getLogger(SimpleReport.class.getName());
    private EventsCollector logEventListener;

    public void start() {
        SelenideLogger.addListener("customReport", new EventsCollector());
    }

    public void finish(String title) {

        EventsCollector logEventListener = (EventsCollector)SelenideLogger.removeListener("customReport");
        if (logEventListener == null) {
            log.warning("Can not publish report because Selenide logger has not started.");
        } else {
            OptionalInt maxLineLength = logEventListener.events().stream().map(LogEvent::getElement).map(String::length).mapToInt(Integer::intValue).max();
            int count = maxLineLength.orElse(0) >= 20 ? maxLineLength.getAsInt() + 1 : 20;
            StringBuilder sb = new StringBuilder();
            sb.append("Report for the Test: ").append(title).append('\n');
            String delimiter = '+' + Joiner.on('+').join(this.line(count), this.line(70), new Object[]{this.line(10), this.line(10)}) + "+\n";
            sb.append(delimiter);
            sb.append(String.format("|%-" + count + "s|%-70s|%-10s|%-10s|%n", "Element", "Subject", "Status", "ms."));
            sb.append(delimiter);
            Iterator var7 = logEventListener.events().iterator();

            while(var7.hasNext()) {
                LogEvent e = (LogEvent)var7.next();
                sb.append(String.format("|%-" + count + "s|%-70s|%-10s|%-10s|%n", e.getElement(), e.getSubject(), e.getStatus(), e.getDuration()));
            }

            sb.append(delimiter);
            log.info(sb.toString());
            AllureHelpers.attachText(sb.toString());
        }

    }
    public void clean() {
        SelenideLogger.removeListener("customReport");
    }

    private String line(int count) {
        return Joiner.on("").join(Collections.nCopies(count, "-"));
    }
}