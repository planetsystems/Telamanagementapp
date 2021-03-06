package com.planetsystems.tela.managementapp.client.presenter.staffattendance;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rpc.shared.DispatchAsync;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ContentSlot;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;
import com.planetsystems.tela.dto.AcademicTermDTO;
import com.planetsystems.tela.dto.AcademicYearDTO;
import com.planetsystems.tela.dto.ClockInDTO;
import com.planetsystems.tela.dto.ClockOutDTO;
import com.planetsystems.tela.dto.DistrictDTO;
import com.planetsystems.tela.dto.FilterDTO;
import com.planetsystems.tela.dto.SchoolDTO;
import com.planetsystems.tela.dto.SchoolStaffDTO;
import com.planetsystems.tela.managementapp.client.gin.SessionManager;
import com.planetsystems.tela.managementapp.client.place.NameTokens;
import com.planetsystems.tela.managementapp.client.presenter.comboutils.ComboUtil;
import com.planetsystems.tela.managementapp.client.presenter.main.MainPresenter;
import com.planetsystems.tela.managementapp.client.presenter.networkutil.NetworkDataUtil;
import com.planetsystems.tela.managementapp.client.presenter.networkutil.NetworkResult;
import com.planetsystems.tela.managementapp.client.presenter.staffattendance.clockin.ClockInListGrid;
import com.planetsystems.tela.managementapp.client.presenter.staffattendance.clockin.ClockInPane;
import com.planetsystems.tela.managementapp.client.presenter.staffattendance.clockin.ClockInWindow;
import com.planetsystems.tela.managementapp.client.presenter.staffattendance.clockin.FilterClockInWindow;
import com.planetsystems.tela.managementapp.client.presenter.staffattendance.clockout.ClockOutPane;
import com.planetsystems.tela.managementapp.client.presenter.staffattendance.clockout.FilterClockOutWindow;
import com.planetsystems.tela.managementapp.client.widget.ControlsPane;
import com.planetsystems.tela.managementapp.client.widget.MenuButton;
import com.planetsystems.tela.managementapp.shared.DatePattern;
import com.planetsystems.tela.managementapp.shared.RequestConstant;
import com.planetsystems.tela.managementapp.shared.RequestDelimeters;
import com.planetsystems.tela.managementapp.shared.RequestResult;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.menu.Menu;
import com.smartgwt.client.widgets.menu.MenuItem;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;
import com.smartgwt.client.widgets.tab.TabSet;
import com.smartgwt.client.widgets.tab.events.TabSelectedEvent;
import com.smartgwt.client.widgets.tab.events.TabSelectedHandler;

