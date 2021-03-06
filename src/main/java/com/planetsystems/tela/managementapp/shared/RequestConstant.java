package com.planetsystems.tela.managementapp.shared;

public class RequestConstant {
	public static final String SYSTEM_CONFIGURATION = "System Administration";
	public static final String SYSTEM_ENROLLMENT = "Enrollment";
	public static final String SYSTEM_SETTINGS = "Setting";
	public static final String SYSTEM_USERS = "System Users";
	public static final String SYSTEM_TIME_TABLES = "TimeTables";
	public static final String SYSTEM_HEAD_TEAHCER_SUPERVISION = "Head Teacher Supervision";
	public static final String SYSTEM_TEAHCER_DAILY_TASKS = "Teacher Daily Tasks";
	public static final String SYSTEM_REPORTS = "Performance Reports";
	public static final String SYSTEM_ATTENDANCE = "Attendance"; 

	public static final String CURRICULUM_COVERAGE = "Curriculum Coverage";
	public static final String INCENTIVES = "Incentives";
	public static final String UTILITY_MANAGER = "Utility Manager";
	
	public static final String SUPERVISION = "Supervision";
	
	public static final String SCHOOL_STATISTICS = "School Statistics";

	public static final String SAVE_ACADEMIC_YEAR = "SAVE_ACADEMIC_YEAR";
	public static final String UPDATE_ACADEMIC_YEAR = "UPDATE_ACADEMIC_YEAR";
	public static final String DELETE_ACADEMIC_YEAR = "DELETE_ACADEMIC_YEAR";
	public static final String GET_ACADEMIC_YEAR = "GET_ACADEMIC_YEAR";

	public static final String SAVE_ACADEMIC_TERM = "SAVE_ACADEMIC_TERM";
	public static final String UPDATE_ACADEMIC_TERM = "UPDATE_ACADEMIC_TERM";
	public static final String DELETE_ACADEMIC_TERM = "DELETE_ACADEMIC_TERM";
	public static final String GET_ACADEMIC_TERM = "GET_ACADEMIC_TERM";
	public static final String GET_ACADEMIC_TERMS_IN_ACADEMIC_YEAR = "GET_ACADEMIC_TERMS_IN_ACADEMIC_YEAR";
	public static final String FILTER_ACADEMIC_TERMS_BY_ACADEMIC_YEAR = "FILTER_ACADEMIC_TERMS_BY_ACADEMIC_YEAR";
	public static final String ACTIVATE_ACADEMIC_TERM = "ACTIVATE_ACADEMIC_TERM";
	public static final String DEACTIVATE_ACADEMIC_TERM = "DEACTIVATE_ACADEMIC_TERM";

	public static final String SAVE_REGION = "SAVE_REGION";
	public static final String UPDATE_REGION = "UPDATE_REGION";
	public static final String DELETE_REGION = "DELETE_REGION";
	public static final String GET_REGION = "GET_REGION";// associated handler block to be removed
	public static final String GET_REGIONS_BY_SYSTEM_USER_PROFILE_SCHOOLS = "GET_REGIONS_BY_SYSTEM_USER_PROFILE_SCHOOLS";

	public static final String SAVE_DISTRICT = "SAVE_DISTRICT";
	public static final String UPDATE_DISTRICT = "UPDATE_DISTRICT";
	public static final String DELETE_DISTRICT = "DELETE_DISTRICT";
	public static final String GET_DISTRICT = "GET_DISTRICT";// associated handler block to be deleted
	public static final String GET_DISTRICTS_BY_SYSTEM_USER_PROFILE_SCHOOLS = "GET_DISTRICTS_BY_SYSTEM_USER_PROFILE_SCHOOLS";
	public static final String GET_DISTRICTS_BY_SYSTEM_USER_PROFILE_SCHOOLS_REGION = "GET_DISTRICTS_BY_SYSTEM_USER_PROFILE_SCHOOLS_REGION";
	public static final String GET_DISTRICTS_IN_REGION = "GET_DISTRICTS_IN_REGION";

	public static final String FILTER_DISTRICTS_BY_REGION = "FILTER_DISTRICTS_BY_REGION";

