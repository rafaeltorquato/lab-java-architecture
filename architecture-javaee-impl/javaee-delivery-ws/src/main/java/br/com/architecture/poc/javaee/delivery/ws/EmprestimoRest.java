package br.com.architecture.poc.javaee.delivery.ws;


import br.com.architecture.poc.api.loan.domain.*;

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

    @Inject
    private LoanRepository loanRepository;
    @Inject
    private HirerRepository hirerRepository;

    @POST
    public Response contratar(HireLoan.Request request) {
        HireLoan casoUso = new HireLoan(hirerRepository, loanRepository);
        try {
            return Response.ok(casoUso.executar(request)).build();
        } catch (LoanException e) {
            return responseError(e);
        }
    }

    @GET
    @Path("/contratante/{cpf}")
    public Response doContratante(@PathParam("cpf") String cpf) {
        GetHirerLoans casoUso = new GetHirerLoans(loanRepository, hirerRepository);
        GetHirerLoans.Solicitacao entrada = new GetHirerLoans.Solicitacao(cpf);
        try {
            return Response.ok(casoUso.executar(entrada)).build();
        } catch (LoanException e) {
            return responseError(e);
        }
    }

    private Response responseError(LoanException e) {
        Response.Status status;
        switch (e.getErrorMessage()) {
            case HIRER_DOES_NOT_EXIST: {
                status = Response.Status.NOT_FOUND;
                break;
            }
            default: {
                status = Response.Status.BAD_REQUEST;
                break;
            }
        }
        return Response.status(status).entity(new ErrorMessage(e)).build();
    }

}
