package com.planetsystems.tela.managementapp.client.presenter.systemuser.manage;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class SystemUserSchoolPane  extends VLayout{

	private IButton userSchoolButton;
	private IButton addButton;
	private IButton deleteButton;
	private SchoolDistrictListGrid schoolDistrictListGrid;
	
	
	public SystemUserSchoolPane() {
		super();
		
		HLayout controlButtonLayout = new HLayout();
        controlButtonLayout.setLayoutAlign(Alignment.RIGHT);
        controlButtonLayout.setAlign(Alignment.RIGHT);
        controlButtonLayout.setLayoutRightMargin(20);
        controlButtonLayout.setMembersMargin(5);
        controlButtonLayout.setAutoHeight();
        controlButtonLayout.setAutoWidth();
        
    	userSchoolButton = new IButton("Load User Schools");
    	addButton = new IButton("Add");
    	deleteButton = new IButton("Delete");
    	
    	controlButtonLayout.setMembers(userSchoolButton , deleteButton , addButton);
		
    	schoolDistrictListGrid = new SchoolDistrictListGrid();
    	schoolDistrictListGrid.setSelectionAppearance(SelectionAppearance.CHECKBOX);
    	schoolDistrictListGrid.setSelectionType(SelectionStyle.SIMPLE);
   
    	
        this.addMember(controlButtonLayout);
		this.addMember(schoolDistrictListGrid);
        this.setHeight100();
		this.setWidth100();
	}

	

	public IButton getUserSchoolButton() {
		return userSchoolButton;
	}



	public IButton getAddButton() {
		return addButton;
	}

	
	
	public SchoolDistrictListGrid getSchoolDistrictListGrid() {
		return schoolDistrictListGrid;
	}



	public IButton getDeleteButton() {
		return deleteButton;
	}
	
	
	
	

	
	
}
