package com.robert.jvm.cocurrent.lock.jenkov;

public class ReentrantLock {

	boolean isLocked = false;
	Thread lockedBy = null;

	int lockedCount = 0;

	public synchronized void lock() throws InterruptedException {
		Thread callingThread = Thread.currentThread();
		while (isLocked && lockedBy != callingThread) {
			wait();
		}

		isLocked = true;
		lockedBy = callingThread;

		lockedCount++;
	}

	public synchronized void unlock() {
		if (Thread.currentThread() == this.lockedBy) {
			lockedCount--;

			if (lockedCount == 0) {
				isLocked = false;
				notify();
			}
		}
	}

}
