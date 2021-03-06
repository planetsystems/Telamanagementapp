package com.planetsystems.tela.managementapp.client.presenter.learnerattendance;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

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
import com.planetsystems.tela.dto.DistrictDTO;
import com.planetsystems.tela.dto.FilterDTO;
import com.planetsystems.tela.dto.LearnerAttendanceDTO;
import com.planetsystems.tela.dto.SchoolClassDTO;
import com.planetsystems.tela.dto.SchoolDTO;
import com.planetsystems.tela.dto.SchoolStaffDTO;
import com.planetsystems.tela.managementapp.client.gin.SessionManager;
import com.planetsystems.tela.managementapp.client.place.NameTokens;
import com.planetsystems.tela.managementapp.client.presenter.comboutils.ComboUtil;
import com.planetsystems.tela.managementapp.client.presenter.main.MainPresenter;
import com.planetsystems.tela.managementapp.client.presenter.networkutil.NetworkDataUtil;
import com.planetsystems.tela.managementapp.client.presenter.networkutil.NetworkResult;
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
import com.smartgwt.client.widgets.menu.Menu;
import com.smartgwt.client.widgets.menu.MenuItem;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;

public class LearnerAttendancePresenter
		extends Presenter<LearnerAttendancePresenter.MyView, LearnerAttendancePresenter.MyProxy> {
	interface MyView extends View {
		public ControlsPane getControlsPane();

		public LearnerAttendancePane getAttendancePane();
	}

	@ContentSlot
	public static final Type<RevealContentHandler<?>> SLOT_LearnerAttendance = new Type<RevealContentHandler<?>>();

	@Inject
	private PlaceManager placeManager;

	@Inject
	private DispatchAsync dispatcher;

	DateTimeFormat dateTimeFormat = DateTimeFormat
			.getFormat(DatePattern.DAY_MONTH_YEAR_HOUR_MINUTE_SECONDS.getPattern());
	DateTimeFormat dateFormat = DateTimeFormat.getFormat(DatePattern.DAY_MONTH_YEAR.getPattern());

	@NameToken(NameTokens.learnerAttendance)
	@ProxyCodeSplit
	interface MyProxy extends ProxyPlace<LearnerAttendancePresenter> {
	}

	@Inject
	LearnerAttendancePresenter(EventBus eventBus, MyView view, MyProxy proxy) {
		super(eventBus, view, proxy, MainPresenter.SLOT_Main);
	}

	@Override
	protected void onBind() {
		super.onBind();
		setControlsPaneMenuButtons();
		//getAllLearnerAttendance();
	}

	public void setControlsPaneMenuButtons() {
		MenuButton newButton = new MenuButton("New");
		MenuButton edit = new MenuButton("Edit");
		MenuButton delete = new MenuButton("Delete");
		MenuButton filter = new MenuButton("Filter");

		List<MenuButton> buttons = new ArrayList<>();
		buttons.add(newButton);
		//buttons.add(edit);
		//buttons.add(delete);
		buttons.add(filter);

		getView().getControlsPane().addMenuButtons("Learner Attendance",buttons);
		loadAdvancedFilter();
		addLearnerAttendance(newButton);
		selectFilterOption(filter);

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
				getView().getAttendancePane().getLearnerAttendanceListGrid().setShowFilterEditor(true);
			}
		});

		advanced.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {

			@Override
			public void onClick(MenuItemClickEvent event) {
				
				loadAdvancedFilter();
			}
		});

	}
	
	private void loadAdvancedFilter() {
		FilterLearnerAttendanceWindow window = new FilterLearnerAttendanceWindow();
		loadFilterLearnerAttendanceAcademicYearCombo(window);
		loadFilterLearnerAttendanceAcademicTermCombo(window);
		loadFilterLearnerAttendanceDistrictCombo(window);
		loadFilterLearnerAttendanceSchoolCombo(window);
		filterLearnerAttendanceByAcademicYearAcademicTermDistrictSchool(window);
		window.show();
	}