	public static final String SAVE_SCHOOL_CATEGORY = "SAVE_SCHOOL_CATEGORY";
	public static final String UPDATE_SCHOOL_CATEGORY = "UPDATE_SCHOOL_CATEGORY";
	public static final String DELETE_SCHOOL_CATEGORY = "DELETE_SCHOOL_CATEGORY";
	public static final String GET_SCHOOL_CATEGORY = "GET_SCHOOL_CATEGORY";// associated handler block to be removed
	public static final String GET_SCHOOL_CATEGORIES_BY_SYSTEM_USER_PROFILE_SCHOOLS = "GET_SCHOOL_CATEGORIES_BY_SYSTEM_USER_PROFILE_SCHOOLS";

	public static final String SAVE_SCHOOL = "SAVE_SCHOOL";
	public static final String UPDATE_SCHOOL = "UPDATE_SCHOOL";
	public static final String DELETE_SCHOOL = "DELETE_SCHOOL";
	public static final String GET_SCHOOLS = "GET_SCHOOLS";
	public static final String GET_SCHOOLS_IN_DISTRICT = "GET_SCHOOLS_IN_DISTRICT";
	public static final String GET_SCHOOLS_IN_SCHOOL_CATEGORY_DISTRICT = "GET_SCHOOLS_IN_SCHOOL_CATEGORY_DISTRICT";
	public static final String GET_SCHOOLS_BY_SYSTEM_USER_PROFILE_SCHOOLS = "GET_SCHOOLS_BY_SYSTEM_USER_PROFILE_SCHOOLS";
	public static final String FILTER_SCHOOLS_BY_SCHOOL_CATEGORY_REGION_DISTRICT = "FILTER_SCHOOLS_BY_SCHOOL_CATEGORY_REGION_DISTRICT";

	public static final String SAVE_SCHOOL_CLASS = "SAVE_SCHOOL_CLASS";
	public static final String UPDATE_SCHOOL_CLASS = "UPDATE_SCHOOL_CLASS";
	public static final String DELETE_SCHOOL_CLASS = "DELETE_SCHOOL_CLASS";
	public static final String GET_SCHOOL_CLASS = "GET_SCHOOL_CLASS"; // ASSOCIATED HANDLER BLOCK TO BE REMOVED
	public static final String GET_SCHOOL_CLASSES_BY_SYSTEM_USER_PROFILE_SCHOOLS = "GET_SCHOOL_CLASSES_BY_SYSTEM_USER_PROFILE_SCHOOLS";
	public static final String GET_SCHOOL_CLASSES_IN_SCHOOL = "GET_SCHOOL_CLASSES_IN_SCHOOL";
	public static final String GET_SCHOOL_CLASSES_IN_SCHOOL_ACADEMIC_TERM = "GET_SCHOOL_CLASSES_IN_SCHOOL_ACADEMIC_TERM";
	public static final String FILTER_SCHOOL_CLASS_BY_ACADEMIC_YEAR_TERM_DISTRICT_SCHOOL = "FILTER_SCHOOL_CLASS_BY_ACADEMIC_YEAR_TERM_DISTRICT_SCHOOL";

	public static final String SAVE_SUBJECT_CATEGORY = "SAVE_SUBJECT_CATEGORY";
	public static final String UPDATE_SUBJECT_CATEGORY = "UPDATE_SUBJECT_CATEGORY";
	public static final String DELETE_SUBJECT_CATEGORY = "DELETE_SUBJECT_CATEGORY";
	public static final String GET_SUBJECT_CATEGORY = "GET_SUBJECT_CATEGORY";

	public static final String SAVE_SUBJECT = "SAVE_SUBJECT";
	public static final String UPDATE_SUBJECT = "UPDATE_SUBJECT";
	public static final String DELETE_SUBJECT = "DELETE_SUBJECT";
	public static final String GET_SUBJECT = "GET_SUBJECT";
	public static final String GET_SUBJECTS_IN_SUBJECT_CATEGORY = "GET_SUBJECTS_IN_SUBJECT_CATEGORY";
	public static final String FILTER_SUBJECTS_BY_SUBJECT_CATEGORY = "FILTER_SUBJECTS_BY_SUBJECT_CATEGORY";

	public static final String LOGIN = "LOGIN";
	public static final String LOGIN_TOKEN = "LOGIN_TOKEN";// handler key
	public static final String LOGED_IN = "LOGED_IN";// status
	public static final String AUTH_TOKEN = "AUTH_TOKEN"; // token key
	public static final String USERNAME = "USERNAME"; // Username
	
