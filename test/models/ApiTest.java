package models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.inMemoryDatabase;
import static play.test.Helpers.running;

import org.junit.Test;

import play.data.Form;
import play.libs.Json;

public class ApiTest {
	
	@Test
    public void camposObligatoriosKO() {
		running(fakeApplication(), () -> {
			Api api = new Api();
			Form<Api> form = Form.form(Api.class).bind(Json.toJson(api));
		    //fallará por los campos obligatorios
			assertTrue(form.hasErrors());
			assertEquals(1, form.field("descripcion").errors().size());
			assertEquals(1, form.field("tematica").errors().size());
			assertEquals(1, form.field("url").errors().size());
			//en los campos booleanos no da error porque por defecto debe tomar un false
			assertEquals(0, form.field("gratis").errors().size());
			assertEquals(1, form.field("precioMes").errors().size());
			assertEquals(0, form.field("xml").errors().size());
			assertEquals(0, form.field("json").errors().size());
			assertEquals(0, form.field("html").errors().size());
			assertEquals(0, form.field("text").errors().size());
			assertEquals(0, form.field("bytes").errors().size());
		});
    	}
	
    @Test
    public void crearApi() {
		running(fakeApplication(inMemoryDatabase()), () -> {
			Api api = insertarApi();
			
			Api apiGuardado = Api.findApi(api.id);
			assertEquals(api.getDescripcion(), apiGuardado.getDescripcion());
		});
    	}
    
    @Test
    public void borrarApi() {
		running(fakeApplication(inMemoryDatabase()), () -> {
			Api api = insertarApi();
			
			Api apiGuardado = Api.findApi(api.id);
			assertEquals(api.getDescripcion(), apiGuardado.getDescripcion());
			//una vez guardado lo borramos
			api.delete();
			
			Api apiBorrado = Api.findApi(apiGuardado.id);
			assertNull(apiBorrado);
		});
    	}
	
	private Api insertarApi(){
		Api api = new Api();
		api.setDescripcion("informacion de prueba");
		api.setTematica("informacion de prueba");
		api.setUrl("ihttp://nformacion de prueba");
		api.setGratis(true);
		api.setPrecioMes(9);
		api.setXml(true);
		api.setJson(true);
		api.setHtml(true);
		api.setText(true);
		api.setBytes(true);
		api.save();
		return api;
	}
}
