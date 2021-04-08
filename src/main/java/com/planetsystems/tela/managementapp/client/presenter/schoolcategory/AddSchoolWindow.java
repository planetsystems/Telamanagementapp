package com.planetsystems.tela.managementapp.client.presenter.schoolcategory;

import com.planetsystems.tela.managementapp.client.widget.ComboBox;
import com.planetsystems.tela.managementapp.client.widget.TextField;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class AddSchoolWindow extends Window {

	private TextField schoolCode;
	private TextField schoolName;
	private ComboBox schoolCategoryCombo;
	private ComboBox districtCombo;
	private ComboBox regionCombo;

	private TextField latitude;
	private TextField longtitude;
	
	private TextField deviceNumber;
	

	private IButton saveButton;
	private IButton cancelButton;



	public AddSchoolWindow() {
		super();
		schoolCode = new TextField();
		schoolCode.setTitle("Code");

		schoolName = new TextField();
		schoolName.setTitle("Name");

		schoolCategoryCombo = new ComboBox();
		schoolCategoryCombo.setTitle("Category");

		latitude = new TextField();
		latitude.setTitle("Latitude");

		longtitude = new TextField();
		longtitude.setTitle("Longtitude");

		districtCombo = new ComboBox();
		districtCombo.setTitle("District");
		
		regionCombo = new ComboBox();
		regionCombo.setTitle("Region");
		
		deviceNumber= new TextField();
		deviceNumber.setTitle("Device Number");
	

		saveButton = new IButton("Save");
		
		cancelButton = new IButton("Cancel");
		cancelButton.setBaseStyle("cancel-button");

		DynamicForm form = new DynamicForm();
		form.setFields(schoolCategoryCombo , regionCombo , districtCombo , schoolCode, schoolName , latitude, longtitude,deviceNumber);
		form.setWrapItemTitles(false);
		form.setMargin(10);
		form.setColWidths("150","250");
		form.setCellPadding(10);

		HLayout buttonLayout = new HLayout();
		buttonLayout.setMembers(cancelButton , saveButton);
		buttonLayout.setAutoHeight();
		buttonLayout.setAutoWidth();
		buttonLayout.setMargin(5);
		buttonLayout.setMembersMargin(4);
		buttonLayout.setLayoutAlign(Alignment.CENTER);

		VLayout layout = new VLayout();
		layout.addMember(form);
		layout.addMember(buttonLayout);

		layout.setMargin(10);
		this.addItem(layout);
		this.setWidth("40%");
		this.setHeight("80%");
		this.setAutoCenter(true);
		this.setTitle("Add School");
		this.setIsModal(true);
		this.setShowModalMask(true);
		cancel(this);
	}

	private void cancel(final Window window) {
		cancelButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				window.close();
			}
		});
	}
	
	public TextField getSchoolCode() {
		return schoolCode;
	}

	public TextField getSchoolName() {
		return schoolName;
	}

	public TextField getLatitude() {
		return latitude;
	}

	public TextField getLongtitude() {
		return longtitude;
	}

	public TextField getDeviceNumber() {
		return deviceNumber;
	}



	public IButton getSaveButton() {
		return saveButton;
	}

	public ComboBox getSchoolCategoryCombo() {
		return schoolCategoryCombo;
	}

	public ComboBox getDistrictCombo() {
		return districtCombo;
	}

	public ComboBox getRegionCombo() {
		return regionCombo;
	}

	

}