
package unigap.api.controllerr;

        import jakarta.validation.Valid;
        import lombok.RequiredArgsConstructor;
        import org.springframework.http.ResponseEntity;
        import org.springframework.validation.BindingResult;
        import org.springframework.web.bind.annotation.*;
        import unigap.api.dto.in.UserDTO;
        import unigap.api.dto.in.UserLoginDTO;
        import unigap.api.model.User1;
        import unigap.api.service.UserService;
        import unigap.common.controller.AbstractResponseController;


@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController extends AbstractResponseController {
    private final UserService userService;


    @PostMapping("/register")
    //can we register an "admin" user ?
    public ResponseEntity<?> createUser(
             @RequestBody UserDTO userDTO
    ) {
        try {
            User1 user = userService.createUser(userDTO);;
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /*
    Thêm tk admin
    * */

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @Valid @RequestBody UserLoginDTO userLoginDTO
    ) {
        // Kiểm tra thông tin đăng nhập và sinh token
        try {
            String token = userService.login(
                    userLoginDTO.getPhoneNumber(),
                    userLoginDTO.getPassword()

            );
            // Trả về token trong response
            return responseEntity(
                    () ->{
                        return token;
                    }
            );

        } catch (Exception e) {
            return ResponseEntity.badRequest().build();

        }
    }
}