	public static final String REQUEST_DATA = "REQUEST_DATA";

	public static final String SAVE_SCHOOL_STAFF = "SAVE_SCHOOL_STAFF";
	public static final String UPDATE_SCHOOL_STAFF = "UPDATE_SCHOOL_STAFF";
	public static final String DELETE_SCHOOL_STAFF = "DELETE_SCHOOL_STAFF";
	public static final String GET_SCHOOL_STAFF = "GET_SCHOOL_STAFF";
	public static final String GET_SCHOOL_STAFFS_BY_SYSTEM_USER_PROFILE_SCHOOLS = "GET_SCHOOL_STAFFS_BY_SYSTEM_USER_PROFILE_SCHOOLS";
	
	public static final String DELETE_SCHOOL_STAFF_BULK = "DELETE_SCHOOL_STAFF_BULK";

	public static final String GET_STAFFS_IN_SCHOOL = "GET_STAFFS_IN_SCHOOL";
	public static final String GET_SCHOOL_STAFFS_IN_DISTRICT_SCHOOL = "GET_SCHOOL_STAFFS_IN_DISTRICT_SCHOOL";
	public static final String FILTER_SCHOOL_STAFFS_BY_DISTRICT_SCHOOL = "FILTER_SCHOOL_STAFFS_BY_DISTRICT_SCHOOL";
	public static final String GET_ABSENT_SCHOOL_STAFF_BY_TERM_SCHOOL_DATE = "GET_ABSENT_SCHOOL_STAFF_BY_TERM_SCHOOL_DATE";

	public static final String SAVE_LEARNER_ENROLLMENT = "SAVE_LEARNER_ENROLLMENT";
	public static final String UPDATE_LEARNER_ENROLLMENT = "UPDATE_LEARNER_ENROLLMENT";
	public static final String DELETE_LEARNER_ENROLLMENT = "DELETE_LEARNER_ENROLLMENT";
	public static final String GET_LEARNER_ENROLLMENT = "GET_LEARNER_ENROLLMENT";
	public static final String GET_LEARNER_ENROLLMENTS_BY_SYSTEM_USER_PROFILE_SCHOOLS = "GET_LEARNER_ENROLLMENTS_BY_SYSTEM_USER_PROFILE_SCHOOLS";
	public static final String GET_LEARNER_ENROLLMENTS_IN_ACADEMIC_YEAR_ACADEMIC_TERM_DISTRICT_SCHOOL = "GET_LEARNER_ENROLLMENTS_IN_ACADEMIC_YEAR_ACADEMIC_TERM_DISTRICT_SCHOOL";
	public static final String FILTER_LEARNER_ENROLLMENTS_BY_ACADEMIC_YEAR_ACADEMIC_TERM_DISTRICT_SCHOOL = "FILTER_LEARNER_ENROLLMENTS_BY_ACADEMIC_YEAR_ACADEMIC_TERM_DISTRICT_SCHOOL";

	public static final String SAVE_STAFF_ENROLLMENT = "SAVE_STAFF_ENROLLMENT";
	public static final String UPDATE_STAFF_ENROLLMENT = "UPDATE_STAFF_ENROLLMENT";
	public static final String DELETE_STAFF_ENROLLMENT = "DELETE_STAFF_ENROLLMENT";
	public static final String GET_STAFF_ENROLLMENT = "GET_STAFF_ENROLLMENT";
	public static final String GET_STAFF_ENROLLMENTS_SYSTEM_USER_PROFILE_SCHOOLS = "GET_STAFF_ENROLLMENTS_SYSTEM_USER_PROFILE_SCHOOLS";
	public static final String GET_SCHOOL_STAFF_ENROLLMENTS_IN_ACADEMIC_YEAR_ACADEMIC_TERM_DISTRICT_SCHOOL = "GET_SCHOOL_STAFF_ENROLLMENTS_IN_ACADEMIC_YEAR_ACADEMIC_TERM_DISTRICT_SCHOOL";
	public static final String FILTER_SCHOOL_STAFF_ENROLLMENTS_BY_ACADEMIC_YEAR_ACADEMIC_TERM_DISTRICT_SCHOOL = "FILTER_SCHOOL_STAFF_ENROLLMENTS_BY_ACADEMIC_YEAR_ACADEMIC_TERM_DISTRICT_SCHOOL";

