import app.api.controller.Controller;
import app.api.service.FileService;
import app.api.service.HttpConnection;
import com.sun.net.httpserver.HttpServer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.net.HttpURLConnection;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ControllerTest {

@InjectMocks
Controller controller;

@Mock
FileService fileService;

@Mock
HttpConnection httpConnection;

@Before
public void setUp() throws IOException{
    when(httpConnection.getConnection()).thenReturn(Mockito.mock(HttpURLConnection.class));
    when(httpConnection.getStatusCode()).thenReturn(String.valueOf(HttpURLConnection.HTTP_OK));
}


}
