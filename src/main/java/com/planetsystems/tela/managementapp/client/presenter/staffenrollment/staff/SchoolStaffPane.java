/**
 * 
 */
package com.planetsystems.tela.managementapp.client.presenter.staffenrollment.staff;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author wanfadger
 *
 */
public class SchoolStaffPane extends VLayout {
	private SchoolStaffListGrid schoolStaffListGrid;

	private VLayout headerinfor;

	public SchoolStaffPane() {
		super();

		Label header = new Label();

		header.setStyleName("crm-ContextArea-Header-Label");
		header.setContents("Teachers");
		header.setWidth("100%");
		header.setAutoHeight();
		header.setMargin(10);
		header.setAlign(Alignment.LEFT);

		schoolStaffListGrid = new SchoolStaffListGrid();

		headerinfor = new VLayout();
		headerinfor.setAutoHeight();

		this.addMember(headerinfor);
		this.addMember(schoolStaffListGrid);

	}

	public SchoolStaffListGrid getSchoolStaffListGrid() {
		return schoolStaffListGrid;
	}

	public VLayout getHeaderinfor() {
		return headerinfor;
	}

}
