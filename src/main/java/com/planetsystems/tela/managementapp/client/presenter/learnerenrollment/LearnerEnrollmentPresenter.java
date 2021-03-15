package com.planetsystems.tela.managementapp.client.presenter.learnerenrollment;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rpc.shared.DispatchAsync;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ContentSlot;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;
import com.planetsystems.tela.managementapp.client.presenter.main.MainPresenter;
import com.planetsystems.tela.managementapp.client.presenter.staffenrollment.FilterStaffHeadCountWindow;
import com.planetsystems.tela.managementapp.client.presenter.staffenrollment.FilterStaffWindow;
import com.planetsystems.tela.managementapp.client.widget.ControlsPane;
import com.planetsystems.tela.managementapp.client.widget.MenuButton;
import com.planetsystems.tela.managementapp.client.widget.SwizimaLoader;
import com.planetsystems.tela.managementapp.shared.DatePattern;
import com.planetsystems.tela.managementapp.shared.RequestAction;
import com.planetsystems.tela.managementapp.shared.RequestConstant;
import com.planetsystems.tela.managementapp.shared.RequestResult;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.HoverEvent;
import com.smartgwt.client.widgets.events.HoverHandler;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.menu.Menu;
import com.smartgwt.client.widgets.menu.MenuItem;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;
import com.planetsystems.tela.dto.AcademicTermDTO;
import com.planetsystems.tela.dto.AcademicYearDTO;
import com.planetsystems.tela.dto.DistrictDTO;
import com.planetsystems.tela.dto.LearnerEnrollmentDTO;
import com.planetsystems.tela.dto.SchoolClassDTO;
import com.planetsystems.tela.dto.SchoolDTO;
import com.planetsystems.tela.dto.SystemFeedbackDTO;
import com.planetsystems.tela.managementapp.client.gin.SessionManager;
import com.planetsystems.tela.managementapp.client.place.NameTokens;
public class LearnerEnrollmentPresenter extends Presenter<LearnerEnrollmentPresenter.MyView, LearnerEnrollmentPresenter.MyProxy>  {
    interface MyView extends View  {
    LearnerEnrollementPane	getLearnerEnrollementPane();
    ControlsPane getControlsPane();
    }
    
    @SuppressWarnings("deprecation")
	@ContentSlot
    public static final Type<RevealContentHandler<?>> SLOT_LearnerEnrollment = new Type<RevealContentHandler<?>>();

    @Inject
    private DispatchAsync dispatcher;
    
    @Inject
    private PlaceManager placeManager;
    
//	DateTimeFormat dateFormat = DateTimeFormat.getFormat("dd/MM/yyyy");
	DateTimeFormat dateTimeFormat = DateTimeFormat.getFormat(DatePattern.DAY_MONTH_YEAR_HOUR_MINUTE_SECONDS.getPattern());
	DateTimeFormat dateFormat = DateTimeFormat.getFormat(DatePattern.DAY_MONTH_YEAR.getPattern());

    
    @NameToken(NameTokens.learnerEnrollment)
    @ProxyCodeSplit
    interface MyProxy extends ProxyPlace<LearnerEnrollmentPresenter> {
    }

    @Inject
    LearnerEnrollmentPresenter(
            EventBus eventBus,
            MyView view, 
            MyProxy proxy) {
        super(eventBus, view, proxy, MainPresenter.SLOT_Main);
        
    }
    
    @Override
    protected void onBind() {
    	super.onBind();
      setControlsPaneMenuButtons();
      getAllLearnerEnrollments();
    }
    
    public void setControlsPaneMenuButtons() {
    	MenuButton newButton = new MenuButton("New");
		MenuButton edit = new MenuButton("Edit");
		MenuButton delete = new MenuButton("Delete");
		MenuButton filter = new MenuButton("Filter");

		List<MenuButton> buttons = new ArrayList<>();
		buttons.add(newButton);
		buttons.add(edit);
		buttons.add(delete);
		buttons.add(filter);

		getView().getControlsPane().addMenuButtons(buttons);
		addLearnerEnrollment(newButton);
		selectFilterOption(filter);		
    	
    }
    
    
    private void selectFilterOption(final MenuButton filter) {
	       final Menu menu = new Menu();
	       MenuItem basic = new MenuItem("Base Filter");
	       MenuItem advanced = new MenuItem("Advanced Filter");
	       
	       menu.setItems(basic , advanced);
	      
	       filter.addClickHandler(new ClickHandler() {
	   		
	   		@Override
	   		public void onClick(ClickEvent event) {
	   			menu.showNextTo(filter, "bottom");
	   		}
	   	});

	       basic.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {
			
			@Override
			public void onClick(MenuItemClickEvent event) {
			SC.say("Basic Search");
			}
		});
	       
