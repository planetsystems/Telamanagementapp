package com.planetsystems.tela.managementapp.client.presenter.subjectcategory;

import com.planetsystems.tela.managementapp.client.widget.ComboBox;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.layout.HLayout;

public class FilterSubjectsPane extends HLayout {

	private ComboBox categoryCombo;
	
	public FilterSubjectsPane() {
		super();

		categoryCombo = new ComboBox();
		categoryCombo.setTitle("Category");
		categoryCombo.setHint("Select Subject Category");
		categoryCombo.setShowHintInField(true);
		
		
		DynamicForm form = new DynamicForm();
		form.setFields(categoryCombo);
		form.setWrapItemTitles(false);
		form.setMargin(10);
		form.setCellPadding(10);
		form.setNumCols(4);
		form.setColWidths("50" , "150" , "50" , "150");
		
		
		this.addMember(form);
		this.setAutoHeight();
		this.setWidth100();
		//this.setBorder("1px solid green");
	}
	
	

	public ComboBox getCategoryCombo() {
		return categoryCombo;
	}


}
