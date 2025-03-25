# jpavalidationex

Custom Validations-

@NotNull validates that the annotated property value is not null.

@Size validates that the annotated property value has a size between the attributes min and max;
 can be applied to String, Collection, Map, and array properties.
 
@Min validates that the annotated property has a value not smaller than the value attribute.

@Max validates that the annotated property has a value no larger than the value attribute.

@Email validates that the annotated property is a valid email address.

@NotEmpty validates that the property is not null or empty; can be applied to String,
 Collection, Map, or Array values.
 
@NotBlank can be applied only to text values and validates that the property is not null or whitespace.


<dependency> 
    <groupId>org.springframework.boot</groupId> 
    <artifactId>spring-boot-starter-validation</artifactId> 
</dependency>



@ControllerAdvice
public class CustomValidationHandler extends ResponseEntityExceptionHandler {

	@Override
	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {

			String fieldName = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			errors.put(fieldName, message);
		});
		return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
	}
}



Pojo Class-

@NotNull
    @Size(min = 2, message = "First Name should be more than 2 character")
    private String empFirstName;

    @Pattern(regexp="[A-Za-z]*", message="Last name should not contain space and special characters")
    private String empLastName;

    private String empAddress;
	
	@Range(min = 1000000000, max = 9999999999L, message = "Employee Contact Number must be 10 digit")
    private long custContactNumber;

    @JsonFormat(pattern = "dd-MM-yyyy", timezone = "Asia/Kolkata")
    private Date empDOB;

    @NotNull
    @Email(message = "Email should be valid")
    private String empEmailId;

    @NotNull
    @Size(min = 4, message = "Password at least 4 character")
    private String empPassword;

    /*@DecimalMin(value="1976", message="Employee should not be more than 60 years old")
    private int empDOBYear;*/