public class StaffAttendancePresenter
		extends Presenter<StaffAttendancePresenter.MyView, StaffAttendancePresenter.MyProxy> {
	interface MyView extends View {
		public ControlsPane getControlsPane();

		public TabSet getTabSet();

		public ClockOutPane getClockOutPane();

		public ClockInPane getClockInPane();
	}

	@ContentSlot
	public static final Type<RevealContentHandler<?>> SLOT_attendance = new Type<RevealContentHandler<?>>();

	@Inject
	private PlaceManager placeManager;

	@Inject
	private DispatchAsync dispatcher;

	DateTimeFormat dateTimeFormat = DateTimeFormat
			.getFormat(DatePattern.DAY_MONTH_YEAR_HOUR_MINUTE_SECONDS.getPattern());
	DateTimeFormat dateFormat = DateTimeFormat.getFormat(DatePattern.DAY_MONTH_YEAR.getPattern());
	DateTimeFormat dayDateFormat = DateTimeFormat.getFormat(DatePattern.DAY_DATE.getPattern());

	@NameToken(NameTokens.staffAttendance)
	@ProxyCodeSplit
	interface MyProxy extends ProxyPlace<StaffAttendancePresenter> {
	}

	@Inject
	StaffAttendancePresenter(EventBus eventBus, MyView view, MyProxy proxy) {
		super(eventBus, view, proxy, MainPresenter.SLOT_Main);
	}

	@Override
	protected void onBind() {
		super.onBind();
		onTabSelected();

	}

	private void onTabSelected() {
		getView().getTabSet().addTabSelectedHandler(new TabSelectedHandler() {

			@Override
			public void onTabSelected(TabSelectedEvent event) {

				String selectedTab = event.getTab().getTitle();

				if (selectedTab.equalsIgnoreCase(StaffAttendanceView.CLOCKIN_TAB_TITLE)) {
					getView().getClockOutPane().getFilterParams().hide();
					MenuButton clockInButton = new MenuButton("ClockIn");
					MenuButton clockOut = new MenuButton("ClockOut");
					MenuButton filter = new MenuButton("Filter");

					List<MenuButton> buttons = new ArrayList<>();
					buttons.add(clockInButton);
					buttons.add(filter);
					buttons.add(clockOut);

					getView().getControlsPane().addMenuButtons("Teacher Clockin Details",buttons);

					//getAllStaffClockIn();
					//Load the filters to allow the user to define the clockins to view
					loadFilter();
					addClockIn(clockInButton);
					clockOut(clockOut);
					selectFilterOption(filter);

				} else if (selectedTab.equalsIgnoreCase(StaffAttendanceView.CLOCKOUT_TAB_TITLE)) {
					
					//Load the filters to allow the user to define the clockouts to view
					getView().getClockInPane().getFilterParams().hide();
					
					loadClockoutFilters();
					
					//getAllStaffClockOut();
					
					
					MenuButton filter = new MenuButton("Filter");

					List<MenuButton> buttons = new ArrayList<>();

					buttons.add(filter);

					getView().getControlsPane().addMenuButtons("Teacher Clockout Details",buttons);
					selectFilterClockOutOption(filter);

				} else {
					List<MenuButton> buttons = new ArrayList<>();
					getView().getControlsPane().addMenuButtons(buttons);
				}

			}

		});
	}

	private void selectFilterOption(final MenuButton filter) {
		final Menu menu = new Menu();
		MenuItem basic = new MenuItem("Base Filter");
		MenuItem advanced = new MenuItem("Advanced Filter");

		menu.setItems(basic, advanced);

		filter.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				menu.showNextTo(filter, "bottom");
			}
		});

		basic.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {

			@Override
			public void onClick(MenuItemClickEvent event) {
				getView().getClockInPane().getClockInListGrid().setShowFilterEditor(true);
			}
		});

		advanced.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {

			@Override
			public void onClick(MenuItemClickEvent event) {
				loadFilter();
			}
		});

	}
	
	private void loadFilter() {
		FilterClockInWindow window = new FilterClockInWindow();
		loadFilterClockInAcademicYearCombo(window);
		loadFilterClockInAcademicTermCombo(window);
		loadFilterClockInDistrictCombo(window);
		loadFilterClockInSchoolCombo(window);
		
		window.getFilterClockInPane().getTodayDateField().setValue(dayDateFormat.format(new Date()));
		filterClockInsByAcademicYearAcademicTermDistrictSchool(window);
		window.show();
		
	}

	private void selectFilterClockOutOption(final MenuButton filter) {
		final Menu menu = new Menu();
		MenuItem basic = new MenuItem("Base Filter");
		MenuItem advanced = new MenuItem("Advanced Filter");

		menu.setItems(basic, advanced);

		filter.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				menu.showNextTo(filter, "bottom");
			}
		});
		basic.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {

			@Override
			public void onClick(MenuItemClickEvent event) {
				getView().getClockOutPane().getClockOutListGrid().setShowFilterEditor(true);
			}
		});

		advanced.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {

			@Override
			public void onClick(MenuItemClickEvent event) {
				
				loadClockoutFilters();
			}
		});

	}
	
	private void loadClockoutFilters() {
		FilterClockOutWindow window = new FilterClockOutWindow();
		loadFilterClockOutAcademicYearCombo(window);
		loadFilterClockOutAcademicTermCombo(window);
		loadFilterClockOutDistrictCombo(window);
		loadFilterClockOutSchoolCombo(window);
		window.getFilterClockOutPane().getDateField().setValue(dayDateFormat.format(new Date()));
		filterClockOutsByAcademicYearAcademicTermDistrictSchool(window);
		window.show();
	}

	///////////////////////////////////////////
	private void addClockIn(MenuButton clockInButton) {
		clockInButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				ClockInWindow window = new ClockInWindow();
				window.show();

				loadAcademicYearCombo(window, null);
				loadAcademicTermCombo(window, null);
				loadDistrictCombo(window, null);
				loadSchoolCombo(window, null);
				loadSchoolStaffCombo(window, null);

				saveClockin(window);
			}
		});

	}

	protected void saveClockin(final ClockInWindow window) {
		window.getSaveButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				if (checkIfNoClockInWindowFieldIsEmpty(window)) {
					ClockInDTO dto = new ClockInDTO();
					// dto.setClockInDate(clockInDate);
					dto.setComment(window.getCommentField().getValueAsString());
					// dto.setId(id);
					dto.setLatitude(window.getLatitudeField().getValueAsString());
					dto.setLongitude(window.getLongitudeField().getValueAsString());
					dto.setCreatedDateTime(dateTimeFormat.format(new Date()));

					AcademicTermDTO academicTermDTO = new AcademicTermDTO(
							window.getAcademicTermCombo().getValueAsString());
					dto.setAcademicTermDTO(academicTermDTO);

					SchoolDTO schoolDTO = new SchoolDTO(window.getSchoolCombo().getValueAsString());
					dto.setSchoolDTO(schoolDTO);

					SchoolStaffDTO schoolStaffDTO = new SchoolStaffDTO();
					schoolStaffDTO.setId(window.getSchoolStaffCombo().getValueAsString());
					dto.setSchoolStaffDTO(schoolStaffDTO);

					GWT.log("DTO " + dto);
					GWT.log("Term " + dto.getAcademicTermDTO().getId());
					GWT.log("Staff " + dto.getSchoolStaffDTO().getId());

					LinkedHashMap<String, Object> map = new LinkedHashMap<>();
					map.put(RequestConstant.SAVE_CLOCK_IN, dto);
					map.put(NetworkDataUtil.ACTION, RequestConstant.SAVE_CLOCK_IN);

					NetworkDataUtil.callNetwork(dispatcher, placeManager, map, new NetworkResult() {

						@Override
						public void onNetworkResult(RequestResult result) {
							clearClockInWindowFields(window);
							SC.say("SUCCESS", result.getSystemFeedbackDTO().getMessage());
							getAllStaffClockIn();
						}
					});

				} else {
					SC.say("Please fill all the fields");
				}

			}

		});

	}

	private boolean checkIfNoClockInWindowFieldIsEmpty(ClockInWindow window) {
		boolean flag = true;

		if (window.getAcademicYearCombo().getValueAsString() == null)
			flag = false;

		if (window.getAcademicTermCombo().getValueAsString() == null)
			flag = false;

		if (window.getDistrictCombo().getValueAsString() == null)
			flag = false;

		if (window.getSchoolCombo().getValueAsString() == null)
			flag = false;

		if (window.getSchoolStaffCombo().getValueAsString() == null)
			flag = false;

		// if(window.getCommentField().getValueAsString() == null) flag = false;

		if (window.getLatitudeField().getValueAsString() == null)
			flag = false;

		if (window.getLongitudeField().getValueAsString() == null)
			flag = false;

		return flag;
	}

	private void clearClockInWindowFields(ClockInWindow window) {
		window.getAcademicYearCombo().clearValue();
		window.getAcademicTermCombo().clearValue();
		window.getDistrictCombo().clearValue();
		window.getSchoolCombo().clearValue();
		window.getSchoolStaffCombo().clearValue();
		window.getCommentField().clearValue();
		window.getLatitudeField().clearValue();
		window.getLongitudeField().clearValue();
	}

	private void loadAcademicYearCombo(final ClockInWindow window, final String defaultValue) {
		ComboUtil.loadAcademicYearCombo(window.getAcademicYearCombo(), dispatcher, placeManager, defaultValue);
	}

	private void loadAcademicTermCombo(final ClockInWindow window, final String defaultValue) {
		window.getAcademicYearCombo().addChangedHandler(new ChangedHandler() {

			@Override
			public void onChanged(ChangedEvent event) {
				ComboUtil.loadAcademicTermComboByAcademicYear(window.getAcademicYearCombo(),
						window.getAcademicTermCombo(), dispatcher, placeManager, defaultValue);
			}
		});
	}

	private void loadDistrictCombo(final ClockInWindow window, final String defaultValue) {
		ComboUtil.loadDistrictCombo(window.getDistrictCombo(), dispatcher, placeManager, defaultValue);
	}

	private void loadSchoolCombo(final ClockInWindow window, final String defaultValue) {
		window.getDistrictCombo().addChangedHandler(new ChangedHandler() {

			@Override
			public void onChanged(ChangedEvent event) {
				ComboUtil.loadSchoolComboByDistrict(window.getDistrictCombo(), window.getSchoolCombo(), dispatcher,
						placeManager, defaultValue);
			}
		});
	}

	private void loadSchoolStaffCombo(final ClockInWindow window, final String defaultValue) {
		window.getSchoolCombo().addChangedHandler(new ChangedHandler() {

			@Override
			public void onChanged(ChangedEvent event) {
				ComboUtil.loadSchoolStaffComboBySchool(window.getSchoolCombo(), window.getSchoolStaffCombo(),
						dispatcher, placeManager, defaultValue);
			}
		});
	}

	private void getAllStaffClockIn() {
		LinkedHashMap<String, Object> map = new LinkedHashMap<>();
		if (SessionManager.getInstance().getLoggedInUserGroup().equalsIgnoreCase(SessionManager.ADMIN))
			map.put(NetworkDataUtil.ACTION, RequestConstant.GET_CLOCK_IN);
		else
			map.put(NetworkDataUtil.ACTION, RequestConstant.GET_CLOCK_INS_BY_SYSTEM_USER_PROFILE_SCHOOLS);

		NetworkDataUtil.callNetwork(dispatcher, placeManager, map, new NetworkResult() {

			@Override
			public void onNetworkResult(RequestResult result) {
				getView().getClockInPane().getClockInListGrid().addRecordsToGrid(result.getClockInDTOs());
			}
		});

	}

	//////////////////////// clockout

	private void clockOut(MenuButton clockOutButton) {

		clockOutButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if (getView().getClockInPane().getClockInListGrid().anySelected()) {
					ListGridRecord record = getView().getClockInPane().getClockInListGrid().getSelectedRecord();
					ClockOutDTO dto = new ClockOutDTO();

					ClockInDTO clockInDTO = new ClockInDTO();
					clockInDTO.setId(record.getAttribute(ClockInListGrid.ID));

					dto.setClockInDTO(clockInDTO);
					dto.setComment(record.getAttribute(ClockInListGrid.COMMENT));
					dto.setCreatedDateTime(dateTimeFormat.format(new Date()));
//            	 SC.say("Hello");
					GWT.log("DTO " + dto);
					GWT.log("DTO id  " + dto.getClockInDTO().getId());

					GWT.log("date " + dto.getCreatedDateTime());

					LinkedHashMap<String, Object> map = new LinkedHashMap<>();
					map.put(RequestConstant.SAVE_CLOCK_OUT, dto);
					map.put(NetworkDataUtil.ACTION, RequestConstant.SAVE_CLOCK_OUT);

					NetworkDataUtil.callNetwork(dispatcher, placeManager, map, new NetworkResult() {

						@Override
						public void onNetworkResult(RequestResult result) {
							SC.say("SUCCESS", result.getSystemFeedbackDTO().getMessage());
							getAllStaffClockIn();
						}
					});

				} else {
					SC.say("Please Select A record to clockout");
				}
			}
		});

	}

	private void getAllStaffClockOut() {
		LinkedHashMap<String, Object> map = new LinkedHashMap<>();
		if (SessionManager.getInstance().getLoggedInUserGroup().equalsIgnoreCase(SessionManager.ADMIN))
			map.put(NetworkDataUtil.ACTION, RequestConstant.GET_CLOCK_OUT);
		else
			map.put(NetworkDataUtil.ACTION, RequestConstant.GET_CLOCK_OUTS_BY_SYSTEM_USER_PROFILE_SCHOOLS);

		NetworkDataUtil.callNetwork(dispatcher, placeManager, map, new NetworkResult() {

			@Override
			public void onNetworkResult(RequestResult result) {
				getView().getClockOutPane().getClockOutListGrid().addRecordsToGrid(result.getClockOutDTOs());
			}
		});

	}