	public static final String SAVE_CLOCK_IN = "SAVE_CLOCK_IN";
	public static final String UPDATE_CLOCK_IN = "UPDATE_CLOCK_IN";
	public static final String DELETE_CLOCK_IN = "DELETE_CLOCK_IN";
	public static final String GET_CLOCK_IN = "GET_CLOCK_IN";// D
	public static final String GET_CLOCK_INS_BY_SYSTEM_USER_PROFILE_SCHOOLS = "GET_CLOCK_INS_BY_SYSTEM_USER_PROFILE_SCHOOLS";
	public static final String GET_CLOCKINS_IN_ACADEMIC_YEAR_ACADEMIC_TERM_DISTRICT_SCHOOL = "GET_CLOCKINS_IN_ACADEMIC_YEAR_ACADEMIC_TERM_DISTRICT_SCHOOL";
	public static final String FILTER_CLOCKINS_BY_ACADEMIC_YEAR_ACADEMIC_TERM_DISTRICT_SCHOOL = "FILTER_CLOCKINS_BY_ACADEMIC_YEAR_ACADEMIC_TERM_DISTRICT_SCHOOL";

	public static final String GET_CLOCK_IN_By_ACADEMIC_TERM_SCHOOL_DATE = "GET_CLOCK_IN_By_ACADEMIC_TERM_SCHOOL_DATE";

	public static final String SAVE_CLOCK_OUT = "SAVE_CLOCK_OUT";
	public static final String UPDATE_CLOCK_OUT = "UPDATE_CLOCK_OUT";
	public static final String DELETE_CLOCK_OUT = "DELETE_CLOCK_OUT";
	public static final String GET_CLOCK_OUT = "GET_CLOCK_OUT";// D
	public static final String GET_CLOCK_OUTS_BY_SYSTEM_USER_PROFILE_SCHOOLS = "GET_CLOCK_OUTS_BY_SYSTEM_USER_PROFILE_SCHOOLS";

	public static final String GET_CLOCK_OUTS_IN_ACADEMIC_YEAR_ACADEMIC_TERM_DISTRICT_SCHOOL = "GET_CLOCK_OUTS_IN_ACADEMIC_YEAR_ACADEMIC_TERM_DISTRICT_SCHOOL";
	public static final String FILTER_CLOCK_OUTS_BY_ACADEMIC_YEAR_ACADEMIC_TERM_DISTRICT_SCHOOL = "FILTER_CLOCK_OUTS_BY_ACADEMIC_YEAR_ACADEMIC_TERM_DISTRICT_SCHOOL";

	public static final String GET_CLOCK_OUT_BY_TERM_SCHOOL_DATE = "GET_CLOCK_OUT_BY_TERM_SCHOOL_DATE";

	public static final String SAVE_LEARNER_ATTENDANCE = "SAVE_LEARNER_ATTENDANCE";
	public static final String UPDATE_LEARNER_ATTENDANCE = "UPDATE_LEARNER_ATTENDANCE";
	public static final String DELETE_LEARNER_ATTENDANCE = "DELETE_LEARNER_ATTENDANCE";
	public static final String GET_LEARNER_ATTENDANCE = "GET_LEARNER_ATTENDANCE";
	public static final String GET_LEARNER_ATTENDANCES_BY_SYSTEM_USER_PROFILE_SCHOOLS = "GET_LEARNER_ATTENDANCES_BY_SYSTEM_USER_PROFILE_SCHOOLS";
	public static final String GET_LEARNER_ATTENDANCE_IN_ACADEMIC_YEAR_ACADEMIC_TERM_DISTRICT_SCHOOL = "GET_LEARNER_ATTENDANCE_IN_ACADEMIC_YEAR_ACADEMIC_TERM_DISTRICT_SCHOOL";
	public static final String FILTER_LEARNER_ATTENDANCE_BY_ACADEMIC_YEAR_ACADEMIC_TERM_DISTRICT_SCHOOL = "FILTER_LEARNER_ATTENDANCE_BY_ACADEMIC_YEAR_ACADEMIC_TERM_DISTRICT_SCHOOL";