	       advanced.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {
	   		
	   		@Override
	   		public void onClick(MenuItemClickEvent event) {
//	   		SC.say("Advanced Search");
	   		FilterLearnerHeadCountWindow window = new FilterLearnerHeadCountWindow();
	        loadFilterLearnerHeadCountAcademicYearCombo(window);
	        loadFilterLearnerHeadCountAcademicTermCombo(window);
	        loadFilterLearnerHeadCountDistrictCombo(window);
	        loadFilterLearnerHeadCountSchoolCombo(window);
	        filterLearnerEnrollmentByAcademicYearAcademicTermDistrictSchool(window);
	   		window.show();
	   		disableEnableFilterButton(window);
	   		}		
	   	});
	       
		}

	
	
	private void disableEnableFilterButton(final FilterLearnerHeadCountWindow window) {;
	window.getFilterLearnerHeadCountPane().getAcademicTermCombo().addChangedHandler(new ChangedHandler() {

		@Override
		public void onChanged(ChangedEvent event) {

			if (window.getFilterLearnerHeadCountPane().getAcademicTermCombo().getValueAsString() != null && window.getFilterLearnerHeadCountPane().getSchoolCombo().getValueAsString() != null) {
                window.getFilterButton().setDisabled(false);
			}else {
				window.getFilterButton().setDisabled(true);	
			}
		}
	});

	window.getFilterLearnerHeadCountPane().getSchoolCombo().addChangedHandler(new ChangedHandler() {

		@Override
		public void onChanged(ChangedEvent event) {
			if (window.getFilterLearnerHeadCountPane().getAcademicTermCombo().getValueAsString() != null && window.getFilterLearnerHeadCountPane().getSchoolCombo().getValueAsString() != null) {
				window.getFilterButton().setDisabled(false);
			}else {
				window.getFilterButton().setDisabled(true);
			}
		}
	});


}
    
    
    
    
    
    
    ///////////////////////////////////////
    
    
    private void addLearnerEnrollment(MenuButton newButton) {
    	newButton.addClickHandler(new ClickHandler() {
    			
    			@Override
    			public void onClick(ClickEvent event) {
    				  LearnerEnrollmentWindow window = new LearnerEnrollmentWindow();
    				  setLearnerTotal(window);
    				  loadSchoolClassCombo(window, null);
    				  window.show();
    				  
    				  saveLearnerEnrollment(window);
    			}

    		});
    	}


	private void loadSchoolClassCombo(final LearnerEnrollmentWindow window, final String defaultValue) {
		LinkedHashMap<String, Object> map = new LinkedHashMap<>();
		map.put(RequestConstant.GET_SCHOOL_CLASS, null);
		map.put(RequestConstant.LOGIN_TOKEN, SessionManager.getInstance().getLoginToken());
		SC.showPrompt("", "", new SwizimaLoader());

		dispatcher.execute(new RequestAction(RequestConstant.GET_SCHOOL_CLASS , map), new AsyncCallback<RequestResult>() {

			@Override
			public void onFailure(Throwable caught) {
				System.out.println(caught.getMessage());
				SC.warn("ERROR", caught.getMessage());
				GWT.log("ERROR " + caught.getMessage());
				SC.clearPrompt();

			}

			@Override
			public void onSuccess(RequestResult result) {

				SC.clearPrompt();
				SessionManager.getInstance().manageSession(result, placeManager);
				if (result != null) {

					if (result.getSystemFeedbackDTO() != null) {
						LinkedHashMap<String, String> valueMap = new LinkedHashMap<>();

						for (SchoolClassDTO schoolClassDTO : result.getSchoolClassDTOs()) {
							valueMap.put(schoolClassDTO.getId(), schoolClassDTO.getName());
						}
						window.getSchoolClassComboBox().setValueMap(valueMap);
						if (defaultValue != null) {
							window.getSchoolClassComboBox().setValue(defaultValue);
						}
					}
				} else {
					SC.warn("ERROR", "Unknow error");
				}

			}
		});
	}
	
    

