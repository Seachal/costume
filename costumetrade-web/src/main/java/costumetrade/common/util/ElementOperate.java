package costumetrade.common.util;

import java.util.List;

/**
 * 元素对象操作
 * @author luchunlong
 * @date 2017-03-14
 */
public class ElementOperate {
	
	/**
     * 更新list内的元素
     * @param objList
     * @param oldObj
     * @param newObj
     * @return
     */
    public static List updateElement(List objList,Object oldObj,Object newObj){
    	int position=objList.indexOf(oldObj);
    	objList.remove(position);
    	objList.add(position, newObj);

    	return objList;
    }
	
}