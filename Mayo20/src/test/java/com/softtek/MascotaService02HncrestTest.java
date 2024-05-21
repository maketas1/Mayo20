package com.softtek;

import com.softtek.modelo.Mascota;
import com.softtek.modelo.Propietario;
import com.softtek.repository.MascotaRepository;
import com.softtek.repository.MascotaRepositoryImpl;
import com.softtek.servicio.ExternalService;
import com.softtek.servicio.ExternalServiceImpl;
import com.softtek.servicio.MascotaService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class MascotaService02HncrestTest {

    MascotaService mascotaService;

    @Test
    @DisplayName("Registrar mascota correctamente")
    void testRegistroMascotaCorrectamente() {
        MascotaRepository mascotaRepository = new MascotaRepositoryImpl();
        ExternalService externalService = new ExternalServiceImpl();
        mascotaService = new MascotaService(mascotaRepository, externalService);

        Propietario propietario = new Propietario("Dany", "Lima", "987654321");
        Mascota mascota = new Mascota();
        mascota.setNombre("Garfield");
        mascota.setPropietario(propietario);
        Mascota registrada = mascotaService.registrarMascota(mascota);

        //verifica la propiedad de la mascota
        assertThat(registrada, is(notNullValue()));
        assertThat(registrada.getNombre(), is(equalTo("Garfield")));
        assertThat(registrada.getPropietario(), is(notNullValue()));
        assertThat(registrada.getPropietario().getNombre(), is(equalTo("Dany")));
        assertThat(registrada.getPropietario().getCiudad(), is(equalTo("Lima")));
        assertThat(registrada.getPropietario().getTelefono(), is(equalTo("987654321")));
        assertThat(registrada, is(sameInstance(mascota)));
        assertThat(registrada, is(notNullValue()));

        //verifica las propiedades del propietario con Hamcrest
        assertThat(registrada.getPropietario(), hasProperty("nombre", is("Dany")));
        assertThat(registrada.getPropietario(), hasProperty("ciudad", is("Lima")));
        assertThat(registrada.getPropietario(), hasProperty("telefono", is("987654321")));

        //Comprobar con mas matchers de Hamcrest
        assertThat(registrada.getId(), is(greaterThanOrEqualTo(0)));
    }
}