public void	setLearnerTotal(final LearnerEnrollmentWindow window){
	final int[] totalGirls = new int[1];
	final int[] totalBoys = new int[1];
	final int[] total = {0};
	window.getTotalBoysField().addChangedHandler(new ChangedHandler() {
		
		@Override
		public void onChanged(ChangedEvent event) {
			
			if(window.getTotalBoysField().getValueAsString() == null) {
				totalBoys[0] = 0;
			}else {
				totalBoys[0] = Integer.parseInt(window.getTotalBoysField().getValueAsString());
			}
		
			if(window.getTotalGirlsField().getValueAsString() == null) {
				totalGirls[0] = 0;
			}
			
				total[0] = totalGirls[0] + totalBoys[0];
				 window.getLearnerTotalField().setValue(total[0]);
		}
	});
	
	
window.getTotalGirlsField().addChangedHandler(new ChangedHandler() {
		
		@Override
		public void onChanged(ChangedEvent event) {
			if(window.getTotalGirlsField().getValueAsString() == null) {
				totalGirls[0] = 0;
			}else {
				totalGirls[0] = Integer.parseInt(window.getTotalGirlsField().getValueAsString());	
			}
			
			if(window.getTotalBoysField().getValueAsString() == null) {
				totalBoys[0] = 0;
			}

				total[0] = totalGirls[0] + totalBoys[0];
				 window.getLearnerTotalField().setValue(total[0]);
		}
	});
	  		
	}
    
    	private void saveLearnerEnrollment(final LearnerEnrollmentWindow window) {
    	  window.getSaveButton().addClickHandler(new ClickHandler() {
    		
    		@Override
    		public void onClick(ClickEvent event) {
    			
    			if(checkIfNoLearnerEnrollmentWindowFieldIsEmpty(window)) {
    				
    			}else {
    			  SC.warn("Please Fill all fields");	
    			}
    			
    			LearnerEnrollmentDTO dto = new LearnerEnrollmentDTO();
    			//dto.setId(id);
    			dto.setTotalBoys(Long.valueOf(window.getTotalBoysField().getValueAsString()));
    			dto.setTotalGirls(Long.valueOf(window.getTotalGirlsField().getValueAsString()));
    			dto.setCreatedDateTime(dateTimeFormat.format(new Date()));
    			
    			SchoolClassDTO schoolClassDTO = new SchoolClassDTO(window.getSchoolClassComboBox().getValueAsString());
    			dto.setSchoolClassDTO(schoolClassDTO);
    			
    			GWT.log("DTO "+dto);
    			GWT.log("ID "+dto.getSchoolClassDTO().getId());
    			
    			  LinkedHashMap<String, Object> map = new LinkedHashMap<>();
    				map.put(RequestConstant.SAVE_LEARNER_ENROLLMENT, dto);
    				map.put(RequestConstant.LOGIN_TOKEN, SessionManager.getInstance().getLoginToken());
    				SC.showPrompt("", "", new SwizimaLoader());

    				dispatcher.execute(new RequestAction(RequestConstant.SAVE_LEARNER_ENROLLMENT, map),
    						new AsyncCallback<RequestResult>() {

    							public void onFailure(Throwable caught) {

    								SC.clearPrompt();
    								System.out.println(caught.getMessage());
    								SC.say("ERROR", caught.getMessage());
    							}

    							public void onSuccess(RequestResult result) {
    								SC.clearPrompt();
    								
    								clearLearnerEnrollmentWindowFields(window);
    								window.close();

    								SessionManager.getInstance().manageSession(result, placeManager);
    								
    								if (result != null) {
    									SystemFeedbackDTO feedback = result.getSystemFeedbackDTO();

    									if (feedback.isResponse()) {
    										SC.say("SUCCESS", feedback.getMessage());
    									} else {
    										SC.warn("INFO", feedback.getMessage());
    									}

    									getView().getLearnerEnrollementPane().getLearnerEnrollmentListGrid().addRecordsToGrid(result.getLearnerEnrollmentDTOs());

    								} else {
    									SC.warn("ERROR", "Unknow error");
    								}

    							}

    						});
    			
    		}
    	});

    	}
    	

    	protected boolean checkIfNoLearnerEnrollmentWindowFieldIsEmpty(LearnerEnrollmentWindow window) {
    		boolean flag = true;

    		if(window.getTotalBoysField().getValueAsString() == null) flag = false;
    		
    		if(window.getTotalGirlsField().getValueAsString() == null) flag = false;
    		
    		if(window.getSchoolClassComboBox().getValueAsString() == null) flag = false;
    		
    		return flag;
    	}

    	private void clearLearnerEnrollmentWindowFields(LearnerEnrollmentWindow window) {
       	
    		window.getSchoolClassComboBox().clearValue();
    		window.getTotalBoysField().clearValue();
    		window.getTotalGirlsField().clearValue();
    		window.getLearnerTotalField().clearValue();
    		
    	}
    	
    
    
    
    
    private void getAllLearnerEnrollments() {
		LinkedHashMap<String, Object> map = new LinkedHashMap<>();
		map.put(RequestConstant.GET_LEARNER_ENROLLMENT, null);
		map.put(RequestConstant.LOGIN_TOKEN, SessionManager.getInstance().getLoginToken());
		SC.showPrompt("", "", new SwizimaLoader());

		dispatcher.execute(new RequestAction(RequestConstant.GET_LEARNER_ENROLLMENT , map),
				new AsyncCallback<RequestResult>() {

					@Override
					public void onFailure(Throwable caught) {
						System.out.println(caught.getMessage());
						SC.warn("ERROR", caught.getMessage());
						GWT.log("ERROR " + caught.getMessage());
						SC.clearPrompt();

					}

					@Override
					public void onSuccess(RequestResult result) {

						SC.clearPrompt();
						SessionManager.getInstance().manageSession(result, placeManager);
						if (result != null) {
	                        SystemFeedbackDTO feedbackDTO = result.getSystemFeedbackDTO();
							if ( feedbackDTO != null) {
								if (feedbackDTO.isResponse()) {
									// SC.say("SUCCESS", result.getSystemFeedbackDTO().getMessage());
									getView().getLearnerEnrollementPane().getLearnerEnrollmentListGrid()
									.addRecordsToGrid(result.getLearnerEnrollmentDTOs());
								} else {
									SC.warn("Not Successful \n ERROR:", feedbackDTO.getMessage());
								}
							}
						} else {
							SC.warn("ERROR", "Unknow error");
						}

					}

				});

	}
    
    
 
