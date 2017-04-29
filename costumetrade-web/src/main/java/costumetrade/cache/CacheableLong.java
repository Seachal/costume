/**
 * $RCSfile: CacheableLong.java,v $
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
 * Wrapper for long values so they can be treated as Cacheable objects.
 */
public class CacheableLong implements Cacheable {

    /**
     * Wrapped long object.
     */
    private long longValue;

    /**
     * Creates a new CacheableLong.
     *
     * @param string the Long object to wrap.
     */
    public CacheableLong(long longValue) {
        this.longValue = longValue;
    }

    /**
     * Returns the Long wrapped by the CacheableLong object.
     *
     * @return the Long object.
     */
    public long getLong() {
        return longValue;
    }

    //FROM THE CACHEABLE INTERFACE//

    public int getSize() {
        return CacheSizes.sizeOfLong();
    }
}
