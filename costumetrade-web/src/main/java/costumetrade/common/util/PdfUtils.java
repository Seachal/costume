/**
 * Copyright (C) 2014-2017, Hrfax and/or its affiliates. All rights reserved.
 * Hrfax PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package costumetrade.common.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.SystemUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

/**
 * @author zhouyq
 * @Date 2017年3月9日
 */
public class PdfUtils {

	public static final String path;

	static {
		if (SystemUtils.IS_OS_WINDOWS) {
			path = "C:/cardry/pdf";
		} else {
			path = "/home/cardry/pdf";
		}
		File dir = new File(path);
		if (!dir.exists()) {
			dir.mkdirs();
		}
	}
	
	
	public static File createImagePdfFile(String urlString) {
		
		File  tempFile = new File(path+File.separator+UUID.randomUUID().toString()+".pdf");
		try(PDDocument doc = createImagePdf(urlString);){
			doc.save(tempFile);
		} catch (IOException e) {
			throw new RuntimeException("生成pdf临时文件失败", e);
		}
		return tempFile;
	}

	public static PDDocument createImagePdf(String urlString) {
		

		File file = null;
		try {
			URL url = new URL(urlString);
			file = new File(path + url.getFile());
			FileUtils.copyURLToFile(url, file);
			return createImagePdf(file);

		} catch (IOException e) {
			throw new RuntimeException("生成PDF异常", e);
		} finally {
			if (file != null) {
				file.delete();
			}
		}

	}

	public static PDDocument createImagePdf(File file) {

		PDPageContentStream contentStream = null;
		try {
			BufferedImage bimg = ImageIO.read(file);
			PDDocument document = new PDDocument();
			PDPage page = new PDPage(new PDRectangle(bimg.getWidth(), bimg.getHeight()));
			document.addPage(page);
			PDImageXObject image = PDImageXObject.createFromFileByExtension(file, document);
			contentStream = new PDPageContentStream(document, page);
			contentStream.drawImage(image, 0, 0);
			return document;
		} catch (IOException e) {
			throw new RuntimeException("生成图片PDF异常", e);
		} finally {
			IOUtils.closeQuietly(contentStream);
		}

	}

}