///////////////////////FILTER LEARNER HEADCOUNT COMBOS(4)
	
//loads school combo in filter learner head count pane
private void loadFilterLearnerHeadCountSchoolCombo(final FilterLearnerHeadCountWindow window) {
	window.getFilterLearnerHeadCountPane().getDistrictCombo().addChangedHandler(new ChangedHandler() {
		
		@Override
		public void onChanged(ChangedEvent event) {
			LinkedHashMap<String, Object> map = new LinkedHashMap<>();
			String districtId = window.getFilterLearnerHeadCountPane().getDistrictCombo().getValueAsString();
			map.put(RequestConstant.GET_SCHOOLS_IN_DISTRICT, districtId);
			map.put(RequestConstant.LOGIN_TOKEN, SessionManager.getInstance().getLoginToken());
			SC.showPrompt("", "", new SwizimaLoader());

			dispatcher.execute(new RequestAction(RequestConstant.GET_SCHOOLS_IN_DISTRICT , map), new AsyncCallback<RequestResult>() {

				@Override
				public void onFailure(Throwable caught) {
					System.out.println(caught.getMessage());
					SC.warn("ERROR", caught.getMessage());
					GWT.log("ERROR " + caught.getMessage());
					SC.clearPrompt();

				}

				@Override
				public void onSuccess(RequestResult result) {

					SC.clearPrompt();
					SessionManager.getInstance().manageSession(result, placeManager);
					if (result != null) {

						if (result.getSystemFeedbackDTO() != null) {
							LinkedHashMap<String, String> valueMap = new LinkedHashMap<>();

							for (SchoolDTO schoolDTO : result.getSchoolDTOs()) {
								valueMap.put(schoolDTO.getId(), schoolDTO.getName());
							}
							window.getFilterLearnerHeadCountPane().getSchoolCombo().setValueMap(valueMap);	
						}
					} else {
						SC.warn("ERROR", "Unknow error");
					}

				}
			});		
		}
	});
	
}



