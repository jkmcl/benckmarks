package my;

import java.time.Instant;
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
		return TimeFormatter.formatDateWithSimpleDateFormat(state.date);
	}

	@Benchmark
	public String formatDateWithDateTimeFormatter(ThreadState state) {
		return TimeFormatter.formatDateWithDateTimeFormatter(state.date);
	}

	@Benchmark
	public String formatInstantWithDateTimeFormatter(ThreadState state) {
		return TimeFormatter.formatInstantWithDateTimeFormatter(state.instant);
	}

}
