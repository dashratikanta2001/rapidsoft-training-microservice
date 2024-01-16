//package cloudgateway;
//
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class FallBackMethodController {
//
//	@GetMapping("/userServiceFallBack")
//	public String userFallBackMethod()
//	{
//		return "User service taking longer than expected."
//				+ "Please Try again later";
//	}
//	
//	@GetMapping("/departmentServiceFallBack")
//	public String departmentFallBackMethod()
//	{
//		return "Department service taking longer than expected."
//				+ "Please Try again later";
//	}
//	
//}
