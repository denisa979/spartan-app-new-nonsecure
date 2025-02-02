package com.spartan.util;

public class SwaggerExamples {

    private SwaggerExamples() {
    }

    public static final String HELLO_WORLD_JSON_RESPONSE = """
            {
              "message": "Hello World!"
            }
            """;

    public static final String HELLO_WORLD_XML_RESPONSE = """
            <HelloResponseDTO>
              <message>Hello World!</message>
            </HelloResponseDTO>
            """;

    public static final String PATCH_SPARTAN_REQUEST = """
            {
                "name": "Leonidas",
                "phone": "1234567890"
            }
            """;

    public static final String POST_SPARTAN_RESPONSE = """
            {
              "message": "Successfully created the Spartan.",
              "totalElement": 1,
              "data": {
                "uuid": "xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx",
                "name": "Leonidas",
                "gender": "Male",
                "phone": "1234567890"
              }
            }
            """;

    public static final String GET_SINGLE_SPARTAN_RESPONSE = """
            {
              "message": "Successfully retrieved the Spartan.",
              "totalElement": 1,
              "data": {
                "uuid": "xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx",
                "name": "Leonidas",
                "gender": "Male",
                "phone": "1234567890"
              }
            }
            """;

    public static final String GET_MULTIPLE_SPARTANS_RESPONSE = """
            {
              "message": "Successfully retrieved all the Spartans.",
              "totalElement": 4,
              "data": [
                {
                  "uuid": "xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx",
                  "name": "Leonidas",
                  "gender": "Male",
                  "phone": "1234567890"
                },
                {
                  "uuid": "xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx",
                  "name": "Kalliope",
                  "gender": "Female",
                  "phone": "8527361480"
                },
                {
                  "uuid": "xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx",
                  "name": "Agesilaus",
                  "gender": "Male",
                  "phone": "9876543210"
                },
                {
                  "uuid": "xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx",
                  "name": "Pausanias",
                  "gender": "Female",
                  "phone": "1122334455"
                }
              ]
            }
            """;

    public static final String SEARCH_MULTIPLE_SPARTANS_RESPONSE = """
            {
              "message": "Successfully queried all the Spartans.",
              "totalElement": 4,
              "data": [
                {
                  "uuid": "xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx",
                  "name": "Leonidas",
                  "gender": "Male",
                  "phone": "1234567890"
                },
                {
                  "uuid": "xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx",
                  "name": "Kalliope",
                  "gender": "Female",
                  "phone": "8527361480"
                },
                {
                  "uuid": "xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx",
                  "name": "Agesilaus",
                  "gender": "Male",
                  "phone": "9876543210"
                },
                {
                  "uuid": "xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx",
                  "name": "Pausanias",
                  "gender": "Female",
                  "phone": "1122334455"
                }
              ]
            }
            """;

    public static final String PUT_PATCH_SPARTAN_RESPONSE = """
            {
              "message": "Successfully updated the Spartan.",
              "totalElement": 1,
              "data": {
                "uuid": "xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx",
                "name": "Leonidas",
                "gender": "Male",
                "phone": "1234567890"
              }
            }
            """;

    public static final String DELETE_SPARTAN_RESPONSE = """
            {
              "message": "Successfully deleted the Spartan."
            }
            """;

    public static final String ERROR_RESPONSE_CREATE_SPARTAN = """
            {
              "message": "Spartan with this ID number already exists.",
              "httpStatus": "CONFLICT",
              "localDateTime": "2025-01-01T00:00:00.0000000"
            }
            """;

    public static final String ERROR_RESPONSE_GET_SINGLE_SPARTAN = """
            {
              "message": "Spartan with the given ID number could not be found.",
              "httpStatus": "NOT_FOUND",
              "localDateTime": "2025-01-01T00:00:00.0000000"
            }
            """;

    public static final String ERROR_RESPONSE_DELETE_SPARTAN = """
            {
              "message": "Spartan with this ID number can not be deleted/already deleted.",
              "httpStatus": "CONFLICT",
              "localDateTime": "2025-01-01T00:00:00.0000000"
            }
            """;

    public static final String ERROR_RESPONSE_VALIDATION_SPARTAN = """
            {
              "message": "Invalid Input(s)",
              "httpStatus": "BAD_REQUEST",
              "localDateTime": "2025-01-01T00:00:00.0000000",
              "errorCount": 3,
              "validationExceptions": [
                {
                  "errorField": "name",
                  "reason": "The name is required."
                },
                {
                  "errorField": "gender",
                  "reason": "The gender is required, and can only have 'Male' or 'Female' values."
                },
                {
                  "errorField": "phone",
                  "reason": "The phone number should be 10 to 13 characters long, and can only include digits."
                }
              ]
            }
            """;

    public static final String ERROR_RESPONSE_VALIDATION_SEARCH_SPARTAN = """
            {
              "message": "Invalid Input(s)",
              "httpStatus": "BAD_REQUEST",
              "localDateTime": "2025-02-02T02:08:15.4063978",
              "errorCount": 2,
              "validationExceptions": [
                {
                  "errorField": "name",
                  "reason": "The 'name contains' can not include only spaces, if provided."
                },
                {
                  "errorField": "gender",
                  "reason": "The gender can only have 'Male' or 'Female' values, if provided."
                }
              ]
            }
            """;

    public static final String GENERIC_ERROR_RESPONSE_JSON = """
            {
              "message": "Action failed: An error occurred!",
              "httpStatus": "INTERNAL_SERVER_ERROR",
              "localDateTime": "2025-01-01T00:00:00.0000000"
            }
            """;

    public static final String GENERIC_ERROR_RESPONSE_XML = """
            <ExceptionWrapper>
             	<message>Action failed: An error occurred!</message>
             	<httpStatus>INTERNAL_SERVER_ERROR</httpStatus>
             	<localDateTime>2025-01-01T00:00:00.0000000</localDateTime>
             </ExceptionWrapper>
            """;

}
