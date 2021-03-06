package com.planetsystems.tela.managementapp.client.presenter.academicyear.term;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.layout.VLayout;

public class AcademicTermPane extends VLayout {

	private AcademicTermListGrid listGrid;
	
	public AcademicTermPane() {
		super();
		Label header = new Label();
		this.setWidth100();

		header.setStyleName("crm-ContextArea-Header-Label");
		header.setContents("Academic Terms");
		header.setWidth("100%");
		header.setAutoHeight();
		header.setMargin(10);
		header.setAlign(Alignment.LEFT);

		listGrid = new AcademicTermListGrid();

		VLayout layout = new VLayout();
		//layout.addMember(header);
		layout.addMember(listGrid);
		this.addMember(layout);
	}

	public AcademicTermListGrid getListGrid() {
		return listGrid;
	}
	
	

}
