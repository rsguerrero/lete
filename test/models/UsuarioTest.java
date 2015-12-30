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

public class UsuarioTest {
	@Test
    public void camposObligatoriosKO() {
		running(fakeApplication(), () -> {
			Usuario usuario = new Usuario();
			Form<Usuario> form = Form.form(Usuario.class).bind(Json.toJson(usuario));
		    //fallará por los campos obligatorios
			assertTrue(form.hasErrors());
			assertEquals(1, form.field("nick").errors().size());
			assertEquals(1, form.field("password").errors().size());
			
		});
    	}
	
    @Test
    public void crearUsuario() {
		running(fakeApplication(inMemoryDatabase()), () -> {
			Usuario usuario = insertarUsuario();
			
			Usuario usuarioGuardado = Usuario.findUsuarioById(usuario.id);
			assertEquals(usuario.getNick(), usuarioGuardado.getNick());
		});
    	}
    
    @Test
    public void borrarUsuario() {
		running(fakeApplication(inMemoryDatabase()), () -> {
			Usuario usuario = insertarUsuario();
			
			Usuario usuarioGuardado = Usuario.findUsuarioById(usuario.id);
			assertEquals(usuario.getNick(), usuarioGuardado.getNick());
			//una vez guardado lo borramos
			usuario.delete();
			
			Usuario usuarioBorrado = Usuario.findUsuarioById(usuarioGuardado.id);
			assertNull(usuarioBorrado);
		});
    	}
	
	private Usuario insertarUsuario(){
		Usuario usuario = new Usuario();
		usuario.setNick("nick1");
		usuario.setPassword("changeit");
		usuario.save();
		return usuario;
	}
}
