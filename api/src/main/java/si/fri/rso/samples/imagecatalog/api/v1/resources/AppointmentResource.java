package si.fri.rso.samples.imagecatalog.api.v1.resources;


import com.kumuluz.ee.logs.cdi.Log;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.headers.Header;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.json.JSONObject;
import si.fri.rso.samples.imagecatalog.models.entities.Appointment;
import si.fri.rso.samples.imagecatalog.services.beans.AppointmentBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.logging.Logger;

@Log
@ApplicationScoped
@Path("/appointments")
public class AppointmentResource {
    private Logger log = Logger.getLogger(AppointmentResource.class.getName());

    @Inject
    private AppointmentBean appointmentBean;


    @Context
    protected UriInfo uriInfo;

    @Operation(description = "Get all appointments.", summary = "Get all appointments")
    @APIResponses({
            @APIResponse(responseCode = "200",
                    description = "List of appointments",
                    content = @Content(schema = @Schema(implementation = Appointment.class, type = SchemaType.ARRAY)),
                    headers = {@Header(name = "X-Total-Count", description = "Number of objects in list")}
            )})
    @GET
    public Response getAppointments() {

        List<Appointment> appointments = appointmentBean.getAppointmentFilter(uriInfo);

        return Response.status(Response.Status.OK).entity(appointments).build();
    }

    @Operation(description = "Get all available appointments by service type.", summary = "Get all available appointments by service type")
    @APIResponses({
            @APIResponse(responseCode = "200",
                    description = "List of available appointments by service type",
                    content = @Content(schema = @Schema(implementation = Appointment.class, type = SchemaType.ARRAY)),
                    headers = {@Header(name = "X-Total-Count", description = "Number of objects in list")}
            )})
    @GET
    @Path("/available/{serviceTypeId}")
    public Response getAppointmentsAvailableByServiceType(@Parameter(description = "Service type id", required = true)
                                                              @PathParam("serviceTypeId") Integer serviceTypeId) {

        List<Appointment> appointments = appointmentBean.getAppointmentAvailableByServiceType(serviceTypeId);


        return Response.status(Response.Status.OK).entity(appointments).build();
    }


    @Operation(description = "Add appointment.", summary = "Add appointment")
    @APIResponses({
            @APIResponse(responseCode = "201",
                    description = "Appointment successfully added."
            ),
            @APIResponse(responseCode = "405", description = "Validation error."),
            @APIResponse(responseCode = "409", description = "Appointment already exists.")
    })
    @POST
    public Response createAppointment(@RequestBody(
            description = "Object with appointment data",
            required = true) String jsonString) {
        try{
            JSONObject obj = new JSONObject(jsonString);
            String customer = obj.getString("customer");
            int start = obj.getInt("start");
            int serviceTypeId = obj.getJSONObject("service_type").getInt("id");
            int employeeId = obj.getJSONObject("employee").getInt("id");

            Appointment appointment = appointmentBean.createAppointment(start,customer,serviceTypeId,employeeId);
            if (appointment == null){
                log.info("conflict");
                return Response.status(Response.Status.CONFLICT).build();
            }
            int appointmentId = appointment.getId();
            obj.put("id", appointmentId);
            return Response.status(Response.Status.CREATED).entity(obj.toString()).build();
        }
        catch (Exception e) {
            return Response.status(Response.Status.METHOD_NOT_ALLOWED).build();
        }







    }


    @Operation(description = "Delete appointment.", summary = "Delete appointment")
    @APIResponses({
            @APIResponse(
                    responseCode = "200",
                    description = "Appointment successfully deleted."
            ),
            @APIResponse(
                    responseCode = "404",
                    description = "Not found."
            )
    })
    @DELETE
    @Path("{id}")
    public Response deleteImageMetadata(@Parameter(description = "Appointment id.", required = true)
                                        @PathParam("id") Integer id){

        boolean deleted = appointmentBean.deleteAppointment(id);

        if (deleted) {
            return Response.status(Response.Status.OK).build();
        }
        else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }




}
