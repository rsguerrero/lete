package controllers;

import static org.junit.Assert.assertNotNull;
import static play.test.Helpers.GET;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.fakeRequest;
import static play.test.Helpers.inMemoryDatabase;
import static play.test.Helpers.route;
import static play.test.Helpers.running;

import org.junit.Test;

import play.mvc.Result;
import play.mvc.Http.RequestBuilder;

public class OpinionesTest {

	
	
	
	@Test
    public void opinionesApiJson() {
		running(fakeApplication(), () -> {
			RequestBuilder request = fakeRequest(GET, "/api/opiniones/0");
    		Result result = route(request);
    		assertNotNull(result);
		});
    }

    @Test
    public void opinionesApiApplicationXml() {
		running(fakeApplication(inMemoryDatabase()), () -> {
			RequestBuilder request = fakeRequest(GET, "/api/opiniones/0").header("Accept", "application/xml");
    		Result result = route(request);
    		assertNotNull(result);
		});
    }

    @Test
    public void opinionesApiTextXml() {
		running(fakeApplication(inMemoryDatabase()), () -> {
			RequestBuilder request = fakeRequest(GET, "/api/opiniones/0").header("Accept", "text/xml");
    		Result result = route(request);
    		assertNotNull(result);
		});
    }
    
    @Test
    public void opinionesUsuarioJson() {
		running(fakeApplication(), () -> {
			RequestBuilder request = fakeRequest(GET, "/usuario/opiniones/0");
    		Result result = route(request);
    		assertNotNull(result);
		});
    }

    @Test
    public void opinionesUsuarioApplicationXml() {
		running(fakeApplication(inMemoryDatabase()), () -> {
			RequestBuilder request = fakeRequest(GET, "/usuario/opiniones/0").header("Accept", "application/xml");
    		Result result = route(request);
    		assertNotNull(result);
		});
    }

    @Test
    public void opinionesUsuarioTextXml() {
		running(fakeApplication(inMemoryDatabase()), () -> {
			RequestBuilder request = fakeRequest(GET, "/usuario/opiniones/0").header("Accept", "text/xml");
    		Result result = route(request);
    		assertNotNull(result);
		});
    }
    
    

}