	// timetables
	public static final String SAVE_TIME_TABLE = "SAVE_TIME_TABLE";
	public static final String GET_TIME_TABLES = "GET_TIME_TABLES";
	public static final String GET_TIME_TABLES_BY_SYSTEM_USER_PROFILE_SCHOOLS = "GET_TIME_TABLES_BY_SYSTEM_USER_PROFILE_SCHOOLS";

	// system users
	public static final String SAVE_SYSTEM_USER = "SAVE_SYSTEM_USER";
	public static final String GET_ALL_SYSTEM_USERS = "GET_ALL_SYSTEM_USERS";

	public static final String SAVE_SystemMENU = "SAVE_SystemMENU";
	public static final String DELETE_SystemMENU = "DELETE_SystemMENU";
	public static final String GET_SystemMENU = "GET_SystemMENU";

	public static final String SAVE_USER_GROUP = "SAVE_USER_GROUP";
	public static final String UPDATE_USER_GROUP = "UPDATE_USER_GROUP";
	public static final String DELETE_USER_GROUP = "DELETE_USER_GROUP";
	public static final String GET_USER_GROUP = "GET_USER_GROUP";

	public static final String SAVE_USER_GROUP_SystemMENU = "SAVE_USER_GROUP_SystemMENU";
	public static final String GET_USER_GROUP_SystemMENU = "GET_USER_GROUP_SystemMENU";

	public static final String GET_LOGED_IN_USER_SYSTEM_MENUS = "GET_LOGED_IN_USER_SYSTEM_MENUS";
	public static final String GET_LOGEDIN_USER_SystemMENU = "GET_LOGEDIN_USER_SystemMENU";

	// time table lesson
	public static final String SAVE_TIME_TABLE_LESSON_BY_TIME_TABLE = "SAVE_TIME_TABLE_LESSON_BY_TIME_TABLE";
	public static final String GET_TIME_TABLE_LESSONS_BY_TIME_TABLE = "GET_TIME_TABLE_LESSONS_BY_TIME_TABLE";
	public static final String GET_TIME_TABLE_LESSONS_FOR_STAFF_ACADEMIC_YEAR_TERM_DISTRICT_SCHOOL_DAY = "GET_TIME_TABLE_LESSONS_FOR_STAFF_ACADEMIC_YEAR_TERM_DISTRICT_SCHOOL_DAY";
	public static final String DELETE_TIME_TABLE_LESSON = "DELETE_TIME_TABLE_LESSON";

	// staff daily time table
	public static final String GET_STAFF_DAILY_TIMETABLE_ACADEMIC_YEAR_TERM_DISTRICT_SCHOOL_DATE = "GET_STAFF_DAILY_TIMETABLE_ACADEMIC_YEAR_TERM_DISTRICT_SCHOOL_DATE";
	public static final String GET_STAFF_DAILY_TIMETABLES_BY_SYSTEM_USER_PROFILE_SCHOOLS_ACADEMIC_YEAR_TERM_DISTRICT_SCHOOL_DATE = "GET_STAFF_DAILY_TIMETABLES_BY_SYSTEM_USER_PROFILE_SCHOOLS_ACADEMIC_YEAR_TERM_DISTRICT_SCHOOL_DATE";

	// staff daily time table lessons
	public static final String SAVE_STAFF_DAILY_TIMETABLE_LESSONS = "SAVE_STAFF_DAILY_TIMETABLE_LESSONS";
	public static final String GET_STAFF_DAILY_TIMETABLE_LESSONS_BY_SCHOOL_STAFF_DATE = "GET_STAFF_DAILY_TIMETABLE_LESSONS_BY_SCHOOL_STAFF_DATE";
	public static final String GET_STAFF_DAILY_TIMETABLE_LESSONS_BY_SYSTEM_USER_PROFILE_SCHOOLS_DAILY_TIMETABLE_SCHOOL_STAFF_DATE = "GET_STAFF_DAILY_TIMETABLE_LESSONS_BY_SYSTEM_USER_PROFILE_SCHOOLS_DAILY_TIMETABLE_SCHOOL_STAFF_DATE";
	public static final String GET_STAFF_DAILY_TIMETABLE_LESSONS_FOR_STAFF_DATE_DAILY_TIMETABLE = "GET_STAFF_DAILY_TIMETABLE_LESSONS_FOR_STAFF_DATE_DAILY_TIMETABLE";

