package controllers;

import static org.junit.Assert.assertNotNull;
import static play.test.Helpers.GET;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.fakeRequest;
import static play.test.Helpers.inMemoryDatabase;
import static play.test.Helpers.route;
import static play.test.Helpers.running;

import org.junit.Test;

import play.mvc.Http.RequestBuilder;
import play.mvc.Result;


public class ApisTest {
	
	
	
	
	@Test
    public void informacionAPIJson() {
		running(fakeApplication(), () -> {
			RequestBuilder request = fakeRequest(GET, "/api/informacion/0");
    		Result result = route(request);
    		assertNotNull(result);
		});
    }

    @Test
    public void informacionAPIApplicationXml() {
		running(fakeApplication(inMemoryDatabase()), () -> {
			RequestBuilder request = fakeRequest(GET, "/api/informacion/0").header("Accept", "application/xml");
    		Result result = route(request);
    		assertNotNull(result);
		});
    }

    @Test
    public void informacionAPITextXml() {
		running(fakeApplication(inMemoryDatabase()), () -> {
			RequestBuilder request = fakeRequest(GET, "/api/informacion/0").header("Accept", "text/xml");
    		Result result = route(request);
    		assertNotNull(result);
		});
    }
    
    @Test
    public void listadoAPIJson() {
		running(fakeApplication(), () -> {
			RequestBuilder request = fakeRequest(GET, "/apis/listado");
    		Result result = route(request);
    		assertNotNull(result);
		});
    }

    @Test
    public void listadoAPIApplicationXml() {
		running(fakeApplication(inMemoryDatabase()), () -> {
			RequestBuilder request = fakeRequest(GET, "/apis/listado").header("Accept", "application/xml");
    		Result result = route(request);
    		assertNotNull(result);
		});
    }

    @Test
    public void listadoAPITextXml() {
		running(fakeApplication(inMemoryDatabase()), () -> {
			RequestBuilder request = fakeRequest(GET, "/apis/listado").header("Accept", "text/xml");
    		Result result = route(request);
    		assertNotNull(result);
		});
    }

}

