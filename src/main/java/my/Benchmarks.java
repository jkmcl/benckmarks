package my;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

@Fork(1)
public class Benchmarks {

	@State(Scope.Thread)
	public static class ThreadState {
		public Instant instant = Instant.now();
		public Date date = Date.from(instant);
	}

	@Benchmark
	public String formatDateWithSimpleDateFormat(ThreadState state) {
		return (new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")).format(state.date);
	}

	// DateTimeFormatter is thread-safe
	private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu-MM-dd'T'HH:mm:ss.SSS")
			.withZone(ZoneId.systemDefault());

	@Benchmark
	public String formatInstantWithDateTimeFormatter(ThreadState state) {
		return dtf.format(state.instant);
	}

	@Benchmark
	public String formatDateWithDateTimeFormatter(ThreadState state) {
		return dtf.format(state.date.toInstant());
	}

}
