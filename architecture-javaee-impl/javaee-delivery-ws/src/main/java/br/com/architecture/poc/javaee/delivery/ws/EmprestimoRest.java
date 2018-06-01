package br.com.architecture.poc.javaee.delivery.ws;


import br.com.architecture.poc.api.domain.*;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author Rafael Torquato
 */
@Path("/emprestimo")
@Consumes(value = {MediaType.APPLICATION_JSON})
@Produces(value = {MediaType.APPLICATION_JSON})
@Stateless
public class EmprestimoRest {

    private static final Integer MAIORIDADE_PENAL = 18;

    @Inject
    private EmprestimoRepository emprestimoRepository;
    @Inject
    private ContratanteRepository contratanteRepository;

    @POST
    public Response contratar(ContratarEmprestimo.Solicitacao solicitacao) {
        ContratarEmprestimo casoUso = new ContratarEmprestimo(contratanteRepository, emprestimoRepository, MAIORIDADE_PENAL);
        try {
            return Response.ok(casoUso.executar(solicitacao)).build();
        } catch (EmprestimoException e) {
            return responseError(e);
        }
    }

    @GET
    @Path("/contratante/{cpf}")
    public Response doContratante(@PathParam("cpf") String cpf) {
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
        return Response.status(status).entity(new ErrorMessage(e)).build();
    }

}
