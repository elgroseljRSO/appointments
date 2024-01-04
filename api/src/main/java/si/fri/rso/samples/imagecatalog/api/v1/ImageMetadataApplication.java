package si.fri.rso.samples.imagecatalog.api.v1;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;


import com.kumuluz.ee.cors.annotations.CrossOrigin;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;
import org.eclipse.microprofile.openapi.annotations.servers.Server;

@OpenAPIDefinition(info = @Info(title = "Appointments API", version = "v1",
        contact = @Contact(email = "rso@fri.uni-lj.si"),
        license = @License(name = "dev"), description = "API for managing appointments."),
//        servers = @Server(url = "http://localhost:8080/"))
        servers = @Server(url = "http://20.242.208.205:8080"))

@ApplicationPath("/v1")
@CrossOrigin(supportedMethods = "GET, POST, HEAD, PUT, DELETE, OPTIONS")
public class ImageMetadataApplication extends Application {

}
