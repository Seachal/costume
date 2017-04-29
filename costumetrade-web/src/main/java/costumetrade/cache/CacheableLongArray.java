/**
 * $RCSfile: CacheableLongArray.java,v $
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
 * Wrapper for long[] values so they can be treated as cacheable objects.
 */
public class CacheableLongArray implements Cacheable {

    /**
     * Wrapped long object.
     */
    private long[] longArray;

    /**
     * Creates a new CacheableLong.
     *
     * @param string the Long object to wrap.
     */
    public CacheableLongArray(long[] longArray) {
        this.longArray = longArray;
    }

    /**
     * Returns the long [] wrapped by the CacheableLongArray object.
     *
     * @return the long [] object.
     */
    public long[] getLongArray() {
        return longArray;
    }

    //FROM THE CACHEABLE INTERFACE//

    public int getSize() {
        return CacheSizes.sizeOfObject() + CacheSizes.sizeOfLong() * longArray.length;
    }
}