package lab_rab.lab2.threads;

public class Thread21 implements Runnable {
    private TransportSynchronizer transportSynchronizer;

    public Thread21 (TransportSynchronizer transportSynchronizer) {
        this.transportSynchronizer = transportSynchronizer;
    }

    public void run() {
        try {
            while(transportSynchronizer.canPrintModel()) {
                transportSynchronizer.printModel();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
