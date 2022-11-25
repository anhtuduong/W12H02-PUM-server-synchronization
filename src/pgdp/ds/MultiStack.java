package pgdp.ds;

public class MultiStack {

	private final Stack stacks;

	public MultiStack() {
		stacks = new Stack(1);
	}

	public void push(int val) {
		stacks.push(val);
	}

	public int pop() {
		if (stacks.isEmpty()) {
			return Integer.MIN_VALUE;
		}
		return stacks.pop();
	}

	public int top() {
		if (stacks.isEmpty()) {
			return Integer.MIN_VALUE;
		}
		return stacks.top();
	}

	public int size() {
		if (stacks.isEmpty()) {
			return 0;
		}
		return stacks.size();
	}

	public int search(int element) {
		if (stacks.isEmpty()) {
			return -1;
		}
		return stacks.search(element);
	}

	@Override
	public String toString() {
		return stacks.toString();
	}
}
