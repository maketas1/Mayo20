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

import static org.junit.jupiter.api.Assertions.*;

public class MascotaService01JUnitTest {

    MascotaService mascotaService;

    @Test
    @DisplayName("Registrar mascota correctamente")
    void testRegistrarMascotaCorrectamente() {
        MascotaRepository mascotaRepository = new MascotaRepositoryImpl();
        ExternalService externalService = new ExternalServiceImpl();
        mascotaService = new MascotaService(mascotaRepository, externalService);

        Propietario propietario = new Propietario("Dany", "Lima", "987654321");
        Mascota mascota = new Mascota();
        mascota.setNombre("Garfield");
        mascota.setPropietario(propietario);

        Mascota registrada = mascotaService.registrarMascota(mascota);

        assertNotNull(registrada, "La mascota no debería ser null.");

        assertSame(mascota, registrada, "La mascota registrada debería ser la misma que la ingresada.");

        assertEquals("Garfield", registrada.getNombre(), "El nombre de la mascota registrada deberia 'Garfield'.");

        assertSame(propietario, registrada.getPropietario(), "El propietario de la mascete registrada deberia el misso que et ingerendo");

        assertEquals( "Dany", registrada.getPropietario().getNombre(), "El nombre del propietario deberán ser 'Dany'.");

        assertEquals( "Lima", registrada.getPropietario().getCiudad(), "La ciudad del propietarie debería ser 'Lima'.");

        assertEquals( "987654321", registrada.getPropietario().getTelefono(), "El Teléfono del granietario debería ser '987654321',");

        assertTrue(registrada.getId() > 0, "ID debe ser positivo");

        assertAll("Propiedades de la mascota",
                () -> assertEquals("Garfield", registrada.getNombre(), "El nombre debería coincidir."),
                () -> assertNotNull( registrada.getPropietario(), "El propietario no debe ser null."),
                () -> assertEquals("Dany", registrada.getPropietario().getNombre(), "El nombre del propietario debe ser Dany."),
                () -> assertEquals("Lima", registrada.getPropietario().getCiudad(), "La ciudad del propietario debe ser Lima."),
                () -> assertEquals("987654321", registrada.getPropietario().getTelefono(), "El telefono del propietario debe ser 987654321")
                );

        assertDoesNotThrow(() -> registrada.getNombre(), "Obtener el nombre de la mascota no deberia lanzar una excepcion.");
        assertDoesNotThrow(() -> registrada.getPropietario().getCiudad(), "Obtener la ciudad del propietario no deberia lanzar una excepcion.");
    }
}
