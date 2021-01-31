package ru.job4j.switcher;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicBoolean;

@ThreadSafe
public class MasterSlaveBarrier {
    @GuardedBy("this")
    private AtomicBoolean isMaster = new AtomicBoolean(false);

    public synchronized void tryMaster() {
        while (isMaster.get()) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public synchronized void trySlave() {
        while (!isMaster.get()) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public synchronized void doneMaster() {
        System.out.println(Thread.currentThread().getName());
        isMaster.set(true);
        notifyAll();
    }

    public synchronized void doneSlave() {
        System.out.println(Thread.currentThread().getName());
        isMaster.set(false);
        notifyAll();
    }
}
