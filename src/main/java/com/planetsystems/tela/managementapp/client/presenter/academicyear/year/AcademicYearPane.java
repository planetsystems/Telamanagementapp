package com.planetsystems.tela.managementapp.client.presenter.academicyear.year;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.layout.VLayout;

public class AcademicYearPane extends VLayout {
	
	private AcademicYearListGrid listGrid;
	
	public AcademicYearPane() {
		super();
		Label header = new Label();
		
		header.setStyleName("crm-ContextArea-Header-Label");
		header.setContents("Academic Years");
		header.setWidth("100%");
		header.setAutoHeight();
		header.setMargin(10);
		header.setAlign(Alignment.LEFT);

		listGrid = new AcademicYearListGrid();

		VLayout layout = new VLayout();
		//layout.addMember(header);
		layout.addMember(listGrid);
		this.addMember(layout);
		
	}

	public AcademicYearListGrid getListGrid() {
		return listGrid;
	}

	

}
