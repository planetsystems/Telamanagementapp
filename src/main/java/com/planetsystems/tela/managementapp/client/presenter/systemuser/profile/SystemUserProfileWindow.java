package com.planetsystems.tela.managementapp.client.presenter.systemuser.profile;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.planetsystems.tela.managementapp.client.widget.ComboBox;
import com.planetsystems.tela.managementapp.shared.DatePattern;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.RadioGroupItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.validator.MatchesFieldValidator;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class SystemUserProfileWindow extends Window {

//	private TextItem firstNameField;
//	private TextItem lastNameField;
//	private TextItem phoneNumberField;
	private TextItem emailField;
//	private DateItem dobItem;
//	private TextItem nationalIdField;
//	private ComboBox genderCombo;
//	private TextItem nameAbbrevField;
	private RadioGroupItem enabledRadioGroupItem;

	private ComboBox systemUserGroupCombo;

	DateTimeFormat dateFormat = DateTimeFormat.getFormat(DatePattern.DAY_MONTH_YEAR.getPattern());

	private IButton saveButton;
	private IButton cancelButton;

	public SystemUserProfileWindow() {

		enabledRadioGroupItem = new RadioGroupItem();
		enabledRadioGroupItem.setTitle("Is User Enabled");
		enabledRadioGroupItem.setDefaultValue(true);


		MatchesFieldValidator validator = new MatchesFieldValidator();
		validator.setOtherField("confirmPassword");
		validator.setErrorMessage("Passwords do not match");
//
//		firstNameField = new TextItem();
//		firstNameField.setTitle("First Name");
//		firstNameField.setHint("First Name");
//		firstNameField.setShowHintInField(true);

//		lastNameField = new TextItem();
//		lastNameField.setTitle("Last Name");
//		lastNameField.setHint("Last Name");
//		lastNameField.setShowHintInField(true);

//		phoneNumberField = new TextItem();
//		phoneNumberField.setTitle("Phone Number");
//		phoneNumberField.setMask("#### ###-###");
//		phoneNumberField.setHint("#### ###-###");
//		phoneNumberField.setShowHintInField(true);

		emailField = new TextItem();
		emailField.setTitle("Email");
		emailField.setHint("Email");
		emailField.setShowHintInField(true);

//		dobItem = new DateItem();
//		dobItem.setTitle("DOB");
//		dobItem.setStartDate(dateFormat.parse("01/01/1996"));

//		nationalIdField = new TextItem();
//		nationalIdField.setTitle("National Id");
//		nationalIdField.setHint("NIN");
//		nationalIdField.setShowHintInField(true);

//		nameAbbrevField = new TextItem();
//		nameAbbrevField.setTitle("Name Abbreviation");
//		nameAbbrevField.setHint("Name Abbreviation");
//		nameAbbrevField.setShowHintInField(true);
//		nameAbbrevField.hide();

//		genderCombo = new ComboBox();
//		genderCombo.setTitle("Gender");
//		genderCombo.setHint("Gender");
//		genderCombo.setShowHintInField(true);

		systemUserGroupCombo = new ComboBox();
		systemUserGroupCombo.setTitle("User Group");

		//nameAbbrevField
		DynamicForm form = new DynamicForm();
		form.setFields(emailField ,  enabledRadioGroupItem , systemUserGroupCombo);
		form.setWrapItemTitles(false);
		form.setCellPadding(10);
		form.setColWidths("150", "250" , "150", "250" , "150", "250" );

		saveButton = new IButton("Save");
		cancelButton = new IButton("Close");

		HLayout buttonLayout = new HLayout();
		buttonLayout.setMembers(cancelButton, saveButton);
		buttonLayout.setAutoHeight();
		buttonLayout.setAutoWidth();
		buttonLayout.setMembersMargin(4);
		buttonLayout.setLayoutAlign(Alignment.CENTER);

		VLayout layout = new VLayout();
		layout.addMember(form);
		layout.addMember(buttonLayout);
		layout.setMembersMargin(10);

		this.addItem(layout);
		this.setWidth("40%");
		this.setHeight("50%");
		this.setAutoCenter(true);
		this.setTitle("Create Profile");
		this.setIsModal(true);
		this.setShowModalMask(true);
		closeWindow(this);

	}

	private void closeWindow(final Window window) {
		cancelButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				window.close();
			}
		});
	}

	public TextItem getEmailField() {
		return emailField;
	}

	public RadioGroupItem getEnabledRadioGroupItem() {
		return enabledRadioGroupItem;
	}

	public ComboBox getSystemUserGroupCombo() {
		return systemUserGroupCombo;
	}

	public IButton getSaveButton() {
		return saveButton;
	}

	
	
	

	

}
