package br.com.architecture.poc.javaee.delivery.ws;


import br.com.architecture.poc.api.loan.domain.*;
import br.com.architecture.poc.api.loan.usecase.*;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author Rafael Torquato
 */
@Path("/loan")
@Consumes(value = {MediaType.APPLICATION_JSON})
@Produces(value = {MediaType.APPLICATION_JSON})
@Stateless
public class LoanRest {

    @Inject
    private LoanRepository loanRepository;
    @Inject
    private HirerRepository hirerRepository;

    @POST
    public Response hire(HireLoanUseCase.Request request) {
        HireLoanUseCase useCase = new HireLoanUseCase(hirerRepository, loanRepository);
        try {
            return Response.ok(useCase.execute(request)).build();
        } catch (LoanException e) {
            return responseError(e);
        }
    }

    @GET
    @Path("/hirer/{ssn}")
    public Response loansOfHirer(@PathParam("ssn") String ssn) {
        GetHirerLoansUseCase useCase = new GetHirerLoansUseCase(loanRepository, hirerRepository);
        GetHirerLoansUseCase.Request request = new GetHirerLoansUseCase.Request(ssn);
        try {
            return Response.ok(useCase.execute(request)).build();
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
            }
        }
        return Response.status(status).entity(new ErrorMessage(e)).build();
    }

}