//////////////////////LEARNER Attendance

	private void addLearnerAttendance(MenuButton newButton) {
		newButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				LearnerAttendanceWindow window = new LearnerAttendanceWindow();
				setTotalAbsentPresent(window);
				String defaultValue = null;
				loadAcademicYearCombo(window, defaultValue);
				loadAcademicTermCombo(window, defaultValue);
				loadDistrictCombo(window, defaultValue);
				loadSchoolCombo(window, defaultValue);
				loadSchoolClassCombo(window, defaultValue);
				loadSchoolStaffCombo(window, defaultValue);
				loadSchoolClassCombo(window, defaultValue);
				loadSchoolStaffCombo(window, defaultValue);
				window.show();

				saveLearnerAttendance(window);
			}

		});

	}

	private void saveLearnerAttendance(final LearnerAttendanceWindow window) {
		window.getSaveButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				if (checkIfNoLearnerAttendanceWindowFieldIsEmpty(window)) {
					LearnerAttendanceDTO dto = new LearnerAttendanceDTO();

					dto.setBoysAbsent(Long.parseLong(window.getBoysAbsentField().getValueAsString()));
					dto.setBoysPresent(Long.parseLong(window.getBoysPresentField().getValueAsString()));
					dto.setGirlsAbsent(Long.parseLong(window.getGirlsAbsentField().getValueAsString()));
					dto.setGirlsPresent(Long.parseLong(window.getGirlsPresentField().getValueAsString()));
					dto.setComment(window.getCommentField().getValueAsString());
					dto.setCreatedDateTime(dateTimeFormat.format(new Date()));
					dto.setAttendanceDate(dateFormat.format(new Date()));

					AcademicTermDTO academicTermDTO = new AcademicTermDTO(
							window.getAcademicTermCombo().getValueAsString());
					dto.setAcademicTermDTO(academicTermDTO);

					SchoolClassDTO schoolClassDTO = new SchoolClassDTO(window.getSchoolClassCombo().getValueAsString());
					dto.setSchoolClassDTO(schoolClassDTO);

					SchoolStaffDTO schoolStaffDTO = new SchoolStaffDTO(window.getSchoolStaffCombo().getValueAsString());
					dto.setSchoolStaffDTO(schoolStaffDTO);

					LinkedHashMap<String, Object> map = new LinkedHashMap<>();
					map.put(RequestConstant.SAVE_LEARNER_ATTENDANCE, dto);
					map.put(NetworkDataUtil.ACTION, RequestConstant.SAVE_LEARNER_ATTENDANCE);

					NetworkDataUtil.callNetwork(dispatcher, placeManager, map, new NetworkResult() {

						@Override
						public void onNetworkResult(RequestResult result) {
							clearAcademicYearWindowFields(window);
							window.close();
							SC.say("SUCCESS", result.getSystemFeedbackDTO().getMessage());
							getAllLearnerAttendance();
						}
					});
				} else {
					SC.warn("Please fill all fields");
				}

			}

		});
	}

	private void clearAcademicYearWindowFields(LearnerAttendanceWindow window) {

		window.getAcademicYearCombo().clearValue();
		window.getAcademicTermCombo().clearValue();
		window.getDistrictCombo().clearValue();
		window.getSchoolCombo().clearValue();
		window.getSchoolClassCombo().clearValue();
		window.getSchoolStaffCombo().clearValue();
		window.getCommentField().clearValue();
		window.getGirlsAbsentField().clearValue();
		window.getBoysAbsentField().clearValue();
		window.getGirlsPresentField().clearValue();
		window.getBoysPresentField().clearValue();
		window.getTotalAbsentField().clearValue();
		window.getTotalPresentField().clearValue();

	}

	private boolean checkIfNoLearnerAttendanceWindowFieldIsEmpty(LearnerAttendanceWindow window) {
		boolean flag = true;

		if (window.getAcademicTermCombo().getValueAsString() == null)
			flag = false;

		if (window.getAcademicYearCombo().getValueAsString() == null)
			flag = false;

		if (window.getDistrictCombo().getValueAsString() == null)
			flag = false;

		if (window.getSchoolCombo().getValueAsString() == null)
			flag = false;

		if (window.getSchoolClassCombo().getValueAsString() == null)
			flag = false;

		if (window.getSchoolStaffCombo().getValueAsString() == null)
			flag = false;

//	    if(window.getCommentField().getValueAsString() == null) flag = false;

		if (window.getGirlsAbsentField().getValueAsString() == null)
			flag = false;

		if (window.getBoysAbsentField().getValueAsString() == null)
			flag = false;

		if (window.getBoysPresentField().getValueAsString() == null)
			flag = false;

		if (window.getGirlsPresentField().getValueAsString() == null)
			flag = false;

		return flag;
	}

	private void loadAcademicYearCombo(final LearnerAttendanceWindow window, final String defaultValue) {
		ComboUtil.loadAcademicYearCombo(window.getAcademicYearCombo(), dispatcher, placeManager, defaultValue);
	}

	private void loadAcademicTermCombo(final LearnerAttendanceWindow window, final String defaultValue) {
		window.getAcademicYearCombo().addChangedHandler(new ChangedHandler() {

			@Override
			public void onChanged(ChangedEvent event) {
				ComboUtil.loadAcademicTermCombo(window.getAcademicTermCombo(), dispatcher, placeManager, defaultValue);
			}
		});

	}

	private void loadDistrictCombo(final LearnerAttendanceWindow window, final String defaultValue) {
		ComboUtil.loadDistrictCombo(window.getDistrictCombo(), dispatcher, placeManager, defaultValue);
	}

	private void loadSchoolCombo(final LearnerAttendanceWindow window, final String defaultValue) {
		window.getDistrictCombo().addChangedHandler(new ChangedHandler() {

			@Override
			public void onChanged(ChangedEvent event) {
				ComboUtil.loadSchoolComboByDistrict(window.getDistrictCombo(), window.getSchoolCombo(), dispatcher,
						placeManager, defaultValue);
			}
		});
	}

	private void loadSchoolStaffCombo(final LearnerAttendanceWindow window, final String defaultValue) {
		window.getSchoolCombo().addChangedHandler(new ChangedHandler() {

			@Override
			public void onChanged(ChangedEvent event) {
				ComboUtil.loadSchoolStaffComboBySchool(window.getSchoolCombo(), window.getSchoolStaffCombo(),
						dispatcher, placeManager, defaultValue);
			}
		});
	}

	private void loadSchoolClassCombo(final LearnerAttendanceWindow window, final String defaultValue) {
		window.getSchoolCombo().addChangedHandler(new ChangedHandler() {

			@Override
			public void onChanged(ChangedEvent event) {
				if (window.getAcademicTermCombo().getValueAsString() != null
						&& window.getSchoolCombo().getValueAsString() != null) {
					ComboUtil.loadSchoolClassesComboBySchoolAcademicTerm(window.getAcademicTermCombo(),
							window.getSchoolCombo(), window.getSchoolClassCombo(), dispatcher, placeManager,
							defaultValue);
				}
			}
		});

		window.getAcademicTermCombo().addChangedHandler(new ChangedHandler() {

			@Override
			public void onChanged(ChangedEvent event) {
				if (window.getAcademicTermCombo().getValueAsString() != null
						&& window.getSchoolCombo().getValueAsString() != null) {
					ComboUtil.loadSchoolClassesComboBySchoolAcademicTerm(window.getAcademicTermCombo(),
							window.getSchoolCombo(), window.getSchoolClassCombo(), dispatcher, placeManager,
							defaultValue);
				}
			}
		});
	}

	private void getAllLearnerAttendance() {
		LinkedHashMap<String, Object> map = new LinkedHashMap<>();
		if (SessionManager.getInstance().getLoggedInUserGroup().equalsIgnoreCase(SessionManager.ADMIN))
			map.put(NetworkDataUtil.ACTION, RequestConstant.GET_LEARNER_ATTENDANCE);
		else
			map.put(NetworkDataUtil.ACTION, RequestConstant.GET_LEARNER_ATTENDANCES_BY_SYSTEM_USER_PROFILE_SCHOOLS);

		NetworkDataUtil.callNetwork(dispatcher, placeManager, map, new NetworkResult() {

			@Override
			public void onNetworkResult(RequestResult result) {
				getView().getAttendancePane().getLearnerAttendanceListGrid()
						.addRecordsToGrid(result.getLearnerAttendanceDTOs());
			}
		});
	}

	public void setTotalAbsentPresent(final LearnerAttendanceWindow window) {
		final int[] totalGirlsPresent = new int[1];
		final int[] totalBoysPresent = new int[1];
		final int[] totalGirlsAbsent = new int[1];
		final int[] totalBoysAbsent = new int[1];
		final int[] totalPresent = { 0 };
		final int[] totalAbsent = { 0 };

		window.getGirlsAbsentField().addChangedHandler(new ChangedHandler() {

			@Override
			public void onChanged(ChangedEvent event) {
				if (window.getGirlsAbsentField().getValueAsString() == null) {
					totalGirlsAbsent[0] = 0;
				} else {
					totalGirlsAbsent[0] = Integer.parseInt(window.getGirlsAbsentField().getValueAsString());
				}

				if (window.getBoysAbsentField().getValueAsString() == null) {
					totalBoysAbsent[0] = 0;
				}

				totalAbsent[0] = totalGirlsAbsent[0] + totalBoysAbsent[0];
				window.getTotalAbsentField().setValue(totalAbsent[0]);
			}
		});

		window.getBoysAbsentField().addChangedHandler(new ChangedHandler() {

			@Override
			public void onChanged(ChangedEvent event) {

				if (window.getBoysAbsentField().getValueAsString() == null) {
					totalBoysAbsent[0] = 0;
				} else {
					totalBoysAbsent[0] = Integer.parseInt(window.getBoysAbsentField().getValueAsString());
				}

				if (window.getGirlsAbsentField().getValueAsString() == null) {
					totalGirlsAbsent[0] = 0;
				}

				totalAbsent[0] = totalBoysAbsent[0] + totalGirlsAbsent[0];
				window.getTotalAbsentField().setValue(totalAbsent[0]);
			}
		});

		///////////////////////////////////////// present
		window.getGirlsPresentField().addChangedHandler(new ChangedHandler() {

			@Override
			public void onChanged(ChangedEvent event) {
				if (window.getGirlsPresentField().getValueAsString() == null) {
					totalGirlsPresent[0] = 0;
				} else {
					totalGirlsPresent[0] = Integer.parseInt(window.getGirlsPresentField().getValueAsString());
				}

				if (window.getBoysPresentField().getValueAsString() == null) {
					totalBoysPresent[0] = 0;
				}

				totalPresent[0] = totalGirlsPresent[0] + totalBoysPresent[0];
				window.getTotalPresentField().setValue(totalPresent[0]);
			}
		});

		window.getBoysPresentField().addChangedHandler(new ChangedHandler() {

			@Override
			public void onChanged(ChangedEvent event) {
				if (window.getBoysPresentField().getValueAsString() == null) {
					totalBoysPresent[0] = 0;
				} else {
					totalBoysPresent[0] = Integer.parseInt(window.getBoysPresentField().getValueAsString());
				}

				if (window.getGirlsPresentField().getValueAsString() == null) {
					totalGirlsPresent[0] = 0;
				}

				totalPresent[0] = totalGirlsPresent[0] + totalBoysPresent[0];
				window.getTotalPresentField().setValue(totalPresent[0]);
			}
		});

	}

	//////////////////////// LOAD FILTER ATTENDANCE COMBOS

	// loads school combo in filter learner head count pane
	private void loadFilterLearnerAttendanceSchoolCombo(final FilterLearnerAttendanceWindow window) {
		window.getFilterLearnerAttendancePane().getDistrictCombo().addChangedHandler(new ChangedHandler() {

			@Override
			public void onChanged(ChangedEvent event) {
				ComboUtil.loadSchoolComboByDistrict(window.getFilterLearnerAttendancePane().getDistrictCombo(),
						window.getFilterLearnerAttendancePane().getSchoolCombo(), dispatcher, placeManager, null);
			}
		});

	}

	// loads district combo in filter learner head count pane
	private void loadFilterLearnerAttendanceDistrictCombo(final FilterLearnerAttendanceWindow window) {
		ComboUtil.loadDistrictCombo(window.getFilterLearnerAttendancePane().getDistrictCombo(), dispatcher,
				placeManager, null);
	}

	// loads academic year combo in filter learner head count pane
	private void loadFilterLearnerAttendanceAcademicYearCombo(final FilterLearnerAttendanceWindow window) {
		ComboUtil.loadAcademicYearCombo(window.getFilterLearnerAttendancePane().getAcademicYearCombo(), dispatcher,
				placeManager, null);
	}

	// loads academic year combo in filter learner head count pane
	private void loadFilterLearnerAttendanceAcademicTermCombo(final FilterLearnerAttendanceWindow window) {
		window.getFilterLearnerAttendancePane().getAcademicYearCombo().addChangedHandler(new ChangedHandler() {

			@Override
			public void onChanged(ChangedEvent event) {

				ComboUtil.loadAcademicTermComboByAcademicYear(
						window.getFilterLearnerAttendancePane().getAcademicYearCombo(),
						window.getFilterLearnerAttendancePane().getAcademicTermCombo(), dispatcher, placeManager, null);
			}
		});

	}

	////////////////////// END FILTER ATTENDANCE COMBOS

	// filter
	private void filterLearnerAttendanceByAcademicYearAcademicTermDistrictSchool(
			final FilterLearnerAttendanceWindow window) {
		window.getFilterButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				String academicYearId = window.getFilterLearnerAttendancePane().getAcademicYearCombo()
						.getValueAsString();
				String academicTermId = window.getFilterLearnerAttendancePane().getAcademicTermCombo()
						.getValueAsString();
				String districtId = window.getFilterLearnerAttendancePane().getDistrictCombo().getValueAsString();
				String schoolId = window.getFilterLearnerAttendancePane().getSchoolCombo().getValueAsString();
				String date = dateFormat
						.format(window.getFilterLearnerAttendancePane().getAttendanceDateItem().getValueAsDate());
				FilterDTO dto = new FilterDTO();
				dto.setAcademicYearDTO(new AcademicYearDTO(academicYearId));
				dto.setAcademicTermDTO(new AcademicTermDTO(academicTermId));
				dto.setDistrictDTO(new DistrictDTO(districtId));
				dto.setSchoolDTO(new SchoolDTO(schoolId));
				dto.setDate(date);

				LinkedHashMap<String, Object> map = new LinkedHashMap<>();
				map.put(RequestDelimeters.FILTER_LEARNER_ATTENDANCES, dto);

				map.put(NetworkDataUtil.ACTION,
						RequestConstant.FILTER_LEARNER_ATTENDANCE_BY_ACADEMIC_YEAR_ACADEMIC_TERM_DISTRICT_SCHOOL);

				NetworkDataUtil.callNetwork(dispatcher, placeManager, map, new NetworkResult() {

					@Override
					public void onNetworkResult(RequestResult result) {
						window.close();
						getView().getAttendancePane().getLearnerAttendanceListGrid()
								.addRecordsToGrid(result.getLearnerAttendanceDTOs());
					}
				});
			}
		});
	}

}