package pgdp.ds;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MultiStack {

	private final Stack stacks;
	private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

	public MultiStack() {
		stacks = new Stack(1);
	}

	public void push(int val) {
		lock.writeLock().lock();
		try {
			stacks.push(val);
		} finally {
			lock.writeLock().unlock();
		}
	}

	public int pop() {
		lock.writeLock().lock();
		try {
			if (stacks.isEmpty()) {
				return Integer.MIN_VALUE;
			}
			return stacks.pop();
		} finally {
			lock.writeLock().unlock();
		}
	}

	public int top() {
		lock.readLock().lock();
		try {
			if (stacks.isEmpty()) {
				return Integer.MIN_VALUE;
			}
			return stacks.top();
		} finally {
			lock.readLock().unlock();
		}
	}

	public int size() {
		lock.readLock().lock();
		try {
			if (stacks.isEmpty()) {
				return 0;
			}
			return stacks.size();
		} finally {
			lock.readLock().unlock();
		}
	}

	public int search(int element) {
		lock.readLock().lock();
		try {
			if (stacks.isEmpty()) {
				return -1;
			}
			return stacks.search(element);
		} finally {
			lock.readLock().unlock();
		}
	}

	@Override
	public String toString() {
		lock.readLock().lock();
		try {
			return stacks.toString();
		} finally {
			lock.readLock().unlock();
		}
	}
}
