package com.user.constants;

public class Constants {
//		Environment Properties
	 	public static final String URL 							= "jdbc:mysql://localhost:3306/task1";
	    public static final String USERNAME 					= "root";
	    public static final String PASSWORD 					= "root";
	    public static final String DRIVER_CLASS_NAME 			= "com.mysql.cj.jdbc.Driver";

//	    Controller Database Properties
	    public static final String TABLE_NAME 					= "tb_user";

//	    Controller Success Messages
	    public static final String INSERT_SUCCESS 				= "Record Inserted Successfully";
		public static final String UPDATE_SUCCESS 				= "Record Updated Successfully";
		public static final String DELETE_SUCCESS 				= "Record Deleted Successfully";

//	    Controller Error Messages
	    public static final String INSERT_ERROR 				= "Failed To Add Record";
		public static final String UPDATE_ERROR 				= "Failed To Update Record";
		public static final String DELETE_ERROR 				= "Failed To Delete Record";

//	    Controller Exception Error Messages
	    public static final String INSERT_EXCEPTION_ERROR 		= "Failed To Add Record";
		public static final String UPDATE_EXCEPTION_ERROR 		= "Failed To Update Record";
		public static final String DELETE_EXCEPTION_ERROR 		= "Failed To Update Record";

//		Regex Patterns
		public static final String USER_NAME_REGEX 				= "[A-Za-z\\s]+";
		public static final String EMAIL_ADDRESS_REGEX 			= "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$";
		public static final String PHONE_NUMBER_REGEX 			= "(0/91)?[7-9][0-9]{9}";

//		Validation Error Messages

		public static final String INVALID_ROLE_ID_FORMAT 		= "Invalid role id format. Roleid must be greater than zero";
		public static final String INVALID_USERNAME_FORMAT	 	= "Invalid username. Name must contain only letters (A-Z, a-z).";
		public static final String INVALID_EMAIL_FORMAT 		= "Invalid email format. Please enter a valid email address.";
		public static final String INVALID_PHONE_FORMAT 		= "Invalid Phone Number. Phone number must be 10 digits in length and consist of numbers (0-9) only.";

//		Conversion Error(Repository)
		public static final String JSON_ERROR 					= "Error converting skills to JSON";

}

