package lab_rab.lab2.threads;

public class Thread22 implements Runnable {
    private TransportSynchronizer transportSynchronizer;

    public Thread22 (TransportSynchronizer transportSynchronizer) {
        this.transportSynchronizer = transportSynchronizer;
    }

    public void run() {
        try {
            while(transportSynchronizer.canPrintPrice()) {
                transportSynchronizer.printPrice();
           }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
