package com.planetsystems.tela.managementapp.client.presenter.subjectcategory.subject;

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

public class SubjectWindow extends Window {

	private TextField codeField;
	private TextField nameField;
	private ComboBox subjectCategoryCombo;

	private IButton saveButton;
	private IButton cancelButton;

	public SubjectWindow() {
		super();
		codeField = new TextField();
		codeField.setTitle("Code");
		codeField.setHint("code");
		codeField.setShowHintInField(true);

		nameField = new TextField();
		nameField.setTitle("Subject");
		nameField.setHint("subject");
		nameField.setShowHintInField(true);

		subjectCategoryCombo = new ComboBox();
		subjectCategoryCombo.setTitle("Category");
		subjectCategoryCombo.setHint("category");
		subjectCategoryCombo.setShowHintInField(true);

		saveButton = new IButton("Save");
		
		cancelButton = new IButton("Cancel");
		cancelButton.setBaseStyle("cancel-button");

		DynamicForm form = new DynamicForm();
		form.setFields(subjectCategoryCombo , codeField, nameField);
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
		this.setHeight("50%");
		this.setAutoCenter(true);
		this.setTitle("Subject");
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

	


	public TextField getCodeField() {
		return codeField;
	}

	public TextField getNameField() {
		return nameField;
	}

	public ComboBox getSubjectCategoryCombo() {
		return subjectCategoryCombo;
	}

	public IButton getSaveButton() {
		return saveButton;
	}


	
	
}
