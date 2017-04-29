/**
 * $RCSfile: CacheTimer.java,v $
 * $Revision: 1.1.1.1 $
 * $Date: 2002/09/09 13:51:09 $
 *
 * New Jive  from Jdon.com.
 *
 * This software is the proprietary information of CoolServlets, Inc.
 * Use is subject to license terms.
 */

package costumetrade.cache;

/**
 * Simple timer that keeps the currentTime variable of Cache accurate to one
 * second of the real clock time.
 */
public class CacheTimer extends Thread {

    private static long updateInterval;
    public static long currentTime;

    //Statically start a timing thread with 1 second of accuracy.
    static {
        new CacheTimer(1000);
    }

    /**
     * Creates a new CacheTimer object. The currentTime of Cache will be
     * updated at the specified update interval.
     *
     * @param updateInterval the interval in milleseconds that updates should
     *    be done.
     */
    public CacheTimer(long updateInterval) {
        this.updateInterval = updateInterval;
        //Make the timer be a daemon thread so that it won't keep the VM from
        //shutting down if there are no other threads.
        this.setDaemon(true);
        //Start the timer thread.
        start();
    }

    public void run() {
        //Run the timer indefinetly.
        while (true) {
            currentTime = System.currentTimeMillis();
            Cache.currentTime = currentTime;
            LongCache.currentTime = currentTime;
            try {
                sleep(updateInterval);
            }
            catch (InterruptedException ie) { }
        }
    }
}