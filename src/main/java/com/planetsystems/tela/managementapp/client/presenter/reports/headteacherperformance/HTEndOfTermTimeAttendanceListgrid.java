package com.planetsystems.tela.managementapp.client.presenter.reports.headteacherperformance;

import java.util.List;

import com.planetsystems.tela.dto.reports.SchoolEndOfTermTimeAttendanceDTO;
import com.planetsystems.tela.managementapp.client.widget.SuperListGrid;
import com.planetsystems.tela.managementapp.shared.UtilityManager;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;

public class HTEndOfTermTimeAttendanceListgrid extends SuperListGrid {

	public static String STAFF = "employee";
	public static String MONTH_1 = "month1";
	public static String MONTH_2 = "month2";
	public static String MONTH_3 = "month3";
	public static String TOTAL = "total";

	public static String EXPECTED = "expected";
	public static String BALANCE = "balance";
	public static String PERCENT = "percent";

	public HTEndOfTermTimeAttendanceListgrid() {
		super();
		ListGridField employee = new ListGridField(STAFF, "HeacherTeacher");
		ListGridField month1 = new ListGridField(MONTH_1, "Month 1");
		ListGridField month2 = new ListGridField(MONTH_2, "Month 2");
		ListGridField month3 = new ListGridField(MONTH_3, "Month 3");

		ListGridField total = new ListGridField(TOTAL, "Total Hours of HeacherTeacher's on Site");

		ListGridField expected = new ListGridField(EXPECTED, "Total Number of Hours Expected on Site");
		ListGridField balance = new ListGridField(BALANCE, "Variance");
		ListGridField percent = new ListGridField(PERCENT, "Percentage (%)");

		this.setFields(employee, month1, month2, month3, total, expected, balance, percent);

		this.setWrapHeaderTitles(true);
		this.setHeaderHeight(50);

	}

	public ListGridRecord addRowData(SchoolEndOfTermTimeAttendanceDTO dto) {
		ListGridRecord record = new ListGridRecord();

		if (dto.getStaff() != null) {
			record.setAttribute(STAFF, dto.getStaff());
		}

		if (dto.getMonth1() != null) {
			record.setAttribute(MONTH_1, dto.getMonth1());
		} else {
			record.setAttribute(MONTH_1, "__");
		}

		if (dto.getMonth2() != null) {
			record.setAttribute(MONTH_2, dto.getMonth2());
		} else {
			record.setAttribute(MONTH_2, "__");
		}

		if (dto.getMonth3() != null) {
			record.setAttribute(MONTH_3, dto.getMonth3());
		} else {
			record.setAttribute(MONTH_3, "__");
		}

		record.setAttribute(TOTAL, dto.getTotalTime());

		record.setAttribute(EXPECTED, dto.getExpectedHours());
		record.setAttribute(BALANCE, dto.getBalance());
		// record.setAttribute(PERCENT, timeSheet.getPercentage()+"%");
		record.setAttribute(PERCENT, UtilityManager.getInstance().formatCash(dto.getPercentage()) + "%");

		return record;
	}

	public void addRecordsToGrid(List<SchoolEndOfTermTimeAttendanceDTO> list) {
		ListGridRecord[] records = new ListGridRecord[list.size()];
		int row = 0;
		for (SchoolEndOfTermTimeAttendanceDTO item : list) {
			records[row] = addRowData(item);
			row++;
		}
		this.setData(records);
	}

	@Override
	protected String getCellCSSText(ListGridRecord record, int rowNum, int colNum) {
		if (getFieldName(colNum).equals(PERCENT)) {

			String percentage = (record.getAttribute(PERCENT)).replace("%", "");

			float percent = Float.parseFloat(percentage);

			if (percent < 75) {
				// red
				return "font-weight:bold; color:#C9C5C5; font-size:14;background-color:#c20215";
			}

			else if (percent >= 75 && percent < 90) {
				// yellow
				return "font-weight:bold; color:#000000; font-size:14; background-color:#fcb143";
			} else if (percent >= 90) {
				// green
				return "font-weight:bold; color:#C9C5C5; font-size:14;background-color:#5eb96d";

			} else {
				return "font-weight:bold; color:#C9C5C5; font-size:14;background-color:#c20215";
			}

		}

		else {
			return super.getCellCSSText(record, rowNum, colNum);
		}

	}

}