///////////////////////FILTER CLOCKIN COMBOS(4)

//loads school combo in filter learner head count pane
	private void loadFilterClockInSchoolCombo(final FilterClockInWindow window) {
		window.getFilterClockInPane().getDistrictCombo().addChangedHandler(new ChangedHandler() {

			@Override
			public void onChanged(ChangedEvent event) {
				ComboUtil.loadSchoolComboByDistrict(window.getFilterClockInPane().getDistrictCombo(),
						window.getFilterClockInPane().getSchoolCombo(), dispatcher, placeManager, null);
			}
		});

	}

//loads district combo in filter learner head count pane	
	private void loadFilterClockInDistrictCombo(final FilterClockInWindow window) {
		ComboUtil.loadDistrictCombo(window.getFilterClockInPane().getDistrictCombo(), dispatcher, placeManager, null);
	}

//loads academic year combo in filter learner head count pane	
	private void loadFilterClockInAcademicYearCombo(final FilterClockInWindow window) {
		ComboUtil.loadAcademicYearCombo(window.getFilterClockInPane().getAcademicYearCombo(), dispatcher, placeManager,
				null);
	}

//loads academic year combo in filter learner head count pane	
	private void loadFilterClockInAcademicTermCombo(final FilterClockInWindow window) {
		window.getFilterClockInPane().getAcademicYearCombo().addChangedHandler(new ChangedHandler() {

			@Override
			public void onChanged(ChangedEvent event) {

				ComboUtil.loadAcademicTermComboByAcademicYear(window.getFilterClockInPane().getAcademicYearCombo(),
						window.getFilterClockInPane().getAcademicTermCombo(), dispatcher, placeManager, null);
			}
		});

	}

