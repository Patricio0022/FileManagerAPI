import app.api.controller.Controller;
import app.api.service.FileService;
import com.sun.net.httpserver.HttpServer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;

public class ControllerTest {

@InjectMocks
Controller controller;

@Mock
FileService fileService;

@Mock
HttpServer httpServer;

}
