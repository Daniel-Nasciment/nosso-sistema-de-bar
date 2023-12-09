package br.com.zup.edu.nossosistemadebares.bar;

import javax.persistence.*;
import java.time.LocalDateTime;

import static br.com.zup.edu.nossosistemadebares.bar.StatusOcupacao.*;
import static java.time.LocalDateTime.*;

@Entity
public class Mesa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusOcupacao status = LIVRE;

    @Column
    private String reservadoPara;

    @Column(nullable = false)
    private LocalDateTime criadoEm = now();

    @Column(nullable = false)
    private LocalDateTime atualizadoEm = now();

    @Column(nullable = false)
    private Integer capacidade;

    public Mesa(Integer capacidade){
        this.capacidade=capacidade;
    }

    /**
     * @deprecated
     */
    @Deprecated
    public Mesa() {
    }

    public Long getId() {
        return id;
    }

    public void reservar(String reservadoPara) {
        this.status = OCUPADO;
        this.reservadoPara = reservadoPara;
    }

    public StatusOcupacao getStatus() {
        return status;
    }
}
