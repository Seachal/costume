/*
 * huirong Inc.
 * Copyright (c) 2015 All Rights Reserved.
 * Author     :liyb
 * Create Date:2015年12月14日
 */
package costumetrade.common.execl;

/**
 * This is Class Description...
 * @author liyb
 * @version ExeclCell.java,2015年12月14日 下午1:00:06
 */
public class ExeclCell {

    private int col;//列
    private int row;//行
    private Object newValue;//值
    public int getCol() {
        return col;
    }
    public void setCol(int col) {
        this.col = col;
    }
    public int getRow() {
        return row;
    }
    public void setRow(int row) {
        this.row = row;
    }
    public Object getNewValue() {
        return newValue;
    }
    public void setNewValue(Object newValue) {
        this.newValue = newValue;
    }
}