//loads district combo in filter learner head count pane	
private void loadFilterLearnerHeadCountDistrictCombo(final FilterLearnerHeadCountWindow window) {
	LinkedHashMap<String, Object> map = new LinkedHashMap<>();
	map.put(RequestConstant.GET_DISTRICT, null);
	map.put(RequestConstant.LOGIN_TOKEN, SessionManager.getInstance().getLoginToken());
	SC.showPrompt("", "", new SwizimaLoader());

	dispatcher.execute(new RequestAction(RequestConstant.GET_DISTRICT , map), new AsyncCallback<RequestResult>() {

		@Override
		public void onFailure(Throwable caught) {
			System.out.println(caught.getMessage());
			SC.warn("ERROR", caught.getMessage());
			GWT.log("ERROR " + caught.getMessage());
			SC.clearPrompt();

		}

		@Override
		public void onSuccess(RequestResult result) {

			SC.clearPrompt();
			SessionManager.getInstance().manageSession(result, placeManager);
			if (result != null) {

				if (result.getSystemFeedbackDTO() != null) {
					LinkedHashMap<String, String> valueMap = new LinkedHashMap<>();

					for (DistrictDTO districtDTO : result.getDistrictDTOs()) {
						valueMap.put(districtDTO.getId(), districtDTO.getName());
					}
				    window.getFilterLearnerHeadCountPane().getDistrictCombo().setValueMap(valueMap);	
				}
			} else {
				SC.warn("ERROR", "Unknow error");
			}

		}
	});
}



