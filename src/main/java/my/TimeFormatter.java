package my;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class TimeFormatter {

	// DateTimeFormatter is thread-safe
	private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu-MM-dd'T'HH:mm:ss.SSS")
			.withZone(ZoneId.systemDefault());

	private TimeFormatter() {
	}

	public static String formatDateWithSimpleDateFormat(Date date) {
		return (new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")).format(date);
	}

	public static String formatDateWithDateTimeFormatter(Date date) {
		return dtf.format(date.toInstant());
	}

	public static String formatInstantWithDateTimeFormatter(Instant instant) {
		return dtf.format(instant);
	}

}
