package common.base;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WriteException;

public class CustomCellExcel extends Label {

	private WritableCellFormat writableCellFormat;
	
	/**
	 * @Description: Constructor
	 * @param c
	 * @param r
	 * @param cont
	 */
	public CustomCellExcel(int c, int r, String cont) {
		super(c, r, cont);
		
		// Create font object
		WritableFont wfobj = new WritableFont(WritableFont.TIMES, 14, WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
		writableCellFormat = new WritableCellFormat(wfobj);
		try {
			writableCellFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setCellFormat(writableCellFormat);
	}
	
	/**
	 * @Description: set border for cell
	 * @param border
	 * @param borderLineStyle
	 */
	public void setBorder(Border border, BorderLineStyle borderLineStyle){
		try {
			writableCellFormat.setBorder(border, borderLineStyle);
		} catch (WriteException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @Description: set background for cell
	 * @param colour
	 */
	public void setBackground(Colour colour){
		try {
			writableCellFormat.setBackground(colour);
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * @Description: set wrap for cell
	 * @param wrap
	 */
	public void setWrap(boolean wrap){
		try {
			writableCellFormat.setWrap(wrap);
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * @Description: set bold for cell format
	 */
	public void setBold(){
		try {
			((WritableFont) writableCellFormat.getFont()).setBoldStyle(WritableFont.BOLD);
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * @Description: set vertical alignment for cell format
	 * @param va
	 */
	public void setVerticalAligment(VerticalAlignment va){
		try {
			writableCellFormat.setVerticalAlignment(va);
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * @Description: set alignment for cell format
	 * @param aligment
	 */
	public void setAlignment(Alignment aligment){
		try {
			writableCellFormat.setAlignment(aligment);
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