	// public static final String GET_STAFF_DAILY_ATTENDANCE_SCHOOL_STAFF_DATE =
	// "GET_STAFF_DAILY_ATTENDANCE_SCHOOL_STAFF_DATE";
	//

	public static final String MIGRATE_DATA = "MIGRATE_DATA";
	public static final String MIGRATE_DATA_ATTENDACE = "MIGRATE_DATA_ATTENDACE";
	public static final String MIGRATE_DATA_TIMETABLES = "MIGRATE_DATA_TIMETABLES";

	public static final String MIGRATE_DATA_SUBJECTS = "MIGRATE_DATA_SUBJECTS";

	// daily supervision
	public static final String SAVE_STAFF_DAILY_TASK_SUPERVISIONS = "SAVE_STAFF_DAILY_TASK_SUPERVISIONS";
	public static final String GET_STAFF_DAILY_SUPERVISIONS_IN_SCHOOL_DATE = "GET_STAFF_DAILY_SUPERVISIONS_IN_SCHOOL_DATE";
	public static final String GET_STAFF_DAILY_SUPERVISIONS_BY_SYSTEM_USER_PROFILE_SCHOOLS_SCHOOL_DATE = "GET_STAFF_DAILY_SUPERVISIONS_BY_SYSTEM_USER_PROFILE_SCHOOLS_SCHOOL_DATE";

	// daily supervision tasks
	public static final String GET_STAFF_DAILY_ATTENDANCE_TASK_SUPERVISIONS_FOR_STAFF_DATE_DAILY_ATTENDANCE_SUPERVISION = "GET_STAFF_DAILY_ATTENDANCE_TASK_SUPERVISIONS_FOR_STAFF_DATE_DAILY_ATTENDANCE_SUPERVISION";
	public static final String GET_STAFF_DAILY_ATTENDANCE_TASK_SUPERVISIONS_BY_SYSTEM_USER_PROFILE_SCHOOLS_STAFF_DATE_DAILY_ATTENDANCE_SUPERVISION = "GET_STAFF_DAILY_ATTENDANCE_TASK_SUPERVISIONS_BY_SYSTEM_USER_PROFILE_SCHOOLS_STAFF_DATE_DAILY_ATTENDANCE_SUPERVISION";

	public static final String SAVE_TIME_ATTENDANCE_SUPERVISION = "SAVE_TIME_ATTENDANCE_SUPERVISION";

	public static final String GET_DEFAULT_ENROLLMENT_DASHBOARD = "GET_DEFAULT_ENROLLMENT_DASHBOARD";
	public static final String GET_DEFAULT_ATTENDANCE_DASHBOARD = "GET_DEFAULT_ATTENDANCE_DASHBOARD";
	
	public static final String GET_DISTRICT_SUMMARY_DASHBOARD = "GET_DISTRICT_SUMMARY_DASHBOARD";

	/// SYSTEM_USER_PROFILE_SCHOOLS_PROFILE
	// public static final String SAVE_SYSTEM_USER_PROFILE_SCHOOLS =
	/// "SAVE_SYSTEM_USER_PROFILE_SCHOOLS";
	public static final String SAVE_SYSTEM_USER_PROFILE_SCHOOLS_PROFILE = "SAVE_SYSTEM_USER_PROFILE_SCHOOLS_PROFILE";
	public static final String GET_SCHOOLS_BY_SYSTEM_USER_PROFILE_SCHOOLS_PROFILE = "GET_SCHOOLS_BY_SYSTEM_USER_PROFILE_SCHOOLS_PROFILE";
	public static final String GET_NOT_SCHOOLS_BY_SYSTEM_USER_PROFILE_SCHOOLS_PROFILE_DISTRICT = "GET_NOT_SCHOOLS_BY_SYSTEM_USER_PROFILE_SCHOOLS_PROFILE_DISTRICT";
	public static final String DELETE_SYSTEM_USER_PROFILE_SCHOOLS = "DELETE_SYSTEM_USER_PROFILE_SCHOOLS";
	public static final String DELETE_SYSTEM_USER_PROFILE_SCHOOLS_PROFILE = "DELETE_SYSTEM_USER_PROFILE_SCHOOLS_PROFILE";
 

