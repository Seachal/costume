package costumetrade.user.domain;

import java.util.List;

import costumetrade.common.Entity;

public class PatternPrice extends Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<PriceJson> colorLists;


	private List<PriceJson> sizeLists;


	public List<PriceJson> getColorLists() {
		return colorLists;
	}


	public void setColorLists(List<PriceJson> colorLists) {
		this.colorLists = colorLists;
	}


	public List<PriceJson> getSizeLists() {
		return sizeLists;
	}


	public void setSizeLists(List<PriceJson> sizeLists) {
		this.sizeLists = sizeLists;
	}
	
	
   
}