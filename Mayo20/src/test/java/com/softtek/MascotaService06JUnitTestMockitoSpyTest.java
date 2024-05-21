package com.softtek;

import com.softtek.modelo.Mascota;
import com.softtek.modelo.Propietario;
import com.softtek.repository.MascotaRepository;
import com.softtek.servicio.ExternalService;
import com.softtek.servicio.MascotaService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class MascotaService06JUnitTestMockitoSpyTest {

    @InjectMocks
    MascotaService mascotaService;

    @Mock
    MascotaRepository mascotaRepository;

    @Mock
    ExternalService externalService;

    @Test
    @DisplayName("Registrar mascota correctamente")
    void testRegistrarMascotaCorrectamente() {

        Propietario propietario = new Propietario("Dany", "Lima", "987654321");
        Mascota mascota = new Mascota();
        mascota.setNombre("Garfield");
        mascota.setPropietario(propietario);

        when(externalService.validarVacunas(any(Mascota.class))).thenReturn(true);
        when(externalService.verificarRegistroMunicipal(any(Mascota.class))).thenReturn(true);
        when(mascotaRepository.findById(any())).thenReturn(Optional.empty());
        when(mascotaRepository.guardar(any(Mascota.class))).thenReturn(mascota);

        Mascota registrada = mascotaService.registrarMascota(mascota);

        assertNotNull(registrada, "La mascota registrada no deberia ser null.");

        assertSame(mascota, registrada, "La mascota registrada debería ser la misma que la ingresada.");
    }
}
