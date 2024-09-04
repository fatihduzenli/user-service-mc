package com.cydeo.util;

public class SwaggerExamples {

    private SwaggerExamples() {
        // Private constructor to prevent instantiation
    }

    public static final String USER_CREATE_REQUEST_EXAMPLE = "{\n" +
            "  \"firstName\": \"John\",\n" +
            "  \"lastName\": \"Doe\",\n" +
            "  \"userName\": \"john.doe@example.com\",\n" +
            "  \"password\": \"Pa$$w0rd\",\n" +
            "  \"phone\": \"1234567890\",\n" +
            "  \"role\":  \"Employee\",\n" +
            "  \"gender\": \"MALE\"\n" +
            "}";

    public static final String USER_UPDATE_REQUEST_EXAMPLE = "{\n" +
            "  \"firstName\": \"John\",\n" +
            "  \"lastName\": \"Doe\",\n" +
            "  \"password\": \"Pa$$w0rd\",\n" +
            "  \"phone\": \"1234567890\",\n" +
            "  \"role\":  \"Employee\",\n" +
            "  \"gender\": \"MALE\"\n" +
            "}";

    public static final String USER_CREATE_RESPONSE_EXAMPLE = "{\n" +
            "  \"success\": true,\n" +
            "  \"statusCode\": \"CREATED\",\n" +
            "  \"message\": \"User is successfully created.\",\n" +
            "  \"data\": {\n" +
            "    \"firstName\": \"John\",\n" +
            "    \"lastName\": \"Doe\",\n" +
            "    \"userName\": \"john.doe@example.com\",\n" +
            "    \"phone\": \"1234567890\",\n" +
            "    \"enabled\": true,\n" +
            "    \"role\": {\n" +
            "      \"description\": \"Employee\"\n" +
            "    },\n" +
            "    \"gender\": \"MALE\"\n" +
            "  }\n" +
            "}";

    public static final String USER_UPDATE_RESPONSE_EXAMPLE = "{\n" +
            "  \"success\": true,\n" +
            "  \"statusCode\": \"OK\",\n" +
            "  \"message\": \"User is successfully updated.\",\n" +
            "  \"data\": {\n" +
            "    \"firstName\": \"John\",\n" +
            "    \"lastName\": \"Doe\",\n" +
            "    \"userName\": \"john.doe@example.com\",\n" +
            "    \"phone\": \"1234567890\",\n" +
            "    \"enabled\": true,\n" +
            "    \"role\": {\n" +
            "      \"description\": \"Employee\"\n" +
            "    },\n" +
            "    \"gender\": \"MALE\"\n" +
            "  }\n" +
            "}";

    public static final String USER_GET_RESPONSE_SINGLE_EXAMPLE = "{\n" +
            "  \"success\": true,\n" +
            "  \"statusCode\": \"OK\",\n" +
            "  \"message\": \"User is successfully retrieved.\",\n" +
            "  \"data\": {\n" +
            "    \"firstName\": \"John\",\n" +
            "    \"lastName\": \"Doe\",\n" +
            "    \"userName\": \"john.doe@example.com\",\n" +
            "    \"phone\": \"1234567890\",\n" +
            "    \"enabled\": true,\n" +
            "    \"role\": {\n" +
            "      \"description\": \"Employee\"\n" +
            "    },\n" +
            "    \"gender\": \"MALE\"\n" +
            "  }\n" +
            "}";

    public static final String USER_GET_RESPONSE_LIST_EXAMPLE = "{\n" +
            "  \"success\": true,\n" +
            "  \"statusCode\": \"OK\",\n" +
            "  \"message\": \"Users are successfully retrieved.\",\n" +
            "  \"data\": [\n" +
            "    {\n" +
            "      \"firstName\": \"John\",\n" +
            "      \"lastName\": \"Doe\",\n" +
            "      \"userName\": \"john.doe@example.com\",\n" +
            "      \"phone\": \"1234567890\",\n" +
            "      \"enabled\": true,\n" +
            "      \"role\": {\n" +
            "        \"description\": \"Employee\"\n" +
            "      },\n" +
            "      \"gender\": \"MALE\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"firstName\": \"Jane\",\n" +
            "      \"lastName\": \"Doe\",\n" +
            "      \"userName\": \"jane.doe@example.com\",\n" +
            "      \"phone\": \"9876543210\",\n" +
            "      \"enabled\": true,\n" +
            "      \"role\": {\n" +
            "        \"description\": \"Manager\"\n" +
            "      },\n" +
            "      \"gender\": \"FEMALE\"\n" +
            "    }\n" +
            "  ]\n" +
            "}";

