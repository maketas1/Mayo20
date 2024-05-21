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
import static org.assertj.core.api.Assertions.assertThat;

public class MascotaService03AssertJTest {

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

        //Verificar las propiedades de la mascota registrada
        assertThat(registrada).isNotNull();
        assertThat(registrada.getNombre()).isEqualTo("Garfield");
        assertThat(registrada.getPropietario()).isNotNull();
        assertThat(registrada.getPropietario().getNombre()).isEqualTo("Dany");
        assertThat(registrada.getPropietario().getCiudad()).isEqualTo("Lima");
        assertThat(registrada.getPropietario().getTelefono()).isEqualTo("987654321");
        assertThat(registrada).isSameAs(mascota);

        assertThat(registrada.getId()).isPositive();
    }
}
