package br.com.zup.edu.nossosistemadebares.bar;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/mesas")
public class CadastrarMesaController {
    private final MesaRepository repository;

    public CadastrarMesaController(MesaRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid MesaRequest request, UriComponentsBuilder uriComponentsBuilder){

        Mesa mesa = request.paraMesa();

        repository.save(mesa);

        URI location = uriComponentsBuilder.path("/mesas/{id}")
                .buildAndExpand(mesa.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }


    @PutMapping
    @Transactional
    public ResponseEntity<?> reservar(@RequestBody @Valid ReservaRequest reservaRequest) throws IllegalAccessException {

        Optional<Mesa> mesa = repository.findById(reservaRequest.getIdMesa());

        if(!mesa.isPresent()){
            throw new IllegalAccessException("Mesa não encontrada!!");
        }

        Mesa mesaEncontrada = mesa.get();

        if (mesaEncontrada.getStatus().equals(StatusOcupacao.OCUPADO)){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }

        mesaEncontrada.reservar(reservaRequest.getReservadoPara());

        repository.save(mesaEncontrada);

        return ResponseEntity.ok("Reservado para " + reservaRequest.getReservadoPara());

    }

}
