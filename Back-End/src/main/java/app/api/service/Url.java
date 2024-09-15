package app.api.service;

import java.io.IOException;

interface Url {

    String setBaseUrl(String Url);
    String getStatusCode() throws IOException;

}
