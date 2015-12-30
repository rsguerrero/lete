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

public class OpinionTest {
	@Test
    public void camposObligatoriosKO() {
		running(fakeApplication(), () -> {
			Opinion opinion = new Opinion();
			Form<Opinion> form = Form.form(Opinion.class).bind(Json.toJson(opinion));
		    //fallará por los campos obligatorios
			assertTrue(form.hasErrors());
			assertEquals(1, form.field("api").errors().size());
			assertEquals(1, form.field("usuario").errors().size());
			assertEquals(1, form.field("puntuacion").errors().size());
			//en los campos booleanos no da error porque por defecto debe tomar un false
			assertEquals(0, form.field("facilManejo").errors().size());
			assertEquals(0, form.field("rapidezRespuesta").errors().size());
		});
    	}
	
    @Test
    public void crearOpinion() {
		running(fakeApplication(inMemoryDatabase()), () -> {
			Opinion opinion = insertarApi();
			
			Opinion apiGuardado = Opinion.findOpinion(opinion.id);
			assertEquals(opinion.getVentajas(), apiGuardado.getVentajas());
		});
    	}
    
    @Test
    public void borrarOpinion() {
		running(fakeApplication(inMemoryDatabase()), () -> {
			Opinion opinion = insertarApi();
			
			Opinion apiGuardado = Opinion.findOpinion(opinion.id);
			assertEquals(opinion.getVentajas(), apiGuardado.getVentajas());
			//una vez guardado lo borramos
			opinion.delete();
			
			Opinion apiBorrado = Opinion.findOpinion(apiGuardado.id);
			assertNull(apiBorrado);
		});
    	}
	
	private Opinion insertarApi(){
		Opinion opinion = new Opinion();
		opinion.setApi(new Api());
		opinion.setUsuario(new Usuario());
		opinion.setPuntuacion(5);
		opinion.setFacilManejo(true);
		opinion.setRapidezRespuesta(true);
		opinion.save();
		return opinion;
	}
}