    public static final String USER_CHECK_RESPONSE_SINGLE_EXAMPLE = "{\n" +
            "  \"success\": true,\n" +
            "  \"statusCode\": \"OK\",\n" +
            "  \"message\": \"User exists.\",\n" +
            "  \"data\": true\n" +
            "}";

    public static final String ACCESS_DENIED_FORBIDDEN_RESPONSE_EXAMPLE = "{\n" +
            "  \"success\": false,\n" +
            "  \"message\": \"Access is denied\",\n" +
            "  \"httpStatus\": \"FORBIDDEN\",\n" +
            "  \"localDateTime\": \"2024-01-01T00:00:00.0000000\"\n" +
            "}";

    public static final String USER_ALREADY_EXISTS_RESPONSE_EXAMPLE = "{\n" +
            "  \"success\": false,\n" +
            "  \"message\": \"User already exists.\",\n" +
            "  \"httpStatus\": \"CONFLICT\",\n" +
            "  \"localDateTime\": \"2024-01-01T00:00:00.0000000\"\n" +
            "}";

    public static final String USER_NOT_FOUND_RESPONSE_EXAMPLE = "{\n" +
            "  \"success\": false,\n" +
            "  \"message\": \"User does not exist.\",\n" +
            "  \"httpStatus\": \"NOT_FOUND\",\n" +
            "  \"localDateTime\": \"2024-01-01T00:00:00.0000000\"\n" +
            "}";

    public static final String USER_NOT_DELETED_RESPONSE_EXAMPLE = "{\n" +
            "  \"success\": false,\n" +
            "  \"message\": \"User can not be deleted.\",\n" +
            "  \"httpStatus\": \"CONFLICT\",\n" +
            "  \"localDateTime\": \"2024-01-01T00:00:00.0000000\"\n" +
            "}";

    public static final String PROJECT_COUNT_NOT_RETRIEVED_RESPONSE_EXAMPLE = "{\n" +
            "  \"success\": false,\n" +
            "  \"message\": \"Project count cannot be retrieved.\",\n" +
            "  \"httpStatus\": \"INTERNAL_SERVER_ERROR\",\n" +
            "  \"localDateTime\": \"2024-01-01T00:00:00.0000000\"\n" +
            "}";

    public static final String TASK_COUNT_NOT_RETRIEVED_RESPONSE_EXAMPLE = "{\n" +
            "  \"success\": false,\n" +
            "  \"message\": \"Task count cannot be retrieved.\",\n" +
            "  \"httpStatus\": \"INTERNAL_SERVER_ERROR\",\n" +
            "  \"localDateTime\": \"2024-01-01T00:00:00.0000000\"\n" +
            "}";

    public static final String ROLE_NOT_FOUND_RESPONSE_EXAMPLE = "{\n" +
            "  \"success\": false,\n" +
            "  \"message\": \"Role does not exist.\",\n" +
            "  \"httpStatus\": \"NOT_FOUND\",\n" +
            "  \"localDateTime\": \"2024-01-01T00:00:00.0000000\"\n" +
            "}";

    public static final String VALIDATION_EXCEPTION_RESPONSE_EXAMPLE = "{\n" +
            "  \"success\": false,\n" +
            "  \"message\": \"Invalid Input(s)\",\n" +
            "  \"httpStatus\": \"BAD_REQUEST\",\n" +
            "  \"localDateTime\": \"2024-01-01T00:00:00.0000000\",\n" +
            "  \"errorCount\": 1,\n" +
            "  \"validationExceptions\": [\n" +
            "    {\n" +
            "      \"errorField\": \"userName\",\n" +
            "      \"rejectedValue\": \"john.doe.123456@email.com\",\n" +
            "      \"reason\": \"Username length should be min 3, max 16.\"\n" +
            "    }\n" +
            "  ]\n" +
            "}";

}