/////////////////////////////////////////END OF FILTER CLOCKIN COMBOS

///////////////////////FILTER CLOCKOUT COMBOS(4)

//loads school combo in filter learner head count pane
	private void loadFilterClockOutSchoolCombo(final FilterClockOutWindow window) {
		window.getFilterClockOutPane().getDistrictCombo().addChangedHandler(new ChangedHandler() {

			@Override
			public void onChanged(ChangedEvent event) {
				ComboUtil.loadSchoolComboByDistrict(window.getFilterClockOutPane().getDistrictCombo(),
						window.getFilterClockOutPane().getSchoolCombo(), dispatcher, placeManager, null);
			}
		});

	}

//loads district combo in filter learner head count pane	
	private void loadFilterClockOutDistrictCombo(final FilterClockOutWindow window) {
		ComboUtil.loadDistrictCombo(window.getFilterClockOutPane().getDistrictCombo(), dispatcher, placeManager, null);
	}

//loads academic year combo in filter learner head count pane	
	private void loadFilterClockOutAcademicYearCombo(final FilterClockOutWindow window) {
		ComboUtil.loadAcademicYearCombo(window.getFilterClockOutPane().getAcademicYearCombo(), dispatcher, placeManager,
				null);
	}

//loads academic year combo in filter learner head count pane	
	private void loadFilterClockOutAcademicTermCombo(final FilterClockOutWindow window) {
		window.getFilterClockOutPane().getAcademicYearCombo().addChangedHandler(new ChangedHandler() {

			@Override
			public void onChanged(ChangedEvent event) {

				ComboUtil.loadAcademicTermComboByAcademicYear(window.getFilterClockOutPane().getAcademicYearCombo(),
						window.getFilterClockOutPane().getAcademicTermCombo(), dispatcher, placeManager, null);
			}
		});

	}