	public static final String LOGGED_SYSTEM_USER_GROUP = "LOGGED_SYSTEM_USER_GROUP";
	public static final String LOGGED_IN_SYSTEM_USER_GROUP_COOKIE = "LOGGED_IN_SYSTEM_USER_GROUP_COOKIE";

	public static final String DistrictEndOfWeekTimeAttendance = "DistrictEndOfWeekTimeAttendance";
	public static final String DistrictEndOfMonthTimeAttendance = "DistrictEndOfMonthTimeAttendance";
	public static final String DistrictEndOfTermTimeAttendance = "DistrictEndOfTermTimeAttendance";

	public static final String DistrictEndOfWeekTimeAttendanceReport = "DistrictEndOfWeekTimeAttendanceReport";
	public static final String DistrictEndOfMonthTimeAttendanceReport = "DistrictEndOfMonthTimeAttendanceReport";
	public static final String DistrictEndOfTermTimeAttendanceReport = "DistrictEndOfTermTimeAttendanceReport";

	public static final String NationalEndOfWeekTimeAttendance = "NationalEndOfWeekTimeAttendance";
	public static final String NationalEndOfMonthTimeAttendance = "NationalEndOfMonthTimeAttendance";
	public static final String NationalEndOfTermTimeAttendance = "NationalEndOfTermTimeAttendance";
	public static final String RESET_PASSWORD = "RESET_PASSWORD";

	public static final String NationalEndOfWeekTimeAttendanceReport = "NationalEndOfWeekTimeAttendanceReport";
	public static final String NationalEndOfMonthTimeAttendanceReport = "NationalEndOfMonthTimeAttendanceReport";
	public static final String NationalEndOfTermTimeAttendanceReport = "NationalEndOfTermTimeAttendanceReport";
	public static final String CHANGE_PASSWORD = "CHANGE_PASSWORD";
	
	
	public static final String TeacherTimeAttendanceReportExport = "TeacherTimeAttendanceReportExport"; 
	
	public static final String SchoolEndOfWeekTimeAttendanceReportExport  = "SchoolEndOfWeekTimeAttendanceReportExport";
	public static final String SchoolEndOfMonthTimeAttendanceReportExport  = "SchoolEndOfMonthTimeAttendanceReportExport";
	public static final String SchoolEndOfTermTimeAttendanceReportExport  = "SchoolEndOfTermTimeAttendanceReportExport";
	
	
	public static final String HeadTeacherTimeAttendanceReportExport = "HeadTeacherTimeAttendanceReportExport";
	public static final String HeadTeacherEndOfWeekTimeAttendanceReportExport  = "HeadTeacherEndOfWeekTimeAttendanceReportExport";
	public static final String HeadTeacherEndOfMonthTimeAttendanceReportExport  = "HeadTeacherEndOfMonthTimeAttendanceReportExport";
	public static final String HeadTeacherEndOfTermTimeAttendanceReportExport  = "HeadTeacherEndOfTermTimeAttendanceReportExport";
	
	public static final String HeadTeacherSupervidsionReportExport  = "HeadTeacherSupervidsionReportExport";
	
	public static final String SMCSupervidsionReportExport  = "SMCSupervidsionReportExport";

	public static final String TimeOnTaskReportExport  = "TimeOnTaskReportExport";

	public static final String FILTER_CLOCKINS = "FILTER_CLOCKINS";
	public static final String FILTER_CLOCK_OUTS = "FILTER_CLOCK_OUTS";
	public static final String FILTER_STAFF_DAILY_TIMETABLES = "FILTER_STAFF_DAILY_TIMETABLES";
	public static final String FILTER_STAFF_DAILY_TIMETABLE_LESSONS = "FILTER_STAFF_DAILY_TIMETABLE_LESSONS";
	public static final String FILTER_STAFF_DAILY_SUPERVISIONS = "FILTER_STAFF_DAILY_SUPERVISIONS";
	public static final String FILTER_STAFF_DAILY_ATTENDANCE_TASK_SUPERVISIONS = "FILTER_STAFF_DAILY_ATTENDANCE_TASK_SUPERVISIONS";
	
	
	public static final String FILTER_SMC_SUPERVISION_BY_ACADEMIC_YEAR_ACADEMIC_TERM_DISTRICT_SCHOOL = "FILTER_SMC_SUPERVISION_BY_ACADEMIC_YEAR_ACADEMIC_TERM_DISTRICT_SCHOOL";

	

}
