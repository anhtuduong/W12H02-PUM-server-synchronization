package pgdp.ds;

import java.util.Arrays;
import java.util.concurrent.Semaphore;

public class RingBuffer {

	private int[] mem;
	private int in;
	private int out;
	private int stored;
	private Semaphore semaphore;

	public RingBuffer(int capacity) {
		mem = new int[capacity];
		in = 0;
		out = 0;
		stored = 0;
		semaphore = new Semaphore(capacity);
	}

	public boolean isEmpty() {
		return stored == 0;
	}

	public boolean isFull() {
		return stored == mem.length;
	}

	public void put(int val) throws InterruptedException {
		semaphore.acquire();
		try {
			mem[in++] = val;
			in %= mem.length;
			stored++;
		} finally {
			semaphore.release();
		}
	}

	public int get() throws InterruptedException {
		semaphore.acquire();
		try {
			if (isEmpty()) {
				return Integer.MIN_VALUE;
			}
			int val = mem[out++];
			out %= mem.length;
			stored--;
			return val;
		} finally {
			semaphore.release();
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("RingBuffer := { capacity = ").append(mem.length).append(", out = ").append(out).append(", in = ")
				.append(in).append(", stored = ").append(stored).append(", mem = ").append(Arrays.toString(mem))
				.append(", buffer = [");
		if (!isEmpty()) {
			if (in >= 0 || in < mem.length) {
				int i = out;
				do {
					sb.append(mem[i]).append(", ");
					i = (i + 1) % mem.length;
				} while (i != in);
				sb.setLength(sb.length() - 2);
			} else {
				sb.append("Error: Field 'in' is <").append(in)
						.append(">, which is out of bounds for an array of length ").append(mem.length);
			}
		}
		sb.append("] }");
		return sb.toString();
	}
}