package br.com.architecture.poc.api.domain;

import br.com.architecture.poc.api.common.CPF;
import br.com.architecture.poc.api.common.CasoUso;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Rafael Torquato
 */
@RequiredArgsConstructor
public class ListarEmprestimosDoContratante implements
        CasoUso<ListarEmprestimosDoContratante.Solicitacao, List<ListarEmprestimosDoContratante.Resposta>> {

    private final EmprestimoRepository emprestimoRepository;
    private final ContratanteRepository contratanteRepository;

    @Override
    public List<Resposta> executar(Solicitacao entrada) throws EmprestimoException {
        Contratante contratante = contratanteRepository.porCPF(new CPF(entrada.cpfContratante));
        erroSeContratanteInexistente(contratante);
        erroSeContratanteMorto(contratante);

        return emprestimoRepository.porContratante(contratante).stream()
                .map(e -> {
                    Resposta r = new Resposta();
                    r.moeda = e.getValor().moeda().toString();
                    r.quantidadeParcelas = e.getParcelas().intValue();
                    r.valor = e.getValor().doubleValue();
                    return r;
                })
                .collect(Collectors.toList());
    }

    private void erroSeContratanteInexistente(Contratante contratante) throws EmprestimoException {
        if (contratante == null)
            throw new EmprestimoException(MensagemErro.CONTRATANTE_INEXISTENTE);
    }

    private void erroSeContratanteMorto(Contratante contratante) throws EmprestimoException {
        if (contratante.morto())
            throw new EmprestimoException(MensagemErro.CONTRATANTE_MORTO);
    }

    @Data
    @AllArgsConstructor
    public static class Solicitacao {
        private String cpfContratante;
    }

    @Data
    public class Resposta {
        private Double valor;
        private String moeda;
        private Integer quantidadeParcelas;
    }

}
