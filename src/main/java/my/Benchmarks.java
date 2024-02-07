package my;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Fork;

@Fork(1)
public class Benchmarks {

	@Benchmark
	public String formatDateWithSimpleDateFormat() {
		return (new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")).format(new Date());
	}

	// DateTimeFormatter is thread-safe
	private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu-MM-dd'T'HH:mm:ss.SSS")
			.withZone(ZoneId.systemDefault());

	@Benchmark
	public String formatInstantWithDateTimeFormatter() {
		return dtf.format(Instant.now());
	}

	@Benchmark
	public String formatDateWithDateTimeFormatter() {
		return dtf.format((new Date()).toInstant());
	}

}
