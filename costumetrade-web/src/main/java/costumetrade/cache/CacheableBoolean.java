/**
 * $RCSfile: CacheableBoolean.java,v $
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
 * Wrapper for boolean values so they can be treated as Cacheable objects.
 */
public class CacheableBoolean implements Cacheable {

    /**
     * Wrapped int value.
     */
    private boolean booleanValue;

    /**
     * Creates a new CacheableBoolean.
     *
     * @param string the boolean value to wrap.
     */
    public CacheableBoolean(boolean booleanValue) {
        this.booleanValue = booleanValue;
    }

    /**
     * Returns the underlying boolean value.
     *
     * @return the boolean value.
     */
    public boolean getBoolean() {
        return booleanValue;
    }

    //FROM THE CACHEABLE INTERFACE//

    public int getSize() {
        return CacheSizes.sizeOfObject() + CacheSizes.sizeOfBoolean();
    }
}