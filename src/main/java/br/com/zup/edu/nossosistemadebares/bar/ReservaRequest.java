package br.com.zup.edu.nossosistemadebares.bar;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ReservaRequest {

    @NotBlank(message = "Digite o nome de quem esta fazendo a reserva!")
    @Size(min = 2)
    private String reservadoPara;

    @NotNull(message = "Digite o numero da mesa desejada!")
    private Long idMesa;

    public Long getIdMesa() {
        return idMesa;
    }

    public void setIdMesa(Long idMesa) {
        this.idMesa = idMesa;
    }

    public String getReservadoPara() {
        return reservadoPara;
    }

    public void setReservadoPara(String reservadoPara) {
        this.reservadoPara = reservadoPara;
    }
}
