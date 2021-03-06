package com.planetsystems.tela.managementapp.client.presenter.schoolcategory;

import javax.inject.Inject;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;
import com.planetsystems.tela.managementapp.client.widget.ControlsPane;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;

class SchoolCategoryView extends ViewImpl implements SchoolCategoryPresenter.MyView {

	private static final String DEFAULT_MARGIN = "0px";
	private VLayout panel;
	private SchCategoryPane schoolCategoryPane;
	private SchoolPane schoolPane;
	private FilterSchoolsPane filterSchoolsPane;
	private SchoolClassPane schoolClassPane;
	private FilterSchoolClassPane filterSchoolClassPane;
	private ControlsPane controlsPane;
	private TabSet tabSet;
	
	public static final String SCHOOL_CATEGORY_TAB_TITLE = "School Categories";
	public static final String SCHOOL_TAB_TITLE = "Schools";
	public static final String SCHOOL_CLASSES_TAB_TITLE = "Classes";

    @Inject
    SchoolCategoryView() {
    	panel = new VLayout();
    	schoolCategoryPane = new SchCategoryPane();
    	schoolPane = new SchoolPane();
    	filterSchoolsPane = new FilterSchoolsPane();
    	
    	schoolClassPane = new SchoolClassPane();
    	filterSchoolClassPane = new FilterSchoolClassPane();
    	
    	controlsPane = new ControlsPane();
    	tabSet = new TabSet();
    	
    	Tab tab1 = new Tab();
    	tab1.setTitle(SCHOOL_TAB_TITLE);
    	tab1.setPane(schoolPane);
    	
    	Tab tab2 = new Tab();
    	tab2.setTitle(SCHOOL_CLASSES_TAB_TITLE);
    	tab2.setPane(schoolClassPane);
    	
    	Tab tab3 = new Tab();
    	tab3.setTitle(SCHOOL_CATEGORY_TAB_TITLE);
    	tab3.setPane(schoolCategoryPane);
    	
    	tabSet.addTab(tab1);
    	tabSet.addTab(tab2);
    	tabSet.addTab(tab3);
    	
    	VLayout filterLayout = new VLayout();
    	filterLayout.addMembers(filterSchoolsPane , filterSchoolClassPane);
    	filterLayout.setWidth100();
    	filterLayout.setHeight("10%");
//    	filterLayout.setBorder("1px solid red");
    	
    	panel.addMember(controlsPane);
    	panel.addMember(filterLayout);
    	panel.addMember(tabSet);
    	panel.setWidth100();
		panel.setHeight("90%");
		Window.enableScrolling(false);
		Window.setMargin(DEFAULT_MARGIN);
      
    }
    
    public Widget asWidget() {
		return panel;
	}

	public ControlsPane getControlsPane() {
		return controlsPane;
	}

	public TabSet getTabSet() {
		return tabSet;
	}

	public SchCategoryPane getSchoolCategoryPane() {
		return schoolCategoryPane;
	}

	public SchoolPane getSchoolPane() {
		return schoolPane;
	}

	public SchoolClassPane getSchoolClassPane() {
		return schoolClassPane;
	}

	public FilterSchoolsPane getFilterSchoolsPane() {
		return filterSchoolsPane;
	}

	public FilterSchoolClassPane getFilterSchoolClassPane() {
		return filterSchoolClassPane;
	}
	

	
	
	
	
	
	
    
    

}