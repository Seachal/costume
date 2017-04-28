/*
 * huirong Inc.
 * Copyright (c) 2017 All Rights Reserved.
 * Author     :liyb
 * Create Date:2017年3月21日
 */
package costumetrade.common.util;

import java.io.File;

/**
 * This is Class Description...
 * @author liyb
 * @version FileUtils.java,2017年3月21日 下午3:21:45
 */
public class FileUtil {

    /**
     * 删除文件
     * @param file
     */
    public static void deleteFile(File file) {
        if (file.isFile()) {//表示该文件不是文件夹  
            file.delete();
        } else {
            //首先得到当前的路径  
            String[] childFilePaths = file.list();
            for (String childFilePath : childFilePaths) {
                File childFile = new File(file.getAbsolutePath() + "\\" + childFilePath);
                deleteFile(childFile);
            }
            file.delete();
        }
    }
}
