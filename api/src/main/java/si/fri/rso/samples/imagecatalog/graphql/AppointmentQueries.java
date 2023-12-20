package si.fri.rso.samples.imagecatalog.graphql;

import com.kumuluz.ee.graphql.annotations.GraphQLClass;
import com.kumuluz.ee.graphql.classes.Pagination;
import com.kumuluz.ee.graphql.classes.PaginationWrapper;
import com.kumuluz.ee.graphql.classes.Sort;
import com.kumuluz.ee.graphql.classes.Filter;
import com.kumuluz.ee.graphql.utils.GraphQLUtils;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLQuery;
import si.fri.rso.samples.imagecatalog.models.entities.Appointment;
import si.fri.rso.samples.imagecatalog.services.beans.AppointmentBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@GraphQLClass
@ApplicationScoped
public class AppointmentQueries {

    @Inject
    private AppointmentBean appointmentBean;

    @GraphQLQuery
    public PaginationWrapper<Appointment> allAppointments(@GraphQLArgument(name = "pagination") Pagination pagination,
                                                          @GraphQLArgument(name = "sort") Sort sort,
                                                          @GraphQLArgument(name = "filter") Filter filter) {

        return GraphQLUtils.process(appointmentBean.getAppointments(), pagination, sort, filter);
    }



}
