package app.api.service;

import java.io.IOException;

interface Url {

    String getBaseUrl(String Url);
    String getStatusCode() throws IOException;

}