/////////////////////////////////////////END OF FILTER CLOCKOUT COMBOS

	// filter
	private void filterClockInsByAcademicYearAcademicTermDistrictSchool(final FilterClockInWindow window) {
		
		window.getFilterButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				
				//show filter parameters
				getView().getClockInPane().getFilterParams().show();
				getView().getClockInPane().getAcademicYearField().setValue(window.getFilterClockInPane().getAcademicYearCombo().getDisplayValue());
				getView().getClockInPane().getAcademicTermField().setValue(window.getFilterClockInPane().getAcademicTermCombo().getDisplayValue());
				getView().getClockInPane().getDistrictField().setValue(window.getFilterClockInPane().getDistrictCombo().getDisplayValue());
				getView().getClockInPane().getSchoolField().setValue(window.getFilterClockInPane().getSchoolCombo().getDisplayValue());
				getView().getClockInPane().getDateField().setValue(dayDateFormat.format(new Date()));
				
				String academicYearId = window.getFilterClockInPane().getAcademicYearCombo().getValueAsString();
				String academicTermId = window.getFilterClockInPane().getAcademicTermCombo().getValueAsString();
				String districtId = window.getFilterClockInPane().getDistrictCombo().getValueAsString();
				String schoolId = window.getFilterClockInPane().getSchoolCombo().getValueAsString();
				String fromDate = dateFormat.format(new Date());
				//String toDate = dateFormat.format(window.getFilterClockInPane().getToDateItem().getValueAsDate());

				FilterDTO dto = new FilterDTO();
				dto.setAcademicYearDTO(new AcademicYearDTO(academicYearId));
				dto.setAcademicTermDTO(new AcademicTermDTO(academicTermId));
				dto.setDistrictDTO(new DistrictDTO(districtId));
				dto.setSchoolDTO(new SchoolDTO(schoolId));
				dto.setFromDate(fromDate);
				//dto.setToDate(toDate);

				LinkedHashMap<String, Object> map = new LinkedHashMap<>();
				map.put(RequestDelimeters.FILTER_CLOCK_INS, dto);
				map.put(NetworkDataUtil.ACTION,
						RequestConstant.FILTER_CLOCKINS);

				NetworkDataUtil.callNetwork(dispatcher, placeManager, map, new NetworkResult() {

					@Override
					public void onNetworkResult(RequestResult result) {
						getView().getClockInPane().getClockInListGrid().addRecordsToGrid(result.getClockInDTOs());
					}
				});

			}
		});
	}

	private void filterClockOutsByAcademicYearAcademicTermDistrictSchool(final FilterClockOutWindow window) {
		window.getFilterButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				
				//show filter parameters
				getView().getClockOutPane().getFilterParams().show();
				getView().getClockOutPane().getAcademicYearField().setValue(window.getFilterClockOutPane().getAcademicYearCombo().getDisplayValue());
				getView().getClockOutPane().getAcademicTermField().setValue(window.getFilterClockOutPane().getAcademicTermCombo().getDisplayValue());
				getView().getClockOutPane().getDistrictField().setValue(window.getFilterClockOutPane().getDistrictCombo().getDisplayValue());
				getView().getClockOutPane().getSchoolField().setValue(window.getFilterClockOutPane().getSchoolCombo().getDisplayValue());
				getView().getClockOutPane().getDateField().setValue(dayDateFormat.format(new Date()));
				
				
				String academicYearId = window.getFilterClockOutPane().getAcademicYearCombo().getValueAsString();
				String academicTermId = window.getFilterClockOutPane().getAcademicTermCombo().getValueAsString();
				String districtId = window.getFilterClockOutPane().getDistrictCombo().getValueAsString();
				String schoolId = window.getFilterClockOutPane().getSchoolCombo().getValueAsString();
				String date = dateFormat.format(new Date());

				FilterDTO dto = new FilterDTO();
				dto.setAcademicYearDTO(new AcademicYearDTO(academicYearId));
				dto.setAcademicTermDTO(new AcademicTermDTO(academicTermId));
				dto.setDistrictDTO(new DistrictDTO(districtId));
				dto.setSchoolDTO(new SchoolDTO(schoolId));
				dto.setDate(date);

				LinkedHashMap<String, Object> map = new LinkedHashMap<>();
				map.put(RequestDelimeters.FILTER_CLOCK_OUTS, dto);
				map.put(NetworkDataUtil.ACTION,
						RequestConstant.FILTER_CLOCK_OUTS_BY_ACADEMIC_YEAR_ACADEMIC_TERM_DISTRICT_SCHOOL);
				NetworkDataUtil.callNetwork(dispatcher, placeManager, map, new NetworkResult() {

					@Override
					public void onNetworkResult(RequestResult result) {
						getView().getClockOutPane().getClockOutListGrid().addRecordsToGrid(result.getClockOutDTOs());
					}
				});
			}
		});
	}

}