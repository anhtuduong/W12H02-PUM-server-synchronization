package pgdp.ds;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Stack {
	private Stack next;
	private final int[] mem;
	private int top;

	public Stack(int capacity) {
		next = null;
		mem = new int[capacity];
		top = -1;
	}

	public boolean isEmpty() {
		return top == -1;
	}

	public boolean isFull() {
		return top == mem.length - 1;
	}

	public void push(int val) {
		if (isFull()) {
			if (next == null) {
				next = new Stack(mem.length * 2);
			}
			next.push(val);
		} else {
			mem[++top] = val;
		}
	}

	public int pop() {
		if (next != null) {
			final int pop = next.pop();
			if (next.isEmpty()) {
				next = null;
			}
			return pop;
		}
		return mem[top--];
	}

	public int top() {
		if (next != null) {
			return next.top();
		}
		return mem[top];
	}

	public int size() {
		int size = top + 1;
		if (next != null) {
			size += next.size();
		}
		return size;
	}

	public int search(int element) {
		int pos = -1;
		if (next != null) {
			pos = next.search(element);
		}
		if (pos != -1) {
			return pos;
		}
		for (int i = top; i >= 0; i--) {
			if (mem[i] == element) {
				return top - i + (next != null ? next.size() : 0) + 1;
			}
		}
		return -1;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("{\ncapacity = ").append(mem.length).append(",\n");
		sb.append("mem = [")
				.append(IntStream.range(0, top + 1).mapToObj(x -> "" + mem[x]).collect(Collectors.joining(", ")))
				.append("],\n");
		sb.append("next = ").append(next == null ? "null" : "\n" + next.toString()).append("\n}\n");
		return sb.toString();
	}
}
