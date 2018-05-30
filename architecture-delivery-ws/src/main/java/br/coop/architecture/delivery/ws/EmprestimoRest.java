package br.coop.architecture.delivery.ws;

import br.coop.cooperforte.architecture.api.casouso.ListarEmprestimosDoContratante;
import br.coop.cooperforte.architecture.api.dominio.ContratanteRepository;
import br.coop.cooperforte.architecture.api.dominio.EmprestimoRepository;
import br.coop.cooperforte.architecture.api.comum.EmprestimoException;
import br.coop.cooperforte.architecture.api.casouso.ContratarEmprestimo;
import lombok.extern.jbosslog.JBossLog;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author Rafael Torquato (Mirante Tecnologia)
 */
@JBossLog
@Path("/emprestimos")
@Consumes(value = {MediaType.APPLICATION_JSON})
@Produces(value = {MediaType.APPLICATION_JSON})
@Stateless
public class EmprestimoRest {

    private static final Integer MAIORIDADE_PENAL = 18;

    @Inject
    private EmprestimoRepository emprestimoRepository;
    @Inject
    private ContratanteRepository contratanteRepository;
//    @EJB
//    private ContratarEmprestimoEJB contratarEmprestimo;

    @POST
    @Path("/contratar")
    public Response contratar(ContratarEmprestimo.Solicitacao solicitacao) throws EmprestimoException {
        ContratarEmprestimo casoUso = new ContratarEmprestimo(contratanteRepository, emprestimoRepository, MAIORIDADE_PENAL);
        try {
            return Response.ok(casoUso.executar(solicitacao)).build();
        } catch (EmprestimoException e) {
            return responseError(e);
        }
    }

    @GET
    @Path("/contratante/{cpf}")
    public Response doContratante(@PathParam("cpf") String cpf) throws EmprestimoException {
        log.info("CPF: " + cpf);
        ListarEmprestimosDoContratante casoUso = new ListarEmprestimosDoContratante(emprestimoRepository, contratanteRepository);
        ListarEmprestimosDoContratante.Solicitacao entrada = new ListarEmprestimosDoContratante.Solicitacao(cpf);
        try {
            return Response.ok(casoUso.executar(entrada)).build();
        } catch (EmprestimoException e) {
            return responseError(e);
        }
    }

    private Response responseError(EmprestimoException e) {
        Response.Status status = null;
        switch (e.getMensagemErro()) {
            case CONTRATANTE_INEXISTENTE: {
                status = Response.Status.NOT_FOUND;
                break;
            }
            case CONTRATANTE_MORTO: {
                status = Response.Status.BAD_REQUEST;
                break;
            }
        }
        return Response.status(status).entity(e.getMensagemErro().getDescricao()).build();
    }

//    @POST
//    @Path("/contratar2")
//    public Response contratar2(ContratarEmprestimo.Solicitacao solicitacao) throws EmprestimoException {
//        return Response.ok(contratarEmprestimo.executar(solicitacao)).build();
//    }

}