//loads academic year combo in filter learner head count pane	
	private void loadFilterLearnerHeadCountAcademicYearCombo(final FilterLearnerHeadCountWindow window) {
		LinkedHashMap<String, Object> map = new LinkedHashMap<>();
		map.put(RequestConstant.GET_ACADEMIC_YEAR, null);
		map.put(RequestConstant.LOGIN_TOKEN, SessionManager.getInstance().getLoginToken());
		SC.showPrompt("", "", new SwizimaLoader());

		dispatcher.execute(new RequestAction(RequestConstant.GET_ACADEMIC_YEAR , map), new AsyncCallback<RequestResult>() {

			@Override
			public void onFailure(Throwable caught) {
				System.out.println(caught.getMessage());
				SC.warn("ERROR", caught.getMessage());
				GWT.log("ERROR " + caught.getMessage());
				SC.clearPrompt();

			}

			@Override
			public void onSuccess(RequestResult result) {

				SC.clearPrompt();
				SessionManager.getInstance().manageSession(result, placeManager);
				if (result != null) {

					if (result.getSystemFeedbackDTO() != null) {
						LinkedHashMap<String, String> valueMap = new LinkedHashMap<>();

						for (AcademicYearDTO academicYearDTO : result.getAcademicYearDTOs()) {
							valueMap.put(academicYearDTO.getId(), academicYearDTO.getName());
						}
						window.getFilterLearnerHeadCountPane().getAcademicYearCombo().setValueMap(valueMap);	
					}
				} else {
					SC.warn("ERROR", "Unknow error");
				}

			}
		});
	}

	
	//loads academic year combo in filter learner head count pane	
			private void loadFilterLearnerHeadCountAcademicTermCombo(final FilterLearnerHeadCountWindow window) {
				window.getFilterLearnerHeadCountPane().getAcademicYearCombo().addChangedHandler(new ChangedHandler() {
					
					@Override
					public void onChanged(ChangedEvent event) {
						LinkedHashMap<String, Object> map = new LinkedHashMap<>();
						String academicYearId = window.getFilterLearnerHeadCountPane().getAcademicYearCombo().getValueAsString();
						map.put(RequestConstant.GET_ACADEMIC_TERMS_IN_ACADEMIC_YEAR , academicYearId);
						map.put(RequestConstant.LOGIN_TOKEN, SessionManager.getInstance().getLoginToken());
						SC.showPrompt("", "", new SwizimaLoader());

						dispatcher.execute(new RequestAction(RequestConstant.GET_ACADEMIC_TERMS_IN_ACADEMIC_YEAR , map), new AsyncCallback<RequestResult>() {

							@Override
							public void onFailure(Throwable caught) {
								System.out.println(caught.getMessage());
								SC.warn("ERROR", caught.getMessage());
								GWT.log("ERROR " + caught.getMessage());
								SC.clearPrompt();

							}

							@Override
							public void onSuccess(RequestResult result) {

								SC.clearPrompt();
								SessionManager.getInstance().manageSession(result, placeManager);
								if (result != null) {

									if (result.getSystemFeedbackDTO() != null) {
										LinkedHashMap<String, String> valueMap = new LinkedHashMap<>();

										for (AcademicTermDTO academicTermDTO : result.getAcademicTermDTOs()) {
											valueMap.put(academicTermDTO.getId(), academicTermDTO.getTerm());
										}
										window.getFilterLearnerHeadCountPane().getAcademicTermCombo().setValueMap(valueMap);	
									}
								} else {
									SC.warn("ERROR", "Unknow error");
								}

							}
						});
					}
				});
				
			}

			/////////////////////////////////////////END OF FILTER LEARNERS COMBOS
			
			
			private void filterLearnerEnrollmentByAcademicYearAcademicTermDistrictSchool(final FilterLearnerHeadCountWindow window) {
				window.getFilterButton().addClickHandler(new ClickHandler() {
					
					@Override
					public void onClick(ClickEvent event) {
						String academicYearId = window.getFilterLearnerHeadCountPane().getAcademicYearCombo().getValueAsString();
						String academicTermId = window.getFilterLearnerHeadCountPane().getAcademicTermCombo().getValueAsString();
						String districtId = window.getFilterLearnerHeadCountPane().getDistrictCombo().getValueAsString();
						String schoolId = window.getFilterLearnerHeadCountPane().getSchoolCombo().getValueAsString();
						
						LinkedHashMap<String, Object> map = new LinkedHashMap<>();
						map.put(FilterLearnerHeadCountWindow.ACADEMIC_YEAR_ID, academicYearId);
						map.put(FilterLearnerHeadCountWindow.ACADEMIC_TERM_ID, academicTermId);
						map.put(FilterLearnerHeadCountWindow.DISTRICT_ID, districtId);
						map.put(FilterLearnerHeadCountWindow.SCHOOL_ID, schoolId);
						
						map.put(RequestConstant.GET_LEARNER_ENROLLMENTS_IN_ACADEMIC_YEAR_ACADEMIC_TERM_DISTRICT_SCHOOL, map);
						map.put(RequestConstant.LOGIN_TOKEN, SessionManager.getInstance().getLoginToken());
						SC.showPrompt("", "", new SwizimaLoader());

						dispatcher.execute(new RequestAction(RequestConstant.GET_LEARNER_ENROLLMENTS_IN_ACADEMIC_YEAR_ACADEMIC_TERM_DISTRICT_SCHOOL , map),
								new AsyncCallback<RequestResult>() {

									@Override
									public void onFailure(Throwable caught) {
										System.out.println(caught.getMessage());
										SC.warn("ERROR", caught.getMessage());
										GWT.log("ERROR " + caught.getMessage());
										SC.clearPrompt();

									}

									@Override
									public void onSuccess(RequestResult result) {

										SC.clearPrompt();
										SessionManager.getInstance().manageSession(result, placeManager);
										if (result != null) {
				                            SystemFeedbackDTO feedbackDTO = result.getSystemFeedbackDTO();
											if ( feedbackDTO != null) {
												window.close();
												if (result.getSystemFeedbackDTO().isResponse()) {
													// SC.say("SUCCESS", result.getSystemFeedbackDTO().getMessage());
													getView().getLearnerEnrollementPane().getLearnerEnrollmentListGrid().addRecordsToGrid(result.getLearnerEnrollmentDTOs());
												} else {
													SC.warn("Not Successful \n ERROR:", result.getSystemFeedbackDTO().getMessage());
												}
											}
										} else {
											SC.warn("ERROR", "Unknow error");
										}

									}

								});
						
					}
				});
			}

    
}