import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.BDDMockito.given;

import org.example.awesomepizza.controller.OrdineController;
import org.example.awesomepizza.model.Ordine;
import org.example.awesomepizza.service.OrdineService;

import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class OrdineControllerTest {

    private MockMvc mockMvc;

    @Mock
    private OrdineService ordineService;

    @InjectMocks
    private OrdineController ordineController;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(ordineController).build();
    }

    @Test
    public void testCreaOrdine() throws Exception {
        Ordine ordine = new Ordine();
        given(ordineService.creaOrdine(any())).willReturn(ordine);

        mockMvc.perform(post("/ordini")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"dettagliPizza\":\"Margherita\"}"))
                .andExpect(status().isOk());

    }

    @Test
    public void testAggiornaStatoOrdine() throws Exception {
        Ordine ordine = new Ordine();
        given(ordineService.aggiornaStatoOrdine(any(),any())).willReturn(ordine);
        Long ordineId = 1L;
        mockMvc.perform(post("/ordini/" + ordineId + "/stato")
                .contentType(MediaType.APPLICATION_JSON)
                .content("\"IN_PREPARAZIONE\""))
                .andExpect(status().isOk());
    }


    @Test
    public void testGetCodaOrdini() throws Exception {
        List<Ordine> ordini = Arrays.asList(new Ordine(), new Ordine());
        given(ordineService.getCodaOrdini()).willReturn(ordini);
        mockMvc.perform(get("/ordini/coda" ))
                .andExpect(status().isOk());
    }
